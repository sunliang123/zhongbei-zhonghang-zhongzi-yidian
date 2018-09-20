package com.waben.stock.applayer.strategist.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全认证 工具类
 * 
 * @author luomengan
 *
 */
public class SecurityUtil {

	public static CustomUserDetails getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object details = authentication.getDetails();
		if (details != null && details instanceof CustomUserDetails) {
			return (CustomUserDetails) details;
		}
		return null;
	}

	public static Long getUserId() {
		CustomUserDetails details = getUserDetails();
		if (details != null) {
			return details.getUserId();
		} else {
			return 0L;
		}
	}

	public static String getSerialCode() {
		CustomUserDetails details = getUserDetails();
		if (details != null) {
			return details.getSerialCode();
		} else {
			return null;
		}
	}

	public static String getUsername() {
		CustomUserDetails details = getUserDetails();
		if (details != null) {
			return details.getUsername();
		} else {
			return null;
		}
	}

}
