package com.crm.service;

import com.crm.model.DataModel;
import com.crm.model.NeedGather;
import com.crm.model.Pager;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by meng on 2017/8/28.
 */

public interface NeedGatherService {
    DataModel<NeedGather> getList(Pager pager, NeedGather needGather);

    void exportExcel(String exportType, HttpServletResponse response, Pager pager, NeedGather needGather, Integer[] needGather_id);

    void delListByIds(Integer[] needGather);
}
