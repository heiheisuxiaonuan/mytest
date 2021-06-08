package com.icss.welcome.bussiness.jumpdata;

import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;

public class ComJumpData {

	private static Log log = LogFactory.getLog(ComJumpData.class);

	/**
	 * 商业购销存 实时蹦数
	 * 
	 * 查询:COMM_STOCK、BS_STOCK
	 * 
	 * 数据库：stma
	 * 
	 * 查询结果 COM_BUY_QTY 商业购进 COM_SAL_QTY 商业销售 COM_STK_QTY 商业库存
	 * 
	 * 
	 * return
	 * 
	 * @author
	 * @since 2015-12-10
	 * @version 1.0
	 * 
	 */

	public static String getSql() {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append("decimal(ROUND(ARRIVAL_NUM1,0),24,0) AS COM_BUY_QTY, ");
		sql.append("decimal(ROUND(ARRIVAL_NUM1+FLOWNUM1_MONTH,0),24,0) AS COM_BUY_QTY_MONTH, ");
		sql.append("decimal(ROUND(ARRIVAL_NUM1+FLOWNUM1_YEAR,0),24,0) AS COM_BUY_QTY_YEAR, ");
		sql.append("decimal(ROUND(PRINT_NUM1,0),24,0) AS COM_SAL_QTY, ");
		sql.append("decimal(ROUND(PRINT_NUM1+PRINT_NUM1_MONTH,0),24,0) AS COM_SAL_QTY_MONTH, ");
		sql.append("decimal(ROUND(PRINT_NUM1+PRINT_NUM1_YEAR,0),24,0) AS COM_SAL_QTY_YEAR, ");
		sql.append("decimal(ROUND(PRINT_SUM,0),24,0) AS COM_SALSUM_QTY, ");
		sql.append("decimal(ROUND(PRINT_SUM+PRINT_SUM_MONTH,0),24,0) AS COM_SALSUM_QTY_MONTH, ");
		sql.append("decimal(ROUND(PRINT_SUM+PRINT_SUM_YEAR,0),24,0) AS COM_SALSUM_QTY_YEAR, ");
		sql.append("decimal(ROUND(BS_STOCK+ ARRIVAL_NUM1- RECAMOSORNUM1,0),24,0) AS COM_STK_QTY, ");
		sql.append(
				"CASE WHEN (PRINT_NUM1+PRINT_NUM1_YEAR)<>0 THEN decimal(round(1.0000*(PRINT_SUM+PRINT_SUM_YEAR)/(PRINT_NUM1+PRINT_NUM1_YEAR),2),24,2) ELSE 0 END AS COM_DX, ");
		sql.append(
				"CASE WHEN PLAN_SELL<>0 THEN decimal(round(100.0000*(PRINT_NUM1+PRINT_NUM1_YEAR)/PLAN_SELL,0),24,0) ELSE 0 END AS COM_JD ");
		sql.append("FROM TABLE ( ");
		sql.append("SELECT ");
		sql.append("value(SUM(ARRIVAL_NUM1-BACKOUT_NUM1)/5,0)        AS ARRIVAL_NUM1, ");
		sql.append("value(SUM(PRINT_NUM1+SELF_SUPP_NUM1-RBACK_NUM1)/5,0) AS  PRINT_NUM1, ");
		sql.append("value(SUM(PRINT_SUM+SELF_SUPP_SUM-RBACK_SUM),0) AS  PRINT_SUM, ");
		sql.append(
				"value(SUM(PRINT_NUM1+SELF_SUPP_NUM1-RBACK_NUM1+TRANSOUT_NUM1-TRANSBACK_NUM1)/5,0)+15000 AS RECAMOSORNUM1 ");
		sql.append("FROM COMM_STOCK ");
		sql.append("WHERE RECDATE = CURRENT DATE ");
		sql.append(") a, TABLE ( ");
		sql.append("SELECT ");
		sql.append(
				"BS_STOCK, FLOWNUM1_MONTH, FLOWNUM1_YEAR, PRINT_NUM1_MONTH, PRINT_NUM1_YEAR, PRINT_SUM_MONTH, PRINT_SUM_YEAR, PLAN_SELL ");
		sql.append("from INITDATA ");
		sql.append(") b ");
		sql.append("with ur ");
		return sql.toString();
	}

	// 数据库连接stma
	public PushJumpDataVO getComJumpData(String dbName) throws Exception {
		DBBeanBase dbbean = new DBBeanBase(dbName);
		PushJumpDataVO pjdvo = new PushJumpDataVO();
		String sql = "";
		try {
			sql = getSql();
			pjdvo = (PushJumpDataVO) dbbean.executeQuerySingle(sql, PushJumpDataVO.class.getName());
			log.info("进来了---------------------------------");
		} catch (Exception e) {
			log.info("---------------------------------");
			log.info("stma商业购进、销售、库存实时蹦数执行sql异常" + e);
			log.error("stma商业购进、销售、库存实时蹦数执行sql执行异常", e);
			log.info("---------------------------------");
			throw new Exception("数据库查询异常：" + sql);
		} finally {
			dbbean.close();
		}
		return pjdvo;
	}

	public static void main(String[] args) {

		ComJumpData cj = new ComJumpData();
		PushJumpDataVO pjdvo = new PushJumpDataVO();
		try {
			pjdvo = cj.getComJumpData("stma");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(pjdvo.toString());

	}
}
