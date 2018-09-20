package com.waben.stock.datalayer.stockcontent.repository;

import java.util.List;

import com.waben.stock.datalayer.stockcontent.entity.Stock;

public interface StockDao extends BaseDao<Stock, Long> {

	Stock retrieveByCode(String code);

	Integer updateById(Boolean status, String name, String code, Long id);

	List<Stock> retrieveByExponentCode(String exponentCode);
}
