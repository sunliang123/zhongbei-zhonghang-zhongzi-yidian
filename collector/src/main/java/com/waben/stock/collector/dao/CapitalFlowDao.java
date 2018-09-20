package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.CapitalFlow;

/**
 * 资金流水 Dao
 * 
 * @author luomengan
 *
 */
public interface CapitalFlowDao {

	public CapitalFlow createCapitalFlow(CapitalFlow capitalFlow);

	public void deleteCapitalFlowById(Long id);

	public CapitalFlow updateCapitalFlow(CapitalFlow capitalFlow);

	public CapitalFlow retrieveCapitalFlowById(Long id);

	public Page<CapitalFlow> pageCapitalFlow(int page, int limit);
	
	public List<CapitalFlow> listCapitalFlow();
	
	public CapitalFlow getByDomainAndDataId(String domain, Long dataId);

}
