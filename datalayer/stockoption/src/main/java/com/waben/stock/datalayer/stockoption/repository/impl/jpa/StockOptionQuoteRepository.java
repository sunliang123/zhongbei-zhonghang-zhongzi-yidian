package com.waben.stock.datalayer.stockoption.repository.impl.jpa;

import java.util.List;

import com.waben.stock.datalayer.stockoption.entity.StockOptionQuote;

/**
 * 期权报价 Jpa
 * 
 * @author luomengan
 *
 */
public interface StockOptionQuoteRepository extends CustomJpaRepository<StockOptionQuote, Long> {

	List<StockOptionQuote> findByStockCodeAndCycle(String stockCode, Integer cycle);

}
