package com.waben.stock.interfaces.vo.stockcontent;

import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;

import java.util.Set;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
public class AmountValueVo implements Comparable<AmountValueVo> {

	private Long id;
	private Long value;
	private Set<StrategyTypeVo> strategyTypes;

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

	public Set<StrategyTypeVo> getStrategyTypes() {
		return strategyTypes;
	}

	public void setStrategyTypes(Set<StrategyTypeVo> strategyTypes) {
		this.strategyTypes = strategyTypes;
	}
	public void setStrategyTypesDto(Set<StrategyTypeVo> strategyTypesDto) {
		this.strategyTypes = strategyTypesDto;
	}

	@Override
	public int compareTo(AmountValueVo o) {
		return new Long(value - o.getValue()).intValue();
	}

}
