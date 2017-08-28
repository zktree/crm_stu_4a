package com.crm.service.impl;

import com.crm.dao.ProductDao;
import com.crm.model.DataModel;
import com.crm.model.Pager;
import com.crm.model.Product;
import com.crm.model.ProductType;
import com.crm.service.ProductService;
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
 * Created by meng on 2017/8/19.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;

    @Override
    public DataModel<Product> getList(Pager pager) {
        DataModel<Product> data = new DataModel<Product>();
        //获得商品资料的分页数据
        List<Product> list = productDao.getProductList(pager.getFrom(),pager.getPageSize());
        //获取商品资料总记录数
        int productNum = productDao.countProduct();

        pager.setTotalRecord(productNum);
        data.setPager(pager);
        data.setRows(list);
        return data;
    }

    @Override
    public void delProductListByIds(Integer[] product_id) {
        if (product_id != null){
            for (int i = 0;i < product_id.length;i++){
                int id = product_id[i];
                productDao.delProductListByIds(id);
            }
        }
    }

    /**
     * 按条件进行搜索
     * @param pager
     * @param product
     * @return
     */
    @Override
    public DataModel<Product> getList(Pager pager, Product product) {
        DataModel<Product> data = new DataModel<Product>();
        //获得商品资料的分页数据
        List<Product> list = productDao.getProductList2(pager.getFrom(),pager.getPageSize(),product);
        //获取商品资料总记录数
        int productNum = productDao.countProduct1(product);

        pager.setTotalRecord(productNum);
        data.setPager(pager);
        data.setRows(list);
        return data;
    }


    /**
     * 新增
     */
    @Override
    public boolean addProduct(Product product) {
        try {
            productDao.addProduct(product);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        try {
            productDao.updateProduct(product);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;

        }
    }

    /**
     * 去新增，查到相关数据
     * @return
     * @param productId
     */
    @Override
    public Product findProductById(int productId) {
        return productDao.findProductById(productId);
    }

    /**
     * 导出Excel
     */
    @Override
    public void exportExcel(String exportType, HttpServletResponse response,
                            Pager pager, Product product, Integer[] product_id) {
        //1.获得数据
        List<Product> list=null;
        if("excel_all".equals(exportType )){//获得所有的商品类别
            list=productDao.findAllProduct();
        }else if("excel_page".equals(exportType)){//获得当前页面的商品类别
            list=productDao.getProductList2(pager.getFrom(), pager.getPageSize(), product);
        }else if("excel_selected".equals(exportType)){//获得选中的商品类别
            if(product_id!=null && product_id.length>0){
                list=productDao.findProductByIds(product_id);
            }
        }
        //2.导出
        ServletOutputStream out=null;
        WritableWorkbook workBook=null;
        try{
            response.setHeader("Content-Disposition", "attachment; filename="+ new String("商品资料.xls".getBytes("utf-8"), "iso-8859-1"));

            out=response.getOutputStream();//获得输出流
            workBook=Workbook.createWorkbook(out);//创建workBook（工作薄）
            WritableSheet sheet=workBook.createSheet("商品资料", 0);
            sheet.addCell(new Label(0, 0, "序号"));//向第0列第0行添加“序号”
            sheet.addCell(new Label(1, 0, "资料名称"));//向第1列第0行添加“类别名称”

            if(list!=null){
                for (int i = 0; i < list.size(); i++) {
                    Product p=list.get(i);
                    sheet.addCell(new Label(0, i+1, String.valueOf(i+1)));
                    if(p.getName()!=null){
                        sheet.addCell(new Label(1, i+1, p.getName()));
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
