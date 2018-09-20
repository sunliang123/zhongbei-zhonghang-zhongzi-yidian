package com.waben.stock.datalayer.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.manage.service.AppVersionAuditService;
import com.waben.stock.interfaces.dto.manage.AppVersionAuditDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.manage.AppVersionAuditInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * app版本审核 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/appversionaudit")
@Component
public class AppVersionAuditController implements AppVersionAuditInterface {

	@Autowired
	private AppVersionAuditService service;

	@Override
	public Response<AppVersionAuditDto> getAppVersionAudit(Integer deviceType, Integer shellIndex) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(AppVersionAuditDto.class,
				service.getAppVersionAudit(deviceType, shellIndex), false));
	}

}
