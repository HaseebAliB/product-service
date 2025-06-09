package com.product.service.gateway.globalgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication

public class GlobalGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlobalGatewayApplication.class, args);
    }

}
