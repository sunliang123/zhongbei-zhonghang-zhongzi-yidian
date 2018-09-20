package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.DomainTableReceiveErrorDao;
import com.waben.stock.collector.entity.DomainTableReceiveError;

/**
 * 同步错误日志 Service
 * 
 * @author luomengan
 *
 */
@Service
public class DomainTableReceiveErrorService {

	@Autowired
	private DomainTableReceiveErrorDao domainTableReceiveErrorDao;

	public DomainTableReceiveError getDomainTableReceiveErrorInfo(Integer id) {
		return domainTableReceiveErrorDao.retrieveDomainTableReceiveErrorById(id);
	}

	@Transactional
	public DomainTableReceiveError addDomainTableReceiveError(DomainTableReceiveError domainTableReceiveError) {
		return domainTableReceiveErrorDao.createDomainTableReceiveError(domainTableReceiveError);
	}

	@Transactional
	public DomainTableReceiveError modifyDomainTableReceiveError(DomainTableReceiveError domainTableReceiveError) {
		return domainTableReceiveErrorDao.updateDomainTableReceiveError(domainTableReceiveError);
	}

	@Transactional
	public void deleteDomainTableReceiveError(Integer id) {
		domainTableReceiveErrorDao.deleteDomainTableReceiveErrorById(id);
	}
	
	@Transactional
	public void deleteDomainTableReceiveErrors(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					domainTableReceiveErrorDao.deleteDomainTableReceiveErrorById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<DomainTableReceiveError> domainTableReceiveErrors(int page, int limit) {
		return domainTableReceiveErrorDao.pageDomainTableReceiveError(page, limit);
	}
	
	public List<DomainTableReceiveError> list() {
		return domainTableReceiveErrorDao.listDomainTableReceiveError();
	}

}
