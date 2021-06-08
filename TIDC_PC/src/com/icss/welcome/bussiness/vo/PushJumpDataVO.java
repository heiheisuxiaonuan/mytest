package com.icss.welcome.bussiness.vo;

public class PushJumpDataVO {
	private String time;
	private String INS_PROD_QTY;	//工业生产
	private String INS_PROD_QTY_MONTH;	//工业产量-月
	private String INS_PROD_QTY_YEAR;	//工业产量-年
	private String INS_SAL_QTY;		//工业销量
	private String INS_SAL_QTY_MONTH;		//工业销量-月
	private String INS_SAL_QTY_YEAR;		//工业销量-年
	private String INS_SALSUM_QTY;		//工业销售额
	private String INS_SALSUM_QTY_MONTH;		//工业销售额-月
	private String INS_SALSUM_QTY_YEAR;		//工业销售额-年
	private String INS_STK_QTY;		//工业库存
	private String INS_DX;		//工业年单箱
	private String INS_JD;		//工业生产进度
	private String COM_BUY_QTY;		//商业购进
	private String COM_BUY_QTY_MONTH;	//商业购进量-月
	private String COM_BUY_QTY_YEAR;	//商业购进量-年
	private String COM_SAL_QTY;		//商业销售
	private String COM_SAL_QTY_MONTH;	//商业销量-月
	private String COM_SAL_QTY_YEAR;	//商业购销量-年
	private String COM_SALSUM_QTY;	//商业销售额
	private String COM_SALSUM_QTY_MONTH;	//商业销售额-月
	private String COM_SALSUM_QTY_YEAR;	//商业销售额-年
	private String COM_STK_QTY;		//商业库存
	private String COM_DX;		//商业年单箱
	private String COM_JD;		//商业生产进度
	private String TRANSIT_QTY;		//在途量
	private int renderSpeed;
	
	public PushJumpDataVO() {
		super();
	}
	
