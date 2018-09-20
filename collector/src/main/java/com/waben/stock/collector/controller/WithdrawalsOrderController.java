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

import com.waben.stock.collector.entity.WithdrawalsOrder;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.WithdrawalsOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 提现订单 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/withdrawalsOrder")
@Api(description = "提现订单接口列表")
public class WithdrawalsOrderController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public WithdrawalsOrderService withdrawalsOrderService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取提现订单")
	public DataResponse<WithdrawalsOrder> fetchById(@PathVariable Long id) {
		return new DataResponse<>(withdrawalsOrderService.getWithdrawalsOrderInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取提现订单分页数据")
	public DataResponse<Page<WithdrawalsOrder>> withdrawalsOrders(int page, int limit) {
		return new DataResponse<>((Page<WithdrawalsOrder>) withdrawalsOrderService.withdrawalsOrders(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取提现订单列表")
	public DataResponse<List<WithdrawalsOrder>> list() {
		return new DataResponse<>(withdrawalsOrderService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加提现订单", hidden = true)
	public DataResponse<WithdrawalsOrder> addition(WithdrawalsOrder withdrawalsOrder) {
		return new DataResponse<>(withdrawalsOrderService.addWithdrawalsOrder(withdrawalsOrder));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改提现订单", hidden = true)
	public DataResponse<WithdrawalsOrder> modification(WithdrawalsOrder withdrawalsOrder) {
		return new DataResponse<>(withdrawalsOrderService.modifyWithdrawalsOrder(withdrawalsOrder));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除提现订单", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		withdrawalsOrderService.deleteWithdrawalsOrder(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除提现订单（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		withdrawalsOrderService.deleteWithdrawalsOrders(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取提现订单列表(后台管理)", hidden = true)
	public DataResponse<List<WithdrawalsOrder>> adminList() {
		return new DataResponse<>(withdrawalsOrderService.list());
	}

}
