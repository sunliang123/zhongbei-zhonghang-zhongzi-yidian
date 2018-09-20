package com.waben.stock.datalayer.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.publisher.entity.PaymentOrder;
import com.waben.stock.datalayer.publisher.service.PaymentOrderService;
import com.waben.stock.interfaces.dto.publisher.PaymentOrderDto;
import com.waben.stock.interfaces.enums.PaymentState;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.PaymentOrderQuery;
import com.waben.stock.interfaces.service.publisher.PaymentOrderInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;

/**
 * 支付订单 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/paymentorder")
@Component
public class PaymentOrderController implements PaymentOrderInterface {

	@Autowired
	private PaymentOrderService service;

	@Override
	public Response<PaymentOrderDto> addPaymentOrder(@RequestBody PaymentOrderDto paymentOrderDto) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(PaymentOrderDto.class,
				service.save(CopyBeanUtils.copyBeanProperties(PaymentOrder.class, paymentOrderDto, false)), false));
	}

	@Override
	public Response<PaymentOrderDto> changeState(@PathVariable String paymentNo, String stateIndex) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(PaymentOrderDto.class,
				service.changeState(paymentNo, PaymentState.getByIndex(stateIndex)), false));
	}

	@Override
	public Response<PaymentOrderDto> fetchByPaymentNo(@PathVariable String paymentNo) {
		return new Response<>(
				CopyBeanUtils.copyBeanProperties(PaymentOrderDto.class, service.findByPaymentNo(paymentNo), false));
	}

	@Override
	public Response<PageInfo<PaymentOrderDto>> pages(PaymentOrderQuery query) {
		Page<PaymentOrder> pages = service.pages(query);
		PageInfo<PaymentOrderDto> result = new PageInfo<>(pages, PaymentOrderDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<PageInfo<PaymentOrderDto>> pagesByQuery(@RequestBody PaymentOrderQuery query) {
		Page<PaymentOrder> page = service.pagesByQuery(query);
		PageInfo<PaymentOrderDto> result = PageToPageInfo.pageToPageInfo(page, PaymentOrderDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<PaymentOrderDto> fetchById(@PathVariable Long id) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(PaymentOrderDto.class, service.findById(id), false));
	}

	@Override
	public Response<PaymentOrderDto> modifyPaymentOrder(@RequestBody PaymentOrderDto paymentOrderDto) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(PaymentOrderDto.class,
				service.revision(CopyBeanUtils.copyBeanProperties(PaymentOrder.class, paymentOrderDto, false)), false));
	}

}
