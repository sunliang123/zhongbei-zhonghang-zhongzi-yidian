package com.waben.stock.interfaces.dto.manage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
public class MenuDto implements Serializable {

    private Long id;
    private String name;
    private Long pid;
    private Boolean state;
    private Integer sort;
    private String url;
    private String icon;
    private Long variety;

    private List<MenuDto> childs = new ArrayList<>();

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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<MenuDto> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuDto> childs) {
        this.childs = childs;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getVariety() {
        return variety;
    }

    public void setVariety(Long variety) {
        this.variety = variety;
    }
}
