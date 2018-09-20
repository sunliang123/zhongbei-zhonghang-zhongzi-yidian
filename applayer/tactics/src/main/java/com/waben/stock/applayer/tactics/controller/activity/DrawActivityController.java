package com.waben.stock.applayer.tactics.controller.activity;

import com.waben.stock.applayer.tactics.business.DrawActivityBusiness;
import com.waben.stock.interfaces.dto.activity.TicketAmountDto;
import com.waben.stock.interfaces.pojo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("tacticsDrawActivityController")
@RequestMapping("/drawactivity")
@Api(description = "抽奖活动")
public class DrawActivityController {

    @Autowired
	private DrawActivityBusiness drawActivityBusiness;

	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "path", dataType = "long", name = "activityId", value = "活动id", required = true),
			@ApiImplicitParam(paramType = "path", dataType = "long", name = "publisherId", value = "发布人id", required = true) })
	@GetMapping("/draw/{activityId}/{publisherId}")
	@ApiOperation(value = "通过活动id和发布人id抽奖")
	public Response<TicketAmountDto> draw(@PathVariable long activityId, @PathVariable long publisherId) {
		TicketAmountDto result = drawActivityBusiness.draw(activityId, publisherId);
		return new Response<>(result);
	}

}
