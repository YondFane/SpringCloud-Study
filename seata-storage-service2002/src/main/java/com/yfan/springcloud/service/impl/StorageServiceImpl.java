package com.yfan.springcloud.service.impl;

import com.yfan.springcloud.dao.StorageDao;
import com.yfan.springcloud.entities.CommonResult;
import com.yfan.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("StorageServiceImpl-decrease---库存扣减，productId:{},count:{}", productId, count);
        storageDao.decrease(productId, count);
    }

}
