package com.waben.stock.datalayer.investors;

import org.springframework.boot.SpringApplication;

// @EnableDiscoveryClient
//服务调用
// @EnableFeignClients
//断路器
// @EnableHystrix
//扫描包
// @ComponentScan(basePackages = { "com.waben.stock" })
// @SpringBootApplication
public class InvestorsApplication {

	public static void testMain(String[] args) {
		SpringApplication.run(InvestorsApplication.class, args);
	}
}
