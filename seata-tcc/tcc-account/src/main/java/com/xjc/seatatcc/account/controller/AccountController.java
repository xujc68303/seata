package com.xjc.seatatcc.account.controller;

import com.xjc.seatatcc.account.service.AccountService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Version 1.0
 * @ClassName AccountController
 * @Author jiachenXu
 * @Date 2020/10/26
 * @Description
 */
@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/decrease")
    public boolean prepare(@RequestBody BusinessActionContext actionContext, @RequestParam("userId") String userId, @RequestParam("money") BigDecimal money) {
        return accountService.decrease(actionContext.getXid( ), userId, money);
    }

    @RequestMapping("/commit")
    public boolean commit(@RequestBody BusinessActionContext actionContext) {
        return accountService.commit(actionContext.getXid( ));

    }

    @RequestMapping("/rollback")
    public boolean rollback(@RequestBody BusinessActionContext actionContext) {
        return accountService.rollback(actionContext.getXid( ));
    }


}
