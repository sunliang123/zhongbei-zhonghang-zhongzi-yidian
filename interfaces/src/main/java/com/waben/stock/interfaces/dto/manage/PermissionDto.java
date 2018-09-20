package com.waben.stock.interfaces.dto.manage;

import java.io.Serializable;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
public class PermissionDto implements Serializable {

    private Long id;
    private String name;
    private Long pid;
    private String expression;
    private Long variety;


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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Long getVariety() {
        return variety;
    }

    public void setVariety(Long variety) {
        this.variety = variety;
    }
}
