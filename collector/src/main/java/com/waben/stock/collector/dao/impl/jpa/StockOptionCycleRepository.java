package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.StockOptionCycle;

/**
 * 期权周期 Repository
 * 
 * @author luomengan
 *
 */
public interface StockOptionCycleRepository extends Repository<StockOptionCycle, Long> {

	StockOptionCycle save(StockOptionCycle stockOptionCycle);

	void delete(Long id);

	Page<StockOptionCycle> findAll(Pageable pageable);
	
	List<StockOptionCycle> findAll();

	StockOptionCycle findById(Long id);

	StockOptionCycle findByDomainAndDataId(String domain, Long dataId);
	
}
