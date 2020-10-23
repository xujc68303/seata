package com.xjc.seata.storage.service;

/**
 * @Version 1.0
 * @ClassName StorageService
 * @Author jiachenXu
 * @Date 2020/10/17
 * @Description
 * */
public interface StorageService {

    /**
     * 减少库存
     *
     * @param productId
     * @param count
     * @return
     */
    boolean decrease(String productId, Integer count);

    /**
     * 回滚库存
     *
     * @param productId
     * @param count
     * @return
     */
    boolean compensateDecrease(String productId, Integer count);

}
