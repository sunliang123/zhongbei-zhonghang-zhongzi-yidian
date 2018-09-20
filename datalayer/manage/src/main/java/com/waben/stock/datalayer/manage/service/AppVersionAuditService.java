package com.waben.stock.datalayer.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.manage.entity.AppVersionAudit;
import com.waben.stock.datalayer.manage.repository.AppVersionAuditDao;

/**
 * app版本 Service
 * 
 * @author luomengan
 *
 */
@Service
public class AppVersionAuditService {

	@Autowired
	private AppVersionAuditDao dao;

	public AppVersionAudit getAppVersionAudit(Integer deviceType, Integer shellIndex) {
		return dao.findByDeviceTypeAndShellIndex(deviceType, shellIndex);
	}

}
