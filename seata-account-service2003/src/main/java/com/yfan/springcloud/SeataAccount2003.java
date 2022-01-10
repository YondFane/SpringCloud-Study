package com.yfan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * @author YFAN
 * @date 2022/1/10/010
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class SeataAccount2003 {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccount2003.class, args);
    }
}
