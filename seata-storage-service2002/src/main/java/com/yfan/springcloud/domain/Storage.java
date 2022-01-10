package com.yfan.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author YFAN
 * @date 2022/1/10/010
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private Long id;
    private Long productId;// 产品ID
    private Integer total;// 总库存
    private Integer used;// 已用库存
    private Integer residue;// 剩余库存

}
