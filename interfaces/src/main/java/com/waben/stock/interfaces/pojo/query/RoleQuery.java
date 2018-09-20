package com.waben.stock.interfaces.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Created by yuyidi on 2017/12/11.
 * @desc
 */
@ApiModel(value="RoleQuery",description="角色查询对象")
public class RoleQuery extends PageAndSortQuery {
    @ApiModelProperty(value = "角色名称")
    private String name;
    @ApiModelProperty(value = "角色代码")
    private String code;
    @ApiModelProperty(value = "角色id")
    private Long id;
    @ApiModelProperty(value = "角色描述")
    private String description;
    @ApiModelProperty(value = "代理商id")
    private Long organization;
    @ApiModelProperty(value = "角色类型")
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrganization() {
        return organization;
    }

    public void setOrganization(Long organization) {
        this.organization = organization;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
