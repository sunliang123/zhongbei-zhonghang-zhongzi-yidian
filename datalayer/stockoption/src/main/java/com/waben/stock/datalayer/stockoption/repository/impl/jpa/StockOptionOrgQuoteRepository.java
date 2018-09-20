package com.waben.stock.datalayer.stockoption.repository.impl.jpa;

import java.util.List;

import com.waben.stock.datalayer.stockoption.entity.StockOptionOrg;
import com.waben.stock.datalayer.stockoption.entity.StockOptionOrgQuote;

/**
 * 期权第三方机构报价 Jpa
 * 
 * @author luomengan
 *
 */
public interface StockOptionOrgQuoteRepository extends CustomJpaRepository<StockOptionOrgQuote, Long> {

	List<StockOptionOrgQuote> findByOrgAndStockCodeAndCycle(StockOptionOrg org, String stockCode, Integer cycle);

	List<StockOptionOrgQuote> findByStockCodeAndCycle(String stockCode, Integer cycle);

}
