package com.yfan.springcloud.service;

import com.yfan.springcloud.entities.CommonResult;

import java.math.BigDecimal;

/*
 * @author YFAN
 * @date 2022/1/10/010
 */
public interface StorageService {
    void decrease(Long productId, Integer count);
}
