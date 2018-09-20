package com.waben.stock.datalayer.organization.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.organization.entity.OrganizationAccountFlow;
import com.waben.stock.datalayer.organization.repository.OrganizationAccountFlowDao;
import com.waben.stock.datalayer.organization.repository.impl.jpa.OrganizationAccountFlowRepository;
import com.waben.stock.interfaces.enums.OrganizationAccountFlowType;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 机构账户流水 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class OrganizationAccountFlowDaoImpl implements OrganizationAccountFlowDao {

	@Autowired
	private OrganizationAccountFlowRepository repository;

	@Override
	public OrganizationAccountFlow create(OrganizationAccountFlow t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public OrganizationAccountFlow update(OrganizationAccountFlow t) {
		return repository.save(t);
	}

	@Override
	public OrganizationAccountFlow retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<OrganizationAccountFlow> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<OrganizationAccountFlow> page(Specification<OrganizationAccountFlow> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<OrganizationAccountFlow> list() {
		return repository.findAll();
	}

	@Override
	public List<OrganizationAccountFlow> retrieveByTypeAndResourceTypeAndResourceId(
			OrganizationAccountFlowType flowType, ResourceType resourceType, Long resourceId) {
		return repository.findByTypeAndResourceTypeAndResourceId(flowType, resourceType, resourceId);
	}

}
