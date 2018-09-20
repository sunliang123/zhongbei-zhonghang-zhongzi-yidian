package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.StrategyTypeDao;
import com.waben.stock.collector.dao.impl.jpa.StrategyTypeRepository;
import com.waben.stock.collector.entity.StrategyType;

/**
 * 策略类型 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class StrategyTypeDaoImpl implements StrategyTypeDao {

	@Autowired
	private StrategyTypeRepository strategyTypeRepository;

	@Override
	public StrategyType createStrategyType(StrategyType strategyType) {
		return strategyTypeRepository.save(strategyType);
	}

	@Override
	public void deleteStrategyTypeById(Long id) {
		strategyTypeRepository.delete(id);
	}

	@Override
	public StrategyType updateStrategyType(StrategyType strategyType) {
		return strategyTypeRepository.save(strategyType);
	}

	@Override
	public StrategyType retrieveStrategyTypeById(Long id) {
		return strategyTypeRepository.findById(id);
	}

	@Override
	public Page<StrategyType> pageStrategyType(int page, int limit) {
		return strategyTypeRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<StrategyType> listStrategyType() {
		return strategyTypeRepository.findAll();
	}

	@Override
	public StrategyType getByDomainAndDataId(String domain, Long dataId) {
		return strategyTypeRepository.findByDomainAndDataId(domain, dataId);
	}

}
