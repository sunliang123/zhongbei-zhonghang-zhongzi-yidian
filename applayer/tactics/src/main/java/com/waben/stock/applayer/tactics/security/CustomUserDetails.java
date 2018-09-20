package com.waben.stock.applayer.tactics.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -8766788348000173980L;

	private String token;
	private String username;
	private String password;
	private Long userId;
	private String serialCode;
	private Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(Long userId, String serialCode, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.serialCode = serialCode;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
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

}
