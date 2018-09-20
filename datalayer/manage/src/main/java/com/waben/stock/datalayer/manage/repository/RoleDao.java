package com.waben.stock.datalayer.manage.repository;

import com.waben.stock.datalayer.manage.entity.Role;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
public interface RoleDao extends BaseDao<Role, Long> {

    Role retrieveRoleAdminByOrganization(Long organization);

    List<Role> retrieveRolesByOrganization(Long organization);
    
    List<Role> findByRoleName(String name);
}
