package com.waben.stock.datalayer.stockoption.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.stockoption.entity.StockOptionTrade;
import com.waben.stock.datalayer.stockoption.repository.StockOptionTradeDao;
import com.waben.stock.datalayer.stockoption.repository.impl.jpa.StockOptionTradeRepository;
import com.waben.stock.interfaces.enums.StockOptionTradeState;

@Repository
public class StockOptionTradeDaoImpl implements StockOptionTradeDao {
	@Autowired
	private StockOptionTradeRepository stockOptionTradeRepository;

	@Override
	public StockOptionTrade create(StockOptionTrade stockOptionTrade) {
		return stockOptionTradeRepository.save(stockOptionTrade);
	}

	@Override
	public void delete(Long id) {
		stockOptionTradeRepository.delete(id);
	}

	@Override
	public StockOptionTrade update(StockOptionTrade stockOptionTrade) {
		return stockOptionTradeRepository.save(stockOptionTrade);
	}

	@Override
	public StockOptionTrade retrieve(Long id) {
		return stockOptionTradeRepository.findById(id);
	}

	@Override
	public Page<StockOptionTrade> page(int page, int limit) {
		return null;
	}

	@Override
	public Page<StockOptionTrade> page(Specification<StockOptionTrade> specification, Pageable pageable) {
		return stockOptionTradeRepository.findAll(specification, pageable);
	}

	@Override
	public List<StockOptionTrade> list() {
		return stockOptionTradeRepository.findAll();
	}

	@Override
	public List<StockOptionTrade> retieveByState(StockOptionTradeState stockOptionTradeState) {
		return stockOptionTradeRepository.findByState(stockOptionTradeState);
	}

	@Override
	public Integer countStockOptionTradeState(Long publisherId) {
		return stockOptionTradeRepository.countStockOptionTradeState(publisherId);
	}
}
