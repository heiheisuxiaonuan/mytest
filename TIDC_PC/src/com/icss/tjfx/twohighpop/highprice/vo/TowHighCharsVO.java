package com.icss.tjfx.twohighpop.highprice.vo;

public class TowHighCharsVO {
	 private String BRAND_NAME;				//品牌名
	 private String BS_SELLNUM;				//销量
	 private String COUNT_C_BRAND;			//高价位规格数量
	 private String HIGH_BS_SELLNUM;				//高端烟销量
	 private String HIGH_BS_SELLSUM;				//高端烟销售额
	 private String ALL_COUNT_C_BRAND;     //所有规格量汇总
	 private String ALL_BS_SELLNUM;			//所有规格烟销量汇总
	 private String ALL_BS_SELLSUM;			//所有规格烟销售额汇总
	public String getBRAND_NAME() {
		return BRAND_NAME;
	}
	public void setBRAND_NAME(String bRAND_NAME) {
		BRAND_NAME = bRAND_NAME;
	}
	public String getBS_SELLNUM() {
		return BS_SELLNUM;
	}
	public void setBS_SELLNUM(String bS_SELLNUM) {
		BS_SELLNUM = bS_SELLNUM;
	}
	public String getCOUNT_C_BRAND() {
		return COUNT_C_BRAND;
	}
	public void setCOUNT_C_BRAND(String cOUNT_C_BRAND) {
		COUNT_C_BRAND = cOUNT_C_BRAND;
	}
	public String getHIGH_BS_SELLNUM() {
		return HIGH_BS_SELLNUM;
	}
	public void setHIGH_BS_SELLNUM(String hIGH_BS_SELLNUM) {
		HIGH_BS_SELLNUM = hIGH_BS_SELLNUM;
	}
	public String getALL_BS_SELLNUM() {
		return ALL_BS_SELLNUM;
	}
	public void setALL_BS_SELLNUM(String aLL_BS_SELLNUM) {
		ALL_BS_SELLNUM = aLL_BS_SELLNUM;
	}
	public String getALL_BS_SELLSUM() {
		return ALL_BS_SELLSUM;
	}
	public void setALL_BS_SELLSUM(String aLL_BS_SELLSUM) {
		ALL_BS_SELLSUM = aLL_BS_SELLSUM;
	}
	@Override
	public String toString() {
		return "TowHighCharsVO [BRAND_NAME=" + BRAND_NAME + ", BS_SELLNUM="
				+ BS_SELLNUM + ", COUNT_C_BRAND=" + COUNT_C_BRAND
				+ ", HIGH_BS_SELLNUM=" + HIGH_BS_SELLNUM + ", HIGH_BS_SELLSUM="
				+ HIGH_BS_SELLSUM + ",ALL_COUNT_C_BRAND="+ALL_COUNT_C_BRAND+", ALL_BS_SELLNUM=" + ALL_BS_SELLNUM
				+ ", ALL_BS_SELLSUM=" + ALL_BS_SELLSUM + "]";
	}
	public TowHighCharsVO() {
		super();
	}
	public void setALL_COUNT_C_BRAND(String aLL_COUNT_C_BRAND) {
		ALL_COUNT_C_BRAND = aLL_COUNT_C_BRAND;
	}
	public String getALL_COUNT_C_BRAND() {
		return ALL_COUNT_C_BRAND;
	}
	public void setHIGH_BS_SELLSUM(String hIGH_BS_SELLSUM) {
		HIGH_BS_SELLSUM = hIGH_BS_SELLSUM;
	}
	public String getHIGH_BS_SELLSUM() {
		return HIGH_BS_SELLSUM;
	}
	 

}
