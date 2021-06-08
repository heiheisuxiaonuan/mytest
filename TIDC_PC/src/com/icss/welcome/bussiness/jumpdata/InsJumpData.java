package com.icss.welcome.bussiness.jumpdata;

import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;

public class InsJumpData {
	private static Log log = LogFactory.getLog(InsJumpData.class);

	/**
	 * 工业产销存 实时蹦数
	 * 
	 * 查询:CIGDAYSTOCK、IN_STOCK
	 * 
	 * 数据库：stma
	 * 
	 * 查询结果 INS_PROD_QTY 工业生产 INS_SAL_QTY 工业销售 INS_STK_QTY 工业库存
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
		sql.append("decimal(ROUND(PRODUCEAMT,0),24,0) AS INS_PROD_QTY, ");
		sql.append("decimal(ROUND(PRODUCEAMT+PRODNUM1_MONTH,0),24,0) AS INS_PROD_QTY_MONTH, ");
		sql.append("decimal(ROUND(PRODUCEAMT+PRODNUM1_YEAR,0),24,0) AS INS_PROD_QTY_YEAR, ");
		sql.append("decimal(ROUND(IND_OUT_FACT,0),24,0) AS INS_SAL_QTY, ");
		sql.append("decimal(ROUND(IND_OUT_FACT+OUT_FACT_NUM1_MONTH,0),24,0) AS INS_SAL_QTY_MONTH, ");
		sql.append("decimal(ROUND(IND_OUT_FACT+OUT_FACT_NUM1_YEAR,0),24,0) AS INS_SAL_QTY_YEAR, ");
		sql.append("decimal(ROUND(IND_OUT_FACT_SUM,0),24,0) AS INS_SALSUM_QTY, ");
		sql.append("decimal(ROUND(IND_OUT_FACT_SUM+OUT_FACT_SUM_MONTH,0),24,0) AS INS_SALSUM_QTY_MONTH, ");
		sql.append("decimal(ROUND(IND_OUT_FACT_SUM+OUT_FACT_SUM_YEAR,0),24,0) AS INS_SALSUM_QTY_YEAR, ");
		sql.append("decimal(ROUND(IN_STOCK+PRODUCEAMT-IND_OUT_FACT1,0),24,0) AS INS_STK_QTY, ");
		sql.append(
				"CASE WHEN (IND_OUT_FACT+OUT_FACT_NUM1_YEAR)<>0 THEN decimal(round(1.0000*(IND_OUT_FACT_SUM+OUT_FACT_SUM_YEAR)/(IND_OUT_FACT+OUT_FACT_NUM1_YEAR),2),24,2) ELSE 0 END AS INS_DX, ");
		sql.append(
				"CASE WHEN PLAN_PRINT<>0 THEN decimal(round(100.0000*(PRODUCEAMT+PRODNUM1_YEAR)/PLAN_PRINT,0),24,0) ELSE 0 END AS INS_JD ");
		sql.append("FROM TABLE ( ");
		sql.append("SELECT ");
		sql.append("value(SUM(PRINTNUM1-REJECTNUM1+REPEATNUM1-JUMPNUM1)/5,0) AS PRODUCEAMT, ");
		sql.append("value(SUM(OUT_FACT_NUM1-RETURNNUM1)/5,0) AS IND_OUT_FACT, ");
		sql.append("value(SUM(OUT_FACT_SUM-RETURN_SUM),0) AS IND_OUT_FACT_SUM, ");
		sql.append("value(SUM(OUT_FACT_NUM1-RETURNNUM1+UNIONNUM1-UNIONOUTNUM1)/5,0) AS IND_OUT_FACT1 ");
		sql.append("FROM CIGDAYSTOCK ");
		sql.append("WHERE RECDATE = CURRENT DATE ");
		sql.append(") a, TABLE ( ");
		sql.append("SELECT ");
		sql.append(
				"IN_STOCK, PRODNUM1_MONTH, PRODNUM1_YEAR, OUT_FACT_NUM1_MONTH, OUT_FACT_NUM1_YEAR, OUT_FACT_SUM_MONTH, OUT_FACT_SUM_YEAR, PLAN_PRINT ");
		sql.append("from INITDATA ");
		sql.append(") ");
		sql.append("with ur ");
		return sql.toString();
	}

	// 数据库连接stma
	public PushJumpDataVO getInsJumpData(String dbName) throws Exception {
		DBBeanBase dbbean = new DBBeanBase(dbName);
		PushJumpDataVO pjdvo = new PushJumpDataVO();
		String sql = "";
		try {
			sql = getSql();
			pjdvo = (PushJumpDataVO) dbbean.executeQuerySingle(sql, PushJumpDataVO.class.getName());
		} catch (Exception e) {
			log.info("---------------------------------");
			log.info("stma工业生产、销售、库存实时蹦数执行sql异常" + e);
			log.error("stma工业生产、销售、库存实时蹦数执行sql执行异常", e);
			log.info("---------------------------------");
			throw new Exception("数据库查询异常：" + sql);
		} finally {
			dbbean.close();
		}
		return pjdvo;
	}

	public static void main(String[] args) {

		InsJumpData cj = new InsJumpData();
		PushJumpDataVO pjdvo = new PushJumpDataVO();
		try {
			pjdvo = cj.getInsJumpData("stma");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(pjdvo.toString());

	}

}
