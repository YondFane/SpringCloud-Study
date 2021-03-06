package com.yfan.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author YFAN
 * @date 2022/1/8/003
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverport;

    @GetMapping("/payment/get/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "payment-getPayment serverport:"+serverport+"\tid:"+id;
    }

    @GetMapping("/payment/openfeign/get/{id}")
    public String getPayment2(@PathVariable("id") Integer id) {
        if (id == 0) {
            throw new IllegalArgumentException("IllegalArgumentException异常,payment中ID不能为0");
        }
        return "payment-openfeignTest-getPayment2 serverport:"+serverport+"\tid:"+id;
    }
}
