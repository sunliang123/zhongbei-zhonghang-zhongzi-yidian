package com.waben.stock.datalayer.activity.controller;


import com.waben.stock.datalayer.activity.entity.DrawActivity;
import com.waben.stock.datalayer.activity.entity.TicketAmount;
import com.waben.stock.datalayer.activity.service.DrawActivityService;
import com.waben.stock.interfaces.dto.activity.DrawActivityDto;
import com.waben.stock.interfaces.dto.activity.TicketAmountDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.activity.DrawActivityInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController
// @RequestMapping("drawactivity")
@Component
public class DrawActivityController implements DrawActivityInterface {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DrawActivityService drawActivityService;

    @Override
    public Response<TicketAmountDto> draw(@PathVariable long activityId, @PathVariable long publisherId) {
        TicketAmount result = drawActivityService.lotteryDraw(activityId, publisherId);
        TicketAmountDto response = CopyBeanUtils.copyBeanProperties(TicketAmountDto.class, result, false);
        return new Response<>(response);
    }

    @Override
    public Response<DrawActivityDto> getDrawActicity(@PathVariable long activityId,@PathVariable long publisherId) {
        DrawActivity result = drawActivityService.getDrawActicity(activityId,publisherId);
        DrawActivityDto response = CopyBeanUtils.copyBeanProperties(DrawActivityDto.class, result, false);
        return new Response<>(response);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void setRemaintime() {
        List<DrawActivity> drawActivities = drawActivityService.setRemaintime();
        logger.info("修改抽奖次数结果:{}", JacksonUtil.encode(drawActivities));
    }
}
