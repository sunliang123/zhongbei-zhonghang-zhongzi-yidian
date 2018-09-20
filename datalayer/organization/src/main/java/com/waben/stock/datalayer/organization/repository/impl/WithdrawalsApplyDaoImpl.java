package com.waben.stock.datalayer.organization.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.organization.entity.WithdrawalsApply;
import com.waben.stock.datalayer.organization.repository.WithdrawalsApplyDao;
import com.waben.stock.datalayer.organization.repository.impl.jpa.WithdrawalsApplyRepository;

/**
 * 提现申请 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class WithdrawalsApplyDaoImpl implements WithdrawalsApplyDao {

	@Autowired
	private WithdrawalsApplyRepository repository;

	@Override
	public WithdrawalsApply create(WithdrawalsApply t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public WithdrawalsApply update(WithdrawalsApply t) {
		return repository.save(t);
	}

	@Override
	public WithdrawalsApply retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<WithdrawalsApply> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<WithdrawalsApply> page(Specification<WithdrawalsApply> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<WithdrawalsApply> list() {
		return repository.findAll();
	}

	@Override
	public WithdrawalsApply retrieveByApplyNo(String applyNo) {
		return repository.findByApplyNo(applyNo);
	}

}
