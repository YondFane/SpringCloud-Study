package com.yfan.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yfan.springcloud.entities.CommonResult;
import com.yfan.springcloud.entities.Payment;
import com.yfan.springcloud.handler.CustomHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author YFAN
 * @date 2022/1/6/006
 */
@RestController
@Slf4j
public class ReadController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "blockHandler")
    public CommonResult byResource() {
        return new CommonResult(200,"按资源名称限流测试", new Payment(10000L,"serial001"));
    }

    public CommonResult blockHandler(BlockException exception) {
        log.info("----blockHandler----{}", exception.getMessage());
        return new CommonResult(444,"服务不可应："+exception.getClass().getCanonicalName());
    }
    /*
     * blockHandlerClass指定处理类
     * blockHandler指定处理类处理方法
     */
    @GetMapping("/read/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200,"按url限流测试", new Payment(10000L,"serial001"));
    }
    /*
     * blockHandlerClass指定处理类
     * blockHandler指定处理类处理方法
     */
    @GetMapping("/read/byUrl2")
    @SentinelResource(value = "byUrl2", blockHandlerClass = CustomHandler.class, blockHandler = "blockHandler")
    public CommonResult byUrl2() {
        return new CommonResult(200,"按url限流测试", new Payment(10000L,"serial001"));
    }
}
