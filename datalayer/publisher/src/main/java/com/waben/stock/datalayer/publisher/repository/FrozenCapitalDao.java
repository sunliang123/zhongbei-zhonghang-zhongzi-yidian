package com.waben.stock.datalayer.publisher.repository;

import com.waben.stock.datalayer.publisher.entity.FrozenCapital;

/**
 * 冻结资金 Dao
 * 
 * @author luomengan
 *
 */
public interface FrozenCapitalDao extends BaseDao<FrozenCapital, Long> {

	FrozenCapital retriveByPublisherIdAndBuyRecordId(Long publisherId, Long buyRecordId);

	FrozenCapital retriveByPublisherIdAndWithdrawalsNo(Long publisherId, String withdrawalsNo);

}
