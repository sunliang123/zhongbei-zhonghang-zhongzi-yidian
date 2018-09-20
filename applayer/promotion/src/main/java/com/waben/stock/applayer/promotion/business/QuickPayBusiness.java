package com.waben.stock.applayer.promotion.business;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.waben.stock.applayer.promotion.payapi.paypal.config.PayPalConfig;
import com.waben.stock.applayer.promotion.payapi.paypal.config.RSAUtil;
import com.waben.stock.applayer.promotion.payapi.paypal.utils.HttpUtil;
import com.waben.stock.applayer.promotion.payapi.paypal.utils.LianLianRSA;
import com.waben.stock.applayer.promotion.payapi.wbpay.WBConfig;
import com.waben.stock.applayer.promotion.rabbitmq.RabbitmqConfiguration;
import com.waben.stock.applayer.promotion.rabbitmq.RabbitmqProducer;
import com.waben.stock.applayer.promotion.rabbitmq.message.WithdrawQueryMessage;
import com.waben.stock.interfaces.commonapi.wabenpay.WabenPayOverHttp;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.WithdrawParam;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.WithdrawRet;
import com.waben.stock.interfaces.commonapi.wabenpay.common.WabenBankType;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.WithdrawalsApplyDto;
import com.waben.stock.interfaces.enums.BankType;
import com.waben.stock.interfaces.enums.WithdrawalsApplyState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.util.StringUtil;

@Service("promotionQuickPayBusiness")
public class QuickPayBusiness {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public WithdrawalsApplyBusiness applyBusiness;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Autowired
	private WBConfig wbConfig;

	@Autowired
	private RabbitmqProducer producer;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	private boolean isProd = true;

	@PostConstruct
	public void init() {
		if ("prod".equals(activeProfile)) {
			isProd = true;
		} else {
			isProd = false;
		}
	}

