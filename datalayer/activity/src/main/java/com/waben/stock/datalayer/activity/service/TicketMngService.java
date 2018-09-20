package com.waben.stock.datalayer.activity.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.util.PageToPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.activity.entity.TicketAmount;
import com.waben.stock.datalayer.activity.repository.TicketDao;
import com.waben.stock.interfaces.dto.activity.TicketAmountDto;
import com.waben.stock.interfaces.pojo.query.PageAndSortQuery;
import com.waben.stock.interfaces.util.CopyBeanUtils;

@Service
public class TicketMngService {
	
	@Autowired
	private TicketDao td;
	
	@Transactional
	public TicketAmountDto saveTicket(TicketAmountDto t) {
		TicketAmount a = CopyBeanUtils.copyBeanProperties(TicketAmount.class, t, false);
		td.saveTicket(a);
		return CopyBeanUtils.copyBeanProperties(TicketAmountDto.class, a, false);
	}

	
	public PageInfo<TicketAmountDto> getTicketList(int pageno, Integer pagesize) {
		if(pagesize == null){
			PageAndSortQuery pq = new PageAndSortQuery();
			pagesize = pq.getSize();
		}
		Page<TicketAmount> page = td.getTicketList(pageno, pagesize);
		PageInfo<TicketAmountDto> pageInfo = PageToPageInfo.pageToPageInfo(page,TicketAmountDto.class);
		return pageInfo;
	}

	@Transactional
	public void deleteTicket(long ticketId) {
		td.deleteTicket(ticketId);
	}

    public TicketAmount getTicketAmount(long ticketAmountId) {
		TicketAmount ticketAmount = td.getTicketAmount(ticketAmountId);
		return ticketAmount;
	}
}
