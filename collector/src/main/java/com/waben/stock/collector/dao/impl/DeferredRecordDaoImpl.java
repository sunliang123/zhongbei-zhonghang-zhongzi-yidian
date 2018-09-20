package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.DeferredRecordDao;
import com.waben.stock.collector.dao.impl.jpa.DeferredRecordRepository;
import com.waben.stock.collector.entity.DeferredRecord;

/**
 * 递延记录 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class DeferredRecordDaoImpl implements DeferredRecordDao {

	@Autowired
	private DeferredRecordRepository deferredRecordRepository;

	@Override
	public DeferredRecord createDeferredRecord(DeferredRecord deferredRecord) {
		return deferredRecordRepository.save(deferredRecord);
	}

	@Override
	public void deleteDeferredRecordById(Long id) {
		deferredRecordRepository.delete(id);
	}

	@Override
	public DeferredRecord updateDeferredRecord(DeferredRecord deferredRecord) {
		return deferredRecordRepository.save(deferredRecord);
	}

	@Override
	public DeferredRecord retrieveDeferredRecordById(Long id) {
		return deferredRecordRepository.findById(id);
	}

	@Override
	public Page<DeferredRecord> pageDeferredRecord(int page, int limit) {
		return deferredRecordRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<DeferredRecord> listDeferredRecord() {
		return deferredRecordRepository.findAll();
	}

	@Override
	public DeferredRecord getByDomainAndDataId(String domain, Long dataId) {
		return deferredRecordRepository.findByDomainAndDataId(domain, dataId);
	}

}
