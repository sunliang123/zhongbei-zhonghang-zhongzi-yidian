package com.waben.stock.collector.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 策略类型
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "collector_strategy_type")
public class StrategyType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long dataId;
	private String domain;
	@Column
	private String name;
	@Column
	private Boolean state;
	/**
	 * 每万元收取的服务费用
	 */
	@Column
	private BigDecimal serviceFeePerWan;
	/**
	 * 穿仓点
	 */
	@Column(scale = 3)
	private BigDecimal wearingPoint;
	/**
	 * 止盈点
	 */
	@Column(scale = 3)
	private BigDecimal profit;
	/**
	 * 递延费
	 */
	@Column
	private Integer deferred;
	/**
	 * 周期
	 */
	@Column
	private Integer cycle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Integer getDeferred() {
		return deferred;
	}

	public void setDeferred(Integer deferred) {
		this.deferred = deferred;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public BigDecimal getServiceFeePerWan() {
		return serviceFeePerWan;
	}

	public void setServiceFeePerWan(BigDecimal serviceFeePerWan) {
		this.serviceFeePerWan = serviceFeePerWan;
	}

	public BigDecimal getWearingPoint() {
		return wearingPoint;
	}

	public void setWearingPoint(BigDecimal wearingPoint) {
		this.wearingPoint = wearingPoint;
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
