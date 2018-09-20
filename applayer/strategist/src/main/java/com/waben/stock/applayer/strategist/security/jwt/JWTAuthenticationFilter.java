package com.waben.stock.applayer.strategist.security.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.waben.stock.applayer.strategist.security.CustomUserDetails;
import com.waben.stock.applayer.strategist.security.WebSecurityConfig;
import com.waben.stock.applayer.strategist.service.RedisCache;

public class JWTAuthenticationFilter extends GenericFilterBean {

	private RedisCache redisCache;
	
	private static final String BLACKUSER_REDISKEY = "BLACKUSER";
	
	private boolean isNoneAuthPath(String url) {
		boolean isMatch = false;
		for (String noneAuthPath : WebSecurityConfig.noneAuthPaths) {
			if (noneAuthPath.indexOf("**") > 0) {
				noneAuthPath = noneAuthPath.replaceAll("\\*\\*", "");
			}
			if (url.indexOf(noneAuthPath) > 0) {
				isMatch = true;
				break;
			}
		}
		return isMatch;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// 获取请求中的token
		String token = ((HttpServletRequest) request).getHeader(JWTTokenUtil.HEADER_STRING);
		if (token != null && !"".equals(token)) {
			// 获取token中的信息
			try {
				Map<String, Object> tokenInfo = JWTTokenUtil.getTokenInfo(token);
				// 判断username是否存在，以及token是否过期
				String username = (String) tokenInfo.get("sub");
				Long userId = new Long((Integer) tokenInfo.get("userId"));
				String isBlack = redisCache.get(BLACKUSER_REDISKEY + "_" + String.valueOf(userId));
				// 判断该用户是否为黑名单用户
				if(isBlack != null && "true".equals(isBlack)) {
					String url = httpRequest.getRequestURL().toString();
					if (!isNoneAuthPath(url)) {
						httpRequest.getSession().invalidate();
						HttpServletResponse httpResponse = (HttpServletResponse) response;
						httpResponse.setStatus(409);
						if (SecurityContextHolder.getContext().getAuthentication() != null) {
							SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
						}
						return;
					}
				} else {
					String serialCode = (String) tokenInfo.get("serialCode");
					if (username != null && !"".equals(username)) {
						Date exp = (Date) tokenInfo.get("exp");
						if (exp != null && exp.getTime() * 1000 > System.currentTimeMillis()) {
							// 如果为正确的token，将身份验证放入上下文中
							List<GrantedAuthority> authorities = AuthorityUtils
									.commaSeparatedStringToAuthorityList((String) tokenInfo.get("authorities"));
							CustomUserDetails userDeatails = new CustomUserDetails(userId, serialCode, username, null,
									authorities);
							UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
									username, null, authorities);
							authentication.setDetails(userDeatails);
							SecurityContextHolder.getContext().setAuthentication(authentication);
						}
					}
				}
			} catch (Exception ex) {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.setStatus(HttpStatus.FORBIDDEN.value());
			}
		}

		filterChain.doFilter(request, response);
	}

	public void setRedisCache(RedisCache redisCache) {
		this.redisCache = redisCache;
	}

}