package com.crm.service.impl;

import com.crm.dao.NeedGatherMapper;
import com.crm.model.DataModel;
import com.crm.model.NeedGather;
import com.crm.model.Pager;
import com.crm.service.NeedGatherService;
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
 * Created by meng on 2017/8/28.
 */
@Service("needGatherService")
public class NeedGatherServiceImpl implements NeedGatherService {

    @Resource
    private NeedGatherMapper needGatherMapper;

    @Override
    public DataModel<NeedGather> getList(Pager pager, NeedGather needGather) {
        DataModel<NeedGather> dataModel = new DataModel<NeedGather>();
        //数据
        List<NeedGather> list = needGatherMapper.getNeedGatherList(pager.getFrom(),pager.getPageSize(),needGather);
        //记录数
        int orderNum = needGatherMapper.countNeedGather(needGather);
        pager.setTotalRecord(orderNum);
        dataModel.setRows(list);
        dataModel.setPager(pager);
        return dataModel;
    }

    @Override
    public void exportExcel(String exportType, HttpServletResponse response, Pager pager, NeedGather needGather, Integer[] needGather_id) {
        //1.获得数据
        List<NeedGather> list=null;
        if("excel_all".equals(exportType )){//获得所有的商品类别
            list=needGatherMapper.findAllOrder();
        }else if("excel_page".equals(exportType)){//获得当前页面的商品类别
            list=needGatherMapper.getNeedGatherList(pager.getFrom(), pager.getPageSize(), needGather);
        }else if("excel_selected".equals(exportType)){//获得选中的商品类别
            if(needGather_id!=null && needGather_id.length>0){
                list=needGatherMapper.findOrderByIds(needGather_id);
            }
        }
        //2.导出
        ServletOutputStream out=null;
        WritableWorkbook workBook=null;
        try{
            response.setHeader("Content-Disposition", "attachment; filename="+ new String("应收账款.xls".getBytes("utf-8"), "iso-8859-1"));

            out=response.getOutputStream();//获得输出流
            workBook= Workbook.createWorkbook(out);//创建workBook（工作薄）
            WritableSheet sheet=workBook.createSheet("应收账款", 0);
            sheet.addCell(new Label(0, 0, "序号"));//向第0列第0行添加“序号”
            sheet.addCell(new Label(1, 0, "销售单号"));//向第1列第0行添加“类别名称”

            if(list!=null){
                for (int i = 0; i < list.size(); i++) {
                    NeedGather n=list.get(i);
                    sheet.addCell(new Label(0, i+1, String.valueOf(i+1)));
                    if(n.getSaleCode()!=null){
                        sheet.addCell(new Label(1, i+1, n.getSaleCode()));
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
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    @Override
    public void delListByIds(Integer[] needGather_id) {
        if (needGather_id != null){
            for (int i = 0;i < needGather_id.length;i++){
                int id = needGather_id[i];
                needGatherMapper.delOrderListByIds(id);
            }
        }
    }
}
