package com.waben.stock.risk;

import com.waben.stock.risk.schedule.WorkCalendar;
import com.waben.stock.risk.schedule.job.BuyInStopJob;
import com.waben.stock.risk.schedule.job.StockApplyEntrustBuyInJob;
import com.waben.stock.risk.schedule.job.StockQuotationJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.DailyCalendar;
import org.quartz.impl.calendar.WeeklyCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Created by yuyidi on 2017/11/28.
 * @desc
 */
public class QuartzTest {
    Logger log = LoggerFactory.getLogger(getClass());


    @Test
    public void testSimpleQuartz() throws SchedulerException {
        System.setProperty("org.terracotta.quartz.skipUpdateCheck", "true");
        log.info("------- Initializing ----------------------");
        // 1、工厂模式 构建Scheduler的Factory，其中STD为Quartz默认的Factory，开发者亦可自行实现自己的Factory;Job、Trigger等组件
        SchedulerFactory sf = new StdSchedulerFactory();
        // 2、通过SchedulerFactory获得Scheduler对象
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete -----------");

        // 3、org.quartz.DateBuilder.evenMinuteDate <下一分钟>  -- 通过DateBuilder构建Date
        Date runTime = DateBuilder.evenMinuteDate(new Date());

        log.info("------- Scheduling Job  -------------------");

        // 4、org.quartz.JobBuilder.newJob --通过JobBuilder构建Job
        JobDetail job = JobBuilder.newJob(StockQuotationJob.class).withIdentity("job1", "group1").build();

        // 5、通过TriggerBuilder进行构建
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

        // 6、工厂模式，组装各个组件<JOB，Trigger>
        sched.scheduleJob(job, trigger);

        // [group1.job1] will run at:
        log.info(job.getKey() + " will run at: " + runTime);

        // 7、start
        sched.start();

        log.info("------- Started Scheduler -----------------");

        log.info("------- Waiting 65 seconds... -------------");
        try {
            // wait 65 seconds to show job
            Thread.sleep(65L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        // shut down the scheduler
        log.info("------- Shutting Down ---------------------");
        // 8、通过Scheduler销毁内置的Trigger和Job
        sched.shutdown(true);
    }

    @Test
    public void testQuartz() throws Exception {
        System.setProperty("org.terracotta.quartz.skipUpdateCheck", "true");
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        // 3、org.quartz.DateBuilder.evenMinuteDate <下一分钟>  -- 通过DateBuilder构建Date
        Date runTime = DateBuilder.evenMinuteDate(new Date());
        WeeklyCalendar workDay = new WeeklyCalendar();
        //排除特定的日期
        WorkCalendar workCalendar = new WorkCalendar(workDay, "2018-01-01");
        //排除在外的时间  通过使用invertTimeRange=true  表示倒置
        DailyCalendar am = new DailyCalendar(workCalendar, "09:30", "11:30");
        am.setInvertTimeRange(true);
        DailyCalendar pm = new DailyCalendar(workCalendar, "13:30", "15:50");
        pm.setInvertTimeRange(true);
        scheduler.addCalendar("calendarAM", am, true, true);
        scheduler.addCalendar("calendarPM", pm, false, false);

        JobDetail jobQuotation = JobBuilder.newJob(StockQuotationJob.class).withIdentity("jobQuotation",
                "groupQuotation")
                .storeDurably(true)
                .build();
        SimpleTrigger stockQuotationAM = newTrigger().withIdentity("quotationAMTrigger", "groupQuotation").startAt(runTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .forJob(jobQuotation)
                .modifiedByCalendar("calendarAM")
                .build();
        SimpleTrigger stockQuotationPM = newTrigger().withIdentity("quotationPMTrigger", "groupQuotation").startAt(runTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .forJob(jobQuotation)
                .modifiedByCalendar("calendarPM")
                .build();

        CronScheduleBuilder scheduleBuyInBuilder = CronScheduleBuilder.cronSchedule("0 30 09,13 * * ?");
        CronScheduleBuilder scheduleBuilderAMStop = CronScheduleBuilder.cronSchedule("0 30 11 * * ?");
        CronScheduleBuilder scheduleBuilderPMStop = CronScheduleBuilder.cronSchedule("0 50 15 * * ?");

        JobDetail jobBuyIn = JobBuilder.newJob(StockApplyEntrustBuyInJob.class).withIdentity("jobBuyIn", "groupBuyIn")
                .storeDurably(true)
                .build();
        JobDetail jobBuyInStop = JobBuilder.newJob(BuyInStopJob.class).withIdentity("jobBuyInStop", "groupBuyIn")
                .storeDurably(true)
                .build();
        Trigger buyInTriggerBegin = newTrigger().withIdentity("buyInTriggerBegin", "groupBuyIn").startNow()
                .withSchedule(scheduleBuyInBuilder)
                .modifiedByCalendar("calendarAM")
                .forJob(jobBuyIn)
                .build();
        Trigger buyInTriggerAMStop = newTrigger().withIdentity("buyInTriggerAMStop", "groupBuyIn").startNow()
                .withSchedule(scheduleBuilderAMStop)
                .modifiedByCalendar("calendarPM")
                .forJob(jobBuyInStop)
                .build();
        Trigger buyInTriggerPMStop = newTrigger().withIdentity("buyInTriggerPMStop", "groupBuyIn").startNow()
                .withSchedule(scheduleBuilderPMStop)
                .modifiedByCalendar("calendar")
                .forJob(jobBuyInStop)
                .build();
//        ListenerManager listenerManager = scheduler.getListenerManager();
//        JobListener listener = new BuyInJobListener();
//        Matcher<JobKey> matcher = KeyMatcher.keyEquals(job.getKey());
//        listenerManager.addJobListener(listener, matcher);
//        TriggerListener triggerListener = new BuyInTriggerListener();
//        Matcher<TriggerKey> triggerAMKeyMatcher = KeyMatcher.keyEquals(triggerAMStop.getKey());
//        Matcher<TriggerKey> triggerPMKeyMatcher = KeyMatcher.keyEquals(triggerPMStop.getKey());
//        listenerManager.addTriggerListener(triggerListener,triggerAMKeyMatcher);
//        listenerManager.addTriggerListener(triggerListener,triggerPMKeyMatcher);
//        Set<Trigger> triggers = new HashSet<>();
//        triggers.add(trigger);
//        triggers.add(triggerAMStop);
//        triggers.add(triggerPMStop);
//        sched.scheduleJob(job,triggers,true);
//        sched.scheduleJob(job2, triggers,true);

        scheduler.addJob(jobQuotation, true);
        scheduler.scheduleJob(stockQuotationAM);
        scheduler.scheduleJob(stockQuotationPM);

        scheduler.addJob(jobBuyIn, true);
        scheduler.scheduleJob(buyInTriggerBegin);
        scheduler.addJob(jobBuyInStop,true);
        scheduler.scheduleJob(buyInTriggerAMStop);
        scheduler.scheduleJob(buyInTriggerPMStop);


        scheduler.start();
        log.info("------- Waiting 65 seconds... -------------{}", scheduler);
        try {
            // wait 65 seconds to show job
            Thread.sleep(1000000 * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }
        // 8、通过Scheduler销毁内置的Trigger和Job
        scheduler.shutdown(true);

    }
}
