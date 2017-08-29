package com.crm.service.impl;

import com.crm.dao.GatherMapper;
import com.crm.model.DataModel;
import com.crm.model.Gather;
import com.crm.model.NeedGather;
import com.crm.model.Pager;
import com.crm.service.GatherService;
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
@Service("gatherService")
public class GatherServiceImpl implements GatherService {
    @Resource
    private GatherMapper gatherMapper;

    /**
     * 导出
     * @param exportType
     * @param response
     * @param pager
     * @param gather
     * @param gather_id
     */
    @Override
    public void exportExcel(String exportType, HttpServletResponse response, Pager pager, Gather gather, Integer[] gather_id) {
        //1.获得数据
        List<Gather> list=null;
        if("excel_all".equals(exportType )){//获得所有的商品类别
            list=gatherMapper.findAllGather();
        }else if("excel_page".equals(exportType)){//获得当前页面的商品类别
            list=gatherMapper.getGatherList(pager.getFrom(), pager.getPageSize(), gather);
        }else if("excel_selected".equals(exportType)){//获得选中的商品类别
            if(gather_id!=null && gather_id.length>0){
                list=gatherMapper.findGatherByIds(gather_id);
            }
        }
        //2.导出
        ServletOutputStream out=null;
        WritableWorkbook workBook=null;
        try{
            response.setHeader("Content-Disposition", "attachment; filename="+ new String("销售收款.xls".getBytes("utf-8"), "iso-8859-1"));

            out=response.getOutputStream();//获得输出流
            workBook= Workbook.createWorkbook(out);//创建workBook（工作薄）
            WritableSheet sheet=workBook.createSheet("销售收款", 0);
            sheet.addCell(new Label(0, 0, "序号"));//向第0列第0行添加“序号”
            sheet.addCell(new Label(1, 0, "帐单号"));//向第1列第0行添加“类别名称”

            if(list!=null){
                for (int i = 0; i < list.size(); i++) {
                    Gather g=list.get(i);
                    sheet.addCell(new Label(0, i+1, String.valueOf(i+1)));
                    if(g.getBillCode()!=null){
                        sheet.addCell(new Label(1, i+1, g.getBillCode()));
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

    /**
     * 查询
     * @param pager
     * @param gather
     * @return
     */
    @Override
    public DataModel<Gather> getList(Pager pager, Gather gather) {

        DataModel<Gather> dataModel = new DataModel<Gather>();
        //数据
        List<Gather> list = gatherMapper.getGatherList(pager.getFrom(),pager.getPageSize(),gather);
        //记录数
        int gatherNum = gatherMapper.countGather(gather);
        pager.setTotalRecord(gatherNum);
        dataModel.setRows(list);
        dataModel.setPager(pager);
        return dataModel;
    }


    /**
     * 删除
     * @param gather_id
     */
    @Override
    public void delListByIds(Integer[] gather_id) {
        if (gather_id != null){
            for (int i = 0;i < gather_id.length;i++){
                int id = gather_id[i];
                gatherMapper.delGatherListByIds(id);
            }
        }
    }

    @Override
    public List<String> getHandler() {
        return gatherMapper.getHandler();
    }
}
