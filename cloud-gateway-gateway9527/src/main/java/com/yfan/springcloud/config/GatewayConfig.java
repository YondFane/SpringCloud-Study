package com.yfan.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("payment_routh1", r-> r.path("/payment/get/**").uri("http://localhost:8001"));
        routes.route("payment_routh2", r-> r.path("/payment/discovery").uri("http://localhost:8001"));
        return routes.build();
    }

}
