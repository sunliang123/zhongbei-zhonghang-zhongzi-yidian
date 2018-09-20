package com.waben.stock.datalayer.activity.repository;

import com.waben.stock.datalayer.activity.entity.Activity;
import com.waben.stock.datalayer.activity.entity.ActivityPublisher;

import java.util.List;

public interface ActivityPublisherDao {

	ActivityPublisher saveActivityPublisher(ActivityPublisher a);

	List<ActivityPublisher> getActivityPublisherList(int pageno, int pagesize);

	ActivityPublisher getActivityPublisher(long activityPublisherId);

	List<ActivityPublisher> getActivityPublishersByApId(long activityId);

	ActivityPublisher getActivityPublisherByPublisherId(long publisherId);
}
