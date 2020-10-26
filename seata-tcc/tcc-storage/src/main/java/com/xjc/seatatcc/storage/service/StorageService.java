package com.xjc.seatatcc.storage.service;

/**
 * @Version 1.0
 * @ClassName StorageService
 * @Author jiachenXu
 * @Date 2020/10/26
 * @Description
 */
public interface StorageService {

    boolean decrease(String xid, String  productId, Integer count);

    boolean commit(String xid);

    boolean rollback(String xid);

}
