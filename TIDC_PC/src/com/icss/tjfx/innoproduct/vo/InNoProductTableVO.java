package com.icss.tjfx.innoproduct.vo;

/**
 * 创新产品页table属性VO
 * 
 * @author lcx 2017-06-26
 */
public class InNoProductTableVO {
	private String xz_parameter;						//下钻参数
	private String CIG_NAME;							//品牌名称
	

	

	// 产量
	private String PRODUCENUM;// 本期
	private String PRODUCENUM_L;// 同期
	private String PRODUCENUM_GROWTH;// +-量
	private String PRODUCENUM_GROWTH_RATE;// +-%

	// 销量
	private String BS_SELLNUM;
	private String BS_SELLNUM_L;
	private String BS_SELLNUM_GROWTH;
	private String BS_SELLNUM_GROWTH_RATE;

	// 工商库存
	private String IB_TERMSTOCKNUM;
	private String IB_TERMSTOCKNUM_L;
	private String IB_TERMSTOCKNUM_GROWTH;
	private String IB_TERMSTOCKNUM_GROWTH_RATE;

	// 商业销售额
	private String BS_SELLSUM;
	private String BS_SELLSUM_L;
	private String BS_SELLSUM_GROWTH;
	private String BS_SELLSUM_GROWTH_RATE;

	// 单箱销售额
	private String SINGLE_SELLSUM;
	private String SINGLE_SELLSUM_L;
	private String SINGLE_SELLSUM_GROWTH;
	private String SINGLE_SELLSUM_GROWTH_RATE;

	// 卷烟税利
	private String IB_TAX_PROFIT;
	private String IB_TAX_PROFIT_L;
	private String IB_TAX_PROFIT_GROWTH;
	private String IB_TAX_PROFIT_GROWTH_RATE;
	
	private String PRODUCER_NAME;				//所有者
	private String CLASS_NAME;				//价类
	private String TRANSFERPRICE;				//调拨价
	private String WHOLESALEPRICE;				//批发价
	private String SUGGESTEDRETAILPRICE;		//零售价
	public String getXz_parameter() {
		return xz_parameter;
	}

	public void setXz_parameter(String xzParameter) {
		xz_parameter = xzParameter;
	}

	public String getCIG_NAME() {
		return CIG_NAME;
	}

