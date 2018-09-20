package com.waben.stock.interfaces.vo.stockcontent;

import com.waben.stock.interfaces.dto.stockcontent.AmountValueDto;
import com.waben.stock.interfaces.dto.stockcontent.LossDto;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
public class LossVo  implements Comparable<LossVo> {

	private Long id;
	private BigDecimal point;
	private Set<StrategyTypeVo> strategyTypes;
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
	public int compareTo(LossVo o) {
		return 0;
	}
}
