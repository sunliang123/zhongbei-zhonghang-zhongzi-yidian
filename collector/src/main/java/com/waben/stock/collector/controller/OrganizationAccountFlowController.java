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

import com.waben.stock.collector.entity.OrganizationAccountFlow;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.OrganizationAccountFlowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 机构账户流水 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/organizationAccountFlow")
@Api(description = "机构账户流水接口列表")
public class OrganizationAccountFlowController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OrganizationAccountFlowService organizationAccountFlowService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取机构账户流水")
	public DataResponse<OrganizationAccountFlow> fetchById(@PathVariable Long id) {
		return new DataResponse<>(organizationAccountFlowService.getOrganizationAccountFlowInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取机构账户流水分页数据")
	public DataResponse<Page<OrganizationAccountFlow>> organizationAccountFlows(int page, int limit) {
		return new DataResponse<>((Page<OrganizationAccountFlow>) organizationAccountFlowService.organizationAccountFlows(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取机构账户流水列表")
	public DataResponse<List<OrganizationAccountFlow>> list() {
		return new DataResponse<>(organizationAccountFlowService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加机构账户流水", hidden = true)
	public DataResponse<OrganizationAccountFlow> addition(OrganizationAccountFlow organizationAccountFlow) {
		return new DataResponse<>(organizationAccountFlowService.addOrganizationAccountFlow(organizationAccountFlow));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改机构账户流水", hidden = true)
	public DataResponse<OrganizationAccountFlow> modification(OrganizationAccountFlow organizationAccountFlow) {
		return new DataResponse<>(organizationAccountFlowService.modifyOrganizationAccountFlow(organizationAccountFlow));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除机构账户流水", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		organizationAccountFlowService.deleteOrganizationAccountFlow(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除机构账户流水（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		organizationAccountFlowService.deleteOrganizationAccountFlows(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取机构账户流水列表(后台管理)", hidden = true)
	public DataResponse<List<OrganizationAccountFlow>> adminList() {
		return new DataResponse<>(organizationAccountFlowService.list());
	}

}
