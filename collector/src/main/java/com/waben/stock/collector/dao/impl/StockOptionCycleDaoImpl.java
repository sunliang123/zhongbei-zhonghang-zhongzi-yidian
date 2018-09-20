package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.StockOptionCycleDao;
import com.waben.stock.collector.dao.impl.jpa.StockOptionCycleRepository;
import com.waben.stock.collector.entity.StockOptionCycle;

/**
 * 期权周期 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class StockOptionCycleDaoImpl implements StockOptionCycleDao {

	@Autowired
	private StockOptionCycleRepository stockOptionCycleRepository;

	@Override
	public StockOptionCycle createStockOptionCycle(StockOptionCycle stockOptionCycle) {
		return stockOptionCycleRepository.save(stockOptionCycle);
	}

	@Override
	public void deleteStockOptionCycleById(Long id) {
		stockOptionCycleRepository.delete(id);
	}

	@Override
	public StockOptionCycle updateStockOptionCycle(StockOptionCycle stockOptionCycle) {
		return stockOptionCycleRepository.save(stockOptionCycle);
	}

	@Override
	public StockOptionCycle retrieveStockOptionCycleById(Long id) {
		return stockOptionCycleRepository.findById(id);
	}

	@Override
	public Page<StockOptionCycle> pageStockOptionCycle(int page, int limit) {
		return stockOptionCycleRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<StockOptionCycle> listStockOptionCycle() {
		return stockOptionCycleRepository.findAll();
	}

	@Override
	public StockOptionCycle getByDomainAndDataId(String domain, Long dataId) {
		return stockOptionCycleRepository.findByDomainAndDataId(domain, dataId);
	}

}
