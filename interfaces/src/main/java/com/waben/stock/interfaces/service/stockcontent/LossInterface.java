package com.waben.stock.interfaces.service.stockcontent;

import com.waben.stock.interfaces.dto.stockcontent.AmountValueDto;
import com.waben.stock.interfaces.dto.stockcontent.LossDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.LossQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface LossInterface {

    @RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = "application/json")
    Response<PageInfo<LossDto>> pagesByQuery(@RequestBody LossQuery lossQuery);
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Response<LossDto> fetchById(@PathVariable("id") Long id);

    @RequestMapping(value = "/modify", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<LossDto> modify(@RequestBody LossDto lossDto);

    @RequestMapping(value = "/save", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<LossDto> add(@RequestBody LossDto lossDto);

    @RequestMapping(value = "/")
    Response<List<LossDto>> fetchAllLoss();
}
