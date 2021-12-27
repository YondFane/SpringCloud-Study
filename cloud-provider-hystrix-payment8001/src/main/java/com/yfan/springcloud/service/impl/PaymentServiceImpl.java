package com.yfan.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yfan.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/*
 * @author YFAN
 * @date 2021/12/20/020
 */
@Service
@Slf4j
// 定义全局fallback方法
//@DefaultProperties(defaultFallback = "paymentGlobalHandler")
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
//    @HystrixCommand
    @Override
    public String paymentInfoTimeOut(Long id) {
        long time = 3;
//        int i = 1/0;//测试fallback
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

    /*
     * 全局处理方法
     * @author YFAN
     * @date 2021/12/26/026
     */
    public String paymentGlobalHandler() {
        return "线程池：" + Thread.currentThread().getName() + " paymentGlobalHandler, "
                 + "/t系统繁忙，请稍后再试";
    }

    /*
     * 服务熔断
     * 10000ms内请求次数达到10次且超过60%失败触发断路器
     * @author YFAN
     * @date 2021/12/27/027
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            // 开启断路器
            @HystrixProperty(name="circuitBreaker.enabled", value = "true"),
            // 请求次数
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "10"),
            // 时间窗口期
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 错误百分比阈值(%)
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(Long id) {
        if (id < 0) {
            throw new RuntimeException("-----id不能为负数------");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t调用成功\tuuid:" + uuid;
    }
    public String paymentCircuitBreakerFallback(Long id) {
        log.info("paymentCircuitBreakerFallback-id:{}",id);
        return "-----id不能为负数------请稍后再试------";
    }
}
