# Eureka注册中心

## Eureka配置安全认证

**pom引入依赖**

```html
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

**application.yml配置**

```
server:
  port: 7001 # 官网默认端口 8761

spring:
  application:
    name: eureka-server #集群的话名称要一致
  security:
    user:
      name: root
      password: 123456


# Eureka配置
eureka:
  instance:
    hostname: eureka7001 #主机名，不配置的时候将根据操作系统的主机名来获取
    prefer-ip-address: true # 开启使用ip地址注册
    instance-id: ${spring.cloud.client.ip-address}:${server.port} #ip:port
  client:
    service-url: # 注册中心对外暴露的注册地址
      defaultZone: http://root:123456@localhost:7001/eureka/
```

eureka client配置

```
eureka:
  instance:
    prefer-ip-address: true # 开启使用ip地址注册
    instance-id: ${spring.cloud.client.ip-address}:${server.port} #ip:port
  client:
    service-url: # 注册中心对外暴露的注册地址
      defaultZone: http://root:123456@localhost:7001/eureka/,http://root:123456@localhost:7001/eureka/
```

**访问http://localhost:7001/eureka/需要用户名和密码才能访问**
