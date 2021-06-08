package com.icss.tjfx.trend.vo;

public class TaxMaxMin {
	private String cxMax;		//产销最大
	private String cxMin;       //产销最小值
	private String slMax;       //税利最大
	private String slMin;       //税利最小值
	
	public TaxMaxMin() {
		super();
	}
	public TaxMaxMin(String cxMax, String cxMin, String slMax, String slMin) {
		super();
		this.cxMax = cxMax;
		this.cxMin = cxMin;
		this.slMax = slMax;
		this.slMin = slMin;
	}
	public String getCxMax() {
		return cxMax;
	}
	public void setCxMax(String cxMax) {
		this.cxMax = cxMax;
	}
	public String getCxMin() {
		return cxMin;
	}
	public void setCxMin(String cxMin) {
		this.cxMin = cxMin;
	}
	public String getSlMax() {
		return slMax;
	}
	public void setSlMax(String slMax) {
		this.slMax = slMax;
	}
	public String getSlMin() {
		return slMin;
	}
	public void setSlMin(String slMin) {
		this.slMin = slMin;
	}
	@Override
	public String toString() {
		return "TaxMaxMin [cxMax=" + cxMax + ", cxMin=" + cxMin + ", slMax="
				+ slMax + ", slMin=" + slMin + "]";
	}
	
}
