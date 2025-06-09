package com.product.service.gateway.globalgateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;
@Configuration
public class RouteConfiguration {

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute(LoadBalancerClient loadBalancerClient) {
        return route("user_service")
                .route(path("/user/**"),http())
                .filter(LoadBalancerFilterFunctions.lb("USER-SERVICE"))
               .filter(CircuitBreakerFilterFunctions.circuitBreaker("UserServiceCB", URI.create("forward:/user-fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(LoadBalancerClient loadBalancerClient) {
        return route("product_service")
                .route(path("/product/**"),http())
                .filter(LoadBalancerFilterFunctions.lb("PRODUCT-SERVICE"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("ProductServiceCB", URI.create("forward:/product-fallback")))
                .build();
    }
}
