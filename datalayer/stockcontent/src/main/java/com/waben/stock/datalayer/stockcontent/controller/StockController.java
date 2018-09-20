package com.waben.stock.datalayer.stockcontent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.stockcontent.entity.Stock;
import com.waben.stock.datalayer.stockcontent.entity.StockExponent;
import com.waben.stock.datalayer.stockcontent.service.StockService;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.dto.stockcontent.StockExponentDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StockQuery;
import com.waben.stock.interfaces.service.stockcontent.StockInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/***
 * @author yuyidi 2017-11-22 10:08:33
 * @class com.waben.stock.datalayer.stockcontent.controller.StockController
 * @description
 */
// @RestController
// @RequestMapping("/stock")
@Component
public class StockController implements StockInterface {

	@Autowired
	private StockService stockService;

	@GetMapping("/initStockAbbr")
	public Response<String> initStockAbbr() {
		stockService.initStockAbbr();
		return new Response<>();
	}

	@Override
	public Response<PageInfo<StockDto>> pagesByQuery(@RequestBody StockQuery staffQuery) {
		Page<Stock> stocks = stockService.pages(staffQuery);
		PageInfo<StockDto> result = new PageInfo<>(stocks, StockDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<StockDto> fetchById(@PathVariable Long id) {
		Stock stock = stockService.findById(id);
		StockDto stockDto = CopyBeanUtils.copyBeanProperties(StockDto.class, stock, false);
		StockExponentDto stockExponentDto = CopyBeanUtils.copyBeanProperties(StockExponentDto.class,
				stock.getExponent(), false);
		stockDto.setExponent(stockExponentDto);
		return new Response<>(stockDto);
	}

	@Override
	public Response<StockDto> fetchWithExponentByCode(@PathVariable String code) {
		Stock stock = stockService.findByCode(code);
		StockDto result = CopyBeanUtils.copyBeanProperties(StockDto.class, stock, false);
		if (stock.getExponent() != null) {
			result.setExponent(CopyBeanUtils.copyBeanProperties(StockExponentDto.class, stock.getExponent(), false));
		}
		return new Response<>(result);
	}

	@Override
	public Response<Integer> modify(@RequestBody StockDto stockDto) {
		Stock stock = CopyBeanUtils.copyBeanProperties(Stock.class, stockDto, false);
		Integer result = stockService.revision(stock);
		return new Response<>(result);
	}

	@Override
	public void delete(@PathVariable Long id) {
		stockService.delete(id);
	}

	@Override
	public Response<StockDto> add(@RequestBody StockDto stockDto) {
		Stock stock = CopyBeanUtils.copyBeanProperties(Stock.class, stockDto, false);
		stock.setExponent(CopyBeanUtils.copyBeanProperties(StockExponent.class, stockDto.getExponent(), false));
		StockDto result = CopyBeanUtils.copyBeanProperties(StockDto.class, stockService.save(stock), false);
		return new Response<>(result);
	}

	@Override
	public Response<StockDto> downline(@PathVariable String code, String stockOptionBlackRemark) {
		Stock stock = stockService.downline(code, stockOptionBlackRemark);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockDto.class, stock, false));
	}

	@Override
	public Response<StockDto> online(@PathVariable String code) {
		Stock stock = stockService.online(code);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockDto.class, stock, false));
	}

}
