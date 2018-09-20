package com.waben.stock.datalayer.stockoption.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.PublisherInterface;

@Service("stockoptionPublisherBusiness")
public class PublisherBusiness {

    @Autowired
    private PublisherInterface publisherReference;

    public PublisherDto findById(Long id) {
        Response<PublisherDto> response = publisherReference.fetchById(id);
        if ("200".equals(response.getCode())) {
            return response.getResult();
        }
        throw new ServiceException(response.getCode());
    }
}
