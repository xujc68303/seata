package com.xjc.seatasaga.order.service;

/**
 * @Version 1.0
 * @ClassName StorageService
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description
 */
public interface StorageService {

    /**
     * 扣减库存
     * @param businessKey
     * @param productId
     * @param count
     * @return
     */
    boolean decrease(String businessKey, String productId, Integer count);

    /**
     * 补偿扣减库存
     * @param businessKey businessKey
     * @param productId productId
     * @param count count
     * @return
     */
    boolean compensateDecrease(String businessKey, String productId, Integer count);

}
