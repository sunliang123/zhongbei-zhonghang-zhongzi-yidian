package com.waben.stock.datalayer.message.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.waben.stock.datalayer.message.entity.MessageReceipt;
import com.waben.stock.datalayer.message.service.MessageReceiptService;
import com.waben.stock.datalayer.message.service.OutsideMessageService;
import com.waben.stock.interfaces.enums.MessageType;
import com.waben.stock.interfaces.pojo.message.OutsideMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.message.entity.Messaging;
import com.waben.stock.datalayer.message.service.MessagingService;
import com.waben.stock.interfaces.dto.message.MessagingDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.MessagingQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.message.MessagingInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * 
 * @author Created by hujian on 2018/1/3.
 *
 */
// @RestController
// @RequestMapping("/messaging")
@Component
public class MessagingController implements MessagingInterface{

	@Autowired
	private MessagingService messagingService;
	@Autowired
	private MessageReceiptService messageReceiptService;
	@Autowired
	private OutsideMessageService servcie;
	@Override
	public Response<MessagingDto> addMessaging(@RequestBody MessagingDto messagingDto) {

		Messaging messaging = CopyBeanUtils.copyBeanProperties(Messaging.class, messagingDto, false);
		Messaging resultMessaging = messagingService.save(messaging);
		if(messagingDto.getType().equals(MessageType.POIT)) {
			MessageReceipt messageReceipt = new MessageReceipt();
			messageReceipt.setMessage(resultMessaging);
			messageReceipt.setRecipient(messagingDto.getPublisherId().toString());
			messageReceipt.setState(false);
			MessageReceipt save = messageReceiptService.save(messageReceipt);
			System.out.println(save.getId());
		}
		if(messaging.getIsOutside()) {
			OutsideMessage outsideMessage = new OutsideMessage();
			outsideMessage.setPublisherId(messagingDto.getPublisherId());
			outsideMessage.setTitle(messaging.getTitle());
			outsideMessage.setContent(messaging.getContent());
			Map<String,String> map = new HashMap<>();
			map.put("publisherId",messagingDto.getPublisherId().toString());
			map.put("title",messaging.getTitle());
			map.put("content",messaging.getContent());
			if(messaging.getType().equals(MessageType.POIT)) {
				servcie.send(outsideMessage);
			}
		}
		return new Response<>(CopyBeanUtils.copyBeanProperties(MessagingDto.class,resultMessaging, false));
	}

	@Override
	public Response<Long> dropMessaging(@PathVariable("messagingId") Long messagingId) {
		return new Response<>(messagingService.remove(messagingId));
	}

	@Override
	public Response<MessagingDto> modifyMessaging(@RequestBody MessagingDto messagingDto) {
		Messaging messaging = CopyBeanUtils.copyBeanProperties(Messaging.class, messagingDto, false);
		Messaging resultMessaging = messagingService.revision(messaging);
		return new Response<>(CopyBeanUtils.copyBeanProperties(MessagingDto.class,resultMessaging, false));
	}

	@Override
	public Response<MessagingDto> fetchMessagingById(@PathVariable("messagingId") Long messagingId) {
		Messaging messaging = messagingService.findById(messagingId);
		return new Response<>(CopyBeanUtils.copyBeanProperties(MessagingDto.class, messaging,false));
	}

	@Override
	public Response<PageInfo<MessagingDto>> pages(@RequestBody MessagingQuery messagingQuery) {
		Page<Messaging> pages = messagingService.pages(messagingQuery);
		PageInfo<MessagingDto> result = new PageInfo<>(pages, MessagingDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<List<MessagingDto>> fetchNotProduceReceiptAllByRecipient(@PathVariable("recipient") String recipient) {
		List<Messaging> pages = messagingService.findNotProduceReceiptMessage(recipient);
		List<MessagingDto> result = CopyBeanUtils.copyListBeanPropertiesToList(pages,MessagingDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<MessagingDto> readMessage(@PathVariable Long recipient, @PathVariable Long id) {
		Messaging resultMessaging = messagingService.readMessage(String.valueOf(recipient), id);
		return new Response<>(CopyBeanUtils.copyBeanProperties(MessagingDto.class,resultMessaging, false));
	}
	
}
