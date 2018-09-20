package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.Settlement;

/**
 * 结算 Dao
 * 
 * @author luomengan
 *
 */
public interface SettlementDao {

	public Settlement createSettlement(Settlement settlement);

	public void deleteSettlementById(Long id);

	public Settlement updateSettlement(Settlement settlement);

	public Settlement retrieveSettlementById(Long id);

	public Page<Settlement> pageSettlement(int page, int limit);
	
	public List<Settlement> listSettlement();
	
	public Settlement getByDomainAndDataId(String domain, Long dataId);

}
