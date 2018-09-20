package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.CapitalFlowDao;
import com.waben.stock.collector.entity.CapitalFlow;

/**
 * 资金流水 Service
 * 
 * @author luomengan
 *
 */
@Service
public class CapitalFlowService {

	@Autowired
	private CapitalFlowDao capitalFlowDao;

	public CapitalFlow getCapitalFlowInfo(Long id) {
		return capitalFlowDao.retrieveCapitalFlowById(id);
	}

	@Transactional
	public CapitalFlow addCapitalFlow(CapitalFlow capitalFlow) {
		return capitalFlowDao.createCapitalFlow(capitalFlow);
	}

	@Transactional
	public CapitalFlow modifyCapitalFlow(CapitalFlow capitalFlow) {
		return capitalFlowDao.updateCapitalFlow(capitalFlow);
	}

	@Transactional
	public void deleteCapitalFlow(Long id) {
		capitalFlowDao.deleteCapitalFlowById(id);
	}
	
	@Transactional
	public void deleteCapitalFlows(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					capitalFlowDao.deleteCapitalFlowById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<CapitalFlow> capitalFlows(int page, int limit) {
		return capitalFlowDao.pageCapitalFlow(page, limit);
	}
	
	public List<CapitalFlow> list() {
		return capitalFlowDao.listCapitalFlow();
	}

}
