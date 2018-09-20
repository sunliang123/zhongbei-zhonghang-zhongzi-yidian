package com.waben.stock.datalayer.activity.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waben.stock.datalayer.activity.entity.TicketAmount;

public interface TicketRespository extends JpaRepository<TicketAmount, Long>{

}
