package com.waben.stock.datalayer.buyrecord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.buyrecord.entity.DeferredRecord;
import com.waben.stock.datalayer.buyrecord.repository.DeferredRecordDao;

/**
 * 递延记录 Service
 * 
 * @author luomengan
 *
 */
@Service
public class DeferredRecordService {

	@Autowired
	private DeferredRecordDao dao;
	
	public List<DeferredRecord> findByPublisherIdAndBuyRecordId(Long publisherId, Long buyRecordId) {
		return dao.retrieveByPublisherIdAndBuyRecordId(publisherId, buyRecordId);
	}
	
}
