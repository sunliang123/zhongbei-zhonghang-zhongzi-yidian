package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.PublisherDao;
import com.waben.stock.collector.entity.Publisher;

/**
 * 策略发布人 Service
 * 
 * @author luomengan
 *
 */
@Service
public class PublisherService {

	@Autowired
	private PublisherDao publisherDao;

	public Publisher getPublisherInfo(Long id) {
		return publisherDao.retrievePublisherById(id);
	}

	@Transactional
	public Publisher addPublisher(Publisher publisher) {
		return publisherDao.createPublisher(publisher);
	}

	@Transactional
	public Publisher modifyPublisher(Publisher publisher) {
		return publisherDao.updatePublisher(publisher);
	}

	@Transactional
	public void deletePublisher(Long id) {
		publisherDao.deletePublisherById(id);
	}
	
	@Transactional
	public void deletePublishers(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					publisherDao.deletePublisherById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<Publisher> publishers(int page, int limit) {
		return publisherDao.pagePublisher(page, limit);
	}
	
	public List<Publisher> list() {
		return publisherDao.listPublisher();
	}

}
