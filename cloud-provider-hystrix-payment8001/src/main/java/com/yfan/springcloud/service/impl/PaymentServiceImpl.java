package com.yfan.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yfan.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/*
 * @author YFAN
 * @date 2021/12/20/020
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    /*
     * 测试Hystrix
     * 访问正常情况
     * @author YFAN
     * @date 2021/12/26/026
     */
    @Override
    public String paymentInfoOk(Long id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoOk,id: " + id;
    }

    /*
     * 测试Hystrix
     * 访问超时情况
     * @author YFAN
     * @date 2021/12/26/026
     */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            // 调用超时时间，超时后调用fallbackMethod指定的方法
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    @Override
    public String paymentInfoTimeOut(Long id) {
        long time = 3;
        try {
            // 休眠
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoTimeOut,id: " + id
                + "\t耗时" + time + "秒";
    }
    // 调用超时处理方法
    public String paymentInfoTimeOutHandler(Long id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoTimeOutHandler,id: "
                + id + "/t系统繁忙，请稍后再试";
    }
}
