package com.waben.stock.datalayer.organization.repository;

import java.util.List;

import com.waben.stock.datalayer.organization.entity.Organization;

/**
 * 机构 Dao
 * 
 * @author luomengan
 *
 */
public interface OrganizationDao extends BaseDao<Organization, Long> {

	List<Organization> listByParent(Organization parent);

	List<Organization> listByParentOrderByCodeDesc(Organization parent);

	Organization retrieveByCode(String orgCode);
	
	List<Organization> retriveByName(String orgName);

	List<Organization> listByLevel(Integer level);

	Organization retrieveOrganizationByName(String orgName);
	
	Organization findByOrgId(Long orgId);
	
	

	Organization getNewestOrg();

}
