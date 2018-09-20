package com.waben.stock.interfaces.service.stockcontent;

import com.waben.stock.interfaces.dto.stockcontent.StockExponentDto;
import com.waben.stock.interfaces.pojo.Response;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc
 */
public interface StockExponentInterface {

    @RequestMapping(value = "/")
    Response<List<StockExponentDto>> fetchStockExponents();
    
    @RequestMapping(value = "/exponent/{code}")
    Response<StockExponentDto> fetchStockExponent(@PathVariable("code") String code);

}
