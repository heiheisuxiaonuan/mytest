package com.icss.tjfx.total.in.vo;

/**
 * 总量情况分中烟VO
 * @author lcx
 *
 */
public class IndustryVO {

	private String factoryName;//工业名称
	private String C_CLASS;//工业编码
	private String className;//价类名称
	private String factName;//企业名称
	
	//产量
	private String PRODUCENUM;//本期
	private String PRODUCENUM_L;//同期
	private String PRODUCENUM_GROWTH;//+-量
	private String PRODUCENUM_GROWTH_RATE;//+-%
	
	//销量
	private String IN_SELLNUM;
	private String IN_SELLNUM_L;
	private String IN_SELLNUM_GROWTH;
	private String IN_SELLNUM_GROWTH_RATE;
	
	//工业库存
	private String IN_TERMSTOCK;
	private String IN_TERMSTOCK_L;
	private String IN_TERMSTOCK_GROWTH;
	private String IN_TERMSTOCK_GROWTH_RATE;
	
	
	//单箱销售额
	private String SINGLE_SELLSUM;
	private String SINGLE_SELLSUM_L;
	private String SINGLE_SELLSUM_GROWTH;
	private String SINGLE_SELLSUM_GROWTH_RATE;
	
	//工业销售额
	private String IN_SELLSUM;
	private String IN_SELLSUM_L;
	private String IN_SELLSUM_GROWTH;
	private String IN_SELLSUM_GROWTH_RATE;
	
	//工业税利
	private String IN_TAX_PROFIT;
	private String IN_TAX_PROFIT_L;
	private String IN_TAX_PROFIT_GROWTH;
	private String IN_TAX_PROFIT_GROWTH_RATE;
	
