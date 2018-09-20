package com.waben.stock.applayer.strategist.schedule;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.applayer.strategist.websocket.StockMarketWebsocket;
import com.waben.stock.applayer.strategist.websocket.config.Cache;
import com.waben.stock.interfaces.commonapi.retrivestock.RetriveStockOverHttp;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockTimeLine;

/**
 * 推送股票分时图作业
 * 
 * @author luomengan
 *
 */
// @Component
public class PushStockTimeLineSchedule {

	/**
	 * 推送间隔
	 */
	public static final long Push_Interval = 60000;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StockMarketWebsocket socket;

	@Autowired
	private RestTemplate restTemplate;

	@PostConstruct
	public void initTask() {
		Timer timer = new Timer();
		timer.schedule(new RetriveTask(), Push_Interval);
	}

	private class RetriveTask extends TimerTask {
		@Override
		public void run() {
			try {
				for (Map.Entry<String, Integer> entry : Cache.timeLineCache.entrySet()) {
					String stockCode = entry.getKey();
					try {
						List<StockTimeLine> list = RetriveStockOverHttp.listTimeLine(restTemplate, stockCode);
						if (list != null && list.size() > 0) {
							socket.sendStockTimeLine(list);
						}
					} catch (Exception ex) {
						logger.error("push {} timeLine exception!", stockCode);
					}
				}
			} finally {
				initTask();
			}
		}
	}

}
