package com.waben.stock.datalayer.manage.service.message;

import java.util.Date;

/**
 * @author Created by yuyidi on 2017/11/20.
 * @desc  短信业务
 */
public interface ShortMessageService {

    String send(MessageTemplate messageTemplate);

    //注册验证
    String register(String phone,String message);

    //修改密码
    String modifyPassword(String phone,String message);

    //通知
    String notice(String phone, Date date,String activity);
}
