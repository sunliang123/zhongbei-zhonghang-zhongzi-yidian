package com.waben.stock.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
// @EnableEurekaServer
@Controller
public class DiscoveryApplication {


    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }


}
