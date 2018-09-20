package com.waben.stock.datalayer.activity.controller;

import com.waben.stock.datalayer.activity.entity.PublisherTicket;
import com.waben.stock.datalayer.activity.service.PublisherTicketService;
import com.waben.stock.interfaces.dto.activity.PublisherTicketDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.activity.PublisherTicketInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController
// @RequestMapping("/publisherticket")
@Component
public class PublisherTicketController implements PublisherTicketInterface{

    @Autowired
    private PublisherTicketService publisherTicketService;

    @Override
    public Response<PublisherTicketDto> savePublisherTicket(@RequestBody PublisherTicketDto publisherTicketDto) {
        PublisherTicket publisherTicket = CopyBeanUtils.copyBeanProperties(PublisherTicket.class,publisherTicketDto,false);
        PublisherTicket result = publisherTicketService.savePublisherTicket(publisherTicket);
        PublisherTicketDto response = CopyBeanUtils.copyBeanProperties(PublisherTicketDto.class, result, false);
        return new Response<>(response);
    }

    @Override
    public Response<PageInfo<PublisherTicketDto>> getPublisherTicketList(int pageno, Integer pagesize) {
        PageInfo<PublisherTicketDto> result = publisherTicketService.getPublisherTicketList(pageno, pagesize);
        return new Response<>(result);
    }

    @Override
    public Response<PublisherTicketDto> getPublisherTicket(@PathVariable long publisherTicketId) {
        PublisherTicket result = publisherTicketService.getPublisherTicket(publisherTicketId);
        PublisherTicketDto publisherTicketDto = CopyBeanUtils.copyBeanProperties(PublisherTicketDto.class, result, false);
        return new Response<>(publisherTicketDto);
    }

    @Override
    public Response<List<PublisherTicketDto>> getPublisherTicketsByApId(@PathVariable long apId) {
        List<PublisherTicket> result = publisherTicketService.getPublisherTicketsByApId(apId);
        List<PublisherTicketDto> publisherTicketDto = CopyBeanUtils.copyListBeanPropertiesToList(result,PublisherTicketDto.class);
        return new Response<>(publisherTicketDto);
    }
}
