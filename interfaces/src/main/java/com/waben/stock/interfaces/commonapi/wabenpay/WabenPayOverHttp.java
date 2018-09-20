package com.waben.stock.interfaces.commonapi.wabenpay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.interfaces.commonapi.wabenpay.bean.GatewayPayParam;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.GatewayPayRet;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.PayQueryOrderParam;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.PayQueryOrderRet;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.SwiftPayParam;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.SwiftPayRet;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.UnionPayParam;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.UnionPayRet;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.WithdrawParam;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.WithdrawQueryOrderParam;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.WithdrawQueryOrderRet;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.WithdrawRet;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.Md5Util;
import com.waben.stock.interfaces.util.RequestParamBuilder;

public class WabenPayOverHttp {

	public static RestTemplate restTemplate = new RestTemplate();

	private static final Logger logger = LoggerFactory.getLogger(WabenPayOverHttp.class);

	/**
	 * 快捷支付
	 * 
	 * @param param
	 *            请求参数
	 * @param appSecret
	 *            秘钥
	 * @return 响应结果
	 */
	@SuppressWarnings("unchecked")
	public static SwiftPayRet swiftPay(SwiftPayParam param, String appSecret) {
		String requestUrl = "http://47.106.62.170:8080/PAY/V1/swift/pay";
		// 签名
		String sign = Md5Util.md5(param.getAppId() + appSecret + param.getTimestamp() + param.getOutOrderNo())
				.toUpperCase();
		param.setSign(sign);
		// 请求参数
		Map<String, Object> paramMap = (Map<String, Object>) JacksonUtil.decode(JacksonUtil.encode(param), Map.class);
		TreeMap<String, Object> sortParamMap = new TreeMap<>(paramMap);
		String queryString = RequestParamBuilder.build(sortParamMap);
		// 发送请求
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		logger.info("请求网贝支付快捷支付接口请求:querystring:{}", queryString);
		HttpEntity<String> requestEntity = new HttpEntity<String>(queryString, requestHeaders);
		String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		logger.info("请求网贝支付快捷支付接口响应:response:{}", response);
		return JacksonUtil.decode(response, SwiftPayRet.class);
	}

	/**
	 * 银联支付
	 * 
	 * @param param
	 *            请求参数
	 * @param appSecret
	 *            秘钥
	 * @return 响应结果
	 */
	@SuppressWarnings("unchecked")
	public static UnionPayRet unionPay(UnionPayParam param, String appSecret) {
		String requestUrl = "http://47.106.62.170:8080/PAY/V1/unionPay";
		// 签名
		String sign = Md5Util.md5(param.getAppId() + appSecret + param.getTimestamp() + param.getOutOrderNo())
				.toUpperCase();
		param.setSign(sign);
		// 请求参数
		Map<String, Object> paramMap = (Map<String, Object>) JacksonUtil.decode(JacksonUtil.encode(param), Map.class);
		TreeMap<String, Object> sortParamMap = new TreeMap<>(paramMap);
		String queryString = RequestParamBuilder.build(sortParamMap);
		// 发送请求
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		logger.info("请求网贝支付网银支付接口请求:querystring:{}", queryString);
		HttpEntity<String> requestEntity = new HttpEntity<String>(queryString, requestHeaders);
		String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		logger.info("请求网贝支付网银支付接口响应:response:{}", response);
		return JacksonUtil.decode(response, UnionPayRet.class);
	}

	/**
	 * 网关支付
	 * 
	 * @param param
	 *            请求参数
	 * @param appSecret
	 *            秘钥
	 * @return 响应结果
	 */
	@SuppressWarnings("unchecked")
	public static GatewayPayRet gatewayPay(GatewayPayParam param, String appSecret) {
		String requestUrl = "http://47.106.62.170:8080/PAY/V1/swift/pay2";
		// 签名
		String sign = Md5Util.md5(param.getAppId() + appSecret + param.getTimestamp() + param.getOutOrderNo())
				.toUpperCase();
		param.setSign(sign);
		// 请求参数
		Map<String, Object> paramMap = (Map<String, Object>) JacksonUtil.decode(JacksonUtil.encode(param), Map.class);
		TreeMap<String, Object> sortParamMap = new TreeMap<>(paramMap);
		String queryString = RequestParamBuilder.build(sortParamMap);
		// 发送请求
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		logger.info("请求网贝支付网银支付接口请求:querystring:{}", queryString);
		HttpEntity<String> requestEntity = new HttpEntity<String>(queryString, requestHeaders);
		String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		logger.info("请求网贝支付网银支付接口响应:response:{}", response);
		return JacksonUtil.decode(response, GatewayPayRet.class);
	}

	/**
	 * 支付查询
	 * 
	 * @param param
	 *            请求参数
	 * @param appSecret
	 *            秘钥
	 * @return 响应结果
	 */
	@SuppressWarnings("unchecked")
	public static PayQueryOrderRet payQuery(PayQueryOrderParam param, String appSecret) {
		String requestUrl = "http://47.106.62.170:8080/PAY/V1/swift/pay";
		// 签名
		String sign = Md5Util.md5(param.getAppId() + appSecret + param.getOutOrderNo() + param.getOrderNo())
				.toUpperCase();
		param.setSign(sign);
		// 请求参数
		Map<String, Object> paramMap = (Map<String, Object>) JacksonUtil.decode(JacksonUtil.encode(param), Map.class);
		TreeMap<String, Object> sortParamMap = new TreeMap<>(paramMap);
		String queryString = RequestParamBuilder.build(sortParamMap);
		// 发送请求
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		logger.info("请求网贝支付查询支付接口请求:querystring:{}", queryString);
		HttpEntity<String> requestEntity = new HttpEntity<String>(queryString, requestHeaders);
		String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		logger.info("请求网贝支付查询支付接口响应:response:{}", response);
		return JacksonUtil.decode(response, PayQueryOrderRet.class);
	}

