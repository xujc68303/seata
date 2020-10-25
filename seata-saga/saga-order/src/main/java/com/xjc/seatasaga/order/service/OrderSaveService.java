package com.xjc.seatasaga.order.service;

import com.xjc.seatasaga.order.mapper.dataobject.OrderDO;

/**
 * @Version 1.0
 * @ClassName OrderSaveService
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description
 */
public interface OrderSaveService {

    /**
     * 保存订单
     * @param businessKey
     * @param order
     * @return
     */
    boolean saveOrder(String businessKey, OrderDO order);

    /**
     * 失败补偿，删除订单
     * @param businessKey
     * @param order
     * @return
     */
    boolean deleteOrder(String businessKey, OrderDO order);
}
