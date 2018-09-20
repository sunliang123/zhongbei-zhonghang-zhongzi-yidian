package com.waben.stock.datalayer.activity.controller;

import com.waben.stock.datalayer.activity.entity.ActivityPublisher;
import com.waben.stock.datalayer.activity.service.ActivityPublisherService;
import com.waben.stock.interfaces.dto.activity.ActivityPublisherDto;
import com.waben.stock.interfaces.dto.activity.PublisherDeduTicketDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.activity.ActivityPublisherInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController
// @RequestMapping("/activitypublisher")
@Component
public class ActivityPublisherController implements ActivityPublisherInterface {

    @Autowired
    private ActivityPublisherService activityPublisherService;

    @Override
    public Response<List<ActivityPublisherDto>> getActivityPublishersByActivityId(@PathVariable long activityId) {
        List<ActivityPublisher> result = activityPublisherService.getActivityPublishersByActivityId(activityId);
        List<ActivityPublisherDto> activityPublisherDto = CopyBeanUtils.copyListBeanPropertiesToList(result,ActivityPublisherDto.class);
        return new Response<>(activityPublisherDto);
    }
}
