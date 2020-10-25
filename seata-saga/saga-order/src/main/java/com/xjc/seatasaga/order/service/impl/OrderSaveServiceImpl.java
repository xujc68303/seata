package com.xjc.seatasaga.order.service.impl;

import com.xjc.seatasaga.order.mapper.dao.OrderDOMapper;
import com.xjc.seatasaga.order.mapper.dataobject.OrderDO;
import com.xjc.seatasaga.order.service.OrderSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Version 1.0
 * @ClassName OrderSaveServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description
 */
@Slf4j
@Service("orderSave")
public class OrderSaveServiceImpl implements OrderSaveService {

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Override
    public boolean saveOrder(String businessKey, OrderDO order) {
        order.setStatus(0);
        orderDOMapper.insertSelective(order);
        log.warn("保存订单成功!");
        return true;
    }

    @Override
    public boolean deleteOrder(String businessKey, OrderDO order) {
        orderDOMapper.deleteByOrderId(order.getOrderId());
        log.warn("删除订单成功!");
        return false;
    }
}
