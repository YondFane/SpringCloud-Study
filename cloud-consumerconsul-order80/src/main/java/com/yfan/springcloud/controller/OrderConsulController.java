package com.yfan.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderConsulController {
    public static final String INVOKE_URL = "http://cloud-providerconsul-payment";

    @Autowired
    RestTemplate restTemplate;

    /*
     * 使用RestTemplate调用Consul的cloud-providerconsul-payment服务
     * @author YFAN
     * @date 2021/12/25/025
     */
    @GetMapping("/consumer/payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        log.info("调用{}返回的结果：{}", INVOKE_URL, result);
        return result;
    }

}
