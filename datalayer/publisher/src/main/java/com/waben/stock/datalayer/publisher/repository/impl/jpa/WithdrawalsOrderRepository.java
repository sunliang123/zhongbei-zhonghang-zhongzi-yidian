package com.waben.stock.datalayer.publisher.repository.impl.jpa;

import com.waben.stock.datalayer.publisher.entity.WithdrawalsOrder;

/**
 * 提现订单 Jpa
 * 
 * @author luomengan
 *
 */
public interface WithdrawalsOrderRepository extends CustomJpaRepository<WithdrawalsOrder, Long> {

	WithdrawalsOrder findByWithdrawalsNo(String withdrawalsNo);

}
