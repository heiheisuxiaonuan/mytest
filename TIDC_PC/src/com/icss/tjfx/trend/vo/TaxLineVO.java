package com.icss.tjfx.trend.vo;

/**
 * 走势图税利折线图vo模型
 * 
 * @author lkt
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class TaxLineVO {
	private String date;		//年份
	private String cl;			//产量
	private String xl;			//销量
	private String sl;			//税利
	public TaxLineVO() {
		super();
	}
	public TaxLineVO(String date, String cl, String xl, String sl) {
		super();
		this.date = date;
		this.cl = cl;
		this.xl = xl;
		this.sl = sl;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCl() {
		return cl;
	}
	public void setCl(String cl) {
		this.cl = cl;
	}
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	@Override
	public String toString() {
		return "TaxLineVO [date=" + date + ", cl=" + cl + ", xl=" + xl
				+ ", sl=" + sl + ", getDate()=" + getDate() + ", getCl()="
				+ getCl() + ", getXl()=" + getXl() + ", getSl()=" + getSl()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
