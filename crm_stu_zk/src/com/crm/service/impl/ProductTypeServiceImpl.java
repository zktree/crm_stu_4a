package com.crm.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.crm.service.ProductTypeService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.stereotype.Service;

import com.crm.dao.ProductTypeDao;
import com.crm.model.DataModel;
import com.crm.model.Pager;
import com.crm.model.ProductType;

@Service("productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService {
	@Resource
	private ProductTypeDao productTypeDao;
	/**
	 * 获得商品类别列表的相关数据
	 */
	@Override
	public DataModel<ProductType> getList(Pager pager) {
		DataModel<ProductType> data=new DataModel<ProductType>();
		//分页获得商品类别列表数据
		List<ProductType> list=productTypeDao.getProductTypeList(pager.getFrom(),pager.getPageSize());
		//获得商品类别总数目
		int totalNum=productTypeDao.countProductType();
		
		pager.setTotalRecord(totalNum);
		data.setPager(pager);
		data.setRows(list);
		
		return data;
	}
	/**
	 * 获得所有的商品类别
	 */
	@Override
	public List<ProductType> findAllProductType() {
		return productTypeDao.findAllProductType();
	}
	/**
	 * 获得所有的商品类别拼成的有层次的字符串
	 */
	@Override
	public String getStr() {
		//获得最顶层的商品类别
		List<ProductType> rootList=productTypeDao.findRootProductType();
		
		if(rootList!=null && rootList.size()>0){
			StringBuilder sBuilder=new StringBuilder();
			for (int i = 0; i < rootList.size(); i++) {
				ProductType p=rootList.get(i);
				sBuilder.append("<option value='"+p.getId()+"'>"+p.getName()+"</option>");
				String childStr=getChildStr(p.getId(),"");
				sBuilder.append(childStr);
			}
			return sBuilder.toString();
		}else{
			return "";
		}
	}
	private String getChildStr(Integer id, String nbsp) {
		//根据pid获得下一层的商品类别
		List<ProductType> list=productTypeDao.findProductTypeByPid(id);
		if(list!=null){
			nbsp+="&nbsp;&nbsp;&nbsp;&nbsp;";
			StringBuilder sBuilder=new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				ProductType p=list.get(i);
				sBuilder.append("<option value='"+p.getId()+"'>"+nbsp+p.getName()+"</option>");
				String str=getChildStr(p.getId(),nbsp);
				sBuilder.append(str);
			}
			return sBuilder.toString();
		}
		return "";
	}
	/**
	 * 新增商品类别
	 */
	@Override
	public boolean addProductType(ProductType productType) {
		try{
			productType.setCreateTime(new Date());
			productTypeDao.addProductType(productType);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 根据商品类别id获得商品类别信息
	 */
	@Override
	public ProductType findProductTypeById(int productTypeId) {
		
		return productTypeDao.findProductTypeById(productTypeId);
	}
	/**
	 * 修改商品类别
	 */
	@Override
	public boolean updateProductType(ProductType productType) {
		try{
			productTypeDao.updateProductType(productType);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	/**
	 * 删除商品类别
	 */
	@Override
	public void deleteProductTypeByIds(Integer[] productType_id) {
		if(productType_id!=null){
			for (int i = 0; i < productType_id.length; i++) {
				int id=productType_id[i];
				//获得此商品类别下的子类别数目
				int num=productTypeDao.countProductTypeByPid(id);
				if(num==0){
					//获得此商品类别下的商品数目
					int productNum=productTypeDao.countProductByPtypeId(id);
					if(productNum==0){
						//根据商品类别id删除商品类别
						productTypeDao.deleteProductTypeById(id);
					}
				}
			}
		}
		
	}
	
	@Override
	public DataModel<ProductType> getList(Pager pager, ProductType productType) {
		DataModel<ProductType> data=new DataModel<ProductType>();
		//根据搜索条件分页获得商品类别列表数据
		List<ProductType> list=productTypeDao.getProductTypeList2(pager.getFrom(),pager.getPageSize(),productType);
		//根据搜索条件获得符合条件商品类别数目
		int totalNum=productTypeDao.countProductType2(productType);
		
		pager.setTotalRecord(totalNum);
		data.setPager(pager);
		data.setRows(list);
		
		return data;
	}
	/**
	 * 获得所有商品类别拼成树形字符串
	 */
	@Override
	public String getTreeStr() {
		StringBuilder sBuilder =new StringBuilder();
		sBuilder.append("<ul class='simpleTree'>" +
				"<li class='root'><span>根节点</span>");
		//查询最顶层的商品类别
		List<ProductType> list=productTypeDao.findRootProductType();
		if(list!=null && list.size()>0){
			sBuilder.append("<ul>");
			for (int i = 0; i < list.size(); i++) {
				ProductType p=list.get(i);
				sBuilder.append("<li id='"+p.getId()+"'><span>"+p.getName()+"</span>");
				String str=getChildTreeStr(p.getId());
				sBuilder.append(str);
				sBuilder.append("</li>");
			}
			
			
			sBuilder.append("</ul>");
		}
		sBuilder.append("</li></ul>");
		return sBuilder.toString();
	}
	private String getChildTreeStr(Integer pid) {
		StringBuilder sBuilder=new StringBuilder();
		//根据pid获得商品类别
		List<ProductType> list=productTypeDao.findProductTypeByPid(pid);
		if(list!=null && list.size()>0){
			sBuilder.append("<ul>");
			for (int i = 0; i < list.size(); i++) {
				ProductType p=list.get(i);
				sBuilder.append("<li id='"+p.getId()+"'><span>"+p.getName()+"</span>");
				String str=getChildTreeStr(p.getId());
				sBuilder.append(str);
				sBuilder.append("</li>");
			}
			sBuilder.append("</ul>");
		}
		return sBuilder.toString();
	}
	/**
	 * 导出Excel
	 */
	@Override
	public void exportExcel(String exportType, HttpServletResponse response,
			Pager pager, ProductType productType, Integer[] productType_id) {
		//1.获得数据
		List<ProductType> list=null;
		if("excel_all".equals(exportType)){//获得所有的商品类别
			list=productTypeDao.findAllProductType();
		}else if("excel_page".equals(exportType)){//获得当前页面的商品类别
			list=productTypeDao.getProductTypeList2(pager.getFrom(), pager.getPageSize(), productType);
		}else if("excel_selected".equals(exportType)){//获得选中的商品类别
			if(productType_id!=null && productType_id.length>0){
				list=productTypeDao.findProductTypeByIds(productType_id);
			}
		}
		//2.导出
		ServletOutputStream out=null;
		WritableWorkbook workBook=null;
		try{
			response.setHeader("Content-Disposition", "attachment; filename="+ new String("商品类别.xls".getBytes("utf-8"), "iso-8859-1"));
			
			out=response.getOutputStream();//获得输出流
			workBook=Workbook.createWorkbook(out);//创建workBook（工作薄）
			WritableSheet sheet=workBook.createSheet("商品类别", 0);
			sheet.addCell(new Label(0, 0, "序号"));//向第0列第0行添加“序号”
			sheet.addCell(new Label(1, 0, "类别名称"));//向第1列第0行添加“类别名称”
			sheet.addCell(new Label(2, 0, "类别编码"));//向第2列第0行添加“类别编码”
			
			if(list!=null){
				for (int i = 0; i < list.size(); i++) {
					ProductType p=list.get(i);
					sheet.addCell(new Label(0, i+1, String.valueOf(i+1)));
					if(p.getName()!=null){
						sheet.addCell(new Label(1, i+1, p.getName()));
					}
					
					sheet.addCell(new Label(2, i+1, p.getCode()));
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}	
		
	}

}
