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

import com.waben.stock.collector.entity.CapitalAccount;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.CapitalAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资金账户 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/capitalAccount")
@Api(description = "资金账户接口列表")
public class CapitalAccountController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public CapitalAccountService capitalAccountService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取资金账户")
	public DataResponse<CapitalAccount> fetchById(@PathVariable Long id) {
		return new DataResponse<>(capitalAccountService.getCapitalAccountInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取资金账户分页数据")
	public DataResponse<Page<CapitalAccount>> capitalAccounts(int page, int limit) {
		return new DataResponse<>((Page<CapitalAccount>) capitalAccountService.capitalAccounts(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取资金账户列表")
	public DataResponse<List<CapitalAccount>> list() {
		return new DataResponse<>(capitalAccountService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加资金账户", hidden = true)
	public DataResponse<CapitalAccount> addition(CapitalAccount capitalAccount) {
		return new DataResponse<>(capitalAccountService.addCapitalAccount(capitalAccount));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改资金账户", hidden = true)
	public DataResponse<CapitalAccount> modification(CapitalAccount capitalAccount) {
		return new DataResponse<>(capitalAccountService.modifyCapitalAccount(capitalAccount));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除资金账户", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		capitalAccountService.deleteCapitalAccount(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除资金账户（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		capitalAccountService.deleteCapitalAccounts(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取资金账户列表(后台管理)", hidden = true)
	public DataResponse<List<CapitalAccount>> adminList() {
		return new DataResponse<>(capitalAccountService.list());
	}

}
