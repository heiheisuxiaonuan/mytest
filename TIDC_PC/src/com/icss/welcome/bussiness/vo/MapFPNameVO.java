package com.icss.welcome.bussiness.vo;

public class MapFPNameVO {

	private String factPopCode;
	private String ProvCode;
	private String factPopName;
	private String ProvName;
	private String pronum1;
	private String outNumBs;

	public MapFPNameVO() {
		super();
	}

	public String getFactPopCode() {
		return factPopCode;
	}

	public void setFactPopCode(String factPopCode) {
		this.factPopCode = factPopCode;
	}

	public String getProvCode() {
		return ProvCode;
	}

	public void setProvCode(String provCode) {
		ProvCode = provCode;
	}

	public String getFactPopName() {
		return factPopName;
	}

	public void setFactPopName(String factPopName) {
		this.factPopName = factPopName;
	}

	public String getProvName() {
		return ProvName;
	}

	public void setProvName(String provName) {
		ProvName = provName;
	}

	public String getOutNumBs() {
		return outNumBs;
	}

	public void setOutNumBs(String outNumBs) {
		this.outNumBs = outNumBs;
	}

	public String getPronum1() {
		return pronum1;
	}

	public void setPronum1(String pronum1) {
		this.pronum1 = pronum1;
	}

	public MapFPNameVO(String factPopCode, String provCode, String factPopName, String provName, String pronum1,
			String outNumBs) {
		super();
		this.factPopCode = factPopCode;
		ProvCode = provCode;
		this.factPopName = factPopName;
		ProvName = provName;
		this.pronum1 = pronum1;
		this.outNumBs = outNumBs;
	}

	@Override
	public String toString() {
		return "MapFPNameVO [factPopCode=" + factPopCode + ", ProvCode=" + ProvCode + ", factPopName=" + factPopName
				+ ", ProvName=" + ProvName + ", pronum1=" + pronum1 + ", outNumBs=" + outNumBs + "]";
	}

}
