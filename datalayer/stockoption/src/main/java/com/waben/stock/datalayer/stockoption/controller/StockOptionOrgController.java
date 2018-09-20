package com.waben.stock.datalayer.stockoption.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.stockoption.entity.StockOptionOrg;
import com.waben.stock.datalayer.stockoption.service.StockOptionOrgService;
import com.waben.stock.interfaces.dto.stockoption.StockOptionOrgDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockoption.StockOptionOrgInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

// @RestController
// @RequestMapping("/stockoptionorg")
@Component
public class StockOptionOrgController implements StockOptionOrgInterface {
	
	@Autowired
	private StockOptionOrgService stockOptionOrgService;

	@Override
	public Response<List<StockOptionOrgDto>> lists() {
		List<StockOptionOrg> StockOptionOrgs = stockOptionOrgService.lists();
		List<StockOptionOrgDto> result = CopyBeanUtils.copyListBeanPropertiesToList(StockOptionOrgs,
				StockOptionOrgDto.class);
		return new Response<>(result);
	}
}
