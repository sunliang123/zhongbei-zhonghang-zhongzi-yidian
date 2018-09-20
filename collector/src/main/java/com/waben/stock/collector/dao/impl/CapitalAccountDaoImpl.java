package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.CapitalAccountDao;
import com.waben.stock.collector.dao.impl.jpa.CapitalAccountRepository;
import com.waben.stock.collector.entity.CapitalAccount;

/**
 * 资金账户 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class CapitalAccountDaoImpl implements CapitalAccountDao {

	@Autowired
	private CapitalAccountRepository capitalAccountRepository;

	@Override
	public CapitalAccount createCapitalAccount(CapitalAccount capitalAccount) {
		return capitalAccountRepository.save(capitalAccount);
	}

	@Override
	public void deleteCapitalAccountById(Long id) {
		capitalAccountRepository.delete(id);
	}

	@Override
	public CapitalAccount updateCapitalAccount(CapitalAccount capitalAccount) {
		return capitalAccountRepository.save(capitalAccount);
	}

	@Override
	public CapitalAccount retrieveCapitalAccountById(Long id) {
		return capitalAccountRepository.findById(id);
	}

	@Override
	public Page<CapitalAccount> pageCapitalAccount(int page, int limit) {
		return capitalAccountRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<CapitalAccount> listCapitalAccount() {
		return capitalAccountRepository.findAll();
	}

	@Override
	public CapitalAccount getByDomainAndDataId(String domain, Long dataId) {
		return capitalAccountRepository.findByDomainAndDataId(domain, dataId);
	}

}
