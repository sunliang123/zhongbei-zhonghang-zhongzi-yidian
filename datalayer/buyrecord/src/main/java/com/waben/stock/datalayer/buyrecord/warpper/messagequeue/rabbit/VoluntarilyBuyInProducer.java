package com.waben.stock.datalayer.buyrecord.warpper.messagequeue.rabbit;

import com.waben.stock.datalayer.buyrecord.warpper.messagequeue.RabbitMQProducer;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Created by yuyidi on 2017/12/3.
 * @desc
 */
@Component
public class VoluntarilyBuyInProducer extends RabbitMQProducer<SecuritiesStockEntrust> {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自动申请买入
     * @param securitiesStockEntrust
     */
    public void voluntarilyEntrustApplyBuyIn(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("开始发送自动买入订单数据:{}",securitiesStockEntrust.getTradeNo());
        super.topic("buyRecord", "voluntarilyBuyIn", securitiesStockEntrust);
    }

    /**
     * 自动申请买入
     * @param securitiesStockEntrust
     */
    public void  voluntarilyEntrustApplySellOut(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("开始发送自动卖出订单数据:{}",securitiesStockEntrust.getTradeNo());
        super.topic("buyRecord", "voluntarilySellOut", securitiesStockEntrust);
    }
}
