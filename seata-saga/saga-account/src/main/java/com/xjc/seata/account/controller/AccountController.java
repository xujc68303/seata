package com.xjc.seata.account.controller;

import com.xjc.seata.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Version 1.0
 * @ClassName AccountController
 * @Author jiachenXu
 * @Date 2020/10/17
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 扣减账户余额
     * @param businessKey
     * @param userId
     * @param money
     * @return
     */
    @RequestMapping("/decrease")
    public boolean decrease(@RequestParam("businessKey") String businessKey,
                            @RequestParam("userId") String userId,
                            @RequestParam("money") BigDecimal money){
        log.warn("businessKey:{}", businessKey);
        return accountService.decrease(userId, money);
    }

    /**
     * 补偿扣减库存
     * @param businessKey
     * @param userId
     * @param money
     * @return
     */
    @RequestMapping("/compensateDecrease")
    public boolean compensateDecrease(@RequestParam("businessKey") String businessKey,
                                      @RequestParam("userId") String userId,
                                      @RequestParam("money") BigDecimal money){
        log.warn("businessKey:{}", businessKey);
        return accountService.compensateDecrease(userId, money);
    }
}
