package com.xjc.seatasaga.order.service.impl;

import com.xjc.seatasaga.order.feign.StorageApi;
import com.xjc.seatasaga.order.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Version 1.0
 * @ClassName StorageServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description
 */
@Slf4j
@Service("storageService")
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageApi storageApi;

    @Override
    public boolean decrease(String businessKey, String productId, Integer count) {
        return storageApi.decrease(businessKey,productId,count);
    }

    @Override
    public boolean compensateDecrease(String businessKey, String productId, Integer count) {
        return storageApi.compensateDecrease(businessKey,productId,count);
    }
}
