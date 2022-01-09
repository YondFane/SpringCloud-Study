package com.yfan.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yfan.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OpenFeignOrderController {
    @Qualifier("com.yfan.springcloud.service.PaymentService")
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/order/openfeign/payment/get/{id}")
    public String paymentById(@PathVariable("id") Integer id) {
//        // id为0抛出异常
//        if (id == 0) {
//            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常！");
//        }
        String result = paymentService.getPayment2(id);
        log.info("order/payment/get---result:{}", result);
        return result;
    }
}
