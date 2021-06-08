package com.icss.tjfx.innoproduct.vo;
/**
 * 创新产品商业销量属性VO
 * @author jyb
 * 2017-07-19
 */
public class BusinessSaleVo {
       
	private String BS_SELLNUM;   // 总销量
	private String SINGLE_BS_SELLNUM;// 本期销量
	private String SINGLE_BS_SELLNUM_L;// 同期销量
	private String SINGLE_BS_SELLNUM_GROWTH;// +-量
	private String SINGLE_BS_SELLNUM_GROWTH_RATE;// +-%
	
	public void setBS_SELLNUM(String bS_SELLNUM) {
		BS_SELLNUM = bS_SELLNUM;
	}
	public String getBS_SELLNUM() {
		return BS_SELLNUM;
	}
	public String getSINGLE_BS_SELLNUM() {
		return SINGLE_BS_SELLNUM;
	}
	public void setSINGLE_BS_SELLNUM(String sINGLEBSSELLNUM) {
		SINGLE_BS_SELLNUM = sINGLEBSSELLNUM;
	}
	public String getSINGLE_BS_SELLNUM_L() {
		return SINGLE_BS_SELLNUM_L;
	}
	public void setSINGLE_BS_SELLNUM_L(String sINGLEBSSELLNUML) {
		SINGLE_BS_SELLNUM_L = sINGLEBSSELLNUML;
	}
	public String getSINGLE_BS_SELLNUM_GROWTH() {
		return SINGLE_BS_SELLNUM_GROWTH;
	}
	public void setSINGLE_BS_SELLNUM_GROWTH(String sINGLEBSSELLNUMGROWTH) {
		SINGLE_BS_SELLNUM_GROWTH = sINGLEBSSELLNUMGROWTH;
	}
	public String getSINGLE_BS_SELLNUM_GROWTH_RATE() {
		return SINGLE_BS_SELLNUM_GROWTH_RATE;
	}
	public void setSINGLE_BS_SELLNUM_GROWTH_RATE(String sINGLEBSSELLNUMGROWTHRATE) {
		SINGLE_BS_SELLNUM_GROWTH_RATE = sINGLEBSSELLNUMGROWTHRATE;
	}
	
}
