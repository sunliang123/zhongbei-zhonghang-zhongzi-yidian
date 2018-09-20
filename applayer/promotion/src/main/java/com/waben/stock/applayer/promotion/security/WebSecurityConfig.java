package com.waben.stock.applayer.promotion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.waben.stock.applayer.promotion.business.cache.RedisCache;
import com.waben.stock.applayer.promotion.security.filter.JwtAuthenticationFilter;
import com.waben.stock.applayer.promotion.security.filter.LoginFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RedisCache redisCache;
	
	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}
	
	public JwtAuthenticationFilter jWTAuthenticationFilter() {
		JwtAuthenticationFilter result = new JwtAuthenticationFilter();
		result.setRedisCache(redisCache);
		return result;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable().and().csrf().disable();
		// swagger
		http.authorizeRequests().antMatchers("/swagger-ui.html").permitAll();
		http.authorizeRequests().antMatchers("/webjars/**").permitAll();
		http.authorizeRequests().antMatchers("/swagger-resources/**").permitAll();
		http.authorizeRequests().antMatchers("/v2/api-docs").permitAll();
		http.authorizeRequests().antMatchers("/configuration/**").permitAll();
		// 部分开放接口
		http.authorizeRequests().antMatchers("/file/upload").permitAll();
		// 其余接口
		http.authorizeRequests().antMatchers("/**").authenticated();
		// 添加一个过滤器，拦截所有POST访问/login的请求，进行初步处理
		http.addFilterBefore(new LoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		// 添加一个过滤器验证其他请求的Token是否合法
		http.addFilterBefore(jWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		// 退出登陆
		http.logout().logoutSuccessHandler(new CustomLogoutSuccessHandler());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
	}
}