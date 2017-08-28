package com.crm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.crm.model.DataModel;
import com.crm.model.Pager;
import com.crm.model.ProductType;
import com.crm.service.ProductTypeService;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;

/**
 * 商品类别
 * @author dz
 *
 */
@Controller
@RequestMapping("/productType")
public class ProductTypeController {
	@Resource(name="productTypeService")
	private ProductTypeService productTypeService;
	
//	@RequestMapping("/list")
//	public ModelAndView getProductTypeList(Pager pager){
//		//获得商品类别列表所需数据
//		DataModel<ProductType> data=productTypeService.getList(pager);
//		
//		ModelAndView mav=new ModelAndView("/jsp/productType/productTypeList.jsp");
//		mav.addObject("pager", data.getPager());
//		mav.addObject("productList", data.getRows());
//		return mav;
//	}
//	@RequestMapping("/list")
//	public ModelAndView getProductTypeList(
//			Pager pager,
//			String isDel,
//			Integer[] productType_id){
//		if("del".equals(isDel)){
//			//删除
//			productTypeService.deleteProductTypeByIds(productType_id);
//			
//		}
//		
//		
//		//获得商品类别列表所需数据
//		DataModel<ProductType> data=productTypeService.getList(pager);
//		
//		ModelAndView mav=new ModelAndView("/jsp/productType/productTypeList.jsp");
//		mav.addObject("pager", data.getPager());
//		mav.addObject("productList", data.getRows());
//		return mav;
//	}
//	@RequestMapping("/list")
//	public ModelAndView getProductTypeList(
//			Pager pager,
//			String isDel,
//			Integer[] productType_id,
//			ProductType productType
//			){
//		if("del".equals(isDel)){
//			//删除
//			productTypeService.deleteProductTypeByIds(productType_id);
//			
//		}
//		//获得商品类别列表所需数据
//		DataModel<ProductType> data=productTypeService.getList(pager,productType);
//		
//		ModelAndView mav=new ModelAndView("/jsp/productType/productTypeList.jsp");
//		mav.addObject("pager", data.getPager());
//		mav.addObject("productList", data.getRows());
//		mav.addObject("productType", productType);
//		return mav;
//	}
	@RequestMapping("/list")
	public ModelAndView getProductTypeList(
			Pager pager,
			String isDel,
			Integer[] productType_id,
			ProductType productType,
			String exportType,
			HttpServletResponse response
			){
		if(exportType!=null && !"".equals(exportType)){
			//导出
			productTypeService.exportExcel(exportType,response,pager,productType,productType_id);
			return null;
		}
		
		
		if("del".equals(isDel)){
			//删除
			productTypeService.deleteProductTypeByIds(productType_id);
			
		}
		//获得商品类别列表所需数据
		DataModel<ProductType> data=productTypeService.getList(pager,productType);
		
		ModelAndView mav=new ModelAndView("/jsp/productType/productTypeList.jsp");
		mav.addObject("pager", data.getPager());
		mav.addObject("productList", data.getRows());
		mav.addObject("productType", productType);
		return mav;
	}
	/**
	 * 跳转到新增商品类别页面
	 * @return
	 */
//	@RequestMapping("toAdd")
//	public ModelAndView toAdd(){
//		//获得所有的商品类别
//		List<ProductType> list=productTypeService.findAllProductType();
//		//创建ModelAndView
//		ModelAndView mav=new ModelAndView("/jsp/productType/productTypeAdd.jsp");
//		//放入数据
//		mav.addObject("productTypeList", list);
//		
//		return mav;
//	}
	@RequestMapping("toAdd")
	public ModelAndView toAdd(){
		//获得所有的商品类别拼成的有层次的字符串
		String str=productTypeService.getStr();
		//创建ModelAndView
		ModelAndView mav=new ModelAndView("/jsp/productType/productTypeAdd.jsp");
		//放入数据
		mav.addObject("str", str);
		
		return mav;
	}
	/**
	 * 商品类别新增
	 * @param productType
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(ProductType productType){
		//新增商品类别
		boolean flag=productTypeService.addProductType(productType);
		//获得所有的商品类别拼成的有层次的字符串
		String str=productTypeService.getStr();
		
		ModelAndView mav=new ModelAndView("/jsp/productType/productTypeAdd.jsp");
		if(flag){
			mav.addObject("mess", "新增成功");
		}else{
			mav.addObject("mess", "新增失败");
		}
		mav.addObject("str", str);
		return mav;
		
	}
	/**
	 * 跳转到商品类别修改页面
	 * @param productTypeId
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(int productTypeId){
		//根据商品类别id获得商品类别信息
		ProductType productType=productTypeService.findProductTypeById(productTypeId);
		//获得所有的商品类别拼成的有层次的字符串
		String str=productTypeService.getStr();
		
		ModelAndView mav=new ModelAndView("/jsp/productType/productTypeAdd.jsp");
		
		mav.addObject("productType", productType);
		mav.addObject("str", str);
		
		return mav;
		
	}
	/**
	 * 新增或修改商品类别
	 * @param productType
	 * @param edit_id
	 * @return
	 */
	@RequestMapping("/addOrUpdate")
	public ModelAndView addOrUpdate(ProductType productType,Integer edit_id){
		ModelAndView mav=new ModelAndView("/jsp/productType/productTypeAdd.jsp");
		if(edit_id==null){//新增
			//新增商品类别
			boolean flag=productTypeService.addProductType(productType);
			
			if(flag){
				mav.addObject("mess", "新增成功");
			}else{
				mav.addObject("mess", "新增失败");
			}
		}else{//修改
			//修改商品类别
			productType.setId(edit_id);
			boolean flag=productTypeService.updateProductType(productType);
			if(flag){
				mav.addObject("mess", "修改成功");
			}else{
				mav.addObject("mess", "修改失败");
			}
			
			mav.addObject("productType", productType);
			
		}
		//获得所有的商品类别拼成的有层次的字符串
		String str=productTypeService.getStr();
		mav.addObject("str", str);
		return mav;
		
	}
	/**
	 * 树形显示商品类别
	 * @return
	 */
	@RequestMapping("/treeWindow")
	public ModelAndView treeWindow(){
		//获得所有商品类别拼成树形字符串
		String str=productTypeService.getTreeStr();
		
		ModelAndView mav=new ModelAndView("/jsp/productType/treeWindow.jsp");
		mav.addObject("str", str);
		return mav;
		
	}
	

}
