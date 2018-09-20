package com.waben.stock.datalayer.activity.controller;

import java.util.List;

import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.waben.stock.datalayer.activity.service.TicketMngService;
import com.waben.stock.interfaces.dto.activity.TicketAmountDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.activity.TicketMngInterface;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author guowei 2018/4/11
 *
 */
// @RestController
// @RequestMapping("/ticketamount")
@Component
public class TicketMngController implements TicketMngInterface{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TicketMngService ts;
	
	@Override
	public Response<TicketAmountDto> saveTicketAmount(@RequestBody TicketAmountDto td) {
		return new Response<>(ts.saveTicket(td));
	}

	@Override
	public Response<PageInfo<TicketAmountDto>> getTicketAmountList(int pageno, Integer pagesize) {
		return new Response<>(ts.getTicketList(pageno, pagesize));
	}

	@Override
	public Response<Void> deleteTicket(@PathVariable long ticketId) {
		ts.deleteTicket(ticketId);
		return new Response<>();
	}

	@Override
	public Response<TicketAmountDto> getTicketAmount(@PathVariable long ticketAmountId) {
		TicketAmountDto ticketAmountDto = CopyBeanUtils.copyBeanProperties(TicketAmountDto.class, ts.getTicketAmount(ticketAmountId), false);
		return new Response<>(ticketAmountDto);
	}

}
