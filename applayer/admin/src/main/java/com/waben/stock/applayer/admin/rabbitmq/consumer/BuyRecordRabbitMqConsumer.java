package com.waben.stock.applayer.admin.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.waben.stock.datalayer.buyrecord.entity.BuyRecord;
import com.waben.stock.datalayer.buyrecord.service.BuyRecordService;
import com.waben.stock.datalayer.buyrecord.warpper.messagequeue.rabbit.RiskProducer;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.pojo.stock.quotation.PositionStock;

/**
 * @author Created by yuyidi on 2017/11/27.
 * @desc
 */
@Component
public class BuyRecordRabbitMqConsumer {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BuyRecordService buyRecordService;

	@Autowired
	private RiskProducer riskProducer;

	@RabbitListener(queues = { "entrustBuyIn" })
	public void entrustBuyIn(SecuritiesStockEntrust securitiesStockEntrust) {
		logger.info("券商股票委托买入成功:{},{}", securitiesStockEntrust.getTradeNo(),securitiesStockEntrust.getBuyRecordId());
		BuyRecord buyRecord = buyRecordService.buyInto(securitiesStockEntrust.getInvestor(), securitiesStockEntrust.getBuyRecordId(),
			  securitiesStockEntrust.getEntrustPrice());
		// 点买记录委托成功  点买记录状态为持仓中，则将当前订单记录放入风控消息队列
		//风控传输对象
		PositionStock positionStock = new PositionStock();
		positionStock.setBuyRecordId(buyRecord.getId());
		positionStock.setStockCode(buyRecord.getStockCode());
		positionStock.setStockName(buyRecord.getStockName());
		positionStock.setLossPosition(buyRecord.getLossPosition());
		positionStock.setProfitPosition(buyRecord.getProfitPosition());
		positionStock.setInvestorId(buyRecord.getInvestorId());
		positionStock.setBuyingTime(buyRecord.getBuyingTime());
		positionStock.setDeferred(buyRecord.getDeferred());
		positionStock.setTradeSession(securitiesStockEntrust.getTradeSession());
		positionStock.setExpireTime(buyRecord.getExpireTime());
		positionStock.setTradeNo(buyRecord.getTradeNo());
		positionStock.setEntrustNumber(buyRecord.getNumberOfStrand());
		riskProducer.risk(positionStock);
	}

	@RabbitListener(queues = { "entrustSellOut" })
	public void entrustSellOut(SecuritiesStockEntrust securitiesStockEntrust) {
		logger.info("券商股票委托卖出成功:{}", securitiesStockEntrust.getTradeNo());
		buyRecordService.sellOut(securitiesStockEntrust.getInvestor(), securitiesStockEntrust.getBuyRecordId(),
				securitiesStockEntrust.getEntrustPrice());
		// 发送短信通知用户
	}

	@RabbitListener(queues = { "entrustWaste" })
	public void entrustWaste(SecuritiesStockEntrust securitiesStockEntrust) {
		logger.info("处理废单:{},订单ID:{}", securitiesStockEntrust.getTradeNo(),securitiesStockEntrust.getBuyRecordId());
		buyRecordService.revoke(securitiesStockEntrust.getBuyRecordId());
	}

}