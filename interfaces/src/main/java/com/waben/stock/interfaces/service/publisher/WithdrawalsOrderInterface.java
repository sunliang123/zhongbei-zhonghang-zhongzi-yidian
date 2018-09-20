package com.waben.stock.interfaces.service.publisher;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.publisher.WithdrawalsOrderDto;
import com.waben.stock.interfaces.pojo.Response;

public interface WithdrawalsOrderInterface {

	@RequestMapping(value = "/{withdrawalsNo}", method = RequestMethod.GET)
	Response<WithdrawalsOrderDto> fetchByWithdrawalsNo(@PathVariable("withdrawalsNo") String withdrawalsNo);

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<WithdrawalsOrderDto> addWithdrawalsOrder(@RequestBody WithdrawalsOrderDto withdrawalsOrderDto);

	@RequestMapping(value = "/{withdrawalsNo}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<WithdrawalsOrderDto> saveWithdrawalsOrders(@RequestBody WithdrawalsOrderDto withdrawalsOrderDto,@PathVariable("withdrawalsNo") String withdrawalsNo);
	
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<WithdrawalsOrderDto> modifyWithdrawalsOrder(@RequestBody WithdrawalsOrderDto withdrawalsOrderDto);

	@RequestMapping(value = "/{withdrawalsNo}", method = RequestMethod.PUT)
	public Response<WithdrawalsOrderDto> changeState(@PathVariable("withdrawalsNo") String withdrawalsNo,
			@RequestParam("stateIndex") String stateIndex);

}
