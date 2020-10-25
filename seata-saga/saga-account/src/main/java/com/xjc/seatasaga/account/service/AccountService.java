package com.xjc.seatasaga.account.service;

import java.math.BigDecimal;

/**
 * @Version 1.0
 * @ClassName AccountService
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description 用户服务
 */
public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     * @return prepare是否成功
     */
    boolean decrease(String userId, BigDecimal money);

    /**
     * 回档账户余额
     *
     * @param userId 用户id
     * @param money 金额
     * @return the boolean
     */
    boolean compensateDecrease(String userId, BigDecimal money);

}
