package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.StockOptionCycleDao;
import com.waben.stock.collector.entity.StockOptionCycle;

/**
 * 期权周期 Service
 * 
 * @author luomengan
 *
 */
@Service
public class StockOptionCycleService {

	@Autowired
	private StockOptionCycleDao stockOptionCycleDao;

	public StockOptionCycle getStockOptionCycleInfo(Long id) {
		return stockOptionCycleDao.retrieveStockOptionCycleById(id);
	}

	@Transactional
	public StockOptionCycle addStockOptionCycle(StockOptionCycle stockOptionCycle) {
		return stockOptionCycleDao.createStockOptionCycle(stockOptionCycle);
	}

	@Transactional
	public StockOptionCycle modifyStockOptionCycle(StockOptionCycle stockOptionCycle) {
		return stockOptionCycleDao.updateStockOptionCycle(stockOptionCycle);
	}

	@Transactional
	public void deleteStockOptionCycle(Long id) {
		stockOptionCycleDao.deleteStockOptionCycleById(id);
	}
	
	@Transactional
	public void deleteStockOptionCycles(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					stockOptionCycleDao.deleteStockOptionCycleById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<StockOptionCycle> stockOptionCycles(int page, int limit) {
		return stockOptionCycleDao.pageStockOptionCycle(page, limit);
	}
	
	public List<StockOptionCycle> list() {
		return stockOptionCycleDao.listStockOptionCycle();
	}

}
