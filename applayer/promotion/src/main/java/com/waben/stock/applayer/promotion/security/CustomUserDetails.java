package com.waben.stock.applayer.promotion.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -8766788348000173980L;

	private Long userId;
	private String nickname;
	private String username;
	private String password;
	private String token;
	/**
	 * 代理商ID
	 */
	private Long orgId;
	/**
	 * 代理商代码
	 */
	private String orgCode;
	/**
	 * 代理商名称
	 */
	private String orgName;
	/**
	 * 代理商层级
	 */
	private Integer orgLevel;
	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 树结构代码
	 */
	private String treeCode;

	private Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(Long userId, String nickname, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public Long getUserId() {
		if (userId != null) {
			return userId.longValue();
		}
		return 0L;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

}
