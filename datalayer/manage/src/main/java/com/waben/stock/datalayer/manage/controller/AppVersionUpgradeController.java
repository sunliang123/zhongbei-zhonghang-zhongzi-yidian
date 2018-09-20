package com.waben.stock.datalayer.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.manage.service.AppVersionUpgradeService;
import com.waben.stock.interfaces.dto.manage.AppVersionUpgradeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.manage.AppVersionUpgradeInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * app版本升级 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/appversionupgrade")
@Component
public class AppVersionUpgradeController implements AppVersionUpgradeInterface {

	@Autowired
	private AppVersionUpgradeService service;

	@Override
	public Response<AppVersionUpgradeDto> checkUpgrade(@PathVariable Integer versionCode,
			@PathVariable Integer deviceType, Integer shellIndex) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(AppVersionUpgradeDto.class,
				service.checkUpgrade(versionCode, deviceType, shellIndex), false));
	}

}
