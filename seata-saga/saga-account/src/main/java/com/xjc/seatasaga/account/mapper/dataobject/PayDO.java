package com.xjc.seatasaga.account.mapper.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDO {
    /**
    * id
    */
    private Long id;

    /**
    * 用户id
    */
    private String userId;

    /**
    * 余额
    */
    private BigDecimal money;
}