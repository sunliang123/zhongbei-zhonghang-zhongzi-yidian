package com.waben.stock.applayer.strategist.business;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.waben.stock.applayer.strategist.payapi.czpay.config.CzPayConfig;
import com.waben.stock.applayer.strategist.payapi.shande.bean.PayRequestBean;
import com.waben.stock.applayer.strategist.payapi.shande.config.SandPayConfig;
import com.waben.stock.applayer.strategist.payapi.shande.utils.FormRequest;
import com.waben.stock.applayer.strategist.payapi.wabenpay.config.WBConfig;
import com.waben.stock.applayer.strategist.rabbitmq.RabbitmqConfiguration;
import com.waben.stock.applayer.strategist.rabbitmq.RabbitmqProducer;
import com.waben.stock.applayer.strategist.rabbitmq.message.WithdrawQueryMessage;
import com.waben.stock.interfaces.commonapi.wabenpay.WabenPayOverHttp;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.WithdrawParam;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.WithdrawRet;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.dto.publisher.PaymentOrderDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.dto.publisher.WithdrawalsOrderDto;
import com.waben.stock.interfaces.enums.PaymentState;
import com.waben.stock.interfaces.enums.PaymentType;
import com.waben.stock.interfaces.enums.WithdrawalsState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.PaymentOrderInterface;
import com.waben.stock.interfaces.service.publisher.PublisherInterface;
import com.waben.stock.interfaces.service.publisher.WithdrawalsOrderInterface;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.StringUtil;
import com.waben.stock.interfaces.util.UniqueCodeGenerator;

@Service("strategistQuickPayBusiness")
public class QuickPayBusiness {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaymentOrderInterface paymentOrderReference;
	@Autowired
	private PublisherInterface publisherReference;
	@Autowired
	private WithdrawalsOrderInterface withdrawalsOrderReference;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	@Autowired
	private CapitalAccountBusiness accountBusiness;

	@Autowired
	private WBConfig wbConfig;
	
	@Autowired
	private RabbitmqProducer producer;
	
	@Value("${spring.profiles.active}")
	private String activeProfile;
    
    private boolean isProd = true;
    
    @PostConstruct
	public void init() {
		if ("prod".equals(activeProfile)) {
			isProd = true;
		} else {
			isProd = false;
		}
	}

	public PaymentOrderDto savePaymentOrder(PaymentOrderDto paymentOrder) {
		Response<PaymentOrderDto> orderResp = paymentOrderReference.addPaymentOrder(paymentOrder);
		if ("200".equals(orderResp.getCode())) {
			return orderResp.getResult();
		}
		throw new ServiceException(orderResp.getCode());
	}

	public Map<String, String> quickpay(BigDecimal amount, String phone) {

		Response<PublisherDto> response = publisherReference.fetchByPhone(phone);
		if (!"200".equals(response.getCode())) {
			throw new ServiceException(response.getCode());
		}
		// 创建订单
		PaymentOrderDto paymentOrder = new PaymentOrderDto();
		paymentOrder.setAmount(amount);
		String paymentNo = UniqueCodeGenerator.generatePaymentNo();
		paymentOrder.setPaymentNo(paymentNo);
		paymentOrder.setType(PaymentType.QuickPay);
		paymentOrder.setState(PaymentState.Unpaid);
		paymentOrder.setPublisherId(response.getResult().getId());
		paymentOrder.setCreateTime(new Date());
		paymentOrder.setUpdateTime(new Date());
		this.savePaymentOrder(paymentOrder);
		// 封装请求参数
		PayRequestBean request = new PayRequestBean();
		request.setUserId(phone.substring(1));
		request.setMchNo(SandPayConfig.mchNo);
		request.setMchType(SandPayConfig.mchType);
		request.setPayChannel(SandPayConfig.payChannel);
		request.setPayChannelTypeNo(SandPayConfig.payChannelTypeNo);
		request.setNotifyUrl(SandPayConfig.notifyUrl);
		request.setFrontUrl(SandPayConfig.fontUrl);
		request.setOrderNo(paymentNo);
		request.setAmount(amount.toString());
		request.setGoodsName(SandPayConfig.goodsName);
		request.setGoodsDesc(SandPayConfig.goodsDesc);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = format.format(date);
		request.setTimeStamp(timeStamp);
		Map<String, String> paramMap = (Map<String, String>) JacksonUtil.decode(JacksonUtil.encode(request), Map.class);
		TreeMap<String, String> sortParamMap = new TreeMap<>(paramMap);
		StringBuilder strForSign = new StringBuilder();
		String toSign = "";
		for (String key : sortParamMap.keySet()) {
			toSign += key + "=" + sortParamMap.get(key) + "&";
		}
		toSign += "key=" + SandPayConfig.key;
		System.out.println(toSign);
		String sign = DigestUtils.md5Hex(toSign);
		sortParamMap.put("sign", sign);
		return sortParamMap;
	}

