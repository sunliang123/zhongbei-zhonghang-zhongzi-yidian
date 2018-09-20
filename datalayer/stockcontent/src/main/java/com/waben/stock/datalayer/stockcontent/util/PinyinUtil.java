package com.waben.stock.datalayer.stockcontent.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {

	/**
	 * 将汉字转换为全拼，拼音首字母大写
	 * 
	 * @param src
	 *            中文
	 * @return 中文全拼
	 */
	public static String getPingYin(String src, boolean isFile) {
		char[] t1 = src.toCharArray();
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);

		StringBuilder result = new StringBuilder();
		try {
			for (int i = 0; i < t1.length; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					String[] charPinyin = PinyinHelper.toHanyuPinyinStringArray(t1[i], format);
					if (charPinyin.length > 0) {
						result.append(
								charPinyin[0].substring(0, 1).toUpperCase() + charPinyin[0].substring(1).toLowerCase());
					}
				} else
					result.append(String.valueOf(t1[i]));
			}
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			throw new RuntimeException("拼音转英文发生异常!");
		}
		String finalStr = filterString(result.toString());
		int fileDianIndex = -1;
		if (isFile) {
			fileDianIndex = finalStr.lastIndexOf(".");
		}
		// 再做一次处理，如果不是数字或者字母，直接去掉
		StringBuilder finalStrBuilder = new StringBuilder();
		for (int i = 0; i < finalStr.length(); i++) {
			char c = finalStr.charAt(i);
			if ((48 <= c && c <= 57) || (65 <= c && c <= 90) || (97 <= c && c <= 122) || c == '@'
					|| i == fileDianIndex) {
				finalStrBuilder.append(c);
			}
		}
		finalStr = finalStrBuilder.toString();
		if (finalStr.length() > 180) {
			finalStr = finalStr.substring(finalStr.length() - 180, finalStr.length());
		}
		return finalStr;
	}

	/**
	 * 获取汉字串拼音首字母
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音首字母
	 */
	public static String getFirstSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
					if (temp != null) {
						pybf.append(temp[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}

	public static String filterString(String source) {
		String[] filterArr = new String[] { " ", " ", " ", "（", "）", "《", "》", "\\(", "\\)", ",", "，", ";", "；", "“",
				"”", "'", "\"", "·", "、" };
		for (String filter : filterArr) {
			source = source.replaceAll(filter, "");
		}
		return source;
	}

	public static void testMain(String[] args) {
		System.out.println(getPingYin("sadfsf三国sdfs演sdfd义sdfdsaf", false));
		System.out.println(getFirstSpell("中国平安A"));
	}

}
