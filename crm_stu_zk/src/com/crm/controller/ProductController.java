package com.crm.controller;

import com.crm.model.DataModel;
import com.crm.model.Pager;
import com.crm.model.Product;
import com.crm.service.ProductService;
import com.crm.service.ProductTypeService;
import com.crm.service.impl.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by meng on 2017/8/19.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    @RequestMapping("/list")
    public ModelAndView getProductList(
            Pager pager,String isDel,
            Integer[] product_id,
            Product product,
            HttpServletResponse response,
            String exportType){
        //导出Excel
        if (exportType != null && !"".equals(exportType)){
            productService.exportExcel(exportType,response,pager,product,product_id);
            return null;
        }

        //删除
        if ("del".equals(isDel)){
            productService.delProductListByIds(product_id);
        }
        //获得商品资料
        DataModel<Product> data =productService.getList(pager,product);
        ModelAndView modelAndView = new ModelAndView("/jsp/product/productList.jsp");
        modelAndView.addObject("pager",data.getPager());
        modelAndView.addObject("productList",data.getRows());
        return modelAndView;
    }

    //去新增
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(Date c_time){
        c_time = new Date();
        String str=productTypeService.getStr();
        ModelAndView modelAndView = new ModelAndView("/jsp/product/productAdd.jsp");
        modelAndView.addObject("str",str);
        modelAndView.addObject("c_time",c_time);
        return modelAndView;
    }
    /**
     * 去修改
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(int productId,Date u_time){
        //修改时间
        u_time = new Date();
        //根据商品的id获得商品的详细信息
        Product product = productService.findProductById(productId);
        //获得商品类别拼成的字符串
        String str = productTypeService.getStr();

        ModelAndView modelAndView = new ModelAndView("/jsp/product/productAdd.jsp");
        modelAndView.addObject("product",product);
        modelAndView.addObject("str",str);
        modelAndView.addObject("u_time",u_time);
        return modelAndView;
    }
    /**
     * 新增或修改
     */
    @RequestMapping("/addOrUpdate")
    public ModelAndView addOrUpdate(
            @RequestParam("file") CommonsMultipartFile file,
            HttpServletRequest request,
            Product product,
            String save,
            Integer edit_id) throws IOException {
        ModelAndView modelAndView = null;
        //新增商品类别
        if(edit_id==null){//新增
            //获得上传的文件名
            String fileName = file.getOriginalFilename();
            //获得上传文件的真实路径
            String path = request.getServletContext().getRealPath("")+"/"+fileName;
            System.out.println(path);
            //创建新文件对象
            File newFile = new File(path);
            //将文件复制到新文件中
            file.transferTo(newFile);
            //放入数据库的字段
            product.setPicture(fileName);
            //判断是否成功
            boolean flag=productService.addProduct(product);
            //两个不同按钮不同龄功能
            if ("0".equals(save)){

                modelAndView = new ModelAndView("/product/list");
            }else {
                modelAndView = new ModelAndView("/product/toAdd");
            }
            if(flag){
                modelAndView.addObject("mess", "新增成功");
            }else{
                modelAndView.addObject("mess", "新增失败");
            }
            modelAndView.addObject("product",new Product());
        }else {//修改
            product.setId(edit_id);

            //获得上传的文件名
            String fileName = file.getOriginalFilename();
            //获得上传文件的真实路径
            String path = request.getServletContext().getRealPath("")+"/"+fileName;
            //创建新文件对象
            File newFile = new File(path);
            //将文件复制到新文件中
            file.transferTo(newFile);
            //当前系统时间
            Long currentTime = System.currentTimeMillis();
            //放入数据库的字段
            product.setPicture(String.valueOf(currentTime)+fileName);

            boolean flag = productService.updateProduct(product);
            modelAndView = new ModelAndView("/product/list");
            if(flag){
                modelAndView.addObject("mess", "修改成功");
            }else{
                modelAndView.addObject("mess", "修改失败");
            }
        }
        return modelAndView;
    }

}
