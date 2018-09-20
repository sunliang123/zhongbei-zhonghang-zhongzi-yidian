package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.DomainTable;

/**
 * 应用表 Dao
 * 
 * @author luomengan
 *
 */
public interface DomainTableDao {

	public DomainTable createDomainTable(DomainTable domainTable);

	public void deleteDomainTableById(Long id);

	public DomainTable updateDomainTable(DomainTable domainTable);

	public DomainTable retrieveDomainTableById(Long id);

	public Page<DomainTable> pageDomainTable(int page, int limit);
	
	public List<DomainTable> listDomainTable();

	public List<DomainTable> listByDomain(String domain);

}
