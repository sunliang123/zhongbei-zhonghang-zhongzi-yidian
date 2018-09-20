package com.waben.stock.applayer.admin.security.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.waben.stock.applayer.admin.security.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {

	/**
	 * token有效时间
	 */
	static final long EXPIRATIONTIME = 4320000000000000000L;
	/**
	 * JWT密码
	 */
	static final String SECRET = "~LMA!_@NET#_$BUDDHA%";
	/**
	 * Token前缀
	 */
	static final String TOKEN_PREFIX = "LmaLoveYan";
	/**
	 * 存放Token的Header Key
	 */
	static final String HEADER_STRING = "Authorization";

	/**
	 * 生成token令牌
	 * 
	 * @param userId
	 *            用户ID
	 * @param name
	 *            姓名
	 * @param username
	 *            当前用户名
	 * @param isAdmin
	 *            是否管理用户
	 * @param grantedAuthStr
	 *            权限（多个权限以英文逗号分割）
	 * @return 令牌
	 */
	public static String generateToken(CustomUserDetails customUserDetails) {
		StringBuilder grantedAuthStr = new StringBuilder();
		Collection<? extends GrantedAuthority> grantedAuthList = customUserDetails.getAuthorities();
		if (grantedAuthList != null && grantedAuthList.size() > 0) {
			for (GrantedAuthority grantedAuth : grantedAuthList) {
				grantedAuthStr.append(grantedAuth.getAuthority() + ",");
			}
		}
		return Jwts.builder().claim("authorities", grantedAuthStr).claim("userId", customUserDetails.getUserId())
				.claim("nickname", customUserDetails.getNickname()).setSubject(customUserDetails.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	public static Map<String, Object> getTokenInfo(String token) throws Exception {
		// 解析 Token
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
		// 获取token中的信息并返回
		Map<String, Object> result = new HashMap<>();
		result.put("sub", claims.getSubject());
		result.put("userId", claims.get("userId"));
		result.put("nickname", claims.get("nickname"));
		result.put("authorities", claims.get("authorities"));
		result.put("exp", claims.getExpiration());
		return result;
	}

	public static List<GrantedAuthority> getAppGrantedAuthList() {
		List<GrantedAuthority> grantedAuthList = new ArrayList<>();
		grantedAuthList.add(new SimpleGrantedAuthority("Role_App"));
		return grantedAuthList;
	}

}
