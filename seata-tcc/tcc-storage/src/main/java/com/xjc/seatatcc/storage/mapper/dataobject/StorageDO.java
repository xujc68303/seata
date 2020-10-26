package com.xjc.seatatcc.storage.mapper.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageDO {

    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 商品价格
     */
    private BigDecimal money;
}