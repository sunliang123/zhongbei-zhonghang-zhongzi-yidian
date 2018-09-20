package com.waben.stock.interfaces.service.stockoption;

import com.waben.stock.interfaces.dto.stockoption.StockOptionOrgDto;
import com.waben.stock.interfaces.pojo.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface StockOptionOrgInterface {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	Response<List<StockOptionOrgDto>> lists();

}
