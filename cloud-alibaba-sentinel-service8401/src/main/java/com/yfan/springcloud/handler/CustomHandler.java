package com.yfan.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yfan.springcloud.entities.CommonResult;

/*
 * sentinel统一处理类
 * @author YFAN
 * @date 2022/1/7/007
 */
public class CustomHandler {

    // 处理方法必须是静态方法
    public static CommonResult blockHandler(BlockException exception) {
        return new CommonResult(444,"global blockHandler，服务不可应："+exception.getClass().getCanonicalName());
    }
}
