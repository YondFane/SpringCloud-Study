package com.yfan.springcloud.service.impl;

import com.yfan.springcloud.dao.OrderDao;
import com.yfan.springcloud.domain.Order;
import com.yfan.springcloud.service.AccountService;
import com.yfan.springcloud.service.OrderService;
import com.yfan.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;

    /*
     * 创建订单--调用库存服务扣减库存--调用账号服务扣减账户余额--修改订单状态
     * @author YFAN
     * @date 2022/1/9/009
     */
    @Override
    // 出现异常进行回滚
    @GlobalTransactional(name = "create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        //1
        log.info("-----开始创建订单-----");
        orderDao.create(order);
        //2
        log.info("-----订单服务开始调用库存，做扣减-----");
        storageService.decrease(order.getProductId(), order.getCount());
        //3
        log.info("-----订单服务开始调用账户，做扣减-----");
        accountService.decrease(order.getUserId(), order.getMoney());
        //4
        log.info("-----修改订单状态开始-----");
        orderDao.update(order.getUserId(), 0);

        log.info("-----创建订单结束-----");
    }
}
