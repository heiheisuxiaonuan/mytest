package com.icss.tjfx.total.structure.vo;
/**
 * 分价类分类销量饼图数据转换VO
 * @author lcx
 *
 */
public class PriceClassPieTransVO {
	private String C_CLASS;
	private String NAME;
	private String BS_SELLNUM;
	private String BS_SELLNUM_RATIO;
	public String getC_CLASS() {
		return C_CLASS;
	}
	public String getBS_SELLNUM_RATIO() {
		return BS_SELLNUM_RATIO;
	}
	public void setBS_SELLNUM_RATIO(String bS_SELLNUM_RATIO) {
		BS_SELLNUM_RATIO = bS_SELLNUM_RATIO;
	}
	public void setC_CLASS(String c_CLASS) {
		C_CLASS = c_CLASS;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getBS_SELLNUM() {
		return BS_SELLNUM;
	}
	public void setBS_SELLNUM(String bS_SELLNUM) {
		BS_SELLNUM = bS_SELLNUM;
	}
	
	
	
	
}
