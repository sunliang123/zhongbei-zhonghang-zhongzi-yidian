package com.waben.stock.applayer.tactics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.LiveplayerBusiness;
import com.waben.stock.applayer.tactics.dto.liveplayer.LiveplayerDto;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 直播
 * 
 * @author luomengan
 *
 */
@RestController("tacticsLiveplayerController")
@RequestMapping("/liveplayer")
@Api(description = "直播")
public class LiveplayerController {

	@Autowired
	private LiveplayerBusiness business;

	@GetMapping("/address")
	@ApiOperation(value = "获取直播、聊天室地址", notes = "range表示直播客户端类型，1web端，2非web端")
	public Response<LiveplayerDto> address(int clientType) {
		return new Response<>(business.address(SecurityUtil.getUserId(), clientType));
	}

}
