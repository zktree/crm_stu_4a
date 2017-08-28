package com.crm.dao;

import com.crm.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

/**
 * Created by meng on 2017/8/20.
 */
public interface ProductDao {
    List<Product> getProductList(@Param("from") int from, @Param("pageSize") int pageSize);

    int countProduct();

    void delProductListByIds(int id);

    List<Product> getProductList2(@Param("from") int from, @Param("pageSize") int pageSize,@Param("product") Product product);

    void addProduct(@Param("p") Product product);

    Product findProductById(@Param("productId") int productId);

    void updateProduct(@Param("p") Product product);

    List<Product> findAllProduct();

    List<Product> findProductByIds(@Param("ids") Integer[] product_id);

    int countProduct1(@Param("product") Product product);
}
