package com.waben.stock.applayer.strategist.payapi.wabenpay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.applayer.strategist.payapi.czpay.util.Md5Util;
import com.waben.stock.applayer.strategist.payapi.wabenpay.bean.BindRequestBean;
import com.waben.stock.applayer.strategist.payapi.wabenpay.bean.BindResponseBean;
import com.waben.stock.applayer.strategist.payapi.wabenpay.bean.MessageRequestBean;
import com.waben.stock.applayer.strategist.payapi.wabenpay.bean.MessageResponseBean;
import com.waben.stock.applayer.strategist.payapi.wabenpay.bean.PayRequestBean;
import com.waben.stock.applayer.strategist.payapi.wabenpay.bean.PayResponseBean;
import com.waben.stock.applayer.strategist.payapi.wabenpay.bean.UnionPayRequestBean;
import com.waben.stock.applayer.strategist.payapi.wabenpay.bean.UnionPayResponseBean;
import com.waben.stock.applayer.strategist.payapi.wabenpay.config.WBConfig;
import com.waben.stock.applayer.strategist.payapi.wabenpay.config.WabenPayConfig;
import com.waben.stock.applayer.strategist.payapi.wabenpay.util.RequestParamBuilder;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.util.JacksonUtil;

public class WabenPayOverHttp {

	public static RestTemplate restTemplate = new RestTemplate();

	private static final Logger logger = LoggerFactory.getLogger(WabenPayOverHttp.class);

	@SuppressWarnings("unchecked")
	public static UnionPayResponseBean netbankPay(UnionPayRequestBean request, WBConfig config) {
		try {
			request.setMerchantNo(config.getMerchantNo());
			request.setNotifyUrl(config.getUnionpayNotifyUrl());
			request.setFrontUrl(config.getUnionpayFrontUrl());
			String requestUrl = "http://b.waben.com.cn/api/netbank/pay";
			Map<String, String> paramMap = (Map<String, String>) JacksonUtil.decode(JacksonUtil.encode(request),
					Map.class);
			TreeMap<String, String> sortParamMap = new TreeMap<>(paramMap);
			// 签名
			StringBuilder strForSign = new StringBuilder();
			for (Entry<String, String> entry : sortParamMap.entrySet()) {
				strForSign.append(String.valueOf(entry.getValue()));
			}
			strForSign.append(config.getKey());
			String sign = Md5Util.MD5(strForSign.toString());
			sortParamMap.put("sign", sign.toLowerCase());
			// 发送请求
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			String queryString = RequestParamBuilder.build(sortParamMap);
			logger.info("请求支付平台网银接口请求:signstr:{}:querystring:{}", strForSign.toString(), queryString);
			HttpEntity<String> requestEntity = new HttpEntity<String>(queryString, requestHeaders);
			String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
			logger.info("请求支付平台网银接口响应:response:{}", response);
			Response<UnionPayResponseBean> responseObj = JacksonUtil.decode(response,
					JacksonUtil.getGenericType(Response.class, UnionPayResponseBean.class));
			if ("200".equals(responseObj.getCode()) && responseObj.getResult() != null) {
				return responseObj.getResult();
			} else {
				throw new RuntimeException(String.format("请求支付平台网银接口发生异常，错误代码{}!", responseObj.getCode()));
			}
		} catch (Exception ex) {
			throw new RuntimeException("请求支付平台网银接口发生异常!", ex);
		}
	}

