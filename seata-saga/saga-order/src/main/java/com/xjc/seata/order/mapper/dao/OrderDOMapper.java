package com.xjc.seata.order.mapper.dao;

import com.xjc.seata.order.mapper.dataobject.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDOMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(OrderDO record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(OrderDO record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    OrderDO selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(OrderDO record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(OrderDO record);

    int deleteByOrderId(@Param("orderId") String orderId);

    OrderDO selectAllByProductId(@Param("productId") String productId);

}