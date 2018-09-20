package com.waben.stock.datalayer.publisher.repository.impl.jpa;

import com.waben.stock.datalayer.publisher.entity.PaymentOrder;

/**
 * 支付订单 Jpa
 * 
 * @author luomengan
 *
 */
public interface PaymentOrderRepository extends CustomJpaRepository<PaymentOrder, Long> {

	PaymentOrder findByPaymentNo(String paymentNo);

}
