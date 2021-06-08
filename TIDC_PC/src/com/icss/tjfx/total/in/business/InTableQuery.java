package com.icss.tjfx.total.in.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.total.in.vo.IndustryVO;

/**
 * 总量情况工业模块右侧表格数据查询类
 * @author lcx
 *
 */
public class InTableQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(InTableQuery.class);
		
	@Override
	public HttpServletRequest handle(HttpServletRequest request)throws Exception {
		Gson gson = new Gson();
		List<IndustryVO> indusryList = new ArrayList<IndustryVO>();
		String type=request.getParameter("type");								//获取下钻的类型
		String code=request.getParameter("code");	
		if (type.equals("jl")) {
			indusryList = getJLTableInfo(code,"dw");
		}else if(type.equals("qy")){
			indusryList = getQYTableInfo(code,"dw");
		}else {
			indusryList = getInTableInfo("dw");
		}				
		String s = gson.toJson(indusryList);
		log.info("总量情况工业业务查询结果："+s);		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);		
		return request;
	}
	
	
	/**
	 * 总量情况、工业模块<p>表格查询sql获取
	 * @return String
	 */
	public static String getSqlTable(){
		StringBuffer sql = new StringBuffer();
		sql.append(" 	SELECT																																	 ");
		sql.append(" 	NVL(K.FACT_POPEDOM, '00') AS C_CLASS																													 ");
		sql.append(" 	,NVL(FACT_POP_NAME, '合 计') AS factoryName																												 ");
		sql.append(" 	,CASE WHEN PRODUCENUM1 = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM1					*1.0000/50000, 2), 16, 2),'999990.99') END 				AS PRODUCENUM                                                                    ");
		sql.append(" 	,CASE WHEN PRODUCENUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 				AS PRODUCENUM_L                                                                  ");
		sql.append(" 	,CASE WHEN (PRODUCENUM1+PRODUCENUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 				AS PRODUCENUM_GROWTH                                                             ");
		sql.append(" 	,CASE WHEN (PRODUCENUM1 = 0 OR PRODUCENUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)*100.0000/DECODE(PRODUCENUM_L,0,1,PRODUCENUM_L), 2), 16, 2)) END AS PRODUCENUM_GROWTH_RATE							 ");
		sql.append(" 	,CASE WHEN IN_SELLNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_SELLNUM                                                                    ");
		sql.append(" 	,CASE WHEN IN_SELLNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_SELLNUM_L                                                                  ");
		sql.append(" 	,CASE WHEN (IN_SELLNUM+IN_SELLNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_SELLNUM - IN_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IN_SELLNUM_GROWTH											 ");
		sql.append(" 	,CASE WHEN (IN_SELLNUM = 0 OR IN_SELLNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IN_SELLNUM - IN_SELLNUM_L)*100.0000/DECODE(IN_SELLNUM_L,0,1,IN_SELLNUM_L), 2), 16, 2)) END AS IN_SELLNUM_GROWTH_RATE							 ");
		sql.append(" 	,CASE WHEN IN_TERMSTOCK = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	(IN_TERMSTOCK 		)						*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_TERMSTOCK                                  ");
		sql.append(" 	,CASE WHEN IN_TERMSTOCK_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	(IN_TERMSTOCK_L 	)						*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_TERMSTOCK_L                                ");
		sql.append(" 	,CASE WHEN (IN_TERMSTOCK+IN_TERMSTOCK_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_TERMSTOCK - IN_TERMSTOCK_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IN_TERMSTOCK_GROWTH										 ");
		sql.append(" 	,CASE WHEN (IN_TERMSTOCK = 0 OR IN_TERMSTOCK_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IN_TERMSTOCK - IN_TERMSTOCK_L)*100.0000/DECODE(IN_TERMSTOCK_L,0,1,IN_TERMSTOCK_L), 2), 16, 2)) END AS IN_TERMSTOCK_GROWTH_RATE					 ");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM												 ");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_L											 ");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_GROWTH										 ");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS SINGLE_SELLSUM_GROWTH_RATE			 ");
		sql.append(" 	,CASE WHEN IN_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 				AS IN_SELLSUM                                                                    ");
		sql.append(" 	,CASE WHEN IN_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 				AS IN_SELLSUM_L                                                                  ");
		sql.append(" 	,CASE WHEN (IN_SELLSUM+IN_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_SELLSUM - IN_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS IN_SELLSUM_GROWTH											 ");
		sql.append(" 	,CASE WHEN (IN_SELLSUM = 0 OR IN_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IN_SELLSUM - IN_SELLSUM_L)*100.0000/DECODE(IN_SELLSUM_L,0,1,IN_SELLSUM_L), 2), 16, 2)) END AS IN_SELLSUM_GROWTH_RATE							 ");
		sql.append(" 	,CASE WHEN BS_SELLNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM,0,0,NULL,0,BS_SELLNUM_O*100.0000/BS_SELLNUM), 2), 16, 2),'999990.99') END AS EXTERNAL_MARKET_RATE											 ");
		sql.append(" 	,CASE WHEN BS_SELLNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM_L,0,0,NULL,0,BS_SELLNUM_O_L*100.0000/BS_SELLNUM_L), 2), 16, 2),'999990.99') END AS EXTERNAL_MARKET_L_RATE										 ");
		sql.append(" 	,CASE WHEN BS_SELLNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM,0,0,NULL,0,BS_SELLNUM_O*100.0000/BS_SELLNUM) - DECODE(BS_SELLNUM_L,0,0,NULL,0,BS_SELLNUM_O_L*100.0000/BS_SELLNUM_L), 2), 16, 2),'999990.99') END AS EXTERNAL_MARKET_RATE_GROWTHFROM	 ");

		sql.append("  	FROM																														       ");
		sql.append("  	(																														       ");
		sql.append("  	SELECT																														       ");
		sql.append("  		NVL(FACT_POPEDOM, '00')						AS FACT_POPEDOM																				       ");
		sql.append("  		,SUM(PRODUCENUM                         )	AS PRODUCENUM1																						       ");
		sql.append("  	    ,SUM(PRODUCENUM_L                       )	AS PRODUCENUM_L																							       ");
		sql.append("  		,SUM(IN_SELLNUM                         )	AS IN_SELLNUM																						       ");
		sql.append("  		,SUM(IN_SELLNUM_L                       )	AS IN_SELLNUM_L																						       ");
		sql.append("  		,SUM(IN_TERMSTOCK   					)	AS IN_TERMSTOCK																				       ");
		sql.append("  		,SUM(IN_TERMSTOCK_L 					)	AS IN_TERMSTOCK_L																			       ");
		sql.append("  		,SUM(IN_SELLSUM                         )	AS IN_SELLSUM																						       ");
		sql.append("  		,SUM(IN_SELLSUM_L                       )	AS IN_SELLSUM_L																						       ");
		sql.append("  		,DECODE(SUM(IN_SELLNUMS	),0,0,SUM(IN_SELLSUMS	)*5.0000/SUM(IN_SELLNUMS		))   AS SINGLE_SELLSUM																       ");
		sql.append("  		,DECODE(SUM(IN_SELLNUMS_L),0,0,SUM(IN_SELLSUMS_L	)*5.0000/SUM(IN_SELLNUMS_L	))   AS SINGLE_SELLSUM_L															       ");
		sql.append("  	FROM																														       ");
		sql.append("  	(																														       ");
		sql.append("  																															       ");
		sql.append("  		SELECT 																													       ");
		sql.append("  			FACT_POPEDOM																											       ");
		sql.append("  			,SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	)		AS PRODUCENUM													       ");
		sql.append("  			,SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	) 		AS PRODUCENUM_L														       ");
		sql.append("  			,0																					    AS IN_SELLNUM					       ");
		sql.append("  			,0																					    AS IN_SELLNUM_L					       ");
		sql.append("  			,0                                                                                      AS IN_SELLSUM																       ");
		sql.append("  			,0                                                                                      AS IN_SELLSUM_L																       ");
		sql.append("  			,SUM(TERMSTOCK1																	)       AS IN_TERMSTOCK								       ");
		sql.append("  			,SUM(TERMSTOCK1_P																)       AS IN_TERMSTOCK_L							       ");
		sql.append("  			,0 AS IN_SELLNUMS																										       ");
		sql.append("  			,0 AS IN_SELLNUMS_L																										       ");
		sql.append("  			,0 AS IN_SELLSUMS																										       ");
		sql.append("  			,0 AS IN_SELLSUMS_L																										       ");
		sql.append("		FROM NICK_K_TJIN_Y_ALL   NK,NICK_TJYY_CIGARETTE SC             ");
		sql.append("		WHERE 1=1              ");
		sql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))         ");
		sql.append("  			AND Y = YEAR(CURRENT DATE-1 DAYS)																								       ");
		sql.append("  		GROUP BY FACT_POPEDOM																											       ");
		sql.append("  																															       ");
		sql.append("  		UNION ALL																												       ");
		sql.append("  																															       ");
		sql.append("  																															       ");
		sql.append("  		SELECT 																													       ");
		sql.append("  			FACT_POPEDOM				AS FACT_POPEDOM																						       ");
		sql.append("  			,0                          AS PRODUCENUM																							       ");
		sql.append("  			,0                          AS PRODUCENUM_L																							       ");
		sql.append("  			,SUM(IN_SELLNUM     )       AS IN_SELLNUM																							       ");
		sql.append("  			,SUM(IN_SELLNUM_L   )       AS IN_SELLNUM_L																							       ");
		sql.append("  			,SUM(IN_SELLSUM     )       AS IN_SELLSUM																							       ");
		sql.append("  			,SUM(IN_SELLSUM_L   )       AS IN_SELLSUM_L																							       ");
		sql.append("  			,0                          AS IN_TERMSTOCK																							       ");
		sql.append("  			,0                          AS IN_TERMSTOCK_L																							       ");
		sql.append("  			,0                          AS IN_SELLNUMS																							       ");
		sql.append("  			,0                          AS IN_SELLNUMS_L																							       ");
		sql.append("  			,0                          AS IN_SELLSUMS																							       ");
		sql.append("  			,0                          AS IN_SELLSUMS_L																							       ");
		sql.append("  		FROM																													       ");
		sql.append("  		(																													       ");
		sql.append("  			SELECT 																												       ");
		sql.append("  				C_BRAND								AS C_BRAND																		       ");
		sql.append("  				,FACT_POPEDOM						AS FACT_POPEDOM																			       ");
		sql.append("  				,SUM(OUT_FACT_NUM1-RECV_NUM1) 		AS IN_SELLNUM																					       ");
		sql.append("  				,SUM(OUT_FACT_NUM1_L-RECV_NUM1_L) 	AS IN_SELLNUM_L																					       ");
		sql.append("  				,SUM(OUT_FACT_SUM-RECV_SUM) 		AS IN_SELLSUM																					       ");
		sql.append("  				,SUM(OUT_FACT_SUM_L-RECV_SUM_L) 	AS IN_SELLSUM_L																					       ");
		sql.append("		FROM NICK_K_TJIN_Y_Q_M_TYPE_SELL  NK,NICK_TJYY_CIGARETTE SC           ");
		sql.append("		WHERE 1=1              ");
		sql.append("		 AND NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))         ");
		sql.append("  				AND TYPE IN ('0','1','2','5')																								       ");
		sql.append("  				AND I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')													       ");
		sql.append("  				AND Y = YEAR(CURRENT DATE-1 DAYS) AND M <= MONTH(CURRENT DATE-1 DAYS)																			       ");
		sql.append("  			GROUP BY C_BRAND, FACT_POPEDOM																									       ");
		sql.append("  		) K																													       ");
		sql.append("  		GROUP BY FACT_POPEDOM																											       ");
		sql.append("            UNION ALL																												       ");
		sql.append("  																															       ");
		sql.append("  																															       ");
		sql.append("  		SELECT 																													       ");
		sql.append("  			FACT_POPEDOM				AS FACT_POPEDOM																						       ");
		sql.append("  			,0                          AS PRODUCENUM																							       ");
		sql.append("  			,0                          AS PRODUCENUM_L																							       ");
		sql.append("  			,0                          AS IN_SELLNUM																							       ");
		sql.append("  			,0                          AS IN_SELLNUM_L																							       ");
		sql.append("  			,0                          AS IN_SELLSUM																							       ");
		sql.append("  			,0                          AS IN_SELLSUM_L																							       ");
		sql.append("  			,0                          AS IN_TERMSTOCK																							       ");
		sql.append("  			,0                          AS IN_TERMSTOCK_L																							       ");
		sql.append("  			,SUM(IN_SELLNUM     )       AS IN_SELLNUMS																							       ");
		sql.append("  			,SUM(IN_SELLNUM_L   )       AS IN_SELLNUMS_L																							       ");
		sql.append("  			,SUM(IN_SELLSUM     )       AS IN_SELLSUMS																							       ");
		sql.append("  			,SUM(IN_SELLSUM_L   )       AS IN_SELLSUMS_L																							       ");
		sql.append("  		FROM																													       ");
		sql.append("  		(																													       ");
		sql.append("  			SELECT 																												       ");
		sql.append("  				C_BRAND								AS C_BRAND																		       ");
		sql.append("  				,FACT_POPEDOM						AS FACT_POPEDOM																			       ");
		sql.append("  				,SUM(OUT_FACT_NUM1-RECV_NUM1) 		AS IN_SELLNUM																					       ");
		sql.append("  				,SUM(OUT_FACT_NUM1_L-RECV_NUM1_L) 	AS IN_SELLNUM_L																					       ");
		sql.append("  				,SUM(OUT_FACT_SUM-RECV_SUM) 		AS IN_SELLSUM																					       ");
		sql.append("  				,SUM(OUT_FACT_SUM_L-RECV_SUM_L) 	AS IN_SELLSUM_L																					       ");
		sql.append("		FROM NICK_K_TJIN_Y_Q_M_TYPE_SELL  NK,NICK_TJYY_CIGARETTE SC           ");
		sql.append("		WHERE 1=1              ");
		sql.append("		 AND NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))         ");
		sql.append("  				AND TYPE IN ('0','1','2','5')																								       ");
		sql.append("  				AND I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')													       ");
		sql.append("  				AND Y = YEAR(CURRENT DATE-1 DAYS) AND M <= MONTH(CURRENT DATE-1 DAYS)																			       ");
		sql.append("  			GROUP BY C_BRAND, FACT_POPEDOM																									       ");
		sql.append("  		) K																													       ");
		sql.append("  		GROUP BY FACT_POPEDOM																											       ");
		sql.append("  	)																														       ");
		sql.append("  	GROUP BY FACT_POPEDOM 																												       ");
		sql.append("  	WITH ROLLUP																													       ");
		sql.append("  	) K																														       ");
		sql.append("  	LEFT JOIN																													       ");
		sql.append("  		(select fact_popedom,sum(BS_SELLNUM) as BS_SELLNUM,sum(BS_SELLNUM_L) as BS_SELLNUM_L								       ");
		sql.append("  		,sum(BS_SELLNUM_O) as BS_SELLNUM_O,sum(BS_SELLNUM_O_L) as BS_SELLNUM_O_L							       ");
		sql.append("  		from table											       ");
		sql.append("  	(																														       ");
		sql.append("  		SELECT																													       ");
		sql.append("  			NVL(FACT_POPEDOM, '00')															AS FACT_POPEDOM										       ");
		sql.append("  			,0 AS BS_SELLNUM ,0 AS BS_SELLNUM_L			       ");
		sql.append("  			,SUM(CASE WHEN I_PROVINCE = ORG_OWNPROVINCE THEN 0 ELSE SELLNUM END		) 	AS BS_SELLNUM_O																       ");
		sql.append("  			,SUM(CASE WHEN I_PROVINCE = ORG_OWNPROVINCE THEN 0 ELSE SELLNUM_L END	)  	AS BS_SELLNUM_O_L															       ");
		sql.append("  		FROM																													       ");
		sql.append("  			(																												       ");
		sql.append("  				SELECT 																											       ");
		sql.append("  								fact_popedom																						       ");
		sql.append("  					,I_PROVINCE	,																								       ");
		sql.append("  		ORG_OWNPROVINCE,BS_SELLNUM as SELLNUM, BS_SELLNUM_L as SELLNUM_L								       ");
		sql.append("  		FROM    (SELECT (case when o.FACT_POPEDOM is not null then o.FACT_POPEDOM else '20130001' end)as FACT_POPEDOM,				       ");
		sql.append("  		I_PROVINCE,SUM(BS_SELLNUM_Y_A)AS BS_SELLNUM,SUM(BS_SELLNUM_Y_AL)AS BS_SELLNUM_L			       ");
		sql.append("  		FROM    view_CIG_PROPERTY C										       ");
		sql.append("  		 full join (SELECT DISTINCT BRANCHCODE,FACT_POPEDOM FROM nick_VIEW_FACTORY_BRANCH) O								       ");
		sql.append("  		on(C.CIG_PRODUCER=O.BRANCHCODE)														       ");
		sql.append("  		,nick_K_DS_TJIB_Y_Q_M_ALL K,nick_TJYY_CIGARETTE SC 													       ");
		sql.append("  		WHERE   K.C_BRAND=C.C_BRAND AND C.CIG_PRODUCER <> '10000001'												       ");
		sql.append("  		AND C.CIG_PRODUCER <> '18000001' AND K.C_BRAND = sc.CIG_CODEBAR												       ");
		sql.append("  		and ((k.d_month<='201712' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_month>='201801' AND sc.is_bsnx='1')) 							       ");
		sql.append("  		AND K.Y = YEAR(CURRENT DATE-1 DAYS) AND K.M = MONTH(CURRENT DATE-1 DAYS)  and k.c_brand<>'6900000000001'		       ");
		sql.append("  		GROUP BY o.FACT_POPEDOM,I_PROVINCE)A,DW_T_STMA_ORGANIZATION S								       ");
		sql.append("  		WHERE    A.FACT_POPEDOM=S.ORG_CODE) aa GROUP BY  FACT_POPEDOM WITH ROLLUP union all							       ");
		sql.append("  		SELECT NVL(FACT_POPEDOM, '00') AS FACT_POPEDOM ,SUM(SELLNUM ) AS BS_SELLNUM 								       ");
		sql.append("  		 ,SUM(SELLNUM_L ) AS BS_SELLNUM_L ,0 AS BS_SELLNUM_O ,0 AS BS_SELLNUM_O_L					       ");
		sql.append("  		 FROM  ( SELECT fact_popedom,					       ");
		sql.append("  		I_PROVINCE,ORG_OWNPROVINCE,BS_SELLNUM as SELLNUM,BS_SELLNUM_L as SELLNUM_L							       ");
		sql.append("  		FROM    (SELECT (case when o.FACT_POPEDOM is not null then o.FACT_POPEDOM else '20130001' end)as FACT_POPEDOM,		       ");
		sql.append("  		I_PROVINCE,SUM(BS_SELLNUM_Y_A)AS BS_SELLNUM,SUM(BS_SELLNUM_Y_AL)AS BS_SELLNUM_L		       ");
		sql.append("  		FROM    view_CIG_PROPERTY C		       ");
		sql.append("  		 full join (SELECT DISTINCT BRANCHCODE,FACT_POPEDOM FROM nick_VIEW_FACTORY_BRANCH) O		       ");
		sql.append("  		on(C.CIG_PRODUCER=O.BRANCHCODE),nick_K_DS_TJIB_Y_Q_M_ALL K,nick_TJYY_CIGARETTE SC	       ");
		sql.append("  		WHERE   K.C_BRAND=C.C_BRAND AND K.C_BRAND = sc.CIG_CODEBAR	       ");
		sql.append("  		and ((k.d_month<='201712' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_month>='201801' AND sc.is_bsnx='1'))		       ");
		sql.append("  		 AND K.Y = YEAR(CURRENT DATE-1 DAYS) AND K.M = MONTH(CURRENT DATE-1 DAYS) and k.c_brand<>'6900000000001'		       ");
		sql.append("  		GROUP BY o.FACT_POPEDOM,I_PROVINCE)A,DW_T_STMA_ORGANIZATION S		       ");
		sql.append("  		WHERE    A.FACT_POPEDOM=S.ORG_CODE) aa GROUP BY  FACT_POPEDOM WITH ROLLUP ) t group by fact_popedom ) B	       ");
		sql.append("  	 ON K.FACT_POPEDOM = B.FACT_POPEDOM																										       ");
		sql.append("  																															       ");
		sql.append("  	LEFT JOIN MA_T_FACT_POP F																											       ");
		sql.append("  	ON K.FACT_POPEDOM = F.FACT_POP_CODE																										       ");
		sql.append("  	WHERE K.FACT_POPEDOM NOT IN('10000001', '18000001')																								       ");
		sql.append("  	ORDER BY DECIMAL(PRODUCENUM1) DESC																										       ");
		sql.append("  	WITH UR																														       ");

		log.info("总量情况工业模块表格查询sql为："+sql);
		return sql.toString();
	}
	
	/**
	 * 总量情况、工业模块<p>表格数据信息的查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<IndustryVO> getInTableInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<IndustryVO> list = new ArrayList<IndustryVO>();
		try {
			sql = getSqlTable();
			list = dbbean.executeQuery(sql, IndustryVO.class.getName());
		} catch (Exception e) {
			log.error("getInTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	/**
	 * 总量情况、工业模块<p>表格下钻价类查询sql获取
	 * @return String
	 */
	public static String getSqlJLTable(String code){
		StringBuffer sql = new StringBuffer();
		sql.append(" 	 select className, PRODUCENUM, PRODUCENUM_L, PRODUCENUM_GROWTH, PRODUCENUM_GROWTH_RATE, IN_SELLNUM, IN_SELLNUM_L, IN_SELLNUM_GROWTH         ");	
		sql.append(" 	 , IN_SELLNUM_GROWTH_RATE, IN_TERMSTOCK, IN_TERMSTOCK_L, IN_TERMSTOCK_GROWTH, IN_TERMSTOCK_GROWTH_RATE, SINGLE_SELLSUM, SINGLE_SELLSUM_L    ");	
		sql.append(" 	 , SINGLE_SELLSUM_GROWTH, SINGLE_SELLSUM_GROWTH_RATE, IN_SELLSUM, IN_SELLSUM_L, IN_SELLSUM_GROWTH, IN_SELLSUM_GROWTH_RATE                   ");	
		sql.append(" 	, EXTERNAL_MARKET_RATE, EXTERNAL_MARKET_L_RATE, EXTERNAL_MARKET_RATE_GROWTHFROM from (                                                     ");
		sql.append(" 	SELECT																																	");																													
		sql.append(" 	NVL(CLASS_NAME, '合  计') AS className																													");
		sql.append(" 	,CASE WHEN PRODUCENUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM					*1.0000/50000, 2), 16, 2),'999990.99') END 				AS PRODUCENUM                                                             	");
		sql.append(" 	,CASE WHEN PRODUCENUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 				AS PRODUCENUM_L                                                           	");
		sql.append(" 	,CASE WHEN (PRODUCENUM+PRODUCENUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 				AS PRODUCENUM_GROWTH                                                      	");
		sql.append(" 	,CASE WHEN (PRODUCENUM = 0 OR PRODUCENUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)*100.0000/DECODE(PRODUCENUM_L,0,1,PRODUCENUM_L), 2), 16, 2)) END AS PRODUCENUM_GROWTH_RATE							");
		sql.append(" 	,CASE WHEN IN_SELLNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_SELLNUM                                                             	");
		sql.append(" 	,CASE WHEN IN_SELLNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_SELLNUM_L                                                           	");
		sql.append(" 	,CASE WHEN (IN_SELLNUM+IN_SELLNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_SELLNUM - IN_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IN_SELLNUM_GROWTH											");
		sql.append(" 	,CASE WHEN (IN_SELLNUM = 0 OR IN_SELLNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IN_SELLNUM - IN_SELLNUM_L)*100.0000/DECODE(IN_SELLNUM_L,0,1,IN_SELLNUM_L), 2), 16, 2)) END AS IN_SELLNUM_GROWTH_RATE							");
		sql.append(" 	,CASE WHEN IN_TERMSTOCK = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	(IN_TERMSTOCK 		)						*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_TERMSTOCK                           	");
		sql.append(" 	,CASE WHEN IN_TERMSTOCK_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	(IN_TERMSTOCK_L 	)						*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_TERMSTOCK_L                         	");
		sql.append(" 	,CASE WHEN (IN_TERMSTOCK+IN_TERMSTOCK_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_TERMSTOCK - IN_TERMSTOCK_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IN_TERMSTOCK_GROWTH										");
		sql.append(" 	,CASE WHEN (IN_TERMSTOCK = 0 OR IN_TERMSTOCK_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IN_TERMSTOCK - IN_TERMSTOCK_L)*100.0000/DECODE(IN_TERMSTOCK_L,0,1,IN_TERMSTOCK_L), 2), 16, 2)) END AS IN_TERMSTOCK_GROWTH_RATE					");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM                                                                                   	");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_L                                                                                 	");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_GROWTH                                                                          	");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS SINGLE_SELLSUM_GROWTH_RATE			");
		sql.append(" 	,CASE WHEN IN_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 				AS IN_SELLSUM                                                             	");
		sql.append(" 	,CASE WHEN IN_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 				AS IN_SELLSUM_L                                                           	");
		sql.append(" 	,CASE WHEN (IN_SELLSUM+IN_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_SELLSUM - IN_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS IN_SELLSUM_GROWTH											");
		sql.append(" 	,CASE WHEN (IN_SELLSUM = 0 OR IN_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IN_SELLSUM - IN_SELLSUM_L)*100.0000/DECODE(IN_SELLSUM_L,0,1,IN_SELLSUM_L), 2), 16, 2)) END AS IN_SELLSUM_GROWTH_RATE							");
		sql.append(" 	,'-' AS EXTERNAL_MARKET_RATE											");
		sql.append(" 	,'-' AS EXTERNAL_MARKET_L_RATE										");
		sql.append(" 	,'-' AS EXTERNAL_MARKET_RATE_GROWTHFROM	");	

		sql.append("  	FROM																														 ");
		sql.append("  	(																														 ");
		sql.append("  	SELECT																														 ");
		sql.append("  		NVL(C_CLASS, '00')							AS C_CLASS																			 ");
		sql.append("  		,SUM(PRODUCENUM                         )	AS PRODUCENUM																						 ");
		sql.append("  	    ,SUM(PRODUCENUM_L                       )	AS PRODUCENUM_L																							 ");
		sql.append("  		,SUM(IN_SELLNUM                         )	AS IN_SELLNUM																						 ");
		sql.append("  		,SUM(IN_SELLNUM_L                       )	AS IN_SELLNUM_L																						 ");
		sql.append("  		,SUM(IN_TERMSTOCK   					)	AS IN_TERMSTOCK																				 ");
		sql.append("  		,SUM(IN_TERMSTOCK_L 					)	AS IN_TERMSTOCK_L																			 ");
		sql.append("  		,SUM(IN_SELLSUM                         )	AS IN_SELLSUM																						 ");
		sql.append("  		,SUM(IN_SELLSUM_L                       )	AS IN_SELLSUM_L																						 ");
		sql.append("  		,DECODE(SUM(IN_SELLNUMS	),0,0,SUM(IN_SELLSUMS	)*5.0000/SUM(IN_SELLNUMS		))   AS SINGLE_SELLSUM																 ");
		sql.append("  		,DECODE(SUM(IN_SELLNUMS_L),0,0,SUM(IN_SELLSUMS_L	)*5.0000/SUM(IN_SELLNUMS_L	))   AS SINGLE_SELLSUM_L															 ");
		sql.append("  	FROM																														 ");
		sql.append("  	(																														 ");
		sql.append("  																															 ");
		sql.append("  		SELECT 																													 ");
		sql.append("  			FACT_POPEDOM																											 ");
		sql.append("  			,C_CLASS																											 ");
		sql.append("  			,SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	)		AS PRODUCENUM													 ");
		sql.append("  			,SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	) 		AS PRODUCENUM_L														 ");
		sql.append("  			,0																					    AS IN_SELLNUM					 ");
		sql.append("  			,0																					    AS IN_SELLNUM_L					 ");
		sql.append("  			,0                                                                                      AS IN_SELLSUM																 ");
		sql.append("  			,0                                                                                      AS IN_SELLSUM_L																 ");
		sql.append("  			,SUM(TERMSTOCK1																	)       AS IN_TERMSTOCK								 ");
		sql.append("  			,SUM(TERMSTOCK1_P																)       AS IN_TERMSTOCK_L							 ");
		sql.append("            ,0 AS IN_SELLNUMS																										 ");
		sql.append("  			,0 AS IN_SELLNUMS_L																										 ");
		sql.append("  			,0 AS IN_SELLSUMS																										 ");
		sql.append("  			,0 AS IN_SELLSUMS_L																										 ");
		sql.append("		FROM NICK_K_TJIN_Y_ALL   NK,NICK_TJYY_CIGARETTE SC             ");
		sql.append("		WHERE 1=1              ");
		sql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))         ");
		sql.append("  			AND Y = YEAR(CURRENT DATE-1 DAYS)																								 ");
		sql.append("  		GROUP BY FACT_POPEDOM, C_CLASS																										 ");
		sql.append("					HAVING(		 ");
		sql.append("							SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	)>0		 ");
		sql.append("								OR	SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	)>0		 ");
		sql.append("								OR	SUM(TERMSTOCK1														)>0		 ");
		sql.append("								OR	SUM(TERMSTOCK1_P													)>0		 ");
		sql.append("								)		 ");
		sql.append("  																															 ");
		sql.append("  		UNION ALL																												 ");
		sql.append("  																															 ");
		sql.append("  																															 ");
		sql.append("  		SELECT 																													 ");
		sql.append("  			FACT_POPEDOM				AS FACT_POPEDOM																						 ");
		sql.append("  			,C_CLASS					AS C_CLASS																					 ");
		sql.append("  			,0                          AS PRODUCENUM																							 ");
		sql.append("  			,0                          AS PRODUCENUM_L																							 ");
		sql.append("  			,SUM(IN_SELLNUM     )       AS IN_SELLNUM																							 ");
		sql.append("  			,SUM(IN_SELLNUM_L   )       AS IN_SELLNUM_L																							 ");
		sql.append("  			,SUM(IN_SELLSUM     )       AS IN_SELLSUM																							 ");
		sql.append("  			,SUM(IN_SELLSUM_L   )       AS IN_SELLSUM_L																							 ");
		sql.append("  			,0                          AS IN_TERMSTOCK																							 ");
		sql.append("  			,0                          AS IN_TERMSTOCK_L																							 ");
		sql.append("                          ,0                          AS IN_SELLNUMS																						 ");
		sql.append("  			,0                          AS IN_SELLNUMS_L																							 ");
		sql.append("  			,0                          AS IN_SELLSUMS																							 ");
		sql.append("  			,0                          AS IN_SELLSUMS_L																							 ");
		sql.append("  																															 ");
		sql.append("  		FROM																													 ");
		sql.append("  		(																													 ");
		sql.append("  			SELECT 																												 ");
		sql.append("  				C_BRAND								AS C_BRAND																		 ");
		sql.append("  				,C_CLASS							AS C_CLASS																		 ");
		sql.append("  				,FACT_POPEDOM						AS FACT_POPEDOM																			 ");
		sql.append("  				,SUM(OUT_FACT_NUM1-RECV_NUM1) 		AS IN_SELLNUM																					 ");
		sql.append("  				,SUM(OUT_FACT_NUM1_L-RECV_NUM1_L) 	AS IN_SELLNUM_L																					 ");
		sql.append("  				,SUM(OUT_FACT_SUM-RECV_SUM) 		AS IN_SELLSUM																					 ");
		sql.append("  				,SUM(OUT_FACT_SUM_L-RECV_SUM_L) 	AS IN_SELLSUM_L																					 ");
		sql.append("		FROM NICK_K_TJIN_Y_Q_M_TYPE_SELL  NK,NICK_TJYY_CIGARETTE SC           ");
		sql.append("		WHERE 1=1              ");
		sql.append("		 AND NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))         ");
		sql.append("  				AND TYPE IN ('0','1','2','5')																								 ");
		sql.append("  				AND I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')													 ");
		sql.append("  				AND Y = YEAR(CURRENT DATE-1 DAYS) AND M <= MONTH(CURRENT DATE-1 DAYS)																			 ");
		sql.append("  			GROUP BY C_BRAND, C_CLASS, FACT_POPEDOM																								 ");
		sql.append("  											HAVING(	");
		sql.append("  													SUM(OUT_FACT_NUM1-RECV_NUM1)>0	");
		sql.append("  												OR	SUM(OUT_FACT_NUM1_L-RECV_NUM1_L)>0");
		sql.append("												OR	SUM(OUT_FACT_SUM-RECV_SUM)>0");
		sql.append("  												OR	SUM(OUT_FACT_SUM_L-RECV_SUM_L)>0");
		sql.append("  												)");
		sql.append("  		) K																													 ");
		sql.append("  		,(SELECT C_BRAND FROM NICK_CIG_PROPERTY ) P																						 ");
		sql.append("  		WHERE 1=1																												 ");
		sql.append("  			AND K.C_BRAND = P.C_BRAND																									 ");
		sql.append("  		GROUP BY FACT_POPEDOM, C_CLASS																										 ");
		sql.append("  		UNION ALL																												 ");
		sql.append("  																															 ");
		sql.append("  																															 ");
		sql.append("  		SELECT 																													 ");
		sql.append("  			FACT_POPEDOM				AS FACT_POPEDOM																						 ");
		sql.append("  			,C_CLASS					AS C_CLASS																					 ");
		sql.append("  			,0                          AS PRODUCENUM																							 ");
		sql.append("  			,0                          AS PRODUCENUM_L																							 ");
		sql.append("  			,0                          AS IN_SELLNUM																							 ");
		sql.append("  			,0                          AS IN_SELLNUM_L																							 ");
		sql.append("  			,0                          AS IN_SELLSUM																							 ");
		sql.append("  			,0                          AS IN_SELLSUM_L																							 ");
		sql.append("  			,0                          AS IN_TERMSTOCK																							 ");
		sql.append("  			,0                          AS IN_TERMSTOCK_L																							 ");
		sql.append("                          ,SUM(IN_SELLNUM     )       AS IN_SELLNUMS																						 ");
		sql.append("  			,SUM(IN_SELLNUM_L   )       AS IN_SELLNUMS_L																							 ");
		sql.append("  			,SUM(IN_SELLSUM     )       AS IN_SELLSUMS																							 ");
		sql.append("  			,SUM(IN_SELLSUM_L   )       AS IN_SELLSUMS_L																							 ");
		sql.append("  																															 ");
		sql.append("  		FROM																													 ");
		sql.append("  		(																													 ");
		sql.append("  			SELECT 																												 ");
		sql.append("  				C_BRAND								AS C_BRAND																		 ");
		sql.append("  				,C_CLASS							AS C_CLASS																		 ");
		sql.append("  				,FACT_POPEDOM						AS FACT_POPEDOM																			 ");
		sql.append("  				,SUM(OUT_FACT_NUM1-RECV_NUM1) 		AS IN_SELLNUM																					 ");
		sql.append("  				,SUM(OUT_FACT_NUM1_L-RECV_NUM1_L) 	AS IN_SELLNUM_L																					 ");
		sql.append("  				,SUM(OUT_FACT_SUM-RECV_SUM) 		AS IN_SELLSUM																					 ");
		sql.append("  				,SUM(OUT_FACT_SUM_L-RECV_SUM_L) 	AS IN_SELLSUM_L																					 ");
		sql.append("		FROM NICK_K_TJIN_Y_Q_M_TYPE_SELL  NK,NICK_TJYY_CIGARETTE SC           ");
		sql.append("		WHERE 1=1              ");
		sql.append("		 AND NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))         ");
		sql.append("  				AND TYPE IN ('0','1','2','5')																								 ");
		sql.append("  				AND I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')													 ");
		sql.append("  				AND Y = YEAR(CURRENT DATE-1 DAYS) AND M <= MONTH(CURRENT DATE-1 DAYS)																			 ");
		sql.append("  			GROUP BY C_BRAND, C_CLASS, FACT_POPEDOM																								 ");
		sql.append("				HAVING(		 ");
		sql.append("						SUM(OUT_FACT_NUM1-RECV_NUM1)>0		 ");
		sql.append("					OR	SUM(OUT_FACT_NUM1_L-RECV_NUM1_L)>0		 ");
		sql.append("					OR	SUM(OUT_FACT_SUM-RECV_SUM)>0		 ");
		sql.append("				OR	SUM(OUT_FACT_SUM_L-RECV_SUM_L)>0		 ");
		sql.append("					)		 ");
		sql.append("  		) K																													 ");
		sql.append("  		,(SELECT C_BRAND FROM NICK_CIG_PROPERTY ) P																						 ");
		sql.append("  		WHERE 1=1																												 ");
		sql.append("  			AND K.C_BRAND = P.C_BRAND																									 ");
		sql.append("  		GROUP BY FACT_POPEDOM, C_CLASS																										 ");
		sql.append("  	) K																														 ");
		sql.append("  	WHERE FACT_POPEDOM = '").append(code).append("'");
		sql.append("  	GROUP BY C_CLASS 																												 ");
		sql.append("  	WITH ROLLUP																													 ");
		sql.append("  	) K																														 ");
		sql.append("  	LEFT JOIN DW_T_CLASS_CODE F																											 ");
		sql.append("  	ON K.C_CLASS = F.CODE																												 ");
		sql.append("  	ORDER BY K.C_CLASS																												 ");
		sql.append("  																														 ");
		sql.append(" 	  		) where className not in ('无价类') WITH  ur          ");
		log.info("总量情况工业模块表格下钻价类sql=="+sql);
		return sql.toString();
	}

	
	/**
	 * 总量情况、工业模块<p>表格数据下价类信息的查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<IndustryVO> getJLTableInfo(String code,String dbName) {
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<IndustryVO> list = new ArrayList<IndustryVO>();
		try {
			sql = getSqlJLTable(code);
			list = dbbean.executeQuery(sql, IndustryVO.class.getName());
		} catch (Exception e) {
			log.error("getJLTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	/**
	 * 总量情况、工业模块<p>表格下钻企业查询sql获取
	 * @return String
	 */
	public static String getSqlQYTable(String code){
		StringBuffer sql = new StringBuffer();
		sql.append(" 	SELECT																															");
		sql.append(" 	NVL(O.PRODUCE_SHORTNAME, '合  计') AS factName																										");
		sql.append(" 	,DECODE(O.PRODUCE_SHORTNAME,NULL, 0,1) AS SORT_NO																									");
		sql.append(" 	,CASE WHEN PRODUCENUM1 = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM1					*1.0000/50000, 2), 16, 2),'999990.99') END 				AS PRODUCENUM                                                 	");
		sql.append(" 	,CASE WHEN PRODUCENUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 				AS PRODUCENUM_L                                               	");
		sql.append(" 	,CASE WHEN (PRODUCENUM1+PRODUCENUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 				AS PRODUCENUM_GROWTH                                          	");
		sql.append(" 	,CASE WHEN (PRODUCENUM1 = 0 OR PRODUCENUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)*100.0000/DECODE(PRODUCENUM_L,0,1,PRODUCENUM_L), 2), 16, 2)) END AS PRODUCENUM_GROWTH_RATE                                	");
		sql.append(" 	,CASE WHEN IN_SELLNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_SELLNUM                                                 	");
		sql.append(" 	,CASE WHEN IN_SELLNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_SELLNUM_L                                               	");
		sql.append(" 	,CASE WHEN (IN_SELLNUM+IN_SELLNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_SELLNUM - IN_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IN_SELLNUM_GROWTH                                                                  	");
		sql.append(" 	,CASE WHEN (IN_SELLNUM = 0 OR IN_SELLNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IN_SELLNUM - IN_SELLNUM_L)*100.0000/DECODE(IN_SELLNUM_L,0,1,IN_SELLNUM_L), 2), 16, 2)) END AS IN_SELLNUM_GROWTH_RATE                                	");
		sql.append(" 	,CASE WHEN IN_TERMSTOCK = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	(IN_TERMSTOCK 		)						*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_TERMSTOCK               	");
		sql.append(" 	,CASE WHEN IN_TERMSTOCK_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	(IN_TERMSTOCK_L 	)						*1.0000/50000, 2), 16, 2),'999990.99') END 				AS IN_TERMSTOCK_L             	");
		sql.append(" 	,CASE WHEN (IN_TERMSTOCK+IN_TERMSTOCK_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_TERMSTOCK - IN_TERMSTOCK_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IN_TERMSTOCK_GROWTH                                                        	");
		sql.append(" 	,CASE WHEN (IN_TERMSTOCK = 0 OR IN_TERMSTOCK_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IN_TERMSTOCK - IN_TERMSTOCK_L)*100.0000/DECODE(IN_TERMSTOCK_L,0,1,IN_TERMSTOCK_L), 2), 16, 2)) END AS IN_TERMSTOCK_GROWTH_RATE	              	");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM                                                                       	");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_L                                                                     	");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_GROWTH                                                              	");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS SINGLE_SELLSUM_GROWTH_RATE      	");
		sql.append(" 	,CASE WHEN IN_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 				AS IN_SELLSUM                                                 	");
		sql.append(" 	,CASE WHEN IN_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IN_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 				AS IN_SELLSUM_L                                               	");
		sql.append(" 	,CASE WHEN (IN_SELLSUM+IN_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_SELLSUM - IN_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS IN_SELLSUM_GROWTH                                                                  	");
		sql.append(" 	,CASE WHEN (IN_SELLSUM = 0 OR IN_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IN_SELLSUM - IN_SELLSUM_L)*100.0000/DECODE(IN_SELLSUM_L,0,1,IN_SELLSUM_L), 2), 16, 2)) END AS IN_SELLSUM_GROWTH_RATE                                	");
		sql.append(" 	,'-' AS EXTERNAL_MARKET_RATE																												");
		sql.append(" 	,'-' AS EXTERNAL_MARKET_L_RATE																												");
		sql.append(" 	,'-' AS EXTERNAL_MARKET_RATE_GROWTHFROM																											");

		sql.append("  	FROM																										 ");
		sql.append("  	(																										 ");
		sql.append("  	SELECT																										 ");
		sql.append("  		NVL(LAW_ORG, '00')							AS LAW_ORG															 ");
		sql.append("  		,SUM(PRODUCENUM                         )	AS PRODUCENUM1																		 ");
		sql.append("  	    ,SUM(PRODUCENUM_L                       )	AS PRODUCENUM_L																			 ");
		sql.append("  		,SUM(IN_SELLNUM                         )	AS IN_SELLNUM																		 ");
		sql.append("  		,SUM(IN_SELLNUM_L                       )	AS IN_SELLNUM_L																		 ");
		sql.append("  		,SUM(IN_TERMSTOCK   					)	AS IN_TERMSTOCK																 ");
		sql.append("  		,SUM(IN_TERMSTOCK_L 					)	AS IN_TERMSTOCK_L															 ");
		sql.append("  		,SUM(IN_SELLSUM                         )	AS IN_SELLSUM																		 ");
		sql.append("  		,SUM(IN_SELLSUM_L                       )	AS IN_SELLSUM_L																		 ");
		sql.append("  		,DECODE(SUM(IN_SELLNUMS	),0,0,SUM(IN_SELLSUMS	)*5.0000/SUM(IN_SELLNUMS		))   AS SINGLE_SELLSUM												 ");
		sql.append("  		,DECODE(SUM(IN_SELLNUMS_L),0,0,SUM(IN_SELLSUMS_L	)*5.0000/SUM(IN_SELLNUMS_L	))   AS SINGLE_SELLSUM_L											 ");
		sql.append("  	FROM																										 ");
		sql.append("  	(																										 ");
		sql.append("  																											 ");
		sql.append("  		SELECT 																									 ");
		sql.append("  			FACT_POPEDOM																							 ");
		sql.append("  			,LAW_ORG																							 ");
		sql.append("  			,SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	)		AS PRODUCENUM									 ");
		sql.append("  			,SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	) 		AS PRODUCENUM_L										 ");
		sql.append("  			,0																					    AS IN_SELLNUM	 ");
		sql.append("  			,0																					    AS IN_SELLNUM_L	 ");
		sql.append("  			,0                                                                                      AS IN_SELLSUM												 ");
		sql.append("  			,0                                                                                      AS IN_SELLSUM_L												 ");
		sql.append("  			,SUM(TERMSTOCK1																	)       AS IN_TERMSTOCK				 ");
		sql.append("  			,SUM(TERMSTOCK1_P																)       AS IN_TERMSTOCK_L			 ");
		sql.append("                          ,0 AS IN_SELLNUMS																						 ");
		sql.append("  			,0 AS IN_SELLNUMS_L																						 ");
		sql.append("  			,0 AS IN_SELLSUMS																						 ");
		sql.append("  			,0 AS IN_SELLSUMS_L																						 ");
		sql.append("		FROM NICK_K_TJIN_Y_ALL   NK,NICK_TJYY_CIGARETTE SC             ");
		sql.append("		WHERE 1=1              ");
		sql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))         ");
		sql.append("  			AND Y = YEAR(CURRENT DATE-1 DAYS)																				 ");
		sql.append("  		GROUP BY FACT_POPEDOM, LAW_ORG																						 ");
		sql.append("  	HAVING( 			 ");
		sql.append("  	  		SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	)>0	 			 ");
		sql.append("  	  		OR 	SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	)>0	 			 ");
		sql.append("  	  		OR  SUM(TERMSTOCK1																	)>0	 			 ");
		sql.append("  	 		OR  SUM(TERMSTOCK1_P																)>0	 			 ");
		sql.append("   		)			 ");
		sql.append("  		UNION ALL																								 ");
		sql.append("  																											 ");
		sql.append("  																											 ");
		sql.append("  		SELECT 																									 ");
		sql.append("  			FACT_POPEDOM				AS FACT_POPEDOM																		 ");
		sql.append("  			,LAW_ORG					AS LAW_ORG																	 ");
		sql.append("  			,0                          AS PRODUCENUM																			 ");
		sql.append("  			,0                          AS PRODUCENUM_L																			 ");
		sql.append("  			,SUM(IN_SELLNUM     )       AS IN_SELLNUM																			 ");
		sql.append("  			,SUM(IN_SELLNUM_L   )       AS IN_SELLNUM_L																			 ");
		sql.append("  			,SUM(IN_SELLSUM     )       AS IN_SELLSUM																			 ");
		sql.append("  			,SUM(IN_SELLSUM_L   )       AS IN_SELLSUM_L																			 ");
		sql.append("  			,0                          AS IN_TERMSTOCK																			 ");
		sql.append("  			,0                          AS IN_TERMSTOCK_L																			 ");
		sql.append("  			,0                          AS IN_SELLNUMS																			 ");
		sql.append("  			,0                          AS IN_SELLNUMS_L																			 ");
		sql.append("  			,0                          AS IN_SELLSUMS																			 ");
		sql.append("  			,0                          AS IN_SELLSUMS_L																			 ");
		sql.append("  																											 ");
		sql.append("  		FROM																									 ");
		sql.append("  		(																									 ");
		sql.append("  			SELECT 																								 ");
		sql.append("  				C_BRAND								AS C_BRAND														 ");
		sql.append("  				,LAW_ORG							AS LAW_ORG														 ");
		sql.append("  				,FACT_POPEDOM						AS FACT_POPEDOM															 ");
		sql.append("  				,SUM(OUT_FACT_NUM1-RECV_NUM1) 		AS IN_SELLNUM																	 ");
		sql.append("  				,SUM(OUT_FACT_NUM1_L-RECV_NUM1_L) 	AS IN_SELLNUM_L																	 ");
		sql.append("  				,SUM(OUT_FACT_SUM-RECV_SUM) 		AS IN_SELLSUM																	 ");
		sql.append("  				,SUM(OUT_FACT_SUM_L-RECV_SUM_L) 	AS IN_SELLSUM_L																	 ");
		sql.append("		FROM NICK_K_TJIN_Y_Q_M_TYPE_SELL  NK,NICK_TJYY_CIGARETTE SC           ");
		sql.append("		WHERE 1=1              ");
		sql.append("		 AND NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))         ");
		sql.append("  				AND TYPE IN ('0','2','5')																				 ");
		sql.append("  				AND I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')									 ");
		sql.append("  				AND Y = YEAR(CURRENT DATE-1 DAYS) AND M <= MONTH(CURRENT DATE-1 DAYS)															 ");
		sql.append("  			GROUP BY C_BRAND, LAW_ORG, FACT_POPEDOM																				 ");
		sql.append("  		) K																									 ");
		sql.append("  		GROUP BY FACT_POPEDOM, LAW_ORG		  ");
		sql.append(" 			HAVING(  ");
		sql.append(" 	SUM(IN_SELLNUM)>0  ");
		sql.append(" 	OR SUM(IN_SELLNUM_L)>0  ");
		sql.append(" 	OR SUM(IN_SELLSUM)>0  ");
		sql.append(" 	OR SUM(IN_SELLSUM_L)>0  ");
		sql.append(" 	)																 ");
		sql.append("  		UNION ALL																								 ");
		sql.append("  																											 ");
		sql.append("  																											 ");
		sql.append("  		SELECT 																									 ");
		sql.append("  			FACT_POPEDOM				AS FACT_POPEDOM																		 ");
		sql.append("  			,LAW_ORG					AS LAW_ORG																	 ");
		sql.append("  			,0                          AS PRODUCENUM																			 ");
		sql.append("  			,0                          AS PRODUCENUM_L																			 ");
		sql.append("  			,0                          AS IN_SELLNUM																			 ");
		sql.append("  			,0                          AS IN_SELLNUM_L																			 ");
		sql.append("  			,0                          AS IN_SELLSUM																			 ");
		sql.append("  			,0                          AS IN_SELLSUM_L																			 ");
		sql.append("  			,0                          AS IN_TERMSTOCK																			 ");
		sql.append("  			,0                          AS IN_TERMSTOCK_L																			 ");
		sql.append("  			,SUM(IN_SELLNUM     )       AS IN_SELLNUMS																			 ");
		sql.append("  			,SUM(IN_SELLNUM_L   )       AS IN_SELLNUMS_L																			 ");
		sql.append("  			,SUM(IN_SELLSUM     )       AS IN_SELLSUMS																			 ");
		sql.append("  			,SUM(IN_SELLSUM_L   )       AS IN_SELLSUMS_L																			 ");
		sql.append("  																											 ");
		sql.append("  		FROM																									 ");
		sql.append("  		(																									 ");
		sql.append("  			SELECT 																								 ");
		sql.append("  				C_BRAND								AS C_BRAND														 ");
		sql.append("  				,LAW_ORG							AS LAW_ORG														 ");
		sql.append("  				,FACT_POPEDOM						AS FACT_POPEDOM															 ");
		sql.append("  				,SUM(OUT_FACT_NUM1-RECV_NUM1) 		AS IN_SELLNUM																	 ");
		sql.append("  				,SUM(OUT_FACT_NUM1_L-RECV_NUM1_L) 	AS IN_SELLNUM_L																	 ");
		sql.append("  				,SUM(OUT_FACT_SUM-RECV_SUM) 		AS IN_SELLSUM																	 ");
		sql.append("  				,SUM(OUT_FACT_SUM_L-RECV_SUM_L) 	AS IN_SELLSUM_L																	 ");
		sql.append("		FROM NICK_K_TJIN_Y_Q_M_TYPE_SELL  NK,NICK_TJYY_CIGARETTE SC           ");
		sql.append("		WHERE 1=1              ");
		sql.append("		 AND NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))         ");
		sql.append("  				AND TYPE IN ('0','1','2','5')																				 ");
		sql.append("  				AND I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')									 ");
		sql.append("  				AND Y = YEAR(CURRENT DATE-1 DAYS) AND M <= MONTH(CURRENT DATE-1 DAYS)															 ");
		sql.append("  			GROUP BY C_BRAND, LAW_ORG, FACT_POPEDOM																				 ");
		sql.append("  		) K																									 ");
		sql.append("  		GROUP BY FACT_POPEDOM, LAW_ORG																						 ");
		sql.append("  	) K																										 ");
		sql.append("  	WHERE FACT_POPEDOM = '").append(code).append("'");
		sql.append("  	GROUP BY LAW_ORG																								 ");
		sql.append("  	WITH ROLLUP																									 ");
		sql.append("  	) K																										 ");
		sql.append("  																											 ");
		sql.append("  	LEFT JOIN (SELECT DISTINCT PRODUCE_CODE, PRODUCE_SHORTNAME FROM DIM_PRODUCE_ORG) O																 ");
		sql.append("  	ON K.LAW_ORG = O.PRODUCE_CODE																							 ");
		sql.append("  	ORDER BY SORT_NO ASC,PRODUCENUM DESC																						 ");
		sql.append("  	WITH UR																										 ");

		log.info("总量情况工业模块表格下钻企业sql=="+sql);
		return sql.toString();
	}

	
	/**
	 * 总量情况、工业模块<p>表格数据下钻企业信息的查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<IndustryVO> getQYTableInfo(String code,String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<IndustryVO> list = new ArrayList<IndustryVO>();
		try {
			sql = getSqlQYTable(code);
			list = dbbean.executeQuery(sql, IndustryVO.class.getName());
		} catch (Exception e) {
			log.error("getQYTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}

}
