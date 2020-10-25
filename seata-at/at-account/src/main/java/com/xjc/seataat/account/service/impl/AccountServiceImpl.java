package com.xjc.seataat.account.service.impl;

import com.xjc.seataat.account.mapper.dao.PayDOMapper;
import com.xjc.seataat.account.mapper.dataobject.PayDO;
import com.xjc.seataat.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Version 1.0
 * @ClassName AccountServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/25
 * @Description
 */
@Slf4j
@Service("accountServiceImpl")
@SuppressWarnings("all")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PayDOMapper payDOMapper;

    @Override
    @Transactional
    public boolean decrease(String userId, BigDecimal money) {
        log.warn("用户服务，尝试扣减账户开始, userId:{}, money:{}", userId, money);
        PayDO payDO = payDOMapper.selectByUserId(userId);
        BigDecimal userMoney = payDO.getMoney( );
        if(userMoney != null){
            payDO.setMoney(userMoney.subtract(money));
            payDOMapper.updateByPrimaryKeySelective(payDO);
            log.warn("用户服务，扣减账户成功, userId:{}, money:{}", userId, money);
            return true;
        }
        return false;
    }

}
