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

import com.waben.stock.collector.entity.OrganizationPublisher;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.OrganizationPublisherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 机构推广的发布人 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/organizationPublisher")
@Api(description = "机构推广的发布人接口列表")
public class OrganizationPublisherController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OrganizationPublisherService organizationPublisherService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取机构推广的发布人")
	public DataResponse<OrganizationPublisher> fetchById(@PathVariable Long id) {
		return new DataResponse<>(organizationPublisherService.getOrganizationPublisherInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取机构推广的发布人分页数据")
	public DataResponse<Page<OrganizationPublisher>> organizationPublishers(int page, int limit) {
		return new DataResponse<>((Page<OrganizationPublisher>) organizationPublisherService.organizationPublishers(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取机构推广的发布人列表")
	public DataResponse<List<OrganizationPublisher>> list() {
		return new DataResponse<>(organizationPublisherService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加机构推广的发布人", hidden = true)
	public DataResponse<OrganizationPublisher> addition(OrganizationPublisher organizationPublisher) {
		return new DataResponse<>(organizationPublisherService.addOrganizationPublisher(organizationPublisher));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改机构推广的发布人", hidden = true)
	public DataResponse<OrganizationPublisher> modification(OrganizationPublisher organizationPublisher) {
		return new DataResponse<>(organizationPublisherService.modifyOrganizationPublisher(organizationPublisher));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除机构推广的发布人", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		organizationPublisherService.deleteOrganizationPublisher(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除机构推广的发布人（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		organizationPublisherService.deleteOrganizationPublishers(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取机构推广的发布人列表(后台管理)", hidden = true)
	public DataResponse<List<OrganizationPublisher>> adminList() {
		return new DataResponse<>(organizationPublisherService.list());
	}

}
