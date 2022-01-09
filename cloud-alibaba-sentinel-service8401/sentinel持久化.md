## Sentinel配置持久化到Nacos

### 1、主要依赖

```
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
```

### 2、application.yml

```
server:
  port: 8401
spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
    sentinel:
      transport:
        #配置Sentinel Dashboard地址
        dashboard: localhost:8080
        # 默认8719，假如被占用会自动从8719开始依次+1扫描，直至找到未被占用的端口
        port: 8719
      # sentinel规则持久化到nacos
      datasource:
        dsl:
          nacos:
            server-addr: localhost:8848
            dataId: cloudalibaba-sentinel-service
            groupId: DEFAULT_GROUP
            data-type: json
            rule-type: flow

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

### 3、在nacao添加配置文件cloudalibaba-sentinel-service.json

内容如下：

```json
[
    {
         "resource":"/rateLimit/byUrl",
         "limitApp":"default",
         "grade":1,
         "count":1,
         "strategy":0,
         "controlBehavior":0,
         "clusterMode":false
    }
]
```

resource: 资源名称
 limitApp: 来源应用
 grade: 阈值类型,0表示线程,1表示QPS
 count: 单机阈值
 strategy: 流控模式,0表示直接,1表示关联,2表示链路
 controlBehavior: 流控效果,0表示快速失败,1表示Warm Up,2表示排队等待
 clusterMode: 是否集群