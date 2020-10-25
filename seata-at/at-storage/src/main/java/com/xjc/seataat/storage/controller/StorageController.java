package com.xjc.seataat.storage.controller;

import com.xjc.seataat.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Version 1.0
 * @ClassName StorageController
 * @Author jiachenXu
 * @Date 2020/10/25
 * @Description 仓库服务
 */
@Slf4j
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     *
     * @param businessKey businessKey
     * @param productId   产品id
     * @param count       数量
     * @return 扣减库存结果
     */
    @RequestMapping("/decrease")
    public boolean decrease(@RequestParam("businessKey") String businessKey,
                            @RequestParam("productId") String productId,
                            @RequestParam("count") Integer count) {
        log.warn("businessKey={}", businessKey);
        return storageService.decrease(productId, count);
    }

}
