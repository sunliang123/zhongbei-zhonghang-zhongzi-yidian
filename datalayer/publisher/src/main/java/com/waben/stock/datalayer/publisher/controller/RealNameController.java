package com.waben.stock.datalayer.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.publisher.entity.RealName;
import com.waben.stock.datalayer.publisher.service.RealNameService;
import com.waben.stock.interfaces.dto.publisher.RealNameDto;
import com.waben.stock.interfaces.enums.ResourceType;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.RealNameInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * 实名认证 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/realname")
@Component
public class RealNameController implements RealNameInterface {

	@Autowired
	private RealNameService service;

	@Override
	public Response<RealNameDto> add(@RequestBody RealNameDto realName) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(RealNameDto.class,
				service.save(CopyBeanUtils.copyBeanProperties(RealName.class, realName, false)), false));
	}

	@Override
	public Response<RealNameDto> fetch(@PathVariable String resourceTypeIndex, @PathVariable Long resourceId) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(RealNameDto.class,
				service.findByResourceTypeAndResourceId(ResourceType.getByIndex(resourceTypeIndex), resourceId),
				false));
	}

}
