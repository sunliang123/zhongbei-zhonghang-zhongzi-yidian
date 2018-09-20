package com.waben.stock.datalayer.stockcontent.entity;

import javax.persistence.*;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc 金融种类
 */
@Entity
@Table(name = "variety_type")
public class VarietyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 种类名称
     */
    @Column
    private String name;
    /**
     * 种类类型标识
     */
    @Column
    private String variety;

    /**
     * 品种类型是否可用
     */
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

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
