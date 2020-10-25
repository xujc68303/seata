package com.xjc.seatasaga.order.service.impl;

import com.xjc.seatasaga.order.feign.AccountApi;
import com.xjc.seatasaga.order.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Version 1.0
 * @ClassName AccountServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountApi accountApi;

    public void setAccountApi(AccountApi accountApi) {
        this.accountApi = accountApi;
    }

    @Override
    public boolean decrease(String businessKey, String userId, BigDecimal money) {
        return accountApi.decrease(businessKey, userId, money);
    }

    @Override
    public boolean compensateDecrease(String businessKey, String userId, BigDecimal money) {
        return accountApi.compensateDecrease(businessKey, userId, money);
    }
}