	public String sdPayReturn() {
		String paymentNo = "";
		String stateStr = "";
		String code = "";
		try {
			return CzPayConfig.webReturnUrl + "?paymentNo" + paymentNo + "&code=" + code + "&message="
					+ URLEncoder.encode(stateStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("utf-8 not supported?");
		}
	}

	public String sdPaycallback(HttpServletRequest request) {
		Map<String, String> responseResult = paramter2Map(request);
		Map<String, String> resultMap = new TreeMap<>(responseResult);
		String toSign = "";
		for (String key : resultMap.keySet()) {
			if (!"msg".equals(key) && !"result".equals(key) && !"sign".equals(key)) {
				if (!StringUtil.isEmpty(resultMap.get(key))) {
					toSign += key + "=" + resultMap.get(key) + "&";
				}
			}
		}
		toSign += "key=" + SandPayConfig.key;
		String resultSign = DigestUtils.md5Hex(toSign);
		String result = responseResult.get("result");
		String sign = responseResult.get("sign");
		String paymentNo = responseResult.get("orderNo");
		String thirdPaymentNo = responseResult.get("gwTradeNo");
		try {
			if (paymentNo != null && !"".equals(paymentNo) && "SUCCESS".equals(result)) {
				// 验证签名
				if (sign.equals(resultSign)) {
					// 支付成功
					logger.info("PC快捷异步通知签名验证通过");
					payCallback(paymentNo, PaymentState.Paid);
				}
				return "success";
			}
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root><retcode>207267</retcode><retmsg></retmsg>支付失败</root>";
		} catch (Exception ex) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root><retcode>207267</retcode><retmsg></retmsg>校验签名失败</root>";
		}
	}

