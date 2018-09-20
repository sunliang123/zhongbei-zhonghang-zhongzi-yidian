package com.waben.stock.interfaces.util;

import java.util.UUID;

public class RandomUtil {

	public static char[] randomCharArr = new char[62];

	static {
		int index = 0;
		for (int i = 48; i <= 57; i++) {
			randomCharArr[index] = (char) i;
			index++;
		}
		for (int i = 65; i <= 90; i++) {
			randomCharArr[index] = (char) i;
			index++;
		}
		for (int i = 97; i <= 122; i++) {
			randomCharArr[index] = (char) i;
			index++;
		}
	}

	/**
	 * 获取[0, max)之前的整数，右开区间（即不包括最大数）
	 * 
	 * @param max
	 *            最大数
	 * @return 随机整数
	 */
	public static int getRandomInt(int max) {
		return (int) (Math.random() * max);
	}

	/**
	 * 生成随机码，一次最多生成10个随机数
	 */
	public static String generateRandomCode(int length) {
		double code = Math.random();
		for (int i = 0; i < length; i++) {
			if (code > 0.1 && code < 1) {
				break;
			} else {
				code = Math.random();
			}
		}
		int js = 1;
		for (int i = 0; i < length; i++) {
			js *= 10;
		}
		return String.valueOf(Math.round((code * js)));
	}

	/**
	 * 获取随机字符串
	 *
	 * @return 随机字符串
	 */
	public static String generateRandomStr(int length) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int randomIndex = (int) (Math.random() * randomCharArr.length);
			result.append(randomCharArr[randomIndex]);
		}
		return result.toString();
	}

	/**
	 * 获取随机字符串 Nonce Str
	 *
	 * @return String 随机字符串
	 */
	public static String generateNonceStr() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
	}

}
