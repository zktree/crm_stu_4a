package com.crm.service;

import com.crm.model.DataModel;
import com.crm.model.Gather;
import com.crm.model.Pager;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by meng on 2017/8/28.
 */

public interface GatherService {
    void exportExcel(String exportType, HttpServletResponse response, Pager pager, Gather gather, Integer[] gather_id);

    DataModel<Gather> getList(Pager pager, Gather gather);

    void delListByIds(Integer[] gather_id);
}
