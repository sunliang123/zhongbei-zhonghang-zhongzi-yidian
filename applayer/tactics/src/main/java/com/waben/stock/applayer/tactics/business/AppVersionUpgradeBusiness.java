package com.waben.stock.applayer.tactics.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.manage.AppVersionUpgradeDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.manage.AppVersionUpgradeInterface;

/**
 * app版本升级 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsAppVersionUpgradeBusiness")
public class AppVersionUpgradeBusiness {

	@Autowired
	private AppVersionUpgradeInterface reference;

	public AppVersionUpgradeDto checkUpgrade(Integer versionCode, Integer deviceType, Integer shellIndex) {
		Response<AppVersionUpgradeDto> response = reference.checkUpgrade(versionCode, deviceType, shellIndex);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
