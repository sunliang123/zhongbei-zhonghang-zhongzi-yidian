package com.waben.stock.risk.schedule.job.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Created by yuyidi on 2017/12/16.
 * @desc
 */
public class BuyInJobListener implements JobListener {

    Logger logger = LoggerFactory.getLogger(getName());
    @Override
    public String getName() {
        return "stockEntrustBuyIn";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        logger.info("开始委托买入轮询,{}",context.getJobDetail().getKey());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
            logger.info("委托买入轮询已经执行");

    }
}
