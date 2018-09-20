package com.waben.stock.datalayer.investors.init;

import com.waben.stock.datalayer.investors.schedule.WorkCalendar;
import com.waben.stock.datalayer.investors.schedule.job.BuyInStopJob;
import com.waben.stock.datalayer.investors.schedule.job.SellOutStopJob;
import com.waben.stock.datalayer.investors.schedule.job.StockApplyEntrustBuyInJob;
import com.waben.stock.datalayer.investors.schedule.job.StockApplyEntrustSellOutJob;
import com.waben.stock.interfaces.constants.HolidayConstant;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.DailyCalendar;
import org.quartz.impl.calendar.WeeklyCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Created by yuyidi on 2017/11/6.
 * @desc 股票行情监控调度器
 */
@Component
public class StockMonitor implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(getClass());


    /***
     * @author yuyidi 2017-11-27 20:04:24
     * @method run
     * @param strings
     * @return void
     * @description 系统启动完成，启动任务调度器
     */
    public void run(String... strings) throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        // 3、org.quartz.DateBuilder.evenMinuteDate <下一分钟>  -- 通过DateBuilder构建Date
        Date runTime = DateBuilder.evenMinuteDate(new Date());
        WeeklyCalendar workDay = new WeeklyCalendar();
        //排除特定的日期
        WorkCalendar workCalendar = new WorkCalendar(workDay, HolidayConstant.holiyday_2018);
        //排除在外的时间  通过使用invertTimeRange=true  表示倒置
        scheduler.addCalendar("workCalendar", workCalendar, false, false);
        //上午任务
        CronScheduleBuilder scheduleEntrustBuilderAM = CronScheduleBuilder.cronSchedule("0 30 9 * * ?");
        CronScheduleBuilder scheduleBuilderAMStop = CronScheduleBuilder.cronSchedule("0 30 11 * * ?");
        //下午任务
        CronScheduleBuilder scheduleEntrustBuilderPM = CronScheduleBuilder.cronSchedule("0 0 13 * * ?");
        CronScheduleBuilder scheduleBuilderPMStop = CronScheduleBuilder.cronSchedule("0 0 15 * * ?");
        //买入任务
        JobDetail jobBuyIn = JobBuilder.newJob(StockApplyEntrustBuyInJob.class).withIdentity("jobBuyIn", "groupBuyIn")
                .storeDurably(true)
                .build();
        JobDetail jobBuyInStop = JobBuilder.newJob(BuyInStopJob.class).withIdentity("jobBuyInStop", "groupBuyIn")
                .storeDurably(true)
                .build();

        Trigger buyInTriggerBeginAM = newTrigger().withIdentity("buyInTriggerBeginAM", "groupBuyIn").startAt(runTime)
                .withSchedule(scheduleEntrustBuilderAM)
                .modifiedByCalendar("workCalendar")
                .forJob(jobBuyIn)
                .build();
        Trigger buyInTriggerBeginPM = newTrigger().withIdentity("buyInTriggerBeginPM", "groupBuyIn").startAt(runTime)
                .withSchedule(scheduleEntrustBuilderPM)
                .modifiedByCalendar("workCalendar")
                .forJob(jobBuyIn)
                .build();

        Trigger buyInTriggerAMStop = newTrigger().withIdentity("buyInTriggerAMStop", "groupBuyIn").startAt(runTime)
                .withSchedule(scheduleBuilderAMStop)
                .modifiedByCalendar("workCalendar")
                .forJob(jobBuyInStop)
                .build();
        Trigger buyInTriggerPMStop = newTrigger().withIdentity("buyInTriggerPMStop", "groupBuyIn").startAt(runTime)
                .withSchedule(scheduleBuilderPMStop)
                .modifiedByCalendar("workCalendar")
                .forJob(jobBuyInStop)
                .build();
        //卖出任务
        JobDetail jobSellOut = JobBuilder.newJob(StockApplyEntrustSellOutJob.class).withIdentity("jobSellOut",
                "groupSellOut")
                .storeDurably(true)
                .build();
        JobDetail jobSellOutStop = JobBuilder.newJob(SellOutStopJob.class).withIdentity("jobSellOutStop",
                "groupSellOut")
                .storeDurably(true)
                .build();
        Trigger sellOutTriggerBeginAM = newTrigger().withIdentity("sellOutTriggerBeginAM", "groupSellOut").startAt(runTime)
                .withSchedule(scheduleEntrustBuilderAM)
                .modifiedByCalendar("workCalendar")
                .forJob(jobSellOut)
                .build();
        Trigger sellOutTriggerBeginPM = newTrigger().withIdentity("sellOutTriggerBeginPM", "groupSellOut").startAt(runTime)
                .withSchedule(scheduleEntrustBuilderPM)
                .modifiedByCalendar("workCalendar")
                .forJob(jobSellOut)
                .build();
        Trigger sellOutTriggerAMStop = newTrigger().withIdentity("sellOutTriggerAMStop", "groupSellOut").startAt
                (runTime)
                .withSchedule(scheduleBuilderAMStop)
                .modifiedByCalendar("workCalendar")
                .forJob(jobSellOutStop)
                .build();
        Trigger sellOutTriggerPMStop = newTrigger().withIdentity("sellOutTriggerPMStop", "groupSellOut").startAt
                (runTime)
                .withSchedule(scheduleBuilderPMStop)
                .modifiedByCalendar("workCalendar")
                .forJob(jobSellOutStop)
                .build();

        scheduler.addJob(jobBuyIn, true);
        scheduler.scheduleJob(buyInTriggerBeginAM);
        scheduler.scheduleJob(buyInTriggerBeginPM);
        scheduler.addJob(jobBuyInStop, true);
        scheduler.scheduleJob(buyInTriggerAMStop);
        scheduler.scheduleJob(buyInTriggerPMStop);

        scheduler.addJob(jobSellOut, true);
        scheduler.scheduleJob(sellOutTriggerBeginAM);
        scheduler.scheduleJob(sellOutTriggerBeginPM);
        scheduler.addJob(jobSellOutStop, true);
        scheduler.scheduleJob(sellOutTriggerAMStop);
        scheduler.scheduleJob(sellOutTriggerPMStop);

        scheduler.start();
    }
}
