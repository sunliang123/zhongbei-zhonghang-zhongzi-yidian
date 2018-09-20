package com.waben.stock.datalayer.activity.repository.impl;

import com.waben.stock.datalayer.activity.entity.DrawActivity;
import com.waben.stock.datalayer.activity.repository.DrawActivityDao;
import com.waben.stock.datalayer.activity.repository.jpa.DrawActivityRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DrawActivityDaoImpl implements DrawActivityDao {

    @Autowired
    private DrawActivityRespository respository;


    @Override
    public DrawActivity getDrawActivityByPublisherId(long publisherId) {
        return respository.findDrawActivityByPublisherId(publisherId);
    }


    @Override
    public List<DrawActivity> getDrawActivitysByActivityId(long activityId) {
        return respository.findDrawActivitysByActivityId(activityId);
    }

    @Override
    public DrawActivity getDrawActivityByActivityIdAndPublisherId(long activityId, long publisherId) {
        return respository.findDrawActivityByActivityIdAndPublisherId(activityId,publisherId);
    }

    @Override
    public DrawActivity getDrawActicity(long id) {
        return respository.getOne(id);
    }

    @Override
    public List<DrawActivity> getDrawActicitys() {
        return respository.findAll();
    }
}
