package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.DomainTableReceiveError;

/**
 * 同步错误日志 Repository
 * 
 * @author luomengan
 *
 */
public interface DomainTableReceiveErrorRepository extends Repository<DomainTableReceiveError, Integer> {

	DomainTableReceiveError save(DomainTableReceiveError domainTableReceiveError);

	void delete(Integer id);

	Page<DomainTableReceiveError> findAll(Pageable pageable);
	
	List<DomainTableReceiveError> findAll();

	DomainTableReceiveError findById(Integer id);
	
}
