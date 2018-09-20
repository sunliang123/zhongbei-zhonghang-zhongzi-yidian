package com.waben.stock.datalayer.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.organization.service.CustomerService;
import com.waben.stock.interfaces.dto.organization.CustomerDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.CustomerQuery;
import com.waben.stock.interfaces.service.organization.CustomerInterface;
import com.waben.stock.interfaces.util.PageToPageInfo;

import io.swagger.annotations.Api;

/**
 * 客户 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/customer")
// @Api(description = "客户接口列表")
@Component
public class CustomerController implements CustomerInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public CustomerService customerService;

	@Override
	public Response<PageInfo<CustomerDto>> adminPage(@RequestBody CustomerQuery query) {
		Page<CustomerDto> page = customerService.pagesByQuery(query);
		PageInfo<CustomerDto> result = PageToPageInfo.pageToPageInfo(page, CustomerDto.class);
		return new Response<>(result);
	}

}
