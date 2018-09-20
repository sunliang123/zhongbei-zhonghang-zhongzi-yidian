package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.FrozenCapital;

/**
 * 冻结资金 Dao
 * 
 * @author luomengan
 *
 */
public interface FrozenCapitalDao {

	public FrozenCapital createFrozenCapital(FrozenCapital frozenCapital);

	public void deleteFrozenCapitalById(Long id);

	public FrozenCapital updateFrozenCapital(FrozenCapital frozenCapital);

	public FrozenCapital retrieveFrozenCapitalById(Long id);

	public Page<FrozenCapital> pageFrozenCapital(int page, int limit);
	
	public List<FrozenCapital> listFrozenCapital();
	
	public FrozenCapital getByDomainAndDataId(String domain, Long dataId);

}
