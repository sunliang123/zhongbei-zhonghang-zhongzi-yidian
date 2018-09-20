package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.WithdrawalsOrder;

/**
 * 提现订单 Dao
 * 
 * @author luomengan
 *
 */
public interface WithdrawalsOrderDao {

	public WithdrawalsOrder createWithdrawalsOrder(WithdrawalsOrder withdrawalsOrder);

	public void deleteWithdrawalsOrderById(Long id);

	public WithdrawalsOrder updateWithdrawalsOrder(WithdrawalsOrder withdrawalsOrder);

	public WithdrawalsOrder retrieveWithdrawalsOrderById(Long id);

	public Page<WithdrawalsOrder> pageWithdrawalsOrder(int page, int limit);
	
	public List<WithdrawalsOrder> listWithdrawalsOrder();
	
	public WithdrawalsOrder getByDomainAndDataId(String domain, Long dataId);

}
