package com.waben.stock.datalayer.publisher.repository;

import java.util.List;

import com.waben.stock.datalayer.publisher.entity.FavoriteStock;

/**
 * 收藏股票 Dao
 * 
 * @author luomengan
 *
 */
public interface FavoriteStockDao extends BaseDao<FavoriteStock, Long> {

	FavoriteStock retrive(Long publisherId, String stockCode);

	Integer retriveMaxSort(Long publisherId);

	List<FavoriteStock> list(Long publisherId);

	List<FavoriteStock> listByCodeNotIn(Long publisherId, String[] stockCodes);

	void delete(Long publisherId, String[] stockCodeArr);

	List<String> listStockCode(Long publisherId);

}
