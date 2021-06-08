package com.icss.tjfx.cigpop.cooperation.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.util.StringUtil;
import com.icss.tjfx.cigpop.cooperation.vo.CooperTableVO;
import com.icss.tjfx.total.in.vo.IndustryVO;

/**
 * 卷烟品牌业务查询类
 * 
 * @author lcx
 * @since July 25, 2017
 * @version 1.0
 * 
 * */
public class CooperTableQuery extends BaseBusiness {

	private static Log log = LogFactory.getLog(CooperTableQuery.class);

	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<CooperTableVO> cooperList = new ArrayList<CooperTableVO>();
		try {
			String type=request.getParameter("type");								//获取下钻的类型
			String code=request.getParameter("code");								//获取编码
			if (type.equals("gg")) {
				cooperList = getGGTableInfo(code,"dw");	
			}else if(type.equals("zy")){
				cooperList = getZYTableInfo(code,"dw");	
			}else {
				cooperList = getTableInfo("dw");
			}
		} catch (Exception e) {
			log.info("*************************");
			log.info("卷烟品牌合作sql执行异常:" + e);
			log.info("*************************");
			log.error("卷烟品牌合作sql执行异常", e);
		}
		
		String result = StringUtil.getJson(cooperList);
		log.info("卷烟品牌新品查询结果" + StringUtil.getJson(cooperList));

		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", result);

