package com.yfan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
 * eureka注册中心单机版
 * @author YFAN
 * @date 2021/12/26/026
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerStandAlone7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerStandAlone7001.class, args);
    }
}
