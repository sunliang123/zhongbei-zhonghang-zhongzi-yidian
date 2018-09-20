package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.FrozenCapitalDao;
import com.waben.stock.collector.dao.impl.jpa.FrozenCapitalRepository;
import com.waben.stock.collector.entity.FrozenCapital;

/**
 * 冻结资金 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class FrozenCapitalDaoImpl implements FrozenCapitalDao {

	@Autowired
	private FrozenCapitalRepository frozenCapitalRepository;

	@Override
	public FrozenCapital createFrozenCapital(FrozenCapital frozenCapital) {
		return frozenCapitalRepository.save(frozenCapital);
	}

	@Override
	public void deleteFrozenCapitalById(Long id) {
		frozenCapitalRepository.delete(id);
	}

	@Override
	public FrozenCapital updateFrozenCapital(FrozenCapital frozenCapital) {
		return frozenCapitalRepository.save(frozenCapital);
	}

	@Override
	public FrozenCapital retrieveFrozenCapitalById(Long id) {
		return frozenCapitalRepository.findById(id);
	}

	@Override
	public Page<FrozenCapital> pageFrozenCapital(int page, int limit) {
		return frozenCapitalRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<FrozenCapital> listFrozenCapital() {
		return frozenCapitalRepository.findAll();
	}

	@Override
	public FrozenCapital getByDomainAndDataId(String domain, Long dataId) {
		return frozenCapitalRepository.findByDomainAndDataId(domain, dataId);
	}

}
