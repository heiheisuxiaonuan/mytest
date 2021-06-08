package com.icss.tjfx.popcatalog.productlayout.vo;

public class ProductLayCharsVO {
	private String SORT_NO;
	private String MDC_TITLE;
	private String BS_SELLNUM;
	private String COUNT_C_BRAND;
	
	
	
	public String getSORT_NO() {
		return SORT_NO;
	}
	public void setSORT_NO(String sORT_NO) {
		SORT_NO = sORT_NO;
	}
	public String getMDC_TITLE() {
		return MDC_TITLE;
	}
	public void setMDC_TITLE(String mDC_TITLE) {
		MDC_TITLE = mDC_TITLE;
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
	@Override
	public String toString() {
		return "ProductLayCharsVO [SORT_NO=" + SORT_NO + ", MDC_TITLE="
				+ MDC_TITLE + ", BS_SELLNUM=" + BS_SELLNUM + ", COUNT_C_BRAND="
				+ COUNT_C_BRAND + "]";
	}
	public ProductLayCharsVO() {
		super();
	}
	
	

}
