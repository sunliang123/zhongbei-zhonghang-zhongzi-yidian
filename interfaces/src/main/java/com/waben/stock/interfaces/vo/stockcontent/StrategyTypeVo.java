package com.waben.stock.interfaces.vo.stockcontent;

import com.waben.stock.interfaces.dto.stockcontent.AmountValueDto;
import com.waben.stock.interfaces.dto.stockcontent.LossDto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
public class StrategyTypeVo {

	private Long id;
	private String name;
	private Boolean state;

	private Set<AmountValueVo> amountValues;
	/**
	 * 止损点集合
	 */
	private Set<LossVo> losses;
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

	private List<Long> loss;

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

	public Set<AmountValueVo> getAmountValues() {
		if (amountValues != null && amountValues.size() > 0) {
			Object obj = amountValues.iterator().next();
			if (obj instanceof AmountValueVo) {
				TreeSet<AmountValueVo> result = new TreeSet<>();
				result.addAll(amountValues);
				Collections.checkedSortedSet(result, AmountValueVo.class);
				return result;
			}
		}
		return amountValues;
	}

	public void setAmountValues(Set<AmountValueVo> amountValues) {
		this.amountValues = amountValues;
	}

	public Set<LossVo> getLosses() {
		if (losses != null && losses.size() > 0) {
			Object obj = losses.iterator().next();
			if (obj instanceof LossVo) {
				TreeSet<LossVo> result = new TreeSet<>();
				result.addAll(losses);
				Collections.checkedSortedSet(result, LossVo.class);
				return result;
			}
		}
		return losses;
	}

	public void setLosses(Set<LossVo> losses) {
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

	public List<Long> getLoss() {
		return loss;
	}

	public void setLoss(List<Long> loss) {
		this.loss = loss;
	}
}
