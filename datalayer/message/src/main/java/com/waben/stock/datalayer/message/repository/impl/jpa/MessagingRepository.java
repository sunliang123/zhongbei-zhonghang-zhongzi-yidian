package com.waben.stock.datalayer.message.repository.impl.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.waben.stock.datalayer.message.entity.Messaging;

/**
 * 
 * @author Created by hujian on 2018年1月4日
 *
 */
public interface MessagingRepository extends CustomJpaRepository<Messaging, Long> {

	@Query("from Messaging m where not exists (select mr.message from MessageReceipt mr where m.id = mr.message and mr.recipient = :recipient)")
	List<Messaging> findAllByRecipient(@Param("recipient") String recipient);
	
}
