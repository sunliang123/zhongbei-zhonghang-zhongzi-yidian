package com.waben.stock.applayer.tactics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.applayer.tactics.dto.system.StockMarketExponentDto;
import com.waben.stock.interfaces.commonapi.retrivestock.RetriveStockOverHttp;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockExponentVariety;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;

/**
 * 股票行情 Service
 * 
 * @author luomengan
 *
 */
@Service
public class StockMarketService {

	@Autowired
	private RestTemplate restTemplate;

	public List<StockMarketExponentDto> listStockExponent() {
		List<StockMarketExponentDto> result = new ArrayList<>();
		List<StockExponentVariety> varietyList = RetriveStockOverHttp.listStockExponentVariety(restTemplate);
		if (varietyList != null && varietyList.size() > 0) {
			List<String> codes = new ArrayList<>();
			for (StockExponentVariety variety : varietyList) {
				codes.add(variety.getVarietyType());
			}
			List<StockMarket> stockMarketList = RetriveStockOverHttp.listStockMarket(restTemplate, codes);
			for (int i = 0; i < varietyList.size(); i++) {
				StockMarketExponentDto exponent = new StockMarketExponentDto();
				exponent.setCode(varietyList.get(i).getVarietyType());
				exponent.setName(varietyList.get(i).getVarietyName());
				exponent.setLastPrice(stockMarketList.get(i).getLastPrice());
				exponent.setUpDropPrice(stockMarketList.get(i).getUpDropPrice());
				exponent.setUpDropSpeed(stockMarketList.get(i).getUpDropSpeed());
				result.add(exponent);
			}
		}
		return result;
	}

	public List<StockMarket> listStockMarket(List<String> codes) {
		return RetriveStockOverHttp.listStockMarket(restTemplate, codes);
	}

}
