package com.waben.stock.applayer.tactics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.AppVersionUpgradeBusiness;
import com.waben.stock.interfaces.dto.manage.AppVersionUpgradeDto;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * app版本 Controller
 * 
 * @author luomengan
 *
 */
@RestController("tacticsAppVersionUpgradeController")
@RequestMapping("/appversionupgrade")
@Api(description = "app版本")
public class AppVersionUpgradeController {

	@Autowired
	private AppVersionUpgradeBusiness business;

	@GetMapping("/checkUpgrade")
	@ApiOperation(value = "检查更新", notes = "deviceType:0安卓,1IOS")
	public Response<AppVersionUpgradeDto> checkUpgrade(Integer versionCode, Integer deviceType,
			@RequestParam(required = false) Integer shellIndex) {
		return new Response<>(business.checkUpgrade(versionCode, deviceType, shellIndex));
	}

}
