package com.waben.stock.applayer.strategist.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.strategist.business.AreaInfoBusiness;
import com.waben.stock.interfaces.dto.manage.AreaInfoDto;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 区域 Controller
 * 
 * @author luomengan
 *
 */
@RestController("strategistAreaInfoController")
@RequestMapping("/areainfo")
@Api(description = "地区")
public class AreaInfoController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AreaInfoBusiness areaInfoBusiness;

	@GetMapping("/provinces")
	@ApiOperation(value = "获取省份列表")
	public Response<List<AreaInfoDto>> fetchProvinces() {
		return new Response<>(areaInfoBusiness.findByParentCode("0000"));
	}

	@GetMapping("/children/{parentCode}")
	@ApiOperation(value = "根据父code获取下级区域列表")
	public Response<List<AreaInfoDto>> fetchChildren(@PathVariable("parentCode") String parentCode) {
		return new Response<>(areaInfoBusiness.findByParentCode(parentCode));
	}

}
