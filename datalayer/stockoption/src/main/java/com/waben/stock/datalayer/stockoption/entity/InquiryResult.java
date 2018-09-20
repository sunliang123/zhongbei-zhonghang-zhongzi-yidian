package com.waben.stock.datalayer.stockoption.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 询价结果
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "inquiry_result")
public class InquiryResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 权利金报价比例
	 */
	private BigDecimal rightMoneyRatio;
	/**
	 * 对应的用户股票期权交易信息
	 */
	@ManyToOne
	@JoinColumn(name = "trade_id")
	private StockOptionTrade trade;
	/**
	 * 对应的期权第三方机构
	 */
	@ManyToOne
	@JoinColumn(name = "org_id")
	private StockOptionOrg org;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getRightMoneyRatio() {
		return rightMoneyRatio;
	}

	public void setRightMoneyRatio(BigDecimal rightMoneyRatio) {
		this.rightMoneyRatio = rightMoneyRatio;
	}

	public StockOptionTrade getTrade() {
		return trade;
	}

	public void setTrade(StockOptionTrade trade) {
		this.trade = trade;
	}

	public StockOptionOrg getOrg() {
		return org;
	}

	public void setOrg(StockOptionOrg org) {
		this.org = org;
	}

}
