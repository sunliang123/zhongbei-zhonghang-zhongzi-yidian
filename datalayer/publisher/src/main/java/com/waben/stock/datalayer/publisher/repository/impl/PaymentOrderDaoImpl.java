package com.waben.stock.datalayer.publisher.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.publisher.entity.PaymentOrder;
import com.waben.stock.datalayer.publisher.repository.PaymentOrderDao;
import com.waben.stock.datalayer.publisher.repository.impl.jpa.PaymentOrderRepository;

/**
 * 支付订单 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class PaymentOrderDaoImpl implements PaymentOrderDao {

	@Autowired
	private PaymentOrderRepository repository;

	@Override
	public PaymentOrder create(PaymentOrder t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public PaymentOrder update(PaymentOrder t) {
		return repository.save(t);
	}

	@Override
	public PaymentOrder retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<PaymentOrder> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<PaymentOrder> page(Specification<PaymentOrder> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<PaymentOrder> list() {
		return repository.findAll();
	}

	@Override
	public PaymentOrder retrieveByPaymentNo(String paymentNo) {
		return repository.findByPaymentNo(paymentNo);
	}

}
