package com.waben.stock.applayer.tactics.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.waben.stock.applayer.tactics.dto.publisher.PublisherCapitalAccountDto;
import com.waben.stock.applayer.tactics.security.CustomUserDetails;
import com.waben.stock.applayer.tactics.security.CustomUsernamePasswordAuthenticationToken;
import com.waben.stock.applayer.tactics.service.RedisCache;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.enums.RedisCacheKeyType;
import com.waben.stock.interfaces.exception.ExceptionMap;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.CapitalAccountInterface;
import com.waben.stock.interfaces.service.publisher.PublisherInterface;
import com.waben.stock.interfaces.util.JacksonUtil;

import io.swagger.models.HttpMethod;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private static final String loginUrl = "/login";

	private PublisherInterface publisherReference;

	private CapitalAccountInterface accountService;

	private RedisCache redisCache;

	public JWTLoginFilter(AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(loginUrl, HttpMethod.POST.name()));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		CustomUsernamePasswordAuthenticationToken authentication = new CustomUsernamePasswordAuthenticationToken(
				req.getParameter("phone"), req.getParameter("password"));
		return getAuthenticationManager().authenticate(authentication);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		// step 1 : 生成token
		CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
		String token = JWTTokenUtil.generateToken(customUserDetails);
		// step 2 : 缓存token到redis
		redisCache.set(String.format(RedisCacheKeyType.AppToken.getKey(), customUserDetails.getUsername()), token);
		customUserDetails.setToken(token);
		// step 3 : 返回用户信息和token到客户端
		Long publisherId = customUserDetails.getUserId();
		Response<PublisherDto> publisherResp = publisherReference.fetchById(publisherId);
		Response<CapitalAccountDto> accountResp = accountService.fetchByPublisherId(publisherId);
		PublisherCapitalAccountDto data = new PublisherCapitalAccountDto(publisherResp.getResult(),
				accountResp.getResult());
		data.setToken(token);
		res.setContentType("application/json;charset=utf-8");
		res.setStatus(HttpServletResponse.SC_OK);
		res.getWriter().println(JacksonUtil.encode(new Response<>(data)));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		Response<String> result = new Response<>();
		if (failed instanceof DisabledException) {
			result.setCode(ExceptionConstant.PUBLISHER_DISABLED_EXCEPITON);
			result.setMessage(ExceptionMap.exceptionMap.get(ExceptionConstant.PUBLISHER_DISABLED_EXCEPITON));
		} else {
			result.setCode(ExceptionConstant.USERNAME_OR_PASSWORD_ERROR_EXCEPTION);
			result.setMessage(ExceptionMap.exceptionMap.get(ExceptionConstant.USERNAME_OR_PASSWORD_ERROR_EXCEPTION));
		}
		response.getWriter().println(JacksonUtil.encode(result));
	}

	public void setPublisherService(PublisherInterface publisherReference) {
		this.publisherReference = publisherReference;
	}

	public void setAccountService(CapitalAccountInterface accountService) {
		this.accountService = accountService;
	}

	public void setJedisCache(RedisCache redisCache) {
		this.redisCache = redisCache;
	}

}
