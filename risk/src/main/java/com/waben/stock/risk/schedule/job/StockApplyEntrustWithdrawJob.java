package com.waben.stock.risk.schedule.job;

import com.waben.stock.interfaces.enums.EntrustState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.pojo.stock.stockjy.data.StockEntrustQueryResult;
import com.waben.stock.risk.container.StockApplyEntrustBuyInContainer;
import com.waben.stock.risk.container.StockApplyEntrustWithdrawContainer;
import com.waben.stock.risk.warpper.ApplicationContextBeanFactory;
import com.waben.stock.risk.warpper.messagequeue.rabbitmq.EntrustProducer;
import com.waben.stock.risk.web.SecuritiesEntrustHttp;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author Created by yuyidi on 2017/12/15.
 * @desc
 */
//@Component
public class StockApplyEntrustWithdrawJob implements InterruptableJob {

    Logger logger = LoggerFactory.getLogger(getClass());

    private StockApplyEntrustWithdrawContainer securitiesStockEntrustContainer = ApplicationContextBeanFactory.getBean
            (StockApplyEntrustWithdrawContainer.class);
    private SecuritiesEntrustHttp securitiesEntrust = ApplicationContextBeanFactory.getBean(SecuritiesEntrustHttp
            .class);
    private EntrustProducer entrustProducer = ApplicationContextBeanFactory.getBean(EntrustProducer.class);

    private Boolean interrupted = false;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("券商股票委托撤单容器对象:{},当前对象{}", securitiesStockEntrustContainer, this);
        String tradeSession = "880003450508";
        while (!interrupted) {
            try {
                logger.info("3秒后开始轮询");
                Thread.sleep(3 * 1000);
                // 容器中委托数据可能包含来自数据库或者消息队列
                Map<String, SecuritiesStockEntrust> stockEntrusts = securitiesStockEntrustContainer
                        .getWithdrawContainer();
                logger.info("券商委托撤单股票容器内剩余:{}个委托订单", stockEntrusts.size());
                for (Map.Entry<String, SecuritiesStockEntrust> entry : stockEntrusts.entrySet()) {
                    logger.info("此处执行HTTP，当前委托订单为：{}", entry.getKey());
                    try {
                        SecuritiesStockEntrust securitiesStockEntrust = entry.getValue();
                        String currTradeSession = securitiesStockEntrust.getTradeSession();
                        if (currTradeSession == null) {
                            logger.info("数据库中加载的委托撤单交易记录");
                            if (tradeSession == null) {
                                continue;
                            }
                            securitiesStockEntrust.setTradeSession(tradeSession);
                        } else {
                            logger.info("最新点买交易记录session:{}", currTradeSession);
                            tradeSession = currTradeSession;
                        }
                        logger.info("当前券商session:{}", tradeSession);
                        StockEntrustQueryResult stockEntrustQueryResult = securitiesEntrust.queryEntrust
                                (securitiesStockEntrust.getTradeSession(), securitiesStockEntrust
                                        .getEntrustNo(), securitiesStockEntrust.getStockCode());
                        if (stockEntrustQueryResult.getEntrustStatus().equals
                                (EntrustState.WASTEORDER.getIndex())) {
                            //废单
                            logger.info("废单:{}", entry.getKey());
                            //TODO 将点买废单放入废单处理队列中
                            entrustProducer.entrustWaste(securitiesStockEntrust);
                            stockEntrusts.remove(entry.getKey());
                            continue;
                        }
                        logger.info("委托结果：{}", EntrustState.getByIndex(stockEntrustQueryResult.getEntrustStatus())
                                .getState());

                    } catch (ServiceException ex) {
                        logger.error("券商委托单查询异常:{}", ex.getMessage());
                    }
                }
            } catch (InterruptedException e) {
                logger.info("中断异常:{}", e.getMessage());
            } catch (Exception e) {
                logger.info("轮询异常：{}", e.getMessage());
            }
        }
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        logger.info("股票申请委托查询撤单任务被中断");
        interrupted = true;
    }
}
