package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.StrategyTypeDao;
import com.waben.stock.collector.entity.StrategyType;

/**
 * 策略类型 Service
 * 
 * @author luomengan
 *
 */
@Service
public class StrategyTypeService {

	@Autowired
	private StrategyTypeDao strategyTypeDao;

	public StrategyType getStrategyTypeInfo(Long id) {
		return strategyTypeDao.retrieveStrategyTypeById(id);
	}

	@Transactional
	public StrategyType addStrategyType(StrategyType strategyType) {
		return strategyTypeDao.createStrategyType(strategyType);
	}

	@Transactional
	public StrategyType modifyStrategyType(StrategyType strategyType) {
		return strategyTypeDao.updateStrategyType(strategyType);
	}

	@Transactional
	public void deleteStrategyType(Long id) {
		strategyTypeDao.deleteStrategyTypeById(id);
	}
	
	@Transactional
	public void deleteStrategyTypes(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					strategyTypeDao.deleteStrategyTypeById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<StrategyType> strategyTypes(int page, int limit) {
		return strategyTypeDao.pageStrategyType(page, limit);
	}
	
	public List<StrategyType> list() {
		return strategyTypeDao.listStrategyType();
	}

}
