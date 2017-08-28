package com.crm.dao;

import com.crm.model.Gather;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gather record);

    int insertSelective(Gather record);

    Gather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gather record);

    int updateByPrimaryKeyWithBLOBs(Gather record);

    int updateByPrimaryKey(Gather record);

    void delOrderListByIds(@Param("id") int id);

    List<Gather> findAllGather();

    List<Gather> getGatherList(@Param("from") int from, @Param("pageSize") int pageSize,@Param("gather") Gather gather);

    List<Gather> findGatherByIds(@Param("ids") Integer[] gather_id);

    int countGather(@Param("g") Gather gather);

    void delGatherListByIds(int id);
}