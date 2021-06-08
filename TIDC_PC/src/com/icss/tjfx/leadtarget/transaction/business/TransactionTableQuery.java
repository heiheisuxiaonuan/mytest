package com.icss.tjfx.leadtarget.transaction.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.leadtarget.transaction.vo.TransactionTableDataVO;
/**
 * 先行指标交易查询方法类
 * @author lcx
 *
 */
public class TransactionTableQuery extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(TransactionTableQuery.class);
	
	/**
	 * 获取先行指标交易表格查询sql
	 * @return 
	 */
	public static String getSqlTransaction(){
		StringBuffer sql = new StringBuffer();
		
		/*sql.append("SELECT                                                  ");
		sql.append("		SUBSTR(M.C_DATE,6 ,2) AS M                                         ");
		sql.append("		,TO_CHAR(DECIMAL(ROUND(SUM(HDL)*1.0000/50000, 1), 16, 2),'999990.99') AS HDL             ");
		sql.append("		,TO_CHAR(DECIMAL(ROUND(SUM(XYL)*1.0000/50000, 1), 16, 2),'999990.99') AS XYL                                         ");
		sql.append("		,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(HDL),0,0,NULL,0,SUM(XYL)*100.0000/SUM(HDL)), 2), 16, 2),'999990.99') AS HDL_SCHEDULE                     ");
		sql.append("		,TO_CHAR(DECIMAL(ROUND(SUM(HTL)*1.0000/50000, 1), 16, 2),'999990.99')   AS HTL                             ");
		sql.append("		,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(XYL),0,0,NULL,0,SUM(HTL)*100.0000/SUM(XYL)), 2), 16, 2),'999990.99') AS HTL_RESOLVE_SCHEDULE                 ");
		sql.append("		,TO_CHAR(DECIMAL(ROUND(SUM(ZYZL)*1.0000/50000, 1), 16, 2),'999990.99')  AS ZYZL                           ");
		sql.append("		,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(HTL),0,0,NULL,0,sum(ZYZL)*100.0000/sum(HTL)), 2), 16, 2),'999990.99') AS HTL_RESOLVE_progress          ");
		*/
		sql.append(" 	SELECT																							");
		sql.append(" 	SUBSTR(M.C_DATE,6 ,2) AS M																				");
		sql.append(" 	,CASE WHEN SUM(HDL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(HDL)*1.0000/50000, 1), 16, 2),'999990.99') END AS HDL  									");
		sql.append(" 	,CASE WHEN SUM(XYL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(XYL)*1.0000/50000, 1), 16, 2),'999990.99') END AS XYL  									");
		sql.append(" 	,CASE WHEN SUM(HDL)=0 AND SUM(XYL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(HDL),0,0,NULL,0,SUM(XYL)*100.0000/SUM(HDL)), 2), 16, 2),'999990.99') END AS HDL_SCHEDULE    		");
		sql.append(" 	,CASE WHEN SUM(HTL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(HTL)*1.0000/50000, 1), 16, 2),'999990.99') END   AS HTL  									");
		sql.append(" 	,CASE WHEN SUM(XYL)=0 AND SUM(HTL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(XYL),0,0,NULL,0,SUM(HTL)*100.0000/SUM(XYL)), 2), 16, 2),'999990.99') END AS HTL_RESOLVE_SCHEDULE 	");
		sql.append(" 	,CASE WHEN SUM(ZYZL)=0 or SUM(ZYZL) is null THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(ZYZL)*1.0000/50000, 1), 16, 2),'999990.99') END  AS ZYZL 									");
		sql.append(" 	,CASE WHEN (SUM(HTL)=0 AND SUM(ZYZL)=0) or sum(ZYZL) is null THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(HTL),0,0,NULL,0,sum(ZYZL)*100.0000/sum(HTL)), 2), 16, 2),'999990.99') END AS HTL_RESOLVE_progress 	");
		sql.append("		FROM NICK_C_REPORT4 K,(select              ");
		sql.append("substr(char(c_date),1,7) AS Y                  ");
		sql.append(",max(c_date) AS C_DATE                         ");
		sql.append(" from                                          ");
		sql.append(" nick_c_report4                                ");
		sql.append(" where                                         ");
		sql.append(" c_date>=(SELECT VARCHAR_FORMAT(current timestamp - (MONTH (current timestamp) -1 )months-(DAY (current timestamp) -1 )days,'yyyy-mm-dd')                  ");
		sql.append("FROM sysibm.sysdummy1)            AND C_DATE<>(current DATE)     ");
		sql.append(" group by substr(char(c_date),1,7)                               ");
		sql.append(" order by substr(char(c_date),1,7)                               ");
		sql.append(" )M                                                              ");
		sql.append("		WHERE                                                        ");
		sql.append("			1=1                                                        ");
		sql.append("			AND K.C_DATE=M.C_DATE                                      ");
		sql.append("			AND K.C_DATE>=(SELECT VARCHAR_FORMAT(current timestamp - (MONTH (current timestamp) -1 )months-(DAY (current timestamp) -1 )days,'yyyy-mm-dd')       ");
		sql.append("FROM sysibm.sysdummy1)                                        ");
		sql.append("		GROUP BY SUBSTR(M.C_DATE,6 ,2)                            ");
		sql.append("		                                               ");
		sql.append("		ORDER BY NVL(SUBSTR(M.C_DATE,6 ,2),'00')                  ");
		sql.append("		WITH UR                                                  ");
		
		log.info("先行指标交易初始表格查询sql为："+sql);
		return sql.toString();
	}
	
	
	
	/**
	 * 查询先行指标交易初始表格数据信息
	 * @param dbName
	 * @return List
	 */
	public List<TransactionTableDataVO> getTransactionInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<TransactionTableDataVO> orderInfoList = new ArrayList<TransactionTableDataVO>();
		try {
			sql = getSqlTransaction();
			orderInfoList = dbbean.executeQuery(sql, TransactionTableDataVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询先行指标交易初始表格数据信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询先行指标交易初始表格数据信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return orderInfoList;
	}
	
	/**
	 * 获取先行指标交易品牌下钻表格查询sql
	 * @param code 
	 * @return 
	 */
	public static String getSqlBrand(String code){
		StringBuffer sql = new StringBuffer();

		/*sql.append("SELECT                                                              ");
		sql.append("		NVL(MPLATE,'合计') AS  MPLATE                                     ");
		sql.append("		,'-' AS HDL             ");
		sql.append("		,NVL(TO_CHAR(DECIMAL(ROUND(SUM(XYL)*1.0000/50000, 1), 16, 2),'999990.99'),0) AS XYL                                ");
		sql.append("		,'-' AS HDL_SCHEDULE                     ");
		sql.append("		,NVL(TO_CHAR(DECIMAL(ROUND(SUM(HTL)*1.0000/50000, 1), 16, 2),'999990.99'),0)   AS HTL                              ");
		sql.append("		,NVL(TO_CHAR(DECIMAL(ROUND(DECODE(SUM(XYL),0,0,NULL,0,SUM(HTL)*100.0000/SUM(XYL)), 2), 16, 2),'999990.99'),0) AS HTL_RESOLVE_SCHEDULE    ");
		sql.append("		,NVL(TO_CHAR(DECIMAL(ROUND(SUM(ZYZL)*1.0000/50000, 1), 16, 2),'999990.99'),0)  AS ZYZL          ");
		sql.append("		,NVL(TO_CHAR(DECIMAL(ROUND(DECODE(SUM(HTL),0,0,NULL,0,SUM(ZYZL)*100.0000/SUM(HTL)), 2), 16, 2),'999990.99'),0) AS HTL_RESOLVE_progress   ");
		*/
		sql.append(" 	SELECT																							");
		sql.append(" 	NVL(MPLATE,'合计') AS  MPLATE																				");
		sql.append(" 	,'-' AS HDL																						");
		sql.append(" 	,NVL(CASE WHEN SUM(XYL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(XYL)*1.0000/50000, 1), 16, 2),'999990.99')END,'-') AS XYL  									");
		sql.append(" 	,'-' AS HDL_SCHEDULE																					");
		sql.append(" 	,NVL(CASE WHEN SUM(HTL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(HTL)*1.0000/50000, 1), 16, 2),'999990.99')END,'-')   AS HTL  									");
		sql.append(" 	,NVL(CASE WHEN SUM(XYL)=0 AND SUM(HTL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(XYL),0,0,NULL,0,SUM(HTL)*100.0000/SUM(XYL)), 2), 16, 2),'999990.99')END,'-')  AS HTL_RESOLVE_SCHEDULE 	");
		sql.append(" 	,NVL(CASE WHEN SUM(ZYZL)=0 or SUM(ZYZL) is null THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(ZYZL)*1.0000/50000, 1), 16, 2),'999990.99')END,'-')  AS ZYZL 									");
		sql.append(" 	,NVL(CASE WHEN (SUM(HTL)=0 AND SUM(ZYZL)=0) or sum(ZYZL) is null THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(HTL),0,0,NULL,0,SUM(ZYZL)*100.0000/SUM(HTL)), 2), 16, 2),'999990.99')END,'-')  AS HTL_RESOLVE_progress 	");
		sql.append("		FROM NICK_C_REPORT1 K,(select       ");
		sql.append("substr(char(c_date),1,7) AS Y           ");
		sql.append(",max(c_date) AS C_DATE                  ");
		sql.append(" from                                   ");
		sql.append(" nick_c_report1                         ");
		sql.append(" where                                  ");
		sql.append(" c_date>=(SELECT VARCHAR_FORMAT(current timestamp - (MONTH (current timestamp) -1 )months-(DAY (current timestamp) -1 )days,'yyyy-mm-dd')             ");
		sql.append("FROM sysibm.sysdummy1)                                 ");
		sql.append(" group by substr(char(c_date),1,7)                     ");
		sql.append(" order by substr(char(c_date),1,7)                     ");
		sql.append(" )M  		WHERE                                          ");
		sql.append("				1=1                                            ");
		sql.append("			AND K.C_DATE=M.C_DATE                            ");
		sql.append("			AND SUBSTR(M.C_DATE,6 ,2)='").append(code).append("'			              ");
		sql.append("		AND XYL <> 0                                    ");
		sql.append("		GROUP BY MPLATE                                    ");
		sql.append("		WITH ROLLUP                                        ");
		sql.append("		                                                   ");
		sql.append("		ORDER BY NVL(XYL,0) DESC                           ");
		sql.append("with ur                                              ");
		
		log.info("获取先行指标交易品牌下钻表格查询sql为："+sql);
		return sql.toString();
	}
	/**
	 * 查询先行指标交易品牌下钻表格数据信息
	 * @param dbName
	 * @return List
	 */
	public List<TransactionTableDataVO> getTransactionBrand(String dbName,String code){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<TransactionTableDataVO> tradeInfoList = new ArrayList<TransactionTableDataVO>();
		try {
			sql = getSqlBrand(code);
			tradeInfoList = dbbean.executeQuery(sql, TransactionTableDataVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询先行指标交易品牌下钻表格数据信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询先行指标交易品牌下钻表格数据信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return tradeInfoList;
	}
	/**
	 * 查询先行指标交易价类下钻表格数据信息
	 * @param code 
	 * @param dbName
	 * @return List
	 */
	public static String getSqlPrice(String code){
		StringBuffer sql = new StringBuffer();
		/*sql.append("SELECT             ");
		sql.append("		NVL((LEFT(CLASS,2)||'类烟'),'合计') AS MPLATE            ");
		sql.append("		,'-' AS HDL           ");
		sql.append("		,NVL(TO_CHAR(DECIMAL(ROUND(SUM(XYL)*1.0000/50000, 1), 16, 2),'999990.99'),0) AS XYL          ");
		sql.append("		,'-' AS HDL_SCHEDULE         ");
		sql.append("		,NVL(TO_CHAR(DECIMAL(ROUND(SUM(HTL)*1.0000/50000, 1), 16, 2),'999990.99'),0)   AS HTL        ");
		sql.append("		,NVL(TO_CHAR(DECIMAL(ROUND(DECODE(SUM(XYL),0,0,NULL,0,SUM(HTL)*100.0000/SUM(XYL)), 2), 16, 2),'999990.99'),0) AS HTL_RESOLVE_SCHEDULE   ");
		sql.append("		,NVL(TO_CHAR(DECIMAL(ROUND(SUM(ZYZL)*1.0000/50000, 1), 16, 2),'999990.99'),0) AS ZYZL                                                   ");
		sql.append("		,NVL(TO_CHAR(DECIMAL(ROUND(DECODE(SUM(HTL),0,0,NULL,0,SUM(ZYZL)*100.0000/SUM(HTL)), 2), 16, 2),'999990.99'),0) AS HTL_RESOLVE_progress  ");
		*/
		sql.append(" 	SELECT																								");
		sql.append(" 	NVL((LEFT(K.CLASS,2)||'类'),'合计') AS MPLATE 																		");
		sql.append(" 	,'-' AS HDL																							");
		sql.append(" 	,CASE WHEN SUM(XYL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(XYL)*1.0000/50000, 1), 16, 2),'999990.99')END AS XYL  										");
		sql.append(" 	,'-' AS HDL_SCHEDULE																						");
		sql.append(" 	,CASE WHEN SUM(HTL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(HTL)*1.0000/50000, 1), 16, 2),'999990.99')END   AS HTL  										");
		sql.append(" 	,CASE WHEN SUM(XYL)=0 AND SUM(HTL)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(XYL),0,0,NULL,0,SUM(HTL)*100.0000/SUM(XYL)), 2), 16, 2),'999990.99')END  AS HTL_RESOLVE_SCHEDULE 		");
		sql.append(" 	,CASE WHEN SUM(ZYZL)=0 or SUM(ZYZL) is null THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SUM(ZYZL)*1.0000/50000, 1), 16, 2),'999990.99')END  AS ZYZL 										");
		sql.append(" 	,CASE WHEN (SUM(HTL)=0 AND SUM(ZYZL)=0) or sum(ZYZL) is null THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SUM(HTL),0,0,NULL,0,SUM(ZYZL)*100.0000/SUM(HTL)), 2), 16, 2),'999990.99')END  AS HTL_RESOLVE_progress 		");
		sql.append("		FROM NICK_C_REPORT2 K,           ");
		sql.append("(                                    ");
		sql.append("		select                           ");
		sql.append("substr(char(c_date),1,7) AS Y        ");
		sql.append(",max(c_date) AS C_DATE               ");
		sql.append(" from                                ");
		sql.append(" nick_c_report1                      ");
		sql.append(" where                               ");
		sql.append(" c_date>=(                           ");
		sql.append(" SELECT VARCHAR_FORMAT(current timestamp - (MONTH (current timestamp) -1 )months-(DAY (current timestamp) -1 )days,'yyyy-mm-dd')            ");
		sql.append("FROM sysibm.sysdummy1                    ");
		sql.append(" )                                       ");
		sql.append(" group by substr(char(c_date),1,7)       ");
		sql.append(" order by substr(char(c_date),1,7)       ");
		sql.append(" )M                                      ");
		sql.append("		WHERE 1=1                            ");
		sql.append("			AND K.C_DATE = M.C_DATE            ");
		sql.append("			AND SUBSTR(M.C_DATE,6 ,2)='01'	    ");
		sql.append("		GROUP BY CLASS                       ");
		sql.append("		WITH ROLLUP                                       ");
		sql.append("order by DECODE(LEFT(CLASS,2),'一',1,'二',2,'三',3,'四',4,'五',5,0)             ");
		sql.append("with ur         ");
		log.info("获取先行指标交易价类下钻表格查询sql为："+sql);
		return sql.toString();
	}
	public List<TransactionTableDataVO> getTransactionPrice(String dbName,String code){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<TransactionTableDataVO> tradeInfoList = new ArrayList<TransactionTableDataVO>();
		try {
			sql = getSqlPrice(code);
			tradeInfoList = dbbean.executeQuery(sql, TransactionTableDataVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询先行指标交易价类下钻表格数据信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询先行指标交易价类下钻表格数据信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return tradeInfoList;
	}

	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<TransactionTableDataVO> TransactionTableList=null;
		Gson gson = new Gson();
		String type=request.getParameter("type");								//获取下钻的类型
		String code=request.getParameter("code");								//获取编码
		
		if (type.equals("pp")) {
			TransactionTableList = getTransactionBrand("dw",code);		
		}else if (type.equals("jl")) {
			TransactionTableList = getTransactionPrice("dw",code);
		}else {
			TransactionTableList = getTransactionInfo("dw");
		}
		
		String s = gson.toJson(TransactionTableList);
		log.info("先行指标交易模块表格查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
}
