package com.crm.service.impl;

import com.crm.dao.CompanyMapper;
import com.crm.dao.LinkmanMapper;
import com.crm.model.*;
import com.crm.service.OpenService;
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
 * Created by meng on 2017/8/25.
 */
@Service("openService")
public class OpenServiceImpl implements OpenService {
    @Resource
    private CompanyMapper companyMapper;

    @Override
    public DataModel<Company> getList(Pager pager, Company company) {



        DataModel<Company> dataModel = new DataModel<Company>();
        //数据
        List<Company> list = companyMapper.getCompanyList(pager.getFrom(),pager.getPageSize(),company);
        //记录数
        int companyNum = companyMapper.countCompany(company);
        pager.setTotalRecord(companyNum);
        dataModel.setRows(list);
        dataModel.setPager(pager);
        return dataModel;
    }
    //导出
    @Override
    public void exportExcel(String exportType, HttpServletResponse response, Pager pager, Company company) {
        //1.获得数据
        List<Company> list=null;
        if("excel_all".equals(exportType )){//获得所有的商品类别
            list=companyMapper.findAllCompany();
        }else if("excel_page".equals(exportType)){//获得当前页面的商品类别
            list=companyMapper.getCompanyList(pager.getFrom(), pager.getPageSize(), company);
        }
        //2.导出
        ServletOutputStream out=null;
        WritableWorkbook workBook=null;
        try{
            response.setHeader("Content-Disposition", "attachment; filename="+ new String("客户资料.xls".getBytes("utf-8"), "iso-8859-1"));

            out=response.getOutputStream();//获得输出流
            workBook= Workbook.createWorkbook(out);//创建workBook（工作薄）
            WritableSheet sheet=workBook.createSheet("客户资料", 0);
            sheet.addCell(new Label(0, 0, "序号"));//向第0列第0行添加“序号”
            sheet.addCell(new Label(1, 0, "客户名称"));//向第1列第0行添加“类别名称”

            if(list!=null){
                for (int i = 0; i < list.size(); i++) {
                    Company c=list.get(i);
                    sheet.addCell(new Label(0, i+1, String.valueOf(i+1)));
                    if(c.getName()!=null){
                        sheet.addCell(new Label(1, i+1, c.getName()));
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

    @Override
    public List<Suser> getUser() {
        return companyMapper.getUsers();
    }

    /**
     * 得到联系人信息
     * @param pager
     * @param linkman
     * @return
     */
    @Resource
    private LinkmanMapper linkmanMapper;


    @Override
    public DataModel<Linkman> getLinkmanList(Pager pager, Linkman linkman) {
        DataModel<Linkman> dataModel = new DataModel<Linkman>();
        //数据
        List<Linkman> list = linkmanMapper.getLinkmanList(pager.getFrom(),pager.getPageSize(),linkman);
        //记录数
        int linkmanNum = linkmanMapper.countLinkman(linkman);
        pager.setTotalRecord(linkmanNum);
        dataModel.setRows(list);
        dataModel.setPager(pager);
        return dataModel;

    }
}
