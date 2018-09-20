package com.waben.stock.datalayer.stockoption.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.stockoption.entity.StockOptionAmountLimit;
import com.waben.stock.datalayer.stockoption.repository.StockOptionAmountLimitDao;
import com.waben.stock.datalayer.stockoption.repository.impl.jpa.StockOptionAmountLimitRepository;

@Repository
public class StockOptionAmountLimitDaoImpl implements StockOptionAmountLimitDao {

	@Autowired
	private StockOptionAmountLimitRepository repository;

	@Override
	public StockOptionAmountLimit create(StockOptionAmountLimit t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public StockOptionAmountLimit update(StockOptionAmountLimit t) {
		return repository.save(t);
	}

	@Override
	public StockOptionAmountLimit retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<StockOptionAmountLimit> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<StockOptionAmountLimit> page(Specification<StockOptionAmountLimit> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<StockOptionAmountLimit> list() {
		return repository.findAll();
	}

	@Override
	public StockOptionAmountLimit retrieveGlobal() {
		return repository.findByIsGlobal(true);
	}

	@Override
	public StockOptionAmountLimit retrieveByStockCode(String stockCode) {
		return repository.findByIsGlobalAndStockCode(false, stockCode);
	}

}
