package com.waben.stock.interfaces.service.stockoption;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.stockoption.StockOptionQuoteDto;
import com.waben.stock.interfaces.pojo.Response;

public interface StockOptionQuoteInterface {

	@RequestMapping(value = "/quote/{publisherId}/{stockCode}/{cycle}", method = RequestMethod.GET)
	Response<StockOptionQuoteDto> quote(@PathVariable("publisherId") Long publisherId,
			@PathVariable("stockCode") String stockCode, @PathVariable("cycle") Integer cycle,
			@RequestParam(value = "nominalAmount", required = false) BigDecimal nominalAmount);

}
