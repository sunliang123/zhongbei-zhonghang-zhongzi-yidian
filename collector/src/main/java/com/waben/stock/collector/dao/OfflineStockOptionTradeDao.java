package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.OfflineStockOptionTrade;

/**
 * 线下期权交易信息 Dao
 * 
 * @author luomengan
 *
 */
public interface OfflineStockOptionTradeDao {

	public OfflineStockOptionTrade createOfflineStockOptionTrade(OfflineStockOptionTrade offlineStockOptionTrade);

	public void deleteOfflineStockOptionTradeById(Long id);

	public OfflineStockOptionTrade updateOfflineStockOptionTrade(OfflineStockOptionTrade offlineStockOptionTrade);

	public OfflineStockOptionTrade retrieveOfflineStockOptionTradeById(Long id);

	public Page<OfflineStockOptionTrade> pageOfflineStockOptionTrade(int page, int limit);
	
	public List<OfflineStockOptionTrade> listOfflineStockOptionTrade();
	
	public OfflineStockOptionTrade getByDomainAndDataId(String domain, Long dataId);

}
