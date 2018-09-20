package com.waben.stock.datalayer.buyrecord.warpper.messagequeue.rabbit;

import com.waben.stock.datalayer.buyrecord.warpper.messagequeue.RabbitMQProducer;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.pojo.stock.quotation.PositionStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Created by yuyidi on 2017/12/3.
 * @desc
 */
@Component
public class RiskProducer extends RabbitMQProducer<PositionStock> {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 风控持仓中订单
     */
    public void risk(PositionStock positionStock) {
        logger.info("开始发送持仓订单数据:{}", positionStock.toString());
        super.topic("buyRecordRisk", "stock", positionStock);
    }

}
