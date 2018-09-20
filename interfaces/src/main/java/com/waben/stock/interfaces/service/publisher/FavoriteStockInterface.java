package com.waben.stock.interfaces.service.publisher;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.publisher.FavoriteStockDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;

public interface FavoriteStockInterface {

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<FavoriteStockDto> add(@RequestBody FavoriteStockDto favoriteStockDto);

	@RequestMapping(value = "/{publisherId}/{stockCodes}", method = RequestMethod.DELETE)
	Response<Void> drop(@PathVariable("publisherId") Long publisherId, @PathVariable("stockCodes") String stockCodes);

	@RequestMapping(value = "/{publisherId}/top/{stockCodes}", method = RequestMethod.PUT)
	Response<Void> top(@PathVariable("publisherId") Long publisherId, @PathVariable("stockCodes") String stockCodes);

	@RequestMapping(value = "/{publisherId}/lists", method = RequestMethod.GET)
	Response<List<FavoriteStockDto>> listsByPublisherId(@PathVariable("publisherId") Long publisherId);

	@RequestMapping(value = "/{publisherId}/pages", method = RequestMethod.GET)
	Response<PageInfo<FavoriteStockDto>> pagesByPublisherId(@PathVariable("publisherId") Long publisherId,
			@RequestParam(name = "page") int page, @RequestParam(name = "size") int size);

	@RequestMapping(value = "/{publisherId}/listsStockCode", method = RequestMethod.GET)
	Response<List<String>> listsStockCode(@PathVariable("publisherId") Long publisherId);

}