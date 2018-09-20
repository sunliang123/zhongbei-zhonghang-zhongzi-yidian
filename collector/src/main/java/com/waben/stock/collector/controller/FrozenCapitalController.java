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

import com.waben.stock.collector.entity.FrozenCapital;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.FrozenCapitalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 冻结资金 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/frozenCapital")
@Api(description = "冻结资金接口列表")
public class FrozenCapitalController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public FrozenCapitalService frozenCapitalService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取冻结资金")
	public DataResponse<FrozenCapital> fetchById(@PathVariable Long id) {
		return new DataResponse<>(frozenCapitalService.getFrozenCapitalInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取冻结资金分页数据")
	public DataResponse<Page<FrozenCapital>> frozenCapitals(int page, int limit) {
		return new DataResponse<>((Page<FrozenCapital>) frozenCapitalService.frozenCapitals(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取冻结资金列表")
	public DataResponse<List<FrozenCapital>> list() {
		return new DataResponse<>(frozenCapitalService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加冻结资金", hidden = true)
	public DataResponse<FrozenCapital> addition(FrozenCapital frozenCapital) {
		return new DataResponse<>(frozenCapitalService.addFrozenCapital(frozenCapital));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改冻结资金", hidden = true)
	public DataResponse<FrozenCapital> modification(FrozenCapital frozenCapital) {
		return new DataResponse<>(frozenCapitalService.modifyFrozenCapital(frozenCapital));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除冻结资金", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		frozenCapitalService.deleteFrozenCapital(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除冻结资金（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		frozenCapitalService.deleteFrozenCapitals(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取冻结资金列表(后台管理)", hidden = true)
	public DataResponse<List<FrozenCapital>> adminList() {
		return new DataResponse<>(frozenCapitalService.list());
	}

}
