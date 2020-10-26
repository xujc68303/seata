package com.xjc.seatatcc.account.service.impl;

import com.google.common.collect.Maps;
import com.xjc.seatatcc.account.mapper.dataobject.PayDO;
import com.xjc.seatatcc.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Version 1.0
 * @ClassName AccountServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/26
 * @Description
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Map<Object, Object> xIdCache = Maps.newConcurrentMap( );

    @Override
    public boolean decrease(String xid, String userId, BigDecimal money) {
        log.warn("尝试扣减账户开始");
        try {
            Connection connection = jdbcTemplate.getDataSource( ).getConnection( );

            connection.setAutoCommit(false);

            String querySql = "select * from account where user_id = ?";
            PayDO payDO = jdbcTemplate.queryForObject(querySql, new Object[]{userId}, PayDO.class);
            if (payDO != null) {
                money = payDO.getMoney( ).multiply(money);
                String sql = "update account set money = ? where user_id = ?";
                jdbcTemplate.update(sql, new Object[]{payDO.getMoney( ).multiply(money), userId});
                payDO.setMoney(money);

                xIdCache.put(xid, connection);
            }
        } catch (SQLException e) {
            log.error("尝试扣减账户 parepare fail", e);
            e.printStackTrace( );
        }

        log.warn("尝试扣减账户 parepare success");
        return true;
    }


    @Override
    public boolean commit(String xid) {
        log.warn("扣减账户金额开始, commit, xid ={}", xid);
        if (xIdCache.get(xid) != null) {
            Connection connection = (Connection) xIdCache.get(xid);
            try {
                if (connection != null) {
                    connection.commit( );
                }
            } catch (SQLException e) {
                log.error("扣减账户金额失败 xid ={}, e={}", xid, e);
                e.printStackTrace( );
            } finally {
                xIdCache.remove(xid);
            }

            log.warn("扣减账户金额结束, commit, xid ={}", xid);
            return true;
        }
        return false;
    }


    @Override
    public boolean rollback(String xid) {
        log.warn("回滚账户金额开始, rollback, xid ={}", xid);
        if (xIdCache.get(xid) != null) {
            Connection connection = (Connection) xIdCache.get(xid);
            try {
                if (connection != null) {
                    connection.rollback( );
                }
            } catch (SQLException e) {
                log.error("回滚账户金额失败 xid ={}, e={}", xid, e);
                e.printStackTrace( );
            } finally {
                xIdCache.remove(xid);
            }

            log.warn("回滚账户金额结束, rollback, xid ={}", xid);
            return true;
        }
        return false;
    }
}
