package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.PaymentOrderDao;
import com.waben.stock.collector.entity.PaymentOrder;

/**
 * 支付订单 Service
 * 
 * @author luomengan
 *
 */
@Service
public class PaymentOrderService {

	@Autowired
	private PaymentOrderDao paymentOrderDao;

	public PaymentOrder getPaymentOrderInfo(Long id) {
		return paymentOrderDao.retrievePaymentOrderById(id);
	}

	@Transactional
	public PaymentOrder addPaymentOrder(PaymentOrder paymentOrder) {
		return paymentOrderDao.createPaymentOrder(paymentOrder);
	}

	@Transactional
	public PaymentOrder modifyPaymentOrder(PaymentOrder paymentOrder) {
		return paymentOrderDao.updatePaymentOrder(paymentOrder);
	}

	@Transactional
	public void deletePaymentOrder(Long id) {
		paymentOrderDao.deletePaymentOrderById(id);
	}
	
	@Transactional
	public void deletePaymentOrders(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					paymentOrderDao.deletePaymentOrderById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<PaymentOrder> paymentOrders(int page, int limit) {
		return paymentOrderDao.pagePaymentOrder(page, limit);
	}
	
	public List<PaymentOrder> list() {
		return paymentOrderDao.listPaymentOrder();
	}

}
