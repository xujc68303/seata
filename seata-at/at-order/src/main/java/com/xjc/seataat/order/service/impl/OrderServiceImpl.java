package com.xjc.seataat.order.service.impl;

import com.xjc.seataat.order.feign.AccountApi;
import com.xjc.seataat.order.feign.StorageApi;
import com.xjc.seataat.order.mapper.dao.OrderDOMapper;
import com.xjc.seataat.order.mapper.dataobject.OrderDO;
import com.xjc.seataat.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Version 1.0
 * @ClassName OrderServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description
 */
@Slf4j
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private AccountApi accountApi;

    @Autowired
    private StorageApi storageApi;

    @GlobalTransactional
    @Override
    public boolean create(OrderDO order) {
        String key = String.valueOf(System.currentTimeMillis( ));
        log.warn("交易开始! key={}", key);
        if (orderDOMapper.selectAllByProductId(order.getOrderId( )) != null) {
            return true;
        }

        order.setStatus(0);
        orderDOMapper.insertSelective(order);

        Map<String, Object> cache = new HashMap<>( );
        if (cache.put(order.getOrderId( ), order) == null) {
            cache.remove(order.getOrderId());
            throw new RuntimeException("cache error");

        }

        accountApi.decrease(key, order.getUserId( ), order.getPayAmount( ));

        storageApi.decrease(key, order.getProductId( ), order.getCount( ));

        order.setStatus(1);
        orderDOMapper.updateByPrimaryKeySelective(order);
        log.warn("交易结束!");
        return true;
    }


}
