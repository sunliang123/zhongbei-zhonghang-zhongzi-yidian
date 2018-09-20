package com.waben.stock.datalayer.publisher.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.publisher.entity.FavoriteStock;
import com.waben.stock.datalayer.publisher.service.FavoriteStockService;
import com.waben.stock.interfaces.dto.publisher.FavoriteStockDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.publisher.FavoriteStockInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;

/**
 * 收藏股票 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/favoriteStock")
@Component
public class FavoriteStockController implements FavoriteStockInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FavoriteStockService favoriteStockService;

	@Override
	public Response<FavoriteStockDto> add(@RequestBody FavoriteStockDto favoriteStockDto) {
		return new Response<>(
				CopyBeanUtils.copyBeanProperties(FavoriteStockDto.class,
						favoriteStockService
								.save(CopyBeanUtils.copyBeanProperties(FavoriteStock.class, favoriteStockDto, false)),
						false));
	}

	@Override
	public Response<Void> drop(@PathVariable Long publisherId, @PathVariable String stockCodes) {
		String[] stockCodeArr = stockCodes.split("-");
		favoriteStockService.remove(publisherId, stockCodeArr);
		return new Response<>();
	}

	@Override
	public Response<Void> top(@PathVariable Long publisherId, @PathVariable String stockCodes) {
		String[] stockCodeArr = stockCodes.split("-");
		favoriteStockService.top(publisherId, stockCodeArr);
		return new Response<>();
	}

	@Override
	public Response<List<FavoriteStockDto>> listsByPublisherId(@PathVariable Long publisherId) {
		return new Response<>(CopyBeanUtils.copyListBeanPropertiesToList(favoriteStockService.list(publisherId),
				FavoriteStockDto.class));
	}

	@Override
	public Response<List<String>> listsStockCode(@PathVariable Long publisherId) {
		return new Response<>(favoriteStockService.listStockCodeByPublisherId(publisherId));
	}

	@Override
	public Response<PageInfo<FavoriteStockDto>> pagesByPublisherId(@PathVariable Long publisherId, int page, int size) {
		Page<FavoriteStock> pageFavoriteStock = favoriteStockService.pagesByQuery(publisherId, page, size);
		PageInfo<FavoriteStockDto> result = PageToPageInfo.pageToPageInfo(pageFavoriteStock, FavoriteStockDto.class);
		return new Response<>(result);
	}

}
