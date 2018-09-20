package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.OrganizationDao;
import com.waben.stock.collector.dao.impl.jpa.OrganizationRepository;
import com.waben.stock.collector.entity.Organization;

/**
 * 机构 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public Organization createOrganization(Organization organization) {
		return organizationRepository.save(organization);
	}

	@Override
	public void deleteOrganizationById(Long id) {
		organizationRepository.delete(id);
	}

	@Override
	public Organization updateOrganization(Organization organization) {
		return organizationRepository.save(organization);
	}

	@Override
	public Organization retrieveOrganizationById(Long id) {
		return organizationRepository.findById(id);
	}

	@Override
	public Page<Organization> pageOrganization(int page, int limit) {
		return organizationRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<Organization> listOrganization() {
		return organizationRepository.findAll();
	}

	@Override
	public Organization getByDomainAndDataId(String domain, Long dataId) {
		return organizationRepository.findByDomainAndDataId(domain, dataId);
	}

}
