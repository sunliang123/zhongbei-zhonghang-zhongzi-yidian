package com.waben.stock.datalayer.buyrecord.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 结算
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "settlement")
public class Settlement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 对应的点买记录
	 */
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "buy_record_id")
	private BuyRecord buyRecord;
	/**
	 * 盈亏
	 */
	@Column(name = "profit_or_loss")
	private BigDecimal profitOrLoss;
	/**
	 * 发布人盈亏
	 */
	@Column(name = "publisher_profit_or_loss")
	private BigDecimal publisherProfitOrLoss;
	/**
	 * 投资人盈亏
	 */
	@Column(name = "investor_profit_or_loss")
	private BigDecimal investorProfitOrLoss;
	/**
	 * 结算时间
	 */
	@Column(name = "settlement_time")
	private Date settlementTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BuyRecord getBuyRecord() {
		return buyRecord;
	}

	public void setBuyRecord(BuyRecord buyRecord) {
		this.buyRecord = buyRecord;
	}

	public BigDecimal getProfitOrLoss() {
		return profitOrLoss;
	}

	public void setProfitOrLoss(BigDecimal profitOrLoss) {
		this.profitOrLoss = profitOrLoss;
	}

	public BigDecimal getPublisherProfitOrLoss() {
		return publisherProfitOrLoss;
	}

	public void setPublisherProfitOrLoss(BigDecimal publisherProfitOrLoss) {
		this.publisherProfitOrLoss = publisherProfitOrLoss;
	}

	public BigDecimal getInvestorProfitOrLoss() {
		return investorProfitOrLoss;
	}

	public void setInvestorProfitOrLoss(BigDecimal investorProfitOrLoss) {
		this.investorProfitOrLoss = investorProfitOrLoss;
	}

	public Date getSettlementTime() {
		return settlementTime;
	}

	public void setSettlementTime(Date settlementTime) {
		this.settlementTime = settlementTime;
	}

}
