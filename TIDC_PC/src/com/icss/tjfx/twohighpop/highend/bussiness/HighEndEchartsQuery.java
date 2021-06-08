package com.icss.tjfx.twohighpop.highend.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.twohighpop.highend.vo.ModelVo;
import com.icss.tjfx.twohighpop.highend.vo.TowHighCharsVO;

public class HighEndEchartsQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(HighEndEchartsQuery.class);
	/**
	 * 获取查询高端烟左侧Echarts数据的sql(高端烟模块)
	 * @return 
	 */
	public static String getSqlHighCigEchartsXLQS(){
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT  CIG_MARKNAME AS BRAND_NAME,                                       ");
		sql.append("		DECIMAL(ROUND(SUM(BS_SELLNUM)*1.0000/50000,2),16,2) AS BS_SELLNUM    ");
		sql.append("FROM  NICK_K_DS_TJIB_Y_ALL K,NICK_TJYY_CIGARETTE SC                        ");
		sql.append("WHERE                                                                 ");
		sql.append("	K.C_BRAND = SC.CIG_CODEBAR              ");
		sql.append("	 AND  ((k.d_year<='2017' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_bsnx='1'))         ");
		sql.append("		AND SC.IS_HIGH_TIER_BRAND = 1    and CIG_IMPORTFLAG in ('0','3')                              ");
		sql.append("	AND K.Y = YEAR(CURRENT DATE-1 DAYS)                                        ");
		sql.append("GROUP BY CIG_MARKNAME                                            ");
		sql.append("ORDER BY BS_SELLNUM DESC                                              ");
		sql.append("FETCH FIRST 3 ROWS ONLY                                               ");
		sql.append("WITH UR                                                              ");
		
		log.info("查询高端烟左侧Echarts数据为："+sql);
		return sql.toString();
	}
	
	public static String getSqlHighCigEchartsGGS(){
		StringBuffer sql = new StringBuffer();
		
		sql.append("select                                                                              ");
		sql.append(" count(distinct K.C_BRAND)	AS COUNT_C_BRAND	                             ");         
		sql.append(" ,DECIMAL(ROUND(SUM(K.BS_SELLNUM)*1.0000/50000,2),16,2) AS HIGH_BS_SELLNUM	      ");        
		sql.append(" ,DECIMAL(ROUND(SUM(K.BS_SELLSUM)*1.0000/10000,2),16,2) AS HIGH_BS_SELLSUM	      ");        
		sql.append("FROM  NICK_K_DS_TJIB_Y_ALL K,NICK_TJYY_CIGARETTE SC                    ");
		sql.append("WHERE                                                                               ");
		sql.append("	  K.C_BRAND =  SC.CIG_CODEBAR                                  ");
		sql.append("	  AND  ((k.d_year<='2017' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_bsnx='1'))              ");
		sql.append("  AND SC.IS_HIGH_TIER_BRAND = '1'   and CIG_IMPORTFLAG in ('0','3')                            ");
		sql.append("	AND K.Y = YEAR(CURRENT DATE-1 DAYS) AND BS_SELLNUM>0                                                     ");
		sql.append("WITH UR                                                                           ");

		
		log.info("查询高端烟左侧Echarts数据为："+sql);
		return sql.toString();
	}
	public static String getSqlHighCigEchartsXLXE(){
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT     " +
				        "count(distinct C_BRAND)	AS ALL_COUNT_C_BRAND                                                                  ");
		sql.append("	,DECIMAL(ROUND(SUM(BS_SELLNUM)*1.0000/50000,2),16,2) AS ALL_BS_SELLNUM     ");
		sql.append("	,DECIMAL(ROUND(SUM(BS_SELLSUM)*1.0000/10000,2),16,2) AS ALL_BS_SELLSUM     ");
		sql.append("FROM  NICK_K_DS_TJIB_Y_ALL K,NICK_TJYY_CIGARETTE SC                                ");
		sql.append("  WHERE   K.C_BRAND = sc.cig_codebar                   ");
		sql.append("  and ((k.d_year<='2017' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_bsnx='1'))          ");
		sql.append("  AND  Y = YEAR(CURRENT DATE-1 DAYS)       ");
		sql.append("WITH UR                                                                     ");
		
		log.info("查询高端烟左侧Echarts数据为："+sql);
		return sql.toString();
	}
	
	public ModelVo getHighEndEcharts(){
		DBBeanBase dbbean = new DBBeanBase("dw");
		String sql = "";
		String sql1="";
		String sql2="";
		TowHighCharsVO towHighCharsVO = new TowHighCharsVO();
		TowHighCharsVO towHighCharsVOHigh = new TowHighCharsVO();
		List<TowHighCharsVO> towHighCharsVOList = new ArrayList<TowHighCharsVO>();
		ModelVo mvo = new ModelVo();
		try {
			sql = getSqlHighCigEchartsXLQS();
			sql1=getSqlHighCigEchartsGGS();
			sql2=getSqlHighCigEchartsXLXE();
			towHighCharsVOList = dbbean.executeQuery(sql, TowHighCharsVO.class.getName()); 
			towHighCharsVO = (TowHighCharsVO)dbbean.executeQuerySingle(sql1, TowHighCharsVO.class.getName()); 
			towHighCharsVOHigh = (TowHighCharsVO)dbbean.executeQuerySingle(sql2, TowHighCharsVO.class.getName()); 
			mvo.setTowHighCharsVOList(towHighCharsVOList);
			mvo.setTowHighCharsVO(towHighCharsVO);
			mvo.setTowHighCharsVOHigh(towHighCharsVOHigh);
			log.info("总销量"+towHighCharsVOHigh.getALL_BS_SELLNUM());
			log.info("总规格"+towHighCharsVOHigh.getALL_COUNT_C_BRAND());
			log.info("总销额"+towHighCharsVOHigh.getALL_BS_SELLSUM());
			log.info("销量"+towHighCharsVO.getHIGH_BS_SELLNUM());
			log.info("规格"+towHighCharsVO.getCOUNT_C_BRAND());
			log.info(" 销额"+towHighCharsVO.getHIGH_BS_SELLSUM());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询高端烟左侧Echarts数据sql执行异常:"+e);
			log.info("*************************");
			log.error("查询高端烟左侧Echarts数据sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return mvo;
		
	}
	
	
	

	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		ModelVo mvo = getHighEndEcharts();
		Gson gson = new Gson();
		String s = gson.toJson(mvo);
		log.info("高端烟品牌业务查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	

}
