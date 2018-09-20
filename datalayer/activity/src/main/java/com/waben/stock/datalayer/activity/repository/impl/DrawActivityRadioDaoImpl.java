package com.waben.stock.datalayer.activity.repository.impl;

import com.waben.stock.datalayer.activity.entity.DrawActivityRadio;
import com.waben.stock.datalayer.activity.repository.DrawActivityRadioDao;
import com.waben.stock.datalayer.activity.repository.jpa.DrawActivityRadioRespository;
import com.waben.stock.datalayer.activity.service.DrawActivityRadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DrawActivityRadioDaoImpl implements DrawActivityRadioDao{

    @Autowired
    private DrawActivityRadioRespository respository;

    @Override
    public List<DrawActivityRadio> getDrawActivityRadioByActivitysId(long activityId) {
        return respository.findDrawActivityRadiosByActivityId(activityId);
    }

    @Override
    public DrawActivityRadio getDrawActivityRadioByTicketId(long ticketId) {
        return respository.findDrawActivityRadioByTicketId(ticketId);
    }
}
