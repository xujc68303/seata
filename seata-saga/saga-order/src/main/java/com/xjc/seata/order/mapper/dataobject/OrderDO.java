package com.xjc.seata.order.mapper.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDO {
    /**
     * id
     */
    private Long id;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 订单价格
     */
    private BigDecimal payAmount;

    /**
     * 订单状态
     */
    private Integer status;
}