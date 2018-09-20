package com.waben.stock.applayer.tactics.crawler.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * Created by 12 on 2016/11/9.
 */
public class SimpleDateFormatUtil {

    public static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyyMMdd HH:mm:ss.SSS");
    public static final FastDateFormat DATE_FORMAT1 = FastDateFormat.getInstance("yyyyMMdd");
    public static final FastDateFormat DATE_FORMAT2 = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat DATE_FORMAT3 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
    public static final FastDateFormat DATE_FORMAT4 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
    public static final FastDateFormat DATE_FORMAT5 = FastDateFormat.getInstance("yyyyMMddHHmm");
    public static final FastDateFormat DATE_FORMAT6 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final FastDateFormat DATE_FORMAT7 = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");

    public static final FastDateFormat DATE_FORMAT8 = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");
    public static final FastDateFormat DATE_FORMAT9 = FastDateFormat.getInstance("yyyyMMddHHmmss");
    public static final FastDateFormat DATE_FORMAT10 = FastDateFormat.getInstance("yyyyMMddHH:mm:ss.SSS");


    public final static FastDateFormat MINUTES_FORMAT = FastDateFormat.getInstance("HHmm");
    //时间字符串转cron表达式
    public final static FastDateFormat DATE_PATTERN = FastDateFormat.getInstance("ss mm HH dd MM ? yyyy");
    /**
     * 将long型的时间精确到秒格式转成date
     *
     * @return
     */
    public static Date getLongToDate(Long date) {

        return new Date(date * 1000);
    }
    public static Date longToDate(String date){
    	 try {
    		 SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	 Long lDate = new Long(date);
        	 String sDate = s.format(lDate);
        	 Date dDate = s.parse(sDate);
        	 return dDate;
		} catch (Exception e) {
			
		}
    	return null;
    }
    public static String dateToString(Date date) {

        return DATE_FORMAT6.format(date);
    }

    public static String dateToCron(Date date){
        return DATE_PATTERN.format(date);
    }
    public static String dateToString(Date date, FastDateFormat fastDateFormat) {

        return fastDateFormat.format(date);
    }

    public static Date stringToDate(String str) {

        try {

            if (str == null) {
                return null;
            }
            if (str.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                return DATE_FORMAT6.parse(str);
            } else if (str.length() == "yyyy-MM-dd".length()) {
                return DATE_FORMAT2.parse(str);
            } else if (str.length() == "yyyy-MM-dd HH:mm:ss.SSS".length()) {
                return DATE_FORMAT3.parse(str);
            } else if (str.length() == "yyyyMMddHHmm".length()) {
                return DATE_FORMAT5.parse(str);
            } else if (str.length() == "yyyyMMdd".length()) {
                return DATE_FORMAT1.parse(str);
            } else if (str.length() == "yyyy-MM-dd HH:mm".length()) {
                return DATE_FORMAT7.parse(str);
            }
        } catch (ParseException e) {

        }
        return null;
    }

    /**
     * 获取某天开始时间  ：2017-10-01 00:00:00
     * @param date
     * @return
     */
    public static Date getZeroTime(Date date){
        Calendar c = new GregorianCalendar();
        if(date != null){
            c.setTime(date);
        }
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
    /**
     * 获取某天结束时间  ：2017-10-01 23:59:59
     * @param date
     * @return
     */
    public static Date getLastTime(Date date){
        Calendar c = new GregorianCalendar();
        if(date != null){
            c.setTime(date);
        }
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }


//    public static Long getDateToLongSeconds(Date date){
//    	Calendar calendar = Calendar.getInstance();
//    	calendar.setTime(date);
//    	
//    }


}
