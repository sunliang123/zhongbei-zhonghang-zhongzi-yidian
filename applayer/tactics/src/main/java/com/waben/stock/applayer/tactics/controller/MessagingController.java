package com.waben.stock.applayer.tactics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.MessagingBusiness;
import com.waben.stock.applayer.tactics.dto.message.MessagingRemindDto;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.interfaces.dto.message.MessagingDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.MessagingQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 消息 Controller
 * 
 * @author luomengan
 *
 */
@RestController("tacticsMessagingController")
@RequestMapping("/messaging")
@Api(description = "消息中心")
public class MessagingController {

	@Autowired
	private MessagingBusiness business;

	@GetMapping("/pages")
	@ApiOperation(value = "获取消息列表", notes = "type(1:站内消息,2:站外消息)")
	public Response<PageInfo<MessagingDto>> pages(int page, int size, @RequestParam(defaultValue = "1") int type) {
		MessagingQuery query = new MessagingQuery();
		query.setPage(page);
		query.setSize(size);
		query.setPublisherId(SecurityUtil.getUserId());
		if (type == 1) {
			query.setIsOutside(false);
		} else {
			query.setIsOutside(true);
		}
		return new Response<>(business.pages(query));
	}
	
	@GetMapping("/remind")
	@ApiOperation(value = "获取是否有未读的消息")
	public Response<MessagingRemindDto> remind() {
		MessagingRemindDto result = new MessagingRemindDto();
		MessagingQuery query = new MessagingQuery();
		query.setPublisherId(SecurityUtil.getUserId());
		query.setIsOutside(true);
		query.setHasRead(false);
		PageInfo<MessagingDto> pages = business.pages(query);
		if(pages.getTotalElements() > 0) {
			result.setHasOutsideMessage(true);
		}
		
		query.setIsOutside(false);
		pages = business.pages(query);
		if(pages.getTotalElements() > 0) {
			result.setHasInsideMessage(true);
		}
		return new Response<>(result);
	}
	
	@PostMapping("/read/{id}")
	@ApiOperation(value = "用户标记已读某条消息")
	public Response<MessagingDto> readMessage(@PathVariable Long id) {
		return new Response<>(business.readMessage(SecurityUtil.getUserId(), id));
	}

}
