package com.waben.stock.datalayer.activity.controller;

import java.util.List;

import com.waben.stock.interfaces.pojo.query.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.waben.stock.datalayer.activity.service.ActivityMngService;
import com.waben.stock.interfaces.dto.activity.ActivityDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.activity.ActivityMngInterface;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author guowei 2018/4/11
 * update zengzhiwei 2018/4/11
 */
// @RestController
// @RequestMapping("/activity")
@Component
public class ActivityMngController implements ActivityMngInterface{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ActivityMngService ams;
	
	@Override
	public Response<ActivityDto> saveActivity(@RequestBody ActivityDto adto) {
		ActivityDto ad = ams.saveActivity(adto);
		return new Response<>(ad);
	}

	
	@Override
	public Response<PageInfo<ActivityDto>> getActivityList(int pageno, Integer pagesize) {
		return new Response<>(ams.getActivityList(pageno, pagesize));
	}

	@Override
	public Response<Void> setValid(@PathVariable long activityId) {
		ams.setValid(activityId);
		return new Response<>();
	}


	@Override
	public Response<ActivityDto> getActivity(@PathVariable long activityId) {
		return new Response<>(ams.getActivityById(activityId));
	}

	@Override
	public Response<ActivityDto> getActivityByLocation(@PathVariable String location) {
		return new Response<>(ams.getActivityByLocation(location));
	}


}
