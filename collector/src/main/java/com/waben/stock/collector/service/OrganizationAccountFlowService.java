package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.OrganizationAccountFlowDao;
import com.waben.stock.collector.entity.OrganizationAccountFlow;

/**
 * 机构账户流水 Service
 * 
 * @author luomengan
 *
 */
@Service
public class OrganizationAccountFlowService {

	@Autowired
	private OrganizationAccountFlowDao organizationAccountFlowDao;

	public OrganizationAccountFlow getOrganizationAccountFlowInfo(Long id) {
		return organizationAccountFlowDao.retrieveOrganizationAccountFlowById(id);
	}

	@Transactional
	public OrganizationAccountFlow addOrganizationAccountFlow(OrganizationAccountFlow organizationAccountFlow) {
		return organizationAccountFlowDao.createOrganizationAccountFlow(organizationAccountFlow);
	}

	@Transactional
	public OrganizationAccountFlow modifyOrganizationAccountFlow(OrganizationAccountFlow organizationAccountFlow) {
		return organizationAccountFlowDao.updateOrganizationAccountFlow(organizationAccountFlow);
	}

	@Transactional
	public void deleteOrganizationAccountFlow(Long id) {
		organizationAccountFlowDao.deleteOrganizationAccountFlowById(id);
	}
	
	@Transactional
	public void deleteOrganizationAccountFlows(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					organizationAccountFlowDao.deleteOrganizationAccountFlowById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<OrganizationAccountFlow> organizationAccountFlows(int page, int limit) {
		return organizationAccountFlowDao.pageOrganizationAccountFlow(page, limit);
	}
	
	public List<OrganizationAccountFlow> list() {
		return organizationAccountFlowDao.listOrganizationAccountFlow();
	}

}
