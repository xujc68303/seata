package com.xjc.seatasaga.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Version 1.0
 * @ClassName AccountApi
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description 用户服务rpc调用
 */
@FeignClient(value = "account-service")
public interface AccountApi {

    /**
     * 扣减账户余额
     * @param businessKey
     * @param userId 用户id
     * @param money 金额
     * @return
     */
    @RequestMapping("/account/decrease")
    boolean decrease(@RequestParam("businessKey") String businessKey,
                     @RequestParam("userId") String userId,
                     @RequestParam("money") BigDecimal money);

    /**
     * 交易回档，把扣减的账户金额加回去
     *
     * @param businessKey
     * @param userId userId
     * @param money money
     * @return the boolean
     */
    @RequestMapping("/account/compensateDecrease")
    boolean compensateDecrease(@RequestParam("businessKey") String businessKey,
                               @RequestParam("userId") String userId,
                               @RequestParam("money") BigDecimal money);

}
