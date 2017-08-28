package com.crm.service;

import com.crm.model.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by meng on 2017/8/25.
 */
public interface OpenService {
    DataModel<Company> getList(Pager pager, Company company);

    void exportExcel(String exportType, HttpServletResponse response, Pager pager, Company company);

    List<Suser> getUser();

    DataModel<Linkman> getLinkmanList(Pager pager, Linkman linkman);
}
