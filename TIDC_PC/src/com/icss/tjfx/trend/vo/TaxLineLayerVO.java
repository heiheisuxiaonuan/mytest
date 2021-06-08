package com.icss.tjfx.trend.vo;

/**
 * 走势图税利折线图弹出vo模型
 * 
 * @author lkt
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class TaxLineLayerVO {
	private String date;		//时间
	private String zl;			//增率
	private String value;		//值
	public TaxLineLayerVO() {
		super();
	}
	public TaxLineLayerVO(String date, String zl, String value) {
		super();
		this.date = date;
		this.zl = zl;
		this.value = value;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "TaxLineLayerVO [date=" + date + ", zl=" + zl + ", value="
				+ value + "]";
	}
}
