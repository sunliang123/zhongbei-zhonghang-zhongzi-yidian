package com.waben.stock.datalayer.publisher.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.publisher.entity.FrozenCapital;
import com.waben.stock.datalayer.publisher.repository.FrozenCapitalDao;
import com.waben.stock.datalayer.publisher.repository.impl.jpa.FrozenCapitalRepository;

/**
 * 冻结资金 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class FrozenCapitalDaoImpl implements FrozenCapitalDao {

	@Autowired
	private FrozenCapitalRepository repository;

	@Override
	public FrozenCapital create(FrozenCapital t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public FrozenCapital update(FrozenCapital t) {
		return repository.save(t);
	}

	@Override
	public FrozenCapital retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<FrozenCapital> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<FrozenCapital> list() {
		return repository.findAll();
	}

	@Override
	public Page<FrozenCapital> page(Specification<FrozenCapital> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public FrozenCapital retriveByPublisherIdAndBuyRecordId(Long publisherId, Long buyRecordId) {
		return repository.findByPublisherIdAndBuyRecordId(publisherId, buyRecordId);
	}

	@Override
	public FrozenCapital retriveByPublisherIdAndWithdrawalsNo(Long publisherId, String withdrawalsNo) {
		return repository.findByPublisherIdAndWithdrawalsNo(publisherId, withdrawalsNo);
	}

}
