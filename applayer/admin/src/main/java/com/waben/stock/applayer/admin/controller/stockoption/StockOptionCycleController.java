package com.waben.stock.applayer.admin.controller.stockoption;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.admin.business.stockoption.StockOptionCycleBusiness;
import com.waben.stock.interfaces.dto.stockoption.StockOptionCycleDto;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("adminStockOptionCycleController")
@RequestMapping("/stockoptioncycle")
@Api(description = "期权周期")
public class StockOptionCycleController {

    @Autowired
    private StockOptionCycleBusiness business;

    @GetMapping("/stockoptioncycles")
    @ApiOperation(value = "获取所有期权周期")
    public Response<List<StockOptionCycleDto>> fetchAll() {
        List<StockOptionCycleDto> response = business.findAll();
        return new Response<>(response);
    }

}
