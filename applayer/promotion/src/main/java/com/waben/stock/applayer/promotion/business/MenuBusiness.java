package com.waben.stock.applayer.promotion.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.manage.MenuDto;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.manage.MenuInterface;

/**
 * @author Created by yuyidi on 2018/3/14.
 * @desc
 */
@Service("promotionMenuBusiness")
public class MenuBusiness {

    @Autowired
    private MenuInterface menuReference;

    public List<MenuDto> menus(Long role) {
        Response<List<MenuDto>> response = menuReference.menusByRole(role);
        String code = response.getCode();
        if ("200".equals(code)) {
//            return new ArrayList<>(childsMenu(response.getResult()));
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public List<MenuDto> findMenusByVariety(Long variety) {
        Response<List<MenuDto>> response = menuReference.fetchMenusByVariety(variety);
        String code = response.getCode();
        if ("200".equals(code)) {
            childsMenu(response.getResult());
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public Collection<MenuDto> childsMenu(Collection<MenuDto> menuDtos) {
        Iterator<MenuDto> iterator = menuDtos.iterator();
        while(iterator.hasNext()){
            MenuDto menuDto = iterator.next();
            if(menuDto.getPid()==0) {
                List<MenuDto> childMenus = new ArrayList();
                Iterator<MenuDto> iteratorChild = menuDtos.iterator();
                while(iteratorChild.hasNext()){
                    MenuDto menu = iteratorChild.next();
                    if(menu.getPid().longValue()==menuDto.getId().longValue()) {
                        childMenus.add(menu);
                    }
                }
                menuDto.setChilds(childMenus);
            }
        }

        Iterator<MenuDto> iteratorRemove = menuDtos.iterator();
        while(iteratorRemove.hasNext()){
            MenuDto menuDto = iteratorRemove.next();
            if(menuDto.getPid()!=0) {
                iteratorRemove.remove();
            }
        }
        return menuDtos;
    }

    public MenuDto findById(Long id) {
        Response<MenuDto> response = menuReference.fetchById(id);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }
}
