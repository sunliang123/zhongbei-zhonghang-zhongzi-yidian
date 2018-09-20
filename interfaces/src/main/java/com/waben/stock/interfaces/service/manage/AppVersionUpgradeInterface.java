package com.waben.stock.interfaces.service.manage;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.manage.AppVersionUpgradeDto;
import com.waben.stock.interfaces.pojo.Response;

/**
 * app版本升级 公共接口
 * 
 * @author luomengan
 *
 */
public interface AppVersionUpgradeInterface {

	@RequestMapping(value = "/{versionCode}/checkUpgrade/{deviceType}", method = RequestMethod.GET)
	Response<AppVersionUpgradeDto> checkUpgrade(@PathVariable("versionCode") Integer versionCode,
			@PathVariable("deviceType") Integer deviceType, @RequestParam("shellIndex") Integer shellIndex);

}
