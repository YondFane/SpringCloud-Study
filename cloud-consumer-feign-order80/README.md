# Spring Cloud集成OpenFeign

**1、引入OpenFeign依赖**

```html
		<!--OpenFeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
            <version>3.1.0</version>
        </dependency>
```

**2、启动类开启OpenFeign**

```java
@SpringBootApplication
@EnableFeignClients//开启OpenFeign
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
```

**3、开启openfeign的注解---调用哪个服务**

```java
@Component
@FeignClient("cloud-provider-payment")
public interface PaymentFeignService {

    @PostMapping("/payment/create")
    CommonResult create(@RequestBody Payment payment);

    @GetMapping("/payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);
}
```

## OpenFeign配置

```
# 默认开启
feign.httpclient.enabled=false
# 默认关闭
feign.okhttp.enabled=true
# 默认关闭
feign.hystrix.enabled=false
# 默认关闭
feign.sentinel.enabled=true
# default context 连接超时时间
feign.client.config.default.connectTimeout = 5000
# default context 读超时时间
feign.client.config.default.readTimeout = 10000
# 设置重试处理器，默认直接抛出异常
# feign.client.config.default.retryer = Class<Retryer>
# 设置日志级别，默认NONE
# feign.client.config.default.loggerLevel = FULL
```

## OpenFeign开启日志

**配置类**

```java
@Configuration
public class FeignConfig {

    // 日志
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
```

**application.yml添加配置**

```yml
logging:
  level:
    # feign日志以什么级别监控哪个接口
    com.yfan.springcloud.service.PaymentFeignService: debug
```

**访问接口日志输出结果**

```
2021-12-26 15:03:46.850 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] ---> GET http://CLOUD-PAYMENT-SERVICE/payment/get/31 HTTP/1.1
2021-12-26 15:03:46.850 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] ---> END HTTP (0-byte body)
2021-12-26 15:03:46.996 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] <--- HTTP/1.1 200 (145ms)
2021-12-26 15:03:46.997 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] connection: keep-alive
2021-12-26 15:03:46.997 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] content-type: application/json
2021-12-26 15:03:46.997 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] date: Sun, 26 Dec 2021 07:03:46 GMT
2021-12-26 15:03:46.997 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] keep-alive: timeout=60
2021-12-26 15:03:46.997 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] transfer-encoding: chunked
2021-12-26 15:03:46.997 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] 
2021-12-26 15:03:46.998 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] {"code":200,"message":"查询成功！:8002","data":{"id":31,"serial":"测试用户111"}}
2021-12-26 15:03:46.998 DEBUG 8500 --- [p-nio-80-exec-1] c.y.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] <--- END HTTP (89-byte body)
```

