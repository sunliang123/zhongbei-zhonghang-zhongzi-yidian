package com.waben.stock.datalayer.manage.repository.impl.jpa;

import com.waben.stock.datalayer.manage.entity.Staff;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Created by yuyidi on 2017/11/15.
 * @desc
 */
public interface StaffRepository extends CustomJpaRepository<Staff, Long> {

    Staff findByUserName(String userName);
    @Transactional
    @Modifying
    @Query(value="update staff set state=?3, user_name=?2, update_time=now() where id=?1",nativeQuery = true)
    Integer revisionById(Long id, String userName, Boolean state);
}
