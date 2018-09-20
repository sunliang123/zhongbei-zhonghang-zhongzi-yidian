package com.waben.stock.datalayer.stockoption.repository;

import java.util.List;

import com.waben.stock.datalayer.stockoption.entity.StockOptionOrg;
import com.waben.stock.datalayer.stockoption.entity.StockOptionOrgQuote;

/**
 * 期权第三方机构报价 Dao
 * 
 * @author luomengan
 *
 */
public interface StockOptionOrgQuoteDao extends BaseDao<StockOptionOrgQuote, Long> {

	StockOptionOrgQuote findByOrgAndStockCodeAndCycle(StockOptionOrg org, String stockCode, Integer cycle);

	List<StockOptionOrgQuote> findByStockCodeAndCycle(String stockCode, Integer cycle);

}
