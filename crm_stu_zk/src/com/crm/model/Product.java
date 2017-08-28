package com.crm.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 商品
 * @author dongzheng
 *
 */
public class Product implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8292454118496587231L;
	private Integer id;
	private Integer ptypeId;//商品类别id
	
	private String productTypeName;//商品类别名称
	
	private Integer sort;//排序相关？序号？
	private String creater;//创建人
	private Date createTime;//创建日期
	private String updater;//修改人
	private Date updateTime;//修改时间
	private String remark;//备注
	private Character status;//状态
	private String code;//编码
	private String name;//名称
	private String model;//型号
	private String spec;//规格
	private String unit;//单位
	private String color;//颜色
	private String productSize;//产品尺寸
	private String barCode;//条码
	private String provider;//供应商
	private Double stock;//库存;股份(这里指库存数量)
	private Double upperLimit;
	private Double lowerLimit;
	private Double price;//单价
	private Double buyPrice;//采购价
	private String picture;//图片路径
	private String pycode;//拼音码
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private Date time1;
	private Date time2;
	
	private Double lowerPrice;//最低单价(用于查询)
	private Double upperPrice;//最高单价(用于查询)
	

	public Product() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPtypeId() {
		return ptypeId;
	}

	public void setPtypeId(Integer ptypeId) {
		this.ptypeId = ptypeId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public String getCreateTime2() {
		if(createTime!=null){
			DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
			return dateformat.format(createTime);
		}
		return "";
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public void setCreateTime2(String createTime) throws ParseException {
		System.out.println("createTime::::::::::"+createTime);
		if(createTime!=null && !"".equals(createTime)){
			DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
			this.createTime=dateformat.parse(createTime);
		}
		
		
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public Double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public Double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPycode() {
		return pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}

	public String getCustom1() {
		return custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	public String getCustom2() {
		return custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	public String getCustom3() {
		return custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	public String getCustom4() {
		return custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public Date getTime1() {
		return time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}
	
	public void setTime1Str(String time1) {
		if(time1!=null && !"".equals(time1)){
			DateFormat dataFormat =new SimpleDateFormat("yyyy-MM-dd");
			try {
				this.time1=dataFormat.parse(time1);
			} catch (ParseException e) {
				e.printStackTrace();
				this.time1 = null;
			}
		}
		this.time1 = null;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public Double getLowerPrice() {
		return lowerPrice;
	}

	public void setLowerPrice(Double lowerPrice) {
		this.lowerPrice = lowerPrice;
	}

	public Double getUpperPrice() {
		return upperPrice;
	}

	public void setUpperPrice(Double upperPrice) {
		this.upperPrice = upperPrice;
	}
	
}
