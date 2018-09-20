package com.waben.stock.interfaces.commonapi.rongpay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.interfaces.commonapi.rongpay.bean.PrePaymentResponse;
import com.waben.stock.interfaces.commonapi.rongpay.bean.PreSignResponse;
import com.waben.stock.interfaces.commonapi.rongpay.bean.SignListResponse;
import com.waben.stock.interfaces.commonapi.rongpay.bean.SignResponse;
import com.waben.stock.interfaces.commonapi.rongpay.bean.SmsPaymentResponse;
import com.waben.stock.interfaces.commonapi.rongpay.config.ReapalH5Config;
import com.waben.stock.interfaces.commonapi.rongpay.util.Decipher;
import com.waben.stock.interfaces.commonapi.rongpay.util.DecipherH5;
import com.waben.stock.interfaces.commonapi.rongpay.util.Md5Utils;
import com.waben.stock.interfaces.commonapi.rongpay.util.ReapalSubmitNew;
import com.waben.stock.interfaces.commonapi.wabenpay.WabenPayOverHttp;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.StringUtil;

public class RongPayOverHttp {

	public static RestTemplate restTemplate = new RestTemplate();

	private static final Logger logger = LoggerFactory.getLogger(WabenPayOverHttp.class);

	/**
	 * 2.1融宝银行卡预签约
	 */
	public static PreSignResponse preSign(String baseRequestUrl, String merchant_id, String member_id,
			String signOrderNo, String bindCard, String name, String idCard, String phone, String publicKeyUrl,
			String privateKeyUrl, String privateKeyPwd) {
		try {
			String requestUrl = baseRequestUrl + "/delivery/authentication";
			Map<String, String> map = new HashMap<String, String>();
			map.put("merchant_id", merchant_id);
			map.put("member_id", member_id);
			map.put("card_no", bindCard);
			map.put("card_type", "0");
			map.put("order_no", signOrderNo);
			map.put("cert_no", idCard);
			map.put("cert_type", "01");
			map.put("phone", phone);
			map.put("owner", name);
			map.put("version", "1.0.0");

			String json = JacksonUtil.encode(map);
			System.out.println("预签约请求参数==========>" + json);
			String post = ReapalSubmitNew.buildSubmitNew(map, requestUrl, publicKeyUrl, privateKeyUrl, privateKeyPwd);
			// System.out.println("预签约返回结果post==========>" + post);
			// 解密返回的数据
			String res = Decipher.decryptData(post, publicKeyUrl, privateKeyUrl, privateKeyPwd);
			System.out.println("预签约返回结果post解密后==========>" + res);
			return JacksonUtil.decode(res, PreSignResponse.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("请求融宝银行卡预签约接口发生异常!");
		}
	}

	/**
	 * 2.2融宝银行卡签约
	 */
	public static SignResponse sign(String baseRequestUrl, String merchant_id, String signOrderNo, String checkCode,
			String publicKeyUrl, String privateKeyUrl, String privateKeyPwd) {
		try {
			String requestUrl = baseRequestUrl + "/delivery/sign";
			Map<String, String> map = new HashMap<String, String>();
			map.put("merchant_id", merchant_id);
			map.put("order_no", signOrderNo);
			map.put("check_code", checkCode);
			map.put("version", "1.0.0");
			String json = JacksonUtil.encode(map);
			System.out.println("签约请求参数==========>" + json);
			String post = ReapalSubmitNew.buildSubmitNew(map, requestUrl, publicKeyUrl, privateKeyUrl, privateKeyPwd);
			// 解密返回的数据
			String res = Decipher.decryptData(post, publicKeyUrl, privateKeyUrl, privateKeyPwd);
			System.out.println("签约返回结果post解密后==========>" + res);
			return JacksonUtil.decode(res, SignResponse.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("请求融宝银行卡签约接口发生异常!");
		}
	}

	/**
	 * 2.3查询融宝银行卡已签约列表
	 */
	public static SignListResponse signList(String baseRequestUrl, String merchant_id, String member_id, String signNo,
			String publicKeyUrl, String privateKeyUrl, String privateKeyPwd) {
		try {
			String requestUrl = baseRequestUrl + "/delivery/querycontract";
			Map<String, String> map = new HashMap<String, String>();
			map.put("merchant_id", merchant_id);
			map.put("member_id", member_id);
			map.put("sign_no", signNo);
			map.put("version", "1.0.0");
			String json = JacksonUtil.encode(map);
			System.out.println("已签约列表请求参数==========>" + json);
			String post = ReapalSubmitNew.buildSubmitNew(map, requestUrl, publicKeyUrl, privateKeyUrl, privateKeyPwd);
			// System.out.println("已签约列表返回结果post==========>" + post);
			// 解密返回的数据
			String res = Decipher.decryptData(post, publicKeyUrl, privateKeyUrl, privateKeyPwd);
			System.out.println("已签约列表返回结果post解密后==========>" + res);
			return JacksonUtil.decode(res, SignListResponse.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("请求查询融宝银行卡已签约列表接口发生异常!");
		}
	}

	/**
	 * 2.4融宝请求发送支付短信
	 */
	public static PrePaymentResponse prePayment(String baseRequestUrl, String merchant_id, String member_id,
			String order_no, String total_fee, String sign_no, String notify_url, String title, String body,
			String transtime, String publicKeyUrl, String privateKeyUrl, String privateKeyPwd) {
		try {
			String requestUrl = baseRequestUrl + "/delivery/pay";
			Map<String, String> map = new HashMap<String, String>();
			map.put("merchant_id", merchant_id);
			map.put("member_id", member_id);
			map.put("currency", "156");
			map.put("total_fee", total_fee);

			map.put("order_no", order_no);
			map.put("sign_no", sign_no);
			map.put("notify_url", notify_url);
			map.put("title", title);
			map.put("body", body);
			map.put("transtime", transtime);
			map.put("version", "1.0.0");
			String json = JacksonUtil.encode(map);
			System.out.println("融宝请求发送支付短信请求参数==========>" + json);
			String post = ReapalSubmitNew.buildSubmitNew(map, requestUrl, publicKeyUrl, privateKeyUrl, privateKeyPwd);
			// System.out.println("融宝请求发送支付短信返回结果post==========>" + post);
			// 解密返回的数据
			String res = Decipher.decryptData(post, publicKeyUrl, privateKeyUrl, privateKeyPwd);
			System.out.println("融宝请求发送支付短信返回结果post解密后==========>" + res);
			return JacksonUtil.decode(res, PrePaymentResponse.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("请求融宝请求发送支付短信接口发生异常!");
		}
	}

	/**
	 * 2.4融宝验证码确认支付
	 */
	public static SmsPaymentResponse smsPayment(String baseRequestUrl, String merchant_id, String order_no, String check_code,
			String publicKeyUrl, String privateKeyUrl, String privateKeyPwd) {
		try {
			String requestUrl = baseRequestUrl + "/delivery/smspay";
			Map<String, String> map = new HashMap<String, String>();
			map.put("merchant_id", merchant_id);
			map.put("order_no", order_no);
			map.put("check_code", check_code);
			map.put("version", "1.0.0");
			String json = JacksonUtil.encode(map);
			System.out.println("融宝验证码确认支付请求参数==========>" + json);
			String post = ReapalSubmitNew.buildSubmitNew(map, requestUrl, publicKeyUrl, privateKeyUrl, privateKeyPwd);
			// System.out.println("融宝验证码确认支付返回结果post==========>" + post);
			// 解密返回的数据
			String res = Decipher.decryptData(post, publicKeyUrl, privateKeyUrl, privateKeyPwd);
			System.out.println("融宝验证码确认支付返回结果post解密后==========>" + res);
			return JacksonUtil.decode(res, SmsPaymentResponse.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("请求融宝验证码确认支付接口发生异常!");
		}
	}

	/**
	 * 1.1融宝h5快捷支付
	 * 
	 * @return 支付表单html
	 */
	public static String h5QuickPay(String requestUrl, String seller_email, String merchant_id, String notify_url,
			String return_url, String transtime, String member_id, String member_ip, String key, String order_no,
			String title, String body, String total_fee, String publicKeyPath) {
		try {
			TreeMap<String, String> sPara = new TreeMap<String, String>();
			sPara.put("seller_email", seller_email);
			sPara.put("merchant_id", merchant_id);
			sPara.put("notify_url", notify_url);
			if (!StringUtil.isEmpty(return_url)) {
				sPara.put("return_url", return_url);
			}
			sPara.put("transtime", transtime);
			sPara.put("currency", "156");
			sPara.put("member_id", member_id);
			sPara.put("member_ip", member_ip);
			sPara.put("terminal_type", "mobile");
			sPara.put("terminal_info", UUID.randomUUID().toString());
			sPara.put("sign_type", "MD5");
			sPara.put("order_no", order_no);
			sPara.put("title", title);
			sPara.put("body", body);
			// 单位分
			sPara.put("total_fee", total_fee);
			sPara.put("payment_type", "2");
			sPara.put("pay_method", "bankPay");
			// 生成签名结果
			String mysign = Md5Utils.BuildMysign(sPara, key);
			sPara.put("sign", mysign);
			String json = JacksonUtil.encode(sPara);
			// 生成加密数据
			Map<String, String> maps = DecipherH5.encryptData(json, publicKeyPath);
			maps.put("merchant_id", merchant_id);
			// 返回payHtml
			StringBuffer sbHtml = new StringBuffer();
			sbHtml.append("<html>");
			sbHtml.append("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head>");
			sbHtml.append("<body onload=\"javascript:document.all.rongpaysubmit.submit();\">");
			sbHtml.append("<form id=\"rongpaysubmit\" name=\"rongpaysubmit\" action=\"").append(requestUrl)
					.append("\" method=\"post\">");
			sbHtml.append("<input type=\"hidden\" name=\"merchant_id\" value=\"").append(merchant_id).append("\"/>");
			sbHtml.append("<input type=\"hidden\" name=\"data\" value=\"").append(maps.get("data")).append("\"/>");
			sbHtml.append("<input type=\"hidden\" name=\"encryptkey\" value=\"").append(maps.get("encryptkey"))
					.append("\"/>");
			sbHtml.append("</form>");
			sbHtml.append("</body ></html>");
			logger.info("融宝h5快捷支付接口响应:response:{}", sbHtml.toString());
			return sbHtml.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("请求融宝h5快捷支付接口发生异常!");
		}
	}

	public static void testH5QuickPay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String transtime = sdf.format(new Date());
		String order_no = "201854150001";
		h5QuickPay(ReapalH5Config.rongpay_api + "/mobile/portal", ReapalH5Config.seller_email,
				ReapalH5Config.merchant_id, ReapalH5Config.notify_url, ReapalH5Config.return_url, transtime, "1",
				"127.0.0.1", ReapalH5Config.key, order_no, "test", "test", "1", "D:\\cert\\itrus001.cer");
	}

	public static void testPreSign() {
		String baseRequestUrl = "https://testapi.reapal.com/";
		preSign(baseRequestUrl, "100000000000147", "LO1234", "sign180904181225", "6214242710498301", "韩梅梅",
				"210302196001012114", "13220482188", "D:\\cert\\100000000000147.cer", "D:\\cert\\100000000000147.pfx",
				"123456");
	}

	public static void testSign() {
		String baseRequestUrl = "https://testapi.reapal.com/";
		sign(baseRequestUrl, "100000000000147", "11180521000182596", "123456", "D:\\cert\\100000000000147.cer",
				"D:\\cert\\100000000000147.pfx", "123456");
	}

	public static void testSignList() {
		String baseRequestUrl = "https://testapi.reapal.com/";
		signList(baseRequestUrl, "100000000000147", "12345", "RB1805200000717126", "D:\\cert\\100000000000147.cer",
				"D:\\cert\\100000000000147.pfx", "123456");
	}

	public static void testPrePayment() {
		String baseRequestUrl = "https://testapi.reapal.com/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		prePayment(baseRequestUrl, "100000000000147", "12345", "1234564756789789", "1", "RB1805200000717126",
				"http://www.baidu.com", "test", "test", sdf.format(new Date()), "D:\\cert\\100000000000147.cer",
				"D:\\cert\\100000000000147.pfx", "123456");
	}
	
	public static void testSmsPayment() {
		String baseRequestUrl = "https://testapi.reapal.com/";
		smsPayment(baseRequestUrl, "100000000000147", "11180521000182596", "123456", "D:\\cert\\100000000000147.cer",
				"D:\\cert\\100000000000147.pfx", "123456");
	}

	public static void main(String[] args) {
		// testPreSign();
		// testPrePayment();
		testSmsPayment();
	}

}
