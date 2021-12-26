package com.yfan.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yfan.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalHandler")
public class OrderHystrixController {

    @Value("${server.port}")
    private String serverport;

    @Autowired
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Long id) {
//        int i = 1/0;// 测试Feign的fallback
        String result = paymentHystrixService.paymentInfoOk(id);
        log.info("consumer-paymentInfoOk:{}", result);
        return "consumer-" + result;
    }

//    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
//            // 调用超时时间，超时后调用fallbackMethod指定的方法
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//    })
    @HystrixCommand
//    (commandProperties = {
//            // 调用超时时间，超时后调用fallbackMethod指定的方法
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Long id) {
        String result = paymentHystrixService.paymentInfoTimeOut(id);
        log.info("consumer-paymentInfoTimeOut:{}", result);
        return "consumer-" + result;
    }

    // 调用超时处理方法
    public String paymentInfoTimeOutHandler(@PathVariable("id") Long id) {
        log.info("consumer:payment服务系统繁忙，请稍后再试！");
        return "consumer:payment服务系统繁忙，请稍后再试！";
    }

    /*
     * 全局处理方法
     * @author YFAN
     * @date 2021/12/26/026
     */
    public String paymentGlobalHandler() {
        log.info("consumer---paymentGlobalHandler:系统繁忙，请稍后再试");
        return "consumer---paymentGlobalHandler:系统繁忙，请稍后再试";
    }
}
