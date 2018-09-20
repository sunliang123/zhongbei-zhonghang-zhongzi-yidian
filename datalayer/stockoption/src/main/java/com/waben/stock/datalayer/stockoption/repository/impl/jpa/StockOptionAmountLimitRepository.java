package com.waben.stock.datalayer.stockoption.repository.impl.jpa;

import com.waben.stock.datalayer.stockoption.entity.StockOptionAmountLimit;

public interface StockOptionAmountLimitRepository extends CustomJpaRepository<StockOptionAmountLimit, Long> {

	StockOptionAmountLimit findByIsGlobal(Boolean isGlobal);

	StockOptionAmountLimit findByIsGlobalAndStockCode(Boolean isGlobal, String stockCode);

}
