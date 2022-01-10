package com.yfan.springcloud.service;

import java.math.BigDecimal;
/*
 * @author YFAN
 * @date 2022/1/10/010
 */
public interface AccountService {

    void decrease(Long userId, BigDecimal count);

}
