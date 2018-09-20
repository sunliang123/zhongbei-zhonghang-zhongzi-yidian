package com.waben.stock.datalayer.activity.service;

import com.waben.stock.datalayer.activity.entity.DrawActivityRadio;
import com.waben.stock.datalayer.activity.repository.DrawActivityRadioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrawActivityRadioService {
    @Autowired
    private DrawActivityRadioDao drawActivityRadioDao;

    public List<DrawActivityRadio> getDrawActivityRadioByActivitysId(long activityId) {
       return drawActivityRadioDao.getDrawActivityRadioByActivitysId(activityId);
    }

    public DrawActivityRadio getDrawActivityRadioByTicketId(long ticketId) {
        return drawActivityRadioDao.getDrawActivityRadioByTicketId(ticketId);
    }
}