	/**
	 * 代付
	 * 
	 * @param param
	 *            请求参数
	 * @param appSecret
	 *            秘钥
	 * @return 响应结果
	 */
	@SuppressWarnings("unchecked")
	public static WithdrawRet withdraw(WithdrawParam param, String appSecret) {
		String requestUrl = "http://47.106.62.170:8080/PAY/daifu/submit";
		// 签名
		String sign = Md5Util.md5(param.getAppId() + appSecret + param.getTimestamp() + param.getOutOrderNo())
				.toUpperCase();
		param.setSign(sign);
		// 请求参数
		Map<String, Object> paramMap = (Map<String, Object>) JacksonUtil.decode(JacksonUtil.encode(param), Map.class);
		TreeMap<String, Object> sortParamMap = new TreeMap<>(paramMap);
		String queryString = RequestParamBuilder.build(sortParamMap);
		// 发送请求
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		logger.info("请求网贝支付代付接口请求:querystring:{}", queryString);
		HttpEntity<String> requestEntity = new HttpEntity<String>(queryString, requestHeaders);
		String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		logger.info("请求网贝支付代付接口响应:response:{}", response);
		return JacksonUtil.decode(response, WithdrawRet.class);
	}

	/**
	 * 代付查询
	 * 
	 * @param param
	 *            请求参数
	 * @param appSecret
	 *            秘钥
	 * @return 响应结果
	 */
	@SuppressWarnings("unchecked")
	public static WithdrawQueryOrderRet withdrawQuery(WithdrawQueryOrderParam param, String appSecret) {
		String requestUrl = "http://47.106.62.170:8080/PAY/daifu/getDaifuInfo";
		// 签名
		String sign = Md5Util.md5(param.getAppId() + appSecret + param.getTimestamp() + param.getOutOrderNo())
				.toUpperCase();
		param.setSign(sign);
		// 请求参数
		Map<String, Object> paramMap = (Map<String, Object>) JacksonUtil.decode(JacksonUtil.encode(param), Map.class);
		TreeMap<String, Object> sortParamMap = new TreeMap<>(paramMap);
		String queryString = RequestParamBuilder.build(sortParamMap);
		// 发送请求
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		logger.info("请求网贝代付查询接口请求:querystring:{}", queryString);
		HttpEntity<String> requestEntity = new HttpEntity<String>(queryString, requestHeaders);
		String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		logger.info("请求网贝代付查询接口响应:response:{}", response);
		return JacksonUtil.decode(response, WithdrawQueryOrderRet.class);
	}

	public static void testSwiftPay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		SwiftPayParam param = new SwiftPayParam();
		param.setAppId("52538");
		param.setSubject("测试");
		param.setBody("测试");
		param.setTotalFee(new BigDecimal("0.01"));
		param.setOutOrderNo("18050812262152664");
		param.setFrontSkipUrl("http://test.com");
		param.setReturnUrl("http://test.com");
		param.setTimestamp(sdf.format(new Date()));
		param.setUserId("test");
		param.setVersion("1.0");

		swiftPay(param, "A2EF0FA7583671ED390B");
	}

	public static void testUnionPay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		UnionPayParam param = new UnionPayParam();
		param.setAppId("52538");
		param.setSubject("测试");
		param.setBody("测试");
		param.setTotalFee(new BigDecimal("0.01"));
		param.setOutOrderNo("180508122621522015");
		param.setFrontSkipUrl("http://test.com");
		param.setReturnUrl("http://test.com");
		param.setTimestamp(sdf.format(new Date()));
		param.setCardType("1");
		param.setChannelType("1");
		param.setUserType("1");
		param.setVersion("1.0");
		unionPay(param, "A2EF0FA7583671ED390B");
	}

	public static void testGatewayPay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		GatewayPayParam param = new GatewayPayParam();
		param.setAppId("52538");
		param.setUserId("1");
		param.setSubject("测试");
		param.setBody("测试");
		param.setTotalFee(new BigDecimal("0.01"));
		param.setOutOrderNo("18050812262145401245");
		param.setFrontSkipUrl("http://test.com");
		param.setReturnUrl("http://test.com");
		param.setTimestamp(sdf.format(new Date()));
		param.setVersion("1.0");
		param.setBankCode("03050000");
		gatewayPay(param, "A2EF0FA7583671ED390B");
	}

	public static void testWithdraw() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		WithdrawParam param = new WithdrawParam();
		param.setAppId("52538");
		param.setBankAcctName("罗梦安");
		param.setBankNo("6217931162022497");
		param.setCardType("0");
		param.setOutOrderNo("1805081356321548");
		param.setTimestamp(sdf.format(new Date()));
		param.setTotalAmt(new BigDecimal("0.01"));
		param.setVersion("1.0");

		withdraw(param, "A2EF0FA7583671ED390B");
	}

	public static void testWithdrawQuery() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		WithdrawQueryOrderParam param = new WithdrawQueryOrderParam();
		param.setAppId("52538");
		param.setOutOrderNo("1805081356321548");
		param.setTimestamp(sdf.format(new Date()));
		withdrawQuery(param, "A2EF0FA7583671ED390B");
	}

	public static void testPayQuery() {
		PayQueryOrderParam param = new PayQueryOrderParam();
		param.setAppId("52538");
		param.setOutOrderNo("1805101334499605");
		param.setOrderNo("CTPAY_201805105378");
		payQuery(param, "A2EF0FA7583671ED390B");
	}

	public static void testMain(String[] args) {
		testPayQuery();
	}

}
