package com.icss.tjfx.trend.vo;

import java.util.List;

/**
 * 走势图品牌散点图弹出vo模型
 * 
 * @author lkt
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class PopScatterLayerVO {
	private String date;           //时间
	private String xlzf;           //销量增幅
	private String pjzf;           //平均增幅
	private String ppxl;           //品牌销量
	public PopScatterLayerVO() {
		super();
	}
	public PopScatterLayerVO(String date, String xlzf, String pjzf, String ppxl) {
		super();
		this.date = date;
		this.xlzf = xlzf;
		this.pjzf = pjzf;
		this.ppxl = ppxl;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getXlzf() {
		return xlzf;
	}
	public void setXlzf(String xlzf) {
		this.xlzf = xlzf;
	}
	public String getPjzf() {
		return pjzf;
	}
	public void setPjzf(String pjzf) {
		this.pjzf = pjzf;
	}
	public String getPpxl() {
		return ppxl;
	}
	public void setPpxl(String ppxl) {
		this.ppxl = ppxl;
	}
	@Override
	public String toString() {
		return "PopScatterLayerVO [date=" + date + ", xlzf=" + xlzf + ", pjzf="
				+ pjzf + ", ppxl=" + ppxl + ", getDate()=" + getDate()
				+ ", getXlzf()=" + getXlzf() + ", getPjzf()=" + getPjzf()
				+ ", getPpxl()=" + getPpxl() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
