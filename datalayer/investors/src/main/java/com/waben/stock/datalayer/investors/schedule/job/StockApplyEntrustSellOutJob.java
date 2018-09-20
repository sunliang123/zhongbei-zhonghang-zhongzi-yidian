package com.waben.stock.datalayer.investors.schedule.job;

import com.waben.stock.datalayer.investors.container.VoluntarilyApplyEntrustSellOutContainer;
import com.waben.stock.datalayer.investors.service.InvestorService;
import com.waben.stock.datalayer.investors.warpper.ApplicationContextBeanFactory;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.util.JacksonUtil;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Created by yuyidi on 2017/12/15.
 * @desc
 */
//@Component
public class StockApplyEntrustSellOutJob implements InterruptableJob {

    Logger logger = LoggerFactory.getLogger(getClass());

    private VoluntarilyApplyEntrustSellOutContainer voluntarilyStockEntrustContainer = ApplicationContextBeanFactory
            .getBean
            (VoluntarilyApplyEntrustSellOutContainer.class);
    private InvestorService investorService = ApplicationContextBeanFactory.getBean(InvestorService.class);
    private Boolean interrupted = false;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("委托卖出任务开始");
        while (!interrupted) {
            Map<String, SecuritiesStockEntrust> sellOutContainer = voluntarilyStockEntrustContainer
                    .getSellOutContainer();
            try {
                Thread.sleep(500);
                for (Map.Entry<String, SecuritiesStockEntrust> entry : sellOutContainer.entrySet()) {
                    logger.info("委托卖出容器对象:{}", sellOutContainer.size());
                    SecuritiesStockEntrust securitiesStockEntrust = entry.getValue();
                    logger.info("自动卖出订单数据:{}", JacksonUtil.encode(securitiesStockEntrust));
                    BuyRecordDto buyRecordDto = investorService.voluntarilyApplySellOut(securitiesStockEntrust);
                    logger.info("委托卖出结果：{}",buyRecordDto);
                    if (buyRecordDto != null) {
                        if (BuyRecordState.SELLLOCK.equals(buyRecordDto.getState())) {
                            logger.info("委托卖出成功：{}", JacksonUtil.encode(buyRecordDto));
                            sellOutContainer.remove(securitiesStockEntrust.getTradeNo());
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        logger.info("股票申请委托卖出任务被中断");
        interrupted = true;
    }
}
