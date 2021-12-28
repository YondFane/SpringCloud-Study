# Gateway

**[Spring Cloud Gateway官网](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)**

### 1、引入依赖

```html
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
        <dependency>
            <groupId>com.yfan.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>
```

### 2、applicaton.yml配置

```=
server:
  port: 9527

spring:
  application:
    name: cloud-gateway
  cloud:
    # 网关配置
    gateway:
      routes:
        - id: payment_routh1 #payment_route 路由ID，没有固定规则但必须唯一，建议配合服务名
          uri: http://localhost:8001 #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**  #断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_route 路由ID，没有固定规则但必须唯一，建议配合服务名
          uri: http://localhost:8001 #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/discovery  #断言，路径相匹配的进行路由
```

当访问 http://localhost:9527/payment/get/31，其实访问的是 http://localhost:8001/payment/get/31

当访问 http://localhost:9527/payment/discovery，其实访问的是 http://localhost:8001/payment/discoverry

**除了再yml中配置Gateway外，也可以使用配置类进行配置**

```java
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("payment_routh1", r-> r.path("/payment/get/**").uri("http://localhost:8001"));
        routes.route("payment_routh2", r-> r.path("/payment/discovery").uri("http://localhost:8001"));
        return routes.build();
    }

}
```

