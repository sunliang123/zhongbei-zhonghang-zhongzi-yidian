package com.waben.stock.datalayer.publisher.repository;

import com.waben.stock.datalayer.publisher.entity.PaymentOrder;

/**
 * 支付订单 Dao
 * 
 * @author luomengan
 *
 */
public interface PaymentOrderDao extends BaseDao<PaymentOrder, Long> {

	PaymentOrder retrieveByPaymentNo(String paymentNo);

}
