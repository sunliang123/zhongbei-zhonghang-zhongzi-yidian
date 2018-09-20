package com.waben.stock.datalayer.message.repository.impl.jpa;

import com.waben.stock.datalayer.message.entity.MessageReceipt;
import com.waben.stock.datalayer.message.entity.Messaging;

/**
 * 
 * @author Created by hujian on 2018年1月4日
 *
 */
public interface MessageReceiptRepository extends CustomJpaRepository<MessageReceipt, Long>{

	MessageReceipt findByMessageAndRecipient(Messaging messaging, String recipient);

	
}
