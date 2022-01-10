package com.yfan.springcloud.controller;

import com.yfan.springcloud.entities.CommonResult;
import com.yfan.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/*
 * @author YFAN
 * @date 2022/1/10/010
 */
@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    /*
     * 扣减账户余额
     * @author YFAN
     * @date 2022/1/10/010
     * @param  * @param userId
     * @param count
     * @return com.yfan.springcloud.entities.CommonResult
     */
    @PostMapping("/account/decrease")
    public CommonResult decrease(Long userId, BigDecimal count){
        accountService.decrease(userId, count);
        return new CommonResult(200, "账户余额扣减成功！");
    }

}
