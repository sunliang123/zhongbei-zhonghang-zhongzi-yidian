package com.waben.stock.interfaces.service.activity;

import com.waben.stock.interfaces.dto.activity.PublisherTeleChargeDto;
import com.waben.stock.interfaces.dto.activity.PublisherTicketDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * 
 * @author guowei 2018/4/11
 *
 */
public interface PublisherTicketInterface {

	/**
	 * 保存优惠券
	 * @param publisherTicketDto
	 * @return
	 */
	@RequestMapping(value = "/savePublisherTicket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PublisherTicketDto> savePublisherTicket(@RequestBody PublisherTicketDto publisherTicketDto);

	/**
	 * 获取优惠券列表
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/getPublisherTicketList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<PublisherTicketDto>> getPublisherTicketList(@RequestParam(value = "pageno") int pageno, @RequestParam(value = "pagesize") Integer pagesize);


	@RequestMapping(value = "/getPublisherTicket/{publisherTicketId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<PublisherTicketDto> getPublisherTicket(@PathVariable("publisherTicketId") long publisherTicketId);

	@RequestMapping(value = "/getPublisherTicketByApId/{apId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<List<PublisherTicketDto>> getPublisherTicketsByApId(@PathVariable("apId") long apId);
}
