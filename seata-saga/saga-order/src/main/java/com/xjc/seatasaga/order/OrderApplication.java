package com.xjc.seatasaga.order;

import com.xjc.seatasaga.order.config.ApplicationContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Version 1.0
 * @ClassName OrderApplication
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description 订单服务
 */
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class OrderApplication {

    // saga模式不需要使用代理 代理数据源会和状态机冲突 会出异常导致无法回滚、悬挂等情况

    public static void main(String[] args) {
        ApplicationContextUtils.setApplicationContext(SpringApplication.run(OrderApplication.class, args));
    }
}
