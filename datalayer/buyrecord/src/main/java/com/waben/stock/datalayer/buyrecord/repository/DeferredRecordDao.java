package com.waben.stock.datalayer.buyrecord.repository;

import java.util.List;

import com.waben.stock.datalayer.buyrecord.entity.DeferredRecord;

/**
 * 递延记录 Dao
 * 
 * @author luomengan
 *
 */
public interface DeferredRecordDao extends BaseDao<DeferredRecord, Long> {

	List<DeferredRecord> retrieveByPublisherIdAndBuyRecordId(Long publisherId, Long buyRecordId);

}
