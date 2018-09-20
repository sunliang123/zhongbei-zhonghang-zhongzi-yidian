package com.waben.stock.applayer.promotion.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.waben.stock.applayer.promotion.business.UserBusiness;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.dto.organization.UserDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.util.PasswordCrypt;

public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UserBusiness userBusiness;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
		String password = "";
		if (authentication != null && authentication.getCredentials() != null) {
			password = authentication.getCredentials().toString();
		}
		if (!PasswordCrypt.match(password, customUserDetails.getPassword())) {
			throw new BadCredentialsException("密码错误");
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		try {
			UserDto user = userBusiness.fetchByUserName(username);
			CustomUserDetails userDetails = new CustomUserDetails(user.getId(), user.getNickname(), username,
					user.getPassword(), getAdminGrantedAuthList());
			// 设置所属代理商相关的信息
			OrganizationDto org = user.getOrg();
			userDetails.setOrgId(org.getId());
			userDetails.setOrgCode(org.getCode());
			userDetails.setOrgName(org.getName());
			userDetails.setOrgLevel(org.getLevel());
			userDetails.setRoleId(user.getRole());
			userDetails.setTreeCode(org.getTreeCode());
			if (user.getState() != null && user.getState()) {
				throw new DisabledException("当前用户已被冻结");
			}
			return userDetails;
		} catch (ServiceException ex) {
			if (ExceptionConstant.STAFF_NOT_FOUND_EXCEPTION.equals(ex.getType())) {
				throw new UsernameNotFoundException("用户名不存在");
			} else {
				throw new AuthenticationServiceException("获取后台管理用户发生异常!" + ex.getMessage());
			}
		}
	}

	private List<GrantedAuthority> getAdminGrantedAuthList() {
		List<GrantedAuthority> grantedAuthList = new ArrayList<>();
		grantedAuthList.add(new SimpleGrantedAuthority("Role_Admin"));
		return grantedAuthList;
	}

}
