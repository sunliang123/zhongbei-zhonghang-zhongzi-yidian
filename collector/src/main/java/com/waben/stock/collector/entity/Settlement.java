package com.waben.stock.collector.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 结算
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "collector_settlement")
public class Settlement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long dataId;
	private String domain;
	/**
	 * 对应的点买记录
	 */
	private Long buyRecordId;
	/**
	 * 盈亏
	 */
	private BigDecimal profitOrLoss;
	/**
	 * 发布人盈亏
	 */
	private BigDecimal publisherProfitOrLoss;
	/**
	 * 投资人盈亏
	 */
	private BigDecimal investorProfitOrLoss;
	/**
	 * 结算时间
	 */
	private Date settlementTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBuyRecordId() {
		return buyRecordId;
	}

	public void setBuyRecordId(Long buyRecordId) {
		this.buyRecordId = buyRecordId;
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

}
