package com.waben.stock.datalayer.publisher.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.publisher.entity.WithdrawalsOrder;
import com.waben.stock.datalayer.publisher.repository.WithdrawalsOrderDao;
import com.waben.stock.datalayer.publisher.repository.impl.jpa.WithdrawalsOrderRepository;

/**
 * 提现订单 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class WithdrawalsOrderDaoImpl implements WithdrawalsOrderDao {
	
	@Autowired
	private WithdrawalsOrderRepository repository;

	@Override
	public WithdrawalsOrder create(WithdrawalsOrder t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public WithdrawalsOrder update(WithdrawalsOrder t) {
		return repository.save(t);
	}

	@Override
	public WithdrawalsOrder retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<WithdrawalsOrder> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<WithdrawalsOrder> page(Specification<WithdrawalsOrder> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<WithdrawalsOrder> list() {
		return repository.findAll();
	}

	@Override
	public WithdrawalsOrder retrieveByWithdrawalsNo(String withdrawalsNo) {
		return repository.findByWithdrawalsNo(withdrawalsNo);
	}


}
