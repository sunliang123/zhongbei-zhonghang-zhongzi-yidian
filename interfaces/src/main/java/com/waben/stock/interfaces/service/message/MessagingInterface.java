package com.waben.stock.interfaces.service.message;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waben.stock.interfaces.dto.message.MessagingDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.MessagingQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;

/**
 * 
 * @author Created by hujian on 2018年1月4日
 *
 */
public interface MessagingInterface {

	/**
	 * 新增消息
	 * @param messagingDto
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<MessagingDto> addMessaging(@RequestBody MessagingDto messagingDto);
	
	/**
	 * 根据id删除消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{messagingId}/drop", method = RequestMethod.GET)
	public Response<Long> dropMessaging(@PathVariable("messagingId") Long messagingId);
	
	/**
	 * 修改消息
	 * @param messagingDto
	 * @return
	 */
	@RequestMapping(value="/modify", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<MessagingDto> modifyMessaging(@RequestBody MessagingDto messagingDto);
	
	/**
	 * 根据id获取消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{messagingId}/fetch", method = RequestMethod.GET)
	public Response<MessagingDto> fetchMessagingById(@PathVariable("messagingId") Long messagingId);
	
	/**
	 * 获取消息列表
	 * @param messagingQuery
	 * @return
	 */
	@RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<MessagingDto>> pages(@RequestBody MessagingQuery messagingQuery);
	
	/**
	 * 读消息
	 */
	@RequestMapping(value = "/{recipient}/read/{id}", method = RequestMethod.PUT)
	Response<MessagingDto> readMessage(@PathVariable("recipient") Long recipient, @PathVariable("id") Long id);
	
	/**
	 * 查询当前用户未产生回执的消息列表
	 * @param recipient
	 * @return
	 */
	@RequestMapping(value = "/{recipient}/pages", method = RequestMethod.GET)
	Response<List<MessagingDto>> fetchNotProduceReceiptAllByRecipient(@PathVariable("recipient") String recipient);
}
