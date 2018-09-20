package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.OfflineStockOptionTradeDao;
import com.waben.stock.collector.dao.impl.jpa.OfflineStockOptionTradeRepository;
import com.waben.stock.collector.entity.OfflineStockOptionTrade;

/**
 * 线下期权交易信息 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class OfflineStockOptionTradeDaoImpl implements OfflineStockOptionTradeDao {

	@Autowired
	private OfflineStockOptionTradeRepository offlineStockOptionTradeRepository;

	@Override
	public OfflineStockOptionTrade createOfflineStockOptionTrade(OfflineStockOptionTrade offlineStockOptionTrade) {
		return offlineStockOptionTradeRepository.save(offlineStockOptionTrade);
	}

	@Override
	public void deleteOfflineStockOptionTradeById(Long id) {
		offlineStockOptionTradeRepository.delete(id);
	}

	@Override
	public OfflineStockOptionTrade updateOfflineStockOptionTrade(OfflineStockOptionTrade offlineStockOptionTrade) {
		return offlineStockOptionTradeRepository.save(offlineStockOptionTrade);
	}

	@Override
	public OfflineStockOptionTrade retrieveOfflineStockOptionTradeById(Long id) {
		return offlineStockOptionTradeRepository.findById(id);
	}

	@Override
	public Page<OfflineStockOptionTrade> pageOfflineStockOptionTrade(int page, int limit) {
		return offlineStockOptionTradeRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<OfflineStockOptionTrade> listOfflineStockOptionTrade() {
		return offlineStockOptionTradeRepository.findAll();
	}

	@Override
	public OfflineStockOptionTrade getByDomainAndDataId(String domain, Long dataId) {
		return offlineStockOptionTradeRepository.findByDomainAndDataId(domain, dataId);
	}

}
