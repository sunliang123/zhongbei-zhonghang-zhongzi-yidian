package com.waben.stock.interfaces.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IdCardUtil {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat sdfFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date getBirthday(String idCard) throws ParseException {
		String birthdayStr = idCard.substring(6, 14);
		return sdf.parse(birthdayStr);
	}

	public static Date getAgeDate(String idCard, int age) throws ParseException {
		Date birthday = getBirthday(idCard);
		Calendar cal = Calendar.getInstance();
		cal.setTime(birthday);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + age);
		return cal.getTime();
	}

	public static boolean isBetten18And65(String idCard) throws ParseException {
		Date age18 = getAgeDate(idCard, 18);
		Date age65 = getAgeDate(idCard, 68);
		Date now = new Date();
		if (now.getTime() >= age18.getTime() && now.getTime() < age65.getTime()) {
			return true;
		} else {
			return false;
		}
	}

	public static void testMain(String[] args) throws ParseException {
		String idCard = "420102195001221761";
		System.out.println(isBetten18And65(idCard));
	}

}
