package com.waben.stock.datalayer.activity.controller;

import com.waben.stock.datalayer.activity.entity.PublisherDeduTicket;
import com.waben.stock.datalayer.activity.entity.PublisherTeleCharge;
import com.waben.stock.datalayer.activity.service.PublisherDeduTicketService;
import com.waben.stock.interfaces.dto.activity.PublisherDeduTicketDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.activity.PublisherDeduTicketInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

// @RestController
// @RequestMapping("/publisherdeduticket")
@Component
public class PublisherDeduTicketController implements PublisherDeduTicketInterface{


    @Autowired
    private PublisherDeduTicketService publisherDeduTicketService;

    @Override
    public Response<PublisherDeduTicketDto> savePublisherDeduTicket(@RequestBody PublisherDeduTicketDto publisherDeduTicketDto) {
        PublisherDeduTicket publisherDeduTicket = CopyBeanUtils.copyBeanProperties(PublisherDeduTicket.class,publisherDeduTicketDto,false);
        PublisherDeduTicket result = publisherDeduTicketService.savePublisherDeduTicket(publisherDeduTicket);
        PublisherDeduTicketDto response = CopyBeanUtils.copyBeanProperties(PublisherDeduTicketDto.class, result, false);
        return new Response<>(response);
    }

    @Override
    public Response<PageInfo<PublisherDeduTicketDto>> getPublisherDeduTicketList(int pageno, Integer pagesize) {
        PageInfo<PublisherDeduTicketDto> result = publisherDeduTicketService.getPublisherDeduTicketList(pageno, pagesize);
        return new Response<>(result);
    }

    @Override
    public Response<PublisherDeduTicketDto> getPublisherDeduTicket(@PathVariable long publisherDeduTicketId) {
        PublisherDeduTicket result = publisherDeduTicketService.getPublisherDeduTicket(publisherDeduTicketId);
        PublisherDeduTicketDto publisherDeduTicketDto = CopyBeanUtils.copyBeanProperties(PublisherDeduTicketDto.class, result, false);
        return new Response<>(publisherDeduTicketDto);
    }

    @Override
    public Response<List<PublisherDeduTicketDto>> getPublisherDeduTicketsByApId(@PathVariable long apId) {
        List<PublisherDeduTicket> result = publisherDeduTicketService.getPublisherDeduTicketsByApId(apId);
        List<PublisherDeduTicketDto> publisherDeduTicketDto = CopyBeanUtils.copyListBeanPropertiesToList(result,PublisherDeduTicketDto.class);
        return new Response<>(publisherDeduTicketDto);
    }

}
