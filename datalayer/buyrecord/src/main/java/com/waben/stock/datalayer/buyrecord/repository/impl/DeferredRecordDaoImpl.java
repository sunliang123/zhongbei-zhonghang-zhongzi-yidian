package com.waben.stock.datalayer.buyrecord.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.buyrecord.entity.DeferredRecord;
import com.waben.stock.datalayer.buyrecord.repository.DeferredRecordDao;
import com.waben.stock.datalayer.buyrecord.repository.impl.jpa.DeferredRecordRepository;

/**
 * 递延记录 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class DeferredRecordDaoImpl implements DeferredRecordDao {

	@Autowired
	private DeferredRecordRepository repository;

	@Override
	public DeferredRecord create(DeferredRecord t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public DeferredRecord update(DeferredRecord t) {
		return repository.save(t);
	}

	@Override
	public DeferredRecord retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<DeferredRecord> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<DeferredRecord> page(Specification<DeferredRecord> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<DeferredRecord> list() {
		return repository.findAll();
	}

	@Override
	public List<DeferredRecord> retrieveByPublisherIdAndBuyRecordId(Long publisherId, Long buyRecordId) {
		return repository.findByPublisherIdAndBuyRecordId(publisherId, buyRecordId);
	}

}
