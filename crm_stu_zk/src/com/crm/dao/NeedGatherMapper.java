package com.crm.dao;

import com.crm.model.NeedGather;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NeedGatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NeedGather record);

    int insertSelective(NeedGather record);

    NeedGather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NeedGather record);

    int updateByPrimaryKeyWithBLOBs(NeedGather record);

    int updateByPrimaryKey(NeedGather record);

    List<NeedGather> getNeedGatherList(@Param("from") int from, @Param("pageSize") int pageSize, @Param("n") NeedGather needGather);

    int countNeedGather(@Param("n") NeedGather needGather);

    List<NeedGather> findAllOrder();

    List<NeedGather> findOrderByIds(@Param("ids") Integer[] needGather_id);

    void delOrderListByIds(@Param("id") int id);
}