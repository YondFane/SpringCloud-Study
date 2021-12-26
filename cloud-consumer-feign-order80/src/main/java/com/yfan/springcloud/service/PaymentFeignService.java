package com.yfan.springcloud.service;

import com.yfan.springcloud.entities.CommonResult;
import com.yfan.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @PostMapping("/payment/create")
    CommonResult create(@RequestBody Payment payment);

    @GetMapping("/payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);

    // 测试OpenFeign超时
    @GetMapping("/payment/feiginTimeout")
    public String feiginTimeout();
}
