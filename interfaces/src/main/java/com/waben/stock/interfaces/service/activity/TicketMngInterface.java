package com.waben.stock.interfaces.service.activity;

import java.util.List;

import com.waben.stock.interfaces.dto.activity.ActivityDto;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.activity.TicketAmountDto;
import com.waben.stock.interfaces.pojo.Response;

/**
 * 
 * 
 * @author guowei 2018/4/11
 *
 */
public interface TicketMngInterface {
	
	/**
	 * 保存优惠券
	 * @param td
	 * @return
	 */
	@RequestMapping(value = "/saveTicketAmount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<TicketAmountDto> saveTicketAmount(@RequestBody TicketAmountDto td);
	
	/**
	 * 获取优惠券列表
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/getTicketAmountList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<TicketAmountDto>> getTicketAmountList(@RequestParam(value = "pageno") int pageno, @RequestParam(value = "pagesize") Integer pagesize);
	
	/**
	 * 删除优惠券
	 * @param ticketId
	 * @return
	 */
	@RequestMapping(value = "/deleteTicket/{ticketId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<Void> deleteTicket(@PathVariable("ticketId") long ticketId);

	@RequestMapping(value = "/getTicketAmount/{ticketAmountId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<TicketAmountDto> getTicketAmount(@PathVariable("ticketAmountId") long ticketAmountId);
}
