package com.xjc.seatasaga.order.service.impl;

import com.xjc.seatasaga.order.config.ApplicationContextUtils;
import com.xjc.seatasaga.order.mapper.dao.OrderDOMapper;
import com.xjc.seatasaga.order.mapper.dataobject.OrderDO;
import com.xjc.seatasaga.order.service.OrderService;
import io.seata.saga.engine.StateMachineEngine;
import io.seata.saga.statelang.domain.ExecutionStatus;
import io.seata.saga.statelang.domain.StateMachineInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @Version 1.0
 * @ClassName OrderServiceImpl
 * @Author jiachenXu
 * @Date 2020/10/24
 * @Description
 */
@Slf4j
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Override
    public boolean create(OrderDO order) {
        log.warn("交易开始!");
        if(orderDOMapper.selectAllByProductId(order.getOrderId( )) != null){
           return true;
        }
        Map<String, Object> startParams = new HashMap<String, Object>(7);
        String businessKey = String.valueOf(System.currentTimeMillis());
        startParams.put("businessKey", businessKey);
        startParams.put("order", order);
        startParams.put("mockReduceAccountFail", "true");
        startParams.put("userId", order.getUserId());
        startParams.put("money", order.getPayAmount());
        startParams.put("productId", order.getProductId());
        startParams.put("count", order.getCount());

        StateMachineEngine stateMachineEngine = (StateMachineEngine) ApplicationContextUtils.getApplicationContext().getBean("stateMachineEngine");

        // 根据sage配置文件调用保存订单接口
        StateMachineInstance inst = stateMachineEngine.startWithBusinessKey("buyGoodsOnline", null, businessKey, startParams);

        Assert.isTrue(ExecutionStatus.SU.equals(inst.getStatus()), "saga transaction execute failed. XID: " + inst.getId());


        log.warn("saga transaction commit succeed., XID={} " , inst.getId());

        order.setStatus(1);
        orderDOMapper.updateByPrimaryKeySelective(order);
        log.warn("交易结束!");
        return true;
    }


}
