package com.icss.tjfx.leadtarget.transaction.vo;

/**
 * 先行指标chartsVO类
 * 
 * @author lcx
 * 
 */
public class TransactionChartsDataVO {
	private String XYLBQ;
	private String HTL_RESOLVE_SCHEDULE_FJ;
	private String HTL_RESOLVE_SCHEDULE_ZX;
	public String getXYLBQ() {
		return XYLBQ;
	}
	public void setXYLBQ(String xYLBQ) {
		XYLBQ = xYLBQ;
	}
	public String getHTL_RESOLVE_SCHEDULE_FJ() {
		return HTL_RESOLVE_SCHEDULE_FJ;
	}
	public void setHTL_RESOLVE_SCHEDULE_FJ(String hTL_RESOLVE_SCHEDULE_FJ) {
		HTL_RESOLVE_SCHEDULE_FJ = hTL_RESOLVE_SCHEDULE_FJ;
	}
	public String getHTL_RESOLVE_SCHEDULE_ZX() {
		return HTL_RESOLVE_SCHEDULE_ZX;
	}
	public void setHTL_RESOLVE_SCHEDULE_ZX(String hTL_RESOLVE_SCHEDULE_ZX) {
		HTL_RESOLVE_SCHEDULE_ZX = hTL_RESOLVE_SCHEDULE_ZX;
	}
	@Override
	public String toString() {
		return "TransactionChartsDataVO [XYLBQ=" + XYLBQ + ", HTL_RESOLVE_SCHEDULE_FJ=" + HTL_RESOLVE_SCHEDULE_FJ
				+ ", HTL_RESOLVE_SCHEDULE_ZX=" + HTL_RESOLVE_SCHEDULE_ZX + "]";
	}
	public TransactionChartsDataVO() {
		super();
	}
	
	

}
