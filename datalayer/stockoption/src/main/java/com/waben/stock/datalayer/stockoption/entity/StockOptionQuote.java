package com.waben.stock.datalayer.stockoption.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 期权报价
 * 
 * <p>
 * 设置某支股票的权利金比例
 * </p>
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "stock_option_quote")
public class StockOptionQuote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 股票代码
	 */
	private String stockCode;
	/**
	 * 股票名称
	 */
	private String stockName;
	/**
	 * 周期，单位天
	 */
	private Integer cycle;
	/**
	 * 权利金报价比例
	 */
	@Column(scale = 4)
	private BigDecimal rightMoneyRatio;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 最大限额名义本金
	 */
	@Transient
	private BigDecimal limitNominalAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public BigDecimal getRightMoneyRatio() {
		return rightMoneyRatio;
	}

	public void setRightMoneyRatio(BigDecimal rightMoneyRatio) {
		this.rightMoneyRatio = rightMoneyRatio;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getLimitNominalAmount() {
		return limitNominalAmount;
	}

	public void setLimitNominalAmount(BigDecimal limitNominalAmount) {
		this.limitNominalAmount = limitNominalAmount;
	}

}
