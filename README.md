# SpringCloud-Study

## 一、Cloud升级

![image-20211220004038849](./NoteFile/cloud升级.png)

**CAP**

- C 一致性(Consistency)
- A 可用性(Availability)
- P 分区容错性(Partition tolerance)

**CAP理论核心：一个分布式系统不可能同时很好的满足一致性、可用性和分区容错性三个需求。**

**经典CAP图**

![image-20211225171422583](./NoteFile/image-20211225171422583.png)



### 1、服务注册与发现

#### 1.1 Eureka服务注册与发现（AP）

**Eureka集群**

- cloud-eureka-server7001
- cloud-eureka-server7002
- cloud-eureka-server7003

**服务集群**

- cloud-provider-payment8001
- cloud-provider-payment8002

**消费者**

- cloud-consumer-order80

#### 1.2 Zookeeper服务注册与发现（CP）

- cloud-provider-payment8004
- cloud-consumerzk-order80



#### 1.3 Consul服务注册与发现（CP）

**[Consul下载](https://www.consul.io/downloads)**

**[Consul使用教程](https://blog.csdn.net/qq_31236849/article/details/119829213?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.opensearchhbase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.opensearchhbase)**

- cloud-provider-payment8005
- cloud-consumerconsul-order80

#### 1.4 Nacos Discovery服务注册与发现（默认AP，可切换到CP）

**[Nacos官网](https://nacos.io/zh-cn/docs/quick-start.html)**

项目：

- cloud-alibaba-provider-payment9001
- cloud-alibaba-provider-payment9002
- cloud-alibaba-consumer-nacos-order80

**[Nacos集群部署说明](https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html)**

![deployDnsVipMode.jpg](https://nacos.io/img/deployDnsVipMode.jpg)

Nacos自带内置数据库derby，如果需要配置Nacos集群，需要保持数据一致性，使用的是MySQL数据库。

### 2、服务调用

#### 2.1 Rabbion服务调用负载均衡



#### 2.2 OpenFeign服务调用

**Eureka集群**

- cloud-eureka-server7001
- cloud-eureka-server7002
- cloud-eureka-server7003

**服务集群**

- cloud-provider-payment8001
- cloud-provider-payment8002

**消费者**

- cloud-consumer-feign-order80



### 3、服务降级

#### 3.1 Hystrix

- cloud-eureka-server-stand-alone7001
- cloud-provider-hystrix-payment8001
- cloud-consumer-feign-hystrix-order80



### 4、服务网关

- 反向代理
- 鉴权
- 流量控制
- 熔断
- 日志监控
- ......

![image-20211228230833197](./NoteFile/image-20211228230833197.png)

#### 4.1 Zuul

已经过时不再使用，已被Gateway替代。

#### 4.2 Gateway

GateWay是SpringCloud团队研发的，基于异步非阻塞模型上进行开发的，性能不需要担心。

项目：

- cloud-eureka-server7001
- cloud-eureka-server7002
- cloud-eureka-server7003
- cloud-provider-payment8001
- cloud-provider-payment8002
- cloud-gateway-gateway9527



### 5、服务配置

​		微服务意味着要将单体应用中的业务拆分成一个个子服务，每个子服务的粒度相对较小，因此系统中会出现大量的服务。由于每个服务都需要必要的配置信息才能运行（例如：application.yml），所以一套集中式的、动态的配置管理设置是必不可少的。

#### 5.1 Spring Cloud Config

**[Github地址：spring-cloud/spring-cloud-config](https://github.com/spring-cloud/spring-cloud-config)**

**[官网：Spring Cloud Config](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)**

​		Spring Cloud Config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。

项目：

- cloud-eureka-server-stand-alone7001
- cloud-config-center3344
- cloud-config-client3355

#### 5.2 Nacos Config

**[Nacos官网](https://nacos.io/zh-cn/docs/quick-start.html)**

项目：

- cloud-alibaba-config-client3377



### 6、服务总线

#### 6.1 Spring Cloud Bus

​		Spring Cloud Bus能管理和传播分布式系统间的消息，就像一个分布式执行器，可用于广播状态更改、时间推送等，也可以当作微服务间的通信通道。仅支持两种消息代理：RabbitMQ和Kafka。

项目：

- cloud-eureka-server-stand-alone7001
- cloud-config-center3344
- cloud-config-client3355
- cloud-config-client3366
