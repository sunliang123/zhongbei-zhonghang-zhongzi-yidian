package com.waben.stock.datalayer.stockcontent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Created by yuyidi on 2017/11/22.
 * @desc 止损
 */
@Entity
@Table(name = "loss")
public class Loss {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(scale = 3)
	private BigDecimal point;
	@Column
	private Integer multiple;
	@JsonBackReference
	@ManyToMany(targetEntity = StrategyType.class, mappedBy = "losses")
	private Set<StrategyType> strategyTypes;

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

	public Set<StrategyType> getStrategyTypes() {
		return strategyTypes;
	}

	public void setStrategyTypes(Set<StrategyType> strategyTypes) {
		this.strategyTypes = strategyTypes;
	}

	public Integer getMultiple() {
		return multiple;
	}

	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}

}
