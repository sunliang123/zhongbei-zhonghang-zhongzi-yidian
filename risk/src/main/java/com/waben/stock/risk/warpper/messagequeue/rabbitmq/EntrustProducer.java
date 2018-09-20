package com.waben.stock.risk.warpper.messagequeue.rabbitmq;

import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.pojo.stock.quotation.PositionStock;
import com.waben.stock.risk.warpper.messagequeue.RabbitMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Created by yuyidi on 2017/12/3.
 * @desc
 */
@Component
public class EntrustProducer extends RabbitMQProducer<SecuritiesStockEntrust> {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 委托买入成功
     * @param securitiesStockEntrust
     */
    public void entrustBuyIn(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("开始发送委托买入成功订单数据:{}",securitiesStockEntrust.getTradeNo());
        super.topic("buyRecord", "buyIn", securitiesStockEntrust);
    }

    /**
     * 委托卖出成功
     * @param securitiesStockEntrust
     */
    public void entrustSellOut(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("开始发送委托卖出订单数据:{}",securitiesStockEntrust.getTradeNo());
        super.topic("buyRecord", "sellOut", securitiesStockEntrust);
    }
    /**
     * 委托申请撤单
     * @param securitiesStockEntrust
     */
    public void entrustWithdraw(SecuritiesStockEntrust securitiesStockEntrust) {
        //TODO 调用上游撤单接口
        logger.info("开始发送委托申请撤单订单数据:{}",securitiesStockEntrust.getTradeNo());
        super.topic("buyRecord", "withdraw", securitiesStockEntrust);
    }

    /**
     * 废单
     * @param securitiesStockEntrust
     */
    public void entrustWaste(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("开始发送废单数据:{}",securitiesStockEntrust.getTradeNo());
        super.topic("buyRecord", "waste", securitiesStockEntrust);
    }

    /**
     * 重新委托卖出
     * @param securitiesStockEntrust
     */
    public void againEntrust(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("开始发送重新委托订单数据:{}",securitiesStockEntrust.getTradeNo());
        super.topic("buyRecord", "again", securitiesStockEntrust);
    }
}