	public void withdrawals(Long publisherId, BigDecimal amount, String name, String phone, String idCard,
			String bankCard, String bankCode, String branchName) {
		// 生成提现订单
		logger.info("保存提现订单");
		WithdrawalsOrderDto order = new WithdrawalsOrderDto();
		String withdrawalsNo = UniqueCodeGenerator.generateWithdrawalsNo();
		order.setWithdrawalsNo(withdrawalsNo);
		order.setAmount(amount);
		order.setState(WithdrawalsState.PROCESSING);
		order.setName(name);
		order.setIdCard(idCard);
		order.setBankCard(bankCard);
		order.setPublisherId(publisherId);
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		this.saveWithdrawalsOrder(order);
		// 请求提现
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String, String> map = new TreeMap();
		map.put("mchNo", SandPayConfig.mchNo);
		map.put("payChannel", SandPayConfig.payChannel);
		map.put("orderNo", withdrawalsNo);
		map.put("amount", amount.toString());
		map.put("bankType", SandPayConfig.bankType);
		map.put("accNo", bankCard);
		map.put("accName", name);
		map.put("bankName", "中国银行");
		map.put("timeStamp", time.format(new Date()));
		// 签名
		String toSign = "";
		for (String key : map.keySet()) {
			toSign += key + "=" + map.get(key) + "&";
		}
		toSign += "key=" + SandPayConfig.key;
		logger.info("代付签名的参数是:{}", toSign);
		String sign = DigestUtils.md5Hex(toSign);
		map.put("sign", sign);
		logger.info("代付的参数是:{}", map.toString());
		logger.info("代付请求发起");
		String result = FormRequest.doPost(map, SandPayConfig.csaUrl);
		JSONObject jsStr = JSONObject.parseObject(result);
		System.out.println(jsStr.toString());
		logger.info("代付请求的结果是:{}", jsStr);
		// CzWithholdResponse resp =
		// CzWithholdOverSocket.withhold(withdrawalsNo, name, bankCard, phone,
		// bankCode, amount);
		// // 提现异常
		JSONObject jsonData = jsStr.getJSONObject("data");
		String resultFlag = jsonData.getString("resultFlag");
		if ("SUCCESS".equals(jsStr.getString("result")) || "0".equals(resultFlag) || "2".equals(resultFlag)) {
			WithdrawalsOrderDto origin = findWithdrawalsOrder(withdrawalsNo);
			accountBusiness.csa(origin.getPublisherId(), origin.getAmount(), order.getId());
			if (origin.getState() != WithdrawalsState.PROCESSED) {
				// 更新代付订单的状态
				withdrawalsOrderReference.changeState(withdrawalsNo, WithdrawalsState.PROCESSED.getIndex());
			}
		} else {
			throw new ServiceException(ExceptionConstant.WITHDRAWALS_EXCEPTION, jsStr.getString("msg"));
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

	public void payCallback(String paymentNo, PaymentState state) {
		PaymentOrderDto origin = findByPaymentNo(paymentNo);
		if (origin.getState() != state) {
			// 更新支付订单的状态
			changeState(paymentNo, state);
			// 给发布人账号中充值
			if (state == PaymentState.Paid) {
				accountBusiness.recharge(origin.getPublisherId(), origin.getAmount(), origin.getId());
			}
		}
	}

	public PaymentOrderDto findByPaymentNo(String paymentNo) {
		Response<PaymentOrderDto> orderResp = paymentOrderReference.fetchByPaymentNo(paymentNo);
		if ("200".equals(orderResp.getCode())) {
			return orderResp.getResult();
		}
		throw new ServiceException(orderResp.getCode());
	}

	public PaymentOrderDto changeState(String paymentNo, PaymentState state) {
		Response<PaymentOrderDto> orderResp = paymentOrderReference.changeState(paymentNo, state.getIndex());
		if ("200".equals(orderResp.getCode())) {
			return orderResp.getResult();
		}
		throw new ServiceException(orderResp.getCode());
	}

	public WithdrawalsOrderDto saveWithdrawalsOrder(WithdrawalsOrderDto withdrawalsOrderDto) {
		Response<WithdrawalsOrderDto> orderResp = withdrawalsOrderReference.saveWithdrawalsOrders(withdrawalsOrderDto,
				withdrawalsOrderDto.getWithdrawalsNo());
		if ("200".equals(orderResp.getCode())) {
			return orderResp.getResult();
		}
		throw new ServiceException(orderResp.getCode());
	}

	public void wbWithdrawals(Long publisherId, BigDecimal amount, String name, String phone, String idCard,
			String bankCard, String bankCode, String bankName) {
//		CapitalAccountDto account = accountBusiness.findByPublisherId(publisherId);
//    	if (account.getState() != null && account.getState() == 2) {
//			throw new ServiceException(ExceptionConstant.CAPITALACCOUNT_FROZEN_EXCEPTION);
//		}
//		logger.info("保存提现订单");
//		String withdrawalsNo = UniqueCodeGenerator.generateWithdrawalsNo();
//		WithdrawalsOrderDto order = new WithdrawalsOrderDto();
//		order.setWithdrawalsNo(withdrawalsNo);
//		order.setAmount(amount);
//		order.setState(WithdrawalsState.PROCESSING);
//		order.setName(name);
//		order.setIdCard(idCard);
//		order.setBankCard(bankCard);
//		order.setPublisherId(publisherId);
//		order.setCreateTime(new Date());
//		order.setUpdateTime(new Date());
//		this.saveWithdrawalsOrders(order);
//
//		logger.info("发起提现申请");
//		Map<String, String> request = new TreeMap<>();
//		SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss");
//		request.put("cardNo", bankCard);
//		request.put("bankCode", bankCode);
//		request.put("name", name);
//		request.put("phone", phone);
//		request.put("outTradeNo", withdrawalsNo);
//		request.put("notifyUrl", wbConfig.getProtocol_callback());
//		request.put("amount", amount.movePointRight(2).toString());
//		request.put("signType", WBConfig.sign_type);
//		request.put("cardType", WBConfig.card_type);
//		request.put("tradeType", WBConfig.protocol_type);
//		request.put("merchantNo", wbConfig.getMerchantNo());
//		request.put("timeStart", time.format(new Date()));
//		request.put("product", "quick");
//        request.put("payment", "d0");
//		String signStr = "";
//		for (String keys : request.keySet()) {
//			signStr += request.get(keys);
//		}
//		signStr += wbConfig.getKey();
//		String sign = DigestUtils.md5Hex(signStr);
//		request.put("sign", sign);
//		String result = FormRequest.doPost(request, WBConfig.protocol_url);
//		logger.info("提现返回:" + result);
//        JSONObject jsStr = JSONObject.parseObject(result);
//        if(!"200".equals(jsStr.getString("code"))){
//            WithdrawalsOrderDto orders = this.findByWithdrawalsNo(withdrawalsNo);
//            accountBusiness.withdrawals(publisherId, orders.getId(),WithdrawalsState.FAILURE);
//            throw new ServiceException(ExceptionConstant.WITHDRAWALS_EXCEPTION);
//        }
		CapitalAccountDto account = accountBusiness.findByPublisherId(publisherId);
    	if (account.getState() != null && account.getState() == 2) {
			throw new ServiceException(ExceptionConstant.CAPITALACCOUNT_FROZEN_EXCEPTION);
		}
        logger.info("保存提现订单");
        String withdrawalsNo = UniqueCodeGenerator.generateWithdrawalsNo();
        WithdrawalsOrderDto order = new WithdrawalsOrderDto();
        order.setWithdrawalsNo(withdrawalsNo);
        order.setAmount(amount);
        order.setState(WithdrawalsState.PROCESSING);
        order.setName(name);
        order.setIdCard(idCard);
        order.setBankCard(bankCard);
        order.setPublisherId(publisherId);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order = this.saveWithdrawalsOrders(order);

        logger.info("发起提现申请:{}_{}_{}_{}", name, idCard, phone, bankCard);
        WithdrawParam param = new WithdrawParam();
		param.setAppId(wbConfig.getMerchantNo());
		param.setBankAcctName(name);
		param.setBankNo(bankCard);
		param.setBankCode(bankCode);
		param.setBankName(bankName);
		param.setCardType("0");
		param.setOutOrderNo(withdrawalsNo);
		param.setTimestamp(sdf.format(date));
		param.setTotalAmt(isProd ? amount : new BigDecimal("0.01"));
		param.setVersion("1.0");
//		WithdrawRet withdrawRet = WabenPayOverHttp.withdraw(param, wbConfig.getKey());
//        if(1 == withdrawRet.getStatus()) {
//        	// 提现请求成功，使用队列查询
//        	WithdrawQueryMessage message = new WithdrawQueryMessage();
//    		message.setAppId(wbConfig.getMerchantNo());
//    		message.setOutOrderNo(withdrawalsNo);
//    		message.setOrderNo(withdrawRet.getOrderNo());
//    		producer.sendMessage(RabbitmqConfiguration.withdrawQueryQueueName, message);
//    		// 更新订单状态
//        	order.setThirdWithdrawalsNo(withdrawRet.getOrderNo());
//        	this.revisionWithdrawalsOrder(order);
//        } else {
//        	WithdrawalsOrderDto orders = this.findByWithdrawalsNo(withdrawalsNo);
//            accountBusiness.withdrawals(publisherId, orders.getId(),WithdrawalsState.FAILURE);
//            throw new ServiceException(ExceptionConstant.WITHDRAWALS_EXCEPTION);
//        }
		// 发起提现请求前，预使用队列查询
    	WithdrawQueryMessage message = new WithdrawQueryMessage();
		message.setAppId(wbConfig.getMerchantNo());
		message.setOutOrderNo(withdrawalsNo);
		producer.sendMessage(RabbitmqConfiguration.withdrawQueryQueueName, message);
		// 发起提现请求
		WithdrawRet withdrawRet = WabenPayOverHttp.withdraw(param, wbConfig.getKey());
		if(withdrawRet != null && !StringUtil.isEmpty(withdrawRet.getOrderNo())) {
			// 更新支付系统第三方订单状态
        	order.setThirdWithdrawalsNo(withdrawRet.getOrderNo());
        	this.revisionWithdrawalsOrder(order);
		}
	}

	public String protocolCallBack(HttpServletRequest request) {
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
				WithdrawalsOrderDto order = this.findByWithdrawalsNo(paymentNo);
				order.setThirdWithdrawalsNo(thirdNo);
				this.revisionWithdrawalsOrder(order);
				if (order.getState() == WithdrawalsState.PROCESSING) {
					accountBusiness.withdrawals(order.getPublisherId(), order.getId(), WithdrawalsState.PROCESSED);
					return "SUCCESS";
				}
			}
		}
		return "FALSE";
	}
	
