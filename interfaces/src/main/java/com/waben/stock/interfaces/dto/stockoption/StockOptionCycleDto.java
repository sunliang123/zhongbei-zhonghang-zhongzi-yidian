package com.waben.stock.interfaces.dto.stockoption;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 期权周期
 * 
 * @author luomengan
 *
 */
@ApiModel(value = "StockOptionCycleDto", description = "期权周期对象")
public class StockOptionCycleDto {

	@ApiModelProperty(value = "周期id")
	private Long id;
	/**
	 * 周期名称
	 */
	@ApiModelProperty(value = "周期名称")
	private String name;
	/**
	 * 周期天数
	 */
	@ApiModelProperty(value = "周期天数")
	private Integer cycle;
	/**
	 * 是否可用
	 */
	private Boolean enable;
	/**
	 * 周期月数
	 */
	@ApiModelProperty(value = "周期月数")
	private String cycleMonth;
	/**
	 * 最大限额名义本金
	 */
	@ApiModelProperty(value = "最大限额名义本金")
	private BigDecimal limitNominalAmount;
	/**
	 * 名义本金集合
	 */
	@ApiModelProperty(value = "名义本金集合")
	private Set<StockOptionAmountDto> amountValues = new HashSet<>();

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

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Set<StockOptionAmountDto> getAmountValues() {
		/*if (amountValues != null && amountValues.size() > 0) {
			Object obj = amountValues.iterator().next();
			if (obj instanceof StockOptionAmountDto) {
				TreeSet<StockOptionAmountDto> result = new TreeSet<>();
				result.addAll(amountValues);
				Collections.checkedSortedSet(result, StockOptionAmountDto.class);
				return result;
			}
		}*/
		return amountValues;
	}

	public void setAmountValues(Set<StockOptionAmountDto> amountValues) {
		this.amountValues = amountValues;
	}

	public String getCycleMonth() {
		return cycleMonth;
	}

	public void setCycleMonth(String cycleMonth) {
		this.cycleMonth = cycleMonth;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public BigDecimal getLimitNominalAmount() {
		return limitNominalAmount;
	}

	public void setLimitNominalAmount(BigDecimal limitNominalAmount) {
		this.limitNominalAmount = limitNominalAmount;
	}

}
