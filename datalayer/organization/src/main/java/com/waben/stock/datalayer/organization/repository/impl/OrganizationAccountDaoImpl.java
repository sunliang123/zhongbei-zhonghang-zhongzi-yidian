package com.waben.stock.datalayer.organization.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.entity.OrganizationAccount;
import com.waben.stock.datalayer.organization.repository.OrganizationAccountDao;
import com.waben.stock.datalayer.organization.repository.impl.jpa.OrganizationAccountRepository;

/**
 * 机构账户 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class OrganizationAccountDaoImpl implements OrganizationAccountDao {

	@Autowired
	private OrganizationAccountRepository repository;

	@Override
	public OrganizationAccount create(OrganizationAccount t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public OrganizationAccount update(OrganizationAccount t) {
		return repository.save(t);
	}

	@Override
	public OrganizationAccount retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<OrganizationAccount> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<OrganizationAccount> page(Specification<OrganizationAccount> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<OrganizationAccount> list() {
		return repository.findAll();
	}

	@Override
	public OrganizationAccount retrieveByOrg(Organization org) {
		return repository.findByOrg(org);
	}

}
