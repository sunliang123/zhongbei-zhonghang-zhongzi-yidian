package com.waben.stock.datalayer.activity.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.activity.entity.Activity;
import com.waben.stock.datalayer.activity.repository.ActivityDao;
import com.waben.stock.datalayer.activity.repository.jpa.ActivityRespository;


@Repository
public class ActivityDaoImpl implements ActivityDao {

	@Autowired
	private ActivityRespository ar;
	
	@Override
	public void saveActivity(Activity a) {
		ar.save(a);
	}

	@Override
	public Page<Activity> getActivityList(int pageno,int pagesize) {
		Pageable p = new PageRequest(pageno-1, pagesize);
		Page<Activity> pt =  ar.findAll(p);
		return pt;
	}
	
	@Override
	public Activity getActivity(long activityId){
		return ar.findByActivityId(activityId);
	}

	@Override
	public Activity getActivityByLocation(String location){
		return ar.findActivitiesByLocation(location);
	}

}
