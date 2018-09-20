package com.waben.stock.datalayer.message.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.message.entity.Messaging;
import com.waben.stock.datalayer.message.repository.MessagingDao;
import com.waben.stock.datalayer.message.repository.impl.jpa.MessagingRepository;

/**
 * 
 * @author Created by hujian on 2018年1月4日
 *
 */
@Repository
public class MessagingDaoImpl implements MessagingDao {

	@Autowired
	private MessagingRepository repository;
	
	@Override
	public Messaging create(Messaging t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Messaging update(Messaging t) {
		return repository.save(t);
	}

	@Override
	public Messaging retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<Messaging> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<Messaging> page(Specification<Messaging> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<Messaging> list() {
		return repository.findAll();
	}

	@Override
	public List<Messaging> retrieveNotProduceReceiptAllByRecipient(String recipient) {
		return repository.findAllByRecipient(recipient);
	}

}
