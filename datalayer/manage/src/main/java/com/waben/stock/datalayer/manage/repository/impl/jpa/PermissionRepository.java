package com.waben.stock.datalayer.manage.repository.impl.jpa;

import com.waben.stock.datalayer.manage.entity.Menu;
import com.waben.stock.datalayer.manage.entity.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/11.
 * @desc
 */
public interface PermissionRepository extends CustomJpaRepository<Permission,Long> {
    List<Permission> findAllByVariety(Long variety);

    @Query("select p from Permission as p join p.roles as r where r.id =:role order by p.id")
    List<Permission> findAllByRolesOrderById(@Param("role") Long role);
}
