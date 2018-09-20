package com.waben.stock.interfaces.service.stockoption;

import com.waben.stock.interfaces.dto.stockoption.OfflineStockOptionTradeDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import com.waben.stock.interfaces.pojo.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

public interface OfflineStockOptionTradeInterface {
    @RequestMapping(value = "/add", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<OfflineStockOptionTradeDto> add(@RequestBody OfflineStockOptionTradeDto offlineStockOptionTradeDto);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Response<OfflineStockOptionTradeDto> find(@PathVariable("id") Long id);

    @RequestMapping(value = "/monthsProfit/{year}", method = RequestMethod.GET)
    Response<List<OfflineStockOptionTradeDto>> fetchMonthsProfit(@PathVariable("year") String year);

    @RequestMapping(value = "/settlement/{id}/{sellingPrice}", method = RequestMethod.PUT)
    Response<OfflineStockOptionTradeDto> settlement(@PathVariable("id") Long id, @PathVariable("sellingPrice") BigDecimal sellingPrice);

    @RequestMapping(value = "/exercise/{id}", method = RequestMethod.PUT)
    Response<OfflineStockOptionTradeDto> exercise(@PathVariable("id") Long id);
}
