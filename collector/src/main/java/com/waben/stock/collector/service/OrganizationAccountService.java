package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.OrganizationAccountDao;
import com.waben.stock.collector.entity.OrganizationAccount;

/**
 * 机构账户 Service
 * 
 * @author luomengan
 *
 */
@Service
public class OrganizationAccountService {

	@Autowired
	private OrganizationAccountDao organizationAccountDao;

	public OrganizationAccount getOrganizationAccountInfo(Long id) {
		return organizationAccountDao.retrieveOrganizationAccountById(id);
	}

	@Transactional
	public OrganizationAccount addOrganizationAccount(OrganizationAccount organizationAccount) {
		return organizationAccountDao.createOrganizationAccount(organizationAccount);
	}

	@Transactional
	public OrganizationAccount modifyOrganizationAccount(OrganizationAccount organizationAccount) {
		return organizationAccountDao.updateOrganizationAccount(organizationAccount);
	}

	@Transactional
	public void deleteOrganizationAccount(Long id) {
		organizationAccountDao.deleteOrganizationAccountById(id);
	}
	
	@Transactional
	public void deleteOrganizationAccounts(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					organizationAccountDao.deleteOrganizationAccountById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<OrganizationAccount> organizationAccounts(int page, int limit) {
		return organizationAccountDao.pageOrganizationAccount(page, limit);
	}
	
	public List<OrganizationAccount> list() {
		return organizationAccountDao.listOrganizationAccount();
	}

}
