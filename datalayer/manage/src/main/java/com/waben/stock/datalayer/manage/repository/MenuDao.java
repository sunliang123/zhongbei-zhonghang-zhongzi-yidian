package com.waben.stock.datalayer.manage.repository;

import com.waben.stock.datalayer.manage.entity.Menu;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/17.
 * @desc
 */
public interface MenuDao extends BaseDao<Menu, Long> {

    List<Menu> retrieveByRole(Long role);

    List<Menu> retrieveAllByVariety(Long variety);
}
