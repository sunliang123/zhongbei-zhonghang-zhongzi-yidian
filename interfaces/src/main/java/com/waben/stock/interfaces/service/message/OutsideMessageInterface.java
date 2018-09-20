package com.waben.stock.interfaces.service.message;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.message.OutsideMessage;

public interface OutsideMessageInterface {

	/**
	 * 发送站外推送消息
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public Response<String> send(@RequestBody OutsideMessage message);

}
