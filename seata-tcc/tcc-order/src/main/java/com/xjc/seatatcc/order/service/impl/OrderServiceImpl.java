package com.xjc.seatatcc.order.service.impl;

import com.google.common.collect.Maps;
import com.xjc.seatatcc.order.mapper.dataobject.OrderDO;
import com.xjc.seatatcc.order.service.OrderService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Version 1.0
 * @ClassName OrderServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/26
 * @Description
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Map<Object, Object> xidCache = Maps.newConcurrentMap( );

    @Override
    public boolean saveOrder(BusinessActionContext actionContext, OrderDO order) {
        try {
            Connection connection = jdbcTemplate.getDataSource( ).getConnection( );
            connection.setAutoCommit(false);

            String updateSql = "insert into order(product_id, count, money) values(?,?,?)";
            jdbcTemplate.update(updateSql,order.getProductId(), order.getCount(), order.getPayAmount());
        } catch (SQLException e) {
            e.printStackTrace( );
        }

        return false;
    }


    @Override
    public boolean commit(BusinessActionContext actionContext) {
        return false;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        return false;
    }
}
