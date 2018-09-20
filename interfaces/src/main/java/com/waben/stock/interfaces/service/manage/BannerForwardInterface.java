package com.waben.stock.interfaces.service.manage;

import com.waben.stock.interfaces.dto.manage.BannerDto;
import com.waben.stock.interfaces.dto.manage.BannerForwardDto;
import com.waben.stock.interfaces.pojo.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BannerForwardInterface {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    Response<List<BannerForwardDto>> fetchBannerForwards();
}
