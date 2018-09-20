package com.waben.stock.applayer.tactics.schedule;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.waben.stock.applayer.tactics.dto.system.StockMarketExponentDto;
import com.waben.stock.applayer.tactics.service.StockMarketService;
import com.waben.stock.applayer.tactics.websocket.StockMarketWebsocket;

/**
 * 推送股票市场指数作业
 * 
 * @author luomengan
 *
 */
// @Component
public class PushStockMarketIndexSchedule {

	/**
	 * 推送间隔
	 */
	public static final long Push_Interval = 5000;

	@Autowired
	private StockMarketWebsocket socket;

	@Autowired
	private StockMarketService stockMarketService;

	@PostConstruct
	public void initTask() {
		Timer timer = new Timer();
		timer.schedule(new RetriveTask(), Push_Interval);
	}

	private class RetriveTask extends TimerTask {

		@Override
		public void run() {
			try {
				List<StockMarketExponentDto> exponentList = stockMarketService.listStockExponent();
				socket.sendStockMarketIndex(exponentList);
			} finally {
				initTask();
			}
		}

	}

}
