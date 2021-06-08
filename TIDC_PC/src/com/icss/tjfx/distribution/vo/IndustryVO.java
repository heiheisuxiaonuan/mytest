package com.icss.tjfx.distribution.vo;

/**
 * 分布图中烟地图vo模型
 * 
 * @author zhaofeng
 * @version 1.0
 * 
 * */
public class IndustryVO {
	private String provname;		//中烟名称
	private String sellnum;			//销量
	
	public IndustryVO(String provname, String sellnum) {
		super();
		this.provname = provname;
		this.sellnum = sellnum;
	} 
	public IndustryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProvname() {
		return provname;
	}
	public void setProvname(String provname) {
		this.provname = provname;
	}
	public String getSellnum() {
		return sellnum;
	}
	public void setSellnum(String sellnum) {
		this.sellnum = sellnum;
	}
	@Override
	public String toString() {
		return "IndustryVO [provname=" + provname + ", sellnum=" + sellnum
				+ "]";
	}
	
}
