package com.waben.stock.datalayer.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.manage.entity.AnalogData;
import com.waben.stock.datalayer.manage.service.AnalogDataService;
import com.waben.stock.interfaces.dto.manage.AnalogDataDto;
import com.waben.stock.interfaces.enums.AnalogDataType;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.manage.AnalogDataInterface;
import com.waben.stock.interfaces.util.PageToPageInfo;

/**
 * 模拟数据 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/analogdata")
@Component
public class AnalogDataController implements AnalogDataInterface {

	@Autowired
	private AnalogDataService service;

	public Response<PageInfo<AnalogDataDto>> pagesByType(String typeIndex, int page, int limit) {
		Page<AnalogData> pageData = service.pageByType(AnalogDataType.getByIndex(typeIndex), page, limit);
		PageInfo<AnalogDataDto> result = PageToPageInfo.pageToPageInfo(pageData, AnalogDataDto.class);
		return new Response<>(result);
	}

}
