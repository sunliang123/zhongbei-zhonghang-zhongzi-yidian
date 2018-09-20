package com.waben.stock.datalayer.manage.service.message.operator.juhe;

import com.waben.stock.datalayer.manage.service.message.MessageTemplate;
import com.waben.stock.datalayer.manage.service.message.ShortMessageService;
import com.waben.stock.datalayer.manage.service.message.template.juhe.JuheModifyPasswordTemplate;
import com.waben.stock.datalayer.manage.service.message.template.juhe.JuheNoticeTemplate;
import com.waben.stock.datalayer.manage.service.message.template.juhe.JuheRegisterTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by yuyidi on 2017/11/20.
 * @desc
 */
@Service
public class JuheShortMessage implements ShortMessageService {

    @Override
    public String register(String phone,String message) {
        JuheRegisterTemplate juheRegisterTemplate = new JuheRegisterTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("message", message);
        juheRegisterTemplate.variable(params);
        return send(juheRegisterTemplate);
    }

    @Override
    public String modifyPassword(String phone, String message) {
        JuheModifyPasswordTemplate juheModifyPasswordTemplate = new JuheModifyPasswordTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("message", message);
        juheModifyPasswordTemplate.variable(params);
        return send(juheModifyPasswordTemplate);
    }

    @Override
    public String notice(String phone, Date date, String activity) {
        JuheNoticeTemplate juheNoticeTemplate = new JuheNoticeTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("date", date.toString());
        params.put("activity", activity);
        juheNoticeTemplate.variable(params);
        return send(juheNoticeTemplate);
    }

    @Override
    public String send(MessageTemplate messageTemplate) {
        messageTemplate.code();
        Map<String, String> template = messageTemplate.template();
        return null;
    }
}
