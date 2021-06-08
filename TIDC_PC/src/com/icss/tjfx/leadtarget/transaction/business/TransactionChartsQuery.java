package com.icss.tjfx.leadtarget.transaction.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.leadtarget.transaction.vo.TransactionChartsDataVO;
import com.icss.tjfx.leadtarget.transaction.vo.TransactionTableDataVO;
/**
 * 先行指标交易查询方法类
 * @author lcx
 *
 */
public class TransactionChartsQuery extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(TransactionChartsQuery.class);
	
	/**
	 * 协议成交量占比
	 * @param code
	 * @return
	 */
	public static String getSqlXYL(){
		StringBuffer sql = new StringBuffer();

		sql.append("	SELECT TO_CHAR(DECIMAL(ROUND(SUM(XYL)*1.0000/50000, 2), 16, 2),'999990.99') AS XYLBQ          ");
		sql.append("	                                                 ");
		sql.append("	FROM NICK_C_REPORT3                              ");
		sql.append("	WHERE C_DATE=date(current timestamp - 1 days )   ");
		sql.append("	GROUP BY C_DATE                                  ");
		sql.append("	UNION ALL                                        ");
		sql.append("	SELECT TO_CHAR(DECIMAL(ROUND(SUM(XYL)*1.0000/50000, 2), 16, 2),'999990.99') AS XYLTQ          ");
		sql.append("	FROM NICK_C_REPORT3                                     ");
		sql.append("	WHERE C_DATE=date(current timestamp-1 year - 1 days )   ");
		sql.append("	GROUP BY C_DATE                                         ");
		log.info("获取先行指标交易协议成交量占比查询sql为："+sql);
		return sql.toString();
	}
	/**
	 * 合同分解进度
	 * @param code
	 * @return
	 */
	public static String getSqlHTL(){
		StringBuffer sql = new StringBuffer();
		sql.append("		SELECT                                     ");
		sql.append("	TO_CHAR(DECIMAL(ROUND(DECODE(SUM(XYL),0,0,NULL,0,SUM(HTL)*100.0000/SUM(XYL)), 2), 16, 2),'999990.99') AS HTL_RESOLVE_SCHEDULE_FJ      ");
		sql.append("	FROM NICK_C_REPORT1                                     ");
		sql.append("	WHERE C_DATE=date(current timestamp - 1 days )          ");
		sql.append("	with ur                                                ");
		log.info("获取先行指标交易合同分解进度查询sql为："+sql);
		return sql.toString();
	}
	
	/**
	 * 合同执行进度
	 * @param code
	 * @return
	 */
	public static String getSqlZXJD(){
		StringBuffer sql = new StringBuffer();
		sql.append("	SELECT                                                  ");
		sql.append("	case when SUM(ZYZL) is null or SUM(ZYZL)=0 then 0 else TO_CHAR(DECIMAL(ROUND(DECODE(SUM(HTL),0,0,NULL,0,sum(ZYZL)*100.0000/sum(HTL)), 2), 16, 2),'999990.99') end AS HTL_RESOLVE_SCHEDULE_ZX   ");
		sql.append("	FROM NICK_C_REPORT1                                    ");
		sql.append("	WHERE C_DATE=date(current timestamp - 1 days )         ");
		sql.append("	with ur                                               ");
		log.info("获取先行指标交易合同执行进度查询sql为："+sql);
		return sql.toString();
	}
	
	
	public List<TransactionChartsDataVO> getTransactionData(){
		List<TransactionChartsDataVO> TransactionChartsList=new ArrayList<TransactionChartsDataVO>();
		DBBeanBase dbbean = new DBBeanBase("dw");
		String sql = "";
		String sql1="";
		String sql2="";
		TransactionChartsDataVO chartsDataVO2=new TransactionChartsDataVO();
		TransactionChartsDataVO chartsDataVO3=new TransactionChartsDataVO();
		
		try {
			sql = getSqlXYL();
			sql1=getSqlHTL();
			sql2=getSqlZXJD();
			TransactionChartsList = dbbean.executeQuery(sql, TransactionChartsDataVO.class.getName());
			chartsDataVO2 = (TransactionChartsDataVO)dbbean.executeQuerySingle(sql1, TransactionChartsDataVO.class.getName()); 
			chartsDataVO3 = (TransactionChartsDataVO)dbbean.executeQuerySingle(sql2, TransactionChartsDataVO.class.getName()); 
			TransactionChartsList.add(chartsDataVO2);
			TransactionChartsList.add(chartsDataVO3);
		} catch (Exception e) {
			log.info("*************************");
			log.info("先行指标交易图表sql执行异常:"+e);
			log.info("*************************");
			log.error("先行指标交易图表sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return TransactionChartsList;
	}
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<TransactionChartsDataVO> TransactionChartsList=getTransactionData();
		
		Gson gson = new Gson();
		String s = gson.toJson(TransactionChartsList);
		log.info("先先行指标交易图表查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
}
