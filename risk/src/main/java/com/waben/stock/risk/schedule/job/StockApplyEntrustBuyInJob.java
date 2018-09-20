package com.waben.stock.risk.schedule.job;

import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.enums.EntrustState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.pojo.stock.stockjy.data.StockEntrustQueryResult;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.risk.business.BuyRecordBusiness;
import com.waben.stock.risk.container.StockApplyEntrustBuyInContainer;
import com.waben.stock.risk.warpper.ApplicationContextBeanFactory;
import com.waben.stock.risk.warpper.messagequeue.rabbitmq.EntrustProducer;
import com.waben.stock.risk.warpper.messagequeue.rabbitmq.VoluntarilyApplyEntrustProducer;
import com.waben.stock.risk.web.SecuritiesEntrustHttp;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Created by yuyidi on 2017/12/15.
 * @desc
 */
//@Component
public class StockApplyEntrustBuyInJob implements InterruptableJob {

    Logger logger = LoggerFactory.getLogger(getClass());

    private StockApplyEntrustBuyInContainer securitiesStockEntrustContainer = ApplicationContextBeanFactory.getBean
            (StockApplyEntrustBuyInContainer.class);
    private SecuritiesEntrustHttp securitiesEntrust = ApplicationContextBeanFactory.getBean(SecuritiesEntrustHttp
            .class);
    private EntrustProducer entrustProducer = ApplicationContextBeanFactory.getBean(EntrustProducer.class);

//    private BuyRecordBusiness buyRecordBusiness = ApplicationContextBeanFactory.getBean(BuyRecordBusiness.class);
//
//    private VoluntarilyApplyEntrustProducer producer = ApplicationContextBeanFactory.getBean
//            (VoluntarilyApplyEntrustProducer
//            .class);

    private Boolean interrupted = false;
    private long millisOfDay = 24 * 60 * 60 * 1000;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //执行任务前将容器中的数据清空
//        securitiesStockEntrustContainer.getBuyInContainer().clear();
        logger.info("券商股票委托容器对象:{},当前对象{}", securitiesStockEntrustContainer, this);
        String tradeSession = "880003450508";
        while (!interrupted) {
            try {
                logger.info("3秒后开始轮询");
                Thread.sleep(3 * 1000);
                // 容器中委托数据可能包含来自数据库或者消息队列
                Map<String, SecuritiesStockEntrust> stockEntrusts = securitiesStockEntrustContainer
                        .getBuyInContainer();
                logger.info("券商委托买入股票容器内剩余:{}个委托订单", stockEntrusts.size());
                for (Map.Entry<String, SecuritiesStockEntrust> entry : stockEntrusts.entrySet()) {
                    logger.info("此处执行HTTP，当前委托订单为：{}", entry.getKey());
                    try {
                        SecuritiesStockEntrust securitiesStockEntrust = entry.getValue();
                        String currTradeSession = securitiesStockEntrust.getTradeSession();
                        if (currTradeSession == null) {
                            logger.info("数据库中加载的委托买入点买交易记录");
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
                        logger.info("委托结果：{}", JacksonUtil.encode(stockEntrustQueryResult));
                        if (stockEntrustQueryResult == null || stockEntrustQueryResult.getEntrustStatus().equals
                                (EntrustState.WASTEORDER.getIndex
                                ())) {
                            if (stockEntrustQueryResult == null) {
                                logger.info("委托买入轮询点买记录不存在，删除容器中该交易记录,进行废单操作:{}", securitiesStockEntrust.getTradeNo());
                            } else {
                                logger.info("委托买入轮询点买记录买入废单:{}", entry.getKey());
                            }
                            // 将点买废单放入废单处理队列中
                            entrustProducer.entrustWaste(securitiesStockEntrust);
                            stockEntrusts.remove(entry.getKey());
                        } else if (stockEntrustQueryResult.getEntrustStatus().equals(EntrustState.HASBEENREPORTED
                                .getIndex())) {
                            logger.info("委托买入轮询点买记录买入已报:{}", entry.getKey());
                            // 若当前时间大于委托买入时间1天。将点买废单放入废单处理队列中
                            //当前时间
                            long currentDay = new Date().getTime() / millisOfDay;
                            //委托买入时间
                            long entrustDay = securitiesStockEntrust.getEntrustTime().getTime() / millisOfDay;
                            logger.info("委托时间:{},当前时间:{},相差天数:{}", entrustDay, currentDay, currentDay - entrustDay);
                            if ((currentDay - entrustDay) >= 1) {
                                logger.info("委托买入轮询点买记录买入超时:{}", entry.getKey());
                                entrustProducer.entrustWithdraw(securitiesStockEntrust);
                                stockEntrusts.remove(entry.getKey());
                            }
                        } else if (stockEntrustQueryResult.getEntrustStatus().equals(EntrustState.HASBEENSUCCESS
                                .getIndex())) {
                            logger.info("委托买入轮询点买记录买入成功，删除容器中交易单号为:{},委托数量为:{},委托价格:{}",
                                    securitiesStockEntrust.getTradeNo(),
                                    securitiesStockEntrust.getEntrustNumber(),
                                    securitiesStockEntrust.getEntrustPrice());
                            //交易委托单委托成功之后，委托价格变成成交价格，委托数量变成成交数量
                            Float amount = Float.valueOf(stockEntrustQueryResult.getEntrustAmount());
                            securitiesStockEntrust.setEntrustNumber(amount.intValue());
                            securitiesStockEntrust.setEntrustPrice(new BigDecimal(stockEntrustQueryResult
                                    .getBusinessPrice()));
                            entrustProducer.entrustBuyIn(securitiesStockEntrust);
                            stockEntrusts.remove(entry.getKey());
                        }
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
        logger.info("股票申请委托买入任务被中断");
        interrupted = true;
    }
}
