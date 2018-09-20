package com.waben.stock.applayer.promotion.security.filter;

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

import com.waben.stock.applayer.promotion.business.cache.RedisCache;
import com.waben.stock.applayer.promotion.security.CustomUserDetails;

public class JwtAuthenticationFilter extends GenericFilterBean {

	private static final String PROMOTION_BLACKUSER_REDISKEY = "PROMOTION_BLACKUSER";

	private RedisCache redisCache;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// 获取请求中的token
		String token = ((HttpServletRequest) request).getHeader(JwtTokenUtil.HEADER_STRING);
		if (token != null && !"".equals(token)) {
			// 获取token中的信息
			try {
				Map<String, Object> tokenInfo = JwtTokenUtil.getTokenInfo(token);
				// 判断username是否存在，以及token是否过期
				String username = (String) tokenInfo.get("sub");
				Long userId = new Long((Integer) tokenInfo.get("userId"));
				String nickname = (String) tokenInfo.get("nickname");
				Long orgId = new Long((Integer) tokenInfo.get("orgId"));
				String orgCode = (String) tokenInfo.get("orgCode");
				String orgName = (String) tokenInfo.get("orgName");
				Integer orgLevel = (Integer) tokenInfo.get("orgLevel");
				Long roleId = new Long((Integer) tokenInfo.get("roleId"));
				String treeCode = (String) tokenInfo.get("treeCode");

				String isFrozen = redisCache.get(PROMOTION_BLACKUSER_REDISKEY + "_" + String.valueOf(userId));
				// 判断该用户是否为冻结用户
				if (isFrozen != null && "true".equals(isFrozen)) {
					httpRequest.getSession().invalidate();
					HttpServletResponse httpResponse = (HttpServletResponse) response;
					httpResponse.setStatus(403);
					if (SecurityContextHolder.getContext().getAuthentication() != null) {
						SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
					}
					return;
				}

				if (username != null && !"".equals(username)) {
					Date exp = (Date) tokenInfo.get("exp");
					if (exp != null && exp.getTime() * 1000 > System.currentTimeMillis()) {
						// 如果为正确的token，将身份验证放入上下文中
						List<GrantedAuthority> authorities = AuthorityUtils
								.commaSeparatedStringToAuthorityList((String) tokenInfo.get("authorities"));
						CustomUserDetails userDeatails = new CustomUserDetails(userId, nickname, username, null,
								authorities);
						userDeatails.setOrgId(orgId);
						userDeatails.setOrgCode(orgCode);
						userDeatails.setOrgName(orgName);
						userDeatails.setOrgLevel(orgLevel);
						userDeatails.setRoleId(roleId);
						userDeatails.setTreeCode(treeCode);
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								username, null, authorities);
						authentication.setDetails(userDeatails);
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
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