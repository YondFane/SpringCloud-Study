package com.yfan.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author YFAN
 * @date 2022/1/5/005
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "---------testA-----------";
    }

    @GetMapping("/testB")
    public String testB() {
        return "---------testB-----------";
    }

    @GetMapping("/testC")
    public String testC() {
        int i = 10 / 0;
        return "---------testC-----------";
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
        return "---------testD-----------";
    }

    @GetMapping("/hostKey")
    /*
     *  对于 @SentinelResource 注解方式定义的资源，
     * 若注解作用的方法上有参数，Sentinel 会将它们作为参数传入 SphU.entry(res, args)。
     * 比如以下的方法里面 uid 和 type 会分别作为第一个和第二个参数传入 Sentinel API，
     * 从而可以用于热点规则判断
     * blockHandler指定处理方法
     */
    @SentinelResource(value = "hostKey", blockHandler = "dealHostKey")
    public String hostKey(@RequestParam(value = "id", required = false) String id) {
        return "----hostKey----test----";
    }

    public String dealHostKey(String id, BlockException exception) {
        log.info("----dealHostKey----{}", exception.getMessage());
        return "----dealHostKey----" + exception.getMessage();
    }

}
