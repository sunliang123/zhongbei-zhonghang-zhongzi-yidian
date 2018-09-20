package com.waben.stock.datalayer.buyrecord.repository.impl;

import java.util.Date;
import java.util.List;

import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.buyrecord.entity.BuyRecord;
import com.waben.stock.datalayer.buyrecord.repository.BuyRecordDao;
import com.waben.stock.datalayer.buyrecord.repository.impl.jpa.BuyRecordRepository;

/**
 * 点买记录 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BuyRecordDaoImpl implements BuyRecordDao {

	@Autowired
	private BuyRecordRepository repository;

	@Override
	public BuyRecord create(BuyRecord t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public BuyRecord update(BuyRecord t) {
		return repository.save(t);
	}

	@Override
	public BuyRecord retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<BuyRecord> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<BuyRecord> page(Specification<BuyRecord> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<BuyRecord> list() {
		return repository.findAll();
	}

	@Override
	public List<BuyRecord> retieveByStateAndOrderByCreateTime(BuyRecordState state) {
		return repository.findAllByStateAndOrderByCreateTime(state);
	}

	@Override
	public Integer strategyJoinCount(Long publisherId, Long strategyTypeId) {
		return repository.strategyJoinCount(publisherId, strategyTypeId);
	}

	@Override
	public List<BuyRecord> retrieveByStateAndUpdateTimeBetween(BuyRecordState state, Date start, Date end) {
		return repository.findByStateAndUpdateTimeBetween(state,start,end);
	}
}
