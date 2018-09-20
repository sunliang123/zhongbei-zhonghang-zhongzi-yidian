package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.BuyRecord;

/**
 * 点买记录 Dao
 * 
 * @author luomengan
 *
 */
public interface BuyRecordDao {

	public BuyRecord createBuyRecord(BuyRecord buyRecord);

	public void deleteBuyRecordById(Long id);

	public BuyRecord updateBuyRecord(BuyRecord buyRecord);

	public BuyRecord retrieveBuyRecordById(Long id);

	public Page<BuyRecord> pageBuyRecord(int page, int limit);
	
	public List<BuyRecord> listBuyRecord();
	
	public BuyRecord getByDomainAndDataId(String domain, Long dataId);

}
