package com.waben.stock.interfaces.service.message;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waben.stock.interfaces.dto.message.MessageReceiptDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.MessageReceiptQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;

/**
 * 
 * @author Created by hujian on 2018年1月7日
 */
public interface MessageReceiptInterface {

	/**
	 * 新增消息回执
	 * @param messagingDto
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<MessageReceiptDto> addMessageReceipt(@RequestBody MessageReceiptDto messageReceiptDto);
	
	/**
	 * 根据id删除消息回执
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{messageReceiptId}/drop", method = RequestMethod.GET)
	public Response<Long> dropMessageReceipt(@PathVariable("messageReceiptId") Long messageReceiptId);
	
	/**
	 * 修改消息回执
	 * @param messageReceiptDto
	 * @return
	 */
	@RequestMapping(value="/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<MessageReceiptDto> modifyMessageReceipt(@RequestBody MessageReceiptDto messageReceiptDto);
	
	/**
	 * 根据id获取消息回执
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{messageReceiptId}/fetch", method = RequestMethod.GET)
	public Response<MessageReceiptDto> fetchMessageReceiptById(@PathVariable("messageReceiptId") Long messageReceiptId);
	
	/**
	 * 获取消息回执列表
	 * @param messagingQuery
	 * @return
	 */
	@RequestMapping(value = "/pages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<MessageReceiptDto>> pages(@RequestBody MessageReceiptQuery messageReceiptQuery);
	
}
