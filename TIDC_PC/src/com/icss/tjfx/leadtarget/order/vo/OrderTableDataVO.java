package com.icss.tjfx.leadtarget.order.vo;

/**
 * 先行指标订单VO类
 * 
 * @author lcx
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class OrderTableDataVO {
		private String Mon;
		private String CIG_NAME;
		//订单需求量
		private String CC_DEMAND_AMOUNT;			
		private String CC_DEMAND_AMOUNT_L;
		private String CC_DEMAND_AMOUNT_GROWTH;
		private String CC_DEMAND_AMOUNT_GROWTH_RATE;
		//订单满足量
		private String CC_ORDER_AMOUNT;
		private String CC_ORDER_AMOUNT_L;
		private String CC_ORDER_AMOUNT_GROWTH;
		private String CC_ORDER_AMOUNT_GROWTH_RATE;
		//订单缺口量
		private String CC_GAP_AMOUNT;
		private String CC_GAP_AMOUNT_L;
		private String CC_GAP_AMOUNT_GROWTH;
		private String CC_GAP_AMOUNT_GROWTH_RATE;
		//订单满足率
		private String CC_satisfaction_AMOUNT;
		private String CC_satisfaction_AMOUNT_L;
		private String CC_satisfaction_AMOUNT_GROWTH_RATE;
		public String getMon() {
			return Mon;
		}
		public void setMon(String mon) {
			Mon = mon;
		}
		public String getCIG_NAME() {
			return CIG_NAME;
		}
		public void setCIG_NAME(String cIG_NAME) {
			CIG_NAME = cIG_NAME;
		}
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
		public String getCC_DEMAND_AMOUNT_GROWTH() {
			return CC_DEMAND_AMOUNT_GROWTH;
		}
		public void setCC_DEMAND_AMOUNT_GROWTH(String cC_DEMAND_AMOUNT_GROWTH) {
			CC_DEMAND_AMOUNT_GROWTH = cC_DEMAND_AMOUNT_GROWTH;
		}
		public String getCC_DEMAND_AMOUNT_GROWTH_RATE() {
			return CC_DEMAND_AMOUNT_GROWTH_RATE;
		}
		public void setCC_DEMAND_AMOUNT_GROWTH_RATE(String cC_DEMAND_AMOUNT_GROWTH_RATE) {
			CC_DEMAND_AMOUNT_GROWTH_RATE = cC_DEMAND_AMOUNT_GROWTH_RATE;
		}
		public String getCC_ORDER_AMOUNT() {
			return CC_ORDER_AMOUNT;
		}
		public void setCC_ORDER_AMOUNT(String cC_ORDER_AMOUNT) {
			CC_ORDER_AMOUNT = cC_ORDER_AMOUNT;
		}
		public String getCC_ORDER_AMOUNT_L() {
			return CC_ORDER_AMOUNT_L;
		}
		public void setCC_ORDER_AMOUNT_L(String cC_ORDER_AMOUNT_L) {
			CC_ORDER_AMOUNT_L = cC_ORDER_AMOUNT_L;
		}
		public String getCC_ORDER_AMOUNT_GROWTH() {
			return CC_ORDER_AMOUNT_GROWTH;
		}
		public void setCC_ORDER_AMOUNT_GROWTH(String cC_ORDER_AMOUNT_GROWTH) {
			CC_ORDER_AMOUNT_GROWTH = cC_ORDER_AMOUNT_GROWTH;
		}
		public String getCC_ORDER_AMOUNT_GROWTH_RATE() {
			return CC_ORDER_AMOUNT_GROWTH_RATE;
		}
		public void setCC_ORDER_AMOUNT_GROWTH_RATE(String cC_ORDER_AMOUNT_GROWTH_RATE) {
			CC_ORDER_AMOUNT_GROWTH_RATE = cC_ORDER_AMOUNT_GROWTH_RATE;
		}
		public String getCC_GAP_AMOUNT() {
			return CC_GAP_AMOUNT;
		}
		public void setCC_GAP_AMOUNT(String cC_GAP_AMOUNT) {
			CC_GAP_AMOUNT = cC_GAP_AMOUNT;
		}
		public String getCC_GAP_AMOUNT_L() {
			return CC_GAP_AMOUNT_L;
		}
		public void setCC_GAP_AMOUNT_L(String cC_GAP_AMOUNT_L) {
			CC_GAP_AMOUNT_L = cC_GAP_AMOUNT_L;
		}
		public String getCC_GAP_AMOUNT_GROWTH() {
			return CC_GAP_AMOUNT_GROWTH;
		}
		public void setCC_GAP_AMOUNT_GROWTH(String cC_GAP_AMOUNT_GROWTH) {
			CC_GAP_AMOUNT_GROWTH = cC_GAP_AMOUNT_GROWTH;
		}
		public String getCC_GAP_AMOUNT_GROWTH_RATE() {
			return CC_GAP_AMOUNT_GROWTH_RATE;
		}
		public void setCC_GAP_AMOUNT_GROWTH_RATE(String cC_GAP_AMOUNT_GROWTH_RATE) {
			CC_GAP_AMOUNT_GROWTH_RATE = cC_GAP_AMOUNT_GROWTH_RATE;
		}
		public String getCC_satisfaction_AMOUNT() {
			return CC_satisfaction_AMOUNT;
		}
		public void setCC_satisfaction_AMOUNT(String cC_satisfaction_AMOUNT) {
			CC_satisfaction_AMOUNT = cC_satisfaction_AMOUNT;
		}
		public String getCC_satisfaction_AMOUNT_L() {
			return CC_satisfaction_AMOUNT_L;
		}
		public void setCC_satisfaction_AMOUNT_L(String cC_satisfaction_AMOUNT_L) {
			CC_satisfaction_AMOUNT_L = cC_satisfaction_AMOUNT_L;
		}
		public String getCC_satisfaction_AMOUNT_GROWTH_RATE() {
			return CC_satisfaction_AMOUNT_GROWTH_RATE;
		}
		public void setCC_satisfaction_AMOUNT_GROWTH_RATE(
				String cC_satisfaction_AMOUNT_GROWTH_RATE) {
			CC_satisfaction_AMOUNT_GROWTH_RATE = cC_satisfaction_AMOUNT_GROWTH_RATE;
		}
		@Override
		public String toString() {
			return "OrderTableDataVO [Mon=" + Mon + ", CIG_NAME=" + CIG_NAME
					+ ", CC_DEMAND_AMOUNT=" + CC_DEMAND_AMOUNT
					+ ", CC_DEMAND_AMOUNT_L=" + CC_DEMAND_AMOUNT_L
					+ ", CC_DEMAND_AMOUNT_GROWTH=" + CC_DEMAND_AMOUNT_GROWTH
					+ ", CC_DEMAND_AMOUNT_GROWTH_RATE="
					+ CC_DEMAND_AMOUNT_GROWTH_RATE + ", CC_ORDER_AMOUNT="
					+ CC_ORDER_AMOUNT + ", CC_ORDER_AMOUNT_L="
					+ CC_ORDER_AMOUNT_L + ", CC_ORDER_AMOUNT_GROWTH="
					+ CC_ORDER_AMOUNT_GROWTH + ", CC_ORDER_AMOUNT_GROWTH_RATE="
					+ CC_ORDER_AMOUNT_GROWTH_RATE + ", CC_GAP_AMOUNT="
					+ CC_GAP_AMOUNT + ", CC_GAP_AMOUNT_L=" + CC_GAP_AMOUNT_L
					+ ", CC_GAP_AMOUNT_GROWTH=" + CC_GAP_AMOUNT_GROWTH
					+ ", CC_GAP_AMOUNT_GROWTH_RATE="
					+ CC_GAP_AMOUNT_GROWTH_RATE + ", CC_satisfaction_AMOUNT="
					+ CC_satisfaction_AMOUNT + ", CC_satisfaction_AMOUNT_L="
					+ CC_satisfaction_AMOUNT_L
					+ ", CC_satisfaction_AMOUNT_GROWTH_RATE="
					+ CC_satisfaction_AMOUNT_GROWTH_RATE + "]";
		}
		public OrderTableDataVO() {
			super();
		}
		
}
