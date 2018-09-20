package com.waben.stock.interfaces.dto.stockcontent;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
public class StrategyTypeDto {

	private Long id;
	private String name;
	private Boolean state;

	private Set<AmountValueDto> amountValues;
	/**
	 * 止损点集合
	 */
	private Set<LossDto> losses;
	/**
	 * 每万元收取的服务费用
	 */
	private BigDecimal serviceFeePerWan;
	/**
	 * 穿仓点
	 */
	private BigDecimal wearingPoint;
	/**
	 * 止盈点
	 */
	private BigDecimal profit;
	/**
	 * 递延费
	 */
	private Integer deferred;
	/**
	 * 周期
	 */
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

	public Set<AmountValueDto> getAmountValues() {
		if (amountValues != null && amountValues.size() > 0) {
			Object obj = amountValues.iterator().next();
			if (obj instanceof AmountValueDto) {
				TreeSet<AmountValueDto> result = new TreeSet<>();
				result.addAll(amountValues);
				Collections.checkedSortedSet(result, AmountValueDto.class);
				return result;
			}
		}
		return amountValues;
	}

	public void setAmountValues(Set<AmountValueDto> amountValues) {
		this.amountValues = amountValues;
	}

	public Set<LossDto> getLosses() {
		if (losses != null && losses.size() > 0) {
			Object obj = losses.iterator().next();
			if (obj instanceof LossDto) {
				TreeSet<LossDto> result = new TreeSet<>();
				result.addAll(losses);
				Collections.checkedSortedSet(result, LossDto.class);
				return result;
			}
		}
		return losses;
	}

	public void setLosses(Set<LossDto> losses) {
		this.losses = losses;
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

}
