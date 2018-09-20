package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.StockOptionTradeDao;
import com.waben.stock.collector.dao.impl.jpa.StockOptionTradeRepository;
import com.waben.stock.collector.entity.StockOptionTrade;

/**
 * 用户股票期权交易信息 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class StockOptionTradeDaoImpl implements StockOptionTradeDao {

	@Autowired
	private StockOptionTradeRepository stockOptionTradeRepository;

	@Override
	public StockOptionTrade createStockOptionTrade(StockOptionTrade stockOptionTrade) {
		return stockOptionTradeRepository.save(stockOptionTrade);
	}

	@Override
	public void deleteStockOptionTradeById(Long id) {
		stockOptionTradeRepository.delete(id);
	}

	@Override
	public StockOptionTrade updateStockOptionTrade(StockOptionTrade stockOptionTrade) {
		return stockOptionTradeRepository.save(stockOptionTrade);
	}

	@Override
	public StockOptionTrade retrieveStockOptionTradeById(Long id) {
		return stockOptionTradeRepository.findById(id);
	}

	@Override
	public Page<StockOptionTrade> pageStockOptionTrade(int page, int limit) {
		return stockOptionTradeRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<StockOptionTrade> listStockOptionTrade() {
		return stockOptionTradeRepository.findAll();
	}

	@Override
	public StockOptionTrade getByDomainAndDataId(String domain, Long dataId) {
		return stockOptionTradeRepository.findByDomainAndDataId(domain, dataId);
	}

}
