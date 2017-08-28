package com.crm.service.impl;

import com.crm.dao.OrderMapper;
import com.crm.model.*;
import com.crm.service.OrderService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by meng on 2017/8/23.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 按条件给出列表
     * @param pager
     * @param order
     * @return
     */
    @Override
    public DataModel<Order> getList(Pager pager, Order order) {
        DataModel<Order> dataModel = new DataModel<Order>();
        //数据
        List<Order> list = orderMapper.getOderList(pager.getFrom(),pager.getPageSize(),order);
        //记录数
        int orderNum = orderMapper.countOrder(order);
        pager.setTotalRecord(orderNum);
        dataModel.setRows(list);
        dataModel.setPager(pager);
        return dataModel;
    }

    @Override
    public List<Suser> getSalesman() {

        return orderMapper.getSalesman();
    }

    @Override
    public Order findOrderById(int orderId) {



        return orderMapper.findOrderById(orderId);
    }

    @Override
    public boolean addOrder(Order order) {

        try {
            orderMapper.addOrder(order);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        try {
            orderMapper.updateOrder(order);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void exportExcel(String exportType, HttpServletResponse response, Pager pager, Order order, Integer[] order_id) {
        //1.获得数据
        List<Order> list=null;
        if("excel_all".equals(exportType )){//获得所有的商品类别
            list=orderMapper.findAllOrder();
        }else if("excel_page".equals(exportType)){//获得当前页面的商品类别
            list=orderMapper.getOderList(pager.getFrom(), pager.getPageSize(), order);
        }else if("excel_selected".equals(exportType)){//获得选中的商品类别
            if(order_id!=null && order_id.length>0){
                list=orderMapper.findOrderByIds(order_id);
            }
        }
        //2.导出
        ServletOutputStream out=null;
        WritableWorkbook workBook=null;
        try{
            response.setHeader("Content-Disposition", "attachment; filename="+ new String("销售单.xls".getBytes("utf-8"), "iso-8859-1"));

            out=response.getOutputStream();//获得输出流
            workBook= Workbook.createWorkbook(out);//创建workBook（工作薄）
            WritableSheet sheet=workBook.createSheet("销售单", 0);
            sheet.addCell(new Label(0, 0, "序号"));//向第0列第0行添加“序号”
            sheet.addCell(new Label(1, 0, "销售单号"));//向第1列第0行添加“类别名称”

            if(list!=null){
                for (int i = 0; i < list.size(); i++) {
                    Order o=list.get(i);
                    sheet.addCell(new Label(0, i+1, String.valueOf(i+1)));
                    if(o.getCode()!=null){
                        sheet.addCell(new Label(1, i+1, o.getCode()));
                    }
                }
            }
            workBook.write();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(workBook!=null){
                try {
                    workBook.close();
                } catch (WriteException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    @Override
    public void delOrderListByIds(Integer[] order_id) {
        if (order_id != null){
            for (int i = 0;i < order_id.length;i++){
                int id = order_id[i];
                orderMapper.delOrderListByIds(id);
            }
        }
    }

    @Override
    public OrderDetail findOrderDetailById(Integer id) {

        return orderMapper.findOrderDetailById(id);

    }

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) {
        try {
            orderMapper.addOrderDetail(orderDetail);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        try {
            orderMapper.updateOrderDetail(orderDetail);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getOrderId(Order order) {
        return orderMapper.getOrderId(order);
    }
}
