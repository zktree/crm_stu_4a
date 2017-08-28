package com.crm.dao;

import com.crm.model.Suser;
import com.crm.model.SuserWithBLOBs;

public interface SuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SuserWithBLOBs record);

    int insertSelective(SuserWithBLOBs record);

    SuserWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SuserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SuserWithBLOBs record);

    int updateByPrimaryKey(Suser record);
}