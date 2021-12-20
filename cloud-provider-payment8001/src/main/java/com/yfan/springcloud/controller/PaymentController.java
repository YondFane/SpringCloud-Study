package com.yfan.springcloud.controller;

import com.yfan.springcloud.entities.CommonResult;
import com.yfan.springcloud.entities.Payment;
import com.yfan.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * @author YFAN
 * @date 2021/12/20/020
 */
@Slf4j
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;


    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("insert result:{}", result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功！", result);
        } else {
            return new CommonResult(444, "插入数据库失败！", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("query result:{}", payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功！", payment);
        } else {
            return new CommonResult(444, "查询失败，没有对应的记录！", null);
        }
    }
}
