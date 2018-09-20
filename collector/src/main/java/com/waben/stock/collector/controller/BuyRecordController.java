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

import com.waben.stock.collector.entity.BuyRecord;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.BuyRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 点买记录 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/buyRecord")
@Api(description = "点买记录接口列表")
public class BuyRecordController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public BuyRecordService buyRecordService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取点买记录")
	public DataResponse<BuyRecord> fetchById(@PathVariable Long id) {
		return new DataResponse<>(buyRecordService.getBuyRecordInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取点买记录分页数据")
	public DataResponse<Page<BuyRecord>> buyRecords(int page, int limit) {
		return new DataResponse<>((Page<BuyRecord>) buyRecordService.buyRecords(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取点买记录列表")
	public DataResponse<List<BuyRecord>> list() {
		return new DataResponse<>(buyRecordService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加点买记录", hidden = true)
	public DataResponse<BuyRecord> addition(BuyRecord buyRecord) {
		return new DataResponse<>(buyRecordService.addBuyRecord(buyRecord));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改点买记录", hidden = true)
	public DataResponse<BuyRecord> modification(BuyRecord buyRecord) {
		return new DataResponse<>(buyRecordService.modifyBuyRecord(buyRecord));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除点买记录", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		buyRecordService.deleteBuyRecord(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除点买记录（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		buyRecordService.deleteBuyRecords(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取点买记录列表(后台管理)", hidden = true)
	public DataResponse<List<BuyRecord>> adminList() {
		return new DataResponse<>(buyRecordService.list());
	}

}
