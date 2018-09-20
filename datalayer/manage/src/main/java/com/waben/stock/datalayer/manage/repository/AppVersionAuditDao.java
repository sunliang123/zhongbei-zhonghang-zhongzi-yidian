package com.waben.stock.datalayer.manage.repository;

import com.waben.stock.datalayer.manage.entity.AppVersionAudit;

/**
 * app版本 Dao
 * 
 * @author luomengan
 *
 */
public interface AppVersionAuditDao extends BaseDao<AppVersionAudit, Long> {

	AppVersionAudit findByDeviceTypeAndShellIndex(Integer deviceType, Integer shellIndex);

}
