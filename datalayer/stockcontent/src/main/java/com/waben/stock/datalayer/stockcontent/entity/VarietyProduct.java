package com.waben.stock.datalayer.stockcontent.entity;

import javax.persistence.*;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc 种类相关产品 (沪深|美股|港股|基金|债券|外汇|期货)
 */
@Entity
@Table(name = "variety_product")
public class VarietyProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 产品名称
     */
    @Column
    private String name;
    /**
     * 相关产品开市时间
     */
    @Column(name = "open_market_time")
    private String openMarketTime;
    /**
     * 所属品种种类类型
     */
    @JoinColumn(name = "type")
    @ManyToOne(targetEntity = VarietyType.class,fetch = FetchType.LAZY)
    private VarietyType type;
    @Column
    private Integer sort;
    @Column
    private Boolean state;

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

    public String getOpenMarketTime() {
        return openMarketTime;
    }

    public void setOpenMarketTime(String openMarketTime) {
        this.openMarketTime = openMarketTime;
    }

    public VarietyType getType() {
        return type;
    }

    public void setType(VarietyType type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
