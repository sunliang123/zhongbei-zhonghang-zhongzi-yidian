package com.waben.stock.datalayer.organization;

import org.springframework.boot.SpringApplication;

// @SpringBootApplication
//服务发现客户端
// @EnableDiscoveryClient
//服务调用
// @EnableFeignClients
//扫描包
// @ComponentScan(basePackages = { "com.waben.stock" })
public class OrganizationApplication {

	public static void testMain(String[] args) {
		SpringApplication.run(OrganizationApplication.class, args);
	}
}
