package com.icss.tjfx.leadtarget.transaction.vo;

/**
 * 先行指标交易VO类
 * 
 * @author lcx
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class TransactionTableDataVO {
		private String M;
		private String MPLATE;
		private String HDL;
		private String XYL;
		private String HDL_SCHEDULE;
		private String HTL;
		private String HTL_RESOLVE_SCHEDULE;
		private String ZYZL;
		private String HTL_RESOLVE_progress;
		public String getM() {
			return M;
		}
		public void setM(String m) {
			M = m;
		}
		public String getMPLATE() {
			return MPLATE;
		}
		public void setMPLATE(String mPLATE) {
			MPLATE = mPLATE;
		}
		public String getHDL() {
			return HDL;
		}
		public void setHDL(String hDL) {
			HDL = hDL;
		}
		public String getXYL() {
			return XYL;
		}
		public void setXYL(String xYL) {
			XYL = xYL;
		}
		public String getHDL_SCHEDULE() {
			return HDL_SCHEDULE;
		}
		public void setHDL_SCHEDULE(String hDL_SCHEDULE) {
			HDL_SCHEDULE = hDL_SCHEDULE;
		}
		public String getHTL() {
			return HTL;
		}
		public void setHTL(String hTL) {
			HTL = hTL;
		}
		public String getHTL_RESOLVE_SCHEDULE() {
			return HTL_RESOLVE_SCHEDULE;
		}
		public void setHTL_RESOLVE_SCHEDULE(String hTL_RESOLVE_SCHEDULE) {
			HTL_RESOLVE_SCHEDULE = hTL_RESOLVE_SCHEDULE;
		}
		public String getZYZL() {
			return ZYZL;
		}
		public void setZYZL(String zYZL) {
			ZYZL = zYZL;
		}
		public String getHTL_RESOLVE_progress() {
			return HTL_RESOLVE_progress;
		}
		public void setHTL_RESOLVE_progress(String hTL_RESOLVE_progress) {
			HTL_RESOLVE_progress = hTL_RESOLVE_progress;
		}
		@Override
		public String toString() {
			return "TransactionTableDataVO [M=" + M + ", MPLATE=" + MPLATE
					+ ", HDL=" + HDL + ", XYL=" + XYL + ", HDL_SCHEDULE="
					+ HDL_SCHEDULE + ", HTL=" + HTL + ", HTL_RESOLVE_SCHEDULE="
					+ HTL_RESOLVE_SCHEDULE + ", ZYZL=" + ZYZL
					+ ", HTL_RESOLVE_progress=" + HTL_RESOLVE_progress + "]";
		}
		public TransactionTableDataVO() {
			super();
		}
		
		
		
}
