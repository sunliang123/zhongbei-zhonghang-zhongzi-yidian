package com.waben.stock.datalayer.manage.controller;

import com.waben.stock.datalayer.manage.service.message.ShortMessageService;
import com.waben.stock.interfaces.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by yuyidi on 2017/11/20.
 * @desc
 */
// @RestController
// @RequestMapping("/message")
@Component
public class MessageController {

    @Autowired
    private ShortMessageService messageService;

    public Response<String> register(String phone,String message) {
        messageService.register(phone,message);
        return null;
    }


}
