package com.yfan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/*
 * @author YFAN
 * @date 2021/12/27/027
 */
@SpringBootApplication
@EnableHystrixDashboard// 开启HystrixDashboard仪表盘
public class DashBoardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(DashBoardMain9001.class, args);
    }
}
