package com.waben.stock.applayer.tactics.schedule;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.datalayer.stockcontent.entity.Stock;
import com.waben.stock.datalayer.stockcontent.entity.StockExponent;
import com.waben.stock.datalayer.stockcontent.service.StockExponentService;
import com.waben.stock.datalayer.stockcontent.service.StockService;
import com.waben.stock.datalayer.stockcontent.util.PinyinUtil;
import com.waben.stock.interfaces.commonapi.retrivestock.RetriveStockOverHttp;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockVariety;

/**
 * 更新股票信息作业
 * 
 * <p>
 * 用于更新股票信息，不需要定期更新
 * </p>
 * 
 * @author luomengan
 *
 */
@Component
public class RetriveStockInfoSchedule {

	public static final long Retrive_Interval = 30 * 60 * 1000;

	@Autowired
	private StockService stockServcie;

	@Autowired
	private StockExponentService stockExponentService;

	private RestTemplate restTemplate = new RestTemplate();

	@PostConstruct
	public void initTask() {
		Timer timer = new Timer();
		timer.schedule(new RetriveTask(), Retrive_Interval);
	}

	private class RetriveTask extends TimerTask {
		@Override
		public void run() {
			int page = 0;
			int pageSize = 500;
			List<StockVariety> stockList = RetriveStockOverHttp.listStockVariety(restTemplate, page, pageSize);
			while (stockList != null && stockList.size() > 0) {
				for (StockVariety stock : stockList) {
					// 更新股票信息
					Stock stockEntity = stockServcie.findByCode(stock.getVarietyType());
					if (stockEntity != null) {
						String stockName = stock.getVarietyName().replaceAll(" ", "").replaceAll("Ａ", "A");
						stockEntity.setName(stockName);
						stockEntity.setAbbr(PinyinUtil.getFirstSpell(stockName));
						if (!stock.getExchangeCode().equals(stockEntity.getExponent().getExponentCode())) {
							StockExponent exponent = stockExponentService
									.findStockExponentOnly(stock.getExchangeCode());
							stockEntity.setExponent(exponent);
						}
						stockServcie.revision(stockEntity);
					} else {
						stockEntity = new Stock();
						stockEntity.setStatus(false);
						stockEntity.setCode(stock.getVarietyType());
						String stockName = stock.getVarietyName().replaceAll(" ", "").replaceAll("Ａ", "A");
						stockEntity.setName(stockName);
						stockEntity.setAbbr(PinyinUtil.getFirstSpell(stockName));
						StockExponent exponent = stockExponentService.findStockExponentOnly(stock.getExchangeCode());
						stockEntity.setExponent(exponent);
						stockServcie.save(stockEntity);
					}
				}
				page++;
				stockList = RetriveStockOverHttp.listStockVariety(restTemplate, page, pageSize);
			}
		}
	}

}
