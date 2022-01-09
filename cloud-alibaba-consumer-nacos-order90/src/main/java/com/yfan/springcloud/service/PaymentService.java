package com.yfan.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * @author YFAN
 * @date 2022/1/8/008
 */
@FeignClient(name = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    String getPayment(@PathVariable("id") Integer id);

    @GetMapping("/payment/openfeign/get/{id}")
    public String getPayment2(@PathVariable("id") Integer id);
}
