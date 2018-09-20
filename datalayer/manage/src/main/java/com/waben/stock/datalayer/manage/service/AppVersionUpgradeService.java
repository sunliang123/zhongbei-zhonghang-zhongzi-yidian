package com.waben.stock.datalayer.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.manage.entity.AppVersionUpgrade;
import com.waben.stock.datalayer.manage.repository.AppVersionUpgradeDao;

/**
 * app版本升级 Service
 * 
 * @author luomengan
 *
 */
@Service
public class AppVersionUpgradeService {

	@Autowired
	private AppVersionUpgradeDao dao;

	public AppVersionUpgrade checkUpgrade(Integer versionCode, Integer deviceType, Integer shellIndex) {
		AppVersionUpgrade upgrade = dao.getGreaterThanCurrentVersion(versionCode, deviceType, shellIndex);
		return upgrade;
	}

}
