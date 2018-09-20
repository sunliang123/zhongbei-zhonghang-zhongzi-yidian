package com.waben.stock.datalayer.publisher;

import org.springframework.boot.SpringApplication;

// @SpringBootApplication
//服务发现客户端
// @EnableDiscoveryClient
//服务调用
// @EnableFeignClients
//扫描包
// @ComponentScan(basePackages = { "com.waben.stock" })
public class PublisherApplication {

    public static void testMain(String[] args) {
        SpringApplication.run(PublisherApplication.class, args);
    }
}
