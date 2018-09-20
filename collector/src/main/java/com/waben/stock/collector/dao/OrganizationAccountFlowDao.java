package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.OrganizationAccountFlow;

/**
 * 机构账户流水 Dao
 * 
 * @author luomengan
 *
 */
public interface OrganizationAccountFlowDao {

	public OrganizationAccountFlow createOrganizationAccountFlow(OrganizationAccountFlow organizationAccountFlow);

	public void deleteOrganizationAccountFlowById(Long id);

	public OrganizationAccountFlow updateOrganizationAccountFlow(OrganizationAccountFlow organizationAccountFlow);

	public OrganizationAccountFlow retrieveOrganizationAccountFlowById(Long id);

	public Page<OrganizationAccountFlow> pageOrganizationAccountFlow(int page, int limit);
	
	public List<OrganizationAccountFlow> listOrganizationAccountFlow();
	
	public OrganizationAccountFlow getByDomainAndDataId(String domain, Long dataId);

}
