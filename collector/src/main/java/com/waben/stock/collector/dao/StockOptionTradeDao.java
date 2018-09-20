package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.StockOptionTrade;

/**
 * 用户股票期权交易信息 Dao
 * 
 * @author luomengan
 *
 */
public interface StockOptionTradeDao {

	public StockOptionTrade createStockOptionTrade(StockOptionTrade stockOptionTrade);

	public void deleteStockOptionTradeById(Long id);

	public StockOptionTrade updateStockOptionTrade(StockOptionTrade stockOptionTrade);

	public StockOptionTrade retrieveStockOptionTradeById(Long id);

	public Page<StockOptionTrade> pageStockOptionTrade(int page, int limit);
	
	public List<StockOptionTrade> listStockOptionTrade();
	
	public StockOptionTrade getByDomainAndDataId(String domain, Long dataId);

}
