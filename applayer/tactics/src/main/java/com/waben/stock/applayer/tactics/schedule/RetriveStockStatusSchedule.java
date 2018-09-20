package com.waben.stock.applayer.tactics.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.datalayer.stockcontent.entity.Stock;
import com.waben.stock.datalayer.stockcontent.service.StockService;
import com.waben.stock.interfaces.commonapi.retrivestock.RetriveStockOverHttp;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;

/**
 * 定期更新股票是否停牌信息
 * 
 * @author luomengan
 * 
 */
@Component
public class RetriveStockStatusSchedule {

	public static final long Retrive_Interval = 2 * 60 * 60 * 1000;

	@Autowired
	private StockService stockServcie;

	private RestTemplate restTemplate = new RestTemplate();

	@PostConstruct
	public void initTask() {
		Timer timer = new Timer();
		timer.schedule(new RetriveTask(), 30 * 1000);
	}

	public void next() {
		Timer timer = new Timer();
		timer.schedule(new RetriveTask(), Retrive_Interval);
	}

	private class RetriveTask extends TimerTask {
		@Override
		public void run() {
			try {
				String[] exponentCodes = new String[] { "4609", "4353", "4621" };
				for (String exponentCode : exponentCodes) {
					List<Stock> stocks = stockServcie.findByExponentCode(exponentCode);
					List<String> codes = new ArrayList<>();
					int index = 0;
					for (Stock stock : stocks) {
						codes.add(stock.getCode());
						if (codes.size() >= 500 || index == stocks.size() - 1) {
							List<StockMarket> markets = RetriveStockOverHttp.listStockMarket(restTemplate, codes);
							for (StockMarket market : markets) {
								Stock stockEntity = stockServcie.findByCode(market.getInstrumentId());
								boolean status = market.getStatus() == 1 ? true : false;
								if (!(stockEntity.getStatus() != null
										&& stockEntity.getStatus().booleanValue() == status)) {
									stockEntity.setStatus(status);
									stockServcie.revision(stockEntity);
								}
							}
							codes.clear();
						}
						index++;
					}
				}
			} finally {
				next();
			}
		}
	}

}
