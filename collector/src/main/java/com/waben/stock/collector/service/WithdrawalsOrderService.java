package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.WithdrawalsOrderDao;
import com.waben.stock.collector.entity.WithdrawalsOrder;

/**
 * 提现订单 Service
 * 
 * @author luomengan
 *
 */
@Service
public class WithdrawalsOrderService {

	@Autowired
	private WithdrawalsOrderDao withdrawalsOrderDao;

	public WithdrawalsOrder getWithdrawalsOrderInfo(Long id) {
		return withdrawalsOrderDao.retrieveWithdrawalsOrderById(id);
	}

	@Transactional
	public WithdrawalsOrder addWithdrawalsOrder(WithdrawalsOrder withdrawalsOrder) {
		return withdrawalsOrderDao.createWithdrawalsOrder(withdrawalsOrder);
	}

	@Transactional
	public WithdrawalsOrder modifyWithdrawalsOrder(WithdrawalsOrder withdrawalsOrder) {
		return withdrawalsOrderDao.updateWithdrawalsOrder(withdrawalsOrder);
	}

	@Transactional
	public void deleteWithdrawalsOrder(Long id) {
		withdrawalsOrderDao.deleteWithdrawalsOrderById(id);
	}
	
	@Transactional
	public void deleteWithdrawalsOrders(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					withdrawalsOrderDao.deleteWithdrawalsOrderById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<WithdrawalsOrder> withdrawalsOrders(int page, int limit) {
		return withdrawalsOrderDao.pageWithdrawalsOrder(page, limit);
	}
	
	public List<WithdrawalsOrder> list() {
		return withdrawalsOrderDao.listWithdrawalsOrder();
	}

}
