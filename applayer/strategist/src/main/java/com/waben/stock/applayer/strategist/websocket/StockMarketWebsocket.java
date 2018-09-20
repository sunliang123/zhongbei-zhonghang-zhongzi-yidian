package com.waben.stock.applayer.strategist.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.waben.stock.applayer.strategist.dto.system.StockMarketExponentDto;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockTimeLine;

// @Component
public class StockMarketWebsocket {

	public SimpMessagingTemplate template;

	@Autowired
	public StockMarketWebsocket(SimpMessagingTemplate template) {
		this.template = template;
	}

	/**
	 * 推送股票大盘指数
	 */
	public void sendStockMarketIndex(List<StockMarketExponentDto> stockMarketIndexList) {
		template.convertAndSend(WebsocketDestination.StockMarketIndex.getDestination(), stockMarketIndexList);
	}

	/**
	 * 推送股票分时数据
	 */
	public void sendStockTimeLine(List<StockTimeLine> stockTimeLineList) {
		/*
		template.convertAndSend("/user/" + stockTimeLineList.get(0).getInstrumentID()
				+ WebsocketDestination.StockTimeLine.getDestination(), stockTimeLineList);
		*/
		template.convertAndSendToUser(stockTimeLineList.get(0).getInstrumentID(),
				WebsocketDestination.StockTimeLine.getDestination(), stockTimeLineList);
	}

}
