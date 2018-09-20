package com.waben.stock.interfaces.service.activity;

import java.util.List;

import com.waben.stock.interfaces.pojo.query.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.activity.ActivityDto;
import com.waben.stock.interfaces.pojo.Response;

/**
 * 
 * 
 * @author guowei 2018/4/10
 * update zengzhiwei 2018/4/11
 */
public interface ActivityMngInterface {
	
	/**
	 * 保存活动
	 * @param adto
	 * @return
	 */
	@RequestMapping(value = "/saveActivity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<ActivityDto> saveActivity(@RequestBody ActivityDto adto);
	
	/**
	 * 获取活动列表
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/getActivityList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<ActivityDto>> getActivityList(@RequestParam(value = "pageno") int pageno, @RequestParam(value = "pagesize") Integer pagesize);
	
	/**
	 * 设置活动生效状态
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value = "/setValid/{activityId}", method = RequestMethod.POST)
	Response<Void> setValid(@PathVariable("activityId") long activityId);
	
	
	/**
	 * 根据Id获取活动信息
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value = "/getActivity/{activityId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<ActivityDto> getActivity(@PathVariable("activityId") long activityId);

	/**
	 * 根据location获取活动信息
	 * @param location
	 * @return
	 */
	@RequestMapping(value = "/location/{location}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<ActivityDto> getActivityByLocation(@PathVariable("location") String location);
}
