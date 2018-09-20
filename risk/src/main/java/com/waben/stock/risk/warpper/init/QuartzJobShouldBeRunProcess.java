package com.waben.stock.risk.warpper.init;

import org.quartz.Calendar;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by yuyidi on 2018/2/2.
 * @desc
 */
//@Component
//@Order(2)
public class QuartzJobShouldBeRunProcess implements CommandLineRunner {


    @Autowired
    private StockMonitor stockMonitor;

    @Override
    public void run(String... args) throws Exception {
        Scheduler scheduler = stockMonitor.getSchedulerInstance();
        List<String> groupNames = scheduler.getJobGroupNames();
        Calendar calendarAM = scheduler.getCalendar("calendarAM");
        Calendar calendarPM = scheduler.getCalendar("calendarPM");
        for (String name : groupNames) {
            System.out.println(name);
//            Date fireTime = jobExecutionContext.getFireTime();
//            boolean fireTimeInclude = calendarAM.isTimeIncluded(fireTime.getTime());
//            if (fireTimeInclude) {
//                //若
//            }

        }
    }
}
