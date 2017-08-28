package com.crm.dao;

import com.crm.model.Gather;

public interface GatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gather record);

    int insertSelective(Gather record);

    Gather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gather record);

    int updateByPrimaryKeyWithBLOBs(Gather record);

    int updateByPrimaryKey(Gather record);
}