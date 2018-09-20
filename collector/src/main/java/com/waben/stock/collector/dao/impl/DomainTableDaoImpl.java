package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.DomainTableDao;
import com.waben.stock.collector.dao.impl.jpa.DomainTableRepository;
import com.waben.stock.collector.entity.DomainTable;

/**
 * 应用表 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class DomainTableDaoImpl implements DomainTableDao {

	@Autowired
	private DomainTableRepository domainTableRepository;

	@Override
	public DomainTable createDomainTable(DomainTable domainTable) {
		return domainTableRepository.save(domainTable);
	}

	@Override
	public void deleteDomainTableById(Long id) {
		domainTableRepository.delete(id);
	}

	@Override
	public DomainTable updateDomainTable(DomainTable domainTable) {
		return domainTableRepository.save(domainTable);
	}

	@Override
	public DomainTable retrieveDomainTableById(Long id) {
		return domainTableRepository.findById(id);
	}

	@Override
	public Page<DomainTable> pageDomainTable(int page, int limit) {
		return domainTableRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<DomainTable> listDomainTable() {
		return domainTableRepository.findAll();
	}

	@Override
	public List<DomainTable> listByDomain(String domain) {
		return domainTableRepository.findByDomain(domain);
	}

}
