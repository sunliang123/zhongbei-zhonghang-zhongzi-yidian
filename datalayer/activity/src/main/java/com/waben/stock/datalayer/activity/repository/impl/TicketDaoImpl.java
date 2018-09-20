package com.waben.stock.datalayer.activity.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.activity.entity.TicketAmount;
import com.waben.stock.datalayer.activity.repository.TicketDao;
import com.waben.stock.datalayer.activity.repository.jpa.TicketRespository;

@Repository
public class TicketDaoImpl implements TicketDao{
	
	@Autowired
	private TicketRespository tr;

	@Override
	public void saveTicket(TicketAmount t) {
		tr.save(t);
	}

	@Override
	public Page<TicketAmount> getTicketList(int pageno, int pagesize) {
		Pageable p = new PageRequest(pageno-1, pagesize);
		Page<TicketAmount> pt =  tr.findAll(p);
		return pt;
	}

	@Override
	public void deleteTicket(long ticketId) {
		tr.delete(ticketId);
	}

	@Override
	public TicketAmount getTicketAmount(long ticketAmountId) {
		return tr.findOne(ticketAmountId);
	}
}
