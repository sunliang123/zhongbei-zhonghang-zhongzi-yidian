package com.waben.stock.interfaces.service.activity;

import com.waben.stock.interfaces.dto.activity.PublisherDeduTicketDto;
import com.waben.stock.interfaces.dto.activity.TicketAmountDto;
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
public interface PublisherDeduTicketInterface {


	@RequestMapping(value = "/savePublisherDeduTicket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PublisherDeduTicketDto> savePublisherDeduTicket(@RequestBody PublisherDeduTicketDto publisherDeduTicketDto);


	@RequestMapping(value = "/getPublisherDeduTicketList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<PublisherDeduTicketDto>> getPublisherDeduTicketList(@RequestParam(value = "pageno") int pageno, @RequestParam(value = "pagesize") Integer pagesize);


	@RequestMapping(value = "/getPublisherDeduTicket/{publisherDeduTicketId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<PublisherDeduTicketDto> getPublisherDeduTicket(@PathVariable("publisherDeduTicketId") long publisherDeduTicketId);

	@RequestMapping(value = "/getPublisherDeduTicketByApId/{apId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<List<PublisherDeduTicketDto>> getPublisherDeduTicketsByApId(@PathVariable("apId") long apId);
}
