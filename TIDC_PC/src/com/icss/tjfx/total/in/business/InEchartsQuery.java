package com.icss.tjfx.total.in.business;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.total.in.vo.ModelVO;
import com.icss.tjfx.total.in.vo.OutProvinceVO;
import com.icss.tjfx.total.in.vo.ProduceNumBarVO;
import com.icss.tjfx.total.in.vo.SingleVO;

/**
 * 总量情况工业模块左侧echarts数据查询类
 * @author lcx
 *
 */
public class InEchartsQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(InEchartsQuery.class);
	
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)throws Exception {
		Gson json = new Gson();
		ModelVO mov = getInEcharts();
		String s = json.toJson(mov);
		log.info("总量模块工业echarts数据查询结果："+s);
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	
	public ModelVO getInEcharts(){
		ModelVO mov = new ModelVO();
		ProduceNumBarVO produceNumBarVO = getProduceNumBarInfo("dw");
		SingleVO singleVO = getInSingleBarInfo("dw");
		List<OutProvinceVO> list = getOutProviceTop3Info("dw");
		mov.setProduceNumBarVO(produceNumBarVO);
		mov.setSingleVO(singleVO);
		mov.setOutProvinceVOList(list);
		Gson json = new Gson();
		String s = json.toJson(mov);
		log.info("总量模块工业echarts数据查询结果："+s);
		return mov;
	}
	
	/**
	 * 总量情况、工业模块<p>产量柱状图查询sql获取
	 * @return String
	 */
	public static String getSqlProduceNum(){
		StringBuffer produceNumSql = new StringBuffer();	
		produceNumSql.append(" 	SELECT	");
		produceNumSql.append(" 	DECIMAL(ROUND(SUM(PRINTNUM1 - REJECTNUM1 + REPEATNUM1 - JUMPNUM1)*1.0000/50000, 2), 16,2) AS proNum_bq ");
		produceNumSql.append(" 	,DECIMAL(ROUND(SUM(PRINTNUM1_P - REJECTNUM1_P + REPEATNUM1_P - JUMPNUM1_P)*1.0000/50000, 2), 16,2) AS proNum_tq ");
		produceNumSql.append("		FROM NICK_K_TJIN_Y_ALL   NK,NICK_TJYY_CIGARETTE SC             ");
		produceNumSql.append("		WHERE 1=1              ");
		produceNumSql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
		produceNumSql.append("		((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))         ");
		produceNumSql.append(" 	AND Y = YEAR(CURRENT DATE-1 DAYS)	");		
		log.info("总量情况工业模块产量柱状图查询sql为："+produceNumSql);
		return produceNumSql.toString();
	}
	
	/**
	 * 总量情况、工业模块<p>产量柱状图数据信息的查询
	 * @param dbName
	 * @return 
	 */
	public ProduceNumBarVO getProduceNumBarInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		ProduceNumBarVO produceNumBarVO = new ProduceNumBarVO();
		try {
			sql = getSqlProduceNum();
			produceNumBarVO = (ProduceNumBarVO)dbbean.executeQuerySingle(sql, ProduceNumBarVO.class.getName());
		} catch (Exception e) {
			log.error("getProduceNumBarInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return produceNumBarVO;
	}
	
	
	/**
	 * 总量情况、工业模块<p>单箱柱状图查询sql获取
	 * @return String
	 */
	public static String getSqlInSingle(){
		StringBuffer inSingleSql = new StringBuffer();
		inSingleSql.append("  	SELECT	");	
		inSingleSql.append("  	DECIMAL(ROUND(DECODE(SUM(OUT_FACT_NUM1-RECV_NUM1),0,0,NULL,0,SUM(OUT_FACT_SUM-RECV_SUM)*5.0000/SUM(OUT_FACT_NUM1-RECV_NUM1))		,2),16,2)  as 	single_bq 		 ");
		inSingleSql.append("  	,DECIMAL(ROUND(DECODE(SUM(OUT_FACT_NUM1_L-RECV_NUM1_L),0,0,NULL,0,SUM(OUT_FACT_SUM_L-RECV_SUM_L)*5.0000/SUM(OUT_FACT_NUM1_L-RECV_NUM1_L))	,2),16,2) as 	single_tq 	 ");
		inSingleSql.append("		FROM NICK_K_TJIN_Y_Q_M_TYPE_SELL  NK,NICK_TJYY_CIGARETTE SC           ");
		inSingleSql.append("		WHERE 1=1              ");
		inSingleSql.append("		 AND NK.C_BRAND = SC.cig_codebar and      ");
		inSingleSql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))         ");
		inSingleSql.append("  	AND TYPE IN ('0','2', '5')	 ");
		inSingleSql.append("  	AND I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')	");
		inSingleSql.append("  	AND Y = YEAR(CURRENT DATE-1 DAYS) AND M <= MONTH(CURRENT DATE-1 DAYS)	");
		log.info("总量情况工业模块单箱柱状图查询sql为："+inSingleSql);
		return inSingleSql.toString();
	}
	
	/**
	 * 总量情况、工业模块<P>单箱柱状图数据信息的查询
	 * @param dbName
	 * @return SingleVO
	 */
	public SingleVO getInSingleBarInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		SingleVO singleVO = new SingleVO();
		try {
			sql = getSqlInSingle();
			singleVO = (SingleVO)dbbean.executeQuerySingle(sql, SingleVO.class.getName());
		} catch (Exception e) {
			log.info("getInSingleBarInfo查询方法sql执行异常:"+e);
			log.error("getInSingleBarInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return singleVO;
	}
	
	
	/**
	 * 总量情况、工业模块<p>省外依存度前三查询sql获取
	 * @return String
	 */
	public static String getSqlTopThree(){
		StringBuffer topThreeSql = new StringBuffer();
		
		/*topThreeSql.append("		SELECT																												 ");		
		topThreeSql.append("		FACT_POP_NAME as FACT_POPEDOM																									 ");
		topThreeSql.append("		,EXTERNAL_MARKET_RATE																										 ");
		topThreeSql.append("		FROM 																												 ");
		topThreeSql.append("		(																												 ");
		topThreeSql.append("		SELECT																												 ");
		topThreeSql.append("				FACT_POPEDOM																	AS FACT_POPEDOM							 ");
		topThreeSql.append("				,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(SELLNUM),0,0,NULL,0,SUM(CASE WHEN K.I_PROVINCE = O.ORG_OWNPROVINCE THEN 0 ELSE SELLNUM END)*100.0000/SUM(SELLNUM)), 2), 16, 2),'999990.99') AS EXTERNAL_MARKET_RATE 		 ");
		topThreeSql.append("			FROM																											 ");
		topThreeSql.append("				(																										 ");
		topThreeSql.append("					SELECT 																									 ");
		topThreeSql.append("						C_BRAND																								 ");
		topThreeSql.append("						,I_PROVINCE																							 ");
		topThreeSql.append("						,SUM(PRINT_NUM1 	- RBACK_NUM1	)     AS SELLNUM																 ");
		topThreeSql.append("						,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	)     AS SELLNUM_L																 ");
		topThreeSql.append("			FROM NICK_K_TJBS_Y_ALL    NK,NICK_TJYY_CIGARETTE SC         ");
		topThreeSql.append("		WHERE NK.C_BRAND = SC.cig_codebar and      ");
		topThreeSql.append("		((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))        ");
		topThreeSql.append("						AND Y = YEAR(CURRENT DATE-1 DAYS)																					 ");
		topThreeSql.append("					GROUP BY C_BRAND, I_PROVINCE																						 ");
		topThreeSql.append("				) K																										 ");
		topThreeSql.append("			INNER JOIN VIEW_CIG_PROPERTY P																								 ");
		topThreeSql.append("				ON K.C_BRAND = P.C_BRAND																							 ");
		topThreeSql.append("			INNER JOIN NICK_STMA_ORGANIZATION O																							 ");
		topThreeSql.append("				ON O.ORG_CODE = P.CIG_PRODUCER																							 ");
		topThreeSql.append("			GROUP BY FACT_POPEDOM																									 ");
		topThreeSql.append("		) K																												 ");
		topThreeSql.append("		LEFT JOIN MA_T_FACT_POP F																									 ");
		topThreeSql.append("		ON K.FACT_POPEDOM = F.FACT_POP_CODE																								 ");
		topThreeSql.append("		WHERE K.FACT_POPEDOM NOT IN('10000001', '18000001')																						 ");
		topThreeSql.append("		ORDER BY DECIMAL(EXTERNAL_MARKET_RATE) DESC																							 ");
		topThreeSql.append("		FETCH FIRST 3 ROWS ONLY																										 ");*/
		topThreeSql.append("		SELECT FACT_POP_NAME AS FACT_POPEDOM ,					 ");	
		topThreeSql.append("		 CASE WHEN BS_SELLNUM = 0 THEN '-'													 ");
		topThreeSql.append("      ELSE TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM,0,0,NULL,0,BS_SELLNUM_O*100.0000/BS_SELLNUM), 2), 16, 2),'999990.99')     ");
		topThreeSql.append("		 END AS EXTERNAL_MARKET_RATE							 ");
		topThreeSql.append("		FROM (SELECT fact_popedom, sum(BS_SELLNUM) AS BS_SELLNUM,sum(BS_SELLNUM_O) AS BS_SELLNUM_O			 ");
		topThreeSql.append("		FROM table (SELECT NVL(FACT_POPEDOM,'00') AS FACT_POPEDOM ,0 AS BS_SELLNUM ,0 AS BS_SELLNUM_L ,SUM(CASE		 ");
		topThreeSql.append("		WHEN I_PROVINCE = ORG_OWNPROVINCE THEN 0							 ");
		topThreeSql.append("		 ELSE SELLNUM END ) AS BS_SELLNUM_O ,SUM(CASE													 ");
		topThreeSql.append("		WHEN I_PROVINCE = ORG_OWNPROVINCE THEN 0													 ");
		topThreeSql.append("		ELSE SELLNUM_L END ) AS BS_SELLNUM_O_L														 ");
		topThreeSql.append("		FROM  (SELECT fact_popedom ,I_PROVINCE ,ORG_OWNPROVINCE,									 ");	
		topThreeSql.append("		BS_SELLNUM AS SELLNUM,BS_SELLNUM_L AS SELLNUM_L													 ");
		topThreeSql.append("		FROM  (SELECT (case WHEN o.FACT_POPEDOM is NOT NULL THEN o.FACT_POPEDOM						 ");
		topThreeSql.append("		 ELSE '20130001' end)as FACT_POPEDOM, I_PROVINCE,SUM(BS_SELLNUM_Y_A)AS BS_SELLNUM,SUM(BS_SELLNUM_Y_AL)AS BS_SELLNUM_L					 ");
		topThreeSql.append("	FROM view_CIG_PROPERTY C full JOIN  (SELECT DISTINCT BRANCHCODE, FACT_POPEDOM			 ");
		topThreeSql.append("	FROM nick_VIEW_FACTORY_BRANCH) O on(C.CIG_PRODUCER=O.BRANCHCODE) ,nick_K_DS_TJIB_Y_Q_M_ALL K,nick_TJYY_CIGARETTE SC		 ");
		topThreeSql.append("	 WHERE K.C_BRAND=C.C_BRAND AND C.CIG_PRODUCER <> '10000001'		 ");
		topThreeSql.append("		 AND C.CIG_PRODUCER <> '18000001' AND K.C_BRAND = sc.CIG_CODEBAR										 ");
		topThreeSql.append("		AND ((k.d_month<='201712' AND sc.CIG_PRODUCTTYPE NOT in('05','06'))						 ");
		topThreeSql.append("		OR (k.d_month>='201801' AND sc.is_bsnx='1'))				 ");
		topThreeSql.append("		 AND K.Y = YEAR(CURRENT DATE-1 DAYS) AND K.M = MONTH(CURRENT DATE-1 DAYS) AND k.c_brand<>'6900000000001'							 ");	
		topThreeSql.append("		 GROUP BY  o.FACT_POPEDOM,I_PROVINCE)A,DW_T_STMA_ORGANIZATION S									 ");
		topThreeSql.append("		WHERE A.FACT_POPEDOM=S.ORG_CODE) aa GROUP BY  FACT_POPEDOM								 ");
		topThreeSql.append("		WITH ROLLUP UNION all SELECT NVL(FACT_POPEDOM,								 ");
		topThreeSql.append("  '00') AS FACT_POPEDOM ,SUM(SELLNUM ) AS BS_SELLNUM ,SUM(SELLNUM_L ) AS BS_SELLNUM_L ,0 AS BS_SELLNUM_O ,0 AS BS_SELLNUM_O_L  ");
		topThreeSql.append("	 FROM (SELECT fact_popedom,I_PROVINCE,ORG_OWNPROVINCE,		 ");
		topThreeSql.append("		BS_SELLNUM AS SELLNUM,BS_SELLNUM_L AS SELLNUM_L								 ");
		topThreeSql.append("		FROM  (SELECT (case WHEN o.FACT_POPEDOM is NOT NULL THEN o.FACT_POPEDOM					 ");
		topThreeSql.append("	ELSE '20130001' end)as FACT_POPEDOM, I_PROVINCE,SUM(BS_SELLNUM_Y_A)AS BS_SELLNUM,SUM(BS_SELLNUM_Y_AL)AS BS_SELLNUM_L		 ");
		topThreeSql.append("	 FROM view_CIG_PROPERTY C full JOIN  (SELECT DISTINCT BRANCHCODE, FACT_POPEDOM					 ");
		topThreeSql.append("	FROM nick_VIEW_FACTORY_BRANCH) O on(C.CIG_PRODUCER=O.BRANCHCODE),nick_K_DS_TJIB_Y_Q_M_ALL K,nick_TJYY_CIGARETTE SC			 ");
		topThreeSql.append("		WHERE K.C_BRAND=C.C_BRAND AND K.C_BRAND = sc.CIG_CODEBAR						 ");
		topThreeSql.append("	AND ((k.d_month<='201712' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_month>='201801'  AND sc.is_bsnx='1'))   ");
		topThreeSql.append("	AND K.Y = YEAR(CURRENT DATE-1 DAYS) AND K.M = MONTH(CURRENT DATE-1 DAYS) AND k.c_brand<>'6900000000001'								 ");
		topThreeSql.append("	GROUP BY  o.FACT_POPEDOM,I_PROVINCE)A,DW_T_STMA_ORGANIZATION S					 ");	
		topThreeSql.append("		 WHERE A.FACT_POPEDOM=S.ORG_CODE) aa GROUP BY  FACT_POPEDOM WITH ROLLUP ) t					 ");
		topThreeSql.append("	GROUP BY  fact_popedom ) B LEFT JOIN MA_T_FACT_POP F ON b.FACT_POPEDOM = F.FACT_POP_CODE					 ");
		topThreeSql.append("		WHERE b.FACT_POPEDOM NOT IN('10000001', '18000001') ORDER BY  CASE WHEN BS_SELLNUM = 0 THEN '-'			 ");
		topThreeSql.append("		ELSE TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM,0,0,NULL,0,BS_SELLNUM_O*100.0000/BS_SELLNUM), 2), 16, 2),'999990.99')	 ");
		topThreeSql.append("		    END DESC FETCH FIRST 3 ROWS ONLY								 ");
		
		log.info("总量情况工业模块省外依存度前三查询sql为："+topThreeSql);
		return topThreeSql.toString();
	}
	
	/**
	 * 总量情况、工业模块<P>省外依存度前三数据信息的查询
	 * @param dbName
	 * @return List<OutProvinceVO>
	 */
	@SuppressWarnings("unchecked")
	public List<OutProvinceVO>  getOutProviceTop3Info(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<OutProvinceVO> top3List = new ArrayList<OutProvinceVO>();
		try {
			sql = getSqlTopThree();
			top3List = dbbean.executeQuery(sql, OutProvinceVO.class.getName());
		} catch (Exception e) {
			log.error("getOutProviceTop3Info查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return top3List;
	}

	
}
