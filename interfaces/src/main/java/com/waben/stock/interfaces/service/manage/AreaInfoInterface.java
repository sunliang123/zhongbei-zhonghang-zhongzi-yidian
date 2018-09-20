package com.waben.stock.interfaces.service.manage;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waben.stock.interfaces.dto.manage.AreaInfoDto;
import com.waben.stock.interfaces.pojo.Response;

/**
 * 区域 Controller接口
 * 
 * @author luomengan
 *
 */
public interface AreaInfoInterface {

	@RequestMapping(value = "/parentcode/{parentCode}", method = RequestMethod.GET)
	public Response<List<AreaInfoDto>> fetchByParentCode(@PathVariable("parentCode") String parentCode);

}
