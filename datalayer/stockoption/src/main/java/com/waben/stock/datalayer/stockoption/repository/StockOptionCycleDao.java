package com.waben.stock.datalayer.stockoption.repository;

import com.waben.stock.datalayer.stockoption.entity.StockOptionCycle;

/**
 * 期权周期 Dao
 * 
 * @author luomengan
 *
 */
public interface StockOptionCycleDao extends BaseDao<StockOptionCycle, Long> {

	StockOptionCycle retrieveByCycle(Integer cycle);
}
