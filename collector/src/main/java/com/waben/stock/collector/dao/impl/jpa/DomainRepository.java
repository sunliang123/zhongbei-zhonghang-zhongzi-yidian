package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.Domain;

/**
 * 应用 Repository
 * 
 * @author luomengan
 *
 */
public interface DomainRepository extends Repository<Domain, Long> {

	Domain save(Domain domain);

	void delete(Long id);

	Page<Domain> findAll(Pageable pageable);
	
	List<Domain> findAll();

	Domain findById(Long id);
	
}
