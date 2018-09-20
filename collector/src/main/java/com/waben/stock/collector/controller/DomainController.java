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

import com.waben.stock.collector.entity.Domain;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.DomainService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 应用 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/domain")
@Api(description = "应用接口列表")
public class DomainController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public DomainService domainService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取应用")
	public DataResponse<Domain> fetchById(@PathVariable Long id) {
		return new DataResponse<>(domainService.getDomainInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取应用分页数据")
	public DataResponse<Page<Domain>> domains(int page, int limit) {
		return new DataResponse<>((Page<Domain>) domainService.domains(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取应用列表")
	public DataResponse<List<Domain>> list() {
		return new DataResponse<>(domainService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加应用", hidden = true)
	public DataResponse<Domain> addition(Domain domain) {
		return new DataResponse<>(domainService.addDomain(domain));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改应用", hidden = true)
	public DataResponse<Domain> modification(Domain domain) {
		return new DataResponse<>(domainService.modifyDomain(domain));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除应用", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		domainService.deleteDomain(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除应用（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		domainService.deleteDomains(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取应用列表(后台管理)", hidden = true)
	public DataResponse<List<Domain>> adminList() {
		return new DataResponse<>(domainService.list());
	}

}
