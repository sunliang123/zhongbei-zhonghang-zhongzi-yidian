package com.waben.stock.datalayer.activity.repository;

import java.util.List;

import com.waben.stock.datalayer.activity.entity.Activity;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import org.springframework.data.domain.Page;

public interface ActivityDao  {
	
	void saveActivity(Activity a);
	
	Page<Activity> getActivityList(int pageno, int pagesize);
	
	Activity getActivity(long activityId);
	Activity getActivityByLocation(String location);
}
