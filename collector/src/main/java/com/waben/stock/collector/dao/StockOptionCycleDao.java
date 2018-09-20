package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.StockOptionCycle;

/**
 * 期权周期 Dao
 * 
 * @author luomengan
 *
 */
public interface StockOptionCycleDao {

	public StockOptionCycle createStockOptionCycle(StockOptionCycle stockOptionCycle);

	public void deleteStockOptionCycleById(Long id);

	public StockOptionCycle updateStockOptionCycle(StockOptionCycle stockOptionCycle);

	public StockOptionCycle retrieveStockOptionCycleById(Long id);

	public Page<StockOptionCycle> pageStockOptionCycle(int page, int limit);
	
	public List<StockOptionCycle> listStockOptionCycle();
	
	public StockOptionCycle getByDomainAndDataId(String domain, Long dataId);

}
