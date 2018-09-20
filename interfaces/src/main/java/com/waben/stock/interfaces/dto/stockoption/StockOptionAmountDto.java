package com.waben.stock.interfaces.dto.stockoption;

import java.math.BigDecimal;

/**
 * 期权名义本金
 * 
 * @author luomengan
 *
 */
public class StockOptionAmountDto implements Comparable<StockOptionAmountDto> {

	private Long id;
	/**
	 * 名义本金
	 */
	private BigDecimal amount;

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

	@Override
	public int compareTo(StockOptionAmountDto o) {
		return this.amount.compareTo(o.getAmount());
	}

}
