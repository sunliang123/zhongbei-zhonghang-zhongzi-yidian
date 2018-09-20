package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.Domain;

/**
 * 应用 Dao
 * 
 * @author luomengan
 *
 */
public interface DomainDao {

	public Domain createDomain(Domain domain);

	public void deleteDomainById(Long id);

	public Domain updateDomain(Domain domain);

	public Domain retrieveDomainById(Long id);

	public Page<Domain> pageDomain(int page, int limit);
	
	public List<Domain> listDomain();

}
