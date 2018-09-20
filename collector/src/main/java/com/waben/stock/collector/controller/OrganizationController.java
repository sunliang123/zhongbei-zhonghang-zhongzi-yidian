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

import com.waben.stock.collector.entity.Organization;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.OrganizationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 机构 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/organization")
@Api(description = "机构接口列表")
public class OrganizationController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OrganizationService organizationService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取机构")
	public DataResponse<Organization> fetchById(@PathVariable Long id) {
		return new DataResponse<>(organizationService.getOrganizationInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取机构分页数据")
	public DataResponse<Page<Organization>> organizations(int page, int limit) {
		return new DataResponse<>((Page<Organization>) organizationService.organizations(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取机构列表")
	public DataResponse<List<Organization>> list() {
		return new DataResponse<>(organizationService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加机构", hidden = true)
	public DataResponse<Organization> addition(Organization organization) {
		return new DataResponse<>(organizationService.addOrganization(organization));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改机构", hidden = true)
	public DataResponse<Organization> modification(Organization organization) {
		return new DataResponse<>(organizationService.modifyOrganization(organization));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除机构", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		organizationService.deleteOrganization(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除机构（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		organizationService.deleteOrganizations(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取机构列表(后台管理)", hidden = true)
	public DataResponse<List<Organization>> adminList() {
		return new DataResponse<>(organizationService.list());
	}

}
