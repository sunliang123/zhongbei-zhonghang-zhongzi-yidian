package com.waben.stock.applayer.tactics.payapi.czpay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.applayer.tactics.payapi.czpay.bean.CzPayResponse;
import com.waben.stock.applayer.tactics.payapi.czpay.config.CzPayConfig;
import com.waben.stock.applayer.tactics.payapi.czpay.util.MacUtil;
import com.waben.stock.interfaces.util.JacksonUtil;

public class CzPayOverHttp {

	public static RestTemplate restTemplate = new RestTemplate();

	public static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssms");

	/**
	 * 支付
	 */
	public static CzPayResponse payment(String paymentNo, BigDecimal amount, String bankCode) {
		TreeMap<String, String> paramsMap = new TreeMap<>();
		String sendTime = sf.format(new Date());
		paramsMap.put("sendTime", sendTime);
		paramsMap.put("sendSeqId", paymentNo);
		paramsMap.put("transType", CzPayConfig.transType);// B002 Z002 S002
		paramsMap.put("organizationId", CzPayConfig.organizationId);// 代理商编号
		paramsMap.put("transAmt", String.valueOf(amount.multiply(new BigDecimal(100)).intValue()));// 分为单位
		paramsMap.put("name", "充值");
		paramsMap.put("body", "网银支付充值");
		paramsMap.put("bankCode", bankCode);
		paramsMap.put("cardType", "0");
		paramsMap.put("mobile", CzPayConfig.merchantPhone);
		paramsMap.put("payType", CzPayConfig.payType);
		paramsMap.put("callBackUrl", CzPayConfig.returnUrl);
		paramsMap.put("notifyUrl", CzPayConfig.notifyUrl);
		String makeMac = MacUtil.makeMac(JacksonUtil.encode(paramsMap), CzPayConfig.macKey);
		paramsMap.put("mac", makeMac);// 报文鉴别码
		// 请求参数
		TreeMap<String, String> requestMap = new TreeMap<>();
		requestMap.put("data", JacksonUtil.encode(paramsMap));
		// 请求
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        HttpEntity<String> requestEntity = new HttpEntity<String>("data=" + JacksonUtil.encode(paramsMap), requestHeaders);
		String resultJson = restTemplate.postForObject(CzPayConfig.bankPaymentUrl, requestEntity, String.class);
		return JacksonUtil.decode(resultJson, CzPayResponse.class);
	}
	
}
