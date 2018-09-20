package com.waben.stock.interfaces.service.manage;

import com.waben.stock.interfaces.dto.manage.MenuDto;
import com.waben.stock.interfaces.pojo.Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/17.
 * @desc
 */
public interface MenuInterface {

    @RequestMapping(value = "/role/{role}", method = RequestMethod.GET)
    Response<List<MenuDto>> menusByRole(@PathVariable("role") Long role);

    @RequestMapping(value = "/variety/{variety}", method = RequestMethod.GET)
    Response<List<MenuDto>> fetchMenusByVariety(@PathVariable("variety") Long variety);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Response<MenuDto> fetchById(@PathVariable("id") Long id);
}
