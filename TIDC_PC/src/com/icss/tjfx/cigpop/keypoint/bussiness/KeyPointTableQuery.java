package com.icss.tjfx.cigpop.keypoint.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.cigpop.keypoint.vo.KeyPointTableVO;

/**
 * 卷烟品牌业务模型
 * 
 * @author lcx
 * @since July 25, 2017
 * @version 1.0
 * 
 * */
public class KeyPointTableQuery extends BaseBusiness {

	private static Log log = LogFactory.getLog(KeyPointTableQuery.class);

	public HttpServletRequest handle(HttpServletRequest request)throws Exception {
		Gson gson = new Gson();
		List<KeyPointTableVO> keyPointList = new ArrayList<KeyPointTableVO>();
		String type=request.getParameter("type");								//获取下钻的类型
		String code=request.getParameter("code");	
		if (type.equals("jl")) {
			keyPointList = getJLTableInfo(code,"dw");
		}else if(type.equals("gg")){
			keyPointList = getGGTableInfo(code,"dw");
		}else {
			keyPointList = getTableInfo("dw");
		}				
		String s = gson.toJson(keyPointList);
		log.info("卷烟品牌重点表格查询结果："+s);		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);		
		return request;
		
		
	}  


	private String getTableJLSql(String code) {
         StringBuffer sql = new StringBuffer();
         sql.append(" 			select xz_parameter			, CIG_NAME				, PRODUCENUM				, PRODUCENUM_L			                        		");	
         sql.append(" 			, PRODUCENUM_GROWTH			, PRODUCENUM_GROWTH_RATE		, BS_SELLNUM				, BS_SELLNUM_L			                		");	
         sql.append(" 			, BS_SELLNUM_GROWTH			, BS_SELLNUM_GROWTH_RATE		, IB_TERMSTOCKNUM			, IB_TERMSTOCKNUM_L			            		");	
         sql.append(" 			, IB_TERMSTOCKNUM_GROWTH		, BS_SELLSUM				, BS_SELLSUM_L			, BS_SELLSUM_GROWTH			                		");	
         sql.append(" 			, BS_SELLSUM_GROWTH_RATE		, SINGLE_SELLSUM			, SINGLE_SELLSUM_L			, SINGLE_SELLSUM_GROWTH		            		");	
         sql.append(" 			, SINGLE_SELLSUM_GROWTH_RATE, EXTERNAL_MARKET_RATE, EXTERNAL_MARKET_L_RATE		, EXTERNAL_MARKET_RATE_GROWTHFROM  from (            	");
         sql.append(" 	SELECT																																													");																																											
         sql.append(" 	K.C_CLASS AS xz_parameter 																																										");
         sql.append(" 	,K.CIG_NAME  AS CIG_NAME   																																										");
         sql.append(" 	,CASE WHEN (PRODUCENUM)=0 THEN '-' ELSE  NVL(TO_CHAR(DECIMAL(ROUND(PRODUCENUM*1.0000/50000, 2), 16, 2),'999990.99'),'0') END AS PRODUCENUM																												");
         sql.append(" 	,CASE WHEN (PRODUCENUM_L)=0 THEN'-' ELSE  TO_CHAR(DECIMAL(ROUND(PRODUCENUM_L*1.0000/50000, 2), 16, 2),'999990.99') END  AS PRODUCENUM_L																													");
         sql.append(" 	,CASE WHEN (PRODUCENUM)=0 AND (PRODUCENUM - PRODUCENUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM_GROWTH  																						");
         sql.append(" 	,CASE WHEN (PRODUCENUM_L)=0 AND (PRODUCENUM - PRODUCENUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(PRODUCENUM_L,0,0,NULL,0,(PRODUCENUM - PRODUCENUM_L)*100.0000/PRODUCENUM_L), 2), 16, 2),'999990.99') END AS PRODUCENUM_GROWTH_RATE		  														");
         sql.append(" 	,CASE WHEN (K.BS_SELLNUM)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(K.BS_SELLNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM																													");
         sql.append(" 	,CASE WHEN (K.BS_SELLNUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(K.BS_SELLNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_L																												");
         sql.append(" 	,CASE WHEN (K.BS_SELLNUM)=0 AND (K.BS_SELLNUM - K.BS_SELLNUM_L)=0 THEN '-'ELSE  TO_CHAR(DECIMAL(ROUND((K.BS_SELLNUM - K.BS_SELLNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_GROWTH																					");
         sql.append(" 	,CASE WHEN (K.BS_SELLNUM_L)=0 AND (K.BS_SELLNUM - K.BS_SELLNUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(K.BS_SELLNUM_L,0,0,NULL,0,(K.BS_SELLNUM - K.BS_SELLNUM_L)*100.0000/K.BS_SELLNUM_L), 2), 16, 2),'999990.99') END AS BS_SELLNUM_GROWTH_RATE 														");
         sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM																												");
         sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END  AS IB_TERMSTOCKNUM_L																											");
         sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM)=0 AND (IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM_GROWTH																			");
         sql.append(" 	,CASE WHEN (BS_SELLSUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM																													");
         sql.append(" 	,CASE WHEN (BS_SELLSUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM_L*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_L																													");
         sql.append(" 	,CASE WHEN (BS_SELLSUM)=0 AND (BS_SELLSUM - BS_SELLSUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_GROWTH																						");
         sql.append(" 	,CASE WHEN (BS_SELLSUM_L)=0 AND (BS_SELLSUM - BS_SELLSUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLSUM_L,0,0,NULL,0,(BS_SELLSUM - BS_SELLSUM_L)*100.0000/BS_SELLSUM_L), 2), 16, 2),'999990.99') END AS BS_SELLSUM_GROWTH_RATE																");
         sql.append(" 	,CASE WHEN (SINGLE_SELLSUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM																														");
         sql.append(" 	,CASE WHEN (SINGLE_SELLSUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_L																													");
         sql.append(" 	,CASE WHEN (SINGLE_SELLSUM)=0 AND (SINGLE_SELLSUM - SINGLE_SELLSUM_L)=0 THEN '-'ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L), 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_GROWTH																					");
         sql.append(" 	,CASE WHEN (SINGLE_SELLSUM_L)=0 AND (SINGLE_SELLSUM - SINGLE_SELLSUM_L)=0 THEN '-'ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SINGLE_SELLSUM_L,0,0,NULL,0,(SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/SINGLE_SELLSUM_L), 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_GROWTH_RATE                                                                                               ");                                                                           
         sql.append(" 	,'-' AS EXTERNAL_MARKET_RATE                                                     																																			");
         sql.append(" 	,'-' AS EXTERNAL_MARKET_L_RATE																																										");
         sql.append(" 	,'-' AS EXTERNAL_MARKET_RATE_GROWTHFROM																																									");
         sql.append(" 																																														");
         sql.append(" 																																														");
         sql.append(" 	FROM																																													");
         sql.append(" 	(																																													");
         sql.append(" 		SELECT																																												");
         sql.append(" 			NVL(K.C_CLASS, '00') AS C_CLASS																																								");
         sql.append(" 			,NVL(P.CLASS_NAME,'合  计')    AS   CIG_NAME       																																					");
         sql.append(" 			,SUM(PRODUCENUM_Y_A) AS PRODUCENUM																																							");
         sql.append(" 			,SUM(PRODUCENUM_Y_AL) AS PRODUCENUM_L																																							");
         sql.append(" 			,SUM(TERMSTOCK1+TERM_STOCK1) AS IB_TERMSTOCKNUM																																						");
         sql.append(" 			,SUM(TERMSTOCK1_l+TERM_STOCK1_L) AS IB_TERMSTOCKNUM_L																																					");
         sql.append(" 			,SUM(BS_SELLNUM) AS BS_SELLNUM																																								");
         sql.append(" 			,SUM(BS_SELLNUM_L) AS BS_SELLNUM_L																																							");
         sql.append(" 			,SUM(BS_SELLSUM) AS BS_SELLSUM 																																								");
         sql.append(" 			,SUM(BS_SELLSUM_L) AS BS_SELLSUM_L 																																							");
         sql.append(" 			,SUM(SINGLE_SELLSUM) AS SINGLE_SELLSUM 																																							");
         sql.append(" 			,SUM(SINGLE_SELLSUM_L) AS SINGLE_SELLSUM_L  																																						");
         sql.append(" 		FROM (SELECT 																																											");
         sql.append(" 				K.C_CLASS																																									");
         sql.append(" 				,SUM(PRINTNUM1-REJECTNUM1+REPEATNUM1-JUMPNUM1) AS PRODUCENUM_Y_A																																");
         sql.append(" 				,SUM(PRINTNUM1_P-REJECTNUM1_P+REPEATNUM1_P-JUMPNUM1_P) AS PRODUCENUM_Y_AL																																");
         sql.append(" 				,0 AS TERM_STOCK1																																								");
         sql.append(" 				,0 AS TERM_STOCK1_L																																								");
         sql.append(" 				,SUM(TERMSTOCK1) AS TERMSTOCK1 																																							");
         sql.append(" 				,SUM(TERMSTOCK1_P) AS TERMSTOCK1_L																																						");
         sql.append(" 				,0 AS BS_SELLNUM																																								");
         sql.append(" 				,0 AS BS_SELLNUM_L																																								");
         sql.append(" 				,0 AS BS_SELLSUM																																								");
         sql.append(" 				,0 AS BS_SELLSUM_L																																								");
         sql.append(" 				,0 AS SINGLE_SELLSUM 																																								");
         sql.append(" 				,0 AS SINGLE_SELLSUM_L  																																							");
         sql.append(" 				FROM NICK_K_TJIN_Y_ALL K																																							");
         sql.append(" 				LEFT JOIN cig_property_div B																																						");
         sql.append(" 					ON K.C_BRAND=B.C_BRAND																																							");
         sql.append(" 				WHERE	1=1																																									");
         sql.append(" 				AND IS_NRT= '1'																																									");
         sql.append(" 				AND C_CLASS <>'06'																																								");
         sql.append(" 				AND B.C_CIG='").append(code).append("'");
         sql.append(" 				AND Y = YEAR(CURRENT DATE-1 DAYS) 																																						");
         sql.append(" 				GROUP BY C_CLASS																																								");
		 sql.append("			 HAVING (    					");
		 sql.append(" 			SUM(PRINTNUM1-REJECTNUM1+REPEATNUM1-JUMPNUM1)>0    ");
		 sql.append(" 			OR  SUM(PRINTNUM1_P-REJECTNUM1_P+REPEATNUM1_P-JUMPNUM1_P)>0    ");
		 sql.append(" 		OR	SUM(TERMSTOCK1)>0    ");
		 sql.append(" 		OR 	SUM(TERMSTOCK1_P)>0    ");
		 sql.append(" 		)    ");
         sql.append(" 		UNION ALL																																											");
         sql.append(" 			SELECT																																											");
         sql.append(" 				K.C_CLASS																																									");
         sql.append(" 				,0 AS PRODUCENUM_Y_A																																								");
         sql.append(" 				,0 AS PRODUCENUM_Y_AL																																								");
         sql.append(" 				,SUM(TERM_STOCK1) AS TERM_STOCK1																																						");
         sql.append(" 				,SUM(TERM_STOCK1_P) AS TERM_STOCK1_L																																						");
         sql.append(" 				,0 AS TERMSTOCK1																																								");
         sql.append(" 				,0 AS TERMSTOCK1_L																																								");
         sql.append(" 				,SUM(PRINT_NUM1-RBACK_NUM1)  AS BS_SELLNUM																																				");
         sql.append(" 				,SUM(PRINT_NUM1_P-RBACK_NUM1_P) AS BS_SELLNUM_L																																				");
         sql.append(" 				,SUM(PRINT_SUM-RBACK_SUM) AS BS_SELLSUM																																					");
         sql.append(" 				,SUM(PRINT_SUM_P-RBACK_SUM_P) AS BS_SELLSUM_L																																				");
         sql.append(" 				,DECODE(SUM(PRINT_NUM1-RBACK_NUM1),0,0,NULL,0,SUM(PRINT_SUM-RBACK_SUM)*5.0000/SUM(PRINT_NUM1-RBACK_NUM1)) AS SINGLE_SELLSUM																								");
         sql.append(" 				,DECODE(SUM(PRINT_NUM1_P-RBACK_NUM1_P),0,0,NULL,0,SUM(PRINT_SUM_P-RBACK_SUM_P)*5.0000/SUM(PRINT_NUM1_P-RBACK_NUM1_P)) AS SINGLE_SELLSUM_L																							");
         sql.append(" 				FROM	NICK_K_TJBS_Y_ALL K																																							");
         sql.append(" 				LEFT JOIN cig_property_div B																																						");
         sql.append(" 					ON K.C_BRAND=B.C_BRAND																																							");
         sql.append(" 					WHERE	1=1																																								");
         sql.append(" 					AND C_CLASS <> '06'																																							");
         sql.append(" 					AND B.C_CIG='").append(code).append("'");
         sql.append(" 					AND Y = YEAR(CURRENT DATE-1 DAYS)																																					");
         sql.append(" 				GROUP BY C_CLASS																																								");
         sql.append("				         HAVING(				");
		 sql.append("							SUM(TERM_STOCK1)>0				");
		 sql.append("							OR	SUM(TERM_STOCK1_P)>0				");
		 sql.append("							OR	SUM(PRINT_NUM1-RBACK_NUM1)>0				");
		 sql.append("							OR	SUM(PRINT_NUM1_P-RBACK_NUM1_P)>0				");
		 sql.append("							OR	SUM(PRINT_SUM-RBACK_SUM)>0				");
		 sql.append("							OR	SUM(PRINT_SUM_P-RBACK_SUM_P)>0				");
		 sql.append("							OR	DECODE(SUM(PRINT_NUM1-RBACK_NUM1),0,0,NULL,0,SUM(PRINT_SUM-RBACK_SUM)*5.0000/SUM(PRINT_NUM1-RBACK_NUM1))>0				");
		 sql.append("							OR	DECODE(SUM(PRINT_NUM1_P-RBACK_NUM1_P),0,0,NULL,0,SUM(PRINT_SUM_P-RBACK_SUM_P)*5.0000/SUM(PRINT_NUM1_P-RBACK_NUM1_P))>0				");
		 sql.append("							)				");
         sql.append(" 				) K																																										");
         sql.append(" 				inner JOIN DW_T_CLASS_CODE P																																							");
         sql.append(" 				ON K.C_CLASS = P.CODE																																								");
         sql.append(" 				GROUP BY K.C_CLASS,P.CLASS_NAME																																							");
         sql.append(" 				WITH ROLLUP																																									");
         sql.append(" 					HAVING																																									");
         sql.append(" 				(																																										");
         sql.append(" 					(K.C_CLASS IS NULL AND P.CLASS_NAME IS NULL)																																				");
         sql.append(" 					OR(K.C_CLASS IS NOT NULL AND P.CLASS_NAME IS NOT NULL)																																			");
         sql.append(" 				)																																										");
         sql.append(" 		) K																																												");
         sql.append(" 		ORDER BY K.C_CLASS 																																										");
         sql.append(" 																																													");
         sql.append("			 ) where xz_parameter not in ('06') WITH UR								                                                             	");	
         log.info("重点品牌下钻价类数据处理sql: " + sql.toString());
		return sql.toString();
	}
	
	/**
	 * 重点品牌表格下钻价类查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<KeyPointTableVO> getJLTableInfo(String code,String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<KeyPointTableVO> list = new ArrayList<KeyPointTableVO>();
		try {
			sql = getTableJLSql(code);
			list = dbbean.executeQuery(sql, KeyPointTableVO.class.getName());
		} catch (Exception e) {
			log.error("getJLTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	


	private String getTableGGSql(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append(" 	SELECT																																						");																							
		sql.append(" 	K.C_BRAND AS xz_parameter 																																			");
		sql.append(" 	,K.CIG_NAME  AS CIG_NAME   																																			");
		sql.append(" 	,CASE WHEN (PRODUCENUM1)=0 THEN '-' ELSE  NVL(TO_CHAR(DECIMAL(ROUND(PRODUCENUM1*1.0000/50000, 2), 16, 2),'999990.99'),'0') END AS PRODUCENUM																					");
		sql.append(" 	,CASE WHEN (PRODUCENUM_L)=0 THEN'-' ELSE  TO_CHAR(DECIMAL(ROUND(PRODUCENUM_L*1.0000/50000, 2), 16, 2),'999990.99') END  AS PRODUCENUM_L                                              																");
		sql.append(" 	,CASE WHEN (PRODUCENUM1)=0 and (PRODUCENUM1 - PRODUCENUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM_GROWTH  															");
		sql.append(" 	,CASE WHEN (PRODUCENUM_L)=0 or (PRODUCENUM1)=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND(DECODE(PRODUCENUM_L,0,0,NULL,0,(PRODUCENUM1 - PRODUCENUM_L)*100.0000/PRODUCENUM_L), 2), 16, 2)) END AS PRODUCENUM_GROWTH_RATE			  						");
		sql.append(" 	,CASE WHEN (K.BS_SELLNUM)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(K.BS_SELLNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM                                                  																");
		sql.append(" 	,CASE WHEN (K.BS_SELLNUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(K.BS_SELLNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_L																					");
		sql.append(" 	,CASE WHEN (K.BS_SELLNUM)=0 and (K.BS_SELLNUM - K.BS_SELLNUM_L)=0 THEN '-'ELSE  TO_CHAR(DECIMAL(ROUND((K.BS_SELLNUM - K.BS_SELLNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_GROWTH														");
		sql.append(" 	,CASE WHEN (K.BS_SELLNUM_L)=0 or (K.BS_SELLNUM )=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND(DECODE(K.BS_SELLNUM_L,0,0,NULL,0,(K.BS_SELLNUM - K.BS_SELLNUM_L)*100.0000/K.BS_SELLNUM_L), 2), 16, 2)) END AS BS_SELLNUM_GROWTH_RATE								");
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM																					");
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END  AS IB_TERMSTOCKNUM_L																				");
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM)=0 and (IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM_GROWTH												");
		sql.append(" 	,CASE WHEN (BS_SELLSUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM																						");
		sql.append(" 	,CASE WHEN (BS_SELLSUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM_L*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_L																						");
		sql.append(" 	,CASE WHEN (BS_SELLSUM)=0 and (BS_SELLSUM - BS_SELLSUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_GROWTH															");
		sql.append(" 	,CASE WHEN (BS_SELLSUM_L)=0 or (BS_SELLSUM)=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND(DECODE(BS_SELLSUM_L,0,0,NULL,0,(BS_SELLSUM - BS_SELLSUM_L)*100.0000/BS_SELLSUM_L), 2), 16, 2)) END AS BS_SELLSUM_GROWTH_RATE								");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM																							");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_L																						");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM)=0 and (SINGLE_SELLSUM - SINGLE_SELLSUM_L)=0 THEN '-'ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L), 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_GROWTH														");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM_L)=0 or (SINGLE_SELLSUM )=0 THEN '-'ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SINGLE_SELLSUM_L,0,0,NULL,0,(SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/SINGLE_SELLSUM_L), 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_GROWTH_RATE	                                ");                                                                                                                                           
		sql.append(" 	,CASE WHEN DECODE(E.BS_SELLNUM,0,0,NULL,0,E.BS_SELLNUM_O*100.0000/E.BS_SELLNUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(E.BS_SELLNUM,0,0,NULL,0,E.BS_SELLNUM_O*100.0000/E.BS_SELLNUM), 2), 16, 2),'999990.99')  END AS EXTERNAL_MARKET_RATE 								");
		sql.append(" 	,CASE WHEN DECODE(E.BS_SELLNUM_L,0,0,NULL,0,E.BS_SELLNUM_O_L*100.0000/E.BS_SELLNUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(E.BS_SELLNUM_L,0,0,NULL,0,E.BS_SELLNUM_O_L*100.0000/E.BS_SELLNUM_L), 2), 16, 2),'999990.99') END AS EXTERNAL_MARKET_L_RATE							");
		sql.append(" 	,TO_CHAR(DECIMAL(ROUND(DECODE(E.BS_SELLNUM,0,0,NULL,0,E.BS_SELLNUM_O*100.0000/E.BS_SELLNUM) - DECODE(E.BS_SELLNUM_L,0,0,NULL,0,E.BS_SELLNUM_O_L*100.0000/E.BS_SELLNUM_L), 2), 16, 2),'999990.99') AS EXTERNAL_MARKET_RATE_GROWTHFROM										");
		sql.append(" 																																							");
		sql.append(" 	FROM																																						");
		sql.append(" 	(																																						");
		sql.append(" 		SELECT																																					");
		sql.append(" 			NVL(P.C_BRAND, '00') AS C_BRAND																																	");
		sql.append(" 			,NVL(P.CIG_NAME,'合  计')    AS   CIG_NAME																															");
		sql.append(" 			,SUM(PRODUCENUM_Y_A) AS PRODUCENUM1              																														");
		sql.append(" 			,SUM(PRODUCENUM_Y_AL) AS PRODUCENUM_L           																														");
		sql.append(" 			,SUM(TERMSTOCK1+TERM_STOCK1) AS IB_TERMSTOCKNUM     																														");
		sql.append(" 			,SUM(TERMSTOCK1_l+TERM_STOCK1_L) AS IB_TERMSTOCKNUM_L   																													");
		sql.append(" 			,SUM(BS_SELLNUM) AS BS_SELLNUM																																	");
		sql.append(" 			,SUM(BS_SELLNUM_L) AS BS_SELLNUM_L																																");
		sql.append(" 			,SUM(BS_SELLSUM) AS BS_SELLSUM 																																	");
		sql.append(" 			,SUM(BS_SELLSUM_L) AS BS_SELLSUM_L 																																");
		sql.append(" 			,SUM(SINGLE_SELLSUM) AS SINGLE_SELLSUM 																																");
		sql.append(" 			,SUM(SINGLE_SELLSUM_L) AS SINGLE_SELLSUM_L  																															");
		sql.append(" 		FROM (SELECT 																																				");
		sql.append(" 				K.C_BRAND																																		");
		sql.append(" 				,SUM(PRINTNUM1-REJECTNUM1+REPEATNUM1-JUMPNUM1) AS PRODUCENUM_Y_A																									");
		sql.append(" 				,SUM(PRINTNUM1_P-REJECTNUM1_P+REPEATNUM1_P-JUMPNUM1_P) AS PRODUCENUM_Y_AL																									");
		sql.append(" 				,0 AS TERM_STOCK1																																	");
		sql.append(" 				,0 AS TERM_STOCK1_L																																	");
		sql.append(" 				,SUM(TERMSTOCK1) AS TERMSTOCK1 																																");
		sql.append(" 				,SUM(TERMSTOCK1_P) AS TERMSTOCK1_L																															");
		sql.append(" 				,0 AS BS_SELLNUM																																	");
		sql.append(" 				,0 AS BS_SELLNUM_L																																	");
		sql.append(" 				,0 AS BS_SELLSUM																																	");
		sql.append(" 				,0 AS BS_SELLSUM_L																																	");
		sql.append(" 				,0 AS SINGLE_SELLSUM 																																	");
		sql.append(" 				,0 AS SINGLE_SELLSUM_L  																																");
		sql.append(" 		FROM NICK_K_TJIN_Y_ALL K  LEFT JOIN cig_property_div A  ON K.C_Brand=A.C_BRAND 	left join NICK_TJYY_CIGARETTE SC	");
		sql.append(" 		 on K.C_BRAND = SC.cig_codebar  WHERE	1=1		AND K.C_BRAND = SC.cig_codebar and	");
		sql.append(" 	 ((D_YEAR<='2017' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_innx='1')) 		");
		sql.append(" 				AND A.C_CIG='").append(code).append("'");
		sql.append(" 				AND K.Y = YEAR(CURRENT DATE-1 DAYS) 																															");
		sql.append(" 				GROUP BY K.C_BRAND																																	");
		sql.append("  			HAVING (				");
		sql.append("			SUM(PRINTNUM1-REJECTNUM1+REPEATNUM1-JUMPNUM1)>0	");
		sql.append("			OR  SUM(PRINTNUM1_P-REJECTNUM1_P+REPEATNUM1_P-JUMPNUM1_P)>0	");  
		sql.append("			OR	SUM(TERMSTOCK1)>0	");
		sql.append("			OR 	SUM(TERMSTOCK1_P)>0 	");
		sql.append("			) 					");  

		sql.append(" 		UNION ALL																																				");
		sql.append(" 			SELECT																																				");
		sql.append(" 				K.C_BRAND																																		");
		sql.append(" 				,0 AS PRODUCENUM_Y_A																																	");
		sql.append(" 				,0 AS PRODUCENUM_Y_AL																																	");
		sql.append(" 				,SUM(TERM_STOCK1) AS TERM_STOCK1																															");
		sql.append(" 				,SUM(TERM_STOCK1_P) AS TERM_STOCK1_L																															");
		sql.append(" 				,0 AS TERMSTOCK1																																	");
		sql.append(" 				,0 AS TERMSTOCK1_L																																	");
		sql.append(" 				,SUM(PRINT_NUM1-RBACK_NUM1)  AS BS_SELLNUM																													");
		sql.append(" 				,SUM(PRINT_NUM1_P-RBACK_NUM1_P) AS BS_SELLNUM_L																													");
		sql.append(" 				,SUM(PRINT_SUM-RBACK_SUM) AS BS_SELLSUM																														");
		sql.append(" 				,SUM(PRINT_SUM_P-RBACK_SUM_P) AS BS_SELLSUM_L																													");
		sql.append(" 				,DECODE(SUM(PRINT_NUM1-RBACK_NUM1),0,0,NULL,0,SUM(PRINT_SUM-RBACK_SUM)*5.0000/SUM(PRINT_NUM1-RBACK_NUM1)) AS SINGLE_SELLSUM																	");
		sql.append(" 				,DECODE(SUM(PRINT_NUM1_P-RBACK_NUM1_P),0,0,NULL,0,SUM(PRINT_SUM_P-RBACK_SUM_P)*5.0000/SUM(PRINT_NUM1_P-RBACK_NUM1_P)) AS SINGLE_SELLSUM_L																");
		sql.append(" 		FROM NICK_K_TJBS_Y_ALL K  LEFT JOIN cig_property_div A  ON K.C_Brand=A.C_BRAND 	left join NICK_TJYY_CIGARETTE SC	");
		sql.append(" 		 on K.C_BRAND = SC.cig_codebar  WHERE	1=1		AND K.C_BRAND = SC.cig_codebar and	");
		sql.append(" 	((D_YEAR<='2017' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_bsnx='1'))		");
		sql.append(" 					AND A.C_CIG='").append(code).append("'");
		sql.append(" 					AND K.Y = YEAR(CURRENT DATE-1 DAYS)																														");
		sql.append(" 				GROUP BY K.C_BRAND																																	");
		sql.append(" 				) K																																			");
		sql.append(" 				inner JOIN (SELECT C_CIG,C_BRAND,CIG_NAME FROM cig_property_div WHERE IS_MAIN_BRAND ='1') P																							");
		sql.append(" 				ON K.C_BRAND = P.C_BRAND																																");
		sql.append(" 				GROUP BY P.C_BRAND,P.CIG_NAME																																");
		sql.append(" 				WITH ROLLUP																																		");
		sql.append(" 					HAVING																																		");
		sql.append(" 				(																																			");
		sql.append(" 					(P.C_BRAND IS NULL AND P.CIG_NAME IS NULL)																													");
		sql.append(" 					OR(P.C_BRAND IS NOT NULL AND P.CIG_NAME IS NOT NULL) 																												");
		sql.append(" 					AND (																																		");
		sql.append(" 					SUM(PRODUCENUM_Y_A)					<>0   																											");
		sql.append(" 					OR SUM(PRODUCENUM_Y_AL)				<>0   																												");
		sql.append(" 					OR SUM(TERMSTOCK1+TERM_STOCK1)		<>0   																													");
		sql.append(" 					OR SUM(TERMSTOCK1_l+TERM_STOCK1_L)	<>0   																													");
		sql.append(" 					OR SUM(BS_SELLNUM)					<>0																											");
		sql.append(" 					OR SUM(BS_SELLNUM_L)				<>0																												");
		sql.append(" 					OR SUM(BS_SELLSUM)					<>0 																											");
		sql.append(" 					OR SUM(BS_SELLSUM_L)				<>0 																												");
		sql.append(" 					OR SUM(SINGLE_SELLSUM)				<>0 																												");
		sql.append(" 					OR SUM(SINGLE_SELLSUM_L)			<>0  																												");
		sql.append(" 					)																																		");
		sql.append(" 				)																																			");
		sql.append(" 		) K																																					");
		sql.append(" 		LEFT JOIN																																				");
		sql.append(" 		(																																					");
		sql.append(" 		SELECT																																					");
		sql.append(" 			NVL(P.C_BRAND, '00') AS C_BRAND																																	");
		sql.append(" 			,NVL(P.CIG_NAME,'合  计')    AS   CIG_NAME      																														");
		sql.append(" 			,SUM(SELLNUM                            									)	AS BS_SELLNUM																				");
		sql.append(" 			,SUM(SELLNUM_L                          									)	AS BS_SELLNUM_L																				");
		sql.append(" 			,SUM(CASE WHEN K.I_PROVINCE = O.ORG_OWNPROVINCE THEN 0 ELSE SELLNUM END		) 	AS BS_SELLNUM_O																								");
		sql.append(" 			,SUM(CASE WHEN K.I_PROVINCE = O.ORG_OWNPROVINCE THEN 0 ELSE SELLNUM_L END	)  	AS BS_SELLNUM_O_L																							");
		sql.append(" 		FROM																																					");
		sql.append(" 			(																																				");
		sql.append(" 				SELECT 																																			");
		sql.append(" 					B.C_BRAND																																	");
		sql.append(" 					,B.C_CIG																																	");
		sql.append(" 					,B.CIG_NAME																																	");
		sql.append(" 					,I_PROVINCE																																	");
		sql.append(" 					,SUM(PRINT_NUM1 	- RBACK_NUM1	)     AS SELLNUM																										");
		sql.append(" 					,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	)     AS SELLNUM_L																										");
		sql.append(" 		FROM NICK_K_TJBS_Y_ALL A,cig_property_div B	,NICK_TJYY_CIGARETTE SC	  WHERE 1=1			");
		sql.append(" 		AND A.C_BRAND=B.C_BRAND	  and A.C_BRAND = SC.cig_codebar and  ");
		sql.append(" 	((D_YEAR<='2017' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_bsnx='1')) 		");
		sql.append(" 					AND B.C_CIG='").append(code).append("'");
		sql.append(" 					AND Y = YEAR(CURRENT DATE-1 DAYS)																														");
		sql.append(" 				GROUP BY B.C_BRAND, B.C_CIG,B.CIG_NAME,I_PROVINCE																													");
		sql.append(" 			) K																																				");
		sql.append(" 		INNER JOIN cig_property_div P																																	");
		sql.append(" 			ON K.C_BRAND = P.C_BRAND																																	");
		sql.append(" 		INNER JOIN NICK_STMA_ORGANIZATION O																																	");
		sql.append(" 			ON O.ORG_CODE = P.CIG_PRODUCER																																	");
		sql.append(" 		GROUP BY P.C_BRAND,P.CIG_NAME																																		");
		sql.append(" 		WITH ROLLUP																																				");
		sql.append(" 			HAVING																																				");
		sql.append(" 		(																																					");
		sql.append(" 			(P.C_BRAND IS NULL AND P.CIG_NAME IS NULL)																															");
		sql.append(" 			OR(P.C_BRAND IS NOT NULL AND P.CIG_NAME IS NOT NULL)																														");
		sql.append(" 		)																																					");
		sql.append(" 		) E																																					");
		sql.append(" 		ON K.C_BRAND = E.C_BRAND																																		");
		sql.append(" 		ORDER BY DECODE(K.CIG_NAME,'合  计',0,1) ASC, PRODUCENUM1 DESC																														");
		sql.append(" 		WITH UR																																					");
		log.info("重点品牌下钻规格数据处理sql: " + sql.toString());
		return sql.toString();
	}
	
	/**
	 * 重点品牌表格下钻规格查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<KeyPointTableVO> getGGTableInfo(String code,String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<KeyPointTableVO> list = new ArrayList<KeyPointTableVO>();
		try {
			sql = getTableGGSql(code);
			list = dbbean.executeQuery(sql, KeyPointTableVO.class.getName());
		} catch (Exception e) {
			log.error("getGGTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}


	private String getTableSql() {
		StringBuffer sql = new StringBuffer();
		sql.append(" 	SELECT																																															");
		sql.append(" 	K.C_CIG AS xz_parameter 																																											      ");
		sql.append(" 	,K.CIG_NAME  AS CIG_NAME   																																											      ");
		sql.append(" 	,CASE WHEN (PRODUCENUM)=0 THEN '-' ELSE  NVL(TO_CHAR(DECIMAL(ROUND(PRODUCENUM*1.0000/50000, 2), 16, 2),'999990.99'),'0') END AS PRODUCENUM																													      ");
		sql.append(" 	,CASE WHEN (PRODUCENUM_L)=0 THEN'-' ELSE  TO_CHAR(DECIMAL(ROUND(PRODUCENUM_L*1.0000/50000, 2), 16, 2),'999990.99') END  AS PRODUCENUM_L																														      ");
		sql.append(" 	,CASE WHEN (PRODUCENUM)=0 AND (PRODUCENUM - PRODUCENUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM_GROWTH  																							      ");
		sql.append(" 	,CASE WHEN (PRODUCENUM_L)=0 AND (PRODUCENUM - PRODUCENUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(PRODUCENUM_L,0,0,NULL,0,(PRODUCENUM - PRODUCENUM_L)*100.0000/PRODUCENUM_L), 2), 16, 2),'999990.99') END AS PRODUCENUM_GROWTH_RATE     																	      ");
		sql.append(" 	,CASE WHEN (K.BS_SELLNUM)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(K.BS_SELLNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM                                                  																								      ");
		sql.append(" 	,CASE WHEN (K.BS_SELLNUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(K.BS_SELLNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_L																													      ");
		sql.append(" 	,CASE WHEN (K.BS_SELLNUM)=0 AND (K.BS_SELLNUM - K.BS_SELLNUM_L)=0 THEN '-'ELSE  TO_CHAR(DECIMAL(ROUND((K.BS_SELLNUM - K.BS_SELLNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_GROWTH																						      ");
		sql.append(" 	,CASE WHEN (K.BS_SELLNUM_L)=0 AND (K.BS_SELLNUM - K.BS_SELLNUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(K.BS_SELLNUM_L,0,0,NULL,0,(K.BS_SELLNUM - K.BS_SELLNUM_L)*100.0000/K.BS_SELLNUM_L), 2), 16, 2),'999990.99') END AS BS_SELLNUM_GROWTH_RATE  															      ");
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM																													      ");
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END  AS IB_TERMSTOCKNUM_L																												      ");
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM)=0 AND (IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM_GROWTH																				      ");
		sql.append(" 	,CASE WHEN (BS_SELLSUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM																														      ");
		sql.append(" 	,CASE WHEN (BS_SELLSUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM_L*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_L																														      ");
		sql.append(" 	,CASE WHEN (BS_SELLSUM)=0 AND (BS_SELLSUM - BS_SELLSUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_GROWTH																							      ");
		sql.append(" 	,CASE WHEN (BS_SELLSUM_L)=0 AND (BS_SELLSUM - BS_SELLSUM_L)=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLSUM_L,0,0,NULL,0,(BS_SELLSUM - BS_SELLSUM_L)*100.0000/BS_SELLSUM_L), 2), 16, 2),'999990.99') END AS BS_SELLSUM_GROWTH_RATE																	      ");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM																															      ");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_L																														      ");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM)=0 AND (SINGLE_SELLSUM - SINGLE_SELLSUM_L)=0 THEN '-'ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L), 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_GROWTH																						      ");
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM_L)=0 AND (SINGLE_SELLSUM - SINGLE_SELLSUM_L)=0 THEN '-'ELSE TO_CHAR(DECIMAL(ROUND(DECODE(SINGLE_SELLSUM_L,0,0,NULL,0,(SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/SINGLE_SELLSUM_L), 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_GROWTH_RATE                                                                                                             ");                                                                  
		sql.append(" 	,CASE WHEN DECODE(E.BS_SELLNUM,0,0,NULL,0,E.BS_SELLNUM_O*100.0000/E.BS_SELLNUM)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(E.BS_SELLNUM,0,0,NULL,0,E.BS_SELLNUM_O*100.0000/E.BS_SELLNUM), 2), 16, 2),'999990.99')  END AS EXTERNAL_MARKET_RATE 																      ");
		sql.append(" 	,CASE WHEN DECODE(E.BS_SELLNUM_L,0,0,NULL,0,E.BS_SELLNUM_O_L*100.0000/E.BS_SELLNUM_L)=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(E.BS_SELLNUM_L,0,0,NULL,0,E.BS_SELLNUM_O_L*100.0000/E.BS_SELLNUM_L), 2), 16, 2),'999990.99') END AS EXTERNAL_MARKET_L_RATE															      ");
		sql.append(" 	,TO_CHAR(DECIMAL(ROUND(DECODE(E.BS_SELLNUM,0,0,NULL,0,E.BS_SELLNUM_O*100.0000/E.BS_SELLNUM) - DECODE(E.BS_SELLNUM_L,0,0,NULL,0,E.BS_SELLNUM_O_L*100.0000/E.BS_SELLNUM_L), 2), 16, 2),'999990.99') AS EXTERNAL_MARKET_RATE_GROWTHFROM																		      ");
		sql.append(" 	FROM																																														      ");
		sql.append(" 	(																																														      ");
		sql.append(" 		SELECT																																													      ");
		sql.append(" 			NVL(P.C_CIG, '00') AS C_CIG																																									      ");
		sql.append(" 			,NVL(P.CIG_MARKNAME,'合  计')    AS   CIG_NAME																																							      ");
		sql.append(" 			,SUM(PRODUCENUM_Y_A) AS PRODUCENUM																																								      ");
		sql.append(" 			,SUM(PRODUCENUM_Y_AL) AS PRODUCENUM_L																																								      ");
		sql.append(" 			,SUM(TERMSTOCK1+TERM_STOCK1) AS IB_TERMSTOCKNUM																																							      ");
		sql.append(" 			,SUM(TERMSTOCK1_l+TERM_STOCK1_L) AS IB_TERMSTOCKNUM_L																																						      ");
		sql.append(" 			,SUM(BS_SELLNUM) AS BS_SELLNUM																																									      ");
		sql.append(" 			,SUM(BS_SELLNUM_L) AS BS_SELLNUM_L																																								      ");
		sql.append(" 			,SUM(BS_SELLSUM) AS BS_SELLSUM																																									      ");
		sql.append(" 			,SUM(BS_SELLSUM_L) AS BS_SELLSUM_L																																								      ");
		sql.append(" 			,DECODE(SUM(BS_SELLNUM),0,0,NULL,0,SUM(BS_SELLSUM)*5.0000/SUM(BS_SELLNUM)) AS SINGLE_SELLSUM																																	      ");
		sql.append(" 			,DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L)*5.0000/SUM(BS_SELLNUM_L)) AS SINGLE_SELLSUM_L																																      ");
		sql.append(" 																																															      ");
		sql.append(" 		FROM (																																													      ");
		sql.append(" 			SELECT 																																												      ");
		sql.append(" 				C_BRAND																																											      ");
		sql.append(" 				,SUM(PRINTNUM1-REJECTNUM1+REPEATNUM1-JUMPNUM1) AS PRODUCENUM_Y_A																																	      ");
		sql.append(" 				,SUM(PRINTNUM1_P-REJECTNUM1_P+REPEATNUM1_P-JUMPNUM1_P) AS PRODUCENUM_Y_AL																																	      ");
		sql.append(" 				,0 AS TERM_STOCK1																																									      ");
		sql.append(" 				,0 AS TERM_STOCK1_L																																									      ");
		sql.append(" 				,SUM(TERMSTOCK1) AS TERMSTOCK1 																																								      ");
		sql.append(" 				,SUM(TERMSTOCK1_P) AS TERMSTOCK1_L																																							      ");
		sql.append(" 				,0 AS BS_SELLNUM																																									      ");
		sql.append(" 				,0 AS BS_SELLNUM_L																																									      ");
		sql.append(" 				,0 AS BS_SELLSUM																																									      ");
		sql.append(" 				,0 AS BS_SELLSUM_L																																									      ");
		sql.append("		FROM NICK_K_TJIN_Y_ALL   NK,NICK_TJYY_CIGARETTE SC             ");
		sql.append("		WHERE 1=1              ");
		sql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))         ");
		sql.append(" 				AND Y = YEAR(CURRENT DATE-1 DAYS) 																																							      ");
		sql.append(" 				GROUP BY C_BRAND																																									      ");
		sql.append(" 		UNION ALL																																												      ");
		sql.append(" 			SELECT																																												      ");
		sql.append(" 				C_BRAND																																											      ");
		sql.append(" 				,0 AS PRODUCENUM_Y_A																																									      ");
		sql.append(" 				,0 AS PRODUCENUM_Y_AL																																									      ");
		sql.append(" 				,SUM(TERM_STOCK1) AS TERM_STOCK1																																							      ");
		sql.append(" 				,SUM(TERM_STOCK1_P) AS TERM_STOCK1_L																																							      ");
		sql.append(" 				,0 AS TERMSTOCK1																																									      ");
		sql.append(" 				,0 AS TERMSTOCK1_L																																									      ");
		sql.append(" 				,SUM(PRINT_NUM1-RBACK_NUM1)  AS BS_SELLNUM																																					      ");
		sql.append(" 				,SUM(PRINT_NUM1_P-RBACK_NUM1_P) AS BS_SELLNUM_L																																					      ");
		sql.append(" 				,SUM(PRINT_SUM-RBACK_SUM) AS BS_SELLSUM																																						      ");
		sql.append(" 				,SUM(PRINT_SUM_P-RBACK_SUM_P) AS BS_SELLSUM_L																																					      ");
		sql.append("			FROM NICK_K_TJBS_Y_ALL    NK,NICK_TJYY_CIGARETTE SC         ");
		sql.append("		WHERE NK.C_BRAND = SC.cig_codebar and      ");
		sql.append("		((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))        																																		      ");
		sql.append(" 					AND Y = YEAR(CURRENT DATE-1 DAYS)																																						      ");
		sql.append(" 				GROUP BY C_BRAND																																									      ");
		sql.append(" 				) K																																											      ");
		sql.append(" 				inner JOIN 																																										      ");
		sql.append(" 				(SELECT C_CIG,C_BRAND,CIG_MARKNAME FROM cig_property_div WHERE IS_MAIN_BRAND ='1') P																																      ");
		sql.append(" 				ON K.C_BRAND = P.C_BRAND																																								      ");
		sql.append(" 				GROUP BY P.C_CIG,P.CIG_MARKNAME																																								      ");
		sql.append(" 				WITH ROLLUP																																										      ");
		sql.append(" 					HAVING																																										      ");
		sql.append(" 				(																																											      ");
		sql.append(" 					(P.C_CIG IS NULL AND P.CIG_MARKNAME IS NULL)																																					      ");
		sql.append(" 					OR(P.C_CIG IS NOT NULL AND P.CIG_MARKNAME IS NOT NULL)																																				      ");
		sql.append(" 					AND (																																										      ");
		sql.append(" 					SUM(PRODUCENUM_Y_A)					<>0   																																			      ");
		sql.append(" 					OR SUM(PRODUCENUM_Y_AL)				<>0   																																				      ");
		sql.append(" 					OR SUM(TERMSTOCK1+TERM_STOCK1)		<>0   																																					      ");
		sql.append(" 					OR SUM(TERMSTOCK1_l+TERM_STOCK1_L)	<>0   																																					      ");
		sql.append(" 					OR SUM(BS_SELLNUM)					<>0																																			      ");
		sql.append(" 					OR SUM(BS_SELLNUM_L)				<>0																																				      ");
		sql.append(" 					OR SUM(BS_SELLSUM)					<>0 																																			      ");
		sql.append(" 					OR SUM(BS_SELLSUM_L)				<>0 																																				      ");
		sql.append(" 					OR DECODE(SUM(BS_SELLNUM),0,0,NULL,0,SUM(BS_SELLSUM)*5.0000/SUM(BS_SELLNUM))				<>0 																													      ");
		sql.append(" 					OR DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L)*5.0000/SUM(BS_SELLNUM_L))			<>0  																													      ");
		sql.append(" 					)																																										      ");
		sql.append(" 				)																																											      ");
		sql.append(" 		) K																																													      ");
		sql.append(" 		LEFT JOIN																																												      ");
		sql.append(" 		(																																													      ");
		sql.append(" 		SELECT																																													      ");
		sql.append(" 			NVL(P.C_CIG, '00') AS C_CIG																																									      ");
		sql.append(" 			,NVL(P.CIG_MARKNAME,'合  计')    AS   CIG_NAME																																							      ");
		sql.append(" 			,SUM(SELLNUM                            									)	AS BS_SELLNUM																												      ");
		sql.append(" 			,SUM(SELLNUM_L                          									)	AS BS_SELLNUM_L																												      ");
		sql.append(" 			,SUM(CASE WHEN K.I_PROVINCE = O.ORG_OWNPROVINCE THEN 0 ELSE SELLNUM END		) 	AS BS_SELLNUM_O																																      ");
		sql.append(" 			,SUM(CASE WHEN K.I_PROVINCE = O.ORG_OWNPROVINCE THEN 0 ELSE SELLNUM_L END	)  	AS BS_SELLNUM_O_L																															      ");
		sql.append(" 		FROM																																													      ");
		sql.append(" 			(																																												      ");
		sql.append(" 				SELECT 																																											      ");
		sql.append(" 					B.C_BRAND																																									      ");
		sql.append(" 					,B.C_CIG																																									      ");
		sql.append(" 					,B.CIG_MARKNAME																																									      ");
		sql.append(" 					,I_PROVINCE																																									      ");
		sql.append(" 					,SUM(PRINT_NUM1 	- RBACK_NUM1	)     AS SELLNUM																																		      ");
		sql.append(" 					,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	)     AS SELLNUM_L																																		      ");
		sql.append(" 		FROM NICK_K_TJBS_Y_ALL A,cig_property_div B	,NICK_TJYY_CIGARETTE SC  			      ");
		sql.append(" 		 WHERE 1=1	AND A.C_BRAND=B.C_BRAND	  and A.C_BRAND = SC.cig_codebar and       ");
		sql.append(" 		((D_YEAR<='2017' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_bsnx='1'))       ");
		sql.append(" 					AND IS_MAIN_BRAND='1'																																								      ");
		sql.append(" 					AND Y = YEAR(CURRENT DATE-1 DAYS)																																						      ");
		sql.append(" 				GROUP BY B.C_BRAND, B.C_CIG,B.CIG_MARKNAME,I_PROVINCE																																					      ");
		sql.append(" 			) K																																												      ");
		sql.append(" 		INNER JOIN cig_property_div P																																									      ");
		sql.append(" 			ON K.C_BRAND = P.C_BRAND																																									      ");
		sql.append(" 		INNER JOIN NICK_STMA_ORGANIZATION O																																									      ");
		sql.append(" 			ON O.ORG_CODE = P.CIG_PRODUCER																																									      ");
		sql.append(" 		GROUP BY P.C_CIG,P.CIG_MARKNAME																																										      ");
		sql.append(" 		WITH ROLLUP																																												      ");
		sql.append(" 			HAVING																																												      ");
		sql.append(" 		(																																													      ");
		sql.append(" 			(P.C_CIG IS NULL AND P.CIG_MARKNAME IS NULL)																																							      ");
		sql.append(" 			OR(P.C_CIG IS NOT NULL AND P.CIG_MARKNAME IS NOT NULL)																																						      ");
		sql.append(" 		)																																													      ");
		sql.append(" 																																															      ");
		sql.append(" 		) E																																													      ");
		sql.append(" 		ON K.C_CIG = E.C_CIG																																											      ");
		sql.append(" 		ORDER BY DECODE(PRODUCENUM,'-',-1,PRODUCENUM) DESC																																									      ");
		sql.append(" 		WITH UR																																													      ");

		log.info("卷烟品牌重点表格数据处理sql: " + sql.toString());
		return sql.toString();
	}
	
	/**
	 * 重点品牌表格查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<KeyPointTableVO> getTableInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<KeyPointTableVO> list = new ArrayList<KeyPointTableVO>();
		try {
			sql = getTableSql();
			list = dbbean.executeQuery(sql, KeyPointTableVO.class.getName());
		} catch (Exception e) {
			log.error("getTableInfo查询方法sql执行异常", e); 
		}finally{
			dbbean.close();
		}
		return list;
	}
}
