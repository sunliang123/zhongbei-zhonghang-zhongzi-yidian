package com.waben.stock.risk.warpper.messagequeue;

import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.pojo.stock.quotation.PositionStock;
import com.waben.stock.risk.container.PositionStockContainer;
import com.waben.stock.risk.container.StockApplyEntrustBuyInContainer;
import com.waben.stock.risk.container.StockApplyEntrustSellOutContainer;
import com.waben.stock.risk.container.StockApplyEntrustWithdrawContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Created by yuyidi on 2017/11/27.
 * @desc
 */
@Component
public class RabbitMqConsumer {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StockApplyEntrustBuyInContainer stockApplyEntrustBuyInContainer;

    @Autowired
    private StockApplyEntrustSellOutContainer stockApplyEntrustSellOutContainer;

    @Autowired
    private StockApplyEntrustWithdrawContainer stockApplyEntrustWithdrawContainer;

    @Autowired
    private PositionStockContainer positionStockContainer;

//    @RabbitListener(queues = {"shangSecurity"})
//    public void shangSecurity(String message) {
//        logger.info("上海证券点买交易记录接收到消息:{}", message);
//    }
//
//    @RabbitListener(queues = {"shenSecurity"})
//    public void shenSecurity(String message) {
//        logger.info("深证证券点买交易记录接收到消息:{}", message);
//    }
//
//    @RabbitListener(queues = {"developSecurity"})
//    public void developSecurity(String message) {
//        logger.info("创业板点买交易记录接收到消息:{}", message);
//    }

    @RabbitListener(queues = {"entrustApplyBuyIn"})
    public void entrustApplyBuyIn(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("消费券商股票申请委托买入消息:{}",securitiesStockEntrust.getTradeNo());
        stockApplyEntrustBuyInContainer.add(securitiesStockEntrust);
    }

    @RabbitListener(queues = {"entrustApplySellOut"})
    public void entrustApplySellOut(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("消费券商股票申请委托卖出消息:{}",securitiesStockEntrust.getTradeNo());
        stockApplyEntrustSellOutContainer.add(securitiesStockEntrust);
    }

    @RabbitListener(queues = {"entrustQueryWithdraw"})
    public void entrustQueryWithdraw(SecuritiesStockEntrust securitiesStockEntrust) {
        logger.info("消费券商股票申请委托撤单消息:{}",securitiesStockEntrust.getTradeNo());
        stockApplyEntrustWithdrawContainer.add(securitiesStockEntrust);
    }

    @RabbitListener(queues = {"risk"})
    public void buyInSuccessRisk(PositionStock positionStock) {
        logger.info("委托买入成功，加入风控:{}", positionStock.toString());
        positionStockContainer.add(positionStock);
    }

}
