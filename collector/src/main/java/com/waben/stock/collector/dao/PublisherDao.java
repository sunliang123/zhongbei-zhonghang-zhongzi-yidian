package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.Publisher;

/**
 * 策略发布人 Dao
 * 
 * @author luomengan
 *
 */
public interface PublisherDao {

	public Publisher createPublisher(Publisher publisher);

	public void deletePublisherById(Long id);

	public Publisher updatePublisher(Publisher publisher);

	public Publisher retrievePublisherById(Long id);

	public Page<Publisher> pagePublisher(int page, int limit);
	
	public List<Publisher> listPublisher();
	
	public Publisher getByDomainAndDataId(String domain, Long dataId);

}
