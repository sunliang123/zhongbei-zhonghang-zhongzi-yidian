package com.waben.stock.datalayer.stockoption.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.stockoption.entity.StockOptionQuote;
import com.waben.stock.datalayer.stockoption.repository.StockOptionQuoteDao;
import com.waben.stock.datalayer.stockoption.repository.impl.jpa.StockOptionQuoteRepository;

/**
 * 期权报价 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class StockOptionQuoteDaoImpl implements StockOptionQuoteDao {

	@Autowired
	private StockOptionQuoteRepository repository;

	@Override
	public StockOptionQuote create(StockOptionQuote t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public StockOptionQuote update(StockOptionQuote t) {
		return repository.save(t);
	}

	@Override
	public StockOptionQuote retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<StockOptionQuote> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<StockOptionQuote> page(Specification<StockOptionQuote> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<StockOptionQuote> list() {
		return repository.findAll();
	}

	@Override
	public StockOptionQuote retrieveByStockCodeAndCycle(String stockCode, Integer cycle) {
		List<StockOptionQuote> quoteList = repository.findByStockCodeAndCycle(stockCode, cycle);
		if (quoteList != null && quoteList.size() > 0) {
			return quoteList.get(0);
		}
		return null;
	}

}
