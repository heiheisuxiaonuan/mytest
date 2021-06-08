package com.icss.tjfx.distribution.vo;

/**
 * 分布图税利地图vo模型
 * 
 * @author zhaofeng
 * @version 1.0
 * 
 * */
public class TaxProfitVO {
	private String provname;		//省份
	private String intax;			//工业税利
	private String bstax;			//商业税利
	private String ibtax;			//工商税利
	public TaxProfitVO() {
		super();
	}
	public String getProvname() {
		return provname;
	}
	public void setProvname(String provname) {
		this.provname = provname;
	}
	public String getIbtax() {
		return ibtax;
	}
	public void setIbtax(String ibtax) {
		this.ibtax = ibtax;
	}
	public String getIntax() {
		return intax;
	}
	public void setIntax(String intax) {
		this.intax = intax;
	}
	public String getBstax() {
		return bstax;
	}
	public void setBstax(String bstax) {
		this.bstax = bstax;
	}
	@Override
	public String toString() {
		return "TaxProfitVO [bstax=" + bstax + ", ibtax=" + ibtax + ", intax="
				+ intax + ", provname=" + provname + "]";
	}
	public TaxProfitVO(String provname, String ibtax, String intax, String bstax) {
		super();
		this.provname = provname;
		this.ibtax = ibtax;
		this.intax = intax;
		this.bstax = bstax;
	}
	
}