	public PushJumpDataVO(String iNS_PROD_QTY, String iNS_PROD_QTY_MONTH, String iNS_PROD_QTY_YEAR, String iNS_SAL_QTY,
			String iNS_SAL_QTY_MONTH, String iNS_SAL_QTY_YEAR, String iNS_SALSUM_QTY, String iNS_SALSUM_QTY_MONTH,
			String iNS_SALSUM_QTY_YEAR, String iNS_STK_QTY, String iNS_DX, String iNS_JD, String cOM_BUY_QTY,
			String cOM_BUY_QTY_MONTH, String cOM_BUY_QTY_YEAR, String cOM_SAL_QTY, String cOM_SAL_QTY_MONTH,
			String cOM_SAL_QTY_YEAR, String cOM_SALSUM_QTY, String cOM_SALSUM_QTY_MONTH, String cOM_SALSUM_QTY_YEAR,
			String cOM_STK_QTY, String cOM_DX, String cOM_JD, String tRANSIT_QTY) {
		super();
		INS_PROD_QTY = iNS_PROD_QTY;
		INS_PROD_QTY_MONTH = iNS_PROD_QTY_MONTH;
		INS_PROD_QTY_YEAR = iNS_PROD_QTY_YEAR;
		INS_SAL_QTY = iNS_SAL_QTY;
		INS_SAL_QTY_MONTH = iNS_SAL_QTY_MONTH;
		INS_SAL_QTY_YEAR = iNS_SAL_QTY_YEAR;
		INS_SALSUM_QTY = iNS_SALSUM_QTY;
		INS_SALSUM_QTY_MONTH = iNS_SALSUM_QTY_MONTH;
		INS_SALSUM_QTY_YEAR = iNS_SALSUM_QTY_YEAR;
		INS_STK_QTY = iNS_STK_QTY;
		INS_DX = iNS_DX;
		INS_JD = iNS_JD;
		COM_BUY_QTY = cOM_BUY_QTY;
		COM_BUY_QTY_MONTH = cOM_BUY_QTY_MONTH;
		COM_BUY_QTY_YEAR = cOM_BUY_QTY_YEAR;
		COM_SAL_QTY = cOM_SAL_QTY;
		COM_SAL_QTY_MONTH = cOM_SAL_QTY_MONTH;
		COM_SAL_QTY_YEAR = cOM_SAL_QTY_YEAR;
		COM_SALSUM_QTY = cOM_SALSUM_QTY;
		COM_SALSUM_QTY_MONTH = cOM_SALSUM_QTY_MONTH;
		COM_SALSUM_QTY_YEAR = cOM_SALSUM_QTY_YEAR;
		COM_STK_QTY = cOM_STK_QTY;
		COM_DX = cOM_DX;
		COM_JD = cOM_JD;
		TRANSIT_QTY = tRANSIT_QTY;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getINS_PROD_QTY() {
		return INS_PROD_QTY;
	}

	public void setINS_PROD_QTY(String iNS_PROD_QTY) {
		INS_PROD_QTY = iNS_PROD_QTY;
	}

	public String getINS_PROD_QTY_MONTH() {
		return INS_PROD_QTY_MONTH;
	}

	public void setINS_PROD_QTY_MONTH(String iNS_PROD_QTY_MONTH) {
		INS_PROD_QTY_MONTH = iNS_PROD_QTY_MONTH;
	}

	public String getINS_PROD_QTY_YEAR() {
		return INS_PROD_QTY_YEAR;
	}

	public void setINS_PROD_QTY_YEAR(String iNS_PROD_QTY_YEAR) {
		INS_PROD_QTY_YEAR = iNS_PROD_QTY_YEAR;
	}

	public String getINS_SAL_QTY() {
		return INS_SAL_QTY;
	}

	public void setINS_SAL_QTY(String iNS_SAL_QTY) {
		INS_SAL_QTY = iNS_SAL_QTY;
	}

	public String getINS_SAL_QTY_MONTH() {
		return INS_SAL_QTY_MONTH;
	}

	public void setINS_SAL_QTY_MONTH(String iNS_SAL_QTY_MONTH) {
		INS_SAL_QTY_MONTH = iNS_SAL_QTY_MONTH;
	}

	public String getINS_SAL_QTY_YEAR() {
		return INS_SAL_QTY_YEAR;
	}

	public void setINS_SAL_QTY_YEAR(String iNS_SAL_QTY_YEAR) {
		INS_SAL_QTY_YEAR = iNS_SAL_QTY_YEAR;
	}

	public String getINS_SALSUM_QTY() {
		return INS_SALSUM_QTY;
	}

	public void setINS_SALSUM_QTY(String iNS_SALSUM_QTY) {
		INS_SALSUM_QTY = iNS_SALSUM_QTY;
	}

	public String getINS_SALSUM_QTY_MONTH() {
		return INS_SALSUM_QTY_MONTH;
	}

	public void setINS_SALSUM_QTY_MONTH(String iNS_SALSUM_QTY_MONTH) {
		INS_SALSUM_QTY_MONTH = iNS_SALSUM_QTY_MONTH;
	}

	public String getINS_SALSUM_QTY_YEAR() {
		return INS_SALSUM_QTY_YEAR;
	}

	public void setINS_SALSUM_QTY_YEAR(String iNS_SALSUM_QTY_YEAR) {
		INS_SALSUM_QTY_YEAR = iNS_SALSUM_QTY_YEAR;
	}

	public String getINS_STK_QTY() {
		return INS_STK_QTY;
	}

	public void setINS_STK_QTY(String iNS_STK_QTY) {
		INS_STK_QTY = iNS_STK_QTY;
	}

	public String getINS_DX() {
		return INS_DX;
	}

	public void setINS_DX(String iNS_DX) {
		INS_DX = iNS_DX;
	}

	public String getINS_JD() {
		return INS_JD;
	}

	public void setINS_JD(String iNS_JD) {
		INS_JD = iNS_JD;
	}

	public String getCOM_BUY_QTY() {
		return COM_BUY_QTY;
	}

	public void setCOM_BUY_QTY(String cOM_BUY_QTY) {
		COM_BUY_QTY = cOM_BUY_QTY;
	}

	public String getCOM_BUY_QTY_MONTH() {
		return COM_BUY_QTY_MONTH;
	}

	public void setCOM_BUY_QTY_MONTH(String cOM_BUY_QTY_MONTH) {
		COM_BUY_QTY_MONTH = cOM_BUY_QTY_MONTH;
	}

	public String getCOM_BUY_QTY_YEAR() {
		return COM_BUY_QTY_YEAR;
	}

	public void setCOM_BUY_QTY_YEAR(String cOM_BUY_QTY_YEAR) {
		COM_BUY_QTY_YEAR = cOM_BUY_QTY_YEAR;
	}

	public String getCOM_SAL_QTY() {
		return COM_SAL_QTY;
	}

	public void setCOM_SAL_QTY(String cOM_SAL_QTY) {
		COM_SAL_QTY = cOM_SAL_QTY;
	}

	public String getCOM_SAL_QTY_MONTH() {
		return COM_SAL_QTY_MONTH;
	}

	public void setCOM_SAL_QTY_MONTH(String cOM_SAL_QTY_MONTH) {
		COM_SAL_QTY_MONTH = cOM_SAL_QTY_MONTH;
	}

	public String getCOM_SAL_QTY_YEAR() {
		return COM_SAL_QTY_YEAR;
	}

	public void setCOM_SAL_QTY_YEAR(String cOM_SAL_QTY_YEAR) {
		COM_SAL_QTY_YEAR = cOM_SAL_QTY_YEAR;
	}

	public String getCOM_SALSUM_QTY() {
		return COM_SALSUM_QTY;
	}

	public void setCOM_SALSUM_QTY(String cOM_SALSUM_QTY) {
		COM_SALSUM_QTY = cOM_SALSUM_QTY;
	}

	public String getCOM_SALSUM_QTY_MONTH() {
		return COM_SALSUM_QTY_MONTH;
	}

	public void setCOM_SALSUM_QTY_MONTH(String cOM_SALSUM_QTY_MONTH) {
		COM_SALSUM_QTY_MONTH = cOM_SALSUM_QTY_MONTH;
	}

	public String getCOM_SALSUM_QTY_YEAR() {
		return COM_SALSUM_QTY_YEAR;
	}

	public void setCOM_SALSUM_QTY_YEAR(String cOM_SALSUM_QTY_YEAR) {
		COM_SALSUM_QTY_YEAR = cOM_SALSUM_QTY_YEAR;
	}

	public String getCOM_STK_QTY() {
		return COM_STK_QTY;
	}

	public void setCOM_STK_QTY(String cOM_STK_QTY) {
		COM_STK_QTY = cOM_STK_QTY;
	}

	public String getCOM_DX() {
		return COM_DX;
	}

	public void setCOM_DX(String cOM_DX) {
		COM_DX = cOM_DX;
	}

	public String getCOM_JD() {
		return COM_JD;
	}

	public void setCOM_JD(String cOM_JD) {
		COM_JD = cOM_JD;
	}

	public String getTRANSIT_QTY() {
		return TRANSIT_QTY;
	}

	public void setTRANSIT_QTY(String tRANSIT_QTY) {
		TRANSIT_QTY = tRANSIT_QTY;
	}

	public int getRenderSpeed() {
		return renderSpeed;
	}

	public void setRenderSpeed(int renderSpeed) {
		this.renderSpeed = renderSpeed;
	}

	@Override
	public String toString() {
		return "PushJumpDataVO [INS_PROD_QTY=" + INS_PROD_QTY + ", INS_PROD_QTY_MONTH=" + INS_PROD_QTY_MONTH
				+ ", INS_PROD_QTY_YEAR=" + INS_PROD_QTY_YEAR + ", INS_SAL_QTY=" + INS_SAL_QTY + ", INS_SAL_QTY_MONTH="
				+ INS_SAL_QTY_MONTH + ", INS_SAL_QTY_YEAR=" + INS_SAL_QTY_YEAR + ", INS_SALSUM_QTY=" + INS_SALSUM_QTY
				+ ", INS_SALSUM_QTY_MONTH=" + INS_SALSUM_QTY_MONTH + ", INS_SALSUM_QTY_YEAR=" + INS_SALSUM_QTY_YEAR
				+ ", INS_STK_QTY=" + INS_STK_QTY + ", INS_DX=" + INS_DX + ", INS_JD=" + INS_JD + ", COM_BUY_QTY="
				+ COM_BUY_QTY + ", COM_BUY_QTY_MONTH=" + COM_BUY_QTY_MONTH + ", COM_BUY_QTY_YEAR=" + COM_BUY_QTY_YEAR
				+ ", COM_SAL_QTY=" + COM_SAL_QTY + ", COM_SAL_QTY_MONTH=" + COM_SAL_QTY_MONTH + ", COM_SAL_QTY_YEAR="
				+ COM_SAL_QTY_YEAR + ", COM_SALSUM_QTY=" + COM_SALSUM_QTY + ", COM_SALSUM_QTY_MONTH="
				+ COM_SALSUM_QTY_MONTH + ", COM_SALSUM_QTY_YEAR=" + COM_SALSUM_QTY_YEAR + ", COM_STK_QTY=" + COM_STK_QTY
				+ ", COM_DX=" + COM_DX + ", COM_JD=" + COM_JD + ", TRANSIT_QTY=" + TRANSIT_QTY + "]";
	}

}
