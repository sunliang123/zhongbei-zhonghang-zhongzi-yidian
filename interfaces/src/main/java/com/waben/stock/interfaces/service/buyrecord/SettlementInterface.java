package com.waben.stock.interfaces.service.buyrecord;

import com.waben.stock.interfaces.dto.investor.InvestorDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waben.stock.interfaces.dto.buyrecord.SettlementDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.SettlementQuery;

public interface SettlementInterface {

	/**
	 * 分页查询结算记录
	 * 
	 * @param query
	 *            查询条件
	 * @return 结算记录
	 */
	@RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<SettlementDto>> pagesByQuery(@RequestBody SettlementQuery query);

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Response<SettlementDto> fetchByBuyRecord(@PathVariable("id")  Long id);
}
