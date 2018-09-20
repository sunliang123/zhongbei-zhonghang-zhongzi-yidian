package com.waben.stock.applayer.tactics.dto.stockoption;

import java.math.BigDecimal;

import com.waben.stock.interfaces.dto.stockoption.StockOptionQuoteDto;

public class StockOptionQuoteWithBalanceDto extends StockOptionQuoteDto {

	public StockOptionQuoteWithBalanceDto(StockOptionQuoteDto quote) {
		if (quote != null) {
			this.setCycle(quote.getCycle());
			this.setId(quote.getId());
			this.setRightMoneyRatio(quote.getRightMoneyRatio());
			this.setStockCode(quote.getStockCode());
			this.setStockName(quote.getStockName());
		}
	}

	private BigDecimal availableBalance;

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

}
