package com.waben.stock.datalayer.message.repository;

import java.util.List;

import com.waben.stock.datalayer.message.entity.Messaging;

/**
 * 
 * @author Created by hujian on 2018年1月4日
 *
 */
public interface MessagingDao extends BaseDao<Messaging, Long>{

	/**
	 * 查询当前用户未产生回执的消息ID集合
	 * @param recipient
	 * @return
	 */
	List<Messaging> retrieveNotProduceReceiptAllByRecipient(String recipient);
	
}
