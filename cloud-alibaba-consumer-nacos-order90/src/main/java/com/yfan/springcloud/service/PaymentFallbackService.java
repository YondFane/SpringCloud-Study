package com.yfan.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public String getPayment(Integer id) {
        return "PaymentFallbackService---ERROR---getPayment：" + id;
    }

    @Override
    public String getPayment2(Integer id) {
        return "PaymentFallbackService---ERROR---openFeign-getPayment：" + id;
    }
}
