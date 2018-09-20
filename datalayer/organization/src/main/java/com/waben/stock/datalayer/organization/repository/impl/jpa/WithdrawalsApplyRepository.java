package com.waben.stock.datalayer.organization.repository.impl.jpa;

import com.waben.stock.datalayer.organization.entity.WithdrawalsApply;

/**
 * 提现申请 Jpa
 * 
 * @author luomengan
 *
 */
public interface WithdrawalsApplyRepository extends CustomJpaRepository<WithdrawalsApply, Long> {

	WithdrawalsApply findByApplyNo(String applyNo);

}
