package com.waben.stock.datalayer.activity;

import org.springframework.boot.SpringApplication;

// @EnableDiscoveryClient
//服务调用
// @EnableFeignClients
//断路器
// @EnableHystrix
//扫描包
// @ComponentScan(basePackages = { "com.waben.stock" })
// @SpringBootApplication
// @EnableScheduling
public class ActivityApplication {
	
	public static void testMain(String[] args) {
		SpringApplication.run(ActivityApplication.class, args);
	}
	
}
