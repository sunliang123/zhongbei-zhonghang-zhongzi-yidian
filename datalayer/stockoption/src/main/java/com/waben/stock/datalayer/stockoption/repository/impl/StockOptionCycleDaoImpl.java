package com.waben.stock.datalayer.stockoption.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.stockoption.entity.StockOptionCycle;
import com.waben.stock.datalayer.stockoption.repository.StockOptionCycleDao;
import com.waben.stock.datalayer.stockoption.repository.impl.jpa.StockOptionCycleRepository;

/**
 * 期权周期 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class StockOptionCycleDaoImpl implements StockOptionCycleDao {

	@Autowired
	private StockOptionCycleRepository repository;

	@Override
	public StockOptionCycle create(StockOptionCycle t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public StockOptionCycle update(StockOptionCycle t) {
		return repository.save(t);
	}

	@Override
	public StockOptionCycle retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<StockOptionCycle> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<StockOptionCycle> page(Specification<StockOptionCycle> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<StockOptionCycle> list() {
		return repository.findAll();
	}

	@Override
	public StockOptionCycle retrieveByCycle(Integer cycle) {
		List<StockOptionCycle> cycleList = repository.findByCycle(cycle);
		if (cycleList != null && cycleList.size() > 0) {
			return cycleList.get(0);
		}
		return null;
	}

}
