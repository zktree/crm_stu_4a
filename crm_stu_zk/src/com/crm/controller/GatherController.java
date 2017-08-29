package com.crm.controller;

import com.crm.model.DataModel;
import com.crm.model.Gather;
import com.crm.model.NeedGather;
import com.crm.model.Pager;
import com.crm.service.GatherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by meng on 2017/8/25.
 */
@Controller
@RequestMapping("/gather")
public class GatherController {

    @Resource(name = "gatherService")
    private GatherService gatherService;

    @RequestMapping("/list")
    public ModelAndView getGatherList(
            Pager pager, String isDel,
            Integer[] gather_id,
            HttpServletResponse response,
            Gather gather,
            String exportType
    ){
        //导出Excel
        if (exportType != null && !"".equals(exportType)){
            gatherService.exportExcel(exportType,response,pager,gather,gather_id);
            return null;
        }
        //删除
        if ("del".equals(isDel)){
            gatherService.delListByIds(gather_id);
        }
        List<String> handler = gatherService.getHandler();
        DataModel<Gather> dataModel = gatherService.getList(pager,gather);
        ModelAndView modelAndView = new ModelAndView("/jsp/gather/gatherProcessList.jsp");
        modelAndView.addObject("pager",dataModel.getPager());
        modelAndView.addObject("gatherList",dataModel.getRows());
        modelAndView.addObject("handler",handler);
        return modelAndView;

    }


}