	public void setCIG_NAME(String cIGNAME) {
		CIG_NAME = cIGNAME;
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

	public void setPRODUCENUM_L(String pRODUCENUML) {
		PRODUCENUM_L = pRODUCENUML;
	}

	public String getPRODUCENUM_GROWTH() {
		return PRODUCENUM_GROWTH;
	}

	public void setPRODUCENUM_GROWTH(String pRODUCENUMGROWTH) {
		PRODUCENUM_GROWTH = pRODUCENUMGROWTH;
	}

	public String getPRODUCENUM_GROWTH_RATE() {
		return PRODUCENUM_GROWTH_RATE;
	}

	public void setPRODUCENUM_GROWTH_RATE(String pRODUCENUMGROWTHRATE) {
		PRODUCENUM_GROWTH_RATE = pRODUCENUMGROWTHRATE;
	}

	public String getBS_SELLNUM() {
		return BS_SELLNUM;
	}

	public void setBS_SELLNUM(String bSSELLNUM) {
		BS_SELLNUM = bSSELLNUM;
	}

	public String getBS_SELLNUM_L() {
		return BS_SELLNUM_L;
	}

	public void setBS_SELLNUM_L(String bSSELLNUML) {
		BS_SELLNUM_L = bSSELLNUML;
	}

	public String getBS_SELLNUM_GROWTH() {
		return BS_SELLNUM_GROWTH;
	}

	public void setBS_SELLNUM_GROWTH(String bSSELLNUMGROWTH) {
		BS_SELLNUM_GROWTH = bSSELLNUMGROWTH;
	}

	public String getBS_SELLNUM_GROWTH_RATE() {
		return BS_SELLNUM_GROWTH_RATE;
	}

	public void setBS_SELLNUM_GROWTH_RATE(String bSSELLNUMGROWTHRATE) {
		BS_SELLNUM_GROWTH_RATE = bSSELLNUMGROWTHRATE;
	}

	public String getIB_TERMSTOCKNUM() {
		return IB_TERMSTOCKNUM;
	}

	public void setIB_TERMSTOCKNUM(String iBTERMSTOCKNUM) {
		IB_TERMSTOCKNUM = iBTERMSTOCKNUM;
	}

	public String getIB_TERMSTOCKNUM_L() {
		return IB_TERMSTOCKNUM_L;
	}

	public void setIB_TERMSTOCKNUM_L(String iBTERMSTOCKNUML) {
		IB_TERMSTOCKNUM_L = iBTERMSTOCKNUML;
	}

	public String getIB_TERMSTOCKNUM_GROWTH() {
		return IB_TERMSTOCKNUM_GROWTH;
	}

	public void setIB_TERMSTOCKNUM_GROWTH(String iBTERMSTOCKNUMGROWTH) {
		IB_TERMSTOCKNUM_GROWTH = iBTERMSTOCKNUMGROWTH;
	}

	public String getIB_TERMSTOCKNUM_GROWTH_RATE() {
		return IB_TERMSTOCKNUM_GROWTH_RATE;
	}

	public void setIB_TERMSTOCKNUM_GROWTH_RATE(String iBTERMSTOCKNUMGROWTHRATE) {
		IB_TERMSTOCKNUM_GROWTH_RATE = iBTERMSTOCKNUMGROWTHRATE;
	}

	public String getBS_SELLSUM() {
		return BS_SELLSUM;
	}

	public void setBS_SELLSUM(String bSSELLSUM) {
		BS_SELLSUM = bSSELLSUM;
	}

	public String getBS_SELLSUM_L() {
		return BS_SELLSUM_L;
	}

	public void setBS_SELLSUM_L(String bSSELLSUML) {
		BS_SELLSUM_L = bSSELLSUML;
	}

	public String getBS_SELLSUM_GROWTH() {
		return BS_SELLSUM_GROWTH;
	}

	public void setBS_SELLSUM_GROWTH(String bSSELLSUMGROWTH) {
		BS_SELLSUM_GROWTH = bSSELLSUMGROWTH;
	}

	public String getBS_SELLSUM_GROWTH_RATE() {
		return BS_SELLSUM_GROWTH_RATE;
	}

	public void setBS_SELLSUM_GROWTH_RATE(String bSSELLSUMGROWTHRATE) {
		BS_SELLSUM_GROWTH_RATE = bSSELLSUMGROWTHRATE;
	}

	public String getSINGLE_SELLSUM() {
		return SINGLE_SELLSUM;
	}

	public void setSINGLE_SELLSUM(String sINGLESELLSUM) {
		SINGLE_SELLSUM = sINGLESELLSUM;
	}

	public String getSINGLE_SELLSUM_L() {
		return SINGLE_SELLSUM_L;
	}

	public void setSINGLE_SELLSUM_L(String sINGLESELLSUML) {
		SINGLE_SELLSUM_L = sINGLESELLSUML;
	}

	public String getSINGLE_SELLSUM_GROWTH() {
		return SINGLE_SELLSUM_GROWTH;
	}

	public void setSINGLE_SELLSUM_GROWTH(String sINGLESELLSUMGROWTH) {
		SINGLE_SELLSUM_GROWTH = sINGLESELLSUMGROWTH;
	}

	public String getSINGLE_SELLSUM_GROWTH_RATE() {
		return SINGLE_SELLSUM_GROWTH_RATE;
	}

	public void setSINGLE_SELLSUM_GROWTH_RATE(String sINGLESELLSUMGROWTHRATE) {
		SINGLE_SELLSUM_GROWTH_RATE = sINGLESELLSUMGROWTHRATE;
	}

	public String getIB_TAX_PROFIT() {
		return IB_TAX_PROFIT;
	}

	public void setIB_TAX_PROFIT(String iBTAXPROFIT) {
		IB_TAX_PROFIT = iBTAXPROFIT;
	}

	public String getIB_TAX_PROFIT_L() {
		return IB_TAX_PROFIT_L;
	}

	public void setIB_TAX_PROFIT_L(String iBTAXPROFITL) {
		IB_TAX_PROFIT_L = iBTAXPROFITL;
	}

	public String getIB_TAX_PROFIT_GROWTH() {
		return IB_TAX_PROFIT_GROWTH;
	}

	public void setIB_TAX_PROFIT_GROWTH(String iBTAXPROFITGROWTH) {
		IB_TAX_PROFIT_GROWTH = iBTAXPROFITGROWTH;
	}

	public String getIB_TAX_PROFIT_GROWTH_RATE() {
		return IB_TAX_PROFIT_GROWTH_RATE;
	}

	public void setIB_TAX_PROFIT_GROWTH_RATE(String iBTAXPROFITGROWTHRATE) {
		IB_TAX_PROFIT_GROWTH_RATE = iBTAXPROFITGROWTHRATE;
	}


	public void setTRANSFERPRICE(String tRANSFERPRICE) {
		TRANSFERPRICE = tRANSFERPRICE;
	}

	public String getTRANSFERPRICE() {
		return TRANSFERPRICE;
	}

	public void setWHOLESALEPRICE(String wHOLESALEPRICE) {
		WHOLESALEPRICE = wHOLESALEPRICE;
	}

	public String getWHOLESALEPRICE() {
		return WHOLESALEPRICE;
	}

	public void setSUGGESTEDRETAILPRICE(String sUGGESTEDRETAILPRICE) {
		SUGGESTEDRETAILPRICE = sUGGESTEDRETAILPRICE;
	}

	public String getSUGGESTEDRETAILPRICE() {
		return SUGGESTEDRETAILPRICE;
	}

	public void setCLASS_NAME(String cLASS_NAME) {
		CLASS_NAME = cLASS_NAME;
	}

	public String getCLASS_NAME() {
		return CLASS_NAME;
	}

	public void setPRODUCER_NAME(String pRODUCER_NAME) {
		PRODUCER_NAME = pRODUCER_NAME;
	}

	public String getPRODUCER_NAME() {
		return PRODUCER_NAME;
	}
	

}