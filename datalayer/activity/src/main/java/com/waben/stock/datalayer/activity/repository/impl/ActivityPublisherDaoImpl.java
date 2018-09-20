package com.waben.stock.datalayer.activity.repository.impl;

import com.waben.stock.datalayer.activity.entity.ActivityPublisher;
import com.waben.stock.datalayer.activity.repository.ActivityPublisherDao;
import com.waben.stock.datalayer.activity.repository.jpa.ActivityPublisherRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityPublisherDaoImpl implements ActivityPublisherDao{

    @Autowired
    private ActivityPublisherRespository respository;

    @Override
    public ActivityPublisher saveActivityPublisher(ActivityPublisher activityPublisher) {
        return respository.save(activityPublisher);
    }

    @Override
    public List<ActivityPublisher> getActivityPublisherList(int pageno, int pagesize) {
        Pageable p = new PageRequest(pageno-1, pagesize);
        Page<ActivityPublisher> pt =  respository.findAll(p);
        return pt.getContent();
    }

    @Override
    public ActivityPublisher getActivityPublisher(long activityPublisherId) {
        return respository.findOne(activityPublisherId);
    }

    @Override
    public List<ActivityPublisher> getActivityPublishersByApId(long aId) {
        return respository.findActivityPublishersByApId(aId);
    }

    @Override
    public ActivityPublisher getActivityPublisherByPublisherId(long publisherId) {
        return respository.findActivityPublishersByPublisherId(publisherId);
    }
}
