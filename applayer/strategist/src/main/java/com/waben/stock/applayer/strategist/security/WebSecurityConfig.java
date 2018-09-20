package com.waben.stock.applayer.strategist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.waben.stock.applayer.strategist.security.jwt.JWTAuthenticationFilter;
import com.waben.stock.applayer.strategist.security.jwt.JWTLoginFilter;
import com.waben.stock.applayer.strategist.service.RedisCache;
import com.waben.stock.applayer.strategist.wrapper.filter.HiddenParamProcessFilter;
import com.waben.stock.interfaces.service.publisher.CapitalAccountInterface;
import com.waben.stock.interfaces.service.publisher.PublisherInterface;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PublisherInterface publisherReference;

	@Autowired
	private CapitalAccountInterface capitalAccountReference;
	
	@Autowired
	private RedisCache redisCache;
	
	public static String[] noneAuthPaths = new String[] {
		"/publisher/sendSms",
		"/publisher/register",
		"/publisher/modifyPassword",
		"/system/**",
		"/strategytype/**",
		"/buyRecord/tradeDynamic", 
		"/buyRecord/isTradeTime",
		"/stock/**",
		"/payment/tbfpaycallback", 
		"/payment/tbfpayreturn", 
		"/payment/czpaycallback",
		"/payment/czpayreturn", 
		"/payment/czwithholdcallback", 
		"/payment/recharge",
		"/payment/wabennetbankpaynotify",
		"/payment/unionpaytempfronturl",
		"/payment/wabennetbankpay/h5wbreturn",
		"/cnaps/**",
		"/jsonp/**",
		"/crawler/**",
		"/turbine/**",
		"/stockoptiontrade/cyclelists", 
		"/stockoptiontrade/tradeDynamic",
		"/alipay/callback",
		"/quickpay/sdpaycallback",
		"/quickpay/sdpayreturn",
		"/quickpay/wbreturn",
		"/quickpay/wbcallback",
		"/quickpay/protocolcallback"
	};

	public JWTAuthenticationFilter jWTAuthenticationFilter() {
		JWTAuthenticationFilter result = new JWTAuthenticationFilter();
		result.setRedisCache(redisCache);
		return result;
	}

	@Bean
	public JWTLoginFilter jwtLoginFilter() {
		try {
			JWTLoginFilter result = new JWTLoginFilter(authenticationManager());
			result.setPublisherReference(publisherReference);
			result.setAccountReference(capitalAccountReference);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("get AuthenticationManager exception!", e);
		}
	}

	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		// 静态页面
		http.authorizeRequests().antMatchers("/receiveSocket.html", "/js/**", "/bankIcon/**", "/bannerPic/**")
				.permitAll();
		// websocket
		http.authorizeRequests().antMatchers("/socket/**").permitAll();
		// 页面
		http.authorizeRequests().antMatchers("/swagger-ui.html").permitAll();
		http.authorizeRequests().antMatchers("/webjars/**").permitAll();
		http.authorizeRequests().antMatchers("/swagger-resources/**").permitAll();
		http.authorizeRequests().antMatchers("/v2/api-docs").permitAll();
		http.authorizeRequests().antMatchers("/configuration/**").permitAll();
		http.authorizeRequests().antMatchers("/guide/**").permitAll();
		http.authorizeRequests().antMatchers("/buy/**").permitAll();
		http.authorizeRequests().antMatchers("/returnUrl.html").permitAll();
		// 开放接口
		for(String noneAuthPath : noneAuthPaths) {
			http.authorizeRequests().antMatchers(noneAuthPath).permitAll();
		}
		// 其余接口
		http.authorizeRequests().antMatchers("/**").authenticated();

		// 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
		http.addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class);
		// 添加一个过滤器验证其他请求的Token是否合法
		http.addFilterBefore(new HiddenParamProcessFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class);
		http.logout().logoutSuccessHandler(new CustomLogoutSuccessHandler());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers("/css/**", "/image/**", "/js/**", "/static/js/**");
	}
}