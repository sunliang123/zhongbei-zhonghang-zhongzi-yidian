package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.OfflineStockOptionTrade;

/**
 * 线下期权交易信息 Repository
 * 
 * @author luomengan
 *
 */
public interface OfflineStockOptionTradeRepository extends Repository<OfflineStockOptionTrade, Long> {

	OfflineStockOptionTrade save(OfflineStockOptionTrade offlineStockOptionTrade);

	void delete(Long id);

	Page<OfflineStockOptionTrade> findAll(Pageable pageable);
	
	List<OfflineStockOptionTrade> findAll();

	OfflineStockOptionTrade findById(Long id);

	OfflineStockOptionTrade findByDomainAndDataId(String domain, Long dataId);
	
}
