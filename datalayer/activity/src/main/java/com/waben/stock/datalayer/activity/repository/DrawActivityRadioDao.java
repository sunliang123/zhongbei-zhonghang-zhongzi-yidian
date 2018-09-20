package com.waben.stock.datalayer.activity.repository;

import com.waben.stock.datalayer.activity.entity.DrawActivityRadio;

import java.util.List;

public interface DrawActivityRadioDao {

    List<DrawActivityRadio> getDrawActivityRadioByActivitysId(long activityId);

    DrawActivityRadio getDrawActivityRadioByTicketId(long ticketId);

}
