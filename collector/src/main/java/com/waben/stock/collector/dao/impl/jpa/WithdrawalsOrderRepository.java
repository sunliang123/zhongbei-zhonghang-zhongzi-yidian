package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.WithdrawalsOrder;

/**
 * 提现订单 Repository
 * 
 * @author luomengan
 *
 */
public interface WithdrawalsOrderRepository extends Repository<WithdrawalsOrder, Long> {

	WithdrawalsOrder save(WithdrawalsOrder withdrawalsOrder);

	void delete(Long id);

	Page<WithdrawalsOrder> findAll(Pageable pageable);
	
	List<WithdrawalsOrder> findAll();

	WithdrawalsOrder findById(Long id);

	WithdrawalsOrder findByDomainAndDataId(String domain, Long dataId);
	
}
