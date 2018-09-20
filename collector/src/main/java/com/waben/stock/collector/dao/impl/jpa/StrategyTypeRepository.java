package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.StrategyType;

/**
 * 策略类型 Repository
 * 
 * @author luomengan
 *
 */
public interface StrategyTypeRepository extends Repository<StrategyType, Long> {

	StrategyType save(StrategyType strategyType);

	void delete(Long id);

	Page<StrategyType> findAll(Pageable pageable);
	
	List<StrategyType> findAll();

	StrategyType findById(Long id);

	StrategyType findByDomainAndDataId(String domain, Long dataId);
	
}
