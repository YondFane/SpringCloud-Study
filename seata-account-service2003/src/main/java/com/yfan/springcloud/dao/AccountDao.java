package com.yfan.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/*
 * @author YFAN
 * @date 2022/1/10/010
 */
@Mapper
public interface AccountDao {

    void decrease(@Param("userId") Long userId, @Param("count") BigDecimal count);

}
