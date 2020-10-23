package com.xjc.seata.storage.service.impl;

import com.xjc.seata.storage.mapper.dao.StorageDOMapper;
import com.xjc.seata.storage.mapper.dataobject.StorageDO;
import com.xjc.seata.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Version 1.0
 * @ClassName StorageServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/17
 * @Description
 */
@Slf4j
@Service("storageService")
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDOMapper storageDOMapper;

    @Override
    public boolean decrease(String productId, Integer count) {
        log.warn("仓库服务，扣减库存开始, commit, productId:{}, count:{}", productId, count);
        StorageDO storageDO = storageDOMapper.selectByProductId(productId);
        Integer productCount = storageDO.getCount();
        if(productCount != null){
            storageDO.setCount(productCount - count);
            if(storageDOMapper.updateByPrimaryKeySelective(storageDO) > 0){
                log.warn("仓库服务，扣减库存成功, commit, productId:{}, count:{}", productId, count);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean compensateDecrease(String productId, Integer count) {
        log.warn("仓库服务，补偿扣减库存开始, compensate, productId:{}, count:{}", productId, count);
        StorageDO storageDO = storageDOMapper.selectByProductId(productId);
        if(storageDO != null){
            Integer productCount = storageDO.getCount();
            storageDO.setCount(productCount + count);
            storageDOMapper.updateByPrimaryKeySelective(storageDO);
            log.warn("仓库服务，补偿扣减库存成功, compensate, productId:{}, count:{}", productId, count);
            return true;
        }
        return false;
    }
}
