package com.icss.tjfx.trend.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.util.StringUtil;
import com.icss.tjfx.trend.vo.ModelVO;
import com.icss.tjfx.trend.vo.TaxLineVO;
import com.icss.tjfx.trend.vo.TaxMaxMin;

/**
 * 走势图税利折线图业务类
 * 
 * @author lkt
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class TaxLine extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(TaxLine.class);	
	
	//走势图税利折线图sql
	public String getSql(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT D.Y AS date , ");
		sql.append(" DECIMAL(ROUND(NVL(SUM(PRO_NUM), 0),2),16,2) AS cl , ");
		sql.append(" DECIMAL(ROUND(NVL(SUM(SELL_NUM), 0),2),16,2) AS xl , ");
		sql.append(" DECIMAL(ROUND(NVL(SUM(TAX_PROFIT), 0),2),16,2) AS sl  ");
		sql.append(" FROM ( SELECT DISTINCT Y FROM DC_DATE WHERE Y BETWEEN 1982 AND YEAR(CURRENT DATE-1 YEARS) ) D  ");
		sql.append(" LEFT JOIN ( SELECT Y ,SUM(cl)*1.0000/50000 AS PRO_NUM ,SUM(xl)*1.0000/50000 AS SELL_NUM ,0 AS TAX_PROFIT  ");
		sql.append(" FROM N_K_TJIB_GRAPH  ");
		sql.append(" GROUP BY Y  ");
		sql.append(" UNION ALL SELECT Y ,0 AS PRO_NUM ,0 AS SELL_NUM ,IC_TAX_PROF AS TAX_PROFIT  ");
		sql.append(" from tax_line ) K ");
		sql.append(" ON D.Y = K.Y GROUP BY D.Y ORDER BY D.Y WITH UR  ");
		
		/*StringBuffer sql = new StringBuffer();
		sql.append("SELECT D.Y AS date , ");
		sql.append(" DECIMAL(ROUND(NVL(SUM(PRO_NUM), 0),2),16,2) AS cl , ");
		sql.append(" DECIMAL(ROUND(NVL(SUM(SELL_NUM), 0),2),16,2) AS xl , ");
		sql.append(" DECIMAL(ROUND(NVL(SUM(TAX_PROFIT), 0),2),16,2) AS sl  ");
		sql.append(" FROM ( SELECT DISTINCT Y FROM DC_DATE WHERE Y BETWEEN 2006 AND YEAR(CURRENT DATE-1 YEARS) ) D  ");
		sql.append(" LEFT JOIN ( SELECT Y ,SUM(cl)*1.0000/50000 AS PRO_NUM ,SUM(xl)*1.0000/50000 AS SELL_NUM ,0 AS TAX_PROFIT  ");
		sql.append(" FROM N_K_TJIB_GRAPH  ");
		sql.append(" GROUP BY Y  ");
		sql.append(" UNION ALL SELECT Y ,0 AS PRO_NUM ,0 AS SELL_NUM ,SUM(IC_TAX_PROF) AS TAX_PROFIT  ");
		sql.append(" from (values(2006,3063.8000),(2007,3978.6000),(2008,4521.3),(2009,5248.1),(2010,6074.8),(2011,7469.7600),(2012,8649.3882), ");
		sql.append(" (2013,9559.8600),(2014,10517.6000),(2015,11436.1041),(2016,10795.7795),(2017,11145.1037)) AS tmp(y,IC_TAX_PROF)  "); 
		sql.append(" GROUP BY Y ) K ");
		sql.append(" ON D.Y = K.Y GROUP BY D.Y ORDER BY D.Y WITH UR  ");
		*/
		
		
		/*sql.append("SELECT                                                                                    ");
		sql.append("	D.Y AS date							                                                          ");
		sql.append("	,DECIMAL(ROUND(NVL(SUM(PRO_NUM), 0),2),16,2) AS cl                          ");
		sql.append("	,DECIMAL(ROUND(NVL(SUM(SELL_NUM), 0),2),16,2) AS xl                      ");
		sql.append("	,DECIMAL(ROUND(NVL(SUM(TAX_PROFIT), 0),2),16,2) AS sl                   ");
		sql.append("FROM                                                                                      ");
		sql.append("(	SELECT DISTINCT Y FROM DC_DATE WHERE Y BETWEEN 2006 AND YEAR(CURRENT DATE-1 YEARS)      ");
		sql.append(") D                         ");
		sql.append("LEFT JOIN                   ");
		sql.append("	(                         ");
		sql.append("		SELECT                  ");
		sql.append("			Y                     ");
		sql.append("			,SUM(PRINTNUM1 - REJECTNUM1 + REPEATNUM1 - JUMPNUM1)*1.0000/50000 AS PRO_NUM        ");
		sql.append("			,0 AS SELL_NUM               ");
		sql.append("			,0 AS TAX_PROFIT             ");
		sql.append("		FROM NICK_K_TJIN_Y_ALL NK,NICK_TJYY_CIGARETTE SC         ");
		sql.append("		WHERE 1=1                      ");
		
		sql.append("			AND NK.C_BRAND = SC.cig_codebar          ");
		sql.append("			AND ((D_YEAR<='2010'            ");
		sql.append("			AND CIG_PRODUCTTYPE NOT in('05','06'))             ");
		sql.append("			OR (D_YEAR BETWEEN '2011' AND '2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1') )           ");
		
		sql.append("		GROUP BY Y                     ");
		sql.append("		UNION  ALL                     ");
		sql.append("		SELECT                         ");
		sql.append("			Y                            ");
		sql.append("			,0 AS PRO_NUM                ");
		sql.append("			,SUM(PRINT_NUM1 + SELF_SUPP_NUM1 - RBACK_NUM1)*1.0000/50000 AS SELL_NUM                            ");
		sql.append("			,0 AS TAX_PROFIT                 ");
		
		sql.append("		FROM NICK_K_TJBS_Y_ALL NK,NICK_TJYY_CIGARETTE SC            ");
		sql.append("		WHERE NK.C_BRAND = SC.cig_codebar                          ");
		sql.append("			AND ((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06'))  OR (D_YEAR>='2018' AND is_bsnx='1'))             ");
		
		sql.append("		GROUP BY Y                         ");
		sql.append("		UNION  ALL                        ");
		sql.append("		SELECT                             ");
		sql.append("			Y                                ");
		sql.append("			,0  AS PRO_NUM                   ");
		sql.append("			,0 AS SELL_NUM                   ");
		sql.append("			,SUM(IC_TAX_PROF) AS TAX_PROFIT                                                     ");
		sql.append("			from ( values(2006,3063.8000),(2007,3978.6000),(2008,4521.3),(2009,5248.1),(2010,6074.8),(2011,7469.7600),(2012,8649.3882),(2013,9559.8600),(2014,10517.6000),(2015,11436.1041),(2016,10795.7795),(2017,11145.1037)) AS tmp(y,IC_TAX_PROF) GROUP BY  Y  ) K                    ");
		sql.append("ON D.Y = K.Y             ");
		sql.append("GROUP BY D.Y             ");
		sql.append("ORDER BY D.Y             ");
		sql.append("WITH UR                 ");*/
		
		log.info("走势图税利折线图业务数据处理sql: "+sql.toString());
		return sql.toString();
	}
	
	//获取产销和税利最大最小值
	public String getMaxMin(){
		
		
		StringBuffer sql = new StringBuffer();
		//select y,ic_tax_prof from tax_line
		/*190102
		 * sql.append("SELECT DECIMAL(ROUND(MAX(max_PRODUCENUM),-3)+1000 ) AS cxMax , ");
		sql.append(" DECIMAL(ROUND(MIN(min_PRODUCENUM),-3)-1000 ) AS cxMin , ");
		sql.append(" DECIMAL(ROUND(MAX(IC_TAX_PROF),-3)+1000 ) AS slMax , ");
		sql.append(" DECIMAL(ROUND(MIN(IC_TAX_PROF),-3)-1000 ) AS slMin  ");
		sql.append(" FROM ( SELECT Y ,MAX(cl) AS max_PRODUCENUM ,MIN(cl) AS min_PRODUCENUM  ");
		sql.append(" FROM ( SELECT Y ,SUM(cl*1.0000/50000 ) AS cl FROM N_K_TJIB_GRAPH GROUP BY Y  ");
		sql.append(" UNION ALL  ");
		sql.append(" SELECT Y ,SUM(xl*1.0000/50000 ) AS cl FROM N_K_TJIB_GRAPH GROUP BY Y )  ");
		sql.append(" GROUP BY Y ) K  ");
		sql.append(" INNER JOIN (values(2006,3063.8000),(2007,3978.6000),(2008,4521.3),(2009,5248.1),(2010,6074.8),(2011,7469.7600),(2012,8649.3882), ");
		sql.append("  (2013,9559.8600),(2014,10517.6000),(2015,11436.1041),(2016,10795.7795),(2017,11145.1037)) AS P(y,IC_TAX_PROF)  ");
		sql.append(" ON K.Y = P.Y WITH UR ");
		 */
		sql.append("SELECT DECIMAL(ROUND(MAX(max_PRODUCENUM),-3)+1000 ) AS cxMax , ");
		sql.append(" DECIMAL(ROUND(MIN(min_PRODUCENUM),-3)-1000 ) AS cxMin , ");
		sql.append(" DECIMAL(ROUND(MAX(IC_TAX_PROF),-3)) AS slMax , ");
		sql.append(" DECIMAL(ROUND(MIN(IC_TAX_PROF),-3)) AS slMin  ");
		sql.append(" FROM ( SELECT Y ,MAX(cl) AS max_PRODUCENUM ,MIN(cl) AS min_PRODUCENUM  ");
		sql.append(" FROM ( SELECT Y ,SUM(cl*1.0000/50000 ) AS cl FROM N_K_TJIB_GRAPH GROUP BY Y  ");
		sql.append(" UNION ALL  ");
		sql.append(" SELECT Y ,SUM(xl*1.0000/50000 ) AS cl FROM N_K_TJIB_GRAPH GROUP BY Y )  ");
		sql.append(" GROUP BY Y ) K  ");
		sql.append(" INNER JOIN (select y,ic_tax_prof from tax_line) p  ");
		sql.append(" ON K.Y = P.Y WITH UR ");
		/*sql.append("SELECT	 ");
		sql.append("	 DECIMAL(ROUND(MAX(max_PRODUCENUM),-3)+1000   )	 AS cxMax ");
		sql.append("	,DECIMAL(ROUND(MIN(min_PRODUCENUM),-3)-1000  )	 AS cxMin ");
		sql.append("	,DECIMAL(ROUND(MAX(IC_TAX_PROF),-3)+1000  )	 AS slMax ");
		sql.append("	,DECIMAL(ROUND(MIN(IC_TAX_PROF),-3)-1000 )	 AS slMin ");
		sql.append("FROM ");
		sql.append("( ");
		sql.append("	SELECT  ");
		sql.append("		Y ");
		sql.append("		,MAX(PRODUCENUM) AS max_PRODUCENUM ");
		sql.append("		,MIN(PRODUCENUM) AS min_PRODUCENUM ");
		sql.append("	FROM ");
		sql.append("	( ");
		sql.append("		SELECT ");
		sql.append("			Y ");
		sql.append("			,SUM(PRODUCENUM*1.0000/50000 ) AS PRODUCENUM  ");
		sql.append("		FROM NICK_K_DS_TJIB_Y_ALL ");
		sql.append("		GROUP BY Y ");
		sql.append("		UNION ALL ");
		sql.append("		SELECT ");
		sql.append("			Y ");
		sql.append("			,SUM(BS_SELLNUM*1.0000/50000 ) AS PRODUCENUM  ");
		sql.append("		FROM NICK_K_DS_TJIB_Y_ALL ");
		sql.append("		GROUP BY Y ");
		sql.append("	) GROUP BY Y ");
		sql.append(") K ");
		sql.append("INNER JOIN ");
		sql.append("	( ");
		sql.append("		values(2006,3063.8000),(2007,3978.6000),(2008,4521.3),(2009,5248.1),(2010,6074.8),(2011,7469.7600),(2012,8649.3882),(2013,9559.8600),(2014,10517.6000),(2015,11436.1041),(2016,10795.7795),(2017,11145.1037)) AS P(y,IC_TAX_PROF)");
		sql.append("ON K.Y = P.Y ");
		sql.append("WITH UR ");*/
		
		log.info("走势图最大最小值处理sql: "+sql.toString());
		return sql.toString();
	}
	
	
	public ModelVO getTaxLine(){
		DBBeanBase dbbean = new DBBeanBase("dw");
		log.info("传入参数： dbName:"+"dw");
		String sql1 = "";
		String sql2 = "";
		List list = new ArrayList();
		TaxMaxMin maxMinObj = new TaxMaxMin();
		try {
			sql1 = getSql();
			sql2 = getMaxMin();
			list = dbbean.executeQuery(sql1, TaxLineVO.class.getName()); 
			maxMinObj = (TaxMaxMin)dbbean.executeQuerySingle(sql2, TaxMaxMin.class.getName()); 
		}catch (Exception e) {
			log.info("*************************");
			log.info("走势图税利折线图业务sql执行异常:"+e);
			log.info("*************************");
			log.error("走势图税利折线图业务sql执行异常", e);
		}finally{
			dbbean.close();
		}
		
		ModelVO mvo = new ModelVO();
		mvo.setSlList(list);
		mvo.setTaxMaxMin(maxMinObj);
		return mvo;
		
	} 
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		ModelVO mvo = getTaxLine();
		String result = StringUtil.getJson(mvo);
		log.info("走势图税利折线图业务查询结果"+StringUtil.getJson(mvo));
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", result);
		
		return request;
	}
	
	public static void main(String[] args) {
		List list = new ArrayList();
		TaxLineVO vo1 = new TaxLineVO();
		vo1.setSl("aaa");
		TaxLineVO vo2 = new TaxLineVO();
		vo2.setSl("bbb");
		list.add(vo1);
		list.add(vo2);
		System.out.println(StringUtil.getJson(vo1));
	}
}
