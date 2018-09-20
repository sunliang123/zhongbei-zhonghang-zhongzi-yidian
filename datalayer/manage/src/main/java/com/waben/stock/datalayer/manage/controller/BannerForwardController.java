package com.waben.stock.datalayer.manage.controller;


import com.waben.stock.datalayer.manage.entity.BannerForward;
import com.waben.stock.datalayer.manage.service.BannerForwardService;
import com.waben.stock.interfaces.dto.manage.BannerForwardDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.manage.BannerForwardInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// @RestController
// @RequestMapping("/bannerforward")
@Component
public class BannerForwardController implements BannerForwardInterface{

    @Autowired
    private BannerForwardService service;

    @Override
    public Response<List<BannerForwardDto>> fetchBannerForwards() {
        List<BannerForward> result = service.findAll();
        List<BannerForwardDto> response = CopyBeanUtils.copyListBeanPropertiesToList(result, BannerForwardDto.class);
        return new Response<>(response);
    }
}
