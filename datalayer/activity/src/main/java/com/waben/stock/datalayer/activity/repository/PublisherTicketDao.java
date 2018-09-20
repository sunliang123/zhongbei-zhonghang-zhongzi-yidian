package com.waben.stock.datalayer.activity.repository;

import com.waben.stock.datalayer.activity.entity.PublisherTicket;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PublisherTicketDao {

	PublisherTicket savePublisherTicket(PublisherTicket a);

	Page<PublisherTicket> getPublisherTicketList(int pageno, int pagesize);

	PublisherTicket getPublisherTicket(long PublisherTicketId);

    List<PublisherTicket> getPublisherTicketsByApId(long apId);
}
