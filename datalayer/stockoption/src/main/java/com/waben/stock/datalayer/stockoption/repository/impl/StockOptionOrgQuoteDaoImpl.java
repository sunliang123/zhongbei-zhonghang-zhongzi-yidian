package com.waben.stock.datalayer.stockoption.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.stockoption.entity.StockOptionOrg;
import com.waben.stock.datalayer.stockoption.entity.StockOptionOrgQuote;
import com.waben.stock.datalayer.stockoption.repository.StockOptionOrgQuoteDao;
import com.waben.stock.datalayer.stockoption.repository.impl.jpa.StockOptionOrgQuoteRepository;

/**
 * 期权第三方机构报价 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class StockOptionOrgQuoteDaoImpl implements StockOptionOrgQuoteDao {

	@Autowired
	private StockOptionOrgQuoteRepository repository;

	@Override
	public StockOptionOrgQuote create(StockOptionOrgQuote t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public StockOptionOrgQuote update(StockOptionOrgQuote t) {
		return repository.save(t);
	}

	@Override
	public StockOptionOrgQuote retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<StockOptionOrgQuote> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<StockOptionOrgQuote> page(Specification<StockOptionOrgQuote> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<StockOptionOrgQuote> list() {
		return repository.findAll();
	}

	@Override
	public StockOptionOrgQuote findByOrgAndStockCodeAndCycle(StockOptionOrg org, String stockCode, Integer cycle) {
		List<StockOptionOrgQuote> list = repository.findByOrgAndStockCodeAndCycle(org, stockCode, cycle);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<StockOptionOrgQuote> findByStockCodeAndCycle(String stockCode, Integer cycle) {
		return repository.findByStockCodeAndCycle(stockCode, cycle);
	}

}
