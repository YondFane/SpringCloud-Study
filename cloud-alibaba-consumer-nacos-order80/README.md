# 服务注册 Nacos Discovery

### **[Nacos discovery教程](https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-discovery)**

### 服务注册发现: Nacos Discovery Starter

服务发现是微服务架构体系中最关键的组件之一。如果尝试着用手动的方式来给每一个客户端来配置所有服务提供者的服务列表是一件非常困难的事，而且也不利于 服务的动态扩缩容。Nacos Discovery Starter 可以帮助您将服务自动注册到 Nacos 服务端并且能够动态感知和刷新某个服务实例的服务列表。除此之外，Nacos Discovery Starter 也将服务实例自身的一些元数据信息-例如 host，port,健康检查URL，主页等-注册到 Nacos 。Nacos 的获取和启动方式可以参考 [Nacos 官网](https://nacos.io/zh-cn/docs/quick-start.html)。

#### 如何引入 Nacos Discovery Starter

如果要在您的项目中使用 Nacos 来实现服务发现，使用 group ID 为 `com.alibaba.cloud` 和 artifact ID 为 `spring-cloud-starter-alibaba-nacos-discovery` 的 starter。

```
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

#### 启动一个 Provider 应用

以下步骤向您展示了如何将一个服务注册到 Nacos。

1. pom.xml的配置。一个完整的 pom.xml 配置如下所示：

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>open.source.test</groupId>
    <artifactId>nacos-discovery-test</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>nacos-discovery-test</name>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>${spring.boot.version}</version>
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring.cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${spring.cloud.alibaba.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

1. application.properties 配置。一些关于 Nacos 基本的配置也必须在 application.properties(也可以是application.yaml)配置，如下所示： application.properties

```
server.port=8081
spring.application.name=nacos-producer
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
management.endpoints.web.exposure.include=*
```

| Note | 如果不想使用 Nacos 作为您的服务注册与发现，可以将 `spring.cloud.nacos.discovery.enabled` 设置为 `false`。 |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

1. 启动 Provider 示例。如下所示：

```
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosProducerDemoApplication.class, args);
    }

    @RestController
    public class EchoController {
        @GetMapping(value = "/echo/{string}")
        public String echo(@PathVariable String string) {
            return "Hello Nacos Discovery " + string;
        }
    }
}
```

这个时候你就可以在 Nacos的控制台上看到注册上来的服务信息了。

| Note | 再启动 Provider 应用之前 请先将 Nacos 服务启动。具体启动方式可参考 [Nacos 官网](https://nacos.io/zh-cn/docs/quick-start.html)。 |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

### 服务的 EndPoint

spring-cloud-starter-alibaba-nacos-discovery 在实现的时候提供了一个EndPoint,EndPoint的访问地址为 `http://ip:port/actuator/nacos-discovery`。 EndPoint 的信息主要提供了两类:

```
1、subscribe: 显示了当前有哪些服务订阅者
2、NacosDiscoveryProperties: 显示了当前服务实例关于 Nacos 的基础配置
```

一个服务实例访问 EndPoint 的信息如下所示：

```
{
  "subscribe": [
    {
      "jsonFromServer": "",
      "name": "nacos-provider",
      "clusters": "",
      "cacheMillis": 10000,
      "hosts": [
        {
          "instanceId": "30.5.124.156#8081#DEFAULT#nacos-provider",
          "ip": "30.5.124.156",
          "port": 8081,
          "weight": 1.0,
          "healthy": true,
          "enabled": true,
          "cluster": {
            "serviceName": null,
            "name": null,
            "healthChecker": {
              "type": "TCP"
            },
            "defaultPort": 80,
            "defaultCheckPort": 80,
            "useIPPort4Check": true,
            "metadata": {

            }
          },
          "service": null,
          "metadata": {

          }
        }
      ],
      "lastRefTime": 1541755293119,
      "checksum": "e5a699c9201f5328241c178e804657e11541755293119",
      "allIPs": false,
      "key": "nacos-producer",
      "valid": true
    }
  ],
  "NacosDiscoveryProperties": {
    "serverAddr": "127.0.0.1:8848",
    "endpoint": "",
    "namespace": "",
    "logName": "",
    "service": "nacos-provider",
    "weight": 1.0,
    "clusterName": "DEFAULT",
    "metadata": {

    },
    "registerEnabled": true,
    "ip": "30.5.124.201",
    "networkInterface": "",
    "port": 8082,
    "secure": false,
    "accessKey": "",
    "secretKey": ""
  }
}
```

### 启动一个 Consumer 应用

Consumer 的应用可能还没像启动一个 Provider 应用那么简单。因为在 Consumer 端需要去调用 Provider 端提供的REST 服务。例子中我们使用最原始的一种方式， 即显示的使用 LoadBalanceClient 和 RestTemolate 结合的方式来访问。 pom.xml 和 application.properties 的配置可以参考 1.2 小结。启动一个 Consumer应用的示例代码如下所示：

