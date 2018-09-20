package com.waben.stock.interfaces.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
@ApiModel(value="MenuVo",description="菜单对象")
public class MenuVo implements Serializable {
    @ApiModelProperty(value = "菜单id")
    private Long id;
    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "父菜单id")
    private Long pid;
    @ApiModelProperty(value = "菜单状态")
    private Boolean state;
    @ApiModelProperty(value = "菜单顺序")
    private Integer sort;
    @ApiModelProperty(value = "菜单链接")
    private String url;
    @ApiModelProperty(value = "菜单图片")
    private String icon;

    @ApiModelProperty(value = "子菜单集合")
    private List<MenuVo> childs = new ArrayList<>();

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

    public List<MenuVo> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuVo> childs) {
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
}
