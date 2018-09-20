package com.waben.stock.interfaces.dto.stockcontent;

import java.util.Set;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
public class AmountValueDto implements Comparable<AmountValueDto> {

	private Long id;
	private Long value;

	private Set<StrategyTypeDto> strategyTypesDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	public Set<StrategyTypeDto> getStrategyTypesDto() {
		return strategyTypesDto;
	}

	public void setStrategyTypesDto(Set<StrategyTypeDto> strategyTypesDto) {
		this.strategyTypesDto = strategyTypesDto;
	}
	public void setStrategyTypes(Set<StrategyTypeDto> strategyTypes) {
		this.strategyTypesDto = strategyTypes;
	}
	@Override
	public int compareTo(AmountValueDto o) {
		return new Long(value - o.getValue()).intValue();
	}

}
