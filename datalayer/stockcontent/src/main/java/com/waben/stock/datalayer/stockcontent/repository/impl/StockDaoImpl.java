package com.waben.stock.datalayer.stockcontent.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.stockcontent.entity.Stock;
import com.waben.stock.datalayer.stockcontent.repository.StockDao;
import com.waben.stock.datalayer.stockcontent.repository.impl.jpa.StockRepository;

@Repository
public class StockDaoImpl implements StockDao {

	@Autowired
	private StockRepository repository;

	@Override
	public Stock create(Stock t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Stock update(Stock t) {
		return repository.save(t);
	}

	@Override
	public Stock retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<Stock> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<Stock> page(Specification<Stock> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<Stock> list() {
		return repository.findAll();
	}

	@Override
	public Stock retrieveByCode(String code) {
		return repository.findByCode(code);
	}

	@Override
	public Integer updateById(Boolean status, String name, String code, Long id) {
		return repository.revisionById(status, name, code, id);
	}

	@Override
	public List<Stock> retrieveByExponentCode(String exponentCode) {
		return repository.findByExponentCode(exponentCode);
	}

}
