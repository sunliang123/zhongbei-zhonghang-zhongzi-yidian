package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.SettlementDao;
import com.waben.stock.collector.entity.Settlement;

/**
 * 结算 Service
 * 
 * @author luomengan
 *
 */
@Service
public class SettlementService {

	@Autowired
	private SettlementDao settlementDao;

	public Settlement getSettlementInfo(Long id) {
		return settlementDao.retrieveSettlementById(id);
	}

	@Transactional
	public Settlement addSettlement(Settlement settlement) {
		return settlementDao.createSettlement(settlement);
	}

	@Transactional
	public Settlement modifySettlement(Settlement settlement) {
		return settlementDao.updateSettlement(settlement);
	}

	@Transactional
	public void deleteSettlement(Long id) {
		settlementDao.deleteSettlementById(id);
	}
	
	@Transactional
	public void deleteSettlements(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					settlementDao.deleteSettlementById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<Settlement> settlements(int page, int limit) {
		return settlementDao.pageSettlement(page, limit);
	}
	
	public List<Settlement> list() {
		return settlementDao.listSettlement();
	}

}
