package com.crm.dao;

import com.crm.model.Order;
import com.crm.model.OrderDetail;
import com.crm.model.Suser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKeyWithBLOBs(Order record);

    int updateByPrimaryKey(Order record);


    List<Order> getOderList(@Param("from") int from, @Param("pageSize") int pageSize, @Param("order") Order order);

    int countOrder(@Param("order") Order order);

    List<Suser> getSalesman();

    Order findOrderById(@Param("orderId") int orderId);

    void addOrder(@Param("o") Order order);

    void updateOrder(@Param("o") Order order);

    List<Order> findOrderByIds(@Param("ids") Integer[] order_id);

    List<Order> findAllOrder();

    void delOrderListByIds(@Param("id") int id);

    OrderDetail findOrderDetailById(@Param("id") Integer id);

    void addOrderDetail(@Param("od") OrderDetail orderDetail);

    void updateOrderDetail(@Param("od") OrderDetail orderDetail);

    int getOrderId(@Param("o") Order order);
}