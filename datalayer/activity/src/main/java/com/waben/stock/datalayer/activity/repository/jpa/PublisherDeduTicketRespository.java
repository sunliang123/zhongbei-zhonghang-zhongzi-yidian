package com.waben.stock.datalayer.activity.repository.jpa;

import com.waben.stock.datalayer.activity.entity.PublisherDeduTicket;
import com.waben.stock.datalayer.activity.entity.TicketAmount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherDeduTicketRespository extends JpaRepository<PublisherDeduTicket, Long>{

    List<PublisherDeduTicket> findPublisherDeduTicketsByApId(long apId);

}
