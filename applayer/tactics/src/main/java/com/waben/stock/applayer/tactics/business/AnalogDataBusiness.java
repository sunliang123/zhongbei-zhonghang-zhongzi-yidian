package com.waben.stock.applayer.tactics.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.manage.AnalogDataDto;
import com.waben.stock.interfaces.enums.AnalogDataType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.manage.AnalogDataInterface;

/**
 * 模拟数据 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsAnalogDataBusiness")
public class AnalogDataBusiness {

	@Autowired
	private AnalogDataInterface reference;

	public PageInfo<AnalogDataDto> pagesByType(AnalogDataType type, int page, int limit) {
		Response<PageInfo<AnalogDataDto>> response = reference.pagesByType(type.getIndex(), page, limit);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
