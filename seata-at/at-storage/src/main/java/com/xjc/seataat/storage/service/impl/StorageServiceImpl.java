package com.xjc.seataat.storage.service.impl;

import com.xjc.seataat.storage.mapper.dao.StorageDOMapper;
import com.xjc.seataat.storage.mapper.dataobject.StorageDO;
import com.xjc.seataat.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Version 1.0
 * @ClassName StorageServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/25
 * @Description
 */
@Slf4j
@Service("storageService")
@SuppressWarnings("all")
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDOMapper storageDOMapper;

    @Override
    @Transactional
    public boolean decrease(String productId, Integer count) {
        log.warn("仓库服务，扣减库存开始, commit, productId:{}, count:{}", productId, count);
        StorageDO storageDO = storageDOMapper.selectByProductId(productId);
        Integer productCount = storageDO.getCount( );
        if (productCount != null) {
            storageDO.setCount(productCount - count);
            if (storageDOMapper.updateByPrimaryKeySelective(storageDO) > 0) {
                log.warn("仓库服务，扣减库存成功, commit, productId:{}, count:{}", productId, count);
                return true;
            }
        }
        return false;
    }

}
