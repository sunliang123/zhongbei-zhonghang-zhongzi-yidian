package com.waben.stock.interfaces.request.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author Created by zzw on 2018/5/10.
 * @desc
 */
@ApiModel(description = "请求参数用户对象")
public class UserRequest {
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 登陆用户名
     */
    @ApiModelProperty(value = "登陆用户名")
    private String username;
    /**
     * 登陆密码
     */
    @ApiModelProperty(value = "登陆密码")
    private String password;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "真实姓名")
    private String nickname;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "角色id")
    private Long role;
    @ApiModelProperty(value = "代理商id")
    private Long orgId;
    /**
     * 状态（0正常，1冻结）
     */
    @ApiModelProperty(value = "状态（0正常，1冻结）",hidden = true)
    private Boolean state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
