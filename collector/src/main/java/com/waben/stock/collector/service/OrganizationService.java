package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.OrganizationDao;
import com.waben.stock.collector.entity.Organization;

/**
 * 机构 Service
 * 
 * @author luomengan
 *
 */
@Service
public class OrganizationService {

	@Autowired
	private OrganizationDao organizationDao;

	public Organization getOrganizationInfo(Long id) {
		return organizationDao.retrieveOrganizationById(id);
	}

	@Transactional
	public Organization addOrganization(Organization organization) {
		return organizationDao.createOrganization(organization);
	}

	@Transactional
	public Organization modifyOrganization(Organization organization) {
		return organizationDao.updateOrganization(organization);
	}

	@Transactional
	public void deleteOrganization(Long id) {
		organizationDao.deleteOrganizationById(id);
	}
	
	@Transactional
	public void deleteOrganizations(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					organizationDao.deleteOrganizationById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<Organization> organizations(int page, int limit) {
		return organizationDao.pageOrganization(page, limit);
	}
	
	public List<Organization> list() {
		return organizationDao.listOrganization();
	}

}
