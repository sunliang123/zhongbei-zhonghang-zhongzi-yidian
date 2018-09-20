package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.PaymentOrderDao;
import com.waben.stock.collector.dao.impl.jpa.PaymentOrderRepository;
import com.waben.stock.collector.entity.PaymentOrder;

/**
 * 支付订单 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class PaymentOrderDaoImpl implements PaymentOrderDao {

	@Autowired
	private PaymentOrderRepository paymentOrderRepository;

	@Override
	public PaymentOrder createPaymentOrder(PaymentOrder paymentOrder) {
		return paymentOrderRepository.save(paymentOrder);
	}

	@Override
	public void deletePaymentOrderById(Long id) {
		paymentOrderRepository.delete(id);
	}

	@Override
	public PaymentOrder updatePaymentOrder(PaymentOrder paymentOrder) {
		return paymentOrderRepository.save(paymentOrder);
	}

	@Override
	public PaymentOrder retrievePaymentOrderById(Long id) {
		return paymentOrderRepository.findById(id);
	}

	@Override
	public Page<PaymentOrder> pagePaymentOrder(int page, int limit) {
		return paymentOrderRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<PaymentOrder> listPaymentOrder() {
		return paymentOrderRepository.findAll();
	}

	@Override
	public PaymentOrder getByDomainAndDataId(String domain, Long dataId) {
		return paymentOrderRepository.findByDomainAndDataId(domain, dataId);
	}

}
