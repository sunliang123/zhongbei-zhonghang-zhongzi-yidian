package com.waben.stock.applayer.promotion.controller;

import com.waben.stock.applayer.promotion.business.MenuBusiness;
import com.waben.stock.interfaces.dto.manage.MenuDto;
import com.waben.stock.interfaces.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Created by yuyidi on 2018/5/10.
 * @desc
 */
@RestController("promotionMenuController")
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuBusiness menuBusiness;

    @GetMapping("/role/{role}")
    public Response<List<MenuDto>> fetchMenusByRole(@PathVariable Long role) {
        List<MenuDto> result = menuBusiness.menus(role);
        return new Response<>(result);
    }

}
