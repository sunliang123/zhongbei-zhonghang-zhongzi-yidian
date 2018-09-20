package com.waben.stock.datalayer.stockcontent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Created by yuyidi on 2017/11/22.
 * @desc 金额市值
 */
@Entity
@Table(name = "amount_value")
public class AmountValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long value;

    @JsonBackReference
    @ManyToMany(targetEntity = StrategyType.class, mappedBy = "amountValues")
    private Set<StrategyType> strategyTypes;

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

    public Set<StrategyType> getStrategyTypes() {
        return strategyTypes;
    }

    public void setStrategyTypes(Set<StrategyType> strategyTypes) {
        this.strategyTypes = strategyTypes;
    }
}
