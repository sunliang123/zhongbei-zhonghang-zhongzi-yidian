package com.waben.stock.interfaces.dto.stockcontent;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
public class LossDto implements Comparable<LossDto> {

	private Long id;
	private BigDecimal point;
	private Set<StrategyTypeDto> strategyTypesDto;
	private Integer multiple;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
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
	public int compareTo(LossDto o) {
		return o.getPoint().compareTo(point);
	}

	public Integer getMultiple() {
		return multiple;
	}

	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}

}
