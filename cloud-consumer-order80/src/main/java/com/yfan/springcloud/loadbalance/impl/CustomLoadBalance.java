package com.yfan.springcloud.loadbalance.impl;

import com.yfan.springcloud.loadbalance.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 手动实现负载均衡
 * @author YFAN
 * @date 2021/12/26/026
 */
@Component
@Slf4j
public class CustomLoadBalance implements LoadBalance {

    // 计数器
    private AtomicInteger counter = new AtomicInteger(0);

    /*
     * CAS获取计数
     * @author YFAN
     * @date 2021/12/26/026
     */
    public final int getIndxByCAS() {
        int current;
        int next;
        do {
            current = counter.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!counter.compareAndSet(current, next));
        log.info("----第{}次访问", next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstanceList) {
        int count = getIndxByCAS();
        if (serviceInstanceList != null) {
            return serviceInstanceList.get(count % serviceInstanceList.size());
        }
        return null;
    }
}
