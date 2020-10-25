package com.xjc.seataat.order;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Version 1.0
 * @ClassName OrderApplication
 * @Author jiachenXu
 * @Date 2020/10/25
 * @Description 订单服务
 */
@EnableTransactionManagement
@EnableAutoDataSourceProxy
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class OrderApplication {

    // at模式使用 seata-spring-boot-starter 可实现自动化配置和数据源自动代理
    // 1.1.0以上版本自动代理支持属性配置和注解@EnableAutoDataSourceProxy两种方式

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
