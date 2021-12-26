package com.yfan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * OpenFeign
 * @author YFAN
 * @date 2021/12/26/026
 */
@SpringBootApplication
@EnableFeignClients//开启OpenFeign
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
