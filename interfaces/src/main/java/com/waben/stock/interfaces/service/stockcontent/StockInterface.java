package com.waben.stock.interfaces.service.stockcontent;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StockQuery;

/***
 * @author yuyidi 2017-11-22 10:06:54
 * @class com.waben.stock.interfaces.service.stockcontent.StockInterface
 * @description
 */
public interface StockInterface {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Response<StockDto> fetchById(@PathVariable("id") Long id);

	@RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = "application/json")
	Response<PageInfo<StockDto>> pagesByQuery(@RequestBody StockQuery stockQuery);

	@RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
	Response<StockDto> fetchWithExponentByCode(@PathVariable("code") String code);

	@RequestMapping(value = "/modify", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<Integer> modify(@RequestBody StockDto stockDto);

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	void delete(@PathVariable("id") Long id);

	@RequestMapping(value = "/save", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<StockDto> add(StockDto requestDto);

	@RequestMapping(value = "/stockoption/downline/{code}", method = RequestMethod.PUT)
	Response<StockDto> downline(@PathVariable("code") String code,
			@RequestParam("stockOptionBlackRemark") String stockOptionBlackRemark);

	@RequestMapping(value = "/stockoption/online/{code}", method = RequestMethod.PUT)
	Response<StockDto> online(@PathVariable("code") String code);

}
