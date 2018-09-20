package com.waben.stock.applayer.tactics.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockcontent.StrategyTypeInterface;

/**
 * 策略类型 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsStrategyTypeBusiness")
public class StrategyTypeBusiness {

	@Autowired
	private StrategyTypeInterface service;

	public List<StrategyTypeDto> lists() {
		Response<List<StrategyTypeDto>> response = service.lists(true);
		if ("200".equals(response.getCode())) {
			List<StrategyTypeDto> result = new ArrayList<>();
			if (response.getResult() != null && response.getResult().size() > 0) {
				for (StrategyTypeDto type : response.getResult()) {
					if (type.getServiceFeePerWan().compareTo(new BigDecimal(0)) > 0) {
						result.add(type);
					}
				}
			}
			return result;
		}
		throw new ServiceException(response.getCode());
	}

	public StrategyTypeDto retriveExperienceStrategyType() {
		Response<List<StrategyTypeDto>> response = service.lists(true);
		if ("200".equals(response.getCode())) {
			StrategyTypeDto result = null;
			if (response.getResult() != null && response.getResult().size() > 0) {
				for (StrategyTypeDto type : response.getResult()) {
					if (type.getId().intValue() == 3) {
						result = type;
					}
				}
			}
			return result;
		}
		throw new ServiceException(response.getCode());
	}

	public Map<Long, Integer> strategyTypeMap() {
		Map<Long, Integer> result = new HashMap<Long, Integer>();
		List<StrategyTypeDto> list = this.lists();
		if (list != null && list.size() > 0) {
			for (StrategyTypeDto strategy : list) {
				result.put(strategy.getId(), strategy.getCycle());
			}
		}
		return result;
	}

}
