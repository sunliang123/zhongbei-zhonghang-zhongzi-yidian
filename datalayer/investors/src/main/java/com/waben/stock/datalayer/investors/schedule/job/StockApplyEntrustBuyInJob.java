package com.waben.stock.datalayer.investors.schedule.job;

import java.util.Date;
import java.util.Map;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.waben.stock.datalayer.investors.container.VoluntarilyApplyEntrustBuyInContainer;
import com.waben.stock.datalayer.investors.service.InvestorService;
import com.waben.stock.datalayer.investors.warpper.ApplicationContextBeanFactory;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.service.buyrecord.BuyRecordInterface;
import com.waben.stock.interfaces.util.JacksonUtil;

/**
 * @author Created by yuyidi on 2017/12/15.
 * @desc
 */
//@Component
public class StockApplyEntrustBuyInJob implements InterruptableJob {

    Logger logger = LoggerFactory.getLogger(getClass());

    private VoluntarilyApplyEntrustBuyInContainer securitiesStockEntrustContainer = ApplicationContextBeanFactory.getBean
            (VoluntarilyApplyEntrustBuyInContainer.class);
    private InvestorService investorService = ApplicationContextBeanFactory.getBean(InvestorService.class);
    private BuyRecordInterface buyRecordReference = ApplicationContextBeanFactory.getBean(BuyRecordInterface.class);
    private Boolean interrupted = false;
    private long millisOfDay = 24 * 60 * 60 * 1000;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("自动委托买入任务开始");
        while (!interrupted) {
            try {
                Map<String, SecuritiesStockEntrust> buyInContainer = securitiesStockEntrustContainer.getBuyInContainer();
                Thread.sleep(500);
                for (Map.Entry<String, SecuritiesStockEntrust> entry : buyInContainer.entrySet()) {
                    logger.info("委托买入容器对象:{}", buyInContainer.size());
                    SecuritiesStockEntrust securitiesStockEntrust = entry.getValue();
                    logger.info("自动买入订单数据:{}", JacksonUtil.encode(securitiesStockEntrust));
                    Date entrustTime = securitiesStockEntrust.getEntrustTime();//点买记录创建时间
                    Date currentTime = new Date();
                    long currentDay = currentTime.getTime()/millisOfDay;
                    long entrustDay = entrustTime.getTime()/millisOfDay;
                    logger.info("当前时间：{},点买创建时间：{}",currentDay,entrustDay);
                    if(currentDay-entrustDay>=1) {
                        logger.info("当前时间大于委托买入时间执行废单:{}，点买记录:{}", currentDay-entrustDay,securitiesStockEntrust.getTradeNo());
                        buyRecordReference.revoke(securitiesStockEntrust.getBuyRecordId());
                        buyInContainer.remove(securitiesStockEntrust.getTradeNo());
                        continue;
                    }
                    BuyRecordDto buyRecordDto = investorService.voluntarilyApplyBuyIn(securitiesStockEntrust);
                    logger.info("委托买入结果：{}",JacksonUtil.encode(buyRecordDto));
                    if (buyRecordDto != null) {
                        if(BuyRecordState.BUYLOCK.equals(buyRecordDto.getState())) {
                            logger.info("委托买入成功：{}",JacksonUtil.encode(buyRecordDto));
                            buyInContainer.remove(securitiesStockEntrust.getTradeNo());
                        }
                    }
                }
            }catch (Exception ex) {
            	ex.printStackTrace();
                logger.error("自动买入异常：{}", ex.getMessage());
            }
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        logger.info("股票申请委托买入任务被中断");
        interrupted = true;
    }
}
