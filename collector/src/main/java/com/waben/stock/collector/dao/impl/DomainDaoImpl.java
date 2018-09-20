package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.DomainDao;
import com.waben.stock.collector.dao.impl.jpa.DomainRepository;
import com.waben.stock.collector.entity.Domain;

/**
 * 应用 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class DomainDaoImpl implements DomainDao {

	@Autowired
	private DomainRepository domainRepository;

	@Override
	public Domain createDomain(Domain domain) {
		return domainRepository.save(domain);
	}

	@Override
	public void deleteDomainById(Long id) {
		domainRepository.delete(id);
	}

	@Override
	public Domain updateDomain(Domain domain) {
		return domainRepository.save(domain);
	}

	@Override
	public Domain retrieveDomainById(Long id) {
		return domainRepository.findById(id);
	}

	@Override
	public Page<Domain> pageDomain(int page, int limit) {
		return domainRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<Domain> listDomain() {
		return domainRepository.findAll();
	}

}
