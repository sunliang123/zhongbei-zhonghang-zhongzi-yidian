package com.waben.stock.datalayer.manage.service;

import com.waben.stock.datalayer.manage.entity.Menu;
import com.waben.stock.datalayer.manage.repository.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/17.
 * @desc
 */
@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    public List<Menu> fetchMenusByRole(Long role) {
        return menuDao.retrieveByRole(role);
    }

    public List<Menu> findMenusByVariety(Long variety) {
        return menuDao.retrieveAllByVariety(variety);
    }

    public Menu findById(Long id) {
        return menuDao.retrieve(id);
    }
}
