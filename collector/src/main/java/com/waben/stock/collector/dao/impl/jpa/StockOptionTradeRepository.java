package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.StockOptionTrade;

/**
 * 用户股票期权交易信息 Repository
 * 
 * @author luomengan
 *
 */
public interface StockOptionTradeRepository extends Repository<StockOptionTrade, Long> {

	StockOptionTrade save(StockOptionTrade stockOptionTrade);

	void delete(Long id);

	Page<StockOptionTrade> findAll(Pageable pageable);
	
	List<StockOptionTrade> findAll();

	StockOptionTrade findById(Long id);

	StockOptionTrade findByDomainAndDataId(String domain, Long dataId);
	
}
