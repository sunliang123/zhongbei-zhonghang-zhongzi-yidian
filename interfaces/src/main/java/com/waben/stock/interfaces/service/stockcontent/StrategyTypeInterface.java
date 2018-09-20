package com.waben.stock.interfaces.service.stockcontent;

import java.util.List;

import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StrategyTypeQuery;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
public interface StrategyTypeInterface {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Response<StrategyTypeDto> fetchById(@PathVariable("id") Long id);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    Response<List<StrategyTypeDto>> lists(
            @RequestParam(value = "enable", required = false, defaultValue = "false") Boolean enable);

    @RequestMapping(value = "/pages", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<PageInfo<StrategyTypeDto>> pages(@RequestBody StrategyTypeQuery query);

    @RequestMapping(value = "/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<StrategyTypeDto> modify(@RequestBody StrategyTypeDto strategyTypeDto,@RequestParam("loss") List<Long> loss);

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    void delete(@PathVariable("id") Long id);
    @RequestMapping(value = "/save", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<StrategyTypeDto> add(StrategyTypeDto requestDto);
}
