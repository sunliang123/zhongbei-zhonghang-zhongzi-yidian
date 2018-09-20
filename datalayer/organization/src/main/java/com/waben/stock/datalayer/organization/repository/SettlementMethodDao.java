package com.waben.stock.datalayer.organization.repository;

import com.waben.stock.datalayer.organization.entity.SettlementMethod;

/**
 * 结算方式 DAO
 * 
 * @author sunliang
 *
 */
public interface SettlementMethodDao extends BaseDao<SettlementMethod, Long> {

	SettlementMethod getMethodOne(); 
}
