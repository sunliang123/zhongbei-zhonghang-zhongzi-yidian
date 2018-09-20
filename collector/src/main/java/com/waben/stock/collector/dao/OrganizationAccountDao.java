package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.OrganizationAccount;

/**
 * 机构账户 Dao
 * 
 * @author luomengan
 *
 */
public interface OrganizationAccountDao {

	public OrganizationAccount createOrganizationAccount(OrganizationAccount organizationAccount);

	public void deleteOrganizationAccountById(Long id);

	public OrganizationAccount updateOrganizationAccount(OrganizationAccount organizationAccount);

	public OrganizationAccount retrieveOrganizationAccountById(Long id);

	public Page<OrganizationAccount> pageOrganizationAccount(int page, int limit);
	
	public List<OrganizationAccount> listOrganizationAccount();
	
	public OrganizationAccount getByDomainAndDataId(String domain, Long dataId);

}
