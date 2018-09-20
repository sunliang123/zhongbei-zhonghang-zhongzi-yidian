package com.waben.stock.datalayer.manage.repository.impl.jpa;

import com.waben.stock.datalayer.manage.entity.AppVersionAudit;

/**
 * app版本 Jpa
 * 
 * @author luomengan
 *
 */
public interface AppVersionAuditRepository extends CustomJpaRepository<AppVersionAudit, Long> {

	AppVersionAudit findByDeviceTypeAndShellIndex(Integer deviceType, Integer shellIndex);

}
