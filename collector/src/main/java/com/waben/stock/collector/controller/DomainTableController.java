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

import com.waben.stock.collector.entity.DomainTable;
import com.waben.stock.collector.pojo.DataResponse;
import com.waben.stock.collector.service.DomainTableService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 应用表 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/domainTable")
@Api(description = "应用表接口列表")
public class DomainTableController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public DomainTableService domainTableService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取应用表")
	public DataResponse<DomainTable> fetchById(@PathVariable Long id) {
		return new DataResponse<>(domainTableService.getDomainTableInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取应用表分页数据")
	public DataResponse<Page<DomainTable>> domainTables(int page, int limit) {
		return new DataResponse<>((Page<DomainTable>) domainTableService.domainTables(page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取应用表列表")
	public DataResponse<List<DomainTable>> list() {
		return new DataResponse<>(domainTableService.list());
	}

	@GetMapping("/listByDomain")
	@ApiOperation(value = "根据用用获取应用表列表")
	public List<DomainTable> listByDomain(String domain) {
		List<DomainTable> result = domainTableService.listByDomain(domain);
		if (result != null && result.size() > 0) {
			for (DomainTable table : result) {
				// 最新ID更新
				if (table.getUpgradeType() != null && table.getUpgradeType().intValue() == 2
						&& table.getLastId() != null && table.getLastId().intValue() > 0) {
					String sql = table.getExecuteSql();
					if (sql.indexOf("where") > 0) {
						sql = sql + " and id>" + table.getLastId();
					} else {
						sql = sql + " where id>" + table.getLastId();
					}
					table.setExecuteSql(sql);
				}
			}
		}
		return result;
	}

	@PostMapping("/receiveData")
	@ApiOperation(value = "接收数据")
	public DataResponse<String> receiveData(Long domainTableId, String data) {
		domainTableService.receiveData(domainTableId, data);
		return new DataResponse<String>("success");
	}

	@PostMapping("/receiveError")
	@ApiOperation(value = "接收数据")
	public DataResponse<String> receiveError(Long domainTableId, String error) {
		domainTableService.receiveError(domainTableId, error);
		return new DataResponse<String>("success");
	}

	/******************************** 后台管理 **********************************/

	@PostMapping("/")
	@ApiOperation(value = "添加应用表", hidden = true)
	public DataResponse<DomainTable> addition(DomainTable domainTable) {
		return new DataResponse<>(domainTableService.addDomainTable(domainTable));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改应用表", hidden = true)
	public DataResponse<DomainTable> modification(DomainTable domainTable) {
		return new DataResponse<>(domainTableService.modifyDomainTable(domainTable));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除应用表", hidden = true)
	public DataResponse<Long> delete(@PathVariable Long id) {
		domainTableService.deleteDomainTable(id);
		return new DataResponse<Long>(id);
	}

	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除应用表（多个id以逗号分割）", hidden = true)
	public DataResponse<Boolean> deletes(String ids) {
		domainTableService.deleteDomainTables(ids);
		return new DataResponse<Boolean>(true);
	}

	@GetMapping("/adminList")
	@ApiOperation(value = "获取应用表列表(后台管理)", hidden = true)
	public DataResponse<List<DomainTable>> adminList() {
		return new DataResponse<>(domainTableService.list());
	}

}
