package com.waben.stock.datalayer.activity.service;

import com.waben.stock.datalayer.activity.entity.ActivityPublisher;
import com.waben.stock.datalayer.activity.repository.ActivityPublisherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ActivityPublisherService {

    @Autowired
    private ActivityPublisherDao dao;

    public List<ActivityPublisher> getActivityPublishersByActivityId(long activityId) {
        return dao.getActivityPublishersByApId(activityId);
    }


    public ActivityPublisher getActivityPublisherByPublisherId(long publisherId) {
        return dao.getActivityPublisherByPublisherId(publisherId);
    }

    @Transactional
    public ActivityPublisher saveActivityPublisher(ActivityPublisher activityPublisher){
        return dao.saveActivityPublisher(activityPublisher);
    }
}
