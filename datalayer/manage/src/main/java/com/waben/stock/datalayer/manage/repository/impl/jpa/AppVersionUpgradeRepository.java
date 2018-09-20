package com.waben.stock.datalayer.manage.repository.impl.jpa;

import com.waben.stock.datalayer.manage.entity.AppVersionUpgrade;

/**
 * app版本升级 Jpa
 * 
 * @author luomengan
 *
 */
public interface AppVersionUpgradeRepository extends CustomJpaRepository<AppVersionUpgrade, Long> {

	AppVersionUpgrade findByIsCurrentVersionAndDeviceTypeAndShellIndexAndVersionCodeGreaterThan(
			Boolean isCurrentVersion, Integer deviceType, Integer shellIndex, Integer versionCode);

}
