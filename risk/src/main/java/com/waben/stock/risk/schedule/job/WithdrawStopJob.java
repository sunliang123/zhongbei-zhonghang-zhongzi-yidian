package com.waben.stock.risk.schedule.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Created by yuyidi on 2017/12/17.
 * @desc
 */
public class WithdrawStopJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            logger.info("中断撤单任务{}",context.getScheduler());
            context.getScheduler().interrupt(JobKey.jobKey("jobWithdraw", "groupWithdraw"));
        } catch (UnableToInterruptJobException e) {
            e.printStackTrace();
        }
    }
}
