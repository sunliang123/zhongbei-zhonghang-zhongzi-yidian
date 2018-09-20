package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.CapitalAccount;

/**
 * 资金账户 Dao
 * 
 * @author luomengan
 *
 */
public interface CapitalAccountDao {

	public CapitalAccount createCapitalAccount(CapitalAccount capitalAccount);

	public void deleteCapitalAccountById(Long id);

	public CapitalAccount updateCapitalAccount(CapitalAccount capitalAccount);

	public CapitalAccount retrieveCapitalAccountById(Long id);

	public Page<CapitalAccount> pageCapitalAccount(int page, int limit);
	
	public List<CapitalAccount> listCapitalAccount();
	
	public CapitalAccount getByDomainAndDataId(String domain, Long dataId);

}
