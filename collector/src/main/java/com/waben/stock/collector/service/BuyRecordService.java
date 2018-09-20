package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.BuyRecordDao;
import com.waben.stock.collector.entity.BuyRecord;

/**
 * 点买记录 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BuyRecordService {

	@Autowired
	private BuyRecordDao buyRecordDao;

	public BuyRecord getBuyRecordInfo(Long id) {
		return buyRecordDao.retrieveBuyRecordById(id);
	}

	@Transactional
	public BuyRecord addBuyRecord(BuyRecord buyRecord) {
		return buyRecordDao.createBuyRecord(buyRecord);
	}

	@Transactional
	public BuyRecord modifyBuyRecord(BuyRecord buyRecord) {
		return buyRecordDao.updateBuyRecord(buyRecord);
	}

	@Transactional
	public void deleteBuyRecord(Long id) {
		buyRecordDao.deleteBuyRecordById(id);
	}
	
	@Transactional
	public void deleteBuyRecords(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					buyRecordDao.deleteBuyRecordById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<BuyRecord> buyRecords(int page, int limit) {
		return buyRecordDao.pageBuyRecord(page, limit);
	}
	
	public List<BuyRecord> list() {
		return buyRecordDao.listBuyRecord();
	}

}
