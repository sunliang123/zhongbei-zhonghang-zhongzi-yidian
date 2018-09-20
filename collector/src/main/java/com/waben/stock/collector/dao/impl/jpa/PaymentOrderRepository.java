package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.PaymentOrder;

/**
 * 支付订单 Repository
 * 
 * @author luomengan
 *
 */
public interface PaymentOrderRepository extends Repository<PaymentOrder, Long> {

	PaymentOrder save(PaymentOrder paymentOrder);

	void delete(Long id);

	Page<PaymentOrder> findAll(Pageable pageable);
	
	List<PaymentOrder> findAll();

	PaymentOrder findById(Long id);

	PaymentOrder findByDomainAndDataId(String domain, Long dataId);
	
}
