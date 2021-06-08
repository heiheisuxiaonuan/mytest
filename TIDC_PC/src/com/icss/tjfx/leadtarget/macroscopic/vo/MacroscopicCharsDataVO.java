package com.icss.tjfx.leadtarget.macroscopic.vo;

/**
 * 先行指标宏观VO类
 * @author lcx
 *
 */
public class MacroscopicCharsDataVO {
	private String GDP;
	private String GNP;
	private String CONSSALEATM;
	private String GDPTQ;
	private String GNPTQ;
	private String CONSSALEATMTQ;
	public String getGDP() {
		return GDP;
	}
	public void setGDP(String gDP) {
		GDP = gDP;
	}
	public String getGNP() {
		return GNP;
	}
	public void setGNP(String gNP) {
		GNP = gNP;
	}
	public String getCONSSALEATM() {
		return CONSSALEATM;
	}
	public void setCONSSALEATM(String cONSSALEATM) {
		CONSSALEATM = cONSSALEATM;
	}
	public String getGDPTQ() {
		return GDPTQ;
	}
	public void setGDPTQ(String gDPTQ) {
		GDPTQ = gDPTQ;
	}
	public String getGNPTQ() {
		return GNPTQ;
	}
	public void setGNPTQ(String gNPTQ) {
		GNPTQ = gNPTQ;
	}
	public String getCONSSALEATMTQ() {
		return CONSSALEATMTQ;
	}
	public void setCONSSALEATMTQ(String cONSSALEATMTQ) {
		CONSSALEATMTQ = cONSSALEATMTQ;
	}
	@Override
	public String toString() {
		return "MacroscopicCharsDataVO [GDP=" + GDP + ", GNP=" + GNP
				+ ", CONSSALEATM=" + CONSSALEATM + ", GDPTQ=" + GDPTQ
				+ ", GNPTQ=" + GNPTQ + ", CONSSALEATMTQ=" + CONSSALEATMTQ + "]";
	}
	public MacroscopicCharsDataVO() {
		super();
	}
	
	
	
}
