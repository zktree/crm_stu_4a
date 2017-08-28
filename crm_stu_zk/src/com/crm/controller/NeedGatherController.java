package com.crm.controller;

import com.crm.model.DataModel;
import com.crm.model.NeedGather;
import com.crm.model.Order;
import com.crm.model.Pager;
import com.crm.service.NeedGatherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by meng on 2017/8/28.
 */
@Controller
@RequestMapping("/needGather")
public class NeedGatherController {

    @Resource(name = "needGatherService")
    private NeedGatherService needGatherService;

    @RequestMapping("/list")
    public ModelAndView getGatherList(
            Pager pager, String isDel,
            Integer[] needGather_id,
            HttpServletResponse response,
             NeedGather needGather,
            String exportType
    ){
        //导出Excel
        if (exportType != null && !"".equals(exportType)){
            needGatherService.exportExcel(exportType,response,pager,needGather,needGather_id);
            return null;
        }
        //删除
        if ("del".equals(isDel)){
            needGatherService.delListByIds(needGather_id);
        }

        DataModel<NeedGather> dataModel = needGatherService.getList(pager,needGather);
        ModelAndView modelAndView = new ModelAndView("/jsp/gather/gatherList.jsp");
        modelAndView.addObject("pager",dataModel.getPager());
        modelAndView.addObject("needGatherList",dataModel.getRows());
        return modelAndView;
    }


}
