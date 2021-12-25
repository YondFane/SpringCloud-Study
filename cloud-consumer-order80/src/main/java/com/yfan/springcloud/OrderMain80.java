package com.yfan.springcloud;

import com.yfan.rule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

/*
 * 订单服务
 * @author YFAN
 * @date 2021/12/20/020
 */
@SpringBootApplication
// RibbonClient注解指定加载自定义配置类
//@RibbonClient(name="CLOUD-PAYMENT-SERVICE", configuration= MyRule.class)

//@LoadBalancerClient(name="CLOUD-PAYMENT-SERVICE", configuration= MyRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