	public WithdrawalsOrderDto saveWithdrawalsOrders(WithdrawalsOrderDto withdrawalsOrderDto) {
        Response<WithdrawalsOrderDto> orderResp = withdrawalsOrderReference.addWithdrawalsOrder(withdrawalsOrderDto);
        if ("200".equals(orderResp.getCode())) {
            return orderResp.getResult();
        }
        throw new ServiceException(orderResp.getCode());
    }

    public WithdrawalsOrderDto findWithdrawalsOrder(String withdrawalsNo) {
        Response<WithdrawalsOrderDto> orderResp = withdrawalsOrderReference.fetchByWithdrawalsNo(withdrawalsNo);
        if ("200".equals(orderResp.getCode())) {
            return orderResp.getResult();
        }
        throw new ServiceException(orderResp.getCode());
    }

    public WithdrawalsOrderDto findByWithdrawalsNo(String withdrawalsNo) {
        Response<WithdrawalsOrderDto> orderResp = withdrawalsOrderReference.fetchByWithdrawalsNo(withdrawalsNo);
        if ("200".equals(orderResp.getCode())) {
            return orderResp.getResult();
        }
        throw new ServiceException(orderResp.getCode());
    }

    public WithdrawalsOrderDto revisionWithdrawalsOrder(WithdrawalsOrderDto withdrawalsOrderDto) {
        Response<WithdrawalsOrderDto> orderResp = withdrawalsOrderReference.modifyWithdrawalsOrder(withdrawalsOrderDto);
        if ("200".equals(orderResp.getCode())) {
            return orderResp.getResult();
        }
        throw new ServiceException(orderResp.getCode());
    }

}
