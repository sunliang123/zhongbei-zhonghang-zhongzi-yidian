package com.waben.stock.datalayer.message.controller;

import com.waben.stock.datalayer.message.service.OutsideMessageService;
import com.waben.stock.interfaces.dto.message.MessagingDto;
import com.waben.stock.interfaces.pojo.message.OutsideMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.message.entity.MessageReceipt;
import com.waben.stock.datalayer.message.service.MessageReceiptService;
import com.waben.stock.interfaces.dto.message.MessageReceiptDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.MessageReceiptQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.message.MessageReceiptInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

import java.util.HashMap;
import java.util.Map;

// @RestController
// @RequestMapping("/messageReceipt")
@Component
public class MessageReceiptController implements MessageReceiptInterface{

	@Autowired
	private MessageReceiptService messageReceiptService;
	@Autowired
	private OutsideMessageService servcie;
	@Override
	public Response<MessageReceiptDto> addMessageReceipt(@RequestBody MessageReceiptDto messageReceiptDto) {
		MessageReceipt requestMessaging = CopyBeanUtils.copyBeanProperties(MessageReceipt.class, messageReceiptDto, false);
		MessageReceipt responseMessaging = messageReceiptService.save(requestMessaging);
		return new Response<MessageReceiptDto>(CopyBeanUtils.copyBeanProperties(MessageReceiptDto.class,responseMessaging, false));
	}

	@Override
	public Response<Long> dropMessageReceipt(@PathVariable("messageReceiptId") Long messageReceiptId) {
		return new Response<>(messageReceiptService.remove(messageReceiptId));
	}

	@Override
	public Response<MessageReceiptDto> modifyMessageReceipt(@RequestBody MessageReceiptDto messageReceiptDto) {
		MessageReceipt requestMessaging = CopyBeanUtils.copyBeanProperties(MessageReceipt.class, messageReceiptDto, false);
		MessageReceipt responseMessaging = messageReceiptService.revision(requestMessaging);
		return new Response<MessageReceiptDto>(CopyBeanUtils.copyBeanProperties(MessageReceiptDto.class,responseMessaging, false));
	}

	@Override
	public Response<MessageReceiptDto> fetchMessageReceiptById(@PathVariable("messageReceiptId") Long messageReceiptId) {
		MessageReceipt messageReceipt = messageReceiptService.findById(messageReceiptId);
		return new Response<>(CopyBeanUtils.copyBeanProperties(MessageReceiptDto.class, messageReceipt, false));
	}

	@Override
	public Response<PageInfo<MessageReceiptDto>> pages(@RequestBody MessageReceiptQuery messageReceiptQuery) {
		Page<MessageReceipt> pages = messageReceiptService.pages(messageReceiptQuery);
		PageInfo<MessageReceiptDto> result = new PageInfo<>(pages, MessageReceiptDto.class);
		return new Response<>(result);
	}

	
	
}
