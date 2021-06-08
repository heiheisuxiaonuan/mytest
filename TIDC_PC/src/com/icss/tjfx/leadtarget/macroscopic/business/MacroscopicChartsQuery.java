package com.icss.tjfx.leadtarget.macroscopic.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.leadtarget.macroscopic.vo.MacroscopicCharsDataVO;
/**
 * 先行指标宏观查询方法类
 * @author lcx
 *
 */
public class MacroscopicChartsQuery extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(MacroscopicChartsQuery.class);
	
	/**
	 * 柱图本期值
	 * @param code
	 * @return
	 */
	public static String getSqlBQ(){
		StringBuffer sql = new StringBuffer();
		sql.append("select         ");
		sql.append("	DECIMAL(sum(GDP),16,2) AS GDP,      ");
		sql.append("	DECIMAL(sum(GNP),16,2) AS GNP,      ");
		sql.append("	DECIMAL(sum(CONS_SALE_ATM),16,2) AS CONSSALEATM                         ");
		sql.append("from MA_T_MACRO_ECONOMY_Y_M_ALL     ");
		sql.append("where   Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-1)  else  (YEAR(CURRENT TIMESTAMP)) end ) and M <= (case MONTH(CURRENT TIMESTAMP) when 1 then 12 else MONTH(CURRENT TIMESTAMP)-1 end) ");
		sql.append("with ur    ");
		log.info("获取先行指标宏观柱图本期查询sql为："+sql);
		return sql.toString();
	}
	/**
	 * 柱图同期值
	 * @param code
	 * @return
	 */
	public static String getSqlTQ(){
		StringBuffer sql = new StringBuffer();
		sql.append("select     ");
		sql.append("	DECIMAL(sum(GDP),16,2) AS GDPTQ,     ");
		sql.append("	DECIMAL(sum(GNP),16,2) AS GNPTQ,     ");
		sql.append("	DECIMAL(sum(CONS_SALE_ATM),16,2) AS CONSSALEATMTQ       ");
		sql.append("from MA_T_MACRO_ECONOMY_Y_M_ALL   ");
		sql.append("where   Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-2)  else  (YEAR(CURRENT TIMESTAMP)-1) end ) and M <= (select    max(m)   from MA_T_MACRO_ECONOMY_Y_M_ALL     where   Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-1)  else  (YEAR(CURRENT TIMESTAMP)) end ) )  ");
		sql.append("with ur        ");
		log.info("获取先行指标宏观柱图同期查询sql为："+sql);
		return sql.toString();
	}
	
	public List<MacroscopicCharsDataVO> getMacroDate(){
		List<MacroscopicCharsDataVO> MacroscopicChartsList=new ArrayList<MacroscopicCharsDataVO>();
		DBBeanBase dbbean = new DBBeanBase("dw");
		String sql = "";
		String sql1="";
		MacroscopicCharsDataVO chartsDataVO2=new MacroscopicCharsDataVO();
		MacroscopicCharsDataVO chartsDataVO3=new MacroscopicCharsDataVO();
		
		try {
			sql = getSqlBQ();
			sql1=getSqlTQ();
			chartsDataVO2 = (MacroscopicCharsDataVO)dbbean.executeQuerySingle(sql, MacroscopicCharsDataVO.class.getName()); 
			chartsDataVO3 = (MacroscopicCharsDataVO)dbbean.executeQuerySingle(sql1, MacroscopicCharsDataVO.class.getName()); 
			MacroscopicChartsList.add(chartsDataVO2);
			MacroscopicChartsList.add(chartsDataVO3);
		} catch (Exception e) {
			log.info("*************************");
			log.info("获取先行指标宏观sql执行异常:"+e);
			log.info("*************************");
			log.error("获取先行指标宏观sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return MacroscopicChartsList;
	}
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<MacroscopicCharsDataVO> MacroscopicChartsList=getMacroDate();
		Gson gson = new Gson();
		String s = gson.toJson(MacroscopicChartsList);
		log.info("先行指标订单图表查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
}
