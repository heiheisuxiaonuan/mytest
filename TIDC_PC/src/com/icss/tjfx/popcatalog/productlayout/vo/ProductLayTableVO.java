package com.icss.tjfx.popcatalog.productlayout.vo;
/**
 * 品牌目录产品布局table属性VO
 * @author lcx
 * 2017-06-26
 */
public class ProductLayTableVO {
	private String NAME;						//分类
	private String COUNT_brand;					//规格数量
	private String C_BRAND_PER;					//占比重
	private String PRODUCENUM;					//产量
	private String PRODUCENUM_PER;				//占比重
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getCOUNT_brand() {
		return COUNT_brand;
	}
	public void setCOUNT_brand(String cOUNT_brand) {
		COUNT_brand = cOUNT_brand;
	}
	public String getC_BRAND_PER() {
		return C_BRAND_PER;
	}
	public void setC_BRAND_PER(String c_BRAND_PER) {
		C_BRAND_PER = c_BRAND_PER;
	}
	public String getPRODUCENUM() {
		return PRODUCENUM;
	}
	public void setPRODUCENUM(String pRODUCENUM) {
		PRODUCENUM = pRODUCENUM;
	}
	public String getPRODUCENUM_PER() {
		return PRODUCENUM_PER;
	}
	public void setPRODUCENUM_PER(String pRODUCENUM_PER) {
		PRODUCENUM_PER = pRODUCENUM_PER;
	}
	@Override
	public String toString() {
		return "ProductLayTableVO [NAME=" + NAME + ", COUNT_brand="
				+ COUNT_brand + ", C_BRAND_PER=" + C_BRAND_PER
				+ ", PRODUCENUM=" + PRODUCENUM + ", PRODUCENUM_PER="
				+ PRODUCENUM_PER + "]";
	}
	public ProductLayTableVO() {
		super();
	}
	
	
}