package com.waben.stock.datalayer.organization.repository.impl.jpa;

import com.waben.stock.datalayer.organization.entity.User;

/**
 * 机构管理用户 Jpa
 * 
 * @author luomengan
 *
 */
public interface UserRepository extends CustomJpaRepository<User, Long> {

    User findByUsername(String userName);
}
