package com.crm.controller;

import com.crm.model.*;
import com.crm.service.OpenService;
import com.crm.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by meng on 2017/8/25.
 */
@Controller
@RequestMapping("/open")
public class OpenController {

    @Resource(name = "openService")
    private OpenService openService;

    @RequestMapping("/companyList")
    public ModelAndView openCompany(
            Company company,
            Pager pager,
            HttpServletResponse response,
            String exportType){

        //导出Excel
        if (exportType != null && !"".equals(exportType)){
            openService.exportExcel(exportType,response,pager,company);
            return null;
        }

        List<Suser> users = openService.getUser();

        DataModel<Company> data = openService.getList(pager,company);
        ModelAndView modelAndView = new ModelAndView("/jsp/open/companyList_openWindow.jsp");
        modelAndView.addObject("pager",data.getPager());
        modelAndView.addObject("companyList",data.getRows());
        modelAndView.addObject("users",users);
        return modelAndView;

    }
    /**
     * 打开联系人
     */
    @RequestMapping("/linkmanList")
    public ModelAndView openLinkman(
            Linkman linkman,
            Pager pager,
            Integer cId,
            HttpServletResponse response,
            String exportType){

        List<Suser> users = openService.getUser();
        linkman.setcId(cId);
        DataModel<Linkman> data = openService.getLinkmanList(pager,linkman);
        ModelAndView modelAndView = new ModelAndView("/jsp/open/linkmanList_openWindow.jsp");
        modelAndView.addObject("pager",data.getPager());
        modelAndView.addObject("linkmanList",data.getRows());
        modelAndView.addObject("users",users);
        modelAndView.addObject("cId",cId);

        return modelAndView;
    }

    @Resource(name = "productService")
    private ProductService productService;
    @RequestMapping("productList")
    public ModelAndView getProductList(Pager pager,  Product product){
        //获得商品资料
        DataModel<Product> data =productService.getList(pager,product);
        ModelAndView modelAndView = new ModelAndView("/jsp/open/productList_openWindow.jsp");
        modelAndView.addObject("pager",data.getPager());
        modelAndView.addObject("productList",data.getRows());
        return modelAndView;
    }





}
