package com.waben.stock.interfaces.service.organization;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waben.stock.interfaces.dto.organization.PromotionStockOptionTradeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.PromotionStockOptionTradeQuery;

public interface PromotionStockOptionTradeInterface {

	/**
	 * 获取推广渠道产生的期权交易分页数据
	 * 
	 * @param query
	 *            条件查询
	 * @return 期权交易分页数据
	 */
	@RequestMapping(value = "/adminPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<PromotionStockOptionTradeDto>> adminPage(@RequestBody PromotionStockOptionTradeQuery query);

}