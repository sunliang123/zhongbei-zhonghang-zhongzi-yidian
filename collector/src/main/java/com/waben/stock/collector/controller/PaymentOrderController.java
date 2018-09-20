package com.waben.stock.collector.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.collector.entity.PaymentOrder;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.PaymentOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付订单 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/paymentOrder")
@Api(description = "支付订单接口列表")
public class PaymentOrderController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public PaymentOrderService paymentOrderService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取支付订单")
	public DataResponse<PaymentOrder> fetchById(@PathVariable Long id) {
		return new DataResponse<>(paymentOrderService.getPaymentOrderInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取支付订单分页数据")
	public DataResponse<Page<PaymentOrder>> paymentOrders(int page, int limit) {
		return new DataResponse<>((Page<PaymentOrder>) paymentOrderService.paymentOrders(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取支付订单列表")
	public DataResponse<List<PaymentOrder>> list() {
		return new DataResponse<>(paymentOrderService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加支付订单", hidden = true)
	public DataResponse<PaymentOrder> addition(PaymentOrder paymentOrder) {
		return new DataResponse<>(paymentOrderService.addPaymentOrder(paymentOrder));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改支付订单", hidden = true)
	public DataResponse<PaymentOrder> modification(PaymentOrder paymentOrder) {
		return new DataResponse<>(paymentOrderService.modifyPaymentOrder(paymentOrder));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除支付订单", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		paymentOrderService.deletePaymentOrder(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除支付订单（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		paymentOrderService.deletePaymentOrders(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取支付订单列表(后台管理)", hidden = true)
	public DataResponse<List<PaymentOrder>> adminList() {
		return new DataResponse<>(paymentOrderService.list());
	}

}
