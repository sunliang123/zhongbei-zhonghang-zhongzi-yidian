package com.waben.stock.datalayer.organization.repository;

import com.waben.stock.datalayer.organization.entity.WithdrawalsApply;

/**
 * 提现申请 Dao
 * 
 * @author luomengan
 *
 */
public interface WithdrawalsApplyDao extends BaseDao<WithdrawalsApply, Long> {

	WithdrawalsApply retrieveByApplyNo(String applyNo);

}
