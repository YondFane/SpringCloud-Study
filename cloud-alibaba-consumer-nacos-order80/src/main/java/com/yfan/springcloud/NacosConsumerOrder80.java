package com.yfan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
 * @author YFAN
 * @date 2022/1/3/003
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerOrder80.class, args);
    }
}
