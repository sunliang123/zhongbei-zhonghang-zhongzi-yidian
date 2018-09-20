package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.OrganizationAccountFlow;

/**
 * 机构账户流水 Repository
 * 
 * @author luomengan
 *
 */
public interface OrganizationAccountFlowRepository extends Repository<OrganizationAccountFlow, Long> {

	OrganizationAccountFlow save(OrganizationAccountFlow organizationAccountFlow);

	void delete(Long id);

	Page<OrganizationAccountFlow> findAll(Pageable pageable);
	
	List<OrganizationAccountFlow> findAll();

	OrganizationAccountFlow findById(Long id);

	OrganizationAccountFlow findByDomainAndDataId(String domain, Long dataId);
	
}
