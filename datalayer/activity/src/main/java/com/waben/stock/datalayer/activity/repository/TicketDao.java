package com.waben.stock.datalayer.activity.repository;


import com.waben.stock.datalayer.activity.entity.TicketAmount;
import org.springframework.data.domain.Page;

public interface TicketDao {
	
	void saveTicket(TicketAmount t);
	
	Page<TicketAmount> getTicketList(int pageno, int pagesize);
	
	void deleteTicket(long ticketId);

	TicketAmount getTicketAmount(long ticketAmountId);

}
