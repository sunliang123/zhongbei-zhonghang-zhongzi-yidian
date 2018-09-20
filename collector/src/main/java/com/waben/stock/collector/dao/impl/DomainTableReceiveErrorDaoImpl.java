package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.DomainTableReceiveErrorDao;
import com.waben.stock.collector.dao.impl.jpa.DomainTableReceiveErrorRepository;
import com.waben.stock.collector.entity.DomainTableReceiveError;

/**
 * 同步错误日志 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class DomainTableReceiveErrorDaoImpl implements DomainTableReceiveErrorDao {

	@Autowired
	private DomainTableReceiveErrorRepository domainTableReceiveErrorRepository;

	@Override
	public DomainTableReceiveError createDomainTableReceiveError(DomainTableReceiveError domainTableReceiveError) {
		return domainTableReceiveErrorRepository.save(domainTableReceiveError);
	}

	@Override
	public void deleteDomainTableReceiveErrorById(Integer id) {
		domainTableReceiveErrorRepository.delete(id);
	}

	@Override
	public DomainTableReceiveError updateDomainTableReceiveError(DomainTableReceiveError domainTableReceiveError) {
		return domainTableReceiveErrorRepository.save(domainTableReceiveError);
	}

	@Override
	public DomainTableReceiveError retrieveDomainTableReceiveErrorById(Integer id) {
		return domainTableReceiveErrorRepository.findById(id);
	}

	@Override
	public Page<DomainTableReceiveError> pageDomainTableReceiveError(int page, int limit) {
		return domainTableReceiveErrorRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<DomainTableReceiveError> listDomainTableReceiveError() {
		return domainTableReceiveErrorRepository.findAll();
	}

}
