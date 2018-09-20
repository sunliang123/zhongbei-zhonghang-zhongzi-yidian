package com.waben.stock.datalayer.activity.repository.jpa;

import com.waben.stock.datalayer.activity.entity.DrawActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DrawActivityRespository extends JpaRepository<DrawActivity, Long>{


    DrawActivity findDrawActivityByPublisherId(long activityId);

    DrawActivity findDrawActivityByActivityIdAndPublisherId(long activityId,long publisherId);

    List<DrawActivity> findDrawActivitysByActivityId(long activityId);

}
