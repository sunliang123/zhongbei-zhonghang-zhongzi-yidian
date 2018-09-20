package com.waben.stock.datalayer.activity.controller;

import com.waben.stock.datalayer.activity.entity.DrawActivityRadio;
import com.waben.stock.datalayer.activity.service.DrawActivityRadioService;
import com.waben.stock.interfaces.dto.activity.DrawActivityRadioDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.activity.DrawActivityRadioInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController
// @RequestMapping("/drawactivityradio")
@Component
public class DrawActivityRadioController implements DrawActivityRadioInterface {

    @Autowired
    private DrawActivityRadioService drawActivityRadioService;


    @Override
    public Response<List<DrawActivityRadioDto>> getDrawActivityRadiosByActivityId(@PathVariable long activityId) {
        List<DrawActivityRadio> result = drawActivityRadioService.getDrawActivityRadioByActivitysId(activityId);
        List<DrawActivityRadioDto> response = CopyBeanUtils.copyListBeanPropertiesToList(result, DrawActivityRadioDto.class);
        return new Response<>(response);
    }

    @Override
    public Response<DrawActivityRadioDto> getDrawActivityRadioByTicketId(@PathVariable long ticketId) {
        DrawActivityRadio result = drawActivityRadioService.getDrawActivityRadioByTicketId(ticketId);
        DrawActivityRadioDto response = CopyBeanUtils.copyBeanProperties(DrawActivityRadioDto.class, result, false);
        return new Response<>(response);
    }
}
