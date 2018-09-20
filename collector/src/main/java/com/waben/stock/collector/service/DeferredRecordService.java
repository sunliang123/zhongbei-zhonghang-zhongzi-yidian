package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.DeferredRecordDao;
import com.waben.stock.collector.entity.DeferredRecord;

/**
 * 递延记录 Service
 * 
 * @author luomengan
 *
 */
@Service
public class DeferredRecordService {

	@Autowired
	private DeferredRecordDao deferredRecordDao;

	public DeferredRecord getDeferredRecordInfo(Long id) {
		return deferredRecordDao.retrieveDeferredRecordById(id);
	}

	@Transactional
	public DeferredRecord addDeferredRecord(DeferredRecord deferredRecord) {
		return deferredRecordDao.createDeferredRecord(deferredRecord);
	}

	@Transactional
	public DeferredRecord modifyDeferredRecord(DeferredRecord deferredRecord) {
		return deferredRecordDao.updateDeferredRecord(deferredRecord);
	}

	@Transactional
	public void deleteDeferredRecord(Long id) {
		deferredRecordDao.deleteDeferredRecordById(id);
	}
	
	@Transactional
	public void deleteDeferredRecords(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					deferredRecordDao.deleteDeferredRecordById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<DeferredRecord> deferredRecords(int page, int limit) {
		return deferredRecordDao.pageDeferredRecord(page, limit);
	}
	
	public List<DeferredRecord> list() {
		return deferredRecordDao.listDeferredRecord();
	}

}
