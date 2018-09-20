package com.waben.stock.datalayer.buyrecord.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.buyrecord.entity.Settlement;
import com.waben.stock.datalayer.buyrecord.repository.SettlementDao;
import com.waben.stock.datalayer.buyrecord.repository.impl.jpa.SettlementRepository;

/**
 * 结算 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class SettlementDaoImpl implements SettlementDao {

	@Autowired
	private SettlementRepository repository;

	@Override
	public Settlement create(Settlement t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Settlement update(Settlement t) {
		return repository.save(t);
	}

	@Override
	public Settlement retrieve(Long id) {
		return repository.findById(id);
	}



	@Override
	public Page<Settlement> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<Settlement> page(Specification<Settlement> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<Settlement> list() {
		return repository.findAll();
	}

	@Override
	public Settlement retrieveByBuyRecord(Long id) {
		return repository.findByBuyRecordId(id);
	}
}
