package com.waben.stock.interfaces.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Created by yuyidi on 2017/11/13.
 * @desc
 */
public interface EchoInterface {

    @RequestMapping(value = "/echo",method = RequestMethod.GET)
    String echo();
}
