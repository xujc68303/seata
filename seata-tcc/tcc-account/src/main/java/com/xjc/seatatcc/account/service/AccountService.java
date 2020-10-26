package com.xjc.seatatcc.account.service;

import java.math.BigDecimal;

/**
 * @Version 1.0
 * @ClassName AccountService
 * @Author jiachenXu
 * @Date 2020/10/25
 * @Description 用户服务
 */
public interface AccountService {

    /**
     * 扣减账户余额
     * @param xid the global transactionId
     * @param userId 用户id
     * @param money 金额
     * @return prepare是否成功
     */
    boolean decrease(String xid, String userId, BigDecimal money);

    /**
     * Commit boolean.
     *
     * @param xid the global transactionId
     * @return the boolean
     */
    boolean commit(String xid);

    /**
     * Rollback boolean.
     *
     * @param xid the global transactionId
     * @return the boolean
     */
    boolean rollback(String xid);

}
