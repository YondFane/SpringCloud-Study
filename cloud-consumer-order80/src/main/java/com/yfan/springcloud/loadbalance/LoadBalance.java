package com.yfan.springcloud.loadbalance;


import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalance {
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
