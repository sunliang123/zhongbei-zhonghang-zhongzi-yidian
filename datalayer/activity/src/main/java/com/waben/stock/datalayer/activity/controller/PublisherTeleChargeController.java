package com.waben.stock.datalayer.activity.controller;

import com.waben.stock.datalayer.activity.entity.PublisherTeleCharge;
import com.waben.stock.datalayer.activity.service.PublisherTeleChargeService;
import com.waben.stock.interfaces.dto.activity.PublisherDeduTicketDto;
import com.waben.stock.interfaces.dto.activity.PublisherTeleChargeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.activity.PublisherTeleChargeInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController
// @RequestMapping("/publishertelecharge")
@Component
public class PublisherTeleChargeController implements PublisherTeleChargeInterface{

    @Autowired
    private PublisherTeleChargeService publisherTeleChargeService;

    @Override
    public Response<PublisherTeleChargeDto> savePublisherTeleCharge(@RequestBody PublisherTeleChargeDto publisherTeleChargeDto) {
        PublisherTeleCharge publisherTeleCharge = CopyBeanUtils.copyBeanProperties(PublisherTeleCharge.class,publisherTeleChargeDto,false);
        PublisherTeleCharge result = publisherTeleChargeService.savePublisherTeleCharge(publisherTeleCharge);
        PublisherTeleChargeDto response = CopyBeanUtils.copyBeanProperties(PublisherTeleChargeDto.class, result, false);
        return new Response<>(response);
    }

    @Override
    public Response<PageInfo<PublisherTeleChargeDto>> getPublisherTeleChargeList(int pageno, Integer pagesize) {
        PageInfo<PublisherTeleChargeDto> result = publisherTeleChargeService.getPublisherTeleChargeList(pageno, pagesize);
        return new Response<>(result);
    }

    @Override
    public Response<PublisherTeleChargeDto> getPublisherTeleCharge(@PathVariable long publisherTeleChargeId) {
        PublisherTeleCharge result = publisherTeleChargeService.getPublisherTeleCharge(publisherTeleChargeId);
        PublisherTeleChargeDto publisherTeleChargeDto = CopyBeanUtils.copyBeanProperties(PublisherTeleChargeDto.class, result, false);
        return new Response<>(publisherTeleChargeDto);
    }

    @Override
    public Response<Void> setPay(@PathVariable long publisherTeleChargeId) {
        publisherTeleChargeService.setPay(publisherTeleChargeId);
        return new Response<>();
    }

    @Override
    public Response<List<PublisherTeleChargeDto>> getPublisherTeleChargesByApId(@PathVariable long activityPublisherId) {
        List<PublisherTeleCharge> result = publisherTeleChargeService.getPublisherTeleChargesByApId(activityPublisherId);
        List<PublisherTeleChargeDto> publisherTeleChargeDto = CopyBeanUtils.copyListBeanPropertiesToList(result,PublisherTeleChargeDto.class);
        return new Response<>(publisherTeleChargeDto);
    }
}
