package com.waben.stock.datalayer.activity.repository;

import com.waben.stock.datalayer.activity.entity.DrawActivity;

import java.util.List;

public interface DrawActivityDao {

    DrawActivity getDrawActivityByPublisherId(long publisherId);

    List<DrawActivity> getDrawActivitysByActivityId(long activityId);

    DrawActivity getDrawActivityByActivityIdAndPublisherId(long activityId, long publisherId);

    DrawActivity getDrawActicity(long id);

    List<DrawActivity> getDrawActicitys();
}
