package com.waben.stock.applayer.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//服务发现客户端
// @EnableDiscoveryClient
//服务调用
// @EnableFeignClients
//断路器
// @EnableHystrix
@ComponentScan(basePackages = { "com.waben.stock" })
public class PromotionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromotionApplication.class, args);
	}
}
