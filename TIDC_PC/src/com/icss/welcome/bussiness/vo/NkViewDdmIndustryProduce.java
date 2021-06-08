package com.icss.welcome.bussiness.vo;


public class NkViewDdmIndustryProduce {
	private String induCode;
	private String pronum1;
	
	public String getInduCode() {
		return induCode;
	}
	public void setInduCode(String induCode) {
		this.induCode = induCode;
	}
	public String getPronum1() {
		return pronum1;
	}
	public void setPronum1(String pronum1) {
		this.pronum1 = pronum1;
	}
	@Override
	public String toString() {
		return "NkViewDdmIndustryProduce [induCode=" + induCode + ", pronum1=" + pronum1 + "]";
	}
	
}
