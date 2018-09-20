package com.waben.stock.datalayer.manage.service.message.template.juhe;

import com.waben.stock.datalayer.manage.service.message.MessageTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by yuyidi on 2017/11/20.
 * @desc
 */
public class JuheRegisterTemplate implements MessageTemplate{

    Map<String, String> template = new HashMap<>();

    @Override
    public String code() {
        return "1001";
    }

    @Override
    public void variable(Map<String, String> param) {
        this.template = param;
    }

    @Override
    public Map<String, String> template() {
        return template;
    }
}
