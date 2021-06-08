package com.icss.tjfx.cigpop.cooperation.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.cigpop.cooperation.vo.CooperPieVO;
import com.icss.tjfx.cigpop.cooperation.vo.CooperProduceTopThreeVO;
import com.icss.tjfx.cigpop.cooperation.vo.ModelVO;

public class CooperEchartQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(CooperEchartQuery.class);
	

	
	/**
	 * 获取查询卷烟品牌新品销量左侧Echarts数据的sql(新品烟模块)
	 * @return 
	 */
	public static String getSqlEchartsPie(){
		StringBuffer sql = new StringBuffer();
		sql.append("  	SELECT																	");
		sql.append("  		TO_CHAR(DECIMAL(ROUND(SUM(K.PRODUCENUM1), 2), 16, 2),'999990.99') AS PRODUCENUM1_C						");
		sql.append("  		,SUM(K.BRAND_NUM) AS BRAND_NUM_C												");
		sql.append("  		,TO_CHAR(DECIMAL(ROUND(SUM(P.PRODUCENUM1), 2), 16, 2),'999990.99') AS PRODUCENUM1_Q						");
		sql.append("  		,SUM(P.BRAND_NUM) AS BRAND_NUM_Q												");
		sql.append("  	FROM (																	");
		sql.append("  	select 																	");
		sql.append("         sum(PRINTNUM1*1.0000 - REJECTNUM1 *1.0000+ REPEATNUM1*1.0000 - JUMPNUM1*1.0000)/50000 PRODUCENUM1,					");
		sql.append("  	   COUNT(DISTINCT(C_BRAND)) AS BRAND_NUM												");
		sql.append("  	from  NICK_K_TJIN_Y_ALL,NICK_TJYY_CIGARETTE											");
		sql.append("  	 where Y = YEAR(CURRENT DATE-1 DAYS) 					");
		sql.append("  	   and C_Brand = cig_codebar							");
		sql.append("  	 and cig_barcarrier = '02' 					");
		sql.append("  	and  ((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))			");
		sql.append("  	  and TRADE_TYPE ='2'															");
		sql.append(" 	and c_brand not in '6900000000001' 	");
		sql.append(" 	and (PRINTNUM1-REJECTNUM1+ REPEATNUM1- JUMPNUM1) <> 0 		");
		sql.append("  	  and (substr(CIG_PRODUCER,3,2) <> substr(law_org,3,2) and law_org <> '12440301'							");
		sql.append("  	  or substr(CIG_PRODUCER,3,2) = substr(law_org,3,2) and law_org = '12440301')								");
		sql.append("  	  ) K,																	");
		sql.append("  	  (select 																");
		sql.append("  	       sum(PRINTNUM1*1.0000 - REJECTNUM1 *1.0000+ REPEATNUM1*1.0000 - JUMPNUM1*1.0000)/50000 AS PRODUCENUM1,				");
		sql.append("  		   COUNT(DISTINCT(C_BRAND)) AS BRAND_NUM											");
		sql.append("  	from 	NICK_K_TJIN_Y_ALL NK,NICK_TJYY_CIGARETTE SC						");
		sql.append("  		WHERE Y = YEAR(CURRENT DATE-1 DAYS)	and NK.C_BRAND = SC.cig_codebar and						");
		sql.append(" 	((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))  					");
		sql.append("  	  ) P																	");

		log.info("查询卷烟品牌合作环图sql："+sql);
		return sql.toString();
	}
	
	/**
	 * 获取查询卷烟品牌合作销量前三左侧Echarts数据的sql(新品烟模块)
	 * @return 
	 */
	public static String getSqlCooperEchartsTop3(){
		StringBuffer sql = new StringBuffer();

		sql.append(" 			select O.C_CIG																				");
		sql.append(" 			,O.CIG_MARKNAME as CIG_MARKNAME																	");
		sql.append(" 			,TO_CHAR(DECIMAL(ROUND((sum(PRINTNUM1*1.0000 - REJECTNUM1 *1.0000+ REPEATNUM1*1.0000 - JUMPNUM1*1.0000)/50000), 2), 16, 2),'999990.99') AS PRODUCENUM1		");
		sql.append(" 			from NICK_K_TJIN_Y_ALL K,VIEW_NICK_CIG_PROPERTY O ,NICK_TJYY_CIGARETTE S 				");
		sql.append(" 			 where Y = YEAR(CURRENT DATE-1 DAYS) 								");
		sql.append(" 			AND O.C_BRAND = K.C_BRAND																");
		sql.append(" 			and K.C_Brand = S.cig_codebar 												");
		sql.append(" 			 and cig_barcarrier = '02'													");
		sql.append(" 			and ((D_YEAR<='2017' AND IS_NRT='1' AND S.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND S.is_innx='1'))  										");
		sql.append(" 			and TRADE_TYPE ='2'																	");
		sql.append(" 			and (substr(S.CIG_PRODUCER,3,2) <> substr(law_org,3,2) and law_org <> '12440301'									");
		sql.append(" 			or substr(S.CIG_PRODUCER,3,2) = substr(law_org,3,2) and law_org = '12440301')										");
		sql.append(" 			group by O.C_CIG,O.CIG_MARKNAME															");
		sql.append(" 			ORDER BY PRODUCENUM1 DESC															");
		sql.append(" 			FETCH FIRST 3 ROWS ONLY																");
		sql.append(" 			WITH UR																			");

		log.info("查询卷烟品牌合作top3的sql："+sql);
		return sql.toString();
	}

	
	@SuppressWarnings("unchecked")
	public ModelVO getCooperEchart(){
		DBBeanBase dbbean = new DBBeanBase("dw");
		String sql = "";
		String sql1="";
		CooperPieVO cooperPieVO = new CooperPieVO();
		List<CooperProduceTopThreeVO> list = new ArrayList<CooperProduceTopThreeVO>();
		ModelVO mvo = new ModelVO();
		try {
			//销量前三
			sql = getSqlEchartsPie();
			//本期同期
			sql1=getSqlCooperEchartsTop3();
			
			cooperPieVO = (CooperPieVO)dbbean.executeQuerySingle(sql, CooperPieVO.class.getName()); 
			list = dbbean.executeQuery(sql1, CooperProduceTopThreeVO.class.getName());
			mvo.setCooperPieVO(cooperPieVO);
			mvo.setList(list);
		} catch (Exception e) {
			log.error("查询卷烟品牌合作左侧Echarts数据sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return mvo;
	}

	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		ModelVO mvo = getCooperEchart();
		Gson gson = new Gson();
		String s = gson.toJson(mvo);
		log.info("卷烟品牌合作左侧Echarts业务查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	

}
