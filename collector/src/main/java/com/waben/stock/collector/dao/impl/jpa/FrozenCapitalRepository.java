package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.FrozenCapital;

/**
 * 冻结资金 Repository
 * 
 * @author luomengan
 *
 */
public interface FrozenCapitalRepository extends Repository<FrozenCapital, Long> {

	FrozenCapital save(FrozenCapital frozenCapital);

	void delete(Long id);

	Page<FrozenCapital> findAll(Pageable pageable);
	
	List<FrozenCapital> findAll();

	FrozenCapital findById(Long id);

	FrozenCapital findByDomainAndDataId(String domain, Long dataId);
	
}
