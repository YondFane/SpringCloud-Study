package com.yfan.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomConfig {

    @Bean
    @LoadBalanced//负载均衡（集群使用）
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // 用于测试Ribbon的负载均衡策略
    @Bean
    public RestTemplate restTemplateByRibbon() {
        return new RestTemplate();
    }
}
