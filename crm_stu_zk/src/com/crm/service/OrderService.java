package com.crm.service;

import com.crm.model.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by meng on 2017/8/23.
 */
public interface OrderService {
    DataModel<Order> getList(Pager pager, Order order);

    List<Suser> getSalesman();

    Order findOrderById(int orderId);

    boolean addOrder(Order order);

    boolean updateOrder(Order order);

    void exportExcel(String exportType, HttpServletResponse response, Pager pager, Order order, Integer[] order_id);

    void delOrderListByIds(Integer[] order_id);

    OrderDetail findOrderDetailById(Integer id);

    boolean addOrderDetail(OrderDetail orderDetail);

    boolean updateOrderDetail(OrderDetail orderDetail);

    int getOrderId(Order order);
}
