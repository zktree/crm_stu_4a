package com.crm.dao;

import com.crm.model.Linkman;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinkmanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Linkman record);

    int insertSelective(Linkman record);

    Linkman selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Linkman record);

    int updateByPrimaryKeyWithBLOBs(Linkman record);

    int updateByPrimaryKey(Linkman record);

    List<Linkman> getLinkmanList(@Param("from") int from, @Param("pageSize") int pageSize,@Param("l") Linkman linkman);

    int countLinkman(@Param("l") Linkman linkman);
}