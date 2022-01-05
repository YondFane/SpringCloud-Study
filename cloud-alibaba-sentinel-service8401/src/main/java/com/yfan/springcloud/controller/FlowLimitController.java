package com.yfan.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author YFAN
 * @date 2022/1/5/005
 */
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "---------testA-----------";
    }

    @GetMapping("/testB")
    public String testB(){
        return "---------testB-----------";
    }

}
