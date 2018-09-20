package com.waben.stock.datalayer.activity.service;

import com.waben.stock.datalayer.activity.entity.Activity;
import com.waben.stock.datalayer.activity.entity.ActivityPublisher;
import com.waben.stock.datalayer.activity.entity.PublisherDeduTicket;
import com.waben.stock.datalayer.activity.entity.PublisherTeleCharge;
import com.waben.stock.datalayer.activity.repository.ActivityDao;
import com.waben.stock.datalayer.activity.repository.ActivityPublisherDao;
import com.waben.stock.datalayer.activity.repository.PublisherDeduTicketDao;
import com.waben.stock.interfaces.dto.activity.PublisherDeduTicketDto;
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
public class PublisherDeduTicketService {
    @Autowired
    private PublisherDeduTicketDao dao;

    @Autowired
    private ActivityPublisherDao activityPublisherDao;

    @Autowired
    private ActivityDao activityDao;
    public PageInfo<PublisherDeduTicketDto> getPublisherDeduTicketList(int pageno, Integer pagesize) {
        if(pagesize == null){
            PageAndSortQuery pq = new PageAndSortQuery();
            pagesize = pq.getSize();
        }
        Page<PublisherDeduTicket> page = dao.getPublisherDeduTicketList(pageno, pagesize);
        PageInfo<PublisherDeduTicketDto> pageInfo = PageToPageInfo.pageToPageInfo(page, PublisherDeduTicketDto.class);

        for(PublisherDeduTicketDto publisherDeduTicket : pageInfo.getContent()) {
            ActivityPublisher activityPublisher = activityPublisherDao.getActivityPublisher(publisherDeduTicket.getApId());
            Activity activity = activityDao.getActivity(activityPublisher.getActivityId());
            publisherDeduTicket.setActivityName(activity.getSubject());
        }

        return pageInfo;
    }

    @Transactional
    public PublisherDeduTicket savePublisherDeduTicket(PublisherDeduTicket publisherDeduTicket){

        return dao.savePublisherDeduTicket(publisherDeduTicket);
    }

    public PublisherDeduTicket getPublisherDeduTicket(long publisherDeduTicketId) {
        PublisherDeduTicket publisherDeduTicket = dao.getPublisherDeduTicket(publisherDeduTicketId);
        return publisherDeduTicket;
    }

    public List<PublisherDeduTicket> getPublisherDeduTicketsByApId(long apId) {
       return dao.getPublisherDeduTicketsByApId(apId);
    }

    @Transactional
    public PublisherDeduTicket setStatus(long id) {
        PublisherDeduTicket publisherDeduTicket = dao.getPublisherDeduTicket(id);
        if(publisherDeduTicket.getStatus()==1) {
            publisherDeduTicket.setStatus(2);
        }
        return publisherDeduTicket;
    }
}
