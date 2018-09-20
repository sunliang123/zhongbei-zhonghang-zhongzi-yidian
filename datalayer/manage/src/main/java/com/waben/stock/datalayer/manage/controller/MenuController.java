package com.waben.stock.datalayer.manage.controller;

import com.waben.stock.datalayer.manage.entity.Menu;
import com.waben.stock.datalayer.manage.service.MenuService;
import com.waben.stock.interfaces.dto.manage.MenuDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.manage.MenuInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/17.
 * @desc
 */
// @RestController
// @RequestMapping("/menu")
@Component
public class MenuController implements MenuInterface {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @Override
    public Response<List<MenuDto>> menusByRole(@PathVariable Long role) {
        List<Menu> menus = menuService.fetchMenusByRole(role);
        List<MenuDto> result = menus(CopyBeanUtils.copyListBeanPropertiesToList(menus, MenuDto.class), 0L);
        return new Response<>(result);
    }

    @Override
    public Response<List<MenuDto>> fetchMenusByVariety(@PathVariable Long variety) {
        List<Menu> result = menuService.findMenusByVariety(variety);
        List<MenuDto> response = CopyBeanUtils.copyListBeanPropertiesToList(result, MenuDto.class);
        return new Response<>(response);
    }

    @Override
    public Response<MenuDto> fetchById(@PathVariable Long id) {
        Menu result = menuService.findById(id);
        MenuDto response = CopyBeanUtils.copyBeanProperties(MenuDto.class,result, false);
        return new Response<>(response);
    }


    private List<MenuDto> menus(List<MenuDto> menuDtos, Long pid) {
        List<MenuDto> menus = new ArrayList<>();
        for (MenuDto menuDto : menuDtos) {
            if (menuDto.getPid().equals(pid)) {
//              若传过来的pid 为当前的id 则找到对应的父级并添加到子列表中
                menuDto.setChilds(menus(menuDtos, menuDto.getId()));
                menus.add(menuDto);
            }
        }
        return menus;
    }
}
