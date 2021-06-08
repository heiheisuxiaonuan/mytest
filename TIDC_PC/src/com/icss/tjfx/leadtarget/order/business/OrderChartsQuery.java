package com.icss.tjfx.leadtarget.order.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.leadtarget.order.vo.OrderChartsDataVO;
/**
 * 先行指标查询方法类
 * @author lcx
 *
 */
public class OrderChartsQuery extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(OrderChartsQuery.class);
	
	/**
	 * 满足率占比
	 * @param code
	 * @return
	 */
	public static String getSqlProportion(){
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT                                                                                                                                             ");
		sql.append("		TO_CHAR(DECIMAL(ROUND(DECODE(CC_DEMAND_AMOUNT,0,0,NULL,0,CC_ORDER_AMOUNT*100.0000/CC_DEMAND_AMOUNT), 2), 16, 2),'999990.99') AS proportion     ");
		sql.append("		FROM(                  ");
		sql.append("SELECT                     ");
		sql.append("	TO_CHAR(DECIMAL(ROUND(SUM(CC_ORDER_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') AS CC_ORDER_AMOUNT 						            ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') AS 	CC_DEMAND_AMOUNT					     ");
		sql.append("	FROM B_ORDER_M_A_brand                                      ");
		sql.append("	WHERE Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-1)  else  (YEAR(CURRENT TIMESTAMP)) end ))                               ");
		log.info("获取先行指标订单满足率占比查询sql为："+sql);
		return sql.toString();
	}
	/**
	 * 订单需求量
	 * @param code
	 * @return
	 */
	public static String getSqlDemand(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT       ");
		sql.append("			TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT)*1.0000/50000, 2), 16, 2),'999990.99') AS 	CC_DEMAND_AMOUNT												     ");
		sql.append("			,TO_CHAR(DECIMAL(ROUND(SUM(CC_DEMAND_AMOUNT_L)*1.0000/50000, 2), 16, 2),'999990.99') AS	CC_DEMAND_AMOUNT_L										     ");
		sql.append("			FROM B_ORDER_M_A_brand                          ");
		sql.append("			WHERE Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-1)  else  (YEAR(CURRENT TIMESTAMP)) end )                ");
		log.info("获取先行指标订单需求量查询sql为："+sql);
		return sql.toString();
	}
	
	public List<OrderChartsDataVO> getOrderDate(){
		List<OrderChartsDataVO> OrderChartsList=new ArrayList<OrderChartsDataVO>();
		DBBeanBase dbbean = new DBBeanBase("dw");
		String sql = "";
		String sql1="";
		OrderChartsDataVO chartsDataVO=new OrderChartsDataVO();
		OrderChartsDataVO chartsDataVO2=new OrderChartsDataVO();
		
		try {
			sql = getSqlDemand();
			sql1=getSqlProportion();
			chartsDataVO = (OrderChartsDataVO)dbbean.executeQuerySingle(sql, OrderChartsDataVO.class.getName()); 
			chartsDataVO2 = (OrderChartsDataVO)dbbean.executeQuerySingle(sql1, OrderChartsDataVO.class.getName()); 
			OrderChartsList.add(chartsDataVO);
			OrderChartsList.add(chartsDataVO2);
		} catch (Exception e) {
			log.info("*************************");
			log.info("先行指标订单图表sql执行异常:"+e);
			log.info("*************************");
			log.error("先行指标订单图表sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return OrderChartsList;
	}
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<OrderChartsDataVO> OrderChartsList=getOrderDate();
		
		Gson gson = new Gson();
		String s = gson.toJson(OrderChartsList);
		log.info("先行指标订单图表查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
}
