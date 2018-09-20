package com.waben.stock.applayer.tactics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.AppVersionAuditBusiness;
import com.waben.stock.interfaces.dto.manage.AppVersionAuditDto;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * app版本 Controller
 * 
 * @author luomengan
 *
 */
@RestController("tacticsAppVersionController")
@RequestMapping("/appversion")
@Api(description = "app版本")
public class AppVersionController {

	@Autowired
	private AppVersionAuditBusiness business;

	@GetMapping("/currentAppVersion")
	@ApiOperation(value = "获取当前审核版本", notes = "deviceType设备类型(1IOS 2安卓),shellIndex空壳包序号")
	public Response<AppVersionAuditDto> getCurrentAppVersion(@RequestParam(defaultValue = "1") Integer deviceType,
			@RequestParam(defaultValue = "1") Integer shellIndex) {
		return new Response<>(business.getAppVersionAudit(deviceType, shellIndex));
	}

}
