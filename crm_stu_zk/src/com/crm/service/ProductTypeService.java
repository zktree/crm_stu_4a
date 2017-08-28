package com.crm.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.crm.model.DataModel;
import com.crm.model.Pager;
import com.crm.model.ProductType;

public interface ProductTypeService {

	DataModel<ProductType> getList(Pager pager);

	List<ProductType> findAllProductType();

	String getStr();

	boolean addProductType(ProductType productType);

	ProductType findProductTypeById(int productTypeId);

	boolean updateProductType(ProductType productType);

	void deleteProductTypeByIds(Integer[] productType_id);

	DataModel<ProductType> getList(Pager pager, ProductType productType);

	String getTreeStr();

	void exportExcel(String exportType, HttpServletResponse response,
                     Pager pager, ProductType productType, Integer[] productType_id);

}
