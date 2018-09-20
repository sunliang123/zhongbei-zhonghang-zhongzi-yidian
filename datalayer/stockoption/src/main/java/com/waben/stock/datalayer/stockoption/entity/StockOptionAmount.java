package com.waben.stock.datalayer.stockoption.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.waben.stock.interfaces.dto.stockoption.StockOptionAmountDto;

/**
 * 期权名义本金
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "stock_option_amount")
public class StockOptionAmount implements Comparable<StockOptionAmount> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 名义本金
	 */
	private BigDecimal amount;
	/**
	 * 对应的周期
	 */
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cycle_id")
	private StockOptionCycle cycle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public StockOptionCycle getCycle() {
		return cycle;
	}

	public void setCycle(StockOptionCycle cycle) {
		this.cycle = cycle;
	}

	@Override
	public int compareTo(StockOptionAmount o) {
		return this.amount.compareTo(o.getAmount());
	}

}
