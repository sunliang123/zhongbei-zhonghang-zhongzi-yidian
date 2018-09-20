package com.waben.stock.datalayer.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.message.service.SmsService;
import com.waben.stock.interfaces.enums.SmsType;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.message.SmsInterface;

/**
 * 短信消息 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/sms")
@Component
public class SmsController implements SmsInterface {

	@Autowired
	public SmsService smsService;

	@Override
	public Response<String> send(@RequestParam(required = true) String phone, @RequestParam(required = true) String type) {
		SmsType smsType = SmsType.getByIndex(type);
		smsService.sendMessage(smsType, phone);
		return new Response<>();
	}

}
