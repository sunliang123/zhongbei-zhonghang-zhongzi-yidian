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

import com.waben.stock.collector.entity.Publisher;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.PublisherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 策略发布人 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/publisher")
@Api(description = "策略发布人接口列表")
public class PublisherController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public PublisherService publisherService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取策略发布人")
	public DataResponse<Publisher> fetchById(@PathVariable Long id) {
		return new DataResponse<>(publisherService.getPublisherInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取策略发布人分页数据")
	public DataResponse<Page<Publisher>> publishers(int page, int limit) {
		return new DataResponse<>((Page<Publisher>) publisherService.publishers(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取策略发布人列表")
	public DataResponse<List<Publisher>> list() {
		return new DataResponse<>(publisherService.list());
	}
	
	/******************************** 后台管理 **********************************/
	
	@PostMapping("/")
	@ApiOperation(value = "添加策略发布人", hidden = true)
	public DataResponse<Publisher> addition(Publisher publisher) {
		return new DataResponse<>(publisherService.addPublisher(publisher));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改策略发布人", hidden = true)
	public DataResponse<Publisher> modification(Publisher publisher) {
		return new DataResponse<>(publisherService.modifyPublisher(publisher));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除策略发布人", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		publisherService.deletePublisher(id);
		return new DataResponse<Long>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除策略发布人（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		publisherService.deletePublishers(ids);
		return new DataResponse<Boolean>(true);
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取策略发布人列表(后台管理)", hidden = true)
	public DataResponse<List<Publisher>> adminList() {
		return new DataResponse<>(publisherService.list());
	}

}
