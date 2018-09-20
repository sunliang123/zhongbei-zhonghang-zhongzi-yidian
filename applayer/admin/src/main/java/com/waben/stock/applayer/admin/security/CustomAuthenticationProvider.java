package com.waben.stock.applayer.admin.security;

import java.util.ArrayList;
import java.util.List;

import com.waben.stock.interfaces.dto.manage.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.waben.stock.applayer.admin.business.manage.StaffBusiness;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.manage.StaffDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.util.PasswordCrypt;

public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private StaffBusiness staffBusiness;

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
			StaffDto staff = staffBusiness.fetchByUserName(username);

			return new CustomUserDetails(staff.getId(), staff.getNickname(), username, staff.getPassword(),
					getAdminGrantedAuthList(staff.getRoleDto()));
		} catch (ServiceException ex) {
			if (ExceptionConstant.STAFF_NOT_FOUND_EXCEPTION.equals(ex.getType())) {
				throw new UsernameNotFoundException("用户名不存在");
			} else {
				throw new AuthenticationServiceException("获取后台管理用户发生异常!" + ex.getMessage());
			}
		}
	}

	private List<GrantedAuthority> getAdminGrantedAuthList(RoleDto dto) {
		List<GrantedAuthority> grantedAuthList = new ArrayList<>();
		grantedAuthList.add(new SimpleGrantedAuthority("Role_Admin"));
		if(dto!=null) {
			grantedAuthList.add(new SimpleGrantedAuthority(dto.getCode()));
		}
		return grantedAuthList;
	}

}
