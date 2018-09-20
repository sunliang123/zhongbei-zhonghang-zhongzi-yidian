package com.waben.stock.applayer.admin.business.publisher;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.admin.security.CustomUserDetails;
import com.waben.stock.applayer.admin.security.SecurityUtil;
import com.waben.stock.interfaces.dto.admin.publisher.CapitalAccountAdminDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.admin.publisher.CapitalAccountAdminQuery;
import com.waben.stock.interfaces.service.publisher.CapitalAccountInterface;

/**
 * 资金账户 Business
 * 
 * @author luomengan
 */
@Service("adminCapitalAccountBusiness")
public class CapitalAccountBusiness {

	@Autowired
	private CapitalAccountInterface reference;

	public PageInfo<CapitalAccountAdminDto> adminPagesByQuery(CapitalAccountAdminQuery query) {
		Response<PageInfo<CapitalAccountAdminDto>> response = reference.adminPagesByQuery(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto revisionState(Long id, Integer state) {
		Response<CapitalAccountDto> response = reference.modifyState(id, state);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto revisionAccount(Long id, BigDecimal availableBalance) {
		CustomUserDetails userDetails = SecurityUtil.getUserDetails();
		Response<CapitalAccountDto> response = reference.modifyAccount(userDetails.getUserId(),id, availableBalance);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}
}
