package com.waben.stock.interfaces.service.publisher;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waben.stock.interfaces.dto.publisher.RealNameDto;
import com.waben.stock.interfaces.pojo.Response;

public interface RealNameInterface {

	/**
	 * 添加实名认证信息
	 * 
	 * @param realName
	 *            实名认证信息
	 * @return 实名认证信息
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<RealNameDto> add(@RequestBody RealNameDto realName);

	/**
	 * 获取实名认证信息
	 * @param resourceTypeIndex 资源类型索引
	 * @param resourceId 资源ID
	 * @return 实名认证信息
	 */
	@RequestMapping(value = "/{resourceTypeIndex}/{resourceId}", method = RequestMethod.GET)
	Response<RealNameDto> fetch(@PathVariable("resourceTypeIndex") String resourceTypeIndex,
			@PathVariable("resourceId") Long resourceId);

}
