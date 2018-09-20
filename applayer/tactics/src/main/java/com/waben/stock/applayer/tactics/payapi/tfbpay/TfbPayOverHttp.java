package com.waben.stock.applayer.tactics.payapi.tfbpay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.applayer.tactics.payapi.tfbpay.config.TFBPayConfig;
import com.waben.stock.applayer.tactics.payapi.tfbpay.util.MD5Utils;
import com.waben.stock.applayer.tactics.payapi.tfbpay.util.RSAUtils;
import com.waben.stock.applayer.tactics.payapi.tfbpay.util.RequestUtils;

public class TfbPayOverHttp {

	public static RestTemplate restTemplate = new RestTemplate();
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

	/**
	 * 支付
	 */
	public static String payment(String paymentNo, BigDecimal amount) throws UnsupportedEncodingException {
		TreeMap<String, String> paramsMap = new TreeMap<>();
		paramsMap.put("spid", TFBPayConfig.spid);
		paramsMap.put("sp_userid", TFBPayConfig.sp_userid);
		paramsMap.put("spbillno", paymentNo);
		paramsMap.put("money", String.valueOf(amount.multiply(new BigDecimal(100)).intValue()));
		paramsMap.put("cur_type", TFBPayConfig.cur_type);
		paramsMap.put("return_url", TFBPayConfig.return_url);
		paramsMap.put("notify_url", TFBPayConfig.notify_url);
		paramsMap.put("memo", "aaa");
		paramsMap.put("card_type", "1");
		paramsMap.put("bank_segment", "6666");
		paramsMap.put("user_type", "1");
		paramsMap.put("channel", TFBPayConfig.channel);
		paramsMap.put("encode_type", TFBPayConfig.encode_type);

		String param = RequestUtils.getParamSrc(paramsMap);
		String sign = MD5Utils.sign(param);
		String groupParam = param + "&sign=" + sign;

		String cipherData = RSAUtils.encrypt(groupParam);
		String url = TFBPayConfig.cardPayApplyApi + "?cipher_data={cipher_data}";
		String result = restTemplate.getForObject(url, String.class, cipherData);
		return new String(result.getBytes(StringHttpMessageConverter.DEFAULT_CHARSET), "GBK");
	}

	/**
	 * 代扣
	 */
	public static String withhold(String paymentNo, BigDecimal amount) throws UnsupportedEncodingException {
		TreeMap<String, String> paramsMap = new TreeMap<>();
		paramsMap.put("version", "1.0");
		paramsMap.put("spid", "1800046681");
		paramsMap.put("sp_serialno", paymentNo);
		paramsMap.put("sp_reqtime", sdf.format(new Date()));
		paramsMap.put("tran_amt", String.valueOf(amount.multiply(new BigDecimal(100)).intValue()));
		paramsMap.put("cur_type", "1");
		paramsMap.put("acct_name", "张三");
		paramsMap.put("acct_id", "6230580000000000005");
		paramsMap.put("business_type", "20101");
		paramsMap.put("memo", "bbb");

		String param = RequestUtils.getParamSrc(paramsMap);
		String sign = MD5Utils.sign(param);
		String groupParam = param + "&sign=" + sign;

		String cipherData = RSAUtils.encrypt(groupParam);
		String url = "http://apitest.tfb8.com/cgi-bin/v2.0/api_pay_single.cgi";
		return restTemplate.postForObject(url, "cipher_data=" + cipherData, String.class);
	}

}
