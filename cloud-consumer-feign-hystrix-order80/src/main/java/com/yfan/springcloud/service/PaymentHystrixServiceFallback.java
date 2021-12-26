package com.yfan.springcloud.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class PaymentHystrixServiceFallback implements PaymentHystrixService {
    @Override
    public String paymentInfoOk(Long id) {
        return "PaymentHystrixServiceFallback----paymentInfoOk";
    }

    @Override
    public String paymentInfoTimeOut(Long id) {
        return "PaymentHystrixServiceFallback----paymentInfoTimeOut";
    }
}
