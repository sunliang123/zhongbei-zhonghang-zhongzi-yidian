package com.waben.stock.applayer.tactics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.PromotionBusiness;
import com.waben.stock.applayer.tactics.dto.publisher.PromotionBaseDto;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 推广 Controller
 * 
 * @author luomengan
 *
 */
@RestController("tacticsPromotionController")
@RequestMapping("/promotion")
@Api(description = "推广")
public class PromotionController {

	@Autowired
	private PromotionBusiness promotionBusiness;

	@GetMapping("/promotion/base")
	@ApiOperation(value = "发布人推广基本信息")
	public Response<PromotionBaseDto> fetchPromotionBase() {
		return new Response<>(promotionBusiness.getPromotionBase(SecurityUtil.getUserId()));
	}

	@GetMapping("/promotion/userpages")
	@ApiOperation(value = "已推广的用户列表")
	public Response<PageInfo<PublisherDto>> pagePromotionUser(int page, int size) {
		PageInfo<PublisherDto> result = promotionBusiness.pagePromotionUser(SecurityUtil.getUserId(), page, size);
		List<PublisherDto> content = result.getContent();
		if (content != null && content.size() > 0) {
			for (PublisherDto publisher : content) {
				publisher.setPhone(publisher.getPhone().substring(0, 3) + "****" + publisher.getPhone().substring(7));
				publisher.setPassword(null);
			}
		}
		return new Response<>(result);
	}

}
