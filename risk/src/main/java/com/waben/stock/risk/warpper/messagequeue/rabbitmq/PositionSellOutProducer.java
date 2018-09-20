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
public class PositionSellOutProducer extends RabbitMQProducer<PositionStock> {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 风控持仓卖出
     * @param positionStock
     */
    public void riskPositionSellOut(PositionStock positionStock) {
        logger.info("开始发送风控持仓订单数据:{}",positionStock.toString());
        super.topic("buyRecordRisk", "position", positionStock);
    }
    
}
