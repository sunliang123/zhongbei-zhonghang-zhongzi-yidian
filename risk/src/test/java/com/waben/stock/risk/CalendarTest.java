package com.waben.stock.risk;

import com.waben.stock.interfaces.constants.HolidayConstant;
import org.junit.Test;
import org.quartz.impl.calendar.WeeklyCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    Logger logger = LoggerFactory.getLogger(CalendarTest.class);
    public static void main(String[] args) throws ParseException, InterruptedException {
        // 止盈点位价格 = 买入价格 + ((市值 * 止盈点)/股数)

        // 止损点位价格 = 买入价格 - ((市值 * 止损点)/股数)

        // 止盈预警点位价格 = (止盈点位 - 买入点位) * 90% + 买入点位

        // 止损预警点位价格 = 买入点位 - (买入点位 - 止损点位) * 90%


    }

    @Test
    public void test() {
        int day = 1000 * 60 * 60 * 24;
        WeeklyCalendar workDay = new WeeklyCalendar();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat hd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        Date expireTime = null;
        try {
            expireTime = simpleDateFormat.parse("2018-05-02 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean isWork = workDay.isTimeIncluded(expireTime.getTime());//是否是工作日
        int isHoliyday = HolidayConstant.holiyday_2018.indexOf(hd.format(expireTime));//是否是节假日
        logger.info("是否是工作日:{}",isWork);
        logger.info("是否是节假日:{}",isHoliyday);
        while (isHoliyday>0||!isWork) {
            calendar.setTime(expireTime);
            calendar.add(Calendar.DATE, -1);
            expireTime = calendar.getTime();
            isHoliyday = HolidayConstant.holiyday_2018.indexOf(hd.format(expireTime));
            isWork = workDay.isTimeIncluded(expireTime.getTime());
        }

        logger.info("行权日期：{}",simpleDateFormat.format(expireTime));

        if(hd.format(expireTime).equals(hd.format(new Date()))) {
            logger.info("当前日期：{}",simpleDateFormat.format(new Date()));
        }
    }
}
