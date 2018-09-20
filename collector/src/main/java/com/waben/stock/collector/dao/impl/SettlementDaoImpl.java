package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.SettlementDao;
import com.waben.stock.collector.dao.impl.jpa.SettlementRepository;
import com.waben.stock.collector.entity.Settlement;

/**
 * 结算 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class SettlementDaoImpl implements SettlementDao {

	@Autowired
	private SettlementRepository settlementRepository;

	@Override
	public Settlement createSettlement(Settlement settlement) {
		return settlementRepository.save(settlement);
	}

	@Override
	public void deleteSettlementById(Long id) {
		settlementRepository.delete(id);
	}

	@Override
	public Settlement updateSettlement(Settlement settlement) {
		return settlementRepository.save(settlement);
	}

	@Override
	public Settlement retrieveSettlementById(Long id) {
		return settlementRepository.findById(id);
	}

	@Override
	public Page<Settlement> pageSettlement(int page, int limit) {
		return settlementRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<Settlement> listSettlement() {
		return settlementRepository.findAll();
	}

	@Override
	public Settlement getByDomainAndDataId(String domain, Long dataId) {
		return settlementRepository.findByDomainAndDataId(domain, dataId);
	}

}
