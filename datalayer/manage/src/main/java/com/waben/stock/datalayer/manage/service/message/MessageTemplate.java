package com.waben.stock.datalayer.manage.service.message;

import java.util.Map;

/**
 * @author Created by yuyidi on 2017/11/20.
 * @desc
 */
public interface MessageTemplate {
    //短信模板标识
    String code();
    //变量参数
    void variable(Map<String,String> param);

    Map<String, String> template();
}