	private Map<String, String> paramter2Map(HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
			// "utf-8");
			params.put(name, valueStr);
		}
		return params;
	}

	public String wabenProtocolCallBack(HttpServletRequest request) {
		Map<String, String> result = paramter2Map(request);
		logger.info("网贝代付回调的结果是:{}", result);
		String paymentNo = result.get("outTradeNo");
		String thirdNo = result.get("transactNo");
		String sign = result.get("signValue");
		Map<String, String> checksign = new TreeMap<>(result);

		checksign.put("transactTime",
				checksign.get("transactTime").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
		String signStr = "";
		for (String keys : checksign.keySet()) {
			if (!"signValue".equals(keys)) {
				signStr += checksign.get(keys);
			}
		}
		signStr += wbConfig.getKey();
		String check = DigestUtils.md5Hex(signStr);
		// 签名验证
		if (paymentNo != null && !"".equals(paymentNo)) {
			// 验证签名
			if (check.equals(sign)) {
				logger.info("网贝代付异步回调");
				WithdrawalsApplyDto apply = applyBusiness.fetchByApplyNo(paymentNo);
				apply.setThirdWithdrawalsNo(thirdNo);
				applyBusiness.revision(apply);
				applyBusiness.changeState(apply.getId(), WithdrawalsApplyState.SUCCESS.getIndex(), null);
				return "SUCCESS";
			}
		}
		return "FALSE";
	}

	public void payWabenWithdrawals(WithdrawalsApplyDto apply) {
		// logger.info("发起提现申请");
		// Map<String, String> request = new TreeMap<>();
		// SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
		// request.put("cardNo", apply.getBankCard());
		// request.put("bankCode", "CCB");
		// request.put("name", apply.getName());
		// request.put("phone", apply.getPhone());
		// request.put("outTradeNo", apply.getApplyNo());
		// request.put("notifyUrl", wbConfig.getProtocol_callback());
		// request.put("amount",
		// apply.getAmount().movePointRight(2).toString());
		// request.put("signType", WBConfig.sign_type);
		// request.put("cardType", WBConfig.card_type);
		// request.put("tradeType", WBConfig.protocol_type);
		// request.put("merchantNo", wbConfig.getMerchantNo());
		// request.put("timeStart", time.format(new Date()));
		// request.put("product", "quick");
		// request.put("payment", "d0");
		// logger.info("发起提现申请:{}_{}_{}_{}", apply.getName(), apply.getIdCard(),
		// apply.getPhone(), apply.getBankCard());
		// String signStr = "";
		// for (String keys : request.keySet()) {
		// signStr += request.get(keys);
		// }
		// signStr += wbConfig.getKey();
		// String sign = DigestUtils.md5Hex(signStr);
		// request.put("sign", sign);
		// String result = FormRequest.doPost(request, WBConfig.protocol_url);
		// logger.info("提现返回:" + result);
		// JSONObject jsStr = JSONObject.parseObject(result);
		// // 修改提现状态
		// applyBusiness.changeState(apply.getId(),
		// "200".equals(jsStr.getString("code"))
		// ? WithdrawalsApplyState.PROCESSING.getIndex() :
		// WithdrawalsApplyState.FAILURE.getIndex(), null);
		// // 如果请求失败 抛出异常
		// if (!"200".equals(jsStr.getString("code"))) {
		// throw new ServiceException(ExceptionConstant.WITHDRAWALS_EXCEPTION,
		// jsStr.getString("message"));
		// }

		WabenBankType bankType = WabenBankType.getByPlateformBankType(BankType.getByCode(apply.getBankCode()));
		if (bankType == null) {
			throw new ServiceException(ExceptionConstant.BANKCARD_NOTSUPPORT_EXCEPTION);
		}
		logger.info("发起提现申请:{}_{}_{}_{}", apply.getName(), apply.getIdCard(), apply.getPhone(), apply.getBankCard());
		WithdrawParam param = new WithdrawParam();
		param.setAppId(wbConfig.getMerchantNo());
		param.setBankAcctName(apply.getName());
		param.setBankNo(apply.getBankCard());
		param.setBankCode(bankType.getCode());
		param.setBankName(bankType.getBank());
		param.setCardType("0");
		param.setOutOrderNo(apply.getApplyNo());
		param.setTimestamp(sdf.format(new Date()));
		BigDecimal realAmount = apply.getAmount();
		if (apply.getProcessFee() != null && apply.getProcessFee().compareTo(BigDecimal.ZERO) > 0) {
			realAmount = realAmount.subtract(apply.getProcessFee());
		}
		param.setTotalAmt(isProd ? realAmount : new BigDecimal("0.01"));
		param.setVersion("1.0");
//		WithdrawRet withdrawRet = WabenPayOverHttp.withdraw(param, wbConfig.getKey());
//		if (1 == withdrawRet.getStatus()) {
//			// 提现请求成功，使用队列查询
//			WithdrawQueryMessage message = new WithdrawQueryMessage();
//			message.setApplyId(apply.getId());
//			message.setAppId(wbConfig.getMerchantNo());
//			message.setOutOrderNo(apply.getApplyNo());
//			message.setOrderNo(withdrawRet.getOrderNo());
//			producer.sendMessage(RabbitmqConfiguration.withdrawQueryQueueName, message);
//			// 更新订单状态
//			apply = applyBusiness.changeState(apply.getId(), WithdrawalsApplyState.PROCESSING.getIndex(), null);
//			apply.setThirdWithdrawalsNo(withdrawRet.getOrderNo());
//			applyBusiness.revision(apply);
//		} else {
//			applyBusiness.changeState(apply.getId(), WithdrawalsApplyState.FAILURE.getIndex(), null);
//			throw new ServiceException(ExceptionConstant.WITHDRAWALS_EXCEPTION);
//		}
		apply = applyBusiness.changeState(apply.getId(),  WithdrawalsApplyState.PROCESSING.getIndex(), null);
		// 发起提现请求前，预使用队列查询
    	WithdrawQueryMessage message = new WithdrawQueryMessage();
    	message.setApplyId(apply.getId());
		message.setAppId(wbConfig.getMerchantNo());
		message.setOutOrderNo(apply.getApplyNo());
		producer.sendMessage(RabbitmqConfiguration.withdrawQueryQueueName, message);
		// 发起提现请求
		WithdrawRet withdrawRet = WabenPayOverHttp.withdraw(param, wbConfig.getKey());
		if(withdrawRet != null && !StringUtil.isEmpty(withdrawRet.getOrderNo())) {
			// 更新支付系统第三方订单状态
			apply.setThirdWithdrawalsNo(withdrawRet.getOrderNo());
        	applyBusiness.revision(apply);
		}
	}

	public void payPalCSA(WithdrawalsApplyDto apply) {
		// 请求提现
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String, String> map = new TreeMap<>();
		map.put("oid_partner", PayPalConfig.oid_partner);
		map.put("api_version", PayPalConfig.csa_version);
		map.put("sign_type", PayPalConfig.sign_type);
		map.put("no_order", apply.getApplyNo());
		map.put("dt_order", time.format(new Date()));
		map.put("money_order", apply.getAmount().toString());
		map.put("card_no", apply.getBankCard());
		map.put("acct_name", apply.getName());
		map.put("info_order", PayPalConfig.info_order);
		map.put("flag_card", PayPalConfig.flag_card);// 对私
		map.put("notify_url", isProd ? PayPalConfig.prod_csa_notifyurl : PayPalConfig.test_csa_notifyurl);
		map.put("memo", PayPalConfig.memo);

		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		String tosign = genSignData(jsonObject);
		logger.info("代付签名的参数是:{}", tosign);
		String sign = RSAUtil.sign(PayPalConfig.private_key, tosign);
		map.put("sign", sign);
		logger.info("代付的参数是:{}", map.toString());
		logger.info("代付请求发起");
		JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(map);
		String sign1 = null;
		try {
			sign1 = LianLianRSA.encrypt(jsonObject1.toJSONString(), PayPalConfig.public_key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject response = new JSONObject();
		response.put("pay_load", sign1);
		response.put("oid_partner", PayPalConfig.oid_partner);
		logger.info("代付请求的参数是:{}", response.toJSONString());
		String result = HttpUtil.doPost(PayPalConfig.csa_url, response, "utf-8");
		JSONObject jsStr = JSONObject.parseObject(result);
		logger.info("代付请求的结果是:{}", jsStr.toJSONString());
		String confirm_code = jsStr.getString("confirm_code");
		// 如果被判定为 疑似重复提交订单则进行确认请求
		if (!StringUtil.isEmpty(confirm_code)) {
			Map<String, String> confirmMap = new TreeMap<>();
			confirmMap.put("oid_partner", PayPalConfig.oid_partner);
			confirmMap.put("sign_type", PayPalConfig.sign_type);
			confirmMap.put("api_version", PayPalConfig.csa_version);
			confirmMap.put("no_order", apply.getApplyNo());
			confirmMap.put("confirm_code", confirm_code);
			map.put("notify_url", isProd ? PayPalConfig.prod_csa_notifyurl : PayPalConfig.test_csa_notifyurl);
			JSONObject confirmObject = (JSONObject) JSONObject.toJSON(confirmMap);
			String confirmToSign = genSignData(confirmObject);
			logger.info("确认提交签名的参数是:{}", confirmToSign);
			String confirmSign = RSAUtil.sign(PayPalConfig.private_key, confirmToSign);
			confirmMap.put("sign", confirmSign);
			logger.info("代付确认的参数是:{}", confirmMap.toString());
			logger.info("代付确认请求发起");
			JSONObject confirmObject1 = (JSONObject) JSONObject.toJSON(confirmMap);
			String confirmSign1 = null;
			try {
				confirmSign1 = LianLianRSA.encrypt(confirmObject1.toJSONString(), PayPalConfig.public_key);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONObject confirmResponse = new JSONObject();
			confirmResponse.put("pay_load", confirmSign1);
			confirmResponse.put("oid_partner", PayPalConfig.oid_partner);
			logger.info("代付确认请求的参数是:{}", confirmResponse.toJSONString());
			String confirmResult = HttpUtil.doPost(PayPalConfig.csa_confirm_url, confirmResponse, "utf-8");
			jsStr = JSONObject.parseObject(confirmResult);
			logger.info("代付确认请求的结果是:{}", jsStr.toJSONString());

		}
		// 修改提现状态
		applyBusiness.changeState(apply.getId(), "0000".equals(jsStr.getString("ret_code"))
				? WithdrawalsApplyState.PROCESSING.getIndex() : WithdrawalsApplyState.FAILURE.getIndex(), null);
		// 如果请求失败 抛出异常
		if (!"0000".equals(jsStr.getString("ret_code"))) {
			throw new ServiceException(ExceptionConstant.WITHDRAWALS_EXCEPTION, jsStr.getString("ret_msg"));
		}
	}

	public void payPalWithholdCallback(String applyNo, WithdrawalsApplyState state, String thirdRespCode,
			String thirdRespMsg) {
		WithdrawalsApplyDto apply = applyBusiness.fetchByApplyNo(applyNo);
		applyBusiness.changeState(apply.getId(), state.getIndex(), null);
	}

	public static String genSignData(com.alibaba.fastjson.JSONObject jsonObject) {
		StringBuffer content = new StringBuffer();

		// 按照key做首字母升序排列
		List<String> keys = new ArrayList<String>(jsonObject.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			if ("sign".equals(key)) {
				continue;
			}
			String value = jsonObject.getString(key);
			// 空串不参与签名
			if (isnull(value)) {
				continue;
			}
			content.append((i == 0 ? "" : "&") + key + "=" + value);

		}
		String signSrc = content.toString();
		if (signSrc.startsWith("&")) {
			signSrc = signSrc.replaceFirst("&", "");
		}
		return signSrc;
	}

	public static boolean isnull(String str) {
		if (null == str || str.equalsIgnoreCase("null") || str.equals("")) {
			return true;
		} else
			return false;
	}

}