| Note | 通过带有负载均衡的RestTemplate 和 FeignClient 也是可以访问的。 |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

```
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerApp {

    @RestController
    public class NacosController{

        @Autowired
        private LoadBalancerClient loadBalancerClient;
        @Autowired
        private RestTemplate restTemplate;

        @Value("${spring.application.name}")
        private String appName;

        @GetMapping("/echo/app-name")
        public String echoAppName(){
            //使用 LoadBalanceClient 和 RestTemolate 结合的方式来访问
            ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
            String url = String.format("http://%s:%s/echo/%s",serviceInstance.getHost(),serviceInstance.getPort(),appName);
            System.out.println("request url:"+url);
            return restTemplate.getForObject(url,String.class);
        }

    }

    //实例化 RestTemplate 实例
    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

    public static void main(String[] args) {

        SpringApplication.run(NacosConsumerApp.class,args);
    }
}
```

这个例子中我们注入了一个 LoadBalancerClient 的实例，并且手动的实例化一个 RestTemplate，同时将 `spring.application.name` 的配置值 注入到应用中来， 目的是调用 Provider 提供的服务时，希望将当前配置的应用名给显示出来。

| Note | 在启动 Consumer 应用之前请先将 Nacos 服务启动好。具体启动方式可参考 [Nacos 官网](https://nacos.io/zh-cn/docs/quick-start.html)。 |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

启动后，访问 Consumer 提供出来的 `http://ip:port/echo/app-name` 接口。我这里测试启动的 port是 8082。访问结果如下所示：

```
访问地址：http://127.0.0.1:8082/echo/app-name
访问结果：Hello Nacos Discovery nacos-consumer
```

### 关于 Nacos Starter 更多的配置项信息

更多关于 spring-cloud-starter-alibaba-nacos-discovery 的 starter 配置项如下所示:

| 配置项                | Key                                              | 默认值                       | 说明                                                         |
| --------------------- | ------------------------------------------------ | ---------------------------- | ------------------------------------------------------------ |
| `服务端地址`          | `spring.cloud.nacos.discovery.server-addr`       | `无`                         | `Nacos Server 启动监听的ip地址和端口`                        |
| `服务名`              | `spring.cloud.nacos.discovery.service`           | `${spring.application.name}` | `给当前的服务命名`                                           |
| `服务分组`            | `spring.cloud.nacos.discovery.group`             | `DEFAULT_GROUP`              | `设置服务所处的分组`                                         |
| `权重`                | `spring.cloud.nacos.discovery.weight`            | `1`                          | `取值范围 1 到 100，数值越大，权重越大`                      |
| `网卡名`              | `spring.cloud.nacos.discovery.network-interface` | `无`                         | `当IP未配置时，注册的IP为此网卡所对应的IP地址，如果此项也未配置，则默认取第一块网卡的地址` |
| `注册的IP地址`        | `spring.cloud.nacos.discovery.ip`                | `无`                         | `优先级最高`                                                 |
| `注册的端口`          | `spring.cloud.nacos.discovery.port`              | `-1`                         | `默认情况下不用配置，会自动探测`                             |
| `命名空间`            | `spring.cloud.nacos.discovery.namespace`         | `无`                         | `常用场景之一是不同环境的注册的区分隔离，例如开发测试环境和生产环境的资源（如配置、服务）隔离等。` |
| `AccessKey`           | `spring.cloud.nacos.discovery.access-key`        | `无`                         | `当要上阿里云时，阿里云上面的一个云账号名`                   |
| `SecretKey`           | `spring.cloud.nacos.discovery.secret-key`        | `无`                         | `当要上阿里云时，阿里云上面的一个云账号密码`                 |
| `Metadata`            | `spring.cloud.nacos.discovery.metadata`          | `无`                         | `使用Map格式配置，用户可以根据自己的需要自定义一些和服务相关的元数据信息` |
| `日志文件名`          | `spring.cloud.nacos.discovery.log-name`          | `无`                         |                                                              |
| `集群`                | `spring.cloud.nacos.discovery.cluster-name`      | `DEFAULT`                    | `配置成Nacos集群名称`                                        |
| `接入点`              | `spring.cloud.nacos.discovery.enpoint`           | `UTF-8`                      | `地域的某个服务的入口域名，通过此域名可以动态地拿到服务端地址` |
| `是否集成Ribbon`      | `ribbon.nacos.enabled`                           | `true`                       | `一般都设置成true即可`                                       |
| `是否开启Nacos Watch` | `spring.cloud.nacos.discovery.watch.enabled`     | `true`                       | `可以设置成false来关闭 watch`                                |
