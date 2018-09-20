package com.waben.stock.datalayer.buyrecord.business;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockcontent.StrategyTypeInterface;

@Service("buyrecordStrategyTypeBusiness")
public class StrategyTypeBusiness {
	
	@Autowired
	private StrategyTypeInterface reference;

	public StrategyTypeDto fetchById(Long strategyTypeId) {
		Response<StrategyTypeDto> response = reference.fetchById(strategyTypeId);
		if("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}
	
}
