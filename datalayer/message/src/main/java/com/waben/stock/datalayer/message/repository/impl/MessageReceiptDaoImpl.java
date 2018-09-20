package com.waben.stock.datalayer.message.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.message.entity.MessageReceipt;
import com.waben.stock.datalayer.message.entity.Messaging;
import com.waben.stock.datalayer.message.repository.MessageReceiptDao;
import com.waben.stock.datalayer.message.repository.impl.jpa.MessageReceiptRepository;

@Repository
public class MessageReceiptDaoImpl implements MessageReceiptDao {

	@Autowired
	private MessageReceiptRepository repository;
	
	@Override
	public MessageReceipt create(MessageReceipt t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public MessageReceipt update(MessageReceipt t) {
		return repository.save(t);
	}

	@Override
	public MessageReceipt retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<MessageReceipt> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<MessageReceipt> page(Specification<MessageReceipt> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<MessageReceipt> list() {
		return repository.findAll();
	}

	@Override
	public MessageReceipt findByMessageAndRecipient(Messaging messaging, String recipient) {
		return repository.findByMessageAndRecipient(messaging, recipient);
	}

}
