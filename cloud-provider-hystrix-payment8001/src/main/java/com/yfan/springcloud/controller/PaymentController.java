package com.yfan.springcloud.controller;

import com.yfan.springcloud.entities.CommonResult;
import com.yfan.springcloud.entities.Payment;
import com.yfan.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Long id) {
        String result = paymentService.paymentInfoOk(id);
        log.info("paymentInfoOk:{}", result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Long id) {
        String result = paymentService.paymentInfoTimeOut(id);
        log.info("paymentInfoError:{}", result);
        return result;
    }

    /*
     * 服务熔断
     * @author YFAN
     * @date 2021/12/27/027
     */
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("paymentService---paymentCircuitBreaker()---result:{}", result);
        return result;
    }

}
