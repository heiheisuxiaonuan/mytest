package com.icss.tjfx.cigpop.newcig.vo;
/**
 * 卷烟品牌展示页table属性VO
 * @author lcx
 * 2017-06-26
 */
public class CigPopNewTableVO {
	

	private String SORT_NO;					
	private String xz_parameter;						//下钻参数
	private String CIG_NAME;							//品牌名称
	private String PROV_NAME;							//省份
	//产量
	private String PRODUCENUM;							
	private String PRODUCENUM_L;
	private String PRODUCENUM_GROWTH;
	private String PRODUCENUM_GROWTH_RATE;
	//销量
	private String BS_SELLNUM;
	private String BS_SELLNUM_L;
	private String BS_SELLNUM_GROWTH;
	private String BS_SELLNUM_GROWTH_RATE;
	//工商库存
	private String IB_TERMSTOCKNUM;
	private String IB_TERMSTOCKNUM_L;
	private String IB_TERMSTOCKNUM_GROWTH;
	private String IB_TERMSTOCKNUM_GROWTH_RATE;
	//商业销售额
	private String BS_SELLSUM;
	private String BS_SELLSUM_L;
	private String BS_SELLSUM_GROWTH;
	private String BS_SELLSUM_GROWTH_RATE;
	//单箱销售额
	private String SINGLE_SELLSUM;
	private String SINGLE_SELLSUM_L;
	private String SINGLE_SELLSUM_GROWTH;
	private String SINGLE_SELLSUM_GROWTH_RATE;
	//卷烟税利
	private String IB_TAX_PROFIT;
	private String IB_TAX_PROFIT_L;
	private String IB_TAX_PROFIT_GROWTH;
	private String IB_TAX_PROFIT_GROWTH_RATE;
								
	//省外依存度
	private String EXTERNAL_MARKET_RATE;
	private String EXTERNAL_MARKET_L_RATE;
	private String EXTERNAL_MARKET_RATE_GROWTHFROM;
	
	//地市覆盖率
	private String CITY_COVER_RATE;
	private String CITY_COVER_RATE_L;
	private String CITY_COVER_RATE_GROWTHA;
	
	
	private String PRODUCER_NAME;				//所有者
	private String CLASS_NAME;				//价类
	private String TRANSFERPRICE;				//调拨价
	private String WHOLESALEPRICE;				//批发价
	private String SUGGESTEDRETAILPRICE;		//零售价
	
