package com.xjc.seataat.storage.mapper.dao;

import com.xjc.seataat.storage.mapper.dataobject.StorageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDOMapper {
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
    int insert(StorageDO record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(StorageDO record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    StorageDO selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(StorageDO record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(StorageDO record);

    StorageDO selectByProductId(@Param("productId") String productId);


}