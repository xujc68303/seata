package com.xjc.seatasaga.order.service;

import java.math.BigDecimal;

/**
 * @Version 1.0
 * @ClassName AccountService
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description
 */
public interface AccountService {

    /**
     * 调用账户服务扣减账户余额
     * @param businessKey
     * @param userId
     * @param money
     * @return
     */
    boolean decrease(String businessKey, String userId, BigDecimal money);

    /**
     * 交易补偿，调用账户服务把扣减的账户金额加回去
     *
     * @param businessKey
     * @param userId
     * @param money
     * @return
     */
    boolean compensateDecrease(String businessKey, String userId, BigDecimal money);
}
