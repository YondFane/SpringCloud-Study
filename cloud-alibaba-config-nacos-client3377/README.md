# 服务配置 Nacos Config

**[Nacos config教程](https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config)**

### 可支持profile粒度的配置（注意）

spring-cloud-starter-alibaba-nacos-config 在加载配置的时候，不仅仅加载了以 dataid 为 `${spring.application.name}.${file-extension:properties}` 为前缀的基础配置，还加载了dataid为 `${spring.application.name}-${profile}.${file-extension:properties}` 的基础配置。在日常开发中如果遇到多套环境下的不同配置，可以通过Spring 提供的 `${spring.profiles.active}` 这个配置项来配置。

