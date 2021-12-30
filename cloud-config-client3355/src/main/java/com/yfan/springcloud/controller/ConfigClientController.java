package com.yfan.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author YFAN
 * @date 2021/12/30/030
 */
@RestController
@Slf4j
@RefreshScope // 运行时刷新
public class ConfigClientController {

    // 获取config-dev.yml中的test属性值
    @Value("${test}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        log.info("configInfo:{}", configInfo);
        return configInfo;
    }

}
