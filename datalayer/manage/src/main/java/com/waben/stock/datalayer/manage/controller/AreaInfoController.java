package com.waben.stock.datalayer.manage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.manage.entity.AreaInfo;
import com.waben.stock.datalayer.manage.service.AreaInfoService;
import com.waben.stock.interfaces.dto.manage.AreaInfoDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.manage.AreaInfoInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * 区域 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/areainfo")
@Component
public class AreaInfoController implements AreaInfoInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AreaInfoService areaInfoService;

	@Override
	public Response<List<AreaInfoDto>> fetchByParentCode(@PathVariable String parentCode) {
		List<AreaInfo> areaInfos = areaInfoService.findByParentCode(parentCode);
		List<AreaInfoDto> areaInfoDtos = CopyBeanUtils.copyListBeanPropertiesToList(areaInfos, AreaInfoDto.class);
		return new Response<>(areaInfoDtos);
	}
	
}
