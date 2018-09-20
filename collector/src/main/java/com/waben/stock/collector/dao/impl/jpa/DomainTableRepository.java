package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.DomainTable;

/**
 * 应用表 Repository
 * 
 * @author luomengan
 *
 */
public interface DomainTableRepository extends Repository<DomainTable, Long> {

	DomainTable save(DomainTable domainTable);

	void delete(Long id);

	Page<DomainTable> findAll(Pageable pageable);
	
	List<DomainTable> findAll();

	DomainTable findById(Long id);

	List<DomainTable> findByDomain(String domain);
	
}
