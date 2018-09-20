package com.waben.stock.risk.warpper.init;

import com.waben.stock.risk.container.StockApplyEntrustBuyInContainer;
import com.waben.stock.risk.warpper.messagequeue.rabbitmq.EntrustProducer;
import com.waben.stock.risk.web.SecuritiesEntrustHttp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * @author Created by yuyidi on 2017/12/4.
 * @desc
 */
//@Component
public class StockApplyEntrustBuyInLoopProcessor implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(getClass());
    //    Executor executor = Executors.newFixedThreadPool(2);
    @Autowired
    private StockApplyEntrustBuyInContainer securitiesStockEntrustContainer;
    @Autowired
    private SecuritiesEntrustHttp securitiesEntrust;
    @Autowired
    private EntrustProducer entrustProducer;

    @Override
    public void run(String... args) throws Exception {

        Thread task = new Thread(new Runnable() {
            @Override
            public void run() {
//                String tradeSession = null;
//                while (true) {
//                    //容器中委托数据可能包含来自数据库或者消息队列
//                    Map<String, SecuritiesStockEntrust> stockEntrusts = securitiesStockEntrustContainer
//                            .getBuyInContainer();
//                    logger.info("券商委托股票容器内剩余:{}个委托订单", stockEntrusts.size());
//                    for (Map.Entry<String, SecuritiesStockEntrust> entry : stockEntrusts.entrySet()) {
//                        logger.info("此处执行HTTP，当前委托订单为：{}", entry.getKey());
//                        try {
//                            SecuritiesStockEntrust securitiesStockEntrust = entry.getValue();
//                            String currTradeSession = securitiesStockEntrust.getTradeSession();
//                            if (currTradeSession == null) {
//                                securitiesStockEntrust.setTradeSession(tradeSession);
//                                continue;
//                            } else {
//                                tradeSession = currTradeSession;
//                            }
//                            logger.info("开始执行委托查询,委托编号:{}", securitiesStockEntrust.getEntrustNo());
//                            StockEntrustQueryResult stockEntrustQueryResult = securitiesEntrust.queryEntrust
//                                    (securitiesStockEntrust.getTradeSession(), securitiesStockEntrust
//                                            .getEntrustNo());
//                            if (stockEntrustQueryResult == null) {
//                                continue;
//                            }
//                            if (stockEntrustQueryResult.getEntrustStatus().equals(EntrustState.HASBEENSUCCESS
//                                    .getIndex())) {
//                                logger.info("交易委托单已交易成功，删除容器中交易单号为:{},委托单号为:{}", securitiesStockEntrust.getTradeNo(),
//                                        securitiesStockEntrust.getEntrustNumber());
//                                // 若执行结果为true 代表订单状态已成功，则  删除集合中的数据
//                                //发送给队列处理，提高委托单轮询处理速度
//                                logger.info("委托订单已完成:{}", entry.getKey());
//                                entrustProducer.entrustBuyIn(entry.getValue());
//                                stockEntrusts.remove(entry.getKey());
//                            }
//                        } catch (ServiceException ex) {
//                            logger.error("券商委托单查询异常:{}", ex.getMessage());
//                        }
//                    }
//                }
            }
        });
        task.start();
    }
}
