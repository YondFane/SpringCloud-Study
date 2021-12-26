package com.yfan.springcloud.controller;

import com.yfan.springcloud.entities.CommonResult;
import com.yfan.springcloud.entities.Payment;
import com.yfan.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;



    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("调用");
        return paymentFeignService.create(payment);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getpayment(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    // 测试OpenFeign超时
    @GetMapping("/consumer/payment/feiginTimeout")
    public String feiginTimeout() throws InterruptedException {
        // 测试OpenFeign超时
        return paymentFeignService.feiginTimeout();
    }
}
