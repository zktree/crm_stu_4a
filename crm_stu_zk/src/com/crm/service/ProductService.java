package com.crm.service;

import com.crm.model.DataModel;
import com.crm.model.Pager;
import com.crm.model.Product;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by meng on 2017/8/19.
 */
public interface ProductService {

    DataModel<Product> getList(Pager pager);

    void delProductListByIds(Integer[] product_id);

    DataModel<Product> getList(Pager pager, Product product);


    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    Product findProductById(int productId);

    void exportExcel(String exportType, HttpServletResponse response, Pager pager, Product product, Integer[] product_id);
}
