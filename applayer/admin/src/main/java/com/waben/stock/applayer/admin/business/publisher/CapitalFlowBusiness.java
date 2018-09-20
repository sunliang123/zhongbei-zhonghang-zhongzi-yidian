package com.waben.stock.applayer.admin.business.publisher;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.admin.publisher.CapitalFlowAdminDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.admin.publisher.CapitalFlowAdminQuery;
import com.waben.stock.interfaces.service.publisher.CapitalFlowInterface;

/**
 * 资金流水 Business
 * 
 * @author luomengan
 */
@Service("adminCapitalFlowBusiness")
public class CapitalFlowBusiness {

	@Autowired
	private CapitalFlowInterface reference;

	public PageInfo<CapitalFlowAdminDto> adminPagesByQuery(CapitalFlowAdminQuery query) {
		Response<PageInfo<CapitalFlowAdminDto>> response = reference.adminPagesByQuery(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public BigDecimal adminAccumulateAmountByQuery(CapitalFlowAdminQuery query) {
		Response<BigDecimal> response = reference.adminAccumulateAmountByQuery(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
