package com.xjc.seataat.account.mapper.dao;

import com.xjc.seataat.account.mapper.dataobject.PayDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PayDOMapper {

    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(PayDO record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(PayDO record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    PayDO selectByPrimaryKey(Long id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(PayDO record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(PayDO record);

    PayDO selectByUserId(@Param("userId") String userId);


}