package com.waben.stock.interfaces.vo.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
//@ApiModel(value="UserVo",description="管理员对象")
public class UserVo {
//    @ApiModelProperty(value = "管理员id")
    private Long id;
    /**
     * 登陆用户名
     */
//    @ApiModelProperty(value = "角色名称")
    private String username;
    /**
     * 登陆密码
     */
//    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 昵称
     */
//    @ApiModelProperty(value = "真实姓名")
    private String nickname;
    /**
     * 创建时间
     */
//    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 所属机构
     */
//    @ApiModelProperty(value = "代理商对象",hidden = true)
    private OrganizationVo org;
//    @ApiModelProperty(value = "代理商id")
    private Long orgId;
//    @ApiModelProperty(value = "角色id")
    private Long role;
//    @ApiModelProperty(value = "角色名称")
    private String roleName;
//    @ApiModelProperty(value = "角色代码",hidden = true)
    private String code;
//    @ApiModelProperty(value = "代理商名称")
    private String orgName;
    /**
     * 状态（0正常，1冻结）
     */
//    @ApiModelProperty(value = "状态")
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

    public OrganizationVo getOrg() {
        return org;
    }

    public void setOrg(OrganizationVo org) {
        this.org = org;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
