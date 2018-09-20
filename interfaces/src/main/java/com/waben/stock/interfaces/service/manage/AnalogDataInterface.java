package com.waben.stock.interfaces.service.manage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.manage.AnalogDataDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;

/**
 * 模拟数据 公共接口
 * 
 * @author luomengan
 *
 */
public interface AnalogDataInterface {

	@RequestMapping(value = "/pagesByType", method = RequestMethod.GET)
	Response<PageInfo<AnalogDataDto>> pagesByType(@RequestParam("typeIndex") String typeIndex,
			@RequestParam("page") int page, @RequestParam("limit") int limit);

}
