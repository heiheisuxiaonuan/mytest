package com.icss.tjfx.trend.bussiness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.util.StringUtil;
import com.icss.tjfx.trend.vo.ModelVO;
import com.icss.tjfx.trend.vo.PopScatterVO;

/**
 * 走势图品牌散点图业务类
 * 
 * @author lkt
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class PopScatter extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(PopScatter.class);	
	
	//走势图品牌散点图业务
	public static String getSql(){
		/*StringBuffer sql = new StringBuffer();
		sql.append("SELECT                       ");
		sql.append("	Y       AS date            ");
		sql.append("	,CIG_CODE   AS code   	   ");
		sql.append("	,CIG_NAME 	AS name 		   ");
		sql.append("	,SELL_NUM   AS value       ");
		sql.append("FROM                         ");
		sql.append("(                            ");
		sql.append("	SELECT                     ");
		sql.append("		Y                        ");
		sql.append("		,P.C_CIG		AS C_CIG     ");
		sql.append("		,DECIMAL(ROUND(SUM(SELL_NUM)*1.0000/50000,2), 16,2) AS SELL_NUM    ");
		sql.append("	FROM               ");
		sql.append("		(                ");
		sql.append("			SELECT         ");
		sql.append("				Y            ");
		sql.append("				,C_BRAND     ");
		sql.append("				,SUM(PRINT_NUM1 - RBACK_NUM1) AS SELL_NUM        ");
		sql.append("			FROM NICK_K_TJBS_Y_ALL     ");
		sql.append("			WHERE C_CLASS <> '06'      ");
		sql.append("			GROUP BY Y, C_BRAND        ");
		sql.append("		) K       ");
		sql.append("		,         ");
		sql.append("		(         ");
		sql.append("			SELECT C_BRAND, C_CIG, CIG_MARKNAME      ");
		sql.append("			FROM VIEW_NICK_CIG_PROPERTY              ");
		sql.append("		)P                                         ");
		sql.append("	WHERE 1=1                                    ");
		sql.append("		AND K.C_BRAND = P.C_BRAND                  ");
		sql.append("	GROUP BY Y, P.C_CIG                          ");
		sql.append(") K, DW_T_TJFX_BRAND_DOUBLE15 D                ");
		sql.append("WHERE K.C_CIG = D.CIG_CODE                     ");
		sql.append("ORDER BY Y           ");
		sql.append("WITH UR             ");*/
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT Y AS date ,CIG_CODE AS code ,CIG_NAME AS name ,SELL_NUM AS value ");
		sql.append(" FROM ( SELECT Y ,P.C_CIG AS C_CIG , ");
		sql.append(" DECIMAL(ROUND(SUM(SELL_NUM)*1.0000/50000,2), 16,2) AS SELL_NUM ");
		sql.append(" FROM (   SELECT Y ,C_CIG ,SUM(PRINT_NUM1 - RBACK_NUM1) AS SELL_NUM  ");
		sql.append(" FROM NICK_K_TJBS_Y_ALL NK,NICK_TJYY_CIGARETTE SC  ");
		sql.append("		WHERE NK.C_BRAND = SC.cig_codebar and D_YEAR>='2018' AND is_bsnx='1'  ");
		sql.append("			AND y NOT in(SELECT DISTINCT y FROM N_K_TJIB_GRAPH)  ");
		sql.append("  GROUP BY Y, C_CIG  ");
		sql.append(" UNION ALL  ");
		sql.append(" (SELECT Y ,C_CIG ,SUM(xl) AS SELL_NUM FROM N_K_TJIB_GRAPH GROUP BY Y, C_CIG)  ");
		sql.append("  ) K , ");
		sql.append("   ( SELECT DISTINCT C_CIG, CIG_MARKNAME FROM VIEW_NICK_CIG_PROPERTY ) P  ");
		sql.append(" WHERE 1=1 AND K.C_CIG = P.C_CIG  ");
		sql.append(" GROUP BY Y, P.C_CIG ) K, ");
		sql.append(" DW_T_TJFX_BRAND_DOUBLE15 D ");
		sql.append(" WHERE K.C_CIG = D.CIG_CODE  ");
		sql.append(" ORDER BY Y WITH UR ");
		
		
		/*StringBuffer sql = new StringBuffer();
		sql.append("SELECT                       ");
		sql.append("	Y       AS date            ");
		sql.append("	,CIG_CODE   AS code   	   ");
		sql.append("	,CIG_NAME 	AS name 		   ");
		sql.append("	,SELL_NUM   AS value       ");
		sql.append("FROM                         ");
		sql.append("(                            ");
		sql.append("	SELECT                     ");
		sql.append("		Y                        ");
		sql.append("		,P.C_CIG		AS C_CIG     ");
		sql.append("		,DECIMAL(ROUND(SUM(SELL_NUM)*1.0000/50000,2), 16,2) AS SELL_NUM    ");
		sql.append("	FROM               ");
		sql.append("		(                ");
		sql.append("			SELECT         ");
		sql.append("				Y            ");
		sql.append("				,C_BRAND     ");
		sql.append("				,SUM(PRINT_NUM1 - RBACK_NUM1) AS SELL_NUM        ");
		sql.append("			FROM NICK_K_TJBS_Y_ALL NK,NICK_TJYY_CIGARETTE SC     ");
		sql.append("			WHERE NK.C_BRAND = SC.cig_codebar      ");
		sql.append("			AND ((D_YEAR<='2017'      ");
		sql.append("			AND CIG_PRODUCTTYPE NOT in('05','06'))      ");
		sql.append("			OR (D_YEAR>='2018'      ");
		sql.append("			AND is_bsnx='1'))      ");
		sql.append("			GROUP BY Y, C_BRAND        ");
		sql.append("		) K       ");
		sql.append("		,         ");
		sql.append("		(         ");
		sql.append("			SELECT C_BRAND, C_CIG, CIG_MARKNAME      ");
		sql.append("			FROM VIEW_NICK_CIG_PROPERTY              ");
		sql.append("		)P                                         ");
		sql.append("	WHERE 1=1                                    ");
		sql.append("		AND K.C_BRAND = P.C_BRAND                  ");
		sql.append("	GROUP BY Y, P.C_CIG                          ");
		sql.append(") K, DW_T_TJFX_BRAND_DOUBLE15 D                ");
		sql.append("WHERE K.C_CIG = D.CIG_CODE                     ");
		sql.append("ORDER BY Y           ");
		sql.append("WITH UR             ");*/
		
		log.info("走势图品牌散点图业务数据处理sql: "+sql.toString());
		return sql.toString();
	}
	
	public ModelVO getPopScaData(){
		DBBeanBase dbbean = new DBBeanBase("dw");
		log.info("传入参数： dbName:"+"dw");
		String sql = "";
		List list = new ArrayList();
		try {  
			sql = getSql();
			list = dbbean.executeQuery(sql, PopScatterVO.class.getName()); 
		}catch (Exception e) {
			log.info("*************************");
			log.info("走势图品牌散点图业务sql执行异常:"+e);
			log.info("*************************");
			log.error("走势图品牌散点图业务sql执行异常", e);
		}finally{
			dbbean.close();
		}
		List yearList = new ArrayList();
		ModelVO mvo = new ModelVO();
		//设置所有品牌数据
		mvo.setPpList(list);
		//设置年份list
		yearList = getYearList(list);
		mvo.setYearList(yearList);
		return mvo;
	}
	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		ModelVO mvo = getPopScaData();
		String result = StringUtil.getJson(mvo);
		log.info("走势图品牌散点图业务查询结果："+result);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", result);
		
		return request;
	}
	
	
	private List getYearList(List list) {
		ArrayList tempList1 = new ArrayList();
		//循环取出所有的年  放在一个临时集合中
		for (int i = 0; i < list.size(); i++) {
			PopScatterVO vo = (PopScatterVO)list.get(i);
			tempList1.add(vo.getDate());
		}
		//把存有所有年份的list集合去重复  得到一个无序的set集合
		HashSet<String> hs = new HashSet<String>(tempList1);
		//把set集合转成list集合 并且排序
		ArrayList list2 = new ArrayList(hs);
		Collections.sort(list2); 
		//返回最终处理好的不重复年份list集合
		return list2;
	}
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add("2012");
		list.add("2012");
		list.add("2013");
		list.add("2013");
		list.add("2014");
		list.add("2014");
		list.add("2015");
		list.add("2015");
		list.add("2016");
		list.add("2016");
		HashSet<String> hs = new HashSet<String>(list);
		ArrayList list2 = new ArrayList(hs);
		Collections.sort(list2);  
		System.out.println(list2.toString());
	}
}
