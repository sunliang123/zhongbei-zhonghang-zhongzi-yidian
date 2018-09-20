package com.waben.stock.interfaces.dto.manage;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
public class RoleDto implements Serializable{

    private Long id;
    private String name;
    private String code;
    private String description;
    private Set<PermissionDto> permissionDtos;
    private Set<MenuDto> menusDtos;
    private Long organization;
    private Integer type;
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

    public Set<PermissionDto> getPermissionDtos() {
    	if(permissionDtos != null) {
    		return CopyBeanUtils.copySetBeanPropertiesToSet(permissionDtos, PermissionDto.class);
    	}
        return permissionDtos;
    }

    public void setPermissionDtos(Set<PermissionDto> permissionDtos) {
        this.permissionDtos = permissionDtos;
    }

    public Set<MenuDto> getMenusDtos() {
    	if(menusDtos != null) {
    		return CopyBeanUtils.copySetBeanPropertiesToSet(menusDtos, MenuDto.class);
    	}
        return menusDtos;
    }

    public void setMenusDtos(Set<MenuDto> menusDtos) {
        this.menusDtos = menusDtos;
    }

    public void setPermissions(Set<PermissionDto> permissions) {
        this.permissionDtos = permissions;
    }

    public void setMenus(Set<MenuDto> menus) {
        this.menusDtos = menus;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
