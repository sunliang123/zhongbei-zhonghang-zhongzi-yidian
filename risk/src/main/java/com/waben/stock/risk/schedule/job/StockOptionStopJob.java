package com.waben.stock.risk.schedule.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockOptionStopJob implements Job {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            logger.info("中断期权到期处理任务{}",context.getScheduler());
            context.getScheduler().interrupt(JobKey.jobKey("jobStockOption","groupStockOption"));
        } catch (UnableToInterruptJobException e) {
            e.printStackTrace();
        }
    }
}
