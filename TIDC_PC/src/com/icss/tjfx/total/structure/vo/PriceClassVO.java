package com.icss.tjfx.total.structure.vo;

/**
 * 总量情况结构VO
 * @author lcx
 *
 */
public class PriceClassVO {
	
	private String C_CLASS;//价类编码
	private String CLASS_NAME;
	private String CIG_NAME;//规格名称
	
	//产量
	private String produceNum_bq;//本期
	private String produceNum_tq;//同期
	private String produceNum_bl;//+-量
	private String produceNum_pt;//+-%
	
	//销量
	private String sellNum_bq;
	private String sellNum_tq;
	private String sellNum_bl;
	private String sellNum_pt;
	
	//工商库存
	private String stock_bq;
	private String stock_tq;
	private String stock_bl;
	private String stock_pt;
	
	//在途量
	private String onRoad_bq;
	private String onRoad_tq;
	private String onRoad_bl;
	private String onRoad_pt;
	
	//单箱销售额
	private String singleSellAmount_bq;
	private String singleSellAmount_tq;
	private String singleSellAmount_bl;
	private String singleSellAmount_pt;
	
	//商业销售额
	private String bsSellAmount_bq;
	private String bsSellAmount_tq;
	private String bsSellAmount_bl;
	private String bsSellAmount_pt;
	
	
	//卷烟税利
	private String ciga_tax_bq;
	private String ciga_tax_tq;
	private String ciga_tax_bl;
	private String ciga_tax_pt;
	
	public String getC_CLASS() {
		return C_CLASS;
	}
	public void setC_CLASS(String c_CLASS) {
		C_CLASS = c_CLASS;
	}
	public String getCLASS_NAME() {
		return CLASS_NAME;
	}
	public void setCLASS_NAME(String cLASS_NAME) {
		CLASS_NAME = cLASS_NAME;
	}
	public String getProduceNum_bq() {
		return produceNum_bq;
	}
	public void setProduceNum_bq(String produceNum_bq) {
		this.produceNum_bq = produceNum_bq;
	}
	public String getProduceNum_tq() {
		return produceNum_tq;
	}
	public void setProduceNum_tq(String produceNum_tq) {
		this.produceNum_tq = produceNum_tq;
	}
	public String getProduceNum_bl() {
		return produceNum_bl;
	}
	public void setProduceNum_bl(String produceNum_bl) {
		this.produceNum_bl = produceNum_bl;
	}
	public String getProduceNum_pt() {
		return produceNum_pt;
	}
	public void setProduceNum_pt(String produceNum_pt) {
		this.produceNum_pt = produceNum_pt;
	}
	public String getSellNum_bq() {
		return sellNum_bq;
	}
	public void setSellNum_bq(String sellNum_bq) {
		this.sellNum_bq = sellNum_bq;
	}
	public String getSellNum_tq() {
		return sellNum_tq;
	}
	public void setSellNum_tq(String sellNum_tq) {
		this.sellNum_tq = sellNum_tq;
	}
	public String getSellNum_bl() {
		return sellNum_bl;
	}
	public void setSellNum_bl(String sellNum_bl) {
		this.sellNum_bl = sellNum_bl;
	}
	public String getSellNum_pt() {
		return sellNum_pt;
	}
	public void setSellNum_pt(String sellNum_pt) {
		this.sellNum_pt = sellNum_pt;
	}
	public String getStock_bq() {
		return stock_bq;
	}
	public void setStock_bq(String stock_bq) {
		this.stock_bq = stock_bq;
	}
	public String getStock_tq() {
		return stock_tq;
	}
	public void setStock_tq(String stock_tq) {
		this.stock_tq = stock_tq;
	}
	public String getStock_bl() {
		return stock_bl;
	}
	public void setStock_bl(String stock_bl) {
		this.stock_bl = stock_bl;
	}
	public String getStock_pt() {
		return stock_pt;
	}
	public void setStock_pt(String stock_pt) {
		this.stock_pt = stock_pt;
	}
	public String getOnRoad_bq() {
		return onRoad_bq;
	}
	public void setOnRoad_bq(String onRoad_bq) {
		this.onRoad_bq = onRoad_bq;
	}
	public String getOnRoad_tq() {
		return onRoad_tq;
	}
	public void setOnRoad_tq(String onRoad_tq) {
		this.onRoad_tq = onRoad_tq;
	}
	public String getOnRoad_bl() {
		return onRoad_bl;
	}
	public void setOnRoad_bl(String onRoad_bl) {
		this.onRoad_bl = onRoad_bl;
	}
	public String getOnRoad_pt() {
		return onRoad_pt;
	}
	public void setOnRoad_pt(String onRoad_pt) {
		this.onRoad_pt = onRoad_pt;
	}
	public String getSingleSellAmount_bq() {
		return singleSellAmount_bq;
	}
	public void setSingleSellAmount_bq(String singleSellAmount_bq) {
		this.singleSellAmount_bq = singleSellAmount_bq;
	}
	public String getSingleSellAmount_tq() {
		return singleSellAmount_tq;
	}
	public void setSingleSellAmount_tq(String singleSellAmount_tq) {
		this.singleSellAmount_tq = singleSellAmount_tq;
	}
	public String getSingleSellAmount_bl() {
		return singleSellAmount_bl;
	}
	public void setSingleSellAmount_bl(String singleSellAmount_bl) {
		this.singleSellAmount_bl = singleSellAmount_bl;
	}
	public String getSingleSellAmount_pt() {
		return singleSellAmount_pt;
	}
	public void setSingleSellAmount_pt(String singleSellAmount_pt) {
		this.singleSellAmount_pt = singleSellAmount_pt;
	}
	public String getBsSellAmount_bq() {
		return bsSellAmount_bq;
	}
	public void setBsSellAmount_bq(String bsSellAmount_bq) {
		this.bsSellAmount_bq = bsSellAmount_bq;
	}
	public String getBsSellAmount_tq() {
		return bsSellAmount_tq;
	}
	public void setBsSellAmount_tq(String bsSellAmount_tq) {
		this.bsSellAmount_tq = bsSellAmount_tq;
	}
	public String getBsSellAmount_bl() {
		return bsSellAmount_bl;
	}
	public void setBsSellAmount_bl(String bsSellAmount_bl) {
		this.bsSellAmount_bl = bsSellAmount_bl;
	}
	public String getBsSellAmount_pt() {
		return bsSellAmount_pt;
	}
	public void setBsSellAmount_pt(String bsSellAmount_pt) {
		this.bsSellAmount_pt = bsSellAmount_pt;
	}
	public String getCiga_tax_bq() {
		return ciga_tax_bq;
	}
	public void setCiga_tax_bq(String ciga_tax_bq) {
		this.ciga_tax_bq = ciga_tax_bq;
	}
	public String getCiga_tax_tq() {
		return ciga_tax_tq;
	}
	public void setCiga_tax_tq(String ciga_tax_tq) {
		this.ciga_tax_tq = ciga_tax_tq;
	}
	public String getCiga_tax_bl() {
		return ciga_tax_bl;
	}
	public void setCiga_tax_bl(String ciga_tax_bl) {
		this.ciga_tax_bl = ciga_tax_bl;
	}
	public String getCiga_tax_pt() {
		return ciga_tax_pt;
	}
	public void setCiga_tax_pt(String ciga_tax_pt) {
		this.ciga_tax_pt = ciga_tax_pt;
	}
	public String getCIG_NAME() {
		return CIG_NAME;
	}
	public void setCIG_NAME(String cIG_NAME) {
		CIG_NAME = cIG_NAME;
	}

	
	
}
