package com.waben.stock.datalayer.organization.repository.impl.jpa;

import org.springframework.data.jpa.repository.Query;

import com.waben.stock.datalayer.organization.entity.SettlementMethod;

/**
 * 结算方式
 * 
 * @author sl
 *
 */
public interface SettlementMethodRepository extends CustomJpaRepository<SettlementMethod, Long> {

	@Query("select s from SettlementMethod s")
	SettlementMethod getMethodOne();
}
