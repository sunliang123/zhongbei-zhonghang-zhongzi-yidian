package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.Publisher;

/**
 * 策略发布人 Repository
 * 
 * @author luomengan
 *
 */
public interface PublisherRepository extends Repository<Publisher, Long> {

	Publisher save(Publisher publisher);

	void delete(Long id);

	Page<Publisher> findAll(Pageable pageable);
	
	List<Publisher> findAll();

	Publisher findById(Long id);

	Publisher findByDomainAndDataId(String domain, Long dataId);
	
}
