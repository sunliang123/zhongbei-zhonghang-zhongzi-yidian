package com.waben.stock.datalayer.stockoption.repository;

import com.waben.stock.datalayer.stockoption.entity.StockOptionAmountLimit;

/**
 * 期权交易限额 Dao
 * 
 * @author luomengan
 *
 */
public interface StockOptionAmountLimitDao extends BaseDao<StockOptionAmountLimit, Long> {

	StockOptionAmountLimit retrieveGlobal();

	StockOptionAmountLimit retrieveByStockCode(String stockCode);

}
