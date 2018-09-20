package com.waben.stock.applayer.strategist.crawler.util.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class WeiXinToken {

	static String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID"
			+ "&secret=SECRET";

	static String tikect = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	static String str = "hy1234";

	public static String getWinxinSign(String url, String timestamp,String appId,String secret) {
		tokenUrl = tokenUrl.replace("APPID", appId).replace("SECRET", secret);
		JSONObject tokenObj = JSON.parseObject(HttpTools.doGet(tokenUrl, null,null));
		String accessToken = tokenObj.getString("access_token");
		JSONObject tikectObj = JSON.parseObject(HttpTools.doGet(tikect.replace("ACCESS_TOKEN", accessToken), null,null));
		String tikect = tikectObj.getString("ticket");
		String[] paramArr = new String[]{"jsapi_ticket=" + tikect, "timestamp=" + timestamp, "noncestr=" + str,
				"url=" + url};
		Arrays.sort(paramArr);
		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat("&" + paramArr[1]).concat("&" + paramArr[2]).concat("&" + paramArr[3]);
		String gensignature = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 对拼接后的字符串进行 sha1 加密
			byte[] digest = md.digest(content.toString().getBytes());
			gensignature = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return gensignature;
	}
	public static void testmain(String[] args) {
		System.out.println(getWinxinSign("http://www.jnhyxx.com", "1444555544","wx2e2c989a1088f67c","b7b7f8bb849a9b22a249ca57848e2fd9"));
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 *
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 *
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

}
