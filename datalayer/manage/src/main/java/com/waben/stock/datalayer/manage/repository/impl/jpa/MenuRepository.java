package com.waben.stock.datalayer.manage.repository.impl.jpa;

import com.waben.stock.datalayer.manage.entity.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/17.
 * @desc
 */
public interface MenuRepository extends CustomJpaRepository<Menu, Long> {

    @Query("select m from Menu as m join m.roles as r where r.id =:role order by m.id")
    List<Menu> findAllByRolesOrderById(@Param("role") Long role);

    List<Menu> findAllByVariety(Long variety);

//    @Query("select m from Menu m join m.roles r join r.staffs staff where staff.id =:staff order by  m.id")
//    List<Menu> findAllByStaff(@Param("staff") Long staff);

//    List<Menu> findAllByPublisher(Long publisher);
}
