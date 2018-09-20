package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.OrganizationAccountDao;
import com.waben.stock.collector.dao.impl.jpa.OrganizationAccountRepository;
import com.waben.stock.collector.entity.OrganizationAccount;

/**
 * 机构账户 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class OrganizationAccountDaoImpl implements OrganizationAccountDao {

	@Autowired
	private OrganizationAccountRepository organizationAccountRepository;

	@Override
	public OrganizationAccount createOrganizationAccount(OrganizationAccount organizationAccount) {
		return organizationAccountRepository.save(organizationAccount);
	}

	@Override
	public void deleteOrganizationAccountById(Long id) {
		organizationAccountRepository.delete(id);
	}

	@Override
	public OrganizationAccount updateOrganizationAccount(OrganizationAccount organizationAccount) {
		return organizationAccountRepository.save(organizationAccount);
	}

	@Override
	public OrganizationAccount retrieveOrganizationAccountById(Long id) {
		return organizationAccountRepository.findById(id);
	}

	@Override
	public Page<OrganizationAccount> pageOrganizationAccount(int page, int limit) {
		return organizationAccountRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<OrganizationAccount> listOrganizationAccount() {
		return organizationAccountRepository.findAll();
	}

	@Override
	public OrganizationAccount getByDomainAndDataId(String domain, Long dataId) {
		return organizationAccountRepository.findByDomainAndDataId(domain, dataId);
	}

}
