package com.waben.stock.applayer.promotion.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.waben.stock.applayer.promotion.security.CustomUserDetails;
import com.waben.stock.applayer.promotion.security.CustomUsernamePassword;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.ExceptionMap;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.util.JacksonUtil;

import io.swagger.models.HttpMethod;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private static final String loginUrl = "/login";

	Logger logger = LoggerFactory.getLogger(getClass());

	public LoginFilter(AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(loginUrl, HttpMethod.POST.name()));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		CustomUsernamePassword authentication = new CustomUsernamePassword(req.getParameter("username"),
				req.getParameter("password"));
		return getAuthenticationManager().authenticate(authentication);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
		customUserDetails.setPassword(null);
		String token = JwtTokenUtil.generateToken(customUserDetails);
		customUserDetails.setToken(token);
		res.setContentType("application/json;charset=utf-8");
		res.setStatus(HttpServletResponse.SC_OK);
		res.getWriter().println(JacksonUtil.encode(new Response<>(customUserDetails)));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		Response<String> result = new Response<>();
		if (failed instanceof DisabledException) {
			result.setCode(ExceptionConstant.AGENT_DISABLED_EXCEPITON);
			result.setMessage(ExceptionMap.exceptionMap.get(ExceptionConstant.AGENT_DISABLED_EXCEPITON));
		} else {
			result.setCode(ExceptionConstant.USERNAME_OR_PASSWORD_ERROR_EXCEPTION);
			result.setMessage(ExceptionMap.exceptionMap.get(ExceptionConstant.USERNAME_OR_PASSWORD_ERROR_EXCEPTION));
		}
		response.getWriter().println(JacksonUtil.encode(result));
	}

}
