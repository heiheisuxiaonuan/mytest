package com.icss.tjfx.twohighpop.highprice.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.twohighpop.highprice .vo.TowHighTableVO;

public class HighPriceTableQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(HighPriceTableQuery.class);

	/**
	 * 获取查询高价烟表格数据的sql(高价烟模块)
	 * @return 
	 */
	public static String getSqlHighCig(){
		StringBuffer sql = new StringBuffer();


		sql.append("SELECT  ");
		sql.append("			ROW_NUMBER() OVER(ORDER BY PRODUCENUM DESC) AS SORT_NO ");
		sql.append("			,P.C_CIG AS xz_parameter 	");			 																					
		sql.append("			,NVL(CIG_MARKNAME, '合计') AS CIG_NAME    	");																		
		sql.append("			,CASE WHEN PRODUCENUM=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM		"); 											
		sql.append("			,CASE WHEN PRODUCENUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM_L  ");                                             
		sql.append("			,CASE WHEN PRODUCENUM=0 AND PRODUCENUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM_GROWTH   	");		
		sql.append("			,CASE WHEN PRODUCENUM=0 OR PRODUCENUM_L=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(PRODUCENUM_L,0,0,NULL,0,(PRODUCENUM - PRODUCENUM_L)*100.0000/PRODUCENUM_L), 2), 16, 2),'999990.99')) END AS PRODUCENUM_GROWTH_RATE ");   
		sql.append("			,CASE WHEN BS_SELLNUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM                          ");                        
		sql.append("			,CASE WHEN BS_SELLNUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_L		 					");				
		sql.append("			,CASE WHEN BS_SELLNUM=0 AND BS_SELLNUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_GROWTH	 ");		
		sql.append("			,CASE WHEN BS_SELLNUM=0 OR BS_SELLNUM_L=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM_L,0,0,NULL,0,(BS_SELLNUM - BS_SELLNUM_L)*100.0000/BS_SELLNUM_L), 2), 16, 2),'999990.99')) END AS BS_SELLNUM_GROWTH_RATE  "); 
		sql.append("			,CASE WHEN IB_TERMSTOCKNUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM	"); 								
		sql.append("			,CASE WHEN IB_TERMSTOCKNUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM_L	 ");								
		sql.append("			,CASE WHEN IB_TERMSTOCKNUM=0 AND IB_TERMSTOCKNUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM_GROWTH	 	");
		sql.append("			,CASE WHEN BS_SELLSUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM	");		 									
		sql.append("			,CASE WHEN BS_SELLSUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM_L*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_L	");	 									
		sql.append("			,CASE WHEN BS_SELLSUM=0 AND BS_SELLSUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_GROWTH 		");	
		sql.append("			,CASE WHEN BS_SELLSUM=0 OR BS_SELLSUM_L=0 THEN '-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLSUM_L,0,0,NULL,0,(BS_SELLSUM - BS_SELLSUM_L)*100.0000/BS_SELLSUM_L), 2), 16, 2),'999990.99')) END AS BS_SELLSUM_GROWTH_RATE ");		
		sql.append("			,CASE WHEN SINGLE_SELLSUM=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM*5, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM	"); 													
		sql.append("			,CASE WHEN SINGLE_SELLSUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L*5, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_L 	");											
		sql.append("			,CASE WHEN SINGLE_SELLSUM=0 AND SINGLE_SELLSUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*5, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_GROWTH	");	 	
		sql.append("			,CASE WHEN SINGLE_SELLSUM=0 OR SINGLE_SELLSUM_L=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(SINGLE_SELLSUM_L,0,0,NULL,0,(SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/SINGLE_SELLSUM_L), 2), 16, 2),'999990.99')) END AS SINGLE_SELLSUM_GROWTH_RATE ");
		sql.append("		FROM ");
		sql.append("			( ");
		sql.append("				SELECT  ");
		sql.append("		  			NVL(P.C_CIG, '00') AS C_CIG   ");           	  
		sql.append("		  			,NVL(P.CIG_MARKNAME,'合计')    AS   CIG_NAME    ");     
		sql.append("		  			,SUM(PRODUCENUM_Y_A) AS PRODUCENUM     ");           
		sql.append("		  			,SUM(PRODUCENUM_Y_AL) AS PRODUCENUM_L    ");            
		sql.append("		  			,SUM(TERMSTOCK1+TERM_STOCK1) AS IB_TERMSTOCKNUM ");   
		sql.append("		  			,SUM(TERMSTOCK1_l+TERM_STOCK1_L) AS IB_TERMSTOCKNUM_L  ");   
		sql.append("		  			,SUM(BS_SELLNUM) AS BS_SELLNUM	");						  
		sql.append("		  			,SUM(BS_SELLNUM_L) AS BS_SELLNUM_L	");					  
		sql.append("		  			,SUM(BS_SELLSUM) AS BS_SELLSUM 		");					  
		sql.append("		  			,SUM(BS_SELLSUM_L) AS BS_SELLSUM_L 	");					 
		sql.append("		  			,DECODE(SUM(BS_SELLNUM),0,0,NULL,0,SUM(BS_SELLSUM)*1.0000/SUM(BS_SELLNUM)) AS SINGLE_SELLSUM   ");
		sql.append("		  			,DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L)*1.0000/SUM(BS_SELLNUM_L)) AS SINGLE_SELLSUM_L  "); 
		sql.append("		  		FROM (  ");
		sql.append("		  			SELECT   ");
		sql.append("		  				C_BRAND  ");
		sql.append("		  				,SUM(PRINTNUM1-REJECTNUM1+REPEATNUM1-JUMPNUM1) AS PRODUCENUM_Y_A    ");
		sql.append("		  				,SUM(PRINTNUM1_P-REJECTNUM1_P+REPEATNUM1_P-JUMPNUM1_P) AS PRODUCENUM_Y_AL ");
		sql.append("		  				,0 AS TERM_STOCK1	");																
		sql.append("		  				,0 AS TERM_STOCK1_L		");															  
		sql.append("		  				,SUM(TERMSTOCK1) AS TERMSTOCK1 		");												 
		sql.append("		  				,SUM(TERMSTOCK1_P) AS TERMSTOCK1_L		");											  
		sql.append("		  				,0 AS BS_SELLNUM	");																  
		sql.append("		  				,0 AS BS_SELLNUM_L	");																  
		sql.append("		  				,0 AS BS_SELLSUM	");																  
		sql.append("		  				,0 AS BS_SELLSUM_L		");															  
		sql.append("		  				FROM  NICK_K_TJIN_Y_ALL K,NICK_TJYY_CIGARETTE SC   ");
		sql.append("		  				WHERE	1=1  ");
		sql.append("		  			and	 K.C_BRAND = sc.cig_codebar  ");
		sql.append("						  and ((k.d_year<='2017' AND IS_NRT='1' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_innx='1'))    ");
		sql.append("		  				AND Y = YEAR(CURRENT DATE-1 DAYS)   ");
		sql.append("							and  		IS_HIGH_PRICE_BRAND = '1'  	and CIG_IMPORTFLAG in ('0','3')	  ");
		sql.append("		  				GROUP BY C_BRAND  ");
		sql.append("		  		UNION ALL  ");
		sql.append("		  			SELECT  ");
		sql.append("		  				C_BRAND  ");
		sql.append("		  				,0 AS PRODUCENUM_Y_A     ");           
		sql.append("		  				,0 AS PRODUCENUM_Y_AL          ");     
		sql.append("		  				,SUM(TERM_STOCK1) AS TERM_STOCK1   ");    
		sql.append("		  				,SUM(TERM_STOCK1_P) AS TERM_STOCK1_L   ");  
		sql.append("		  				,0 AS TERMSTOCK1	");					  
		sql.append("		  				,0 AS TERMSTOCK1_L	");					  
		sql.append("		  				,SUM(PRINT_NUM1-RBACK_NUM1)  AS BS_SELLNUM	  ");
		sql.append("		  				,SUM(PRINT_NUM1_P-RBACK_NUM1_P) AS BS_SELLNUM_L	");	  
		sql.append("		  				,SUM(PRINT_SUM-RBACK_SUM) AS BS_SELLSUM    ");
		sql.append("		  				,SUM(PRINT_SUM_P-RBACK_SUM_P) AS BS_SELLSUM_L ");   
		sql.append("		  				FROM	NICK_K_TJBS_Y_ALL K,NICK_TJYY_CIGARETTE SC  ");
		sql.append("		  					WHERE	1=1  ");
		sql.append("						and  K.C_BRAND = sc.cig_codebar ");
		sql.append("						and ((k.d_year<='2017' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_bsnx='1'))   ");
		sql.append("		  				AND Y = YEAR(CURRENT DATE-1 DAYS)  ");
		sql.append("						and 	IS_HIGH_PRICE_BRAND = '1'  	and CIG_IMPORTFLAG in ('0','3')	 ");
		sql.append("		  				GROUP BY C_BRAND  ");
		sql.append("		  				) K  ");
		sql.append("		  				INNER JOIN cig_property_div P  ");
		sql.append("		  				ON K.C_BRAND = P.C_BRAND  ");
		sql.append("						INNER JOIN VIEW_CIG_PROPERTY S ");
		sql.append("					ON K.C_BRAND = S.C_BRAND 	 ");					
		sql.append("						WHERE 1=1 ");
		sql.append("		  				GROUP BY P.C_CIG,P.CIG_MARKNAME ");  
		sql.append("		  				WITH ROLLUP   ");
		sql.append("		  					HAVING   ");
		sql.append("		  				( 			 ");																		 
		sql.append("		  					(P.C_CIG IS NULL AND P.CIG_MARKNAME IS NULL) "); 														 
		sql.append("		  					OR(P.C_CIG IS NOT NULL AND P.CIG_MARKNAME IS NOT NULL) 	 ");						 
		sql.append("		 AND 		 ");																						
		sql.append("		 (		 ");																									 
		sql.append("		 SUM(PRODUCENUM_Y_A)<>0 	 ");																		
		sql.append("		 OR SUM(PRODUCENUM_Y_AL)<>0 	 ");																		
		sql.append("		 OR SUM(TERMSTOCK1+TERM_STOCK1)<>0	 ");																	 
		sql.append("		 OR SUM(TERMSTOCK1_l+TERM_STOCK1_L)<>0	 ");															 
		sql.append("		 OR SUM(BS_SELLNUM)<>0 						 ");																	
		sql.append("		 OR SUM(BS_SELLNUM_L)<>0	 ");																			 
		sql.append("		 OR SUM(BS_SELLSUM)<>0 		 ");																			
		sql.append("		 OR SUM(BS_SELLSUM_L)<>0	 ");																		 
		sql.append("		 )  ");
		sql.append("		  				)   ");

		sql.append("			) K  ");
		sql.append("		LEFT JOIN (SELECT DISTINCT C_CIG, CIG_MARKNAME FROM cig_property_div) P  ");
		sql.append("			ON K.C_CIG = P.C_CIG  ");
		sql.append("		ORDER BY SORT_NO  ");
		sql.append("		WITH UR  ");                                
		                                                                                                                                         
		
		log.info("查询高价烟初始品牌表格信息Sql为："+sql);
		return sql.toString();
	}
	
	
	public List<TowHighTableVO> getHighEndTable(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<TowHighTableVO> highCigList = new ArrayList<TowHighTableVO>();
		try {
			sql = getSqlHighCig();
			highCigList = dbbean.executeQuery(sql, TowHighTableVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询高价烟初始品牌表格信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询高价烟初始品牌表格信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return highCigList;
	}
	
	public static String getSqlHighCigSpec(String code){
		StringBuffer sql = new StringBuffer();
		sql.append(" 	SELECT																																				");
		sql.append(" 	NVL(CIG_TRADEMARK, '合计') AS CIG_NAME   			 																												");
		sql.append(" 	,CASE WHEN PRODUCENUM1=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM1*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM	 																				");
		sql.append(" 	,CASE WHEN PRODUCENUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM_L																				");
		sql.append(" 	,CASE WHEN PRODUCENUM1=0 AND PRODUCENUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM_GROWTH  	 														");
		sql.append(" 	,CASE WHEN PRODUCENUM1=0 OR PRODUCENUM_L=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(PRODUCENUM_L,0,0,NULL,0,(PRODUCENUM1 - PRODUCENUM_L)*100.0000/PRODUCENUM_L), 2), 16, 2),'999990.99')) END AS PRODUCENUM_GROWTH_RATE     						");
		sql.append(" 	,CASE WHEN BS_SELLNUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM																					");
		sql.append(" 	,CASE WHEN BS_SELLNUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_L		 																		");
		sql.append(" 	,CASE WHEN BS_SELLNUM=0 AND BS_SELLNUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_GROWTH	 														");
		sql.append(" 	,CASE WHEN BS_SELLNUM=0 OR BS_SELLNUM_L=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM_L,0,0,NULL,0,(BS_SELLNUM - BS_SELLNUM_L)*100.0000/BS_SELLNUM_L), 2), 16, 2),'999990.99')) END AS BS_SELLNUM_GROWTH_RATE   							");
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM	 																		");
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM_L		 																");
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM=0 AND IB_TERMSTOCKNUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM_GROWTH	 											");
		sql.append(" 	,CASE WHEN BS_SELLSUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM	 																				");
		sql.append(" 	,CASE WHEN BS_SELLSUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM_L*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_L	 																			");
		sql.append(" 	,CASE WHEN BS_SELLSUM=0 AND BS_SELLSUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_GROWTH	 														");
		sql.append(" 	,CASE WHEN BS_SELLSUM=0 OR BS_SELLSUM_L=0 THEN '-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLSUM_L,0,0,NULL,0,(BS_SELLSUM - BS_SELLSUM_L)*100.0000/BS_SELLSUM_L), 2), 16, 2),'999990.99')) END AS BS_SELLSUM_GROWTH_RATE	 						");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM*5, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM		 																			");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L*5, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_L	 																			");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM=0 AND SINGLE_SELLSUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*5, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_GROWTH	 														");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM=0 OR SINGLE_SELLSUM_L=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(SINGLE_SELLSUM_L,0,0,NULL,0,(SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/SINGLE_SELLSUM_L), 2), 16, 2),'999990.99')) END AS SINGLE_SELLSUM_GROWTH_RATE 				");
		sql.append(" 	,NVL(DECODE(D.CIG_PRODUCER,'10000001','国家局',O.PRODUCE_SHORTNAME), '-') AS PRODUCER_NAME																									");
		sql.append(" 	,case when substr(NVL(CHAR(TRANSFERPRICE), '-'),1,1)='.' then '0'||CHAR(TRANSFERPRICE) else NVL(CHAR(TRANSFERPRICE), '-') end AS TRANSFERPRICE 			");
		sql.append(" 	,case when substr(NVL(CHAR(WHOLESALEPRICE), '-'),1,1)='.' then '0'||CHAR(WHOLESALEPRICE) else NVL(CHAR(WHOLESALEPRICE), '-') end AS WHOLESALEPRICE 		");
		sql.append(" 	,case when substr(NVL(CHAR(SUGGESTEDRETAILPRICE), '-'),1,1)='.' then '0'||CHAR(SUGGESTEDRETAILPRICE) else NVL(CHAR(SUGGESTEDRETAILPRICE), '-') end AS SUGGESTEDRETAILPRICE		");
		sql.append(" 	FROM 																																				");
		sql.append(" 	( SELECT  																																			");
		sql.append(" 	NVL(P.C_BRAND, '00') AS C_BRAND              	  																														");
		sql.append(" 	,NVL(P.CIG_NAME,'合计')    AS   CIG_NAME         																														");
		sql.append(" 	,SUM(PRODUCENUM_Y_A) AS PRODUCENUM1                																														");
		sql.append(" 	,SUM(PRODUCENUM_Y_AL) AS PRODUCENUM_L                																														");
		sql.append(" 	,SUM(TERMSTOCK1+TERM_STOCK1) AS IB_TERMSTOCKNUM    																														");
		sql.append(" 	,SUM(TERMSTOCK1_l+TERM_STOCK1_L) AS IB_TERMSTOCKNUM_L     																													");
		sql.append(" 	,SUM(BS_SELLNUM) AS BS_SELLNUM							  																										");
		sql.append(" 	,SUM(BS_SELLNUM_L) AS BS_SELLNUM_L						  																										");
		sql.append(" 	,SUM(BS_SELLSUM) AS BS_SELLSUM 							  																										");
		sql.append(" 	,SUM(BS_SELLSUM_L) AS BS_SELLSUM_L 						 																										");
		sql.append(" 	,DECODE(SUM(BS_SELLNUM),0,0,NULL,0,SUM(BS_SELLSUM)*1.0000/SUM(BS_SELLNUM)) AS SINGLE_SELLSUM   																									");
		sql.append(" 	,DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L)*1.0000/SUM(BS_SELLNUM_L)) AS SINGLE_SELLSUM_L   																								");
		sql.append(" 	FROM (  																																			");
		sql.append(" 	SELECT   																																			");
		sql.append(" 	K.C_BRAND  																																			");
		sql.append(" 	,SUM(PRINTNUM1-REJECTNUM1+REPEATNUM1-JUMPNUM1) AS PRODUCENUM_Y_A    																												");
		sql.append(" 	,SUM(PRINTNUM1_P-REJECTNUM1_P+REPEATNUM1_P-JUMPNUM1_P) AS PRODUCENUM_Y_AL 																											");
		sql.append(" 	,0 AS TERM_STOCK1																																		");
		sql.append(" 	,0 AS TERM_STOCK1_L																	  																	");
		sql.append(" 	,SUM(TERMSTOCK1) AS TERMSTOCK1 														 																			");
		sql.append(" 	,SUM(TERMSTOCK1_P) AS TERMSTOCK1_L													  																			");
		sql.append(" 	,0 AS BS_SELLNUM																	  																	");
		sql.append(" 	,0 AS BS_SELLNUM_L																	  																	");
		sql.append(" 	,0 AS BS_SELLSUM																	  																	");
		sql.append(" 	,0 AS BS_SELLSUM_L																	  																	");
		sql.append(" 	FROM NICK_K_TJIN_Y_ALL K   																																	");
		sql.append(" 	INNER JOIN cig_property_div P  																																	");
		sql.append(" 	ON K.C_BRAND = P.C_BRAND  																																	");
		sql.append(" 	INNER JOIN nick_tjyy_cigarette S 			 																														");
		sql.append(" 	ON K.C_BRAND = S.cig_codebar																															");
		sql.append(" 	WHERE 1=1																																			");
		sql.append(" 	AND  ((k.d_year<='2017' AND IS_NRT='1' AND s.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND s.is_innx='1'))							");
		sql.append(" 	and s.IS_HIGH_PRICE_BRAND = '1'   and s.CIG_IMPORTFLAG in ('0','3')																					");
		sql.append(" 	AND K.C_CIG='").append(code).append("'");
		sql.append(" 	AND Y = YEAR(CURRENT DATE-1 DAYS)   																																");
		sql.append(" 	GROUP BY K.C_BRAND  																																		");
		sql.append(" 	UNION ALL  																																			");
		sql.append(" 	SELECT  																																			");
		sql.append(" 	K.C_BRAND  																																			");
		sql.append(" 	,0 AS PRODUCENUM_Y_A                																																");
		sql.append(" 	,0 AS PRODUCENUM_Y_AL               																																");
		sql.append(" 	,SUM(TERM_STOCK1) AS TERM_STOCK1       																																");
		sql.append(" 	,SUM(TERM_STOCK1_P) AS TERM_STOCK1_L     																															");
		sql.append(" 	,0 AS TERMSTOCK1						  																												");
		sql.append(" 	,0 AS TERMSTOCK1_L						  																												");
		sql.append(" 	,SUM(PRINT_NUM1-RBACK_NUM1)  AS BS_SELLNUM	  																														");
		sql.append(" 	,SUM(PRINT_NUM1_P-RBACK_NUM1_P) AS BS_SELLNUM_L		  																													");
		sql.append(" 	,SUM(PRINT_SUM-RBACK_SUM) AS BS_SELLSUM    																															");
		sql.append(" 	,SUM(PRINT_SUM_P-RBACK_SUM_P) AS BS_SELLSUM_L    																														");
		sql.append(" 	FROM	NICK_K_TJBS_Y_ALL K 																																	");
		sql.append(" 	INNER JOIN cig_property_div P  																																	");
		sql.append(" 	ON K.C_BRAND = P.C_BRAND  																																	");
		sql.append(" 	INNER JOIN   nick_tjyy_cigarette S																																");
		sql.append(" 	ON K.C_BRAND = S.Cig_codebar																														");
		sql.append(" 	WHERE 1=1																																			");
		sql.append(" 	AND  ((k.d_year<='2017' AND s.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND s.is_bsnx='1'))									");
		sql.append(" 	and S.IS_HIGH_PRICE_BRAND =1 and s.CIG_IMPORTFLAG in ('0','3')																				");
		sql.append(" 	AND K.C_CIG='").append(code).append("'");
		sql.append(" 	AND Y = YEAR(CURRENT DATE-1 DAYS)   																																");
		sql.append(" 	GROUP BY K.C_BRAND  																																		");
		sql.append(" 	) K  																																				");
		sql.append(" 	INNER JOIN cig_property_div P  																																	");
		sql.append(" 	ON K.C_BRAND = P.C_BRAND  																																	");
		sql.append(" 	INNER JOIN   nick_tjyy_cigarette S 																																");
		sql.append(" 	ON  K.C_BRAND = S.cig_codebar																													");
		sql.append(" 	WHERE 1=1																																			");
		sql.append(" 	AND S.IS_HIGH_PRICE_BRAND='1'																								");
		sql.append(" 	GROUP BY P.C_BRAND,P.CIG_NAME  																																	");
		sql.append(" 	WITH ROLLUP  																																			");
		sql.append(" 	HAVING  																																			");
		sql.append(" 	( 																					 															");
		sql.append(" 	(P.C_BRAND IS NULL AND P.CIG_NAME IS NULL) 														 																	");
		sql.append(" 	OR(P.C_BRAND IS NOT NULL AND P.CIG_NAME IS NOT NULL) 							 																							");
		sql.append(" 	AND 																																				");
		sql.append(" 	(																											 									");
		sql.append(" 	SUM(PRODUCENUM_Y_A)<>0 																																		");
		sql.append(" 	OR SUM(PRODUCENUM_Y_AL)<>0 																																	");
		sql.append(" 	OR SUM(TERMSTOCK1+TERM_STOCK1)<>0																		 														");
		sql.append(" 	OR SUM(TERMSTOCK1_l+TERM_STOCK1_L)<>0																 																");
		sql.append(" 	OR SUM(BS_SELLNUM)<>0 																																		");
		sql.append(" 	OR SUM(BS_SELLNUM_L)<>0																				 														");
		sql.append(" 	OR SUM(BS_SELLSUM)<>0 																																		");
		sql.append(" 	OR SUM(BS_SELLSUM_L)<>0																			 															");
		sql.append(" 	) 																																				");
		sql.append(" 	) 																																				");
		sql.append(" 	) K 																																				");
		sql.append(" 	LEFT JOIN NICK_STMA_CIGARETTE d 																																");
		sql.append(" 	ON K.C_BRAND = d.CIG_CODEBAR 																																	");
		sql.append(" 	LEFT JOIN(SELECT DISTINCT PRODUCE_CODE, PRODUCE_SHORTNAME FROM DIM_PRODUCE_ORG) O 																										");
		sql.append(" 	ON D.CIG_PRODUCER = O.PRODUCE_CODE 																																");
		sql.append(" 	LEFT JOIN NICK_MA_T_CLASS_CODE N	 																															");
		sql.append(" 	ON N.CODE = d.CIG_PRICETYPE 																																	");
		sql.append(" 	LEFT JOIN (SELECT DISTINCT BAR, TRANSFERPRICE, WHOLESALEPRICE, SUGGESTEDRETAILPRICE FROM PRICELIST) P	 																							");
		sql.append(" 	ON K.C_BRAND = P.BAR 																																		");
		sql.append(" 	ORDER BY DECODE(CIG_NAME,'合计',0,1) ASC, PRODUCENUM1 DESC 																													");
		sql.append(" 	WITH UR 																																			");

		                                                     
		log.info("查询高价烟规格下钻表格信息sql："+sql);
		return sql.toString();
	}
	public List<TowHighTableVO> getHighEndTableSpec(String dbName,String code){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<TowHighTableVO> highCigSpecList = new ArrayList<TowHighTableVO>();
		try {
			sql = getSqlHighCigSpec(code);
			highCigSpecList = dbbean.executeQuery(sql, TowHighTableVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询高价烟规格下钻表格信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询高价烟规格下钻表格信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return highCigSpecList;
	}
	
	public static String getSqlHighCigRegion(String code){
		StringBuffer sql = new StringBuffer();
		sql.append(" 	SELECT																																		");
		sql.append(" 	ROW_NUMBER() OVER(ORDER BY PRODUCENUM DESC) AS SORT_NO 																												");
		sql.append(" 	,PROV_ORG_NAME as CIG_NAME 																															");
		sql.append(" 	,CASE WHEN PRODUCENUM=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM																			");
		sql.append(" 	,CASE WHEN PRODUCENUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM_L																		");
		sql.append(" 	,CASE WHEN PRODUCENUM=0 AND PRODUCENUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM_GROWTH													");
		sql.append(" 	,CASE WHEN PRODUCENUM=0 OR PRODUCENUM_L=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(PRODUCENUM_L,0,0,NULL,0,(PRODUCENUM - PRODUCENUM_L)*100.0000/PRODUCENUM_L), 2), 16, 2),'999990.99')) END AS PRODUCENUM_GROWTH_RATE					");
		sql.append(" 	,CASE WHEN BS_SELLNUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM																			");
		sql.append(" 	,CASE WHEN BS_SELLNUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_L																		");
		sql.append(" 	,CASE WHEN BS_SELLNUM=0 AND BS_SELLNUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_GROWTH													");
		sql.append(" 	,CASE WHEN BS_SELLNUM=0 OR BS_SELLNUM_L=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM_L,0,0,NULL,0,(BS_SELLNUM - BS_SELLNUM_L)*100.0000/BS_SELLNUM_L), 2), 16, 2),'9999990.99')) END AS BS_SELLNUM_GROWTH_RATE					");
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM																	");
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(IB_TERMSTOCKNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM_L																");
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM=0 AND IB_TERMSTOCKNUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS IB_TERMSTOCKNUM_GROWTH										");
		sql.append(" 	,CASE WHEN BS_SELLSUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM																			");
		sql.append(" 	,CASE WHEN BS_SELLSUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM_L*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_L																		");
		sql.append(" 	,CASE WHEN BS_SELLSUM=0 AND BS_SELLSUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_GROWTH													");
		sql.append(" 	,CASE WHEN BS_SELLSUM=0 OR BS_SELLSUM_L=0 THEN '-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLSUM_L,0,0,NULL,0,(BS_SELLSUM - BS_SELLSUM_L)*100.0000/BS_SELLSUM_L), 2), 16, 2),'9999990.99')) END AS BS_SELLSUM_GROWTH_RATE					");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM*5, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM																			");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L*5, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_L																		");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM=0 AND SINGLE_SELLSUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*5, 2), 16, 2),'999990.99') END AS SINGLE_SELLSUM_GROWTH													");
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM=0 OR SINGLE_SELLSUM_L=0 THEN'-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(SINGLE_SELLSUM_L,0,0,NULL,0,(SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/SINGLE_SELLSUM_L), 2), 16, 2),'999990.99')) END AS SINGLE_SELLSUM_GROWTH_RATE		");
		sql.append(" 	FROM 																																		");
		sql.append(" 	( 																																		");
		sql.append(" 	SELECT  																																	");
		sql.append(" 	NVL(PROV_ORG_NAME, '合计') AS PROV_ORG_NAME                        																										");
		sql.append(" 	,SUM(PRODUCENUM_Y_A) AS PRODUCENUM               																												");
		sql.append(" 	,SUM(PRODUCENUM_Y_AL) AS PRODUCENUM_L                																												");
		sql.append(" 	,SUM(TERMSTOCK1+TERM_STOCK1) AS IB_TERMSTOCKNUM    																												");
		sql.append(" 	,SUM(TERMSTOCK1_l+TERM_STOCK1_L) AS IB_TERMSTOCKNUM_L     																											");
		sql.append(" 	,SUM(BS_SELLNUM) AS BS_SELLNUM							  																								");
		sql.append(" 	,SUM(BS_SELLNUM_L) AS BS_SELLNUM_L						  																								");
		sql.append(" 	,SUM(BS_SELLSUM) AS BS_SELLSUM 							  																								");
		sql.append(" 	,SUM(BS_SELLSUM_L) AS BS_SELLSUM_L 						 																								");
		sql.append(" 	,DECODE(SUM(BS_SELLNUM),0,0,NULL,0,SUM(BS_SELLSUM)*1.0000/SUM(BS_SELLNUM)) AS SINGLE_SELLSUM   																							");
		sql.append(" 	,DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L)*1.0000/SUM(BS_SELLNUM_L)) AS SINGLE_SELLSUM_L   																						");
		sql.append(" 	FROM (  																																	");
		sql.append(" 	SELECT   																																	");
		sql.append(" 	NVL(D.PROV_ORG_NAME, '00') AS PROV_ORG_NAME                  																											");
		sql.append(" 	,SUM(PRINTNUM1-REJECTNUM1+REPEATNUM1-JUMPNUM1) AS PRODUCENUM_Y_A    																										");
		sql.append(" 	,SUM(PRINTNUM1_P-REJECTNUM1_P+REPEATNUM1_P-JUMPNUM1_P) AS PRODUCENUM_Y_AL 																									");
		sql.append(" 	,0 AS TERM_STOCK1																																");
		sql.append(" 	,0 AS TERM_STOCK1_L																	  															");
		sql.append(" 	,SUM(TERMSTOCK1) AS TERMSTOCK1 														 																	");
		sql.append(" 	,SUM(TERMSTOCK1_P) AS TERMSTOCK1_L													  																	");
		sql.append(" 	,0 AS BS_SELLNUM																	  															");
		sql.append(" 	,0 AS BS_SELLNUM_L																	  															");
		sql.append(" 	,0 AS BS_SELLSUM																	  															");
		sql.append(" 	,0 AS BS_SELLSUM_L																	  															");
		sql.append(" 	FROM NICK_K_TJIN_Y_ALL K   																															");
		sql.append(" 	INNER JOIN cig_property_div P  																															");
		sql.append(" 	ON K.C_BRAND = P.C_BRAND  																															");
		sql.append(" 	INNER JOIN MA_T_PROV_ORG D 																															");
		sql.append(" 	ON K.AREA_CODE=d.prov_code 																															");
		sql.append(" 	INNER JOIN  nick_tjyy_cigarette sc																												");
		sql.append(" 	ON    K.C_BRAND = sc.cig_codebar																											");
		sql.append(" 	WHERE 1=1																																	");
		sql.append(" 	AND SC.IS_HIGH_PRICE_BRAND ='1'																														");
		sql.append(" 	AND  ((k.d_year<='2017' AND IS_NRT='1' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_innx='1'))																					");
		sql.append(" 	AND K.C_CIG='").append(code).append("'");
		sql.append(" 	AND Y = YEAR(CURRENT DATE-1 DAYS)   																														");
		sql.append(" 	GROUP BY D.PROV_ORG_NAME  																															");
		sql.append(" 	UNION ALL																																	");
		sql.append(" 	SELECT																																		");
		sql.append(" 	NVL(D.PROV_ORG_NAME, '00') AS PROV_ORG_NAME                  																											");
		sql.append(" 	,0 AS PRODUCENUM_Y_A                																														");
		sql.append(" 	,0 AS PRODUCENUM_Y_AL               																														");
		sql.append(" 	,SUM(TERM_STOCK1) AS TERM_STOCK1       																														");
		sql.append(" 	,SUM(TERM_STOCK1_P) AS TERM_STOCK1_L     																													");
		sql.append(" 	,0 AS TERMSTOCK1						  																										");
		sql.append(" 	,0 AS TERMSTOCK1_L						  																										");
		sql.append(" 	,SUM(PRINT_NUM1-RBACK_NUM1)  AS BS_SELLNUM	  																												");
		sql.append(" 	,SUM(PRINT_NUM1_P-RBACK_NUM1_P) AS BS_SELLNUM_L		  																											");
		sql.append(" 	,SUM(PRINT_SUM-RBACK_SUM) AS BS_SELLSUM    																													");
		sql.append(" 	,SUM(PRINT_SUM_P-RBACK_SUM_P) AS BS_SELLSUM_L    																												");
		sql.append(" 	FROM	NICK_K_TJBS_Y_ALL K 																															");
		sql.append(" 	INNER JOIN cig_property_div P  																															");
		sql.append(" 	ON K.C_BRAND = P.C_BRAND  																															");
		sql.append(" 	INNER JOIN MA_T_PROV_ORG D 																															");
		sql.append(" 	ON K.I_PROVINCE=d.prov_code 																															");
		sql.append(" 	INNER JOIN nick_tjyy_cigarette sc																														");  
		sql.append(" 	ON K.C_BRAND = sc.cig_codebar																												");
		sql.append(" 	WHERE 1=1																																	");
		sql.append(" 	AND  SC.IS_HIGH_PRICE_BRAND ='1'																										");
		sql.append(" 	and ((k.d_year<='2017' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_bsnx='1'))																						");
		sql.append(" 	AND Y = YEAR(CURRENT DATE-1 DAYS)   																														");
		sql.append(" 	GROUP BY PROV_ORG_NAME  																															");
		sql.append(" 	) K  																																		");
		sql.append(" 	GROUP BY PROV_ORG_NAME  																															");
		sql.append(" 	WITH ROLLUP  																																	");
		sql.append(" 	HAVING																																		");
		sql.append(" 	(																											 							");
		sql.append(" 	SUM(PRODUCENUM_Y_A)<>0 																																");
		sql.append(" 	OR SUM(PRODUCENUM_Y_AL)<>0 																															");
		sql.append(" 	OR SUM(TERMSTOCK1+TERM_STOCK1)<>0																		 												");
		sql.append(" 	OR SUM(TERMSTOCK1_l+TERM_STOCK1_L)<>0																 														");
		sql.append(" 	OR SUM(BS_SELLNUM)<>0 																																");
		sql.append(" 	OR SUM(BS_SELLNUM_L)<>0																				 												");
		sql.append(" 	OR SUM(BS_SELLSUM)<>0 																																");
		sql.append(" 	OR SUM(BS_SELLSUM_L)<>0																			 													");
		sql.append(" 	) 																																		");
		sql.append(" 																																			");
		sql.append(" 	) K 																																		");
		sql.append(" 	ORDER BY DECODE(PROV_ORG_NAME,'合计',0,1) ASC 																													");
		sql.append(" 	WITH UR 																																	");
																																						

		log.info("查询高价烟地区下钻表格信息sql："+sql);
		return sql.toString();
	}
	public List<TowHighTableVO> getHighEndTableRegion(String dbName,String code){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<TowHighTableVO> highCigRegionList = new ArrayList<TowHighTableVO>();
		try {
			sql = getSqlHighCigRegion(code);
			highCigRegionList = dbbean.executeQuery(sql, TowHighTableVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询高价烟地区下钻表格信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询高价烟地区下钻表格信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return highCigRegionList;
	}


	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<TowHighTableVO> highCigList=null;
		Gson gson = new Gson();
		String type=request.getParameter("type");								//获取下钻的类型
		String code=request.getParameter("code");								//获取编码
		
		if (type.equals("gg")) {
			log.info("--------------------规格查询-------------------");
			highCigList = getHighEndTableSpec("dw",code);		
		}else if (type.equals("dq")) {
			log.info("--------------------地市查询-------------------");
			highCigList = getHighEndTableRegion("dw",code);
		}else {
			log.info("--------------------常规查询-------------------");
			highCigList = getHighEndTable("dw");
		}
		
		String s = gson.toJson(highCigList);
		log.info("高价烟品牌业务查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}

}
