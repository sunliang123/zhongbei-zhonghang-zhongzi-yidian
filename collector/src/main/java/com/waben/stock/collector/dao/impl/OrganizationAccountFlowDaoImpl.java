package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.OrganizationAccountFlowDao;
import com.waben.stock.collector.dao.impl.jpa.OrganizationAccountFlowRepository;
import com.waben.stock.collector.entity.OrganizationAccountFlow;

/**
 * 机构账户流水 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class OrganizationAccountFlowDaoImpl implements OrganizationAccountFlowDao {

	@Autowired
	private OrganizationAccountFlowRepository organizationAccountFlowRepository;

	@Override
	public OrganizationAccountFlow createOrganizationAccountFlow(OrganizationAccountFlow organizationAccountFlow) {
		return organizationAccountFlowRepository.save(organizationAccountFlow);
	}

	@Override
	public void deleteOrganizationAccountFlowById(Long id) {
		organizationAccountFlowRepository.delete(id);
	}

	@Override
	public OrganizationAccountFlow updateOrganizationAccountFlow(OrganizationAccountFlow organizationAccountFlow) {
		return organizationAccountFlowRepository.save(organizationAccountFlow);
	}

	@Override
	public OrganizationAccountFlow retrieveOrganizationAccountFlowById(Long id) {
		return organizationAccountFlowRepository.findById(id);
	}

	@Override
	public Page<OrganizationAccountFlow> pageOrganizationAccountFlow(int page, int limit) {
		return organizationAccountFlowRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<OrganizationAccountFlow> listOrganizationAccountFlow() {
		return organizationAccountFlowRepository.findAll();
	}

	@Override
	public OrganizationAccountFlow getByDomainAndDataId(String domain, Long dataId) {
		return organizationAccountFlowRepository.findByDomainAndDataId(domain, dataId);
	}

}
