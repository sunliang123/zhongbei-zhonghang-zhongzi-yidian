package com.waben.stock.applayer.tactics.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.PaymentOrderBusiness;
import com.waben.stock.interfaces.dto.publisher.PaymentOrderDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.PaymentOrderQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付订单 Controller
 * 
 * @author luomengan
 *
 */
@RestController("tacticsPaymentOrderController")
@RequestMapping("/paymentorder")
@Api(description = "支付订单")
public class PaymentOrderController {

	@Autowired
	private PaymentOrderBusiness paymentOrderBusiness;

	@PostMapping("/pages")
	@ApiOperation(value = "查询订单")
	public Response<PageInfo<PaymentOrderDto>> pages(@RequestBody PaymentOrderQuery query) {
		return new Response<>(paymentOrderBusiness.pages(query));
	}

	@PostMapping("/aliturn/paid")
	@ApiOperation(value = "支付宝转账支付确认支付完成")
	public Response<PaymentOrderDto> aliturnPaid(@RequestParam(required = true) String paymentNo) {
		return new Response<>(paymentOrderBusiness.aliturnPaid(paymentNo));
	}

	@PostMapping("/aliturn/partpaid")
	@ApiOperation(value = "支付宝转账支付确认部分支付完成")
	public Response<PaymentOrderDto> aliturnPartPaid(@RequestParam(required = true) String paymentNo,
			@RequestParam(required = true) BigDecimal partAmount) {
		return new Response<>(paymentOrderBusiness.aliturnPartPaid(paymentNo, partAmount));
	}

}
