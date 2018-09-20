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

import com.waben.stock.collector.entity.DeferredRecord;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.DeferredRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 递延记录 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/deferredRecord")
@Api(description = "递延记录接口列表")
public class DeferredRecordController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public DeferredRecordService deferredRecordService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取递延记录")
	public DataResponse<DeferredRecord> fetchById(@PathVariable Long id) {
		return new DataResponse<>(deferredRecordService.getDeferredRecordInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取递延记录分页数据")
	public DataResponse<Page<DeferredRecord>> deferredRecords(int page, int limit) {
		return new DataResponse<>((Page<DeferredRecord>) deferredRecordService.deferredRecords(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取递延记录列表")
	public DataResponse<List<DeferredRecord>> list() {
		return new DataResponse<>(deferredRecordService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加递延记录", hidden = true)
	public DataResponse<DeferredRecord> addition(DeferredRecord deferredRecord) {
		return new DataResponse<>(deferredRecordService.addDeferredRecord(deferredRecord));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改递延记录", hidden = true)
	public DataResponse<DeferredRecord> modification(DeferredRecord deferredRecord) {
		return new DataResponse<>(deferredRecordService.modifyDeferredRecord(deferredRecord));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除递延记录", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		deferredRecordService.deleteDeferredRecord(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除递延记录（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		deferredRecordService.deleteDeferredRecords(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取递延记录列表(后台管理)", hidden = true)
	public DataResponse<List<DeferredRecord>> adminList() {
		return new DataResponse<>(deferredRecordService.list());
	}

}
