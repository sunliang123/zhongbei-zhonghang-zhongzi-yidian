package com.waben.stock.interfaces.service.activity;

import com.waben.stock.interfaces.dto.activity.ActivityPublisherDto;
import com.waben.stock.interfaces.dto.activity.PublisherDeduTicketDto;
import com.waben.stock.interfaces.pojo.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * 
 * @author guowei 2018/4/11
 *
 */
public interface ActivityPublisherInterface {

	@RequestMapping(value = "/getActivityPublisherByActivityId/{activityId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<List<ActivityPublisherDto>> getActivityPublishersByActivityId(@PathVariable("activityId") long activityId);
}
