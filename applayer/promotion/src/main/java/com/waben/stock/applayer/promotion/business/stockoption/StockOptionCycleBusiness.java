package com.waben.stock.applayer.promotion.business.stockoption;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.stockoption.StockOptionCycleDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockoption.StockOptionCycleInterface;

@Service("promotionStockOptionCycleBusiness")
public class StockOptionCycleBusiness {

	@Autowired
	private StockOptionCycleInterface reference;

	public List<StockOptionCycleDto> findAll() {
		Response<List<StockOptionCycleDto>> response = reference.lists();
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionCycleDto fetchById(Long id) {
		Response<StockOptionCycleDto> response = reference.fetchById(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
