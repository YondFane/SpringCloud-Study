package com.yfan.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 配置类
 * @author YFAN
 * @date 2021/12/26/026
 */
@Configuration
public class FeignConfig {

    // 日志
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
