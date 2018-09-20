package com.waben.stock.interfaces.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
@ApiModel(value="PermissionVo",description="权限对象")
public class PermissionVo implements Serializable {
    @ApiModelProperty(value = "权限id")
    private Long id;
    @ApiModelProperty(value = "权限名称")
    private String name;
    @ApiModelProperty(value = "权限父id")
    private Long pid;
    @ApiModelProperty(value = "权限代码")
    private String expression;
    @ApiModelProperty(value = "子权限数组")
    private List<PermissionVo> childPermissions;

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

    public List<PermissionVo> getChildPermissions() {
        return childPermissions;
    }

    public void setChildPermissions(List<PermissionVo> childPermissions) {
        this.childPermissions = childPermissions;
    }
}
