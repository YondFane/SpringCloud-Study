package com.yfan.springcloud.controller;

import com.yfan.springcloud.entities.CommonResult;
import com.yfan.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author YFAN
 * @date 2022/1/10/010
 */
@RestController
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    /*
     * 扣减库存
     * @author YFAN
     * @date 2022/1/10/010
     * @param  * @param productId
     * @param count
     * @return com.yfan.springcloud.entities.CommonResult
     */
    @PostMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200, "扣减库存成功！");
    }
}
