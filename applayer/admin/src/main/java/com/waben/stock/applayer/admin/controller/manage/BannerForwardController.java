package com.waben.stock.applayer.admin.controller.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.admin.business.manage.BannerForwardBusiness;
import com.waben.stock.interfaces.dto.manage.BannerForwardDto;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("adminBannerForwardController")
@RequestMapping("/bannerforward")
@Api(description="轮播图跳转对象")
public class BannerForwardController {

    @Autowired
    private BannerForwardBusiness business;

    @GetMapping("/")
    @ApiOperation(value = "获取所有轮播图跳转对象")
    public Response<List<BannerForwardDto>> fetchAll() {
        List<BannerForwardDto> response = business.findAll();
        return new Response<>(response);
    }

}
