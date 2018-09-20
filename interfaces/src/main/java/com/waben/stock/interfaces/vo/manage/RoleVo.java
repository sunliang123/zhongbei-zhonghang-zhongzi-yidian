package com.waben.stock.interfaces.vo.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
@ApiModel(value="RoleVo",description="角色对象")
public class RoleVo implements Serializable{
    @ApiModelProperty(value = "角色id")
    private Long id;
    @ApiModelProperty(value = "角色名称")
    private String name;
    @ApiModelProperty(value = "角色代码")
    private String code;
    @ApiModelProperty(value = "角色描述")
    private String description;
    @ApiModelProperty(value = "角色权限", hidden = true)
    private Set<PermissionVo> permissionVos;
    @ApiModelProperty(value = "角色菜单", hidden = true)
    private Set<MenuVo> menusVos;
    @ApiModelProperty(value = "代理商名称")
    private String organizationName;
    @ApiModelProperty(value = "代理商id")
    private Long organization;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Set<PermissionVo> getPermissionVos() {
        return permissionVos;
    }

    public void setPermissionVos(Set<PermissionVo> permissionVos) {
        this.permissionVos = permissionVos;
    }

    public Set<MenuVo> getMenusVos() {
        return menusVos;
    }

    public void setMenusVos(Set<MenuVo> menusVos) {
        this.menusVos = menusVos;
    }

    public void setMenusDtos(Set<MenuVo> menusDtos) {
        this.menusVos = menusDtos;
    }
    public void setPermissionDtos(Set<PermissionVo> permissionDtos) {
        this.permissionVos = permissionDtos;
    }

    public Long getOrganization() {
        return organization;
    }

    public void setOrganization(Long organization) {
        this.organization = organization;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