	private String IS_THIN;
	private String IS_BEAD;
	private String IS_SHORT;
	
	
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
	public String getCITY_COVER_RATE() {
		return CITY_COVER_RATE;
	}
	public void setCITY_COVER_RATE(String cITY_COVER_RATE) {
		CITY_COVER_RATE = cITY_COVER_RATE;
	}
	public String getCITY_COVER_RATE_L() {
		return CITY_COVER_RATE_L;
	}
	public void setCITY_COVER_RATE_L(String cITY_COVER_RATE_L) {
		CITY_COVER_RATE_L = cITY_COVER_RATE_L;
	}
	public String getCITY_COVER_RATE_GROWTHA() {
		return CITY_COVER_RATE_GROWTHA;
	}
	public void setCITY_COVER_RATE_GROWTHA(String cITY_COVER_RATE_GROWTHA) {
		CITY_COVER_RATE_GROWTHA = cITY_COVER_RATE_GROWTHA;
	}
	public String getPRODUCER_NAME() {
		return PRODUCER_NAME;
	}
	public void setPRODUCER_NAME(String pRODUCERNAME) {
		PRODUCER_NAME = pRODUCERNAME;
	}
	public String getCLASS_NAME() {
		return CLASS_NAME;
	}
	public void setCLASS_NAME(String cLASSNAME) {
		CLASS_NAME = cLASSNAME;
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
	public String getIB_TERMSTOCKNUM_GROWTH_RATE() {
		return IB_TERMSTOCKNUM_GROWTH_RATE;
	}
	public void setIB_TERMSTOCKNUM_GROWTH_RATE(String iBTERMSTOCKNUMGROWTHRATE) {
		IB_TERMSTOCKNUM_GROWTH_RATE = iBTERMSTOCKNUMGROWTHRATE;
	}
	public String getEXTERNAL_MARKET_RATE() {
		return EXTERNAL_MARKET_RATE;
	}
	public void setEXTERNAL_MARKET_RATE(String eXTERNALMARKETRATE) {
		EXTERNAL_MARKET_RATE = eXTERNALMARKETRATE;
	}
	public String getEXTERNAL_MARKET_L_RATE() {
		return EXTERNAL_MARKET_L_RATE;
	}
	public void setEXTERNAL_MARKET_L_RATE(String eXTERNALMARKETLRATE) {
		EXTERNAL_MARKET_L_RATE = eXTERNALMARKETLRATE;
	}
	public String getEXTERNAL_MARKET_RATE_GROWTHFROM() {
		return EXTERNAL_MARKET_RATE_GROWTHFROM;
	}
	public void setEXTERNAL_MARKET_RATE_GROWTHFROM(
			String eXTERNALMARKETRATEGROWTHFROM) {
		EXTERNAL_MARKET_RATE_GROWTHFROM = eXTERNALMARKETRATEGROWTHFROM;
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
	public String getPRODUCENUM_GROWTH() {
		return PRODUCENUM_GROWTH;
	}
	public void setPRODUCENUM_GROWTH(String pRODUCENUM_GROWTH) {
		PRODUCENUM_GROWTH = pRODUCENUM_GROWTH;
	}
	public String getPRODUCENUM_GROWTH_RATE() {
		return PRODUCENUM_GROWTH_RATE;
	}
	public void setPRODUCENUM_GROWTH_RATE(String pRODUCENUM_GROWTH_RATE) {
		PRODUCENUM_GROWTH_RATE = pRODUCENUM_GROWTH_RATE;
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
	public String getBS_SELLNUM_GROWTH() {
		return BS_SELLNUM_GROWTH;
	}
	public void setBS_SELLNUM_GROWTH(String bS_SELLNUM_GROWTH) {
		BS_SELLNUM_GROWTH = bS_SELLNUM_GROWTH;
	}
	public String getBS_SELLNUM_GROWTH_RATE() {
		return BS_SELLNUM_GROWTH_RATE;
	}
	public void setBS_SELLNUM_GROWTH_RATE(String bS_SELLNUM_GROWTH_RATE) {
		BS_SELLNUM_GROWTH_RATE = bS_SELLNUM_GROWTH_RATE;
	}
	public String getIB_TERMSTOCKNUM() {
		return IB_TERMSTOCKNUM;
	}
	public void setIB_TERMSTOCKNUM(String iB_TERMSTOCKNUM) {
		IB_TERMSTOCKNUM = iB_TERMSTOCKNUM;
	}
	public String getIB_TERMSTOCKNUM_L() {
		return IB_TERMSTOCKNUM_L;
	}
	public void setIB_TERMSTOCKNUM_L(String iB_TERMSTOCKNUM_L) {
		IB_TERMSTOCKNUM_L = iB_TERMSTOCKNUM_L;
	}
	public String getIB_TERMSTOCKNUM_GROWTH() {
		return IB_TERMSTOCKNUM_GROWTH;
	}
	public void setIB_TERMSTOCKNUM_GROWTH(String iB_TERMSTOCKNUM_GROWTH) {
		IB_TERMSTOCKNUM_GROWTH = iB_TERMSTOCKNUM_GROWTH;
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
	public String getBS_SELLSUM_GROWTH() {
		return BS_SELLSUM_GROWTH;
	}
	public void setBS_SELLSUM_GROWTH(String bS_SELLSUM_GROWTH) {
		BS_SELLSUM_GROWTH = bS_SELLSUM_GROWTH;
	}
	public String getBS_SELLSUM_GROWTH_RATE() {
		return BS_SELLSUM_GROWTH_RATE;
	}
	public void setBS_SELLSUM_GROWTH_RATE(String bS_SELLSUM_GROWTH_RATE) {
		BS_SELLSUM_GROWTH_RATE = bS_SELLSUM_GROWTH_RATE;
	}
	public String getSINGLE_SELLSUM() {
		return SINGLE_SELLSUM;
	}
	public void setSINGLE_SELLSUM(String sINGLE_SELLSUM) {
		SINGLE_SELLSUM = sINGLE_SELLSUM;
	}
	public String getSINGLE_SELLSUM_L() {
		return SINGLE_SELLSUM_L;
	}
	public void setSINGLE_SELLSUM_L(String sINGLE_SELLSUM_L) {
		SINGLE_SELLSUM_L = sINGLE_SELLSUM_L;
	}
	public String getSINGLE_SELLSUM_GROWTH() {
		return SINGLE_SELLSUM_GROWTH;
	}
	public void setSINGLE_SELLSUM_GROWTH(String sINGLE_SELLSUM_GROWTH) {
		SINGLE_SELLSUM_GROWTH = sINGLE_SELLSUM_GROWTH;
	}
	public String getSINGLE_SELLSUM_GROWTH_RATE() {
		return SINGLE_SELLSUM_GROWTH_RATE;
	}
	public void setSINGLE_SELLSUM_GROWTH_RATE(String sINGLE_SELLSUM_GROWTH_RATE) {
		SINGLE_SELLSUM_GROWTH_RATE = sINGLE_SELLSUM_GROWTH_RATE;
	}
	
	public String getIB_TAX_PROFIT() {
		return IB_TAX_PROFIT;
	}
	public void setIB_TAX_PROFIT(String iB_TAX_PROFIT) {
		IB_TAX_PROFIT = iB_TAX_PROFIT;
	}
	public String getIB_TAX_PROFIT_L() {
		return IB_TAX_PROFIT_L;
	}
	public void setIB_TAX_PROFIT_L(String iB_TAX_PROFIT_L) {
		IB_TAX_PROFIT_L = iB_TAX_PROFIT_L;
	}
	public String getIB_TAX_PROFIT_GROWTH() {
		return IB_TAX_PROFIT_GROWTH;
	}
	public void setIB_TAX_PROFIT_GROWTH(String iB_TAX_PROFIT_GROWTH) {
		IB_TAX_PROFIT_GROWTH = iB_TAX_PROFIT_GROWTH;
	}
	public String getIB_TAX_PROFIT_GROWTH_RATE() {
		return IB_TAX_PROFIT_GROWTH_RATE;
	}
	public void setIB_TAX_PROFIT_GROWTH_RATE(String iB_TAX_PROFIT_GROWTH_RATE) {
		IB_TAX_PROFIT_GROWTH_RATE = iB_TAX_PROFIT_GROWTH_RATE;
	}
	
	public String getPROV_NAME() {
		return PROV_NAME;
	}
	public void setPROV_NAME(String pROV_NAME) {
		PROV_NAME = pROV_NAME;
	}
	public CigPopNewTableVO() {
		super();
	}
}