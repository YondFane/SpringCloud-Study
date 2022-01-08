package com.yfan.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    public static final String SERVER_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/payment/get/{id}")
//    @SentinelResource(value = "order-payment-get-id")//不配置fallback
    // 配置fallback，可以处理业务异常
//    @SentinelResource(value = "order-payment-get-id", fallback = "handleFallback")
    // 配置blockHandler，只负责sentinel控制配置的违规
//    @SentinelResource(value = "order-payment-get-id", blockHandler = "blockFallback")
    // 配置blockHandler&fallback，未到流控规则执行fallback方法，进入流控则执行blockHandler方法
    @SentinelResource(value = "order-payment-get-id", fallback = "handleFallback", blockHandler = "blockFallback")
    public String paymentById(@PathVariable("id") Integer id) {
        // id为0抛出异常
        if (id == 0) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常！");
        }
        String result = restTemplate.getForObject(SERVER_URL + "/payment/get/" + id, String.class, id);
        log.info("order/payment/get---result:{}", result);
        return result;
    }

    // fallback
    public String handleFallback(Integer id, Throwable e) {
        return "handleFallback处理异常--" + e.getMessage();
    }

    // blockHandler
    public String blockFallback(Integer id, BlockException e) {
        return "blockFallback处理异常--" + e.getMessage();
    }
}
