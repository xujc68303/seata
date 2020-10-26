package com.xjc.seatatcc.storage.service.impl;

import com.google.common.collect.Maps;
import com.xjc.seatatcc.storage.mapper.dataobject.StorageDO;
import com.xjc.seatatcc.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Version 1.0
 * @ClassName StorageServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/26
 * @Description
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Map<Object, Object> xIdCache = Maps.newConcurrentMap( );

    @Override
    public boolean decrease(String xid, String productId, Integer count) {
        log.warn("库存服务尝试扣减开始 xid={}", xid);
        try {
            if (jdbcTemplate != null) {
                Connection connection = jdbcTemplate.getDataSource( ).getConnection( );
                connection.setAutoCommit(false);

                String querySql = "select * from storage where product_id = ?";
                StorageDO storageDO = jdbcTemplate.queryForObject(querySql, new Object[]{productId}, StorageDO.class);

                String updateSql = "update storage set count = ? where product_id = ?";
                jdbcTemplate.update(updateSql, storageDO.getCount( ) - count, productId);

                xIdCache.put(xid, connection);
            }
        } catch (SQLException e) {
            log.warn("库存服务尝试扣减失败 xid={}", xid);
            e.printStackTrace( );
        }
        log.warn("库存服务尝试扣减结束 xid={}", xid);
        return true;
    }

    @Override
    public boolean commit(String xid, String productId, Integer count) {
        log.warn("提交库存服务开始 xid={}", xid);

        if (xIdCache.get(xid) != null) {
            Connection connection= (Connection) xIdCache.get(xid);
            if(connection != null){
                try {
                    connection.commit();
                } catch (SQLException e) {
                    log.warn("提交库存服务失败 xid={}", xid);
                    e.printStackTrace( );
                } finally {
                    xIdCache.remove(xid);
                }
            }
            log.warn("提交库存服务成功 xid={}", xid);
            return true;
        }
        return false;
    }

    @Override
    public boolean rollback(String xid, String productId, Integer count) {
        log.warn("回滚库存服务开始 xid={}", xid);
        if (xIdCache.get(xid) != null) {
            Connection connection= (Connection) xIdCache.get(xid);
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    log.warn("回滚库存服务失败 xid={}", xid);
                    e.printStackTrace( );
                } finally {
                    xIdCache.remove(xid);
                }
            }
            log.warn("回滚库存服务成功 xid={}", xid);
            return true;
        }
        return false;
    }
}
