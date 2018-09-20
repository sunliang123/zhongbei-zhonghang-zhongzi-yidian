package com.waben.stock.risk.schedule.job;

import com.waben.stock.interfaces.pojo.stock.quotation.PositionStock;
import com.waben.stock.interfaces.pojo.stock.quotation.StockMarket;
import com.waben.stock.risk.container.PositionStockContainer;
import com.waben.stock.risk.schedule.thread.RiskProcess;
import com.waben.stock.risk.warpper.ApplicationContextBeanFactory;
import com.waben.stock.risk.warpper.messagequeue.rabbitmq.PositionSellOutProducer;
import com.waben.stock.risk.web.StockQuotationHttp;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Created by yuyidi on 2017/11/28.
 * @desc 持仓中点买记录风控队列
 */
//@Component
public class StockQuotationJob implements InterruptableJob {

    Logger logger = LoggerFactory.getLogger(getClass());

    private PositionStockContainer positionStockContainer = ApplicationContextBeanFactory.getBean
            (PositionStockContainer.class);

    private StockQuotationHttp stockQuotationHttp = ApplicationContextBeanFactory.getBean(StockQuotationHttp.class);
    private PositionSellOutProducer positionProducer = ApplicationContextBeanFactory.getBean(PositionSellOutProducer
            .class);

    private ExecutorService executors = Executors.newFixedThreadPool(4);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("开始执行行情数据拉取,股票数量:{}", positionStockContainer.getRiskStockContainer().size());

        Map<String, List<PositionStock>> riskStockContainer = positionStockContainer.getRiskStockContainer();
        Map<String, PositionStock> entrustSellOutContainer = positionStockContainer.getEntrustSellOutContainer();
        Set<String> codes = riskStockContainer.keySet();
        List<String> codePrams = new ArrayList();
        codePrams.addAll(codes);
        //拉取股票行情数据
        if (codePrams.isEmpty()) {
            logger.info("没有股票！");
            return;
        }
        List<StockMarket> quotations = stockQuotationHttp.fetQuotationByCode(codePrams);
        //线程处理
        for (StockMarket stockMarket : quotations) {
            List<PositionStock> stocks = riskStockContainer.get(stockMarket.getInstrumentId());
            Future<List<PositionStock>> future = executors.submit(new RiskProcess(stockMarket, stocks,
                    entrustSellOutContainer));
            logger.info("线程执行中,当前行情：{},股票名称:{}", stockMarket.getInstrumentId(), stockMarket.getName());
            try {
                //接收被风控的持仓点买订单
                List<PositionStock> result = future.get();
                for (PositionStock stock : result) {
                    logger.info("风控点买交易记录:{},风控类型:{}", stock.getTradeNo(), stock.getWindControlType());
                    if(stock.getWindControlType()!=null) {
                        positionProducer.riskPositionSellOut(stock);
                        entrustSellOutContainer.remove(stock.getTradeNo());
                    }
                    stocks.remove(stock);
                }
            } catch (InterruptedException e) {
                logger.error("中断异常:{}", e.getMessage());
            } catch (ExecutionException e) {
                e.printStackTrace();
                logger.error("线程执行异常:{}", e.getMessage());
            }
        }
    }


    @Override
    public void interrupt() throws UnableToInterruptJobException {
        logger.info("行情数据拉取中断");
    }

}
