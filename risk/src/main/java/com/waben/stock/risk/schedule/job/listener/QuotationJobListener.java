package com.waben.stock.risk.schedule.job.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Created by yuyidi on 2017/12/17.
 * @desc
 */
public class QuotationJobListener implements JobListener {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getName() {
        return "quotationListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        logger.info("任务被执行");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

    }
}
