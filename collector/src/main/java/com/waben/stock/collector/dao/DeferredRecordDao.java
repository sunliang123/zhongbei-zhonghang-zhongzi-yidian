package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.DeferredRecord;

/**
 * 递延记录 Dao
 * 
 * @author luomengan
 *
 */
public interface DeferredRecordDao {

	public DeferredRecord createDeferredRecord(DeferredRecord deferredRecord);

	public void deleteDeferredRecordById(Long id);

	public DeferredRecord updateDeferredRecord(DeferredRecord deferredRecord);

	public DeferredRecord retrieveDeferredRecordById(Long id);

	public Page<DeferredRecord> pageDeferredRecord(int page, int limit);
	
	public List<DeferredRecord> listDeferredRecord();
	
	public DeferredRecord getByDomainAndDataId(String domain, Long dataId);

}
