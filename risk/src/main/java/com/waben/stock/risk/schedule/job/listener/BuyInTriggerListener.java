package com.waben.stock.risk.schedule.job.listener;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Created by yuyidi on 2017/12/17.
 * @desc
 */
public class BuyInTriggerListener implements TriggerListener {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public String getName() {
        return "buyInJobTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        logger.info("trigger 创建完成{}",trigger.getKey());
        try {
            context.getScheduler().interrupt(JobKey.jobKey("job1","group1"));
        } catch (UnableToInterruptJobException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction
            triggerInstructionCode) {
            logger.info("Trigger 执行完成");

    }
}
