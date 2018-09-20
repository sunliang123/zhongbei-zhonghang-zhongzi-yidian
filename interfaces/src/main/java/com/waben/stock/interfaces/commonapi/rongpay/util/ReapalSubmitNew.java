package com.waben.stock.interfaces.commonapi.rongpay.util;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.waben.stock.interfaces.util.JacksonUtil;
/* *
 *类名：ReapalSubmit
 *功能：融宝各接口请求提交类
 *详细：构造融宝各接口发送请求数据，获取远程HTTP数据
 *版本：3.1.2
 *日期：2015-08-15
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究融宝接口使用，只是提供一个参考。
 */

public class ReapalSubmitNew {

	public static String buildSubmitNew(Map<String, String> para, String url, String publicKeyUrl,
			String privateKeyUrl, String privateKeyPwd) throws Exception {
		// 生成签名
		// String mysign = Md5Utils.BuildMysign(para, Key.getKey(merchant_id));
		String merchantId = para.get("merchant_id");
		String mysign = setSignRSA(JacksonUtil.encode(para), "", merchantId, privateKeyUrl, privateKeyPwd);
		para.put("sign", mysign);
		// 将map转化为json 串
		String json = JacksonUtil.encode(para);
		// System.out.println("==【请求参数】==" + json);
		// 加密数据
		Map<String, String> maps = Decipher.encryptData(json, merchantId, publicKeyUrl);
		maps.put("merchant_id", merchantId);
		// 发送请求 并接收
		// System.out.println("==【请求地址】==" + url);
		// System.out.println("==【请求参数】==" + maps);
		String post = HttpClientUtil.post(url, maps);
		return post;
	}

	public static String setSignRSA(String content, String version, String merchantId, String privateKeyUrl,
			String privateKeyPwd) throws Exception {
		// System.out.println("---------[content]----------" + content);
		String result = null;
		String data = resultSign(content);
		// 1.通过融宝私钥
		PrivateKey priKey = null;
		// priKey = RSA.getPvkformPfx("D://cert//itrus001.pfx", "123456");
		priKey = RSA.getPvkformPfx(privateKeyUrl, privateKeyPwd);
		result = sign(data, priKey);
		return result;
	}

	public static String resultSign(String json) throws Exception {
		TreeMap<String, String> map = JacksonUtil.decode(json, new TypeReference<TreeMap<String, String>>() {
		});
		return getMapOrderStr(map);
	}

	public static String getMapOrderStr(Map<String, String> request) {
		List<String> fieldNames = new ArrayList<String>(request.keySet());
		Collections.sort(fieldNames);
		StringBuffer buf = new StringBuffer();
		Iterator<String> itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = String.valueOf(request.get(fieldName));
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				if (!"sign".equals(fieldName) && !"sign_type".equals(fieldName)) {
					buf.append(fieldName + "=" + fieldValue + "&");
				}
			}
		}
		if (buf.length() > 1)
			buf.setLength(buf.length() - 1);
		// System.out.println("签名字符串：" + buf.toString());
		return buf.toString(); // 去掉最后&
	}

	public static String sign(String content, PrivateKey privateKey) throws Exception {
		String charset = "UTF-8";
		Signature signature = Signature.getInstance("SHA256WithRSA");
		signature.initSign(privateKey);
		signature.update(content.getBytes(charset));
		byte[] signed = signature.sign();
		return new String(Base64.encodeBase64(signed));
	}

}
