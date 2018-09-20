package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.CapitalAccount;

/**
 * 资金账户 Repository
 * 
 * @author luomengan
 *
 */
public interface CapitalAccountRepository extends Repository<CapitalAccount, Long> {

	CapitalAccount save(CapitalAccount capitalAccount);

	void delete(Long id);

	Page<CapitalAccount> findAll(Pageable pageable);
	
	List<CapitalAccount> findAll();

	CapitalAccount findById(Long id);

	CapitalAccount findByDomainAndDataId(String domain, Long dataId);
	
}
