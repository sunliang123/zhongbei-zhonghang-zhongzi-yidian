package com.waben.stock.datalayer.activity.service;

import com.waben.stock.datalayer.activity.entity.Activity;
import com.waben.stock.datalayer.activity.entity.ActivityPublisher;
import com.waben.stock.datalayer.activity.entity.PublisherTeleCharge;
import com.waben.stock.datalayer.activity.repository.ActivityDao;
import com.waben.stock.datalayer.activity.repository.ActivityPublisherDao;
import com.waben.stock.datalayer.activity.repository.PublisherTeleChargeDao;
import com.waben.stock.interfaces.dto.activity.PublisherDeduTicketDto;
import com.waben.stock.interfaces.dto.activity.PublisherTeleChargeDto;
import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherTeleChargeService {
    @Autowired
    private PublisherTeleChargeDao dao;
    @Autowired
    private ActivityPublisherDao activityPublisherDao;

    @Autowired
    private ActivityDao activityDao;
    public PageInfo<PublisherTeleChargeDto> getPublisherTeleChargeList(int pageno, Integer pagesize) {
        if(pagesize == null){
            PageAndSortQuery pq = new PageAndSortQuery();
            pagesize = pq.getSize();
        }
        Page<PublisherTeleCharge> page = dao.getPublisherTeleChargeList(pageno, pagesize);
        PageInfo<PublisherTeleChargeDto> pageInfo = PageToPageInfo.pageToPageInfo(page,PublisherTeleChargeDto.class);

        for(PublisherTeleChargeDto publisherTeleCharge : pageInfo.getContent()) {
            ActivityPublisher activityPublisher = activityPublisherDao.getActivityPublisher(publisherTeleCharge.getApId());
            Activity activity = activityDao.getActivity(activityPublisher.getActivityId());
            publisherTeleCharge.setActivityName(activity.getSubject());
        }
        return pageInfo;
    }

    @Transactional
    public PublisherTeleCharge savePublisherTeleCharge(PublisherTeleCharge publisherTeleCharge){
        return dao.savePublisherTeleCharge(publisherTeleCharge);
    }

    public PublisherTeleCharge getPublisherTeleCharge(long publisherTeleChargeId) {
        PublisherTeleCharge publisherTeleCharge = dao.getPublisherTeleCharge(publisherTeleChargeId);
        return publisherTeleCharge;
    }

    @Transactional
    public void setPay(long publisherTeleChargeId) {
        PublisherTeleCharge publisherTeleCharge = dao.getPublisherTeleCharge(publisherTeleChargeId);
        if(!publisherTeleCharge.isIspay()) {
            publisherTeleCharge.setIspay(!publisherTeleCharge.isIspay());
        }
    }

    public List<PublisherTeleCharge> getPublisherTeleChargesByApId(long apId) {
        return dao.getPublisherTeleChargesByApId(apId);
    }

    @Transactional
    public PublisherTeleCharge setStatus(long id) {
        PublisherTeleCharge publisherTeleCharge = dao.getPublisherTeleCharge(id);
        if(publisherTeleCharge.getStatus()==1) {
            publisherTeleCharge.setStatus(2);
        }
        return publisherTeleCharge;
    }
}
