package com.waben.stock.datalayer.stockoption.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.stockoption.entity.StockOptionCycle;
import com.waben.stock.datalayer.stockoption.repository.StockOptionCycleDao;

/**
 * 期权周期 Service
 * 
 * @author luomengan
 *
 */
@Service
public class StockOptionCycleService {

	@Autowired
	private StockOptionCycleDao cycleDao;

	public List<StockOptionCycle> lists() {
		return cycleDao.list();
	}

	public StockOptionCycle findById(Long id) {
		return cycleDao.retrieve(id);
	}

	public StockOptionCycle findByCycle(Integer cycle) {
		return cycleDao.retrieveByCycle(cycle);
	}
	
}
