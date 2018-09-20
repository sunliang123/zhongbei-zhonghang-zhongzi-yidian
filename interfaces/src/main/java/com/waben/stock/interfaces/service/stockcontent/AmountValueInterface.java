package com.waben.stock.interfaces.service.stockcontent;

import com.waben.stock.interfaces.dto.stockcontent.AmountValueDto;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.AmountValueQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface AmountValueInterface {

    @RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = "application/json")
    Response<PageInfo<AmountValueDto>> pagesByQuery(@RequestBody AmountValueQuery amountValueQuery);
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Response<AmountValueDto> fetchById(@PathVariable("id") Long id);

    @RequestMapping(value = "/modify", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<AmountValueDto> modify(@RequestBody AmountValueDto amountValueDto);
}
