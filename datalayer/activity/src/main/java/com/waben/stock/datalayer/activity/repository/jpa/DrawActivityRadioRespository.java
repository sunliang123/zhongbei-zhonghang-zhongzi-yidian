package com.waben.stock.datalayer.activity.repository.jpa;

import com.waben.stock.datalayer.activity.entity.DrawActivity;
import com.waben.stock.datalayer.activity.entity.DrawActivityRadio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DrawActivityRadioRespository extends JpaRepository<DrawActivityRadio, Long>{

    List<DrawActivityRadio> findDrawActivityRadiosByActivityId(long activityId);

    DrawActivityRadio findDrawActivityRadioByTicketId(long ticketId);
}
