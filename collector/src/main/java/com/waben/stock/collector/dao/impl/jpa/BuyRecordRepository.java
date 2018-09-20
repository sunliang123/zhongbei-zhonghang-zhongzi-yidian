package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.BuyRecord;

/**
 * 点买记录 Repository
 * 
 * @author luomengan
 *
 */
public interface BuyRecordRepository extends Repository<BuyRecord, Long> {

	BuyRecord save(BuyRecord buyRecord);

	void delete(Long id);

	Page<BuyRecord> findAll(Pageable pageable);
	
	List<BuyRecord> findAll();

	BuyRecord findById(Long id);

	BuyRecord findByDomainAndDataId(String domain, Long dataId);
	
}
