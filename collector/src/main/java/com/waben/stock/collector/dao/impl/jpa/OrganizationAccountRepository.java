package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.OrganizationAccount;

/**
 * 机构账户 Repository
 * 
 * @author luomengan
 *
 */
public interface OrganizationAccountRepository extends Repository<OrganizationAccount, Long> {

	OrganizationAccount save(OrganizationAccount organizationAccount);

	void delete(Long id);

	Page<OrganizationAccount> findAll(Pageable pageable);
	
	List<OrganizationAccount> findAll();

	OrganizationAccount findById(Long id);

	OrganizationAccount findByDomainAndDataId(String domain, Long dataId);
	
}
