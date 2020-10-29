package com.xjc.seataat.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Version 1.0
 * @ClassName StorageApi
 * @Author jiachenXu
 * @Date 2020/10/25
 * @Description 仓库服务rpc调用
 */
@FeignClient(value = "storage-service")
public interface StorageApi {

    /**
     * 扣减库存
     * @param businessKey businessKey
     * @param productId productId
     * @param count count
     * @returns
     */
    @GetMapping(value = "/storage/decrease")
    boolean decrease(@RequestParam("businessKey") String businessKey,
                     @RequestParam("productId") String productId,
                     @RequestParam("count") Integer count);

}
