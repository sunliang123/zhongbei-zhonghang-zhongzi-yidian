package com.waben.stock.applayer.tactics.business;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.publisher.PaymentOrderDto;
import com.waben.stock.interfaces.enums.PaymentState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.PaymentOrderQuery;
import com.waben.stock.interfaces.service.publisher.PaymentOrderInterface;

@Service("tacticsPaymentOrderBusiness")
public class PaymentOrderBusiness {

	@Autowired
	private PaymentOrderInterface paymentOrderReference;

	@Autowired
	private CapitalAccountBusiness accountBusiness;

	public PaymentOrderDto save(PaymentOrderDto paymentOrder) {
		Response<PaymentOrderDto> orderResp = paymentOrderReference.addPaymentOrder(paymentOrder);
		if ("200".equals(orderResp.getCode())) {
			return orderResp.getResult();
		}
		throw new ServiceException(orderResp.getCode());
	}

	public PaymentOrderDto findByPaymentNo(String paymentNo) {
		Response<PaymentOrderDto> orderResp = paymentOrderReference.fetchByPaymentNo(paymentNo);
		if ("200".equals(orderResp.getCode())) {
			return orderResp.getResult();
		}
		throw new ServiceException(orderResp.getCode());
	}
	
	public PaymentOrderDto findById(Long paymentId) {
		Response<PaymentOrderDto> orderResp = paymentOrderReference.fetchById(paymentId);
		if ("200".equals(orderResp.getCode())) {
			return orderResp.getResult();
		}
		throw new ServiceException(orderResp.getCode());
	}

	public PaymentOrderDto changeState(String paymentNo, PaymentState state) {
		Response<PaymentOrderDto> orderResp = paymentOrderReference.changeState(paymentNo, state.getIndex());
		if ("200".equals(orderResp.getCode())) {
			return orderResp.getResult();
		}
		throw new ServiceException(orderResp.getCode());
	}

	public PageInfo<PaymentOrderDto> pages(PaymentOrderQuery query) {
		Response<PageInfo<PaymentOrderDto>> response = paymentOrderReference.pagesByQuery(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PaymentOrderDto aliturnPaid(String paymentNo) {
		PaymentOrderDto paymentOrder = this.findByPaymentNo(paymentNo);
		if (paymentOrder.getState() != PaymentState.Paid) {
			PaymentState oldState = paymentOrder.getState();
			this.changeState(paymentNo, PaymentState.Paid);
			paymentOrder.setState(PaymentState.Paid);
			if (oldState == PaymentState.PartPaid) {
				accountBusiness.recharge(paymentOrder.getPublisherId(),
						paymentOrder.getAmount().subtract(paymentOrder.getPartAmount()), paymentOrder.getId());
			} else {
				accountBusiness.recharge(paymentOrder.getPublisherId(), paymentOrder.getAmount(), paymentOrder.getId());
			}
		}
		return paymentOrder;
	}

	public PaymentOrderDto aliturnPartPaid(String paymentNo, BigDecimal partAmount) {
		PaymentOrderDto paymentOrder = this.findByPaymentNo(paymentNo);
		if (paymentOrder.getState() == PaymentState.Unpaid || paymentOrder.getState() == PaymentState.PartPaid) {
			if (partAmount.add(paymentOrder.getPartAmount() != null ? paymentOrder.getPartAmount() : new BigDecimal(0))
					.compareTo(paymentOrder.getAmount()) >= 0) {
				paymentOrder.setState(PaymentState.Paid);
				paymentOrder.setPartAmount(partAmount
						.add(paymentOrder.getPartAmount() != null ? paymentOrder.getPartAmount() : new BigDecimal(0)));
				this.save(paymentOrder);
			} else {
				paymentOrder.setState(PaymentState.PartPaid);
				paymentOrder.setPartAmount(partAmount);
				this.save(paymentOrder);
			}
			accountBusiness.recharge(paymentOrder.getPublisherId(), partAmount, paymentOrder.getId());
		} else {
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "支付订单状态不匹配");
		}
		return paymentOrder;
	}

}
