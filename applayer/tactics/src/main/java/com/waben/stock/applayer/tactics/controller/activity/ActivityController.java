package com.waben.stock.applayer.tactics.controller.activity;

import com.waben.stock.applayer.tactics.business.ActivityBusiness;
import com.waben.stock.interfaces.dto.activity.ActivityDto;
import com.waben.stock.interfaces.pojo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 活动接入
 * 
 * @author guowei
 *
 */
@RestController("tacticsActivityController")
@RequestMapping("/activity")
@Api(description = "活动")
public class ActivityController {

    @Autowired
    private ActivityBusiness activityBusiness;


    /**
     * 获取活动信息和中奖信息
     * @param activityId
     * @return
     */
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "path", dataType = "long", name = "activityId", value = "活动id", required = true) })
    @GetMapping("/{activityId}")
    @ApiOperation(value = "通过活动id获取活动信息和中奖信息")
    public Response<ActivityDto> fetchActivityById(@PathVariable long activityId) {
        ActivityDto activityDto = activityBusiness.findActivityById(activityId);
        return new Response<>(activityDto);
    }

}
