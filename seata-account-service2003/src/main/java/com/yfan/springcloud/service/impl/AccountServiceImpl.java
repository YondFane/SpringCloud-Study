package com.yfan.springcloud.service.impl;

import com.yfan.springcloud.dao.AccountDao;
import com.yfan.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/*
 * @author YFAN
 * @date 2022/1/10/010
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal count) {
        // 模拟超时异常，全局事务回滚
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
        }
        log.info("账号余额扣减，userId:{},count:{}",userId, count);
        accountDao.decrease(userId, count);
    }
}
