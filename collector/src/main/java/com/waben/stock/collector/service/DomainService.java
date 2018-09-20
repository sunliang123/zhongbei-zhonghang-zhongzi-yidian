package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.DomainDao;
import com.waben.stock.collector.entity.Domain;

/**
 * 应用 Service
 * 
 * @author luomengan
 *
 */
@Service
public class DomainService {

	@Autowired
	private DomainDao domainDao;

	public Domain getDomainInfo(Long id) {
		return domainDao.retrieveDomainById(id);
	}

	@Transactional
	public Domain addDomain(Domain domain) {
		return domainDao.createDomain(domain);
	}

	@Transactional
	public Domain modifyDomain(Domain domain) {
		return domainDao.updateDomain(domain);
	}

	@Transactional
	public void deleteDomain(Long id) {
		domainDao.deleteDomainById(id);
	}
	
	@Transactional
	public void deleteDomains(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					domainDao.deleteDomainById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<Domain> domains(int page, int limit) {
		return domainDao.pageDomain(page, limit);
	}
	
	public List<Domain> list() {
		return domainDao.listDomain();
	}

}
