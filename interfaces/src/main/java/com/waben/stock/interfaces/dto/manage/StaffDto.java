package com.waben.stock.interfaces.dto.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Author zengzhiwei
 * @Create 2018/4/23 15:18
 */
@ApiModel(value = "StaffDto",description = "角色对象")
public class StaffDto implements Serializable {

	@ApiModelProperty(value = "员工id")
	private Long id;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private String nickname;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String userName;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;
	/**
	 * 加密salt
	 */
	@ApiModelProperty(value = "加密salt")
	private String salt;
	/**
	 * 员工状态
	 */
	@ApiModelProperty(value = "员工状态")
	private Boolean state;
	/**
	 * 注册时间
	 */
	@ApiModelProperty(value = "注册时间")
	private Date createTime = new Date();
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**
	 * 登录时间
	 */
	@ApiModelProperty(value = "登录时间")
	private Date loginTime;
	/**
	 * 登录IP
	 */
	@ApiModelProperty(value = "登录IP")
	private String ip;
	@ApiModelProperty(value = "对应的角色")
	private RoleDto roleDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public RoleDto getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleDto roleDto) {
		this.roleDto = roleDto;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
