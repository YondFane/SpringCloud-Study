package com.yfan.springcloud.dao;

import com.yfan.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
 * @author YFAN
 * @date 2022/1/9/009
 */
@Mapper
public interface OrderDao {
    // 新建订单
    void create(Order order);
    // 修改订单状态
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
