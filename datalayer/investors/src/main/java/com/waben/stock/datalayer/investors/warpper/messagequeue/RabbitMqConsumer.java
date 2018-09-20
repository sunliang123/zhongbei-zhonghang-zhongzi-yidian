package com.waben.stock.datalayer.investors.warpper.messagequeue;

import com.waben.stock.datalayer.investors.business.BuyRecordBusiness;
import com.waben.stock.datalayer.investors.business.StockBusiness;
import com.waben.stock.datalayer.investors.container.VoluntarilyApplyEntrustBuyInContainer;
import com.waben.stock.datalayer.investors.container.VoluntarilyApplyEntrustSellOutContainer;
import com.waben.stock.datalayer.investors.service.InvestorService;
import com.waben.stock.datalayer.investors.warpper.messagequeue.rabbitmq.EntrustApplyProducer;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.enums.EntrustState;
import com.waben.stock.interfaces.enums.WindControlType;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.pojo.stock.quotation.PositionStock;
import com.waben.stock.interfaces.util.JacksonUtil;
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
	private InvestorService investorService;
	@Autowired
	private StockBusiness stockBusiness;
	@Autowired
	private BuyRecordBusiness buyRecordBusiness;
	@Autowired
	private EntrustApplyProducer entrustProducer;
	@Autowired
	private VoluntarilyApplyEntrustBuyInContainer voluntarilyApplyEntrustBuyInContainer;
	@Autowired
	private VoluntarilyApplyEntrustSellOutContainer voluntarilyApplyEntrustSellOutContainer;
	@RabbitListener(queues = {"riskPositionSellOut"})
	public void buyInSuccessRisk(PositionStock positionStock) throws InterruptedException {
		logger.info("强制卖出持仓订单数据:{}", JacksonUtil.encode(positionStock));
		BuyRecordDto buyRecordDto = buyRecordBusiness.findById(positionStock.getBuyRecordId());
		logger.info("风控卖出队列消费点买记录状态：{}",buyRecordDto.getState());
		if(BuyRecordState.HOLDPOSITION.equals(buyRecordDto.getState())) {
			//放入卖出容器
			SecuritiesStockEntrust securitiesStockEntrust = new SecuritiesStockEntrust();
			StockDto stockDto = stockBusiness.fetchWithExponentByCode(positionStock.getStockCode());
			securitiesStockEntrust.setExponent(stockDto.getExponent().getExponentCode());
			securitiesStockEntrust.setStockCode(positionStock.getStockCode());
			securitiesStockEntrust.setEntrustNumber(positionStock.getEntrustNumber());
			securitiesStockEntrust.setEntrustPrice(positionStock.getEntrustPrice());
			securitiesStockEntrust.setBuyRecordId(positionStock.getBuyRecordId());
			securitiesStockEntrust.setWindControlType(positionStock.getWindControlType());
			securitiesStockEntrust.setTradeNo(positionStock.getTradeNo());
			voluntarilyApplyEntrustSellOutContainer.add(securitiesStockEntrust);
		}
	}

	@RabbitListener(queues = {"entrustApplyWithdraw"})
	public void entrustApplyWithdraw(SecuritiesStockEntrust securitiesStockEntrust) throws InterruptedException {
		logger.info("委托撤单订单数据:{}", JacksonUtil.encode(securitiesStockEntrust));
		try{
			String entrustNo = investorService.buyRecordApplyWithdraw(securitiesStockEntrust);
			BuyRecordDto buyRecordDto = buyRecordBusiness.entrustApplyWithdraw(entrustNo, securitiesStockEntrust.getBuyRecordId());
			logger.info("修改订单撤单锁定状态成功:{}",buyRecordDto.getTradeNo());
			securitiesStockEntrust.setTradeNo(buyRecordDto.getTradeNo());
			securitiesStockEntrust.setEntrustNo(buyRecordDto.getDelegateNumber());
			securitiesStockEntrust.setEntrustState(EntrustState.REPORTEDTOWITHDRAW);
			entrustProducer.entrustQueryWithdraw(securitiesStockEntrust);
			logger.info("撤单成功：{}",JacksonUtil.encode(buyRecordDto));
		}catch (Exception ex) {
			logger.error("撤单失败：{}",ex.getMessage());
		}
	}

	@RabbitListener(queues = {"voluntarilyApplyBuyIn"})
	public void voluntarilyEntrustApplyBuyIn(SecuritiesStockEntrust securitiesStockEntrust) throws InterruptedException {
		logger.info("自动买入订单数据:{}", JacksonUtil.encode(securitiesStockEntrust));
		voluntarilyApplyEntrustBuyInContainer.add(securitiesStockEntrust);
	}

	@RabbitListener(queues = {"voluntarilyApplySellOut"})
	public void voluntarilyEntrustApplySellOut(SecuritiesStockEntrust securitiesStockEntrust) throws InterruptedException {
		logger.info("自动卖出订单数据:{}", JacksonUtil.encode(securitiesStockEntrust));
		voluntarilyApplyEntrustSellOutContainer.add(securitiesStockEntrust);
	}

	@RabbitListener(queues = {"againEntrust"})
	public void againEntrustApplySellOut(SecuritiesStockEntrust securitiesStockEntrust) throws InterruptedException {
		logger.info("重新委托卖出订单数据:{}", JacksonUtil.encode(securitiesStockEntrust));
		investorService.againEntrustApplySellOut(securitiesStockEntrust);
		logger.info("重新委托成功：{}",JacksonUtil.encode(securitiesStockEntrust));
	}
}