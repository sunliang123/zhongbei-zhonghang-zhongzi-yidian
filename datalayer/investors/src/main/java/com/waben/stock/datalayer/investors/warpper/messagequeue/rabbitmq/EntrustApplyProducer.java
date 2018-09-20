package com.waben.stock.datalayer.investors.warpper.messagequeue.rabbitmq;

import com.waben.stock.datalayer.investors.warpper.messagequeue.RabbitMQProducer;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Created by yuyidi on 2017/12/3.
 * @desc
 */
@Component
public class EntrustApplyProducer extends RabbitMQProducer<SecuritiesStockEntrust> {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 委托申请买入
     * @param securitiesStockEntrust
     */
    public void entrustApplyBuyIn(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("开始发送委托买入订单数据:{}",securitiesStockEntrust.getTradeNo());
        super.topic("buyRecord", "applyBuyIn", securitiesStockEntrust);
    }

    /**
     * 委托申请卖出
     * @param securitiesStockEntrust
     */
    public void entrustApplySellOut(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("开始发送委托卖出订单数据:{}",securitiesStockEntrust.getTradeNo());
        super.topic("buyRecord", "applySellOut", securitiesStockEntrust);
    }

    /**
     * 委托查询撤单
     * @param securitiesStockEntrust
     */
    public void entrustQueryWithdraw(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("开始发送委托查询撤单订单数据:{}",securitiesStockEntrust.getTradeNo());
        super.topic("buyRecord", "queryWithdraw", securitiesStockEntrust);
    }
}
