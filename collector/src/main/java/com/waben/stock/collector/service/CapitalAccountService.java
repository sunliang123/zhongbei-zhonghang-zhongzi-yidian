package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.CapitalAccountDao;
import com.waben.stock.collector.entity.CapitalAccount;

/**
 * 资金账户 Service
 * 
 * @author luomengan
 *
 */
@Service
public class CapitalAccountService {

	@Autowired
	private CapitalAccountDao capitalAccountDao;

	public CapitalAccount getCapitalAccountInfo(Long id) {
		return capitalAccountDao.retrieveCapitalAccountById(id);
	}

	@Transactional
	public CapitalAccount addCapitalAccount(CapitalAccount capitalAccount) {
		return capitalAccountDao.createCapitalAccount(capitalAccount);
	}

	@Transactional
	public CapitalAccount modifyCapitalAccount(CapitalAccount capitalAccount) {
		return capitalAccountDao.updateCapitalAccount(capitalAccount);
	}

	@Transactional
	public void deleteCapitalAccount(Long id) {
		capitalAccountDao.deleteCapitalAccountById(id);
	}
	
	@Transactional
	public void deleteCapitalAccounts(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					capitalAccountDao.deleteCapitalAccountById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<CapitalAccount> capitalAccounts(int page, int limit) {
		return capitalAccountDao.pageCapitalAccount(page, limit);
	}
	
	public List<CapitalAccount> list() {
		return capitalAccountDao.listCapitalAccount();
	}

}
