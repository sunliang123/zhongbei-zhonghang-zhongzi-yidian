package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.DeferredRecord;

/**
 * 递延记录 Repository
 * 
 * @author luomengan
 *
 */
public interface DeferredRecordRepository extends Repository<DeferredRecord, Long> {

	DeferredRecord save(DeferredRecord deferredRecord);

	void delete(Long id);

	Page<DeferredRecord> findAll(Pageable pageable);
	
	List<DeferredRecord> findAll();

	DeferredRecord findById(Long id);

	DeferredRecord findByDomainAndDataId(String domain, Long dataId);
	
}
