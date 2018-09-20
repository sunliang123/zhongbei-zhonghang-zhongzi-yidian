package com.waben.stock.applayer.tactics.test;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.io.IOUtils;

public class Md5Util {

	/**
	 * 生成 MD5
	 *
	 * @param data
	 *            待处理数据
	 * @return MD5结果
	 */
	public static String md5(String data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 生成 MD5
	 *
	 * @param data
	 *            待处理数据
	 * @return MD5结果
	 */
	public static String md5(byte[] data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(data);
		StringBuilder sb = new StringBuilder();
		for (byte item : array) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString().toUpperCase();
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
		return result;
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(Md5Util.md5("18588557093", "123456"));
		//
		// System.out.println(Md5Util.md5("药师佛佛法诵"));
		// System.out.println(Md5Util.md5("药师佛佛法颂"));
		//
		// System.out.println(Md5Util.md5("123456").toLowerCase());

		byte[] data = IOUtils.toByteArray(new FileInputStream("apk/dingbang_v1.1.1_dingbang.apk"));

		System.out.println(md5(data));
	}
}
