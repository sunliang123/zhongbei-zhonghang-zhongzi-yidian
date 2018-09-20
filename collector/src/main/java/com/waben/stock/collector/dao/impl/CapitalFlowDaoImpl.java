package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.CapitalFlowDao;
import com.waben.stock.collector.dao.impl.jpa.CapitalFlowRepository;
import com.waben.stock.collector.entity.CapitalFlow;

/**
 * 资金流水 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class CapitalFlowDaoImpl implements CapitalFlowDao {

	@Autowired
	private CapitalFlowRepository capitalFlowRepository;

	@Override
	public CapitalFlow createCapitalFlow(CapitalFlow capitalFlow) {
		return capitalFlowRepository.save(capitalFlow);
	}

	@Override
	public void deleteCapitalFlowById(Long id) {
		capitalFlowRepository.delete(id);
	}

	@Override
	public CapitalFlow updateCapitalFlow(CapitalFlow capitalFlow) {
		return capitalFlowRepository.save(capitalFlow);
	}

	@Override
	public CapitalFlow retrieveCapitalFlowById(Long id) {
		return capitalFlowRepository.findById(id);
	}

	@Override
	public Page<CapitalFlow> pageCapitalFlow(int page, int limit) {
		return capitalFlowRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<CapitalFlow> listCapitalFlow() {
		return capitalFlowRepository.findAll();
	}

	@Override
	public CapitalFlow getByDomainAndDataId(String domain, Long dataId) {
		return capitalFlowRepository.findByDomainAndDataId(domain, dataId);
	}

}
