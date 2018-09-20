package com.waben.stock.applayer.strategist.crawler.util.date;

/**
 * Created by huangsan on 2017/10/9.
 */

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;


public class DiffDaysUtil {
    /**
     * @param @param  date1
     * @param @param  date2
     * @param @return 设定文件
     * @return int    返回类型
     * @throws
     * @Title: differentDays
     * @Description: 获取两个日期相差的天数, 不足一天算一天
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            // System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2 - day1;
        }
    }

    /**
     * 返回当前时间距离今天结束还有多少毫秒
     */
    public static Long intevalCurrentTimeWithCurrentLastTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Duration.between(localDateTime, endOfDay).toMillis();
    }
}
