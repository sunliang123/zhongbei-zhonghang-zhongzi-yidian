package com.waben.stock.datalayer.manage.repository.impl.jpa;

import com.waben.stock.datalayer.manage.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
public interface RoleRepository extends CustomJpaRepository<Role, Long> {

    Role findByOrganizationAndCode(Long organization, String admin);

//    @Query("select r from Role as r join r.staffs as staffs where staffs.id=:staffId")
//    List<Role> findAllByStaffId(@Param("staffId") Long staffId);

    List<Role> findRolesByOrganization(Long organization);
    
    @Query("select r from Role r where r.name = ?1")
    List<Role> findByRoleName(String name);

}
