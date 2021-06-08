package com.icss.welcome.bussiness.jumpdata;

import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;

public class TransitJumpData {
	private static Log log = LogFactory.getLog(TransitJumpData.class);

	/**
	 * 工商在途 实时蹦数
	 * 
	 * 查询:INITDATA、CIGDAYSTOCK、COMM_IN_STOCK
	 * 
	 * 数据库：stma
	 * 
	 * 查询结果 TRANSIT_QTY 工商在途
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
		sql.append("  decimal(round(ONROAD+ IND_OUT_FACT_SUM - IN_NUM1,0),18,0) as TRANSIT_QTY ");
		sql.append("from table ( ");
		sql.append("  select ");
		sql.append("    IB_ONROAD AS ONROAD ");
		sql.append("  from INITDATA ");
		sql.append(") a, table( ");
		sql.append("  select ");
		sql.append("    value(decimal(round(sum(OUT_FACT_NUM1-RETURNNUM1)/5,2),18,2),0) as IND_OUT_FACT_SUM ");
		sql.append("  from CIGDAYSTOCK ");
		sql.append("    where RECDATE = current date ");
		sql.append(") b, table( ");
		sql.append("  select ");
		sql.append("    value(decimal(round(sum(IN_NUM1)/5,2),18,2),0) as IN_NUM1 ");
		sql.append("   from COMM_IN_STOCK ");
		sql.append("     where RECDATE = current date ");
		sql.append(") c ");
		sql.append("with ur ");
		return sql.toString();
	}

	// 数据库连接stma
	public PushJumpDataVO getTransitJumpData(String dbName) throws Exception {
		DBBeanBase dbbean = new DBBeanBase(dbName);
		PushJumpDataVO pjdvo = new PushJumpDataVO();
		String sql = "";
		try {
			sql = getSql();
			pjdvo = (PushJumpDataVO) dbbean.executeQuerySingle(sql, PushJumpDataVO.class.getName());
		} catch (Exception e) {
			log.info("----------------------------------------------");
			log.info("stma执行工商在途sql异常: " + e);
			log.error("stma执行sql执行异常", e);
			log.info("----------------------------------------------");
			throw new Exception("数据库查询异常：" + sql);
		} finally {
			dbbean.close();
		}
		return pjdvo;
	}

	public static void main(String[] args) {

		TransitJumpData cj = new TransitJumpData();
		PushJumpDataVO pjdvo = new PushJumpDataVO();
		try {
			pjdvo = cj.getTransitJumpData("stma");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(pjdvo.toString());

	}
}
