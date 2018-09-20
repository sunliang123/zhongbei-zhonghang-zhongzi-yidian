package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.OrganizationPublisherDao;
import com.waben.stock.collector.entity.OrganizationPublisher;

/**
 * 机构推广的发布人 Service
 * 
 * @author luomengan
 *
 */
@Service
public class OrganizationPublisherService {

	@Autowired
	private OrganizationPublisherDao organizationPublisherDao;

	public OrganizationPublisher getOrganizationPublisherInfo(Long id) {
		return organizationPublisherDao.retrieveOrganizationPublisherById(id);
	}

	@Transactional
	public OrganizationPublisher addOrganizationPublisher(OrganizationPublisher organizationPublisher) {
		return organizationPublisherDao.createOrganizationPublisher(organizationPublisher);
	}

	@Transactional
	public OrganizationPublisher modifyOrganizationPublisher(OrganizationPublisher organizationPublisher) {
		return organizationPublisherDao.updateOrganizationPublisher(organizationPublisher);
	}

	@Transactional
	public void deleteOrganizationPublisher(Long id) {
		organizationPublisherDao.deleteOrganizationPublisherById(id);
	}
	
	@Transactional
	public void deleteOrganizationPublishers(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					organizationPublisherDao.deleteOrganizationPublisherById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<OrganizationPublisher> organizationPublishers(int page, int limit) {
		return organizationPublisherDao.pageOrganizationPublisher(page, limit);
	}
	
	public List<OrganizationPublisher> list() {
		return organizationPublisherDao.listOrganizationPublisher();
	}

}
