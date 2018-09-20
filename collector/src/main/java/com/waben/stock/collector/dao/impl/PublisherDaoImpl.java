package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.PublisherDao;
import com.waben.stock.collector.dao.impl.jpa.PublisherRepository;
import com.waben.stock.collector.entity.Publisher;

/**
 * 策略发布人 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class PublisherDaoImpl implements PublisherDao {

	@Autowired
	private PublisherRepository publisherRepository;

	@Override
	public Publisher createPublisher(Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	@Override
	public void deletePublisherById(Long id) {
		publisherRepository.delete(id);
	}

	@Override
	public Publisher updatePublisher(Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	@Override
	public Publisher retrievePublisherById(Long id) {
		return publisherRepository.findById(id);
	}

	@Override
	public Page<Publisher> pagePublisher(int page, int limit) {
		return publisherRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<Publisher> listPublisher() {
		return publisherRepository.findAll();
	}

	@Override
	public Publisher getByDomainAndDataId(String domain, Long dataId) {
		return publisherRepository.findByDomainAndDataId(domain, dataId);
	}

}
