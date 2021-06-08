package com.icss.tjfx.popcatalog.productinformation.vo;
/**
 * 两高品牌展示页table属性VO
 * @author lcx
 * 2017-06-26
 */
public class ProductInTableVO {
	
	private String SORT_NO;					
	private String xz_parameter;						//下钻参数
	private String CIG_NAME;							//品牌名称
	//产量
	private String PRODUCENUM;							
	private String PRODUCENUM_L;
	private String PRODUCENUM_GROWTH_RATE;
	//工业库存
	private String TERMSTOCK1;
	private String TERMSTOCK1_L;
	private String TERMSTOCK1_GROWTH;
	//商业销量
	private String BS_SELLNUM;
	private String BS_SELLNUM_L;
	private String BS_SELLNUM_GROWTH_RATE;
	//商业库存
	private String TERM_STOCK1;
	private String TERM_STOCK1_L;
	private String TERM_STOCK1_GROWTH_RATE;
	
	//商业销售额
	private String BS_SELLSUM;
	private String BS_SELLSUM_L;
	private String BS_SELLSUM_GROWTH_RATE;
	

	private String JIALEI;					//价类
	private String TRANSFERPRICE;			//调拨价
	private String WHOLESALEPRICE;			//批发价
	private String SUGGESTEDRETAILPRICE;	//零售价
	//private String BRAND_TYPE_NAME;			//属性
	
	private String IS_THIN;
	private String IS_BEAD;
	private String IS_SHORT;
	
	private String picture;     
	
	
	public String getIS_BEAD() {
		return IS_BEAD;
	}
	public void setIS_BEAD(String iS_BEAD) {
		IS_BEAD = iS_BEAD;
	}
	public String getIS_THIN() {
		return IS_THIN;
	}
	public void setIS_THIN(String iS_THIN) {
		IS_THIN = iS_THIN;
	}
	public String getIS_SHORT() {
		return IS_SHORT;
	}
	public void setIS_SHORT(String iS_SHORT) {
		IS_SHORT = iS_SHORT;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSORT_NO() {
		return SORT_NO;
	}
	public void setSORT_NO(String sORT_NO) {
		SORT_NO = sORT_NO;
	}
	public String getXz_parameter() {
		return xz_parameter;
	}
	public void setXz_parameter(String xz_parameter) {
		this.xz_parameter = xz_parameter;
	}
	public String getCIG_NAME() {
		return CIG_NAME;
	}
	public void setCIG_NAME(String cIG_NAME) {
		CIG_NAME = cIG_NAME;
	}
	public String getPRODUCENUM() {
		return PRODUCENUM;
	}
	public void setPRODUCENUM(String pRODUCENUM) {
		PRODUCENUM = pRODUCENUM;
	}
	public String getPRODUCENUM_L() {
		return PRODUCENUM_L;
	}
	public void setPRODUCENUM_L(String pRODUCENUM_L) {
		PRODUCENUM_L = pRODUCENUM_L;
	}
	public String getPRODUCENUM_GROWTH_RATE() {
		return PRODUCENUM_GROWTH_RATE;
	}
	public void setPRODUCENUM_GROWTH_RATE(String pRODUCENUM_GROWTH_RATE) {
		PRODUCENUM_GROWTH_RATE = pRODUCENUM_GROWTH_RATE;
	}
	public String getTERMSTOCK1() {
		return TERMSTOCK1;
	}
	public void setTERMSTOCK1(String tERMSTOCK1) {
		TERMSTOCK1 = tERMSTOCK1;
	}
	public String getTERMSTOCK1_L() {
		return TERMSTOCK1_L;
	}
	public void setTERMSTOCK1_L(String tERMSTOCK1_L) {
		TERMSTOCK1_L = tERMSTOCK1_L;
	}
	public String getTERMSTOCK1_GROWTH() {
		return TERMSTOCK1_GROWTH;
	}
	public void setTERMSTOCK1_GROWTH(String tERMSTOCK1_GROWTH) {
		TERMSTOCK1_GROWTH = tERMSTOCK1_GROWTH;
	}
	public String getBS_SELLNUM() {
		return BS_SELLNUM;
	}
	public void setBS_SELLNUM(String bS_SELLNUM) {
		BS_SELLNUM = bS_SELLNUM;
	}
	public String getBS_SELLNUM_L() {
		return BS_SELLNUM_L;
	}
	public void setBS_SELLNUM_L(String bS_SELLNUM_L) {
		BS_SELLNUM_L = bS_SELLNUM_L;
	}
	public String getBS_SELLNUM_GROWTH_RATE() {
		return BS_SELLNUM_GROWTH_RATE;
	}
	public void setBS_SELLNUM_GROWTH_RATE(String bS_SELLNUM_GROWTH_RATE) {
		BS_SELLNUM_GROWTH_RATE = bS_SELLNUM_GROWTH_RATE;
	}
	public String getTERM_STOCK1() {
		return TERM_STOCK1;
	}
	public void setTERM_STOCK1(String tERM_STOCK1) {
		TERM_STOCK1 = tERM_STOCK1;
	}
	public String getTERM_STOCK1_L() {
		return TERM_STOCK1_L;
	}
	public void setTERM_STOCK1_L(String tERM_STOCK1_L) {
		TERM_STOCK1_L = tERM_STOCK1_L;
	}
	public String getTERM_STOCK1_GROWTH_RATE() {
		return TERM_STOCK1_GROWTH_RATE;
	}
	public void setTERM_STOCK1_GROWTH_RATE(String tERM_STOCK1_GROWTH_RATE) {
		TERM_STOCK1_GROWTH_RATE = tERM_STOCK1_GROWTH_RATE;
	}
	public String getBS_SELLSUM() {
		return BS_SELLSUM;
	}
	public void setBS_SELLSUM(String bS_SELLSUM) {
		BS_SELLSUM = bS_SELLSUM;
	}
	public String getBS_SELLSUM_L() {
		return BS_SELLSUM_L;
	}
	public void setBS_SELLSUM_L(String bS_SELLSUM_L) {
		BS_SELLSUM_L = bS_SELLSUM_L;
	}
	public String getBS_SELLSUM_GROWTH_RATE() {
		return BS_SELLSUM_GROWTH_RATE;
	}
	public void setBS_SELLSUM_GROWTH_RATE(String bS_SELLSUM_GROWTH_RATE) {
		BS_SELLSUM_GROWTH_RATE = bS_SELLSUM_GROWTH_RATE;
	}

	public String getJIALEI() {
		return JIALEI;
	}
	public void setJIALEI(String jIALEI) {
		JIALEI = jIALEI;
	}
	public String getTRANSFERPRICE() {
		return TRANSFERPRICE;
	}
	public void setTRANSFERPRICE(String tRANSFERPRICE) {
		TRANSFERPRICE = tRANSFERPRICE;
	}
	public String getWHOLESALEPRICE() {
		return WHOLESALEPRICE;
	}
	public void setWHOLESALEPRICE(String wHOLESALEPRICE) {
		WHOLESALEPRICE = wHOLESALEPRICE;
	}
	public String getSUGGESTEDRETAILPRICE() {
		return SUGGESTEDRETAILPRICE;
	}
	public void setSUGGESTEDRETAILPRICE(String sUGGESTEDRETAILPRICE) {
		SUGGESTEDRETAILPRICE = sUGGESTEDRETAILPRICE;
	}
	
	public ProductInTableVO() {
		super();
	}
	
	
	

}