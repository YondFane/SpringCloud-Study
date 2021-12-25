package com.yfan.springcloud.controller;

import com.yfan.springcloud.entities.CommonResult;
import com.yfan.springcloud.entities.Payment;
import com.yfan.springcloud.loadbalance.impl.CustomLoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/*
 * OrderController
 * @author YFAN
 * @date 2021/12/20/020
 */
@RestController
@Slf4j
public class OrderController {

    //    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    RestTemplate restTemplateByRibbon;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    CustomLoadBalance customLoadBalance;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create/", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getpayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
    // 用于测试Ribbon的负载均衡策略
    @GetMapping("/consumer/ribbon/payment/get/{id}")
    public CommonResult<Payment> getpayment2(@PathVariable("id") Long id) {
        return restTemplateByRibbon.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    // 用于测试自定义的负载均衡策略
    @GetMapping("/consumer/payment/lb/get/{id}")
    public CommonResult<Payment> getpayment3(@PathVariable("id") Long id) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("cloud-payment-service");
        if (serviceInstances == null || serviceInstances.size() <= 0) {
            return null;
        }
        ServiceInstance instances = customLoadBalance.instances(serviceInstances);
        log.info("instances.getUri():{}",instances.getUri());
        return restTemplateByRibbon.getForObject(instances.getUri() + "/payment/get/" + id, CommonResult.class);
    }
}
