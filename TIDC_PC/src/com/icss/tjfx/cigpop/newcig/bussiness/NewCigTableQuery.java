package com.icss.tjfx.cigpop.newcig.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.util.StringUtil;
import com.icss.tjfx.cigpop.newcig.vo.CigPopNewTableVO;

/**
 * 卷烟品牌业务模型
 * 
 * @author lcx
 * @since July 25, 2017
 * @version 1.0
 * 
 * */
public class NewCigTableQuery extends BaseBusiness {

	private static Log log = LogFactory.getLog(NewCigTableQuery.class);

	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<CigPopNewTableVO> cigPopTableVOlist = new ArrayList<CigPopNewTableVO>();
		try {
			String type=request.getParameter("type");								//获取下钻的类型
			String code=request.getParameter("code");								//获取编码
			if (type.equals("dq")) {
				cigPopTableVOlist = getDQTableInfo(code, "dw");	
			}else {
				cigPopTableVOlist = getTableInfo("dw");
			}
		} catch (Exception e) {
			log.info("*************************");
			log.info("卷烟品牌新品sql执行异常:" + e);
			log.info("*************************");
			log.error("卷烟品牌新品sql执行异常", e);
		} 
		
		String result = StringUtil.getJson(cigPopTableVOlist);
		log.info("卷烟品牌新品查询结果" + StringUtil.getJson(cigPopTableVOlist));

		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", result);

