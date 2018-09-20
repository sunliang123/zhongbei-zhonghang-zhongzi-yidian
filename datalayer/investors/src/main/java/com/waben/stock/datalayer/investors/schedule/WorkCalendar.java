package com.waben.stock.datalayer.investors.schedule;

import org.quartz.Calendar;
import org.quartz.impl.calendar.HolidayCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Created by yuyidi on 2017/11/29.
 * @desc
 */
public class WorkCalendar extends HolidayCalendar {
    //默认的时间格式。
    public static String DEFAULT_DATE_FROMART = "yyyy-MM-dd";

    Logger logger = LoggerFactory.getLogger(getClass());
    // Add the holiday calendar to the schedule
    //时间格式。
    private String dateFormat = DEFAULT_DATE_FROMART;

    public WorkCalendar(Calendar baseCalendar) {
        super(baseCalendar);
    }

    public WorkCalendar(TimeZone timeZone) {
        super(timeZone);
    }

    public WorkCalendar(Calendar baseCalendar, TimeZone timeZone) {
        super(baseCalendar, timeZone);
    }


    public WorkCalendar(Calendar calendar, String holidays) {
        this(calendar);
        String[] holidayArray = holidays.split(",");
        Date[] dates = null;
        try {
            dates = getDatesFromStrings(holidayArray);
        } catch (ParseException e) {
            logger.error("日期解析异常:{}", e.getMessage());
        }
        if (dates != null && dates.length == holidayArray.length) {
            logger.debug("Excluded dates : " + holidays);
            addExcludedDates(dates);
        } else {
            //某些日期无法解析。
            throw new IllegalArgumentException(
                    "Some configured dates were invalids (not parseable as "
                            + dateFormat + "). Full list of configured dates{"
                            + holidays + "} valid dates " + holidays);
        }
    }

    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * 设置日期格式。
     *
     * @param dateFormat
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    private Date[] getDatesFromStrings(String[] stringDatesArray) throws ParseException {
        if (stringDatesArray == null || stringDatesArray.length == 0)
            return null;
        int length = stringDatesArray.length;
        Date[] dates = new Date[length];
        for (int i = 0; i < length; i++) {
            String stringDate = stringDatesArray[i];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getDateFormat());
            dates[i] = simpleDateFormat.parse(stringDate);

        }
        return dates;
    }

    /**
     * 循环添加数组中的日期到被排除的日期列表中，会跳过那些无法解析的日期。
     */
    private void addExcludedDates(Date[] dates) {
        for (int i = 0; i < dates.length; i++) {
            Date legalHoliday = dates[i];
            addExcludedDate(legalHoliday);
        }
    }
}
