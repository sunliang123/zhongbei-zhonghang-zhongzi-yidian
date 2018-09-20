package com.waben.stock.interfaces.service.inverstors;

import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.investor.InvestorDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.InvestorQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
public interface InvestorInterface {
    @RequestMapping(value = "/modify", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<Integer> modify(@RequestBody InvestorDto investorDto);

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    void delete(@PathVariable("id") Long id);

    @RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<PageInfo<InvestorDto>> pagesByQuery(@RequestBody InvestorQuery investorQuery);

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    Response<InvestorDto> fetchById(@PathVariable("id") Long id);

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    Response<InvestorDto> fetchByUserName(@PathVariable("username") String username);

    @RequestMapping(value = "/{investor}/buyrecord/applybuyin", method = RequestMethod.POST, consumes = MediaType
            .APPLICATION_JSON_VALUE)
    Response<BuyRecordDto> stockApplyBuyIn(@PathVariable("investor") Long investor, @RequestBody SecuritiesStockEntrust
            securitiesStockEntrust, @RequestParam("tradeSession") String tradeSession);

    @RequestMapping(value = "/volapplybuyin/{buyrecord}/", method = RequestMethod.POST, consumes = MediaType
            .APPLICATION_JSON_VALUE)
    Response<BuyRecordDto> voluntarilyStockApplyBuyIn(@PathVariable("buyrecord") Long buyrecord);

    @RequestMapping(value = "/{investor}/buyrecord/applysellout", method = RequestMethod.POST, consumes = MediaType
            .APPLICATION_JSON_VALUE)
    Response<BuyRecordDto> stockApplySellOut(@PathVariable("investor") Long investor, @RequestBody SecuritiesStockEntrust
            securitiesStockEntrust, @RequestParam("tradeSession") String tradeSession);
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    Response<List<InvestorDto>> fetchAllInvestors();
}
