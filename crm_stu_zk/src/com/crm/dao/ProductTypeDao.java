package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.model.ProductType;

public interface ProductTypeDao {

	List<ProductType> getProductTypeList(@Param("from") int from, @Param("pageSize") int pageSize);

	int countProductType();

	List<ProductType> findAllProductType();

	List<ProductType> findRootProductType();

	List<ProductType> findProductTypeByPid(@Param("pid") Integer pid);

	void addProductType(@Param("p") ProductType productType);

	ProductType findProductTypeById(@Param("id") int productTypeId);

	void updateProductType(@Param("p") ProductType productType);

	int countProductTypeByPid(@Param("pid") int id);

	int countProductByPtypeId(@Param("ptypeId") int id);

	void deleteProductTypeById(@Param("id") int id);

	List<ProductType> getProductTypeList2(@Param("from") int from, @Param("pageSize") int pageSize,
                                          @Param("p") ProductType productType);

	int countProductType2(@Param("p") ProductType productType);

	List<ProductType> findProductTypeByIds(@Param("ids") Integer[] productType_id);

}
