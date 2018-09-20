package com.waben.stock.interfaces.service.activity;

import com.waben.stock.interfaces.dto.activity.ActivityPublisherDto;
import com.waben.stock.interfaces.dto.activity.DrawActivityRadioDto;
import com.waben.stock.interfaces.pojo.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 
 * 
 * @author guowei 2018/4/11
 *
 */
public interface DrawActivityRadioInterface {

	@RequestMapping(value = "/getDrawActivityRadiosByActivityId/{activityId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<List<DrawActivityRadioDto>> getDrawActivityRadiosByActivityId(@PathVariable("activityId") long activityId);

	@RequestMapping(value = "/getDrawActivityRadioByTicketId/{ticketId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<DrawActivityRadioDto> getDrawActivityRadioByTicketId(@PathVariable("ticketId") long ticketId);
}
