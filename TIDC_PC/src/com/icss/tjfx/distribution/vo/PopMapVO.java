package com.icss.tjfx.distribution.vo;

/**
 * 分布图品牌地图vo模型
 * 
 * @author zhaofeng
 * @version 1.0
 * 
 * */
public class PopMapVO { 
	private String provorgname;
	private String brandcode;		//品牌规格代码
	private String provcode;		//省份代码
	private String provname;		//中烟名称/单位名称
	private String sellnum;			//销量
	private String iscover;			//是否开式、覆盖
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
	public String getIscover() {
		return iscover;
	}
	public void setIscover(String iscover) {
		this.iscover = iscover;
	}
	public PopMapVO(String brandcode, String provcode, String provname,
			String sellnum, String iscover) {
		super();
		this.brandcode = brandcode;
		this.provcode = provcode;
		this.provname = provname;
		this.sellnum = sellnum;
		this.iscover = iscover;
	}
	public PopMapVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBrandcode() {
		return brandcode;
	}
	public void setBrandcode(String brandcode) {
		this.brandcode = brandcode;
	}
	public String getProvcode() {
		return provcode;
	}
	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}
	public void setProvorgname(String provorgname) {
		this.provorgname = provorgname;
	}
	public String getProvorgname() {
		return provorgname;
	}
	
}
