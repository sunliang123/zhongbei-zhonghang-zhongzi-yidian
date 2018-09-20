package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.WithdrawalsOrderDao;
import com.waben.stock.collector.dao.impl.jpa.WithdrawalsOrderRepository;
import com.waben.stock.collector.entity.WithdrawalsOrder;

/**
 * 提现订单 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class WithdrawalsOrderDaoImpl implements WithdrawalsOrderDao {

	@Autowired
	private WithdrawalsOrderRepository withdrawalsOrderRepository;

	@Override
	public WithdrawalsOrder createWithdrawalsOrder(WithdrawalsOrder withdrawalsOrder) {
		return withdrawalsOrderRepository.save(withdrawalsOrder);
	}

	@Override
	public void deleteWithdrawalsOrderById(Long id) {
		withdrawalsOrderRepository.delete(id);
	}

	@Override
	public WithdrawalsOrder updateWithdrawalsOrder(WithdrawalsOrder withdrawalsOrder) {
		return withdrawalsOrderRepository.save(withdrawalsOrder);
	}

	@Override
	public WithdrawalsOrder retrieveWithdrawalsOrderById(Long id) {
		return withdrawalsOrderRepository.findById(id);
	}

	@Override
	public Page<WithdrawalsOrder> pageWithdrawalsOrder(int page, int limit) {
		return withdrawalsOrderRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<WithdrawalsOrder> listWithdrawalsOrder() {
		return withdrawalsOrderRepository.findAll();
	}

	@Override
	public WithdrawalsOrder getByDomainAndDataId(String domain, Long dataId) {
		return withdrawalsOrderRepository.findByDomainAndDataId(domain, dataId);
	}

}
