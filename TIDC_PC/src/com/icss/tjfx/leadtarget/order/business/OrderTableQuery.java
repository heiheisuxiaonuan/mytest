package com.icss.tjfx.leadtarget.order.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.leadtarget.order.vo.OrderTableDataVO;
/**
 * 先行指标查询方法类
 * @author lcx
 *
 */
public class OrderTableQuery extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(OrderTableQuery.class);
	
	/**
	 * 获取先行指标订单表格查询sql
	 * @return 
	 */
	public static String getSqlOrder(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT                                                                                                                                                                                                                                                                                            ");
		sql.append("			                                                                                                                                                                                                                                                                                            ");
		sql.append("			DECODE(M,0,'合计',CHAR(M))  as Mon                                                                                                                                                                                                                                                            ");
		sql.append("			,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS 	CC_DEMAND_AMOUNT													                                                                                                      ");
		sql.append("			,CASE WHEN SUM(CC_DEMAND_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS	CC_DEMAND_AMOUNT_L											                                                                                                      ");
		sql.append("			,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 AND SUM(CC_DEMAND_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT - CC_DEMAND_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_DEMAND_AMOUNT_GROWTH		                                                                    ");
		sql.append("			,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 AND SUM(CC_DEMAND_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT_L),0,0,NULL,0,SUM(CC_DEMAND_AMOUNT - CC_DEMAND_AMOUNT_L)*100.0000/SUM(CC_DEMAND_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_DEMAND_AMOUNT_GROWTH_RATE    ");
		sql.append("			,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT                                                                                                                                     ");
		sql.append("			,CASE WHEN SUM(CC_ORDER_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT_L                                                                                                                               ");
		sql.append("			,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 AND SUM(CC_ORDER_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT - CC_ORDER_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT_GROWTH	                                                                            ");
		sql.append("			,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 AND SUM(CC_ORDER_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_ORDER_AMOUNT_L),0,0,NULL,0,SUM(CC_ORDER_AMOUNT - CC_ORDER_AMOUNT_L)*100.0000/SUM(CC_ORDER_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT_GROWTH_RATE           ");
		sql.append("			,CASE WHEN SUM(CC_GAP_AMOUNT)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS  CC_GAP_AMOUNT	                                                                                                                                        ");
		sql.append("			,CASE WHEN SUM(CC_GAP_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_L                                                                                                                                     ");
		sql.append("			,CASE WHEN SUM(CC_GAP_AMOUNT)=0 AND SUM(CC_GAP_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT - CC_GAP_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_GROWTH		                                                                                    ");
		sql.append("			,CASE WHEN SUM(CC_GAP_AMOUNT)=0 AND SUM(CC_GAP_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_GAP_AMOUNT_L),0,0,NULL,0,SUM(CC_GAP_AMOUNT - CC_GAP_AMOUNT_L)*100.0000/SUM(CC_GAP_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_GROWTH_RATE                         ");
		sql.append("			,CASE WHEN SUM(CC_satisfaction_AMOUNT)=0 THEN '-' ELSE TO_CHAR(SUM(CC_satisfaction_AMOUNT),'999990.99') END AS CC_satisfaction_AMOUNT                                                                                                                                                                            ");
		sql.append("			,CASE WHEN SUM(CC_satisfaction_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(SUM(CC_satisfaction_AMOUNT_L),'999990.99') END AS CC_satisfaction_AMOUNT_L                                                                                                                                                                      ");
		sql.append("			,CASE WHEN SUM(CC_satisfaction_AMOUNT)=0 AND SUM(CC_satisfaction_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(SUM(CC_satisfaction_AMOUNT - CC_satisfaction_AMOUNT_L),'999990.99') END AS CC_satisfaction_AMOUNT_GROWTH_RATE		                                                                                              ");
		sql.append("FROM                                                                                                       ");
		sql.append("	(                                                                                                        ");
		sql.append("		SELECT                                                                                                 ");
		sql.append("			NVL(M,0) AS M                                                                                        ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT) AS CC_DEMAND_AMOUNT					                                                              ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT_L) AS	CC_DEMAND_AMOUNT_L			                                                              ");
		sql.append("			,SUM(CC_ORDER_AMOUNT) AS CC_ORDER_AMOUNT                                                                          ");
		sql.append("			,SUM(CC_ORDER_AMOUNT_L) AS CC_ORDER_AMOUNT_L                                                                      ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT)-SUM(CC_ORDER_AMOUNT) AS  CC_GAP_AMOUNT	                                     ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT_L)-SUM(CC_ORDER_AMOUNT_L) AS CC_GAP_AMOUNT_L                                 ");
		sql.append("			,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT),0,0,NULL,0,SUM(CC_ORDER_AMOUNT)*100.0000/SUM(CC_DEMAND_AMOUNT)), 2), 16, 2),'999990.99') AS CC_satisfaction_AMOUNT                                                                                                                      ");
		sql.append("			,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT_L),0,0,NULL,0,SUM(CC_ORDER_AMOUNT_L)*100.0000/SUM(CC_DEMAND_AMOUNT_L)), 2), 16, 2),'999990.99') AS CC_satisfaction_AMOUNT_L                                                                                                              ");
		sql.append("		FROM B_ORDER_M_A_brand 			                                                                                                                                                        ");
		sql.append("		WHERE Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-1)  else  (YEAR(CURRENT TIMESTAMP)) end )                                                      ");
		sql.append("		GROUP BY M                                                                         ");
		sql.append("		WITH ROLLUP                                                                        ");
		sql.append("		                                                                                                   ");
		sql.append("		                                                                                                   ");
		sql.append("	) K                                                                                       ");
		sql.append("GROUP BY m                                                                                  ");
		sql.append("ORDER BY M                                                                                  ");
		sql.append("WITH UR                                                                                                                                                                                                                                                                                           ");

		
		log.info("先行指标订单初始表格查询sql为："+sql);
		return sql.toString();
	}
	
	
	
	/**
	 * 查询先行指标订单初始表格数据信息
	 * @param dbName
	 * @return List
	 */
	public List<OrderTableDataVO> getOrderInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<OrderTableDataVO> orderInfoList = new ArrayList<OrderTableDataVO>();
		try {
			sql = getSqlOrder();
			orderInfoList = dbbean.executeQuery(sql, OrderTableDataVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询先行指标订单初始表格数据信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询先行指标订单初始表格数据信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return orderInfoList;
	}
	
	/**
	 * 获取先行指标订单品牌下钻表格查询sql
	 * @param code 
	 * @return 
	 */
	public static String getSqlBrand(String code){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (                                                                                                                                                                                                                                                                                 ");
		sql.append("SELECT		                                                                                                                                                                                                                                                                                      ");
		sql.append("	NVL(CIG_MARKNAME,'合计') as CIG_NAME                                                                                                                                                                                                                                                            ");
		sql.append("	,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS 	CC_DEMAND_AMOUNT													                                                                                                          ");
		sql.append("	,CASE WHEN SUM(CC_DEMAND_AMOUNT_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS	CC_DEMAND_AMOUNT_L											                                                                                                        ");
		sql.append("	,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 AND SUM(CC_DEMAND_AMOUNT_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT - CC_DEMAND_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END as CC_DEMAND_AMOUNT_GROWTH		                                                                        ");
		sql.append("	,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 AND SUM(CC_DEMAND_AMOUNT_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT_L),0,0,NULL,0,SUM(CC_DEMAND_AMOUNT - CC_DEMAND_AMOUNT_L)*100.0000/SUM(CC_DEMAND_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_DEMAND_AMOUNT_GROWTH_RATE       ");
		sql.append("	,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT                                                                                                                                        ");
		sql.append("	,CASE WHEN SUM(CC_ORDER_AMOUNT_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT_L                                                                                                                                  ");
		sql.append("	,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 AND SUM(CC_ORDER_AMOUNT_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT - CC_ORDER_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END as CC_ORDER_AMOUNT_GROWTH	                                                                              ");
		sql.append("	,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 or SUM(CC_ORDER_AMOUNT_L)=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND(DECODE(SUM(CC_ORDER_AMOUNT_L),0,0,NULL,0,SUM(CC_ORDER_AMOUNT - CC_ORDER_AMOUNT_L)*100.0000/SUM(CC_ORDER_AMOUNT_L)), 2), 16, 2)) END AS CC_ORDER_AMOUNT_GROWTH_RATE              ");
		sql.append("	,CASE WHEN SUM(CC_GAP_AMOUNT)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS  CC_GAP_AMOUNT	                                                                                                                                            ");
		sql.append("	,CASE WHEN SUM(CC_GAP_AMOUNT_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_L                                                                                                                                        ");
		sql.append("	,CASE WHEN SUM(CC_GAP_AMOUNT)=0 AND SUM(CC_GAP_AMOUNT_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT - CC_GAP_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END as CC_GAP_AMOUNT_GROWTH		                                                                                      ");
		sql.append("	,CASE WHEN SUM(CC_GAP_AMOUNT)=0 AND SUM(CC_GAP_AMOUNT_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_GAP_AMOUNT_L),0,0,NULL,0,SUM(CC_GAP_AMOUNT - CC_GAP_AMOUNT_L)*100.0000/SUM(CC_GAP_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_GROWTH_RATE                            ");
		sql.append("	,CASE WHEN SUM(CC_satisfaction_AMOUNT)=0 THEN'-' ELSE TO_CHAR(SUM(CC_satisfaction_AMOUNT),'999990.99') END AS CC_satisfaction_AMOUNT                                                                                                                                                          ");
		sql.append("	,CASE WHEN SUM(CC_satisfaction_AMOUNT_L)=0 THEN'-' ELSE TO_CHAR(SUM(CC_satisfaction_AMOUNT_L),'999990.99') END AS CC_satisfaction_AMOUNT_L                                                                                                                                                    ");
		sql.append("	,CASE WHEN SUM(CC_satisfaction_AMOUNT)=0 AND SUM(CC_satisfaction_AMOUNT_L)=0 THEN'-' ELSE TO_CHAR(SUM(CC_satisfaction_AMOUNT - CC_satisfaction_AMOUNT_L),'999990.99') END as CC_satisfaction_AMOUNT_GROWTH_RATE		                                                                            ");
		sql.append("FROM                                                                      ");
		sql.append("	(                                                                       ");
		sql.append("		SELECT                                                                ");
		sql.append("			value(J.CIG_MARKNAME,' ') AS CIG_MARKNAME                           ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT) AS CC_DEMAND_AMOUNT					                ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT_L) AS	CC_DEMAND_AMOUNT_L			                ");
		sql.append("			,SUM(CC_ORDER_AMOUNT) AS CC_ORDER_AMOUNT                            ");
		sql.append("			,SUM(CC_ORDER_AMOUNT_L) AS CC_ORDER_AMOUNT_L                        ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT)-SUM(CC_ORDER_AMOUNT) AS  CC_GAP_AMOUNT	                                                                                                                                                                                                                            ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT_L)-SUM(CC_ORDER_AMOUNT_L) AS CC_GAP_AMOUNT_L                                                                                                                                                                                                                        ");
		sql.append("			,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT),0,0,NULL,0,SUM(CC_ORDER_AMOUNT)*100.0000/SUM(CC_DEMAND_AMOUNT)), 2), 16, 2),'999990.99') AS CC_satisfaction_AMOUNT                                                                                                                    ");
		sql.append("			,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT_L),0,0,NULL,0,SUM(CC_ORDER_AMOUNT_L)*100.0000/SUM(CC_DEMAND_AMOUNT_L)), 2), 16, 2),'999990.99') AS CC_satisfaction_AMOUNT_L                                                                                                            ");
		sql.append("		FROM B_ORDER_M_A_brand K                                      ");
		sql.append("left join                                                         ");
		sql.append("VIEW_NICK_CIG_PROPERTY J on K.BRAND_CODE=J.C_BRAND                ");
		sql.append("		WHERE 1 = 1                                                   ");
		sql.append("		AND  Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-1)  else  (YEAR(CURRENT TIMESTAMP)) end ) ");
		sql.append("		AND M='").append(code).append("'                                                     ");
		sql.append("		GROUP BY value(J.CIG_MARKNAME,' ')                            ");
		sql.append("		HAVING                                                        ");
		sql.append("	(                                                               ");
		sql.append("		SUM(CC_DEMAND_AMOUNT)         <> 0                            ");
		sql.append("		or SUM(CC_DEMAND_AMOUNT_L)    <> 0                            ");
		sql.append("		or SUM(CC_ORDER_AMOUNT)      <> 0                             ");
		sql.append("		or SUM(CC_ORDER_AMOUNT_L)    <> 0                             ");
		sql.append("	)                                                               ");
		sql.append("		                                                              ");
		sql.append("	) K                                                             ");
		sql.append("GROUP BY CIG_MARKNAME                                             ");
		sql.append("WITH ROLLUP )                                                     ");
		sql.append("where CIG_NAME <> ' '                                             ");
		sql.append("ORDER BY DECODE(CIG_NAME,'合计',0,1) ASC,DECODE(CC_DEMAND_AMOUNT,'-',-1,CC_DEMAND_AMOUNT) DESC                                                                                                                                                                                                    ");
		sql.append("WITH UR                                                                                                                                                                                                                                                                                        ");

		log.info("获取先行指标订单品牌下钻表格查询sql为："+sql);
		return sql.toString();
	}
	/**
	 * 查询先行指标订单品牌下钻表格数据信息
	 * @param dbName
	 * @return List
	 */
	public List<OrderTableDataVO> getTradeInfoBrand(String dbName,String code){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<OrderTableDataVO> tradeInfoList = new ArrayList<OrderTableDataVO>();
		try {
			sql = getSqlBrand(code);
			tradeInfoList = dbbean.executeQuery(sql, OrderTableDataVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询先行指标订单品牌下钻表格数据信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询先行指标订单品牌下钻表格数据信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return tradeInfoList;
	}
	/**
	 * 查询先行指标订单价类下钻表格数据信息
	 * @param code 
	 * @param dbName
	 * @return List
	 */
	public static String getSqlPrice(String code){
		StringBuffer sql = new StringBuffer();
		/*sql.append("select * from (                                                                                                                                                                                                                                                                                 ");
		sql.append("SELECT                                                                                                                                                                                                                                                                                          ");
		sql.append("			                                                                                                                                                                                                                                                                                          ");
		sql.append("			NVL(NAME,'合计') as CIG_NAME                                                                                                                                                                                                                                                                ");
		sql.append("			,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS 	CC_DEMAND_AMOUNT													                                                                                                    ");
		sql.append("			,CASE WHEN SUM(CC_DEMAND_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS	CC_DEMAND_AMOUNT_L											                                                                                                    ");
		sql.append("			,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 AND SUM(CC_DEMAND_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT - CC_DEMAND_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_DEMAND_AMOUNT_GROWTH		                                                                  ");
		sql.append("			,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 AND SUM(CC_DEMAND_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT_L),0,0,NULL,0,SUM(CC_DEMAND_AMOUNT - CC_DEMAND_AMOUNT_L)*100.0000/SUM(CC_DEMAND_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_DEMAND_AMOUNT_GROWTH_RATE  ");  
		sql.append("			,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT                                                                                                                                   ");
		sql.append("			,CASE WHEN SUM(CC_ORDER_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT_L                                                                                                                             ");
		sql.append("			,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 AND SUM(CC_ORDER_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT - CC_ORDER_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT_GROWTH	                                                                          ");
		sql.append("			,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 AND SUM(CC_ORDER_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_ORDER_AMOUNT_L),0,0,NULL,0,SUM(CC_ORDER_AMOUNT - CC_ORDER_AMOUNT_L)*100.0000/SUM(CC_ORDER_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT_GROWTH_RATE         ");
		sql.append("			,CASE WHEN SUM(CC_GAP_AMOUNT)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS  CC_GAP_AMOUNT	                                                                                                                                      ");
		sql.append("			,CASE WHEN SUM(CC_GAP_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_L                                                                                                                                   ");
		sql.append("			,CASE WHEN SUM(CC_GAP_AMOUNT)=0 AND SUM(CC_GAP_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT - CC_GAP_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_GROWTH		                                                                                  ");
		sql.append("			,CASE WHEN SUM(CC_GAP_AMOUNT)=0 AND SUM(CC_GAP_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_GAP_AMOUNT_L),0,0,NULL,0,SUM(CC_GAP_AMOUNT - CC_GAP_AMOUNT_L)*100.0000/SUM(CC_GAP_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_GROWTH_RATE                       ");
		sql.append("			,CASE WHEN SUM(CC_satisfaction_AMOUNT)=0 THEN '-' ELSE SUM(CC_satisfaction_AMOUNT) END AS CC_satisfaction_AMOUNT                                                                                                                                                                          ");
		sql.append("			,CASE WHEN SUM(CC_satisfaction_AMOUNT_L)=0 THEN '-' ELSE SUM(CC_satisfaction_AMOUNT_L) END AS CC_satisfaction_AMOUNT_L                                                                                                                                                                    ");
		sql.append("			,CASE WHEN SUM(CC_satisfaction_AMOUNT)=0 AND SUM(CC_satisfaction_AMOUNT_L)=0 THEN '-' ELSE SUM(CC_satisfaction_AMOUNT - CC_satisfaction_AMOUNT_L) END AS CC_satisfaction_AMOUNT_GROWTH_RATE		                                                                                            ");
		sql.append("FROM                                                                                                                                                          ");
		sql.append("	(                                                                                                                                                           ");
		sql.append("		SELECT                                                                                                                                                    ");
		sql.append("			value(O.CLASS_NAME,' ') AS NAME                                                                                                                         ");
		sql.append("			,value(O.CODE,' ') AS CODE                                                                                                                              ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT) AS CC_DEMAND_AMOUNT													                                                                                    ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT_L) AS	CC_DEMAND_AMOUNT_L													                                                                                ");
		sql.append("			,SUM(CC_ORDER_AMOUNT) AS CC_ORDER_AMOUNT                                                                                                                ");
		sql.append("			,SUM(CC_ORDER_AMOUNT_L) AS CC_ORDER_AMOUNT_L                                                                                                            ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT)-SUM(CC_ORDER_AMOUNT) AS  CC_GAP_AMOUNT	                                                                                          ");
		sql.append("			,SUM(CC_DEMAND_AMOUNT_L)-SUM(CC_ORDER_AMOUNT_L) AS CC_GAP_AMOUNT_L                                                                                      ");
		sql.append("			,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT),0,0,NULL,0,SUM(CC_ORDER_AMOUNT)*100.0000/SUM(CC_DEMAND_AMOUNT)), 2), 16, 2),'999990.99') AS CC_satisfaction_AMOUNT                                                                                                                    ");
		sql.append("			,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT_L),0,0,NULL,0,SUM(CC_ORDER_AMOUNT_L)*100.0000/SUM(CC_DEMAND_AMOUNT_L)), 2), 16, 2),'999990.99') AS CC_satisfaction_AMOUNT_L                                                                                                            ");
		sql.append("		FROM B_ORDER_M_A_brand K                                                                                                                                                                                                                                                                    ");
		sql.append("		left join                                                                                                                                                                                                                                                                                   ");
		sql.append("		(select CIG_CODEBAR,CIG_PRICETYPENEW from STMA_CIGARETTE_REINFORCE group by CIG_CODEBAR,CIG_PRICETYPENEW) J                                                                                                                                                                                 ");
		sql.append("		on K.BRAND_CODE=J.CIG_CODEBAR                                       ");
		sql.append("                left join                                               ");
		sql.append("		(select * from DW_T_CLASS_CODE where CODE <> 06) O                  ");
		sql.append("		on J.CIG_PRICETYPENEW=O.CODE                                        ");
		sql.append("		WHERE 1 = 1                                                         ");
		sql.append("		AND Y = YEAR(CURRENT DATE)                                          ");
		sql.append("		AND M = ").append(code).append("			                                                      ");
		sql.append("		GROUP BY value(O.CLASS_NAME,' '),value(O.CODE,' ')                  ");
		sql.append("		                                                                    ");
		sql.append("	) K                                                                   ");
		sql.append("	                                                                      ");
		sql.append("GROUP BY K.CODE ,NAME                                                   ");
		sql.append("WITH ROLLUP                                                             ");
		sql.append("HAVING                                                                  ");
		sql.append("	  (                                                                   ");
		sql.append("	   	   (CODE IS NULL AND NAME IS NULL)                                ");
		sql.append("		   OR(CODE IS NOT NULL AND NAME IS NOT NULL)                        ");
		sql.append("		   OR(CODE <> ' ' AND NAME <> ' ')                                  ");
		sql.append("	  )                                                                   ");
		sql.append("ORDER BY NVL(CODE,'00')                                                 ");
		sql.append(") s                                                                     ");
		sql.append("where CIG_NAME <> ' '                                                                                                                                                                                                                                                                           ");
		sql.append("WITH UR                                                                                                                                                                                                                                                                                         ");
*/
		
		
		sql.append(" 	SELECT																																				");												
		sql.append(" 	decode(NAME,' ','合计',NAME) as CIG_NAME																															");
		sql.append(" 	,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS 	CC_DEMAND_AMOUNT																");
		sql.append(" 	,CASE WHEN SUM(CC_DEMAND_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS	CC_DEMAND_AMOUNT_L																");
		sql.append(" 	,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 AND SUM(CC_DEMAND_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT - CC_DEMAND_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_DEMAND_AMOUNT_GROWTH										");
		sql.append(" 	,CASE WHEN SUM(CC_DEMAND_AMOUNT)=0 AND SUM(CC_DEMAND_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT_L),0,0,NULL,0,SUM(CC_DEMAND_AMOUNT - CC_DEMAND_AMOUNT_L)*100.0000/SUM(CC_DEMAND_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_DEMAND_AMOUNT_GROWTH_RATE	");
		sql.append(" 	,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT																		");
		sql.append(" 	,CASE WHEN SUM(CC_ORDER_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT_L																	");
		sql.append(" 	,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 AND SUM(CC_ORDER_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT - CC_ORDER_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT_GROWTH											");
		sql.append(" 	,CASE WHEN SUM(CC_ORDER_AMOUNT)=0 AND SUM(CC_ORDER_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_ORDER_AMOUNT_L),0,0,NULL,0,SUM(CC_ORDER_AMOUNT - CC_ORDER_AMOUNT_L)*100.0000/SUM(CC_ORDER_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_ORDER_AMOUNT_GROWTH_RATE		");
		sql.append(" 	,CASE WHEN SUM(CC_GAP_AMOUNT)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') END AS  CC_GAP_AMOUNT																		");
		sql.append(" 	,CASE WHEN SUM(CC_GAP_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_L																		");
		sql.append(" 	,CASE WHEN SUM(CC_GAP_AMOUNT)=0 AND SUM(CC_GAP_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(CC_GAP_AMOUNT - CC_GAP_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_GROWTH												");
		sql.append(" 	,CASE WHEN SUM(CC_GAP_AMOUNT)=0 AND SUM(CC_GAP_AMOUNT_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_GAP_AMOUNT_L),0,0,NULL,0,SUM(CC_GAP_AMOUNT - CC_GAP_AMOUNT_L)*100.0000/SUM(CC_GAP_AMOUNT_L)), 2), 16, 2),'999990.99') END AS CC_GAP_AMOUNT_GROWTH_RATE				");
		sql.append(" 	,CASE WHEN SUM(CC_satisfaction_AMOUNT)=0 THEN '-' ELSE SUM(CC_satisfaction_AMOUNT) END AS CC_satisfaction_AMOUNT																						");
		sql.append(" 	,CASE WHEN SUM(CC_satisfaction_AMOUNT_L)=0 THEN '-' ELSE SUM(CC_satisfaction_AMOUNT_L) END AS CC_satisfaction_AMOUNT_L																						");
		sql.append(" 	,CASE WHEN SUM(CC_satisfaction_AMOUNT)=0 AND SUM(CC_satisfaction_AMOUNT_L)=0 THEN '-' ELSE SUM(CC_satisfaction_AMOUNT - CC_satisfaction_AMOUNT_L) END AS CC_satisfaction_AMOUNT_GROWTH_RATE													");
		sql.append(" 	FROM																																				");
		sql.append(" 	(																																				");
		sql.append(" 	SELECT																																				");
		sql.append(" 	' ' AS NAME 																																			");
		sql.append(" 	,'00' AS CODE																																			");
		sql.append(" 	,SUM(CC_DEMAND_AMOUNT) AS CC_DEMAND_AMOUNT																															");
		sql.append(" 	,SUM(CC_DEMAND_AMOUNT_L) AS	CC_DEMAND_AMOUNT_L																														");
		sql.append(" 	,SUM(CC_ORDER_AMOUNT) AS CC_ORDER_AMOUNT																															");
		sql.append(" 	,SUM(CC_ORDER_AMOUNT_L) AS CC_ORDER_AMOUNT_L																															");
		sql.append(" 	,SUM(CC_DEMAND_AMOUNT)-SUM(CC_ORDER_AMOUNT) AS  CC_GAP_AMOUNT																													");
		sql.append(" 	,SUM(CC_DEMAND_AMOUNT_L)-SUM(CC_ORDER_AMOUNT_L) AS CC_GAP_AMOUNT_L																												");
		sql.append(" 	,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT),0,0,NULL,0,SUM(CC_ORDER_AMOUNT)*100.0000/SUM(CC_DEMAND_AMOUNT)), 2), 16, 2),'999990.99') AS CC_satisfaction_AMOUNT																");
		sql.append(" 	,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT_L),0,0,NULL,0,SUM(CC_ORDER_AMOUNT_L)*100.0000/SUM(CC_DEMAND_AMOUNT_L)), 2), 16, 2),'999990.99') AS CC_satisfaction_AMOUNT_L		 													");
		sql.append(" 	FROM (select * from B_ORDER_M_A_brand WHERE  Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-1)  else  (YEAR(CURRENT TIMESTAMP)) end ) AND M = ").append(code).append("   ) K 																										");
		sql.append(" 	GROUP BY ' ',' '																																		");
		sql.append(" 	union all																																			");
		sql.append(" 	SELECT																																				");
		sql.append(" 	O.CLASS_NAME AS NAME 																																		");
		sql.append(" 	,O.CODE AS CODE																																			");
		sql.append(" 	,SUM(CC_DEMAND_AMOUNT) AS CC_DEMAND_AMOUNT																															");
		sql.append(" 	,SUM(CC_DEMAND_AMOUNT_L) AS	CC_DEMAND_AMOUNT_L																														");
		sql.append(" 	,SUM(CC_ORDER_AMOUNT) AS CC_ORDER_AMOUNT																															");
		sql.append(" 	,SUM(CC_ORDER_AMOUNT_L) AS CC_ORDER_AMOUNT_L																															");
		sql.append(" 	,SUM(CC_DEMAND_AMOUNT)-SUM(CC_ORDER_AMOUNT) AS  CC_GAP_AMOUNT																													");
		sql.append(" 	,SUM(CC_DEMAND_AMOUNT_L)-SUM(CC_ORDER_AMOUNT_L) AS CC_GAP_AMOUNT_L																												");
		sql.append(" 	,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT),0,0,NULL,0,SUM(CC_ORDER_AMOUNT)*100.0000/SUM(CC_DEMAND_AMOUNT)), 2), 16, 2),'999990.99') AS CC_satisfaction_AMOUNT																");
		sql.append(" 	,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(CC_DEMAND_AMOUNT_L),0,0,NULL,0,SUM(CC_ORDER_AMOUNT_L)*100.0000/SUM(CC_DEMAND_AMOUNT_L)), 2), 16, 2),'999990.99') AS CC_satisfaction_AMOUNT_L   														");
		sql.append(" 	FROM B_ORDER_M_A_brand K,STMA_CIGARETTE_REINFORCE J,DW_T_CLASS_CODE  O																												");
		sql.append(" 	WHERE 1=1																																			");
		sql.append(" 	and K.BRAND_CODE=J.CIG_CODEBAR																																	");
		sql.append(" 	and J.CIG_PRICETYPENEW=O.CODE																																	");
		sql.append(" 	and CODE <> '06'																																		");
		sql.append(" 	AND M = ").append(code).append("          ");
		sql.append(" 	and Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-1)  else  (YEAR(CURRENT TIMESTAMP)) end ) 																																	");
		sql.append(" 	GROUP BY O.CLASS_NAME,O.CODE																																	");
		sql.append(" 	) K1																																				");
		sql.append(" 	GROUP BY decode(NAME,' ','合计',NAME),CODE																														");
		sql.append(" 	order by CODE																																			");
		sql.append(" 	WITH UR																																				");

		
		log.info("获取先行指标价类下钻表格查询sql为："+sql);
		return sql.toString();
	}
	public List<OrderTableDataVO> getTradeInfoPrice(String dbName,String code){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<OrderTableDataVO> tradeInfoList = new ArrayList<OrderTableDataVO>();
		try {
			sql = getSqlPrice(code);
			tradeInfoList = dbbean.executeQuery(sql, OrderTableDataVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询先行指标订单价类下钻表格数据信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询先行指标订单价类下钻表格数据信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return tradeInfoList;
	}

	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<OrderTableDataVO> OrderTableList=null;
		Gson gson = new Gson();
		String type=request.getParameter("type");								//获取下钻的类型
		String code=request.getParameter("code");								//获取编码
		
		if (type.equals("pp")) {
			OrderTableList = getTradeInfoBrand("dw",code);		
		}else if (type.equals("jl")) {
			OrderTableList = getTradeInfoPrice("dw",code);
		}else {
			OrderTableList = getOrderInfo("dw");
		}
		
		String s = gson.toJson(OrderTableList);
		log.info("先行指标订单模块表格查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	
	
	public static void main(String[] args) {
		for(int i=0;i<26;i++){
			System.out.println(i+".");
		}
	}
}
