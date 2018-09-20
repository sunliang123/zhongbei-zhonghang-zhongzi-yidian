package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.OfflineStockOptionTradeDao;
import com.waben.stock.collector.entity.OfflineStockOptionTrade;

/**
 * 线下期权交易信息 Service
 * 
 * @author luomengan
 *
 */
@Service
public class OfflineStockOptionTradeService {

	@Autowired
	private OfflineStockOptionTradeDao offlineStockOptionTradeDao;

	public OfflineStockOptionTrade getOfflineStockOptionTradeInfo(Long id) {
		return offlineStockOptionTradeDao.retrieveOfflineStockOptionTradeById(id);
	}

	@Transactional
	public OfflineStockOptionTrade addOfflineStockOptionTrade(OfflineStockOptionTrade offlineStockOptionTrade) {
		return offlineStockOptionTradeDao.createOfflineStockOptionTrade(offlineStockOptionTrade);
	}

	@Transactional
	public OfflineStockOptionTrade modifyOfflineStockOptionTrade(OfflineStockOptionTrade offlineStockOptionTrade) {
		return offlineStockOptionTradeDao.updateOfflineStockOptionTrade(offlineStockOptionTrade);
	}

	@Transactional
	public void deleteOfflineStockOptionTrade(Long id) {
		offlineStockOptionTradeDao.deleteOfflineStockOptionTradeById(id);
	}
	
	@Transactional
	public void deleteOfflineStockOptionTrades(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					offlineStockOptionTradeDao.deleteOfflineStockOptionTradeById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<OfflineStockOptionTrade> offlineStockOptionTrades(int page, int limit) {
		return offlineStockOptionTradeDao.pageOfflineStockOptionTrade(page, limit);
	}
	
	public List<OfflineStockOptionTrade> list() {
		return offlineStockOptionTradeDao.listOfflineStockOptionTrade();
	}

}
