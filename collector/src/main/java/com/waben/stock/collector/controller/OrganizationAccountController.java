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

import com.waben.stock.collector.entity.OrganizationAccount;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.OrganizationAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 机构账户 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/organizationAccount")
@Api(description = "机构账户接口列表")
public class OrganizationAccountController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OrganizationAccountService organizationAccountService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取机构账户")
	public DataResponse<OrganizationAccount> fetchById(@PathVariable Long id) {
		return new DataResponse<>(organizationAccountService.getOrganizationAccountInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取机构账户分页数据")
	public DataResponse<Page<OrganizationAccount>> organizationAccounts(int page, int limit) {
		return new DataResponse<>((Page<OrganizationAccount>) organizationAccountService.organizationAccounts(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取机构账户列表")
	public DataResponse<List<OrganizationAccount>> list() {
		return new DataResponse<>(organizationAccountService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加机构账户", hidden = true)
	public DataResponse<OrganizationAccount> addition(OrganizationAccount organizationAccount) {
		return new DataResponse<>(organizationAccountService.addOrganizationAccount(organizationAccount));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改机构账户", hidden = true)
	public DataResponse<OrganizationAccount> modification(OrganizationAccount organizationAccount) {
		return new DataResponse<>(organizationAccountService.modifyOrganizationAccount(organizationAccount));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除机构账户", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		organizationAccountService.deleteOrganizationAccount(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除机构账户（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		organizationAccountService.deleteOrganizationAccounts(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取机构账户列表(后台管理)", hidden = true)
	public DataResponse<List<OrganizationAccount>> adminList() {
		return new DataResponse<>(organizationAccountService.list());
	}

}