		return request;
	}



	/**
	 * 新品表格
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<CigPopNewTableVO> getDQTableInfo(String code ,String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<CigPopNewTableVO> list = new ArrayList<CigPopNewTableVO>();
		try {
			sql = getTableDQSql(code);
			list = dbbean.executeQuery(sql, CigPopNewTableVO.class.getName());
		} catch (Exception e) {
			log.error("getDQTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}

	private String getTableDQSql(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append(" 	SELECT																																																	");
		sql.append(" 	NVL(PROV_ORG_NAME, '合  计') AS PROV_NAME																																													");
		sql.append(" 	,DECODE(PRODUCENUM, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	PRODUCENUM					*1.0000/50000, 2), 16, 2),'999990.99')) 				AS PRODUCENUM																									");
		sql.append(" 	,DECODE(PRODUCENUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	PRODUCENUM_L				*1.0000/50000, 2), 16, 2),'999990.99')) 				AS PRODUCENUM_L																										");
		sql.append(" 	,DECODE((PRODUCENUM+PRODUCENUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99')) 	AS PRODUCENUM_GROWTH																												");
		sql.append(" 	,DECODE(PRODUCENUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE(PRODUCENUM_L,0,0,NULL,0,(PRODUCENUM - PRODUCENUM_L)*100.0000/PRODUCENUM_L)), 2), 16, 2),'999990.99')) AS PRODUCENUM_GROWTH_RATE																						");
		sql.append(" 	,DECODE(BS_SELLNUM, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99')) 				AS BS_SELLNUM																									");
		sql.append(" 	,DECODE(BS_SELLNUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99')) 				AS BS_SELLNUM_L																										");
		sql.append(" 	,DECODE((BS_SELLNUM+BS_SELLNUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99')) 	AS BS_SELLNUM_GROWTH																												");
		sql.append(" 	,DECODE(BS_SELLNUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE(BS_SELLNUM_L,0,0,NULL,0,(BS_SELLNUM - BS_SELLNUM_L)*100.0000/BS_SELLNUM_L)), 2), 16, 2),'999990.99')) AS BS_SELLNUM_GROWTH_RATE																						");
		sql.append(" 	,DECODE((IN_TERMSTOCKNUM+BS_TERMSTOCKNUM), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	(IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	)		*1.0000/50000, 2), 16, 2),'999990.99')) 	AS IB_TERMSTOCKNUM                                                      															");
		sql.append(" 	,DECODE((IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	(IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	)		*1.0000/50000, 2), 16, 2),'999990.99')) 	AS IB_TERMSTOCKNUM_L                                            															");
		sql.append(" 	,DECODE((IN_TERMSTOCKNUM+BS_TERMSTOCKNUM+IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(((IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	) - (IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	))	*1.0000/50000, 2), 16, 2),'999990.99')) 	AS IB_TERMSTOCKNUM_GROWTH        											");
		sql.append(" 	,DECODE((IN_TERMSTOCKNUM+BS_TERMSTOCKNUM), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE((IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	),0,0,NULL,0,((IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	) - (IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	))*100.0000/(IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	))), 2), 16, 2),'999990.99')) AS IB_TERMSTOCKNUM_GROWTH_RATE		");
		sql.append(" 	,DECODE(BS_SELLSUM, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99')) 	AS BS_SELLSUM                                                                        																			");
		sql.append(" 	,DECODE(BS_SELLSUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99')) 	AS BS_SELLSUM_L                                                                              																			");
		sql.append(" 	,DECODE((BS_SELLSUM+BS_SELLSUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99')) 	AS BS_SELLSUM_GROWTH                                                                         																			");
		sql.append(" 	,DECODE(BS_SELLSUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE(BS_SELLSUM_L,0,0,NULL,0,NVL((BS_SELLSUM - BS_SELLSUM_L),0)*100.0000/BS_SELLSUM_L)), 2), 16, 2),'999990.99')) AS BS_SELLSUM_GROWTH_RATE                     																			");
		sql.append(" 	,DECODE(SINGLE_SELLSUM, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99')) AS SINGLE_SELLSUM                                                                                 																			");
		sql.append(" 	,DECODE(SINGLE_SELLSUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99')) AS SINGLE_SELLSUM_L                                                                       																			");
		sql.append(" 	,DECODE((SINGLE_SELLSUM+SINGLE_SELLSUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99')) 	AS SINGLE_SELLSUM_GROWTH                                                                      																			");
		sql.append(" 	,DECODE(SINGLE_SELLSUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE(SINGLE_SELLSUM_L,0,0,NULL,0,(SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/SINGLE_SELLSUM_L)), 2), 16, 2),'999990.99')) AS SINGLE_SELLSUM_GROWTH_RATE    																			");

		sql.append("  	FROM																																										  ");
		sql.append("  	(																																										  ");
		sql.append("  	SELECT																																										  ");
		sql.append("  		NVL(I_PROVINCE, '00')             AS I_PROVINCE																																				  ");
		sql.append("  	    ,SUM(PRODUCENUM            ) AS PRODUCENUM																																					  ");
		sql.append("  	    ,SUM(PRODUCENUM_L          ) AS PRODUCENUM_L																																				  ");
		sql.append("  	    ,SUM(IN_TERMSTOCKNUM       ) AS IN_TERMSTOCKNUM																																				  ");
		sql.append("  	    ,SUM(IN_TERMSTOCKNUM_L     ) AS IN_TERMSTOCKNUM_L																																				  ");
		sql.append("  	    ,SUM(BS_SELLNUM            ) AS BS_SELLNUM																																					  ");
		sql.append("  	    ,SUM(BS_SELLNUM_L          ) AS BS_SELLNUM_L																																				  ");
		sql.append("  	    ,SUM(BS_SELLSUM            ) AS BS_SELLSUM																																					  ");
		sql.append("  	    ,SUM(BS_SELLSUM_L          ) AS BS_SELLSUM_L																																				  ");
		sql.append("  		,DECODE(SUM(BS_SELLNUM		),0,0,NULL,0,SUM(BS_SELLSUM		)*5.0000/SUM(BS_SELLNUM		)) 	AS SINGLE_SELLSUM																									  ");
		sql.append("  		,DECODE(SUM(BS_SELLNUM_L	),0,0,NULL,0,SUM(BS_SELLSUM_L	)*5.0000/SUM(BS_SELLNUM_L	)) 	AS SINGLE_SELLSUM_L																										  ");
		sql.append("  		,SUM(BS_TERMSTOCKNUM       ) AS BS_TERMSTOCKNUM																																				  ");
		sql.append("  	    ,SUM(BS_TERMSTOCKNUM_L     ) AS BS_TERMSTOCKNUM_L																																				  ");
		sql.append("  	FROM																																										  ");
		sql.append("  	(																																										  ");
		//--工业																																									  ");
		sql.append("  		SELECT 																																									  ");
		sql.append("  		CASE WHEN I_PROVINCE='990000' THEN'110000'ELSE 	I_PROVINCE END AS I_PROVINCE							  ");
		sql.append("  			,SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	)	  AS PRODUCENUM																										  ");
		sql.append("  			,SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	)	  AS PRODUCENUM_L																										  ");
		sql.append("  			,SUM(TERMSTOCK1																	)     AS IN_TERMSTOCKNUM																			  ");
		sql.append("  			,SUM(TERMSTOCK1_P																)     AS IN_TERMSTOCKNUM_L																			  ");
		sql.append("  			,0				                                                                      AS BS_SELLNUM																										  ");
		sql.append("  			,0				                                                                      AS BS_SELLNUM_L																										  ");
		sql.append("  			,0				                                                                      AS BS_SELLSUM																										  ");
		sql.append("  			,0				                                                                      AS BS_SELLSUM_L																										  ");
		sql.append("  			,0				                                                                      AS BS_TERMSTOCKNUM																									  ");
		sql.append("  			,0				                                                                      AS BS_TERMSTOCKNUM_L																									  ");
		sql.append("  		FROM NICK_K_TJIN_Y_ALL	NK,NICK_TJYY_CIGARETTE SC																									  ");
		sql.append("  		WHERE 1=1																																								  ");
		sql.append("  			AND NK.C_BRAND = SC.cig_codebar																												  ");
		sql.append("  			and ((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))														  ");
		sql.append("  			AND C_BRAND = '").append(code).append("'");
		sql.append("  			AND Y = YEAR(CURRENT DATE-1 DAYS)																																					  ");
		sql.append("  		GROUP BY I_PROVINCE																																							  ");
		sql.append("  			HAVING(                                          ");
		sql.append("  			SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	)>0");
		sql.append("  			OR  SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	)>0");
		sql.append("  			OR  SUM(TERMSTOCK1)>0");
		sql.append("  			or  SUM(TERMSTOCK1_P)>0");
		sql.append("  			)																																							  ");
		sql.append("  		UNION ALL																																							  ");
		sql.append("  																																											  ");
		//--商业，省外依存度																																							  ");
		sql.append("  		SELECT 																																									  ");
		sql.append("  			I_PROVINCE			as I_PROVINCE																																				  ");
		sql.append("  			,0                                              AS PRODUCENUM																																	  ");
		sql.append("  			,0                                              AS PRODUCENUM_L																																	  ");
		sql.append("  			,0                                              AS IN_TERMSTOCKNUM																																  ");
		sql.append("  			,0                                              AS IN_TERMSTOCKNUM_L																																  ");
		sql.append("  			,SUM(PRINT_NUM1 	- RBACK_NUM1	)   AS BS_SELLNUM																																  ");
		sql.append("  			,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	)   AS BS_SELLNUM_L																																  ");
		sql.append("  			,SUM(PRINT_SUM 		- RBACK_SUM		)   AS BS_SELLSUM																															  ");
		sql.append("  			,SUM(PRINT_SUM_P 	- RBACK_SUM_P	)   AS BS_SELLSUM_L																																  ");
		sql.append("  			,SUM(TERM_STOCK1							)	AS BS_TERMSTOCKNUM																												  ");
		sql.append("  			,SUM(TERM_STOCK1_P							)	AS BS_TERMSTOCKNUM_L																												  ");
		sql.append("  		FROM NICK_K_TJBS_Y_ALL 	NK,NICK_TJYY_CIGARETTE SC																				  ");
		sql.append("  		WHERE 1=1																																								  ");
		sql.append("  			AND NK.C_BRAND = SC.cig_codebar																							  ");
		sql.append("  		and ((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))					  ");
		sql.append("  			AND C_BRAND = '").append(code).append("'");
		sql.append("  			AND Y = YEAR(CURRENT DATE-1 DAYS)																																					  ");
		sql.append("  		GROUP BY I_PROVINCE	            ");
		sql.append("  		HAVING(      ");
		sql.append("  					SUM(PRINT_NUM1 	- RBACK_NUM1	)>0      ");
		sql.append("  					OR SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	)>0      ");
		sql.append("  					OR SUM(PRINT_SUM 		- RBACK_SUM		)>0      ");
		sql.append("  				OR SUM(PRINT_SUM_P 	- RBACK_SUM_P	)>0      ");
		sql.append("  				OR SUM(TERM_STOCK1)>0      ");
		sql.append("  				OR SUM(TERM_STOCK1_P)>0      ");
		sql.append("  				)		      																																				  ");
		sql.append("  		)				 																																				  ");
		sql.append("  		GROUP BY I_PROVINCE																																							  ");
		sql.append("  		WITH ROLLUP																																								  ");
		sql.append("  	) K																																										  ");
		sql.append("  	LEFT JOIN MA_T_PROV_ORG O																																							  ");
		sql.append("  	ON K.I_PROVINCE = O.PROV_CODE																																							  ");
		sql.append("  	ORDER BY DECODE(PROV_ORG_NAME,NULL,0,1) ASC, 6 DESC																																					  ");
		sql.append("  	WITH UR																																										  ");

		log.info("卷烟品牌新品地区表格下钻规格数据处理sql: " + sql.toString());
		return sql.toString();
	}

	/**
	 * 新品表格
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<CigPopNewTableVO> getTableInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<CigPopNewTableVO> list = new ArrayList<CigPopNewTableVO>();
		try {
			sql = getTableSql();
			list = dbbean.executeQuery(sql, CigPopNewTableVO.class.getName());
		} catch (Exception e) {
			log.error("getTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}

	private String getTableSql() {
		StringBuffer sql = new StringBuffer();
		sql.append("		select xz_parameter			, CIG_NAME				, PRODUCENUM				, PRODUCENUM_L			, PRODUCENUM_GROWTH			     ");	
		sql.append("		, PRODUCENUM_GROWTH_RATE		, BS_SELLNUM				, BS_SELLNUM_L			, BS_SELLNUM_GROWTH			                         ");	
		sql.append("		, BS_SELLNUM_GROWTH_RATE		, IB_TERMSTOCKNUM                     , IB_TERMSTOCKNUM_L                                                ");	
		sql.append("		, IB_TERMSTOCKNUM_GROWTH        	, IB_TERMSTOCKNUM_GROWTH_RATE		, BS_SELLSUM				, BS_SELLSUM_L			             ");	
		sql.append("		, BS_SELLSUM_GROWTH			, BS_SELLSUM_GROWTH_RATE		, SINGLE_SELLSUM			, SINGLE_SELLSUM_L                               ");	
		sql.append("		, SINGLE_SELLSUM_GROWTH		, SINGLE_SELLSUM_GROWTH_RATE          , EXTERNAL_MARKET_RATE		, EXTERNAL_MARKET_L_RATE		         ");	
		sql.append("		, EXTERNAL_MARKET_RATE_GROWTHFROM	, CITY_COVER_RATE			, CITY_COVER_RATE_L			, CITY_COVER_RATE_GROWTHA		             ");	
		sql.append("		, PRODUCER_NAME			, CLASS_NAME				, TRANSFERPRICE 			, WHOLESALEPRICE			, SUGGESTEDRETAILPRICE  	 ");	
		sql.append("		, 			IS_BEAD		, 			IS_THIN		, 			IS_SHORT from (                                                              ");
		sql.append(" 	SELECT																																																			");
		sql.append(" 	K.C_BRAND AS xz_parameter																																																	");
		sql.append(" 	,NVL(BRAND_NAME, '合  计') AS CIG_NAME																																														");
		sql.append(" 	,DECODE(PRODUCENUM1, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	PRODUCENUM1					*1.000000/50000, 2), 16, 2),'999990.99')) 				AS PRODUCENUM																											");
		sql.append(" 	,DECODE(PRODUCENUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	PRODUCENUM_L				*1.000000/50000, 2), 16, 2),'999990.99')) 				AS PRODUCENUM_L																												");
		sql.append(" 	,DECODE((PRODUCENUM1+PRODUCENUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99')) 	AS PRODUCENUM_GROWTH																													");
		sql.append(" 	,CASE WHEN PRODUCENUM_L=0 OR PRODUCENUM1=0 THEN '-' ELSE  TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE(PRODUCENUM_L,0,0,NULL,0,(PRODUCENUM1 - PRODUCENUM_L)*100.0000/PRODUCENUM_L)), 2), 16, 2),'999990.99') END AS PRODUCENUM_GROWTH_RATE																								");
		sql.append(" 	,DECODE(BS_SELLNUM, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99')) 				AS BS_SELLNUM																											");
		sql.append(" 	,DECODE(BS_SELLNUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99')) 				AS BS_SELLNUM_L																												");
		sql.append(" 	,DECODE((BS_SELLNUM+BS_SELLNUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99')) 	AS BS_SELLNUM_GROWTH																														");
		sql.append(" 	,CASE WHEN BS_SELLNUM_L=0 OR BS_SELLNUM=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE(BS_SELLNUM_L,0,0,NULL,0,(BS_SELLNUM - BS_SELLNUM_L)*100.0000/BS_SELLNUM_L)), 2), 16, 2),'999990.99') END AS BS_SELLNUM_GROWTH_RATE																								");
		sql.append(" 	,DECODE((IN_TERMSTOCKNUM+BS_TERMSTOCKNUM), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	(IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	)		*1.0000/50000, 2), 16, 2),'999990.99')) 	AS IB_TERMSTOCKNUM                                                      																	");
		sql.append(" 	,DECODE((IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	(IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	)		*1.0000/50000, 2), 16, 2),'999990.99')) 	AS IB_TERMSTOCKNUM_L                                            																	");
		sql.append(" 	,DECODE((IN_TERMSTOCKNUM+BS_TERMSTOCKNUM+IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(((IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	) - (IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	))	*1.0000/50000, 2), 16, 2),'999990.99')) 	AS IB_TERMSTOCKNUM_GROWTH        													");
		sql.append(" 	,CASE WHEN (IN_TERMSTOCKNUM+BS_TERMSTOCKNUM)=0 OR (IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE((IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	),0,0,NULL,0,((IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	) - (IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	))*100.0000/(IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	))), 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM_GROWTH_RATE				");
		sql.append(" 	,DECODE(BS_SELLSUM, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99')) 	AS BS_SELLSUM																														");
		sql.append(" 	,DECODE(BS_SELLSUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99')) 	AS BS_SELLSUM_L																															");
		sql.append(" 	,DECODE((BS_SELLSUM+BS_SELLSUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99')) 	AS BS_SELLSUM_GROWTH																														");
		sql.append(" 	,DECODE(BS_SELLSUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE(BS_SELLSUM_L,0,0,NULL,0,NVL((BS_SELLSUM - BS_SELLSUM_L),0)*100.0000/BS_SELLSUM_L)), 2), 16, 2),'999990.99')) AS BS_SELLSUM_GROWTH_RATE																							");
		sql.append(" 	,DECODE(SINGLE_SELLSUM, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99')) AS SINGLE_SELLSUM																															");
		sql.append(" 	,DECODE(SINGLE_SELLSUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99')) AS SINGLE_SELLSUM_L                                                                                  																			");
		sql.append(" 	,DECODE((SINGLE_SELLSUM+SINGLE_SELLSUM_L), NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99')) 	AS SINGLE_SELLSUM_GROWTH																														");
		sql.append(" 	,DECODE(SINGLE_SELLSUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE(SINGLE_SELLSUM_L,0,0,NULL,0,(SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/SINGLE_SELLSUM_L)), 2), 16, 2),'999990.99')) AS SINGLE_SELLSUM_GROWTH_RATE               																			");
		sql.append(" 	,DECODE(BS_SELLNUM, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM,0,0,NULL,0,BS_SELLNUM_O*100.0000/BS_SELLNUM), 2), 16, 2),'999990.99')) AS EXTERNAL_MARKET_RATE																														");
		sql.append(" 	,DECODE(BS_SELLNUM_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM_L,0,0,NULL,0,BS_SELLNUM_O_L*100.0000/BS_SELLNUM_L), 2), 16, 2),'999990.99')) AS EXTERNAL_MARKET_L_RATE																													");
		sql.append(" 	,DECODE(BS_SELLNUM, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM,0,0,NULL,0,BS_SELLNUM_O*100.0000/BS_SELLNUM) - DECODE(BS_SELLNUM_L,0,0,NULL,0,BS_SELLNUM_O_L*100.0000/BS_SELLNUM_L), 2), 16, 2),'999990.99')) AS EXTERNAL_MARKET_RATE_GROWTHFROM																				");
		sql.append(" 	,DECODE(ORG_COVER_RATE, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(ORG_COVER_RATE			,2),16,2),'999990.99')) AS CITY_COVER_RATE																																		");
		sql.append(" 	,DECODE(ORG_COVER_RATE_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(NVL(ORG_COVER_RATE_L,0)	,2),16,2),'999990.99')) AS CITY_COVER_RATE_L																																		");
		sql.append(" 	,DECODE(ORG_COVER_RATE_L, NULL, '-', 0, '-', TO_CHAR(DECIMAL(ROUND(DC_FUNC_TRANS_RATE1(DECODE(ORG_COVER_RATE_L,NULL,0,0,0,(ORG_COVER_RATE-ORG_COVER_RATE_L)*100.0000/ORG_COVER_RATE_L)),2),16,2),'999990.99')) AS CITY_COVER_RATE_GROWTHA																						");
		sql.append(" 	,NVL(DECODE(C.CIG_PRODUCER,'10000001','国家局',O.PRODUCE_SHORTNAME), '-') AS PRODUCER_NAME																																								");
		sql.append(" 	,NVL(N.CLASS_NAME, '-') AS CLASS_NAME																																															");
		sql.append(" 	,case when substr(NVL(CHAR(TRANSFERPRICE), '-'),1,1)='.' then '0'||CHAR(TRANSFERPRICE) else NVL(CHAR(TRANSFERPRICE), '-') end AS TRANSFERPRICE 			");
		sql.append(" 	,case when substr(NVL(CHAR(P.WHOLESALEPRICE), '-'),1,1)='.' then '0'||CHAR(P.WHOLESALEPRICE) else NVL(CHAR(P.WHOLESALEPRICE), '-') end AS WHOLESALEPRICE								");
		sql.append(" 	,case when substr(NVL(CHAR(SUGGESTEDRETAILPRICE), '-'),1,1)='.' then '0'||CHAR(SUGGESTEDRETAILPRICE) else NVL(CHAR(SUGGESTEDRETAILPRICE), '-') end AS SUGGESTEDRETAILPRICE  		");
		sql.append(" 	,CASE O.IS_BEAD 	WHEN '0' THEN'-' WHEN '1' THEN '1' ELSE '-' END	AS 			IS_BEAD																																							");
		sql.append(" 	,CASE O.IS_THIN 	WHEN '0' THEN'-' WHEN '1' THEN '1' ELSE '-' END	AS 			IS_THIN																																							");
		sql.append(" 	,CASE O.IS_SHORT	WHEN '0' THEN'-' WHEN '1' THEN '1' ELSE '-' END AS 			IS_SHORT																																						");
		sql.append(" 	FROM																																																			");
		sql.append(" 	(																																																			");
		sql.append(" 	SELECT																																																			");
		sql.append(" 		NVL(K.C_BRAND, '00')	 AS C_BRAND																																														");
		sql.append(" 		,SUM(PRODUCENUM1         ) AS PRODUCENUM1																																													");
		sql.append(" 		,SUM(PRODUCENUM_L        ) AS PRODUCENUM_L																																													");
		sql.append(" 	    ,SUM(IN_TERMSTOCKNUM     ) AS IN_TERMSTOCKNUM																																													");
		sql.append(" 	    ,SUM(IN_TERMSTOCKNUM_L   ) AS IN_TERMSTOCKNUM_L																																													");
		sql.append(" 		,SUM(BS_SELLNUM          ) AS BS_SELLNUM																																													");
		sql.append(" 	    ,SUM(BS_SELLNUM_L        ) AS BS_SELLNUM_L																																														");
		sql.append(" 	    ,SUM(BS_SELLSUM          ) AS BS_SELLSUM																																														");
		sql.append(" 	    ,SUM(BS_SELLSUM_L        ) AS BS_SELLSUM_L																																														");
		sql.append(" 	    ,SUM(SINGLE_SELLSUM      ) AS SINGLE_SELLSUM																																													");
		sql.append(" 	    ,SUM(SINGLE_SELLSUM_L    ) AS SINGLE_SELLSUM_L																																													");
		sql.append(" 	    ,SUM(BS_SELLNUM_O        ) AS BS_SELLNUM_O																																														");
		sql.append(" 	    ,SUM(BS_SELLNUM_O_L      ) AS BS_SELLNUM_O_L																																													");
		sql.append(" 	    ,SUM(BS_TERMSTOCKNUM     ) AS BS_TERMSTOCKNUM																																													");
		sql.append(" 	    ,SUM(BS_TERMSTOCKNUM_L   ) AS BS_TERMSTOCKNUM_L																																													");
		sql.append(" 	    ,SUM(ORG_COVER_RATE      ) AS ORG_COVER_RATE																																													");
		sql.append(" 		,SUM(ORG_COVER_RATE_L    ) AS ORG_COVER_RATE_L																																													");
		sql.append(" 	FROM																																																			");
		sql.append(" 	(																																																			");
		sql.append(" 																																																				");
		sql.append(" 		SELECT 																																																		");
		sql.append(" 			NVL(C_BRAND, '00')																		AS C_BRAND																												");
		sql.append(" 			,SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	)		AS PRODUCENUM1																																		");
		sql.append(" 			,SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	) 		AS PRODUCENUM_L																																			");
		sql.append(" 			,SUM(TERMSTOCK1																	)       AS IN_TERMSTOCKNUM																												");
		sql.append(" 			,SUM(TERMSTOCK1_P																)       AS IN_TERMSTOCKNUM_L																												");
		sql.append(" 		FROM NICK_K_TJIN_Y_ALL	NK,NICK_TJYY_CIGARETTE SC																																	");
		sql.append(" 		WHERE 1=1																																																	");
		sql.append(" 			AND NK.C_BRAND = SC.cig_codebar																																		");
		sql.append(" 			and ((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))																				");
		sql.append(" 			AND C_BRAND IN (SELECT C_BRAND FROM DW_T_NEW_PRODUCT)																																											");
		sql.append(" 			AND Y = YEAR(CURRENT DATE -1 DAYS)																																														");
		sql.append(" 		GROUP BY C_BRAND																																																");
		sql.append(" 		WITH ROLLUP																																																		");
		sql.append(" 		HAVING																																																		");
		sql.append(" 		(																																																		");
		sql.append(" 			SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	) <> 0																																					");
		sql.append(" 			OR SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	) <>0																																						");
		sql.append(" 		)																																																		");
		sql.append(" 	) K																																																			");
		sql.append(" 	LEFT JOIN																																																		");
		sql.append(" 	(																																																			");
		sql.append(" 																																																				");
		sql.append(" 		SELECT																																																		");
		sql.append(" 			NVL(K.C_BRAND, '00')																AS C_BRAND																														");
		sql.append(" 			,SUM(SELLNUM                            										)	AS BS_SELLNUM																																");
		sql.append(" 			,SUM(SELLNUM_L                          										)	AS BS_SELLNUM_L																																");
		sql.append(" 			,SUM(SELLSUM                            										)	AS BS_SELLSUM																																");
		sql.append(" 			,SUM(SELLSUM_L                          										)	AS BS_SELLSUM_L																																");
		sql.append(" 			,DECODE(SUM(SELLNUM		),0,0,NULL,0,SUM(SELLSUM		)*5.0000/SUM(SELLNUM	)) 	AS SINGLE_SELLSUM																																		");
		sql.append(" 			,DECODE(SUM(SELLNUM_L	),0,0,NULL,0,SUM(SELLSUM_L		)*5.0000/SUM(SELLNUM_L	)) 	AS SINGLE_SELLSUM_L																																			");
		sql.append(" 			,SUM(CASE WHEN K.I_PROVINCE = O.ORG_OWNPROVINCE THEN 0 ELSE SELLNUM END			) 	AS BS_SELLNUM_O																																				");
		sql.append(" 			,SUM(CASE WHEN K.I_PROVINCE = O.ORG_OWNPROVINCE THEN 0 ELSE SELLNUM_L END		)  	AS BS_SELLNUM_O_L																																			");
		sql.append(" 			,SUM(BS_TERMSTOCKNUM                            								)	AS BS_TERMSTOCKNUM																																");
		sql.append(" 			,SUM(BS_TERMSTOCKNUM_L                            								)	AS BS_TERMSTOCKNUM_L																																");
		sql.append(" 			,COUNT(DISTINCT I_ORG)*100.0000/(SELECT COUNT(1) FROM NICK_MA_T_CITY_ORG)			AS ORG_COVER_RATE																																			");
		sql.append(" 		FROM																																																		");
		sql.append(" 			(																																																	");
		sql.append(" 				SELECT 																																																");
		sql.append(" 					C_BRAND																																															");
		sql.append(" 					,I_PROVINCE																																														");
		sql.append(" 					,I_ORG																																															");
		sql.append(" 					,SUM(PRINT_NUM1 	- RBACK_NUM1	)   AS SELLNUM																																								");
		sql.append(" 					,SUM(PRINT_NUM1_P	- RBACK_NUM1_P	)   AS SELLNUM_L																																							");
		sql.append(" 					,SUM(PRINT_SUM 		- RBACK_SUM		)   AS SELLSUM																																							");
		sql.append(" 					,SUM(PRINT_SUM_P 	- RBACK_SUM_P	)   AS SELLSUM_L																																							");
		sql.append(" 					,SUM(TERM_STOCK1							)	AS BS_TERMSTOCKNUM																																			");
		sql.append(" 					,SUM(TERM_STOCK1_P							)	AS BS_TERMSTOCKNUM_L																																			");
		sql.append(" 				FROM  NICK_K_TJBS_Y_ALL 	NK,NICK_TJYY_CIGARETTE SC																																	");
		sql.append(" 				WHERE 1=1																																															");
		sql.append(" 			AND NK.C_BRAND = SC.cig_codebar and ((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))																");
		sql.append(" 					AND C_BRAND IN (SELECT C_BRAND FROM DW_T_NEW_PRODUCT)																																									");
		sql.append(" 					AND Y = YEAR(CURRENT DATE-1 DAYS)																																											");
		sql.append(" 				GROUP BY C_BRAND, I_PROVINCE, I_ORG																																												");
		sql.append(" 			) K																																																	");
		sql.append(" 		INNER JOIN VIEW_CIG_PROPERTY P																																															");
		sql.append(" 			ON K.C_BRAND = P.C_BRAND																																														");
		sql.append(" 		INNER JOIN NICK_STMA_ORGANIZATION O																																														");
		sql.append(" 			ON O.ORG_CODE = P.CIG_PRODUCER																																														");
		sql.append(" 		GROUP BY K.C_BRAND																																																");
		sql.append(" 		WITH ROLLUP																																																		");
		sql.append(" 	) B																																																			");
		sql.append(" 		ON K.C_BRAND = B.C_BRAND																																															");
		sql.append(" 	LEFT JOIN																																																		");
		sql.append(" 	(																																																			");
		sql.append(" 																																																				");
		sql.append(" 		SELECT																																																		");
		sql.append(" 			NVL(C_BRAND, '00')																AS C_BRAND																														");
		sql.append(" 			,COUNT(DISTINCT I_ORG)*100.0000/(SELECT COUNT(1) FROM NICK_MA_T_CITY_ORG)		AS ORG_COVER_RATE_L																																				");
		sql.append(" 			FROM  NICK_K_TJBS_Y_ALL	NK,NICK_TJYY_CIGARETTE SC																															");
		sql.append(" 			WHERE 1=1																																																");
		sql.append(" 		AND NK.C_BRAND = SC.cig_codebar and ((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))																	");
		sql.append(" 				AND C_BRAND IN (SELECT C_BRAND FROM DW_T_NEW_PRODUCT)																																										");
		sql.append(" 				AND Y = YEAR(CURRENT DATE-1 DAYS -1 YEARS)																																											");
		sql.append(" 			GROUP BY C_BRAND																																															");
		sql.append(" 			WITH ROLLUP																																																		");
		sql.append(" 	) CL																																																			");
		sql.append(" 		ON K.C_BRAND = CL.C_BRAND																																															");
		sql.append(" 	GROUP BY K.C_BRAND																																																	");
		sql.append(" 																																																				");
		sql.append(" 	) K																																																			");
		sql.append(" 	LEFT JOIN DW_T_NEW_PRODUCT N																																																");
		sql.append(" 		ON K.C_BRAND = N.C_BRAND																																															");
		sql.append(" 	LEFT JOIN NICK_CIG_PROPERTY P																																																");
		sql.append(" 		ON K.C_BRAND = P.C_BRAND																																															");
		sql.append(" 	LEFT JOIN NICK_STMA_CIGARETTE C																																																");
		sql.append(" 		ON K.C_BRAND = C.CIG_CODEBAR																																															");
		sql.append(" 	LEFT JOIN(SELECT DISTINCT PRODUCE_CODE, PRODUCE_SHORTNAME FROM DIM_PRODUCE_ORG) O																																									");
		sql.append(" 		ON C.CIG_PRODUCER = O.PRODUCE_CODE																																														");
		sql.append(" 	LEFT JOIN DW_T_CLASS_CODE N																																																");
		sql.append(" 		ON C.CIG_PRICETYPE = N.CODE																																															");
		sql.append(" 	LEFT JOIN (SELECT DISTINCT BAR, TRANSFERPRICE, WHOLESALEPRICE, SUGGESTEDRETAILPRICE FROM PRICELIST) P																																							");
		sql.append(" 		ON K.C_BRAND = P.BAR																																																");
		sql.append(" 		LEFT JOIN view_cig_property O																																															");
		sql.append(" 		ON K.C_BRAND=O.C_BRAND																																																");
		sql.append(" 	ORDER BY PRODUCENUM1 DESC																																																");
		sql.append(" 																																																		");
		sql.append("		) where CLASS_NAME not in ('无价类') WITH UR																                             ");
		log.info("卷烟品牌新品表格数据处理sql: " + sql.toString());
		return sql.toString();
	}
}
