package com.waben.stock.applayer.strategist.business;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.publisher.PaymentOrderDto;
import com.waben.stock.interfaces.enums.PaymentState;
import com.waben.stock.interfaces.enums.PaymentType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.PaymentOrderInterface;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.UniqueCodeGenerator;

@Service("strategistAliPayBusiness")
public class AliPayBusiness {

	private Logger logger = LoggerFactory.getLogger(getClass());

	// 实例化客户端
	private AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfigConstant.URL, AliPayConfigConstant.APPID,
			AliPayConfigConstant.APP_PRIVATE_KEY, AliPayConfigConstant.FORMAT, AliPayConfigConstant.CHARSET,
			AliPayConfigConstant.ALIPAY_PUBLIC_KEY, AliPayConfigConstant.SIGNTYPE);

	@Autowired
	private PaymentOrderInterface paymentOrderReference;

	@Autowired
	private CapitalAccountBusiness accountBusiness;

	public PaymentOrderDto save(PaymentOrderDto paymentOrder) {
		Response<PaymentOrderDto> orderResp = paymentOrderReference.addPaymentOrder(paymentOrder);
		if ("200".equals(orderResp.getCode())) {
			return orderResp.getResult();
		}
		throw new ServiceException(orderResp.getCode());
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

	/********************************************* 支付宝处理 *****************************************/

	public String pay(Long publisherId, BigDecimal amount) {
		// 创建支付订单
		PaymentOrderDto paymentOrder = new PaymentOrderDto();
		paymentOrder.setAmount(amount);
		paymentOrder.setPaymentNo(UniqueCodeGenerator.generatePaymentNo());
		paymentOrder.setPublisherId(publisherId);
		paymentOrder.setType(PaymentType.AliAppPay);
		paymentOrder.setState(PaymentState.Unpaid);
		this.save(paymentOrder);
		// 支付宝签名
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		String amountStr = String.valueOf(amount);
		model.setSubject("充值");
		model.setOutTradeNo(paymentOrder.getPaymentNo());
		model.setTimeoutExpress("30m");
		model.setTotalAmount(amountStr);
		model.setProductCode("QUICK_MSECURITY_PAY");
		model.setGoodsType("0");// 虚拟类商品
		request.setBizModel(model);
		request.setNotifyUrl(AliPayConfigConstant.NOTIFY_URL);
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			String result = URLDecoder.decode(response.getBody(), "UTF-8");
			logger.info("订单支付请求完成:{}", result);
			return response.getBody();
		} catch (AlipayApiException | UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION);
		}
	}

	public String callback(Map<String, String> params) throws AlipayApiException {
		boolean flag = AlipaySignature.rsaCheckV1(params, AliPayConfigConstant.ALIPAY_PUBLIC_KEY,
				AliPayConfigConstant.CHARSET, AliPayConfigConstant.SIGNTYPE);
		if (flag) {
			// 商户订单号
			String outTradeNo = params.get("out_trade_no");
			// 支付宝交易号
			String tradeNo = params.get("trade_no");
			// 支付交易状态
			String status = params.get("trade_status");
			String timestamp = params.get("timestamp");
			logger.info("商户订单号：{},支付宝交易号:{},交易状态为:{},支付时间:{}", outTradeNo, tradeNo, status, timestamp);
			// 交易成功后，需要判断当前商户订单是否已经处理 并处理当前订单状态
			if (status.equals("TRADE_SUCCESS")) {
				payCallback(outTradeNo, PaymentState.Paid);
			}
			return "success";
		}
		return "fail";
	}

	public String orderQuery(String paymentNo) throws AlipayApiException {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		Map<String, String> bizModel = new HashMap<>();
		bizModel.put("out_trade_no", paymentNo);
		request.setBizContent(JacksonUtil.encode(bizModel));
		AlipayTradeQueryResponse response = alipayClient.sdkExecute(request);
		try {
			String result = URLDecoder.decode(response.getBody(), "UTF-8");
			logger.info("订单查询结果：{}", result);
			if (response.getTradeStatus().equals("TRADE_SUCCESS")) {
				payCallback(paymentNo, PaymentState.Paid);
				return "success";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "fail";
	}

}
