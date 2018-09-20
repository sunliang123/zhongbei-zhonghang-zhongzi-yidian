package com.waben.stock.applayer.tactics.controller.activity;


import com.waben.stock.applayer.tactics.business.DrawActivityRadioBusiness;
import com.waben.stock.applayer.tactics.business.TicketAmountBusiness;
import com.waben.stock.interfaces.dto.activity.DrawActivityRadioDto;
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

import java.util.ArrayList;
import java.util.List;

@RestController("tacticsTicketAmountController")
@RequestMapping("/ticket")
@Api(description = "奖品")
public class TicketAmountController {

    @Autowired
    private DrawActivityRadioBusiness drawActivityRadioBusiness;

    @Autowired
    private TicketAmountBusiness ticketAmountBusiness;

    @ApiImplicitParams({ @ApiImplicitParam(paramType = "path", dataType = "long", name = "activityId", value = "活动id", required = true) })
    @GetMapping("/tickets/{activityId}")
    @ApiOperation(value = "通过活动id获取奖品列表")
    public Response<List<TicketAmountDto>> fetchTicketAmountsById(@PathVariable long activityId) {
        List<DrawActivityRadioDto> result = drawActivityRadioBusiness.getDrawActivityRadiosByActivityId(activityId);
        List<TicketAmountDto> ticketAmountDtos = new ArrayList<>();
        for(DrawActivityRadioDto drawActivityRadioDto : result) {
            TicketAmountDto ticketAmountDto = ticketAmountBusiness.findTicketAmountById(drawActivityRadioDto.getTicketId());
            ticketAmountDtos.add(ticketAmountDto);
        }
        return new Response<>(ticketAmountDtos);
    }
    
}
