package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.Settlement;

/**
 * 结算 Repository
 * 
 * @author luomengan
 *
 */
public interface SettlementRepository extends Repository<Settlement, Long> {

	Settlement save(Settlement settlement);

	void delete(Long id);

	Page<Settlement> findAll(Pageable pageable);
	
	List<Settlement> findAll();

	Settlement findById(Long id);

	Settlement findByDomainAndDataId(String domain, Long dataId);
	
}
