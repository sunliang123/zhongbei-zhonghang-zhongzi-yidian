package com.waben.stock.applayer.strategist.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.publisher.RealNameDto;
import com.waben.stock.interfaces.enums.ResourceType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.RealNameInterface;

/**
 * 实名认证 Business
 * 
 * @author luomengan
 *
 */
@Service("strategistRealNameBusiness")
public class RealNameBusiness {

	@Autowired
	private RealNameInterface reference;

	public RealNameDto add(RealNameDto realName) {
		Response<RealNameDto> response = reference.add(realName);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public RealNameDto fetch(ResourceType resourceType, Long resourceId) {
		Response<RealNameDto> response = reference.fetch(resourceType.getIndex(), resourceId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
