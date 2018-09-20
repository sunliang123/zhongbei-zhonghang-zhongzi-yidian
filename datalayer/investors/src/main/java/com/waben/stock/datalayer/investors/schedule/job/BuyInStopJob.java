package com.waben.stock.datalayer.investors.schedule.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Created by yuyidi on 2017/12/17.
 * @desc
 */
public class BuyInStopJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            logger.info("中断买入任务{}",context.getScheduler());
            context.getScheduler().interrupt(JobKey.jobKey("jobBuyIn", "groupBuyIn"));
        } catch (UnableToInterruptJobException e) {
            e.printStackTrace();
        }
    }
}
