package com.icss.tjfx.leadtarget.order.vo;

/**
 * 先行指标chartsVO类
 * 
 * @author lcx
 * 
 */
public class OrderChartsDataVO {

	private String CC_DEMAND_AMOUNT; // 订单需求量
	private String CC_DEMAND_AMOUNT_L; // 同期
	private String proportion; // 占比

	public String getCC_DEMAND_AMOUNT() {
		return CC_DEMAND_AMOUNT;
	}

	public void setCC_DEMAND_AMOUNT(String cC_DEMAND_AMOUNT) {
		CC_DEMAND_AMOUNT = cC_DEMAND_AMOUNT;
	}

	public String getCC_DEMAND_AMOUNT_L() {
		return CC_DEMAND_AMOUNT_L;
	}

	public void setCC_DEMAND_AMOUNT_L(String cC_DEMAND_AMOUNT_L) {
		CC_DEMAND_AMOUNT_L = cC_DEMAND_AMOUNT_L;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	@Override
	public String toString() {
		return "OrderChartsDataVO [CC_DEMAND_AMOUNT=" + CC_DEMAND_AMOUNT
				+ ", CC_DEMAND_AMOUNT_L=" + CC_DEMAND_AMOUNT_L
				+ ", proportion=" + proportion + "]";
	}

	public OrderChartsDataVO() {
		super();
	}

}
