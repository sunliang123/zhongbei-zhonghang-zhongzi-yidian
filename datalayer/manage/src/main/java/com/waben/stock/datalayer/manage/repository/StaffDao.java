package com.waben.stock.datalayer.manage.repository;

import com.waben.stock.datalayer.manage.entity.Staff;

/**
 * @author Created by yuyidi on 2017/11/15.
 * @desc
 */
public interface StaffDao extends BaseDao<Staff, Long> {

    Staff findByUserName(String userName);

    Integer updateById(Long id, String userName, Boolean state);
}