		return request;
	}
	
	/**
	 * 合作表格下钻中烟数据信息的查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<CooperTableVO> getZYTableInfo(String code,String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<CooperTableVO> list = new ArrayList<CooperTableVO>();
		try {
			sql = getTableZYSql(code);
			list = dbbean.executeQuery(sql, CooperTableVO.class.getName());
		} catch (Exception e) {
			log.error("getZYTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}

	private String getTableZYSql(String code) {
		StringBuffer sql =new StringBuffer();
		sql.append(" 	 SELECT																																														");
		sql.append(" 	K.FACT_POPEDOM as xz_parameter																																											");
		sql.append(" 	,NVL(R.FACT_POP_NAME,'合计') AS FACT_POP_NAME 																																								");
		sql.append(" 	,CASE WHEN PRINTNUM1=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(PRINTNUM1								*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM1																							");
		sql.append(" 	,CASE WHEN PRINTNUM1_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRINTNUM1_L								*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM1_L																							");
		sql.append(" 	,CASE WHEN PRINTNUM1=0 AND PRINTNUM1_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRINTNUM1-PRINTNUM1_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS PRO_JJL																											");
		sql.append(" 	,CASE WHEN PRINTNUM1=0 OR PRINTNUM1_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRINTNUM1-PRINTNUM1_L)*100.0000/NULLIF(PRINTNUM1_L,0), 2), 16, 2),'999990.99') END  AS PRO_TB																								");
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_NUM1							*1.0000/50000, 2), 16, 2),'999990.99') END AS SELLNUM1																							");
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1_L=0 THEN '-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_NUM1_L							*1.0000/50000, 2), 16, 2),'999990.99') END AS SELLNUM1_L																						");
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1=0 AND (K.OUT_FACT_NUM1-K.OUT_FACT_NUM1_L)=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND((K.OUT_FACT_NUM1-K.OUT_FACT_NUM1_L)			*1.0000/50000, 2), 16, 2),'999990.99') END AS SELLNUM1_JJL																			");
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1_L=0 OR (K.OUT_FACT_NUM1)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((K.OUT_FACT_NUM1-K.OUT_FACT_NUM1_L)*100.0000/NULLIF(K.OUT_FACT_NUM1_L,0), 2), 16, 2),'999990.99')END  AS SELLNUM1_TB																	");
		sql.append(" 	,CASE WHEN OUT_FACT_NUM1_LD=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(OUT_FACT_NUM1_LD							*1.0000/50000, 2), 16, 2),'999990.99') END AS LDXL																							");
		sql.append(" 	,CASE WHEN OUT_FACT_NUM1_LD_L=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(OUT_FACT_NUM1_LD_L						*1.0000/50000, 2), 16, 2),'999990.99') END AS LDXL_L																								");
		sql.append(" 	,CASE WHEN OUT_FACT_NUM1_LD=0 AND (OUT_FACT_NUM1_LD-OUT_FACT_NUM1_LD_L)=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND((OUT_FACT_NUM1_LD-OUT_FACT_NUM1_LD_L)	*1.0000/50000, 2), 16, 2),'999990.99') END AS LDXL_JJL																					");
		sql.append(" 	,CASE WHEN TERMSTOCK1=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(TERMSTOCK1								*1.0000/50000, 2), 16, 2),'999990.99') END AS GYKC																								");
		sql.append(" 	,CASE WHEN TERMSTOCK1_L=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(TERMSTOCK1_L								*1.0000/50000, 2), 16, 2),'999990.99') END AS GYKC_L																							");
		sql.append(" 	,CASE WHEN TERMSTOCK1=0 AND (TERMSTOCK1-TERMSTOCK1_L)=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND((TERMSTOCK1-TERMSTOCK1_L) 				*1.0000/50000, 2), 16, 2),'999990.99') END AS GYKC_JJL																						");
		sql.append(" 	,CASE WHEN K.OUT_FACT_SUM=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_SUM								*1.0000/10000, 2), 16, 2),'999990.99') END AS SELLSUM																							");
		sql.append(" 	,CASE WHEN K.OUT_FACT_SUM_L=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_SUM_L							*1.0000/10000, 2), 16, 2),'999990.99') END AS SELLSUM_L																							");
		sql.append(" 	,CASE WHEN K.OUT_FACT_SUM=0 AND (K.OUT_FACT_SUM-K.OUT_FACT_SUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((K.OUT_FACT_SUM-K.OUT_FACT_SUM_L)			*1.0000/10000, 2), 16, 2),'999990.99') END AS SELLSUM_JJL																			");
		sql.append(" 	,NVL((CASE WHEN K.OUT_FACT_SUM_L=0 OR (K.OUT_FACT_SUM)=0 THEN'-'ELSE TO_CHAR(DECIMAL(ROUND((K.OUT_FACT_SUM-K.OUT_FACT_SUM_L)*100.0000/NULLIF(K.OUT_FACT_SUM_L,0), 2), 16, 2),'999990.99')END),'-')	 AS SELLSUM_TB																		");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM=0 AND S.OUT_FACT_NUM1=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((S.OUT_FACT_SUM)*5.0000/NULLIF(S.OUT_FACT_NUM1,0), 2), 16, 2),'999990.99')END),'-')			   AS DX																					");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM_L=0 AND S.OUT_FACT_NUM1_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((S.OUT_FACT_SUM_L)*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0), 2), 16, 2),'999990.99')END),'-') 		   AS DX_L																					");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM=0 AND S.OUT_FACT_SUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((((S.OUT_FACT_SUM)*5.0000/NULLIF(S.OUT_FACT_NUM1,0))-((S.OUT_FACT_SUM_L)*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0))), 2), 16, 2),'999990.99')END),'-') AS DX_JJL																");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM=0 AND S.OUT_FACT_SUM_L=0 THEN'-' ELSE 																																						");
		sql.append(" 	TO_CHAR(DECIMAL(ROUND(DECODE(S.OUT_FACT_SUM_L*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0),0,0,((S.OUT_FACT_SUM*5.0000/NULLIF(S.OUT_FACT_NUM1,0))-(S.OUT_FACT_SUM_L*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0)))*100.0000/(S.OUT_FACT_SUM_L*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0))),2),16,2),'999990.99')END),'-') AS DX_TB										");
		sql.append(" 	FROM 																																														");
		sql.append(" 	 (SELECT																																													");
		sql.append(" 	NVL(FACT_POPEDOM,'00') 			AS FACT_POPEDOM																																								");
		sql.append(" 	,SUM(PRINTNUM1           ) AS PRINTNUM1																																										");
		sql.append(" 	,SUM(PRINTNUM1_L         ) AS PRINTNUM1_L																																									");
		sql.append(" 	,SUM(K.OUT_FACT_NUM1       ) AS OUT_FACT_NUM1																																									");
		sql.append(" 	,SUM(K.OUT_FACT_NUM1_L     ) AS OUT_FACT_NUM1_L																																									");
		sql.append(" 	,SUM(OUT_FACT_NUM1_LD    ) AS OUT_FACT_NUM1_LD																																									");
		sql.append(" 	,SUM(OUT_FACT_NUM1_LD_L  ) AS OUT_FACT_NUM1_LD_L																																								");
		sql.append(" 	,SUM(K.OUT_FACT_SUM        ) AS OUT_FACT_SUM																																									");
		sql.append(" 	,SUM(K.OUT_FACT_SUM_L      ) AS OUT_FACT_SUM_L																																									");
		sql.append(" 	,SUM(TERMSTOCK1          ) AS TERMSTOCK1																																									");
		sql.append(" 	,SUM(TERMSTOCK1_L        ) AS TERMSTOCK1_L																																									");
		sql.append(" 	FROM																																														");
		sql.append(" 	(																																														");
		sql.append(" 	SELECT 																																														");
		sql.append(" 		K.FACT_POPEDOM AS FACT_POPEDOM																																										");
		sql.append(" 		,SUM(PRINTNUM1) AS PRINTNUM1																																										");
		sql.append(" 		,SUM(PRINTNUM1_L) AS PRINTNUM1_L																																									");
		sql.append(" 		,0 AS OUT_FACT_NUM1																																											");
		sql.append(" 		,0 AS OUT_FACT_NUM1_L																																											");
		sql.append(" 		,SUM(OUT_FACT_NUM1_LD) AS OUT_FACT_NUM1_LD																																								");
		sql.append(" 		,SUM(OUT_FACT_NUM1_LD_L) AS OUT_FACT_NUM1_LD_L																																								");
		sql.append(" 		,0 AS OUT_FACT_SUM																																											");
		sql.append(" 		,0 AS OUT_FACT_SUM_L																																											");
		sql.append(" 		,0 AS TERMSTOCK1																																				");
		sql.append(" 		,0 AS TERMSTOCK1_L																																				");
		sql.append(" 	FROM NICK_MA_T_D_TJIN_LAW_FACT_ALL K																																											");
		sql.append(" 	INNER JOIN MA_T_FACT_POP_DI_IN O 																																										");
		sql.append(" 			ON K.FACT_POPEDOM = O.FACT_POP_CODE																																								");
		sql.append(" 	INNER JOIN cig_property_div C																																										");
		sql.append(" 			ON K.C_BRAND = C.C_BRAND																																									");
		sql.append(" 	WHERE  TYPE ='1'																																												");
		sql.append(" 		AND K.Y = YEAR(CURRENT DATE -1 DAYS)																																									");
		sql.append(" 		AND C.C_CIG='").append(code).append("'");
		sql.append(" 	GROUP BY K.FACT_POPEDOM																																												");
		sql.append(" 	HAVING(																																														");
		sql.append(" 	SUM(PRINTNUM1) <>0																																												");
		sql.append(" 	OR SUM(PRINTNUM1_L)<>0																																												");
		sql.append(" 	OR SUM(OUT_FACT_NUM1_LD)<>0																																											");
		sql.append(" 	OR SUM(OUT_FACT_NUM1_LD_L)<>0																																											");
		sql.append(" 	)																																														");
		sql.append(" 	UNION ALL																																													");
		sql.append(" 	SELECT 																																														");
		sql.append(" 		FACT_POPEDOM																				 AS	FACT_POPEDOM																						");
		sql.append(" 		,0 																					AS PRINTNUM1																							");
		sql.append(" 		,0 																					AS PRINTNUM1_L																							");
		sql.append(" 		,SUM (GYXL) 																		AS OUT_FACT_NUM1																								");
		sql.append(" 		,SUM (TQGYXL)  																		AS OUT_FACT_NUM1_L																								");
		sql.append(" 		,0 																					AS OUT_FACT_NUM1_LD																						");
		sql.append(" 		,0 																					AS OUT_FACT_NUM1_LD_L																						");
		sql.append(" 		,SUM (GYXE) 								 										AS OUT_FACT_SUM																									");
		sql.append(" 		,SUM (TQGYXE) 																		AS OUT_FACT_SUM_L																								");
		sql.append(" 		,0 AS TERMSTOCK1																																											");
		sql.append(" 		,0 AS TERMSTOCK1_L																																											");
		sql.append(" 		FROM   (SELECT 																																												");
		sql.append(" 						K.FACT_POPEDOM AS FACT_POPEDOM																																						");
		sql.append(" 						,SUM (OUT_FACT_NUM1-RECV_NUM1) AS GYXL,																																					");
		sql.append(" 						SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) AS TQGYXL,																																				");
		sql.append(" 						SUM (OUT_FACT_SUM-RECV_SUM) AS GYXE,																																					");
		sql.append(" 						SUM (OUT_FACT_SUM_L-RECV_SUM_L) AS TQGYXE																																				");
		sql.append(" 						FROM   NICK_K_TJIN_Y_Q_M_TYPE_SELL  K																																						");
		sql.append(" 						INNER JOIN cig_property_div C																																					");
		sql.append(" 							ON K.C_BRAND = C.C_BRAND																																					");
		sql.append(" 						INNER JOIN MA_T_FACT_POP_DI_IN O 																																					");
		sql.append(" 							ON K.FACT_POPEDOM = O.FACT_POP_CODE																																				");
		sql.append(" 							left join NICK_TJYY_CIGARETTE SC   on K.C_BRAND = SC.cig_codebar  																																				");
		sql.append(" 						WHERE 1=1 																																								");
		sql.append(" 						AND TRADE_TYPE = '2'																																							");
		sql.append(" 					AND ((d_month<='201712' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND SC.is_innx='1')) 																														");
		sql.append(" 						AND K.TYPE IN ('0','2','5') 																																						");
		sql.append(" 						AND C.C_CIG='").append(code).append("'");
		sql.append(" 						AND K.C_BRAND<>'6900000000001'																																						");
		sql.append(" 						AND K.I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')																											");
		sql.append(" 						AND K.M <= MONTH(CURRENT DATE-1 DAYS)																																					");
		sql.append(" 						AND K.Y = YEAR(CURRENT DATE-1 DAYS)																																					");
		sql.append(" 						GROUP BY K.FACT_POPEDOM																																							");
		sql.append(" 						HAVING SUM (OUT_FACT_NUM1-RECV_NUM1) <> 0																																				");
		sql.append(" 							OR SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) <> 0																																			");
		sql.append(" 							OR SUM (OUT_FACT_SUM-RECV_SUM) <> 0																																				");
		sql.append(" 							OR SUM (OUT_FACT_SUM_L-RECV_SUM_L) <> 0																																				");
		sql.append(" 						ORDER BY GYXL DESC																																							");
		sql.append(" 		)																																													");
		sql.append(" 		GROUP BY FACT_POPEDOM																																											");
		sql.append(" 			UNION ALL																																											");
		sql.append(" 			SELECT 																																												");
		sql.append(" 				K.FACT_POPEDOM AS FACT_POPEDOM																																								");
		sql.append(" 				,0 																					AS PRINTNUM1																					");
		sql.append(" 				,0 																					AS PRINTNUM1_L																					");
		sql.append(" 				,0 																					AS OUT_FACT_NUM1																				");
		sql.append(" 				,0  																				AS OUT_FACT_NUM1_L																					");
		sql.append(" 				,0 																					AS OUT_FACT_NUM1_LD																				");
		sql.append(" 				,0 																					AS OUT_FACT_NUM1_LD_L																				");
		sql.append(" 				,0 								 													AS OUT_FACT_SUM																					");
		sql.append(" 				,0 																					AS OUT_FACT_SUM_L																				");
		sql.append(" 				,SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1 ELSE 0 END)  	AS TERMSTOCK1																																	");
		sql.append(" 				,SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1_L ELSE 0 END)  AS TERMSTOCK1_L																																	");
		sql.append(" 																																															");
		sql.append(" 				FROM  NICK_K_TJIN_Y_Q_M_ALL K																																								");
		sql.append(" 					INNER JOIN cig_property_div C																																						");
		sql.append(" 							ON K.C_BRAND = C.C_BRAND																																					");
		sql.append(" 					INNER JOIN MA_T_FACT_POP_DI_IN O 																																						");
		sql.append(" 							ON K.FACT_POPEDOM = O.FACT_POP_CODE																																				");
		sql.append(" 							left join NICK_TJYY_CIGARETTE SC   on K.C_BRAND = SC.cig_codebar																																				");
		sql.append(" 					WHERE 1=1 																																									");
		sql.append(" 					AND K.M <= MONTH(CURRENT DATE-1 DAYS)																																						");
		sql.append(" 					AND K.Y = YEAR(CURRENT DATE-1 DAYS)																																						");
		sql.append(" 					AND TRADE_TYPE = '2'																																								");
		sql.append(" 					AND ((d_month<='201712' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND SC.is_innx='1')) 																																				");
		sql.append(" 					AND C.C_CIG='").append(code).append("'");
		sql.append(" 					AND K.C_BRAND<>'6900000000001'																																							");
		sql.append(" 					GROUP BY K.FACT_POPEDOM																																								");
		sql.append(" 					HAVING 																																										");
		sql.append(" 					SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1 ELSE 0 END)  <> 0																																		");
		sql.append(" 					OR SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1_L ELSE 0 END)  <> 0																																	");
		sql.append(" 																																															");
		sql.append(" 																																															");
		sql.append(" 		) K																																													");
		sql.append(" 		LEFT JOIN MA_T_FACT_POP_DI_IN R 																																									");
		sql.append(" 			ON K.FACT_POPEDOM = R.FACT_POP_CODE																																								");
		sql.append(" 		GROUP BY K.FACT_POPEDOM																																											");
		sql.append(" 		WITH ROLLUP																																												");
		sql.append(" 		)K																																													");
		sql.append(" 		LEFT JOIN(																																												");
		sql.append(" 		SELECT 			NVL(K.FACT_POPEDOM,'00')		AS FACT_POPEDOM																																				");
		sql.append(" 						,SUM (OUT_FACT_NUM1-RECV_NUM1) AS OUT_FACT_NUM1																																				");
		sql.append(" 						,SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) AS OUT_FACT_NUM1_L																																			");
		sql.append(" 						,SUM (OUT_FACT_SUM-RECV_SUM) AS OUT_FACT_SUM																																				");
		sql.append(" 						,SUM (OUT_FACT_SUM_L-RECV_SUM_L) AS OUT_FACT_SUM_L																																			");
		sql.append(" 						FROM   NICK_K_TJIN_Y_Q_M_TYPE_SELL  K																																					");
		sql.append(" 						INNER JOIN cig_property_div C																																					");
		sql.append(" 							ON K.C_BRAND = C.C_BRAND																																					");
		sql.append(" 						INNER JOIN MA_T_FACT_POP_DI_IN O 																																					");
		sql.append(" 							ON K.FACT_POPEDOM = O.FACT_POP_CODE																																				");
		sql.append(" 							left join NICK_TJYY_CIGARETTE SC  on K.C_BRAND = SC.cig_codebar																																				");
		sql.append(" 						WHERE 1=1 																																								");
		sql.append(" 						AND TRADE_TYPE = '2'																																							");
		sql.append(" 						AND ((d_month<='201712' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND SC.is_innx='1'))																		");
		sql.append(" 						AND K.TYPE IN ('0','1','2','5') 																																					");
		sql.append(" 						AND C.C_CIG='").append(code).append("'");
		sql.append(" 						AND K.C_BRAND<>'6900000000001'																																						");
		sql.append(" 						AND K.I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')																											");
		sql.append(" 						AND K.M <= MONTH(CURRENT DATE-1 DAYS)																																					");
		sql.append(" 						AND K.Y = YEAR(CURRENT DATE-1 DAYS)																																					");
		sql.append(" 						GROUP BY  K.FACT_POPEDOM																																						");
		sql.append(" 						WITH ROLLUP																																								");
		sql.append(" 						HAVING SUM (OUT_FACT_NUM1-RECV_NUM1) <> 0																																				");
		sql.append(" 							OR SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) <> 0																																			");
		sql.append(" 							OR SUM (OUT_FACT_SUM-RECV_SUM) <> 0																																				");
		sql.append(" 							OR SUM (OUT_FACT_SUM_L-RECV_SUM_L) <> 0																																				");
		sql.append(" 																																															");
		sql.append(" 				) S ON S.FACT_POPEDOM=K.FACT_POPEDOM																																							");
		sql.append(" 				LEFT JOIN MA_T_FACT_POP_DI_IN R 																																							");
		sql.append(" 					ON K.FACT_POPEDOM = R.FACT_POP_CODE																																						");
		sql.append(" 				 ORDER BY DECODE(FACT_POP_NAME,'合计',0,1) ASC, DECODE(PRODUCENUM1,'-',0,DECIMAL(PRODUCENUM1)) DESC																																					");
		sql.append(" 				 WITH UR																																										");

		log.info("合作生产表格下钻中烟sql="+sql.toString());
		return sql.toString();
	}


	/**
	 * 合作表格下钻规格数据信息的查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<CooperTableVO> getGGTableInfo(String code,String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<CooperTableVO> list = new ArrayList<CooperTableVO>();
		try {
			sql = getTableGGSql(code);
			list = dbbean.executeQuery(sql, CooperTableVO.class.getName());
		} catch (Exception e) {
			log.error("getGGTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	private String getTableGGSql(String code) {
		StringBuffer sql =new StringBuffer();
		sql.append(" 	SELECT																																												");
		sql.append(" 	K.C_BRAND	as xz_parameter	 																																									");
		sql.append(" 	,NVL(CIG_NAME,'合计') AS brand_name 																																									");
		sql.append(" 	,CASE WHEN PRINTNUM1=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(PRINTNUM1								*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM1																					");
		sql.append(" 	,CASE WHEN PRINTNUM1_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRINTNUM1_L								*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM1_L																					");
		sql.append(" 	,CASE WHEN PRINTNUM1=0 or PRINTNUM1_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRINTNUM1-PRINTNUM1_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS PRO_JJL																									");
		sql.append(" 	,CASE WHEN PRINTNUM1=0 or PRINTNUM1_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRINTNUM1-PRINTNUM1_L)*100.0000/NULLIF(PRINTNUM1_L,0), 2), 16, 2),'999990.99') END  AS PRO_TB																						");
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_NUM1							*1.0000/50000, 2), 16, 2),'999990.99') END AS SELLNUM1																					");
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1_L=0 THEN '-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_NUM1_L							*1.0000/50000, 2), 16, 2),'999990.99') END AS SELLNUM1_L																				");
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1=0 AND (K.OUT_FACT_NUM1_L)=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND((K.OUT_FACT_NUM1-K.OUT_FACT_NUM1_L)			*1.0000/50000, 2), 16, 2),'999990.99') END AS SELLNUM1_JJL																	");
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1_L=0 OR (K.OUT_FACT_NUM1)=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((K.OUT_FACT_NUM1-K.OUT_FACT_NUM1_L)*100.0000/NULLIF(K.OUT_FACT_NUM1_L,0), 2), 16, 2))END  AS SELLNUM1_TB															");
		sql.append(" 	,CASE WHEN OUT_FACT_NUM1_LD=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(OUT_FACT_NUM1_LD							*1.0000/50000, 2), 16, 2),'999990.99') END AS LDXL																					");
		sql.append(" 	,CASE WHEN OUT_FACT_NUM1_LD_L=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(OUT_FACT_NUM1_LD_L						*1.0000/50000, 2), 16, 2),'999990.99') END AS LDXL_L																						");
		sql.append(" 	,CASE WHEN OUT_FACT_NUM1_LD=0 or (OUT_FACT_NUM1_LD_L)=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND((OUT_FACT_NUM1_LD-OUT_FACT_NUM1_LD_L)	*1.0000/50000, 2), 16, 2),'999990.99') END AS LDXL_JJL																			");
		sql.append(" 	,CASE WHEN TERMSTOCK1=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(TERMSTOCK1								*1.0000/50000, 2), 16, 2),'999990.99') END AS GYKC																						");
		sql.append(" 	,CASE WHEN TERMSTOCK1_L=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(TERMSTOCK1_L								*1.0000/50000, 2), 16, 2),'999990.99') END AS GYKC_L																					");
		sql.append(" 	,CASE WHEN TERMSTOCK1=0 or (TERMSTOCK1_L)=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND((TERMSTOCK1-TERMSTOCK1_L) 				*1.0000/50000, 2), 16, 2),'999990.99') END AS GYKC_JJL																				");
		sql.append(" 	,CASE WHEN K.OUT_FACT_SUM=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_SUM								*1.0000/10000, 2), 16, 2),'999990.99') END AS SELLSUM																					");
		sql.append(" 	,CASE WHEN K.OUT_FACT_SUM_L=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_SUM_L							*1.0000/10000, 2), 16, 2),'999990.99') END AS SELLSUM_L																					");
		sql.append(" 	,CASE WHEN K.OUT_FACT_SUM=0 or (K.OUT_FACT_SUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((K.OUT_FACT_SUM-K.OUT_FACT_SUM_L)			*1.0000/10000, 2), 16, 2),'999990.99') END AS SELLSUM_JJL																	");
		sql.append(" 	,NVL((CASE WHEN K.OUT_FACT_SUM_L=0 OR (K.OUT_FACT_SUM)=0 THEN'-'ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((K.OUT_FACT_SUM-K.OUT_FACT_SUM_L)*100.0000/NULLIF(K.OUT_FACT_SUM_L,0), 2), 16, 2))END),'-')	 AS SELLSUM_TB															");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM=0 AND S.OUT_FACT_NUM1=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((S.OUT_FACT_SUM)*5.0000/NULLIF(S.OUT_FACT_NUM1,0), 2), 16, 2),'999990.99')END),'-')			   AS DX																			");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM_L=0 AND S.OUT_FACT_NUM1_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((S.OUT_FACT_SUM_L)*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0), 2), 16, 2),'999990.99')END),'-') 		   AS DX_L																			");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM=0 AND S.OUT_FACT_SUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((((S.OUT_FACT_SUM)*5.0000/NULLIF(S.OUT_FACT_NUM1,0))-((S.OUT_FACT_SUM_L)*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0))), 2), 16, 2),'999990.99')END),'-') AS DX_JJL														");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM=0 AND S.OUT_FACT_SUM_L=0 THEN'-' ELSE 																																				");
		sql.append(" 	TO_CHAR(DECIMAL(ROUND(DECODE(S.OUT_FACT_SUM_L*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0),0,0,((S.OUT_FACT_SUM*5.0000/NULLIF(S.OUT_FACT_NUM1,0))-(S.OUT_FACT_SUM_L*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0)))*100.0000/(S.OUT_FACT_SUM_L*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0))),2),16,2),'999990.99')END),'-') AS DX_TB								");
		sql.append(" 	FROM 																																												");
		sql.append(" 	 (SELECT																																											");
		sql.append(" 	NVL(R.C_BRAND,'00') 			AS C_BRAND																																						");
		sql.append(" 	,NVL(CIG_NAME,'合计') AS CIG_NAME																																								");
		sql.append(" 	,SUM(PRINTNUM1           ) AS PRINTNUM1																																								");
		sql.append(" 	,SUM(PRINTNUM1_L         ) AS PRINTNUM1_L																																							");
		sql.append(" 	,SUM(K.OUT_FACT_NUM1       ) AS OUT_FACT_NUM1																																							");
		sql.append(" 	,SUM(K.OUT_FACT_NUM1_L     ) AS OUT_FACT_NUM1_L																																							");
		sql.append(" 	,SUM(OUT_FACT_NUM1_LD    ) AS OUT_FACT_NUM1_LD																																							");
		sql.append(" 	,SUM(OUT_FACT_NUM1_LD_L  ) AS OUT_FACT_NUM1_LD_L																																						");
		sql.append(" 	,SUM(K.OUT_FACT_SUM        ) AS OUT_FACT_SUM																																							");
		sql.append(" 	,SUM(K.OUT_FACT_SUM_L      ) AS OUT_FACT_SUM_L																																							");
		sql.append(" 	,SUM(TERMSTOCK1          ) AS TERMSTOCK1																																							");
		sql.append(" 	,SUM(TERMSTOCK1_L        ) AS TERMSTOCK1_L																																							");
		sql.append(" 	FROM																																												");
		sql.append(" 	(																																												");
		sql.append(" 	SELECT 																																												");
		sql.append(" 		K.C_BRAND AS C_BRAND																																									");
		sql.append(" 		,SUM(PRINTNUM1) AS PRINTNUM1																																								");
		sql.append(" 		,SUM(PRINTNUM1_L) AS PRINTNUM1_L																																							");
		sql.append(" 		,0 AS OUT_FACT_NUM1																																									");
		sql.append(" 		,0 AS OUT_FACT_NUM1_L																																									");
		sql.append(" 		,SUM(OUT_FACT_NUM1_LD) AS OUT_FACT_NUM1_LD																																						");
		sql.append(" 		,SUM(OUT_FACT_NUM1_LD_L) AS OUT_FACT_NUM1_LD_L																																						");
		sql.append(" 		,0 AS OUT_FACT_SUM																																									");
		sql.append(" 		,0 AS OUT_FACT_SUM_L																																									");
		sql.append(" 		,0 AS TERMSTOCK1																																									");
		sql.append(" 		,0 AS TERMSTOCK1_L																																									");
		sql.append(" 	FROM NICK_MA_T_D_TJIN_LAW_FACT_ALL K																																									");
		sql.append(" 	INNER  JOIN																																											");
		sql.append(" 			cig_property_div C																																							");
		sql.append(" 				ON K.C_BRAND = C.C_BRAND																																						");
		sql.append(" 	WHERE  TYPE ='1'																																										");
		sql.append(" 		AND K.Y = YEAR(CURRENT DATE -1 DAYS)																																							");
		sql.append(" 		AND C.C_CIG='").append(code).append("'");
		sql.append(" 	GROUP BY K.C_BRAND																																										");
		sql.append(" 	HAVING(																																												");
		sql.append(" 	SUM(PRINTNUM1) <>0																																										");
		sql.append(" 	OR SUM(PRINTNUM1_L)<>0																																										");
		sql.append(" 	OR SUM(OUT_FACT_NUM1_LD)<>0																																									");
		sql.append(" 	OR SUM(OUT_FACT_NUM1_LD_L)<>0																																									");
		sql.append(" 	)																																												");
		sql.append(" 	UNION ALL																																											");
		sql.append(" 	SELECT 																																												");
		sql.append(" 		C_BRAND																				 AS	C_BRAND																						");
		sql.append(" 		,0 																					AS PRINTNUM1																					");
		sql.append(" 		,0 																					AS PRINTNUM1_L																					");
		sql.append(" 		,SUM (GYXL) 																		AS OUT_FACT_NUM1																						");
		sql.append(" 		,SUM (TQGYXL)  																		AS OUT_FACT_NUM1_L																						");
		sql.append(" 		,0 																					AS OUT_FACT_NUM1_LD																				");
		sql.append(" 		,0 																					AS OUT_FACT_NUM1_LD_L																				");
		sql.append(" 		,SUM (GYXE) 								 										AS OUT_FACT_SUM																							");
		sql.append(" 		,SUM (TQGYXE) 																		AS OUT_FACT_SUM_L																						");
		sql.append(" 		,0 AS TERMSTOCK1																																									");
		sql.append(" 		,0 AS TERMSTOCK1_L																																									");
		sql.append(" 		FROM   (SELECT 																																										");
		sql.append(" 						K.C_BRAND AS C_BRAND,																																					");
		sql.append(" 						SUM (OUT_FACT_NUM1-RECV_NUM1) AS GYXL,																																			");
		sql.append(" 						SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) AS TQGYXL,																																		");
		sql.append(" 						SUM (OUT_FACT_SUM-RECV_SUM) AS GYXE,																																			");
		sql.append(" 						SUM (OUT_FACT_SUM_L-RECV_SUM_L) AS TQGYXE																																		");
		sql.append(" 						FROM   NICK_K_TJIN_Y_Q_M_TYPE_SELL  K																																			");
		sql.append(" 						INNER  JOIN																																						");
		sql.append(" 						cig_property_div C																																				");
		sql.append(" 							ON K.C_BRAND = C.C_BRAND																																			");
		sql.append(" 					left join NICK_TJYY_CIGARETTE SC   on K.C_BRAND = SC.cig_codebar 																	");
		sql.append(" 						WHERE 1=1 																																						");
		sql.append(" 						AND TRADE_TYPE = '2'																																					");
		sql.append(" 				AND ((d_month<='201712' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND SC.is_innx='1')) 																			");
		sql.append(" 						AND K.TYPE IN ('0','2','5') 																																				");
		sql.append(" 						AND C.C_CIG='").append(code).append("'");
		sql.append(" 						AND K.C_BRAND<>'6900000000001'																																				");
		sql.append(" 						AND K.I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')																									");
		sql.append(" 						AND K.M <= MONTH(CURRENT DATE-1 DAYS)																																			");
		sql.append(" 						AND K.Y = YEAR(CURRENT DATE-1 DAYS)																																			");
		sql.append(" 						GROUP BY K.C_BRAND																																					");
		sql.append(" 						HAVING SUM (OUT_FACT_NUM1-RECV_NUM1) <> 0																																		");
		sql.append(" 							OR SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) <> 0																																	");
		sql.append(" 							OR SUM (OUT_FACT_SUM-RECV_SUM) <> 0																																		");
		sql.append(" 							OR SUM (OUT_FACT_SUM_L-RECV_SUM_L) <> 0																																		");
		sql.append(" 						ORDER BY GYXL DESC																																					");
		sql.append(" 					)																																								");
		sql.append(" 					GROUP BY C_BRAND																																						");
		sql.append(" 																																													");
		sql.append(" 		UNION ALL																																										");
		sql.append(" 			SELECT 																																										");
		sql.append(" 				K.C_BRAND																					 AS	C_BRAND																		");
		sql.append(" 				,0 																					AS PRINTNUM1																			");
		sql.append(" 				,0 																					AS PRINTNUM1_L																			");
		sql.append(" 				,0 																					AS OUT_FACT_NUM1																		");
		sql.append(" 				,0  																				AS OUT_FACT_NUM1_L																			");
		sql.append(" 				,0 																					AS OUT_FACT_NUM1_LD																		");
		sql.append(" 				,0 																					AS OUT_FACT_NUM1_LD_L																		");
		sql.append(" 				,0 								 													AS OUT_FACT_SUM																			");
		sql.append(" 				,0 																					AS OUT_FACT_SUM_L																		");
		sql.append(" 				,SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1 ELSE 0 END)  	AS TERMSTOCK1																															");
		sql.append(" 				,SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1_L ELSE 0 END)  AS TERMSTOCK1_L																															");
		sql.append(" 																																													");
		sql.append(" 				FROM  NICK_K_TJIN_Y_Q_M_ALL K																																						");
		sql.append(" 					INNER  JOIN																																							");
		sql.append(" 						cig_property_div C																																				");
		sql.append(" 					ON K.C_BRAND = C.C_BRAND																																					");
		sql.append(" 					left join NICK_TJYY_CIGARETTE SC   on K.C_BRAND = SC.cig_codebar																				");
		sql.append(" 					WHERE 1=1																																							");
		sql.append(" 					AND K.M <= MONTH(CURRENT DATE-1 DAYS)																																				");
		sql.append(" 					AND K.Y = YEAR(CURRENT DATE-1 DAYS)																																				");
		sql.append(" 					AND	TRADE_TYPE = '2'																																					");
		sql.append(" 			AND ((d_month<='201712' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND SC.is_innx='1'))											");
		sql.append(" 					AND C.C_CIG='").append(code).append("'");
		sql.append(" 					AND K.C_BRAND<>'6900000000001'																																					");
		sql.append(" 					GROUP BY K.C_BRAND																																						");
		sql.append(" 					HAVING 																																								");
		sql.append(" 					SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1 ELSE 0 END)  <> 0																																");
		sql.append(" 					OR SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1_L ELSE 0 END)  <> 0																															");
		sql.append(" 																																													");
		sql.append(" 																																													");
		sql.append(" 		) K																																											");
		sql.append(" 		LEFT JOIN cig_property_div R 																																							");
		sql.append(" 			ON K.C_BRAND = R.C_BRAND																																							");
		sql.append(" 		GROUP BY R.C_BRAND,CIG_NAME																																								");
		sql.append(" 		WITH ROLLUP																																										");
		sql.append(" 		HAVING (R.C_BRAND IS NULL AND CIG_NAME IS NULL) 																																					");
		sql.append(" 		OR (R.C_BRAND IS NOT NULL AND CIG_NAME IS NOT NULL) 																																					");
		sql.append(" 		)K																																											");
		sql.append(" 		LEFT JOIN(																																										");
		sql.append(" 				SELECT 			NVL(C.C_BRAND,'00')		AS C_BRAND																																	");
		sql.append(" 						,SUM (OUT_FACT_NUM1-RECV_NUM1) AS OUT_FACT_NUM1																																		");
		sql.append(" 						,SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) AS OUT_FACT_NUM1_L																																	");
		sql.append(" 						,SUM (OUT_FACT_SUM-RECV_SUM) AS OUT_FACT_SUM																																		");
		sql.append(" 						,SUM (OUT_FACT_SUM_L-RECV_SUM_L) AS OUT_FACT_SUM_L																																	");
		sql.append(" 						FROM   NICK_K_TJIN_Y_Q_M_TYPE_SELL  K																																			");
		sql.append(" 						INNER  JOIN																																						");
		sql.append(" 						cig_property_div C																																				");
		sql.append(" 							ON K.C_BRAND = C.C_BRAND																																			");
		sql.append(" 						left join NICK_TJYY_CIGARETTE SC  on K.C_BRAND = SC.cig_codebar																		");
		sql.append(" 						WHERE 1=1 																																						");
		sql.append(" 						AND TRADE_TYPE = '2'																																					");
		sql.append(" 				AND ((d_month<='201712' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND SC.is_innx='1'))									");
		sql.append(" 						AND K.TYPE IN ('0','2','5') 																																			");
		sql.append(" 						AND C.C_CIG='").append(code).append("'");
		sql.append(" 						AND K.C_BRAND<>'6900000000001'																																				");
		sql.append(" 						AND K.I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')																									");
		sql.append(" 						AND K.M <= MONTH(CURRENT DATE-1 DAYS)																																			");
		sql.append(" 						AND K.Y = YEAR(CURRENT DATE-1 DAYS)																																			");
		sql.append(" 						GROUP BY  C.C_BRAND																																					");
		sql.append(" 						WITH ROLLUP																																						");
		sql.append(" 						HAVING SUM (OUT_FACT_NUM1-RECV_NUM1) <> 0																																		");
		sql.append(" 							OR SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) <> 0																																	");
		sql.append(" 							OR SUM (OUT_FACT_SUM-RECV_SUM) <> 0																																		");
		sql.append(" 							OR SUM (OUT_FACT_SUM_L-RECV_SUM_L) <> 0																																		");
		sql.append(" 																																													");
		sql.append(" 			) S ON S.C_BRAND=K.C_BRAND																																							");
		sql.append(" 			 ORDER BY DECODE(CIG_NAME,'合计',0,1) ASC																																						");
		sql.append(" 			 WITH UR																																									");

		log.info("合作生产表格下钻规格sql="+sql.toString());
		return sql.toString();
	}


	/**
	 * 合作表格数据信息的查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<CooperTableVO> getTableInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<CooperTableVO> list = new ArrayList<CooperTableVO>();
		try {
			sql = getTableSql();
			list = dbbean.executeQuery(sql, CooperTableVO.class.getName());
		} catch (Exception e) {
			log.error("getTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	private String getTableSql() {
		StringBuffer sql =new StringBuffer();
		sql.append(" 	SELECT																																												");
		sql.append(" 	K.C_CIG as xz_parameter																																										");
		sql.append(" 	,NVL(CIG_MARKNAME,'合  计') AS cig_name 																																							");
		sql.append(" 	,CASE WHEN PRINTNUM1=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(PRINTNUM1								*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM1																					");
		sql.append(" 	,CASE WHEN PRINTNUM1_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRINTNUM1_L								*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM1_L																					");
		sql.append(" 	,CASE WHEN PRINTNUM1=0 and PRINTNUM1_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRINTNUM1-PRINTNUM1_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS PRO_JJL																									");
		sql.append(" 	,NVL((CASE WHEN PRINTNUM1=0 or PRINTNUM1_L=0 THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((PRINTNUM1-PRINTNUM1_L)*100.0000/NULLIF(PRINTNUM1_L,0), 2), 16, 2)) END),'-')  AS PRO_TB																						");
		
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_NUM1							*1.0000/50000, 2), 16, 2),'999990.99') END AS SELLNUM1																					");
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1_L=0 THEN '-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_NUM1_L							*1.0000/50000, 2), 16, 2),'999990.99') END AS SELLNUM1_L																				");
		sql.append(" 	,CASE WHEN K.OUT_FACT_NUM1=0 and (K.OUT_FACT_NUM1-K.OUT_FACT_NUM1_L)=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND((K.OUT_FACT_NUM1-K.OUT_FACT_NUM1_L)			*1.0000/50000, 2), 16, 2),'999990.99') END AS SELLNUM1_JJL																	");
		sql.append(" 	,NVL((CASE WHEN K.OUT_FACT_NUM1_L=0 or (K.OUT_FACT_NUM1)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((K.OUT_FACT_NUM1-K.OUT_FACT_NUM1_L)*100.0000/NULLIF(K.OUT_FACT_NUM1_L,0), 2), 16, 2),'999990.99')END),'-')  AS SELLNUM1_TB															");
		
		sql.append(" 	,CASE WHEN OUT_FACT_NUM1_LD=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(OUT_FACT_NUM1_LD							*1.0000/50000, 2), 16, 2),'999990.99') END AS LDXL																					");
		sql.append(" 	,CASE WHEN OUT_FACT_NUM1_LD_L=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(OUT_FACT_NUM1_LD_L						*1.0000/50000, 2), 16, 2),'999990.99') END AS LDXL_L																						");
		sql.append(" 	,CASE WHEN OUT_FACT_NUM1_LD=0 and (OUT_FACT_NUM1_LD_L)=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND((OUT_FACT_NUM1_LD-OUT_FACT_NUM1_LD_L)	*1.0000/50000, 2), 16, 2),'999990.99') END AS LDXL_JJL																			");
		
		sql.append(" 	,CASE WHEN TERMSTOCK1=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(TERMSTOCK1								*1.0000/50000, 2), 16, 2),'999990.99') END AS GYKC																						");
		sql.append(" 	,CASE WHEN TERMSTOCK1_L=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(TERMSTOCK1_L								*1.0000/50000, 2), 16, 2),'999990.99') END AS GYKC_L																					");
		sql.append(" 	,CASE WHEN TERMSTOCK1=0 and (TERMSTOCK1-TERMSTOCK1_L)=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND((TERMSTOCK1-TERMSTOCK1_L) 				*1.0000/50000, 2), 16, 2),'999990.99') END AS GYKC_JJL																				");
		sql.append(" 	,CASE WHEN K.OUT_FACT_SUM=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_SUM								*1.0000/10000, 2), 16, 2),'999990.99') END AS SELLSUM																					");
		sql.append(" 	,CASE WHEN K.OUT_FACT_SUM_L=0 THEN'-' ELSE TO_CHAR( DECIMAL(ROUND(K.OUT_FACT_SUM_L							*1.0000/10000, 2), 16, 2),'999990.99') END AS SELLSUM_L																					");
		sql.append(" 	,CASE WHEN K.OUT_FACT_SUM=0 and (K.OUT_FACT_SUM-K.OUT_FACT_SUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((K.OUT_FACT_SUM-K.OUT_FACT_SUM_L)			*1.0000/10000, 2), 16, 2),'999990.99') END AS SELLSUM_JJL																	");
		sql.append(" 	,NVL((CASE WHEN K.OUT_FACT_SUM_L=0 or (K.OUT_FACT_SUM-K.OUT_FACT_SUM_L)=0 THEN'-'ELSE TO_CHAR(DECIMAL(ROUND((K.OUT_FACT_SUM-K.OUT_FACT_SUM_L)*100.0000/NULLIF(K.OUT_FACT_SUM_L,0), 2), 16, 2),'999990.99')END),'-')	 AS SELLSUM_TB																");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM=0 AND S.OUT_FACT_NUM1=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((S.OUT_FACT_SUM)*5.0000/NULLIF(S.OUT_FACT_NUM1,0), 2), 16, 2),'999990.99')END),'-')			   AS DX																			");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM_L=0 AND S.OUT_FACT_NUM1_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((S.OUT_FACT_SUM_L)*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0), 2), 16, 2),'999990.99')END),'-') 		   AS DX_L																			");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM=0 AND S.OUT_FACT_SUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((((S.OUT_FACT_SUM)*5.0000/NULLIF(S.OUT_FACT_NUM1,0))-((S.OUT_FACT_SUM_L)*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0))), 2), 16, 2),'999990.99')END),'-') AS DX_JJL														");
		sql.append(" 	,NVL((CASE WHEN S.OUT_FACT_SUM=0 AND S.OUT_FACT_SUM_L=0 THEN'-' ELSE 																																				");
		sql.append(" 	TO_CHAR(DECIMAL(ROUND(DECODE(S.OUT_FACT_SUM_L*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0),0,0,((S.OUT_FACT_SUM*5.0000/NULLIF(S.OUT_FACT_NUM1,0))-(S.OUT_FACT_SUM_L*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0)))*100.0000/(S.OUT_FACT_SUM_L*5.0000/NULLIF(S.OUT_FACT_NUM1_L,0))),2),16,2),'999990.99')END),'-') AS DX_TB								");
		sql.append(" 	FROM 																																												");
		sql.append(" 	 (SELECT																																											");
		sql.append(" 	NVL(R.C_CIG,'00') 			AS C_CIG																																						");
		sql.append(" 	,NVL(CIG_MARKNAME,'合  计') AS CIG_MARKNAME																																							");
		sql.append(" 	,SUM(PRINTNUM1           ) AS PRINTNUM1																																								");
		sql.append(" 	,SUM(PRINTNUM1_L         ) AS PRINTNUM1_L																																							");
		sql.append(" 	,SUM(K.OUT_FACT_NUM1       ) AS OUT_FACT_NUM1																																							");
		sql.append(" 	,SUM(K.OUT_FACT_NUM1_L     ) AS OUT_FACT_NUM1_L																																							");
		sql.append(" 	,SUM(OUT_FACT_NUM1_LD    ) AS OUT_FACT_NUM1_LD																																							");
		sql.append(" 	,SUM(OUT_FACT_NUM1_LD_L  ) AS OUT_FACT_NUM1_LD_L																																						");
		sql.append(" 	,SUM(K.OUT_FACT_SUM        ) AS OUT_FACT_SUM																																							");
		sql.append(" 	,SUM(K.OUT_FACT_SUM_L      ) AS OUT_FACT_SUM_L																																							");
		sql.append(" 	,SUM(TERMSTOCK1          ) AS TERMSTOCK1																																							");
		sql.append(" 	,SUM(TERMSTOCK1_L        ) AS TERMSTOCK1_L																																							");
		sql.append(" 	FROM																																												");
		sql.append(" 	(																																												");
		sql.append(" 	SELECT 																																												");
		sql.append(" 		C_BRAND AS C_BRAND																																									");
		sql.append(" 		,C_CIG AS C_CIG																																										");
		sql.append(" 		,SUM(PRINTNUM1) AS PRINTNUM1																																								");
		sql.append(" 		,SUM(PRINTNUM1_L) AS PRINTNUM1_L																																							");
		sql.append(" 		,0 AS OUT_FACT_NUM1																																									");
		sql.append(" 		,0 AS OUT_FACT_NUM1_L																																									");
		sql.append(" 		,SUM(OUT_FACT_NUM1_LD) AS OUT_FACT_NUM1_LD																																						");
		sql.append(" 		,SUM(OUT_FACT_NUM1_LD_L) AS OUT_FACT_NUM1_LD_L																																						");
		sql.append(" 		,0 AS OUT_FACT_SUM																																									");
		sql.append(" 		,0 AS OUT_FACT_SUM_L																																									");
		sql.append(" 		,0 AS TERMSTOCK1																																									");
		sql.append(" 		,0 AS TERMSTOCK1_L																																									");
		sql.append(" 	FROM NICK_MA_T_D_TJIN_LAW_FACT_ALL																																									");
		sql.append(" 	WHERE  TYPE ='1'																																										");
		sql.append(" 		AND Y = YEAR(CURRENT DATE -1 DAYS)																																							");
		sql.append(" 	GROUP BY C_BRAND, C_CIG																																										");
		sql.append(" 	HAVING(																																												");
		sql.append(" 	SUM(PRINTNUM1) <>0																																										");
		sql.append(" 	OR SUM(PRINTNUM1_L)<>0																																										");
		sql.append(" 	OR SUM(OUT_FACT_NUM1_LD)<>0																																									");
		sql.append(" 	OR SUM(OUT_FACT_NUM1_LD_L)<>0																																									");
		sql.append(" 	)																																												");
		sql.append(" 	UNION ALL																																											");
		sql.append(" 	SELECT 																																												");
		sql.append(" 		C_BRAND																				 AS	C_BRAND																						");
		sql.append(" 		,C_CIG 																				AS C_CIG																						");
		sql.append(" 		,0 																					AS PRINTNUM1																					");
		sql.append(" 		,0 																					AS PRINTNUM1_L																					");
		sql.append(" 		,SUM (GYXL) 																		AS OUT_FACT_NUM1																						");
		sql.append(" 		,SUM (TQGYXL)  																		AS OUT_FACT_NUM1_L																						");
		sql.append(" 		,0 																					AS OUT_FACT_NUM1_LD																				");
		sql.append(" 		,0 																					AS OUT_FACT_NUM1_LD_L																				");
		sql.append(" 		,SUM (GYXE) 								 										AS OUT_FACT_SUM																							");
		sql.append(" 		,SUM (TQGYXE) 																		AS OUT_FACT_SUM_L																						");
		sql.append(" 		,0 AS TERMSTOCK1																																									");
		sql.append(" 		,0 AS TERMSTOCK1_L																																									");
		sql.append(" 		FROM   (SELECT 	C.C_CIG		AS C_CIG																																						");
		sql.append(" 						,K.C_BRAND AS C_BRAND,																																					");
		sql.append(" 						SUM (OUT_FACT_NUM1-RECV_NUM1) AS GYXL,																																			");
		sql.append(" 						SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) AS TQGYXL,																																		");
		sql.append(" 						SUM (OUT_FACT_SUM-RECV_SUM) AS GYXE,																																			");
		sql.append(" 						SUM (OUT_FACT_SUM_L-RECV_SUM_L) AS TQGYXE																																		");
		sql.append(" 						FROM   NICK_K_TJIN_Y_Q_M_TYPE_SELL  K																																			");
		sql.append(" 						INNER  JOIN																																						");
		sql.append(" 						cig_property_div C																																				");
		sql.append(" 							ON K.C_BRAND = C.C_BRAND																																			");
		sql.append(" 						left join NICK_TJYY_CIGARETTE SC   on K.C_BRAND = SC.cig_codebar															");
		sql.append(" 						WHERE 1=1 																																						");
		sql.append(" 						AND TRADE_TYPE = '2'																																					");
		sql.append(" 					AND ((d_month<='201712' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND SC.is_innx='1'))															");
		sql.append(" 						AND K.TYPE IN ('0','2','5') 																																				");
		sql.append(" 						AND K.C_BRAND<>'6900000000001'																																				");
		sql.append(" 						AND K.I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')																									");
		sql.append(" 						AND K.M <= MONTH(CURRENT DATE-1 DAYS)																																			");
		sql.append(" 						AND K.Y = YEAR(CURRENT DATE-1 DAYS)																																			");
		sql.append(" 						GROUP BY K.C_BRAND, C.C_CIG																																				");
		sql.append(" 						HAVING SUM (OUT_FACT_NUM1-RECV_NUM1) <> 0																																		");
		sql.append(" 							OR SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) <> 0																																	");
		sql.append(" 							OR SUM (OUT_FACT_SUM-RECV_SUM) <> 0																																		");
		sql.append(" 							OR SUM (OUT_FACT_SUM_L-RECV_SUM_L) <> 0																																		");
		sql.append(" 						ORDER BY GYXL DESC																																					");
		sql.append(" 		)																																											");
		sql.append(" 		GROUP BY C_CIG,C_BRAND																																									");
		sql.append(" 	UNION ALL																																											");
		sql.append(" 	SELECT 																																												");
		sql.append(" 		K.C_BRAND																					 AS	C_BRAND																				");
		sql.append(" 		,C.C_CIG 																				AS C_CIG																					");
		sql.append(" 		,0 																					AS PRINTNUM1																					");
		sql.append(" 		,0 																					AS PRINTNUM1_L																					");
		sql.append(" 		,0 																					AS OUT_FACT_NUM1																				");
		sql.append(" 		,0  																				AS OUT_FACT_NUM1_L																					");
		sql.append(" 		,0 																					AS OUT_FACT_NUM1_LD																				");
		sql.append(" 		,0 																					AS OUT_FACT_NUM1_LD_L																				");
		sql.append(" 		,0 								 													AS OUT_FACT_SUM																					");
		sql.append(" 		,0 																					AS OUT_FACT_SUM_L																				");
		sql.append(" 		,SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1 ELSE 0 END)  	AS TERMSTOCK1																																	");
		sql.append(" 		,SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1_L ELSE 0 END)  AS TERMSTOCK1_L																																	");
		sql.append(" 	  																																												");
		sql.append(" 		FROM  NICK_K_TJIN_Y_Q_M_ALL K																																								");
		sql.append("              INNER  JOIN																																										");
		sql.append("                 cig_property_div C																																								");
		sql.append("              ON K.C_BRAND = C.C_BRAND																																								");
		sql.append("           left join NICK_TJYY_CIGARETTE SC  on K.C_BRAND = SC.cig_codebar																		");
		sql.append(" 			WHERE	1=1																																									");
		sql.append(" 			AND K.M<= MONTH(CURRENT DATE-1 DAYS)																																						");
		sql.append(" 			AND K.Y = YEAR(CURRENT DATE-1 DAYS)																																						");
		sql.append(" 			AND TRADE_TYPE = '2'																																								");
		sql.append(" 		AND ((d_month<='201712' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND SC.is_innx='1'))									");
		sql.append(" 			AND K.C_BRAND<>'6900000000001'																																							");
		sql.append(" 			GROUP BY K.C_BRAND, C.C_CIG																																							");
		sql.append(" 			HAVING 																																										");
		sql.append(" 			SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1 ELSE 0 END)  <> 0																																		");
		sql.append(" 			OR SUM (CASE WHEN K.D_MONTH = substr(dc_func_dtostr(current date -1 day),1,6) THEN TERMSTOCK1_L ELSE 0 END)  <> 0																																	");
		sql.append(" 			) K																																										");
		sql.append(" 			LEFT JOIN cig_property_div R 																																						");
		sql.append(" 				ON K.C_BRAND = R.C_BRAND																																						");
		sql.append(" 			GROUP BY R.C_CIG,CIG_MARKNAME																																							");
		sql.append(" 			WITH ROLLUP																																									");
		sql.append(" 			HAVING (R.C_CIG IS NULL AND CIG_MARKNAME IS NULL) 																																				");
		sql.append(" 			OR (R.C_CIG IS NOT NULL AND CIG_MARKNAME IS NOT NULL) 																																				");
		sql.append(" 			)K																																										");
		sql.append(" 			LEFT JOIN(																																									");
		sql.append(" 					SELECT 			NVL(C.C_CIG,'00')		AS C_CIG																																");
		sql.append(" 						,SUM (OUT_FACT_NUM1-RECV_NUM1) AS OUT_FACT_NUM1																																		");
		sql.append(" 						,SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) AS OUT_FACT_NUM1_L																																	");
		sql.append(" 						,SUM (OUT_FACT_SUM-RECV_SUM) AS OUT_FACT_SUM																																		");
		sql.append(" 						,SUM (OUT_FACT_SUM_L-RECV_SUM_L) AS OUT_FACT_SUM_L																																	");
		sql.append(" 						FROM   NICK_K_TJIN_Y_Q_M_TYPE_SELL  K																																			");
		sql.append(" 						INNER  JOIN																																						");
		sql.append(" 						cig_property_div C																																				");
		sql.append(" 							ON K.C_BRAND = C.C_BRAND																																			");
		sql.append(" 				left join NICK_TJYY_CIGARETTE SC  on K.C_BRAND = SC.cig_codebar															");
		sql.append(" 						WHERE 1=1 																																						");
		sql.append(" 						AND TRADE_TYPE = '2'																																					");
		sql.append(" 			AND ((d_month<='201712' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND SC.is_innx='1')) 												");
		sql.append(" 						AND K.TYPE IN ('0','2','5') 																																			");
		sql.append(" 						AND K.C_BRAND<>'6900000000001'																																				");
		sql.append(" 						AND K.I_ORG NOT IN ('12510102','12510101','12500101','12510601','12510701','12513401','12500104','12500103','12500102')																									");
		sql.append(" 						AND K.M <= MONTH(CURRENT DATE-1 DAYS)																																			");
		sql.append(" 						AND K.Y = YEAR(CURRENT DATE-1 DAYS)																																			");
		sql.append(" 						GROUP BY  C.C_CIG																																					");
		sql.append(" 						WITH ROLLUP																																						");
		sql.append(" 						HAVING SUM (OUT_FACT_NUM1-RECV_NUM1) <> 0																																		");
		sql.append(" 							OR SUM (OUT_FACT_NUM1_L-RECV_NUM1_L) <> 0																																	");
		sql.append(" 							OR SUM (OUT_FACT_SUM-RECV_SUM) <> 0																																		");
		sql.append(" 							OR SUM (OUT_FACT_SUM_L-RECV_SUM_L) <> 0																																		");
		sql.append(" 																																													");
		sql.append(" 				) S ON S.C_CIG=K.C_CIG																																							");
		sql.append(" 				 ORDER BY PRINTNUM1 DESC,cig_name DESC																																					");
		sql.append(" 				 WITH UR																																								");

		log.info("合作生产表格sql="+sql.toString());
		return sql.toString();
	}
}
