package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.PaymentOrder;

/**
 * 支付订单 Dao
 * 
 * @author luomengan
 *
 */
public interface PaymentOrderDao {

	public PaymentOrder createPaymentOrder(PaymentOrder paymentOrder);

	public void deletePaymentOrderById(Long id);

	public PaymentOrder updatePaymentOrder(PaymentOrder paymentOrder);

	public PaymentOrder retrievePaymentOrderById(Long id);

	public Page<PaymentOrder> pagePaymentOrder(int page, int limit);
	
	public List<PaymentOrder> listPaymentOrder();
	
	public PaymentOrder getByDomainAndDataId(String domain, Long dataId);

}
