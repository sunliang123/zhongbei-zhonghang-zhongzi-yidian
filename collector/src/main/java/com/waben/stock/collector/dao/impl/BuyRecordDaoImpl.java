package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.BuyRecordDao;
import com.waben.stock.collector.dao.impl.jpa.BuyRecordRepository;
import com.waben.stock.collector.entity.BuyRecord;

/**
 * 点买记录 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BuyRecordDaoImpl implements BuyRecordDao {

	@Autowired
	private BuyRecordRepository buyRecordRepository;

	@Override
	public BuyRecord createBuyRecord(BuyRecord buyRecord) {
		return buyRecordRepository.save(buyRecord);
	}

	@Override
	public void deleteBuyRecordById(Long id) {
		buyRecordRepository.delete(id);
	}

	@Override
	public BuyRecord updateBuyRecord(BuyRecord buyRecord) {
		return buyRecordRepository.save(buyRecord);
	}

	@Override
	public BuyRecord retrieveBuyRecordById(Long id) {
		return buyRecordRepository.findById(id);
	}

	@Override
	public Page<BuyRecord> pageBuyRecord(int page, int limit) {
		return buyRecordRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<BuyRecord> listBuyRecord() {
		return buyRecordRepository.findAll();
	}

	@Override
	public BuyRecord getByDomainAndDataId(String domain, Long dataId) {
		return buyRecordRepository.findByDomainAndDataId(domain, dataId);
	}

}
