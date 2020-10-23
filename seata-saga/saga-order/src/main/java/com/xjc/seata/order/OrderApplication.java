package com.xjc.seata.order;

import com.xjc.seata.order.config.ApplicationContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Version 1.0
 * @ClassName OrderApplication
 * @Author jiachenXu
 * @Date 2020/10/18
 * @Description 订单服务
 */
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        ApplicationContextUtils.setApplicationContext(SpringApplication.run(OrderApplication.class, args));
    }
}
