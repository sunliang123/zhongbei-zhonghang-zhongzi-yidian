package com.waben.stock.datalayer.buyrecord.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("buyrecordHolidayBusiness")
public class HolidayBusiness {

	private static String propPath = "holiday.properties";

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Properties prop = new Properties();

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private SimpleDateFormat daySdf = new SimpleDateFormat("MM-dd");

	private SimpleDateFormat fullSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@PostConstruct
	public void init() {
		try {
			InputStream in = HolidayBusiness.class.getClassLoader().getResourceAsStream(propPath);
			prop.load(in);
		} catch (FileNotFoundException e) {
			logger.error("缓存节假日信息发生异常! {}", e.getMessage());
		} catch (IOException e) {
			logger.error("缓存节假日信息发生异常! {}", e.getMessage());
		}
	}

	public boolean isTradeTime() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Date now = cal.getTime();
		String nowStr = sdf.format(now);
		String dayStr = daySdf.format(now);
		// 判断是否为周六日
		int weekDay = cal.get(Calendar.DAY_OF_WEEK);
		if (weekDay == 7 || weekDay == 1) {
			return false;
		}
		// 判断是否是节假日
		String holiday = prop.getProperty(String.valueOf(year));
		if (holiday != null) {
			if (holiday.indexOf(dayStr) >= 0) {
				return false;
			}
		}
		// 判断是否为9:30~11:30 13:00~15:00
		try {
			boolean isAm = now.getTime() >= fullSdf.parse(nowStr + " 09:30:00").getTime()
					&& now.getTime() < fullSdf.parse(nowStr + " 11:30:00").getTime();
			boolean isPm = now.getTime() >= fullSdf.parse(nowStr + " 13:00:00").getTime()
					&& now.getTime() < fullSdf.parse(nowStr + " 15:00:00").getTime();
			if (!(isAm || isPm)) {
				return false;
			}
		} catch (ParseException e) {
			logger.error("解析时间格式错误!");
		}
		return true;
	}

	public boolean isTradeDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		Date now = cal.getTime();
		String dayStr = daySdf.format(now);
		// 判断是否为周六日
		int weekDay = cal.get(Calendar.DAY_OF_WEEK);
		if (weekDay == 7 || weekDay == 1) {
			return false;
		}
		// 判断是否是节假日
		String holiday = prop.getProperty(String.valueOf(year));
		if (holiday != null) {
			if (holiday.indexOf(dayStr) >= 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取顺延多少个交易日的时间
	 * 
	 * @param date
	 *            时间
	 * @param after
	 *            多少个交易日
	 * @return 日期
	 */
	public Date getAfterTradeDate(Date before, Integer after) {
		Date date = new Date(before.getTime());
		while (after > 0) {
			date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
			if (isTradeDay(date)) {
				after--;
			}
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

}
