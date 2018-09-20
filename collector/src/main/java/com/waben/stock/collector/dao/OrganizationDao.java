package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.Organization;

/**
 * 机构 Dao
 * 
 * @author luomengan
 *
 */
public interface OrganizationDao {

	public Organization createOrganization(Organization organization);

	public void deleteOrganizationById(Long id);

	public Organization updateOrganization(Organization organization);

	public Organization retrieveOrganizationById(Long id);

	public Page<Organization> pageOrganization(int page, int limit);
	
	public List<Organization> listOrganization();
	
	public Organization getByDomainAndDataId(String domain, Long dataId);

}
