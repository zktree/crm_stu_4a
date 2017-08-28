package com.crm.dao;

import com.crm.model.Company;
import com.crm.model.Suser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKeyWithBLOBs(Company record);

    int updateByPrimaryKey(Company record);

    List<Company> getCompanyList(@Param("from") int from, @Param("pageSize") int pageSize, @Param("c") Company company);

    int countCompany(@Param("c") Company company);

    List<Company> findAllCompany();

    List<Suser> getUsers();
}