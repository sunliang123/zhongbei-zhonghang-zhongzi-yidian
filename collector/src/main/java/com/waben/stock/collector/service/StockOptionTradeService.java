package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.StockOptionTradeDao;
import com.waben.stock.collector.entity.StockOptionTrade;

/**
 * 用户股票期权交易信息 Service
 * 
 * @author luomengan
 *
 */
@Service
public class StockOptionTradeService {

	@Autowired
	private StockOptionTradeDao stockOptionTradeDao;

	public StockOptionTrade getStockOptionTradeInfo(Long id) {
		return stockOptionTradeDao.retrieveStockOptionTradeById(id);
	}

	@Transactional
	public StockOptionTrade addStockOptionTrade(StockOptionTrade stockOptionTrade) {
		return stockOptionTradeDao.createStockOptionTrade(stockOptionTrade);
	}

	@Transactional
	public StockOptionTrade modifyStockOptionTrade(StockOptionTrade stockOptionTrade) {
		return stockOptionTradeDao.updateStockOptionTrade(stockOptionTrade);
	}

	@Transactional
	public void deleteStockOptionTrade(Long id) {
		stockOptionTradeDao.deleteStockOptionTradeById(id);
	}
	
	@Transactional
	public void deleteStockOptionTrades(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					stockOptionTradeDao.deleteStockOptionTradeById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<StockOptionTrade> stockOptionTrades(int page, int limit) {
		return stockOptionTradeDao.pageStockOptionTrade(page, limit);
	}
	
	public List<StockOptionTrade> list() {
		return stockOptionTradeDao.listStockOptionTrade();
	}

}
