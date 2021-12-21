package com.yfan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
 * 支付服务启动类
 * @author YFAN
 * @date 2021/12/20/020
 */
@SpringBootApplication
//@EnableEurekaClient//其实不加也可以，默认根据引用的Eureka包自动添加注解
//@EnableDiscoveryClient//其实不加也可以，默认根据引用的服务发现Jar包自动添加注解
public class PaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class, args);
    }
}