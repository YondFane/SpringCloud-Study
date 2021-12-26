package com.yfan.springcloud.controller;

import com.yfan.springcloud.entities.CommonResult;
import com.yfan.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import com.yfan.springcloud.service.PaymentService;

import java.util.List;

/*
 * @author YFAN
 * @date 2021/12/20/020
 */
@Slf4j
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverport;

    @Autowired
    DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("insert result:{}", result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功！:" + serverport, result);
        } else {
            return new CommonResult(444, "插入数据库失败！:" + serverport, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("query result:{}", payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功！:" + serverport, payment);
        } else {
            return new CommonResult(444, "查询失败，没有对应的记录！:" + serverport, null);
        }
    }

    @GetMapping("payment/discovery")
    public Object discovery() {
        // 获取所有服务信息
        List<String> services = discoveryClient.getServices();
        services.forEach(o->{
            log.info("discovery()-service:{}", o);
        });
        // 获取cloud-payment-service服务信息
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        instances.forEach(o->log.info("discovery()-cloud-payment-service:{}",o));
        return discoveryClient;
    }

    // 测试OpenFeign超时
    @GetMapping("/payment/feiginTimeout")
    public String feiginTimeout() {
        try {
            // 休眠
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return "feiginTimeout执行完成:" + serverport;
    }
}
