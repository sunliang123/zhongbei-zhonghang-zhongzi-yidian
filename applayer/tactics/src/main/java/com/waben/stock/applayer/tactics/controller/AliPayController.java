package com.waben.stock.applayer.tactics.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.waben.stock.applayer.tactics.business.AliPayBusiness;
import com.waben.stock.applayer.tactics.business.PaymentOrderBusiness;
import com.waben.stock.applayer.tactics.dto.payment.AliturnPaymentOrderDto;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.interfaces.dto.publisher.PaymentOrderDto;
import com.waben.stock.interfaces.enums.PaymentState;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.util.CopyBeanUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("tacticsAliPayController")
@RequestMapping("/alipay")
@Api(description = "支付宝支付")
public class AliPayController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AliPayBusiness aliPayBusiness;

	@Autowired
	private PaymentOrderBusiness paymentOrderBusiness;

	@ApiOperation(value = "支付宝转账充值")
	@PostMapping("/turnrecharge")
	public Response<AliturnPaymentOrderDto> turnRecharge(String alipayAccount, BigDecimal amount) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(AliturnPaymentOrderDto.class,
				aliPayBusiness.turnRecharge(SecurityUtil.getUserId(), alipayAccount, amount), false));
	}

	@ApiOperation(value = "支付宝充值")
	@PostMapping("/alirecharge")
	public Response<String> aliRecharge(BigDecimal amount) {
		Response<String> resp = new Response<String>();
		resp.setResult(aliPayBusiness.pay(SecurityUtil.getUserId(), amount));
		return resp;
	}

	/**
	 * 支付宝服务器异步通知支付结果
	 */
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "支付宝回调", hidden = true)
	@PostMapping("/callback")
	public String callback(HttpServletRequest request) throws AlipayApiException {
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
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		logger.info("receive alipay notify result:" + params.toString());
		return aliPayBusiness.callback(params);
	}

	/**
	 * 客户端同步支付结果返回，服务器端验签并解析支付结果，并返回最终支付结果给客户端
	 */
	@ApiOperation(value = "支付宝APP同步支付结果")
	@PostMapping("/sync")
	public Response<String> sync(String paymentNo) throws AlipayApiException {
		Response<String> resp = new Response<String>();
		String result = "fail";
		PaymentOrderDto order = paymentOrderBusiness.findByPaymentNo(paymentNo);
		if (order != null) {
			if (order.getState() == PaymentState.Paid) {
				// 当前订单支付成功
				result = "success";
			} else {
				result = aliPayBusiness.orderQuery(paymentNo);
			}
		} else {
			result = aliPayBusiness.orderQuery(paymentNo);
		}
		resp.setResult(result);
		return resp;
	}

}