	//省外依存度
	private String EXTERNAL_MARKET_RATE;
	private String EXTERNAL_MARKET_L_RATE;
	private String EXTERNAL_MARKET_RATE_GROWTHFROM;
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getC_CLASS() {
		return C_CLASS;
	}
	public void setC_CLASS(String c_CLASS) {
		C_CLASS = c_CLASS;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getFactName() {
		return factName;
	}
	public void setFactName(String factName) {
		this.factName = factName;
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
	public String getIN_SELLNUM() {
		return IN_SELLNUM;
	}
	public void setIN_SELLNUM(String iN_SELLNUM) {
		IN_SELLNUM = iN_SELLNUM;
	}
	public String getIN_SELLNUM_L() {
		return IN_SELLNUM_L;
	}
	public void setIN_SELLNUM_L(String iN_SELLNUM_L) {
		IN_SELLNUM_L = iN_SELLNUM_L;
	}
	public String getIN_SELLNUM_GROWTH() {
		return IN_SELLNUM_GROWTH;
	}
	public void setIN_SELLNUM_GROWTH(String iN_SELLNUM_GROWTH) {
		IN_SELLNUM_GROWTH = iN_SELLNUM_GROWTH;
	}
	public String getIN_SELLNUM_GROWTH_RATE() {
		return IN_SELLNUM_GROWTH_RATE;
	}
	public void setIN_SELLNUM_GROWTH_RATE(String iN_SELLNUM_GROWTH_RATE) {
		IN_SELLNUM_GROWTH_RATE = iN_SELLNUM_GROWTH_RATE;
	}
	public String getIN_TERMSTOCK() {
		return IN_TERMSTOCK;
	}
	public void setIN_TERMSTOCK(String iN_TERMSTOCK) {
		IN_TERMSTOCK = iN_TERMSTOCK;
	}
	public String getIN_TERMSTOCK_L() {
		return IN_TERMSTOCK_L;
	}
	public void setIN_TERMSTOCK_L(String iN_TERMSTOCK_L) {
		IN_TERMSTOCK_L = iN_TERMSTOCK_L;
	}
	public String getIN_TERMSTOCK_GROWTH() {
		return IN_TERMSTOCK_GROWTH;
	}
	public void setIN_TERMSTOCK_GROWTH(String iN_TERMSTOCK_GROWTH) {
		IN_TERMSTOCK_GROWTH = iN_TERMSTOCK_GROWTH;
	}
	public String getIN_TERMSTOCK_GROWTH_RATE() {
		return IN_TERMSTOCK_GROWTH_RATE;
	}
	public void setIN_TERMSTOCK_GROWTH_RATE(String iN_TERMSTOCK_GROWTH_RATE) {
		IN_TERMSTOCK_GROWTH_RATE = iN_TERMSTOCK_GROWTH_RATE;
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
	public String getIN_SELLSUM() {
		return IN_SELLSUM;
	}
	public void setIN_SELLSUM(String iN_SELLSUM) {
		IN_SELLSUM = iN_SELLSUM;
	}
	public String getIN_SELLSUM_L() {
		return IN_SELLSUM_L;
	}
	public void setIN_SELLSUM_L(String iN_SELLSUM_L) {
		IN_SELLSUM_L = iN_SELLSUM_L;
	}
	public String getIN_SELLSUM_GROWTH() {
		return IN_SELLSUM_GROWTH;
	}
	public void setIN_SELLSUM_GROWTH(String iN_SELLSUM_GROWTH) {
		IN_SELLSUM_GROWTH = iN_SELLSUM_GROWTH;
	}
	public String getIN_SELLSUM_GROWTH_RATE() {
		return IN_SELLSUM_GROWTH_RATE;
	}
	public void setIN_SELLSUM_GROWTH_RATE(String iN_SELLSUM_GROWTH_RATE) {
		IN_SELLSUM_GROWTH_RATE = iN_SELLSUM_GROWTH_RATE;
	}
	public String getIN_TAX_PROFIT() {
		return IN_TAX_PROFIT;
	}
	public void setIN_TAX_PROFIT(String iN_TAX_PROFIT) {
		IN_TAX_PROFIT = iN_TAX_PROFIT;
	}
	public String getIN_TAX_PROFIT_L() {
		return IN_TAX_PROFIT_L;
	}
	public void setIN_TAX_PROFIT_L(String iN_TAX_PROFIT_L) {
		IN_TAX_PROFIT_L = iN_TAX_PROFIT_L;
	}
	public String getIN_TAX_PROFIT_GROWTH() {
		return IN_TAX_PROFIT_GROWTH;
	}
	public void setIN_TAX_PROFIT_GROWTH(String iN_TAX_PROFIT_GROWTH) {
		IN_TAX_PROFIT_GROWTH = iN_TAX_PROFIT_GROWTH;
	}
	public String getIN_TAX_PROFIT_GROWTH_RATE() {
		return IN_TAX_PROFIT_GROWTH_RATE;
	}
	public void setIN_TAX_PROFIT_GROWTH_RATE(String iN_TAX_PROFIT_GROWTH_RATE) {
		IN_TAX_PROFIT_GROWTH_RATE = iN_TAX_PROFIT_GROWTH_RATE;
	}
	public String getEXTERNAL_MARKET_RATE() {
		return EXTERNAL_MARKET_RATE;
	}
	public void setEXTERNAL_MARKET_RATE(String eXTERNAL_MARKET_RATE) {
		EXTERNAL_MARKET_RATE = eXTERNAL_MARKET_RATE;
	}
	public String getEXTERNAL_MARKET_L_RATE() {
		return EXTERNAL_MARKET_L_RATE;
	}
	public void setEXTERNAL_MARKET_L_RATE(String eXTERNAL_MARKET_L_RATE) {
		EXTERNAL_MARKET_L_RATE = eXTERNAL_MARKET_L_RATE;
	}
	public String getEXTERNAL_MARKET_RATE_GROWTHFROM() {
		return EXTERNAL_MARKET_RATE_GROWTHFROM;
	}
	public void setEXTERNAL_MARKET_RATE_GROWTHFROM(
			String eXTERNAL_MARKET_RATE_GROWTHFROM) {
		EXTERNAL_MARKET_RATE_GROWTHFROM = eXTERNAL_MARKET_RATE_GROWTHFROM;
	}
	
	
	
	
	
}