	@SuppressWarnings("unchecked")
	public static BindResponseBean bind(BindRequestBean request) {
		try {
			String requestUrl = "http://b.waben.com.cn/api/quick/bind";
			Map<String, String> paramMap = (Map<String, String>) JacksonUtil.decode(JacksonUtil.encode(request),
					Map.class);
			TreeMap<String, String> sortParamMap = new TreeMap<>(paramMap);
			// 签名
			StringBuilder strForSign = new StringBuilder();
			for (Entry<String, String> entry : sortParamMap.entrySet()) {
				strForSign.append(entry.getValue());
			}
			strForSign.append(WabenPayConfig.key);
			String sign = Md5Util.MD5(strForSign.toString());
			sortParamMap.put("sign", sign.toLowerCase());
			// 发送请求
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			String queryString = RequestParamBuilder.build(sortParamMap);
			logger.info("请求支付平台绑卡接口:signstr:{}:querystring:{}", strForSign.toString(), queryString);
			HttpEntity<String> requestEntity = new HttpEntity<String>(queryString, requestHeaders);
			String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
			logger.info("支付平台绑卡接口响应:response:{}", response);
			Response<BindResponseBean> responseObj = JacksonUtil.decode(response,
					JacksonUtil.getGenericType(Response.class, BindResponseBean.class));
			if ("200".equals(responseObj.getCode()) && responseObj.getResult() != null) {
				return responseObj.getResult();
			} else {
				throw new RuntimeException(String.format("请求支付平台绑卡接口发生异常，错误代码{}!", responseObj.getCode()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("请求支付平台绑卡接口发生异常!", ex);
		}
	}

	@SuppressWarnings("unchecked")
	public static MessageResponseBean message(MessageRequestBean request) {
		try {
			String requestUrl = "http://b.waben.com.cn/api/quick/message";
			Map<String, String> paramMap = (Map<String, String>) JacksonUtil.decode(JacksonUtil.encode(request),
					Map.class);
			TreeMap<String, String> sortParamMap = new TreeMap<>(paramMap);
			// 签名
			StringBuilder strForSign = new StringBuilder();
			for (Entry<String, String> entry : sortParamMap.entrySet()) {
				strForSign.append(entry.getValue());
			}
			strForSign.append(WabenPayConfig.key);
			String sign = Md5Util.MD5(strForSign.toString());
			sortParamMap.put("sign", sign.toLowerCase());
			// 发送请求
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			String queryString = RequestParamBuilder.build(sortParamMap);
			logger.info("请求支付平台快捷发短信接口:signstr:{}:querystring:{}", strForSign.toString(), queryString);
			HttpEntity<String> requestEntity = new HttpEntity<String>(queryString, requestHeaders);
			String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
			logger.info("支付平台快捷发短信接口响应:response:{}", response);
			Response<MessageResponseBean> responseObj = JacksonUtil.decode(response,
					JacksonUtil.getGenericType(Response.class, MessageResponseBean.class));
			if ("200".equals(responseObj.getCode()) && responseObj.getResult() != null) {
				return responseObj.getResult();
			} else {
				throw new RuntimeException(String.format("请求支付平台快捷发短信接口发生异常，错误代码{}!", responseObj.getCode()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("请求支付平台快捷发短信接口发生异常!", ex);
		}
	}

	@SuppressWarnings("unchecked")
	public static PayResponseBean pay(PayRequestBean request) {
		try {
			String requestUrl = "http://b.waben.com.cn/api/quick/pay";
			Map<String, String> paramMap = (Map<String, String>) JacksonUtil.decode(JacksonUtil.encode(request),
					Map.class);
			TreeMap<String, String> sortParamMap = new TreeMap<>(paramMap);
			// 签名
			StringBuilder strForSign = new StringBuilder();
			for (Entry<String, String> entry : sortParamMap.entrySet()) {
				strForSign.append(entry.getValue());
			}
			strForSign.append(WabenPayConfig.key);
			String sign = Md5Util.MD5(strForSign.toString());
			sortParamMap.put("sign", sign.toLowerCase());
			// 发送请求
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			String queryString = RequestParamBuilder.build(sortParamMap);
			logger.info("请求快捷支付接口:signstr:{}:querystring:{}", strForSign.toString(), queryString);
			HttpEntity<String> requestEntity = new HttpEntity<String>(queryString, requestHeaders);
			String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
			logger.info("快捷支付接口响应:response:{}", response);
			Response<PayResponseBean> responseObj = JacksonUtil.decode(response,
					JacksonUtil.getGenericType(Response.class, PayResponseBean.class));
			if ("200".equals(responseObj.getCode()) && responseObj.getResult() != null) {
				return responseObj.getResult();
			} else {
				throw new RuntimeException(String.format("请求快捷支付接口发生异常，错误代码{}!", responseObj.getCode()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("请求快捷支付接口发生异常!", ex);
		}
	}

	public static void testBind() {
		BindRequestBean request = new BindRequestBean();
		request.setMember("1");
		request.setIdNo("410323199309021511");
		request.setName("李振华");
		request.setPhone("13949111791");
		request.setCardNo("6227002432210669566");
		request.setBankCode("CCB");
		bind(request);
	}

	public static void testMessage() {
		MessageRequestBean request = new MessageRequestBean();
		request.setAmount("1100");
		request.setContractNo("1007811356577182");
		request.setMember("3");
		message(request);
	}

	public static void testPay() {
		PayRequestBean request = new PayRequestBean();
		request.setOutTradeNo("test007");
		request.setTimeStart("20180206203441");
		request.setContractNo("1002112031516182");
		request.setBankAccount("6227002432210669566");
		request.setValidaCode("183240");
		request.setNotifyUrl(WabenPayConfig.testNotifyUrl);
		request.setTransactNo("1802062002415148256");
		request.setAmount("1");
		pay(request);
	}

	public static void testUnionPay() {
		WBConfig config = new WBConfig();
		config.setMerchantNo("888888888");
		config.setKey("134fb0bf3bdd54ee9098f4cbc4351b9a");
		config.setUnionpayNotifyUrl("http://luomengan.com/notifyurl");
		config.setUnionpayFrontUrl("http://luomengan.com/fronturl");

		UnionPayRequestBean request = new UnionPayRequestBean();
		request.setAmount("1");
		request.setBankCode("CCB");
		request.setOutTradeNo(String.valueOf(System.currentTimeMillis()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		request.setTimeStart(sdf.format(new Date()));
		netbankPay(request, config);
	}

	public static void testMain(String[] args) {
		// testBind();
		// testMessage();
		// testPay();
		testUnionPay();
	}

}
