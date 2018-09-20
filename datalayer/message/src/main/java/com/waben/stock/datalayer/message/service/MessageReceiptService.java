package com.waben.stock.datalayer.message.service;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.waben.stock.datalayer.message.entity.MessageReceipt;
import com.waben.stock.datalayer.message.repository.MessageReceiptDao;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.pojo.query.MessageReceiptQuery;

/**
 * 
 * @author Created by hujian on 2018年1月4日
 */
@Service
public class MessageReceiptService {

	@Autowired
	private MessageReceiptDao messageReceiptDao;
	
	public MessageReceipt save(MessageReceipt messageReceipt){
		return messageReceiptDao.create(messageReceipt);
	}
	
	public Long remove(Long id){
		messageReceiptDao.delete(id);
		return id;
	}
	
	public MessageReceipt revision(MessageReceipt messageReceipt){
		return messageReceiptDao.update(messageReceipt);
	}
	
	public MessageReceipt findById(Long id){
		MessageReceipt messageReceipt = messageReceiptDao.retrieve(id);
		if(messageReceipt == null){
			throw new ServiceException(ExceptionConstant.DATANOTFOUND_EXCEPTION);
		}
		return messageReceipt;
	}
	
	public Page<MessageReceipt> pages(final MessageReceiptQuery messageReceiptQuery){
		Pageable pageable = new PageRequest(messageReceiptQuery.getPage(), messageReceiptQuery.getSize());
		Page<MessageReceipt> pages = messageReceiptDao.page(new Specification<MessageReceipt>() {
			@Override
			public Predicate toPredicate(Root<MessageReceipt> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				if (!StringUtils.isEmpty(messageReceiptQuery.getRecipient())) {
					Predicate recipientQuery = criteriaBuilder.equal(root.get("recipient").as(String.class), messageReceiptQuery
							.getRecipient());
					criteriaQuery.where(criteriaBuilder.and(recipientQuery));
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}
	
}
