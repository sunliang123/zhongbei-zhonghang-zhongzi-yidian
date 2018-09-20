package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.CapitalFlow;

/**
 * 资金流水 Repository
 * 
 * @author luomengan
 *
 */
public interface CapitalFlowRepository extends Repository<CapitalFlow, Long> {

	CapitalFlow save(CapitalFlow capitalFlow);

	void delete(Long id);

	Page<CapitalFlow> findAll(Pageable pageable);
	
	List<CapitalFlow> findAll();

	CapitalFlow findById(Long id);

	CapitalFlow findByDomainAndDataId(String domain, Long dataId);
	
}
