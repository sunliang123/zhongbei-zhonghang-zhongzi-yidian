package com.waben.stock.datalayer.manage.repository;

import com.waben.stock.datalayer.manage.entity.Permission;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/11.
 * @desc
 */
public interface PermissionDao extends BaseDao<Permission,Long> {

    List<Permission> retrieveAllByVariety(Long variety);
    List<Permission> retrieveAllByRole(Long role);
}
