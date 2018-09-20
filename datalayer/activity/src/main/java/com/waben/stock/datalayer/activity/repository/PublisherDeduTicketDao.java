package com.waben.stock.datalayer.activity.repository;

import com.waben.stock.datalayer.activity.entity.PublisherDeduTicket;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PublisherDeduTicketDao {

	PublisherDeduTicket savePublisherDeduTicket(PublisherDeduTicket a);

	Page<PublisherDeduTicket> getPublisherDeduTicketList(int pageno, int pagesize);

	PublisherDeduTicket getPublisherDeduTicket(long publisherDeduTicketId);

    List<PublisherDeduTicket> getPublisherDeduTicketsByApId(long activityPublisherId);
}
