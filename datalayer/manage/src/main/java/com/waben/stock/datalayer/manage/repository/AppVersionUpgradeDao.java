package com.waben.stock.datalayer.manage.repository;

import com.waben.stock.datalayer.manage.entity.AppVersionUpgrade;

/**
 * app版本升级 Dao
 * 
 * @author luomengan
 *
 */
public interface AppVersionUpgradeDao extends BaseDao<AppVersionUpgrade, Long> {

	AppVersionUpgrade getGreaterThanCurrentVersion(Integer versionCode, Integer deviceType, Integer shellIndex);

}
