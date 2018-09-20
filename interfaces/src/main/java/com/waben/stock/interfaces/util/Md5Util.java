package com.waben.stock.interfaces.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Util {

	/**
	 * 生成 MD5
	 *
	 * @param data
	 *            待处理数据
	 * @return MD5结果
	 */
	public static String md5(String data) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(data.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (byte item : array) {
				sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
			}
			String result = sb.toString();
			if (result.length() < 32) {
				for (int i = 0; i < 32 - result.length(); i++) {
					result = "0" + result;
				}
			}
			return result.toUpperCase();
		} catch(Exception ex) {
			throw new RuntimeException("not support md5 and utf-8");
		}
	}

	public static String md5(String username, String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(username.getBytes());
		md.update(password.getBytes());

		String result = new BigInteger(1, md.digest()).toString(16);
		if (result.length() < 32) {
			for (int i = 0; i < 32 - result.length(); i++) {
				result = "0" + result;
			}
		}
		return result.toUpperCase();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(Md5Util.md5("18588557093", "123456"));

		System.out.println(Md5Util.md5("root"));
		System.out.println(Md5Util.md5("luo"));
	}
}
