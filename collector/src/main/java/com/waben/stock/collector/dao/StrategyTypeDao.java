package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.StrategyType;

/**
 * 策略类型 Dao
 * 
 * @author luomengan
 *
 */
public interface StrategyTypeDao {

	public StrategyType createStrategyType(StrategyType strategyType);

	public void deleteStrategyTypeById(Long id);

	public StrategyType updateStrategyType(StrategyType strategyType);

	public StrategyType retrieveStrategyTypeById(Long id);

	public Page<StrategyType> pageStrategyType(int page, int limit);
	
	public List<StrategyType> listStrategyType();
	
	public StrategyType getByDomainAndDataId(String domain, Long dataId);

}
