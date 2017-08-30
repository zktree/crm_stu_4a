package com.crm.controller;

import com.crm.model.*;
import com.crm.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by meng on 2017/8/23.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource(name = "orderService")
    private OrderService orderService;

    @RequestMapping("edit")
    public ModelAndView orderEdit(){
        ModelAndView modelAndView = new ModelAndView("/jsp/order/orderEdit.jsp");
        return modelAndView;
    }


    @RequestMapping("/list")
    public ModelAndView getOrderList(
            Pager pager, String isDel,
            Integer[] order_id,
            HttpServletResponse response,
            Order order,
            String exportType){
        //导出Excel
        if (exportType != null && !"".equals(exportType)){
            orderService.exportExcel(exportType,response,pager,order,order_id);
            return null;
        }
        //删除
        if ("del".equals(isDel)){
            orderService.delOrderListByIds(order_id);
        }


        //获得销售单
        DataModel<Order> dataModel = orderService.getList(pager,order);
        ModelAndView modelAndView = new ModelAndView("/jsp/order/orderList.jsp");
        modelAndView.addObject("pager",dataModel.getPager());
        modelAndView.addObject("orderList",dataModel.getRows());
        return modelAndView;
    }

    @RequestMapping("/toAdd")
    public ModelAndView toAdd(Date c_time){
        List<Suser> salesmans = orderService.getSalesman();

        c_time = new Date();
        ModelAndView modelAndView = new ModelAndView("/jsp/order/orderAdd.jsp");
        modelAndView.addObject("c_time",c_time);
        modelAndView.addObject("salesmans",salesmans);
        return modelAndView;
    }
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(Date u_time,int orderId){
        u_time = new Date();
        //获得数据详细信息
        Order order = orderService.findOrderById(orderId);
        //获得全部业务员
        List<Suser> salesmans = orderService.getSalesman();
        //转发页面
        ModelAndView modelAndView = new ModelAndView("/jsp/order/orderAdd.jsp");
        modelAndView.addObject("order",order);
        modelAndView.addObject("salesmans",salesmans);
        modelAndView.addObject("u_time",u_time);
        return modelAndView;
    }
    //更新或修改
    @RequestMapping("addOrUpdate")
    public ModelAndView addOrUpdate(Integer edit_id,Order order,OrderDetail orderDetail){
        ModelAndView modelAndView = null;
        if (edit_id == null){//新增
            boolean flag=orderService.addOrder(order);
            int orderId = order.getId();
            modelAndView = new ModelAndView("/jsp/order/orderDetail.jsp");
            modelAndView.addObject("orderId",orderId);
//            if(flag){
//                modelAndView.addObject("mess", "新增成功");
//            }else{
//                modelAndView.addObject("mess", "新增失败");
//            }


        } else {//修改
            order.setId(edit_id);
            boolean flag=orderService.updateOrder(order);

            modelAndView = new ModelAndView("/jsp/order/orderDetail.jsp");
            modelAndView.addObject("edit_id",edit_id);

            if(flag){
                modelAndView.addObject("mess", "修改成功");
            }else{
                modelAndView.addObject("mess", "修改失败");
            }
        }
        return modelAndView;

    }

    @RequestMapping("/orderDetail/addOrUpdate")
    public ModelAndView addOrUpdate(Integer edit_id,OrderDetail orderDetail){
        ModelAndView modelAndView = null;
        if (edit_id == null){
            boolean flag = orderService.addOrderDetail(orderDetail);
            modelAndView = new ModelAndView("/order/list");
            if(flag){
                modelAndView.addObject("mess", "修改成功");
            }else{
                modelAndView.addObject("mess", "修改失败");
            }
        } else {
            orderDetail.setOrderId(edit_id);
            boolean flag = orderService.addOrderDetail(orderDetail);
            modelAndView = new ModelAndView("/order/list");
            if(flag){
                modelAndView.addObject("mess", "修改成功");
            }else{
                modelAndView.addObject("mess", "修改失败");
            }
//            boolean flag = orderService.updateOrderDetail(orderDetail);
//            modelAndView = new ModelAndView("/order/list");
//            if(flag){
//                modelAndView.addObject("mess", "修改成功");
//            }else{
//                modelAndView.addObject("mess", "修改失败");
//            }

        }

         return modelAndView;
    }






}
