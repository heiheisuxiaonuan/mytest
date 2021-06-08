package com.icss.tjfx.popcatalog.productlayout.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.popcatalog.productlayout.vo.ProductLayTableVO;

public class ProductLayOutTableQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(ProductLayOutTableQuery.class);
	
	/**
	 * 获取查询品牌目录产品布局表格数据的sql
	 * @return 
	 */
	public static String getSqlProductLay(){
		StringBuffer sql = new StringBuffer();                                                
		sql.append("SELECT                          ");
		sql.append("	NAME                          ");
		sql.append("	,COUNT_brand                  ");
		sql.append("	,C_BRAND_PER                  ");
		sql.append("	,PRODUCENUM                   ");
		sql.append("	,PRODUCENUM_PER               ");
		sql.append("FROM(                           ");
		sql.append("SELECT                          ");
		sql.append("		'11' AS CODE                  ");
		sql.append("		,'高价位' AS NAME              ");
		sql.append("		,A.C_BRAND as COUNT_brand	           ");
		sql.append("		,DECIMAL(ROUND(A.C_BRAND*1.0000/S.C_BRAND*100,2), 16, 2) AS C_BRAND_PER                                                                 ");
		sql.append("		,TO_CHAR(DECIMAL(ROUND(A.PRODUCENUM*1.0000/50000, 2), 16, 2),'999990.99') AS PRODUCENUM	                                                ");
		sql.append("		,DECIMAL(ROUND(DECODE(S.PRODUCENUM,0,0,NULL,0,A.PRODUCENUM*1.0000/S.PRODUCENUM)*100, 2), 16, 2) AS PRODUCENUM_PER                       ");
		sql.append("		FROM                                           ");
		sql.append("		(SELECT COUNT(K.C_BRAND) AS C_BRAND            ");
		sql.append("				,SUM(PRODUCENUM) AS PRODUCENUM             ");
		sql.append(" FROM(                                             ");
		sql.append("SELECT                                             ");
		sql.append("				DISTINCT(C_BRAND) AS C_BRAND               ");
		sql.append("				,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM(JUMPNUM1) AS PRODUCENUM                                         ");
		sql.append("				FROM NICK_K_TJIN_Y_ALL               ");
		sql.append("				WHERE	1=1                                ");
		sql.append("				AND Y = YEAR(CURRENT DATE -1 DAYS)               ");
		sql.append("				AND C_CLASS<>'06'                        ");
		sql.append("				AND IS_NRT = '1'                         ");
		sql.append("				GROUP BY C_BRAND                         ");
		sql.append("				HAVING( SUM(PRINTNUM1)<>0            ");
		sql.append("						OR SUM(REJECTNUM1)<>0            ");
		sql.append("						OR SUM(REPEATNUM1)<>0            ");
		sql.append("						OR SUM(JUMPNUM1)<>0)             ");
		sql.append("				)K,VIEW_CIG_PROPERTY P                   ");
		sql.append("				WHERE 1=1                                ");
		sql.append("			AND K.C_BRAND = P.C_BRAND		               ");
		sql.append("			                                           ");
		sql.append("			AND P.IS_HIGH_PRICE_BRAND =1 ) A,          ");
		sql.append("		(SELECT                                      ");
		sql.append("				COUNT(DISTINCT(C_BRAND)) AS C_BRAND      ");
		sql.append("				,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM(JUMPNUM1) AS PRODUCENUM                                         ");
		sql.append("				FROM NICK_K_TJIN_Y_ALL                 ");
		sql.append("				WHERE	1=1                                  ");
		sql.append("				AND Y = YEAR(CURRENT DATE -1 DAYS)                 ");
		sql.append("				AND C_CLASS<>'06'                          ");
		sql.append("				AND IS_NRT = '1'                           ");
		sql.append("				HAVING( SUM(PRINTNUM1)<>0              ");
		sql.append("						OR SUM(REJECTNUM1)<>0              ");
		sql.append("						OR SUM(REPEATNUM1)<>0              ");
		sql.append("						OR SUM(JUMPNUM1)<>0)) S            ");
		sql.append("	UNION ALL                                        ");
		sql.append("SELECT                                             ");
		sql.append("		'22' AS CODE                                      ");
		sql.append("		,'高    端' AS NAME		 											         ");
		sql.append("		,A.C_BRAND as COUNT_brand	                     ");
		sql.append("		,DECIMAL(ROUND(A.C_BRAND*1.0000/S.C_BRAND*100,2), 16, 2) AS C_BRAND_PER                                                                 ");
		sql.append("		,TO_CHAR(DECIMAL(ROUND(A.PRODUCENUM*1.0000/50000, 2), 16, 2),'999990.99') AS PRODUCENUM	                                                ");
		sql.append("		,DECIMAL(ROUND(DECODE(S.PRODUCENUM,0,0,NULL,0,A.PRODUCENUM*1.0000/S.PRODUCENUM)*100, 2), 16, 2) AS PRODUCENUM_PER                       ");
		sql.append("		from                                                 ");
		sql.append("		(SELECT COUNT(K.C_BRAND) AS C_BRAND                  ");
		sql.append("				,SUM(PRODUCENUM) AS PRODUCENUM                   ");
		sql.append(" FROM(                                                   ");
		sql.append("SELECT                                                   ");
		sql.append("				DISTINCT(C_BRAND) AS C_BRAND                     ");
		sql.append("				,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM(JUMPNUM1) AS PRODUCENUM                                         ");
		sql.append("				FROM NICK_K_TJIN_Y_ALL                    ");
		sql.append("				WHERE	1 = 1                                   ");
		sql.append("				AND Y = YEAR(CURRENT DATE -1 DAYS)                    ");
		sql.append("				AND C_CLASS<>'06'                             ");
		sql.append("				AND IS_NRT = '1'                              ");
		sql.append("				GROUP BY C_BRAND                              ");
		sql.append("				HAVING( SUM(PRINTNUM1)<>0                 ");
		sql.append("						OR SUM(REJECTNUM1)<>0                 ");
		sql.append("						OR SUM(REPEATNUM1)<>0                 ");
		sql.append("						OR SUM(JUMPNUM1)<>0)                  ");
		sql.append("				)K,cig_property_div P                   ");
		sql.append("				WHERE 1=1                                     ");
		sql.append("				                                              ");
		sql.append("			AND K.C_BRAND = P.C_BRAND                       ");
		sql.append("			AND P.IS_HIGH_TIER_BRAND =1) A,                 ");
		sql.append("		(SELECT                                           ");
		sql.append("				COUNT(DISTINCT(C_BRAND)) AS C_BRAND           ");
		sql.append("				,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM(JUMPNUM1) AS PRODUCENUM                                         ");
		sql.append("				FROM NICK_K_TJIN_Y_ALL              ");
		sql.append("				WHERE	1 = 1                             ");
		sql.append("				AND Y = YEAR(CURRENT DATE -1 DAYS)              ");
		sql.append("				AND C_CLASS<>'06'                       ");
		sql.append("				AND IS_NRT = '1'                        ");
		sql.append("				HAVING( SUM(PRINTNUM1)<>0           ");
		sql.append("						OR SUM(REJECTNUM1)<>0           ");
		sql.append("						OR SUM(REPEATNUM1)<>0           ");
		sql.append("						OR SUM(JUMPNUM1)<>0)) S         ");
		sql.append("UNION ALL                                       ");
		sql.append("SELECT                                          ");
		sql.append("		CODE AS SORT_NO                             ");
		sql.append("		,A.NAME AS NAME                             ");
		sql.append("		,A.C_BRAND as C_BRAND	                      ");
		sql.append("		,DECIMAL(ROUND(SUM(A.C_BRAND)*1.0000/SUM(S.C_BRAND)*100,2), 16, 2) AS C_BRAND_PER                                                       ");
		sql.append("		,TO_CHAR(DECIMAL(ROUND(SUM(A.PRODUCENUM)*1.0000/50000, 2), 16, 2),'999990.99') AS PRODUCENUM	                                          ");
		sql.append("		,DECIMAL(ROUND(DECODE(SUM(S.PRODUCENUM),0,0,NULL,0,SUM(A.PRODUCENUM)*1.0000/SUM(S.PRODUCENUM))*100, 2), 16, 2) AS PRODUCENUM_PER        ");
		sql.append("		FROM(                                    ");
		sql.append("		SELECT	O.CLASS_NAME AS NAME                         ");
		sql.append("				,CODE                                ");
		sql.append("				,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM(JUMPNUM1) AS PRODUCENUM                                         ");
		sql.append("				,COUNT(DISTINCT(K.C_BRAND)) AS C_BRAND                           ");
		sql.append("				FROM NICK_K_TJIN_Y_ALL K                                     ");
		sql.append("				INNER JOIN DW_T_CLASS_CODE O ON K.C_CLASS=O.CODE                 ");
		sql.append("				INNER JOIN cig_property_div P ON K.C_BRAND=P.C_BRAND       ");
		sql.append("				WHERE 1=1                                                        ");
		sql.append("				AND C_CLASS<>'06'                      ");
		sql.append("				AND IS_NRT = '1'                       ");
		sql.append("				AND Y = YEAR(CURRENT DATE -1 DAYS)             ");
		sql.append("				GROUP BY O.CLASS_NAME, CODE                  ");
		sql.append("				HAVING( SUM(PRINTNUM1)<>0          ");
		sql.append("						OR SUM(REJECTNUM1)<>0          ");
		sql.append("						OR SUM(REPEATNUM1)<>0          ");
		sql.append("						OR SUM(JUMPNUM1)<>0)           ");
		sql.append("		) A,                                       ");
		sql.append("		(SELECT                                    ");
		sql.append("				COUNT(DISTINCT(C_BRAND)) AS C_BRAND    ");
		sql.append("				,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM(JUMPNUM1) AS PRODUCENUM                                         ");
		sql.append("				FROM NICK_K_TJIN_Y_ALL K               ");
		sql.append("				WHERE 1=1                                  ");
		sql.append("				AND C_CLASS<>'06'                          ");
		sql.append("				AND IS_NRT = '1'                           ");
		sql.append("				AND Y = YEAR(CURRENT DATE -1 DAYS)                 ");
		sql.append("				HAVING( SUM(PRINTNUM1)<>0              ");
		sql.append("						OR SUM(REJECTNUM1)<>0              ");
		sql.append("						OR SUM(REPEATNUM1)<>0              ");
		sql.append("						OR SUM(JUMPNUM1)<>0)               ");
		sql.append("		) S                                            ");
		sql.append("		GROUP BY NAME,A.C_BRAND,CODE                   ");
		sql.append(")                                                  ");
		sql.append("ORDER BY DECODE(LEFT(CODE,2),'01',1,'11',2,'22',3,'02',4,'03',5,'04',6,'05',7,0)                                       ");
		sql.append("		with ur            ");

		log.info("查询品牌目录产品布局初始品牌表格信息Sql为："+sql);
		return sql.toString();
	}
	
	
	public List<ProductLayTableVO> getProductLayTable(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<ProductLayTableVO> ProductLayList = new ArrayList<ProductLayTableVO>();
		try {
			sql = getSqlProductLay();
			ProductLayList = dbbean.executeQuery(sql, ProductLayTableVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询品牌目录产品布局表格信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询品牌目录产品布局表格信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return ProductLayList;
	}
	
	/**
	 * 获取查询品牌目录产品布局分中烟表格数据的sql
	 * @return 
	 */
	public static String getSqlProductLayZY(String code){
		StringBuffer sql = new StringBuffer();                                                
		sql.append(" 	select * from (                                                                   ");
		sql.append(" 		SELECT	                                                                                                      ");
		sql.append("		 			NAME                          										                                                      ");
		sql.append("		 			,CASE WHEN COUNT_brand=0 THEN'-' ELSE to_char(COUNT_brand)  END      as   COUNT_brand                            ");
		sql.append("		 			,CASE WHEN C_BRAND_PER=0 THEN'-' ELSE to_char(C_BRAND_PER)  END       as      C_BRAND_PER                        ");
		sql.append("		 			,CASE WHEN PRODUCENUM=0 THEN'-' ELSE to_char(PRODUCENUM)  END         as PRODUCENUM         	                    ");
		sql.append("		 			,CASE WHEN PRODUCENUM_PER=0 THEN'-' ELSE to_char(PRODUCENUM_PER)   END     as PRODUCENUM_PER       		          ");
		sql.append("		 		FROM(                           																                                          ");
		sql.append("		 		SELECT                          															                                            ");
		sql.append("		 				'11' AS CODE                                                                                          ");
		sql.append("		 				,'高价位' AS NAME                                                                                     ");
		sql.append("		 				,A.C_BRAND as COUNT_brand	                                                                            ");
		sql.append("		 				,DECIMAL(ROUND(A.C_BRAND*1.0000/S.C_BRAND*100,2), 16, 2) AS C_BRAND_PER                               ");
		sql.append("		 				,TO_CHAR(DECIMAL(ROUND(A.PRODUCENUM*1.0000/50000, 2), 16, 2),'999990.99') AS                          ");
		sql.append("PRODUCENUM	                                                		                                                  ");
		sql.append("		 				,DECIMAL(ROUND(DECODE                                                                                 ");
		sql.append("(S.PRODUCENUM,0,0,NULL,0,A.PRODUCENUM*1.0000/S.PRODUCENUM)*100, 2), 16, 2) AS PRODUCENUM_PER                      "); 		
		sql.append("		 				FROM                                           					                                              ");
		sql.append("		 				(SELECT COUNT(K.C_BRAND) AS C_BRAND            					                                              ");
		sql.append("		 						,SUM(PRODUCENUM) AS PRODUCENUM             			                                                  ");
		sql.append("		 		 FROM(                                             						                                            ");
		sql.append("		 		SELECT                                             				                                                ");
		sql.append("		 						DISTINCT(C_BRAND) AS C_BRAND               			                                                  ");
		sql.append("		 						,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM                                               ");
		sql.append("(JUMPNUM1) AS PRODUCENUM                                         	                                                ");
		sql.append("		 						FROM NICK_K_TJIN_Y_ALL   NK,NICK_TJYY_CIGARETTE SC                                ");
		sql.append("		 						WHERE	1=1                                			                                                    ");
		sql.append("		 						AND Y = YEAR(CURRENT DATE  -1 DAYS)                                                         ");
		sql.append("		 						AND NK.C_BRAND = SC.cig_codebar                                               ");
		sql.append("		 					and ((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))                             ");
		sql.append("		 						GROUP BY C_BRAND                                                                                  ");
		sql.append("		 						HAVING( SUM(PRINTNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(REJECTNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(REPEATNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(JUMPNUM1)<>0)                                                                          ");
		sql.append("		 						)K,VIEW_CIG_PROPERTY P                                                                            ");
		sql.append("		 						WHERE 1=1                                                                                         ");
		sql.append("		 					AND K.C_BRAND = P.C_BRAND		                                                                        ");
		sql.append("		 						AND FACT_POPEDOM='").append(code).append("'                                                                       ");
		sql.append("		 					AND P.IS_HIGH_PRICE_BRAND =1 ) A,                                                                   ");
		sql.append("		 				(SELECT                                                                                               ");
		sql.append("		 						COUNT(DISTINCT(C_BRAND)) AS C_BRAND                                                               ");
		sql.append("		 						,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM                                               ");
		sql.append("(JUMPNUM1) AS PRODUCENUM                                         	                                                ");
		sql.append("		 						FROM NICK_K_TJIN_Y_ALL    NK,NICK_TJYY_CIGARETTE SC                           ");
		sql.append("		 						WHERE	1=1                                                                                         ");
		sql.append("		 						AND Y = YEAR(CURRENT DATE -1 DAYS)                                                  ");
		sql.append("		 AND FACT_POPEDOM=	'").append(code).append("'                                                                                ");
		sql.append("		 	AND NK.C_BRAND = SC.cig_codebar and ((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))                       ");
		sql.append("		 						HAVING( SUM(PRINTNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(REJECTNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(REPEATNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(JUMPNUM1)<>0)) S                                                                       ");
		sql.append("		 			UNION ALL                                                                                               ");
		sql.append("		 		SELECT                                                                                                    ");
		sql.append("		 				'22' AS CODE                                                                                          ");
		sql.append("		 				,'高端' AS NAME		 				                                                                            ");
		sql.append("		 				,A.C_BRAND as COUNT_brand	                                                                            ");
		sql.append("		 				,DECIMAL(ROUND(A.C_BRAND*1.0000/S.C_BRAND*100,2), 16, 2) AS C_BRAND_PER                  		          ");
		sql.append("		 				,TO_CHAR(DECIMAL(ROUND(A.PRODUCENUM*1.0000/50000, 2), 16, 2),'999990.99') AS                          ");
		sql.append("PRODUCENUM	                                                		                                                  ");
		sql.append("		 				,DECIMAL(ROUND(DECODE                                                                                 ");
		sql.append("(S.PRODUCENUM,0,0,NULL,0,A.PRODUCENUM*1.0000/S.PRODUCENUM)*100, 2), 16, 2) AS PRODUCENUM_PER                      "); 		
		sql.append("		 				from                                                 				                                          ");
		sql.append("		 				(SELECT COUNT(K.C_BRAND) AS C_BRAND                  				                                          ");
		sql.append("		 						,SUM(PRODUCENUM) AS PRODUCENUM                   	                                                ");
		sql.append("		 		 FROM(                                                                                                    ");
		sql.append("		 		SELECT                                                                                                    ");
		sql.append("		 						DISTINCT(C_BRAND) AS C_BRAND                                                                      ");
		sql.append("		 						,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM                                               ");
		sql.append("(JUMPNUM1) AS PRODUCENUM                                         	                                                ");
		sql.append("		 				FROM NICK_K_TJIN_Y_ALL   NK,NICK_TJYY_CIGARETTE SC                                           ");
		sql.append("		 						WHERE	1 = 1                                                                                       ");
		sql.append("		 					 AND Y = YEAR(CURRENT DATE -1 DAYS)                                                                ");
		sql.append("		 					AND NK.C_BRAND = SC.cig_codebar                                                         ");
		sql.append("		 					and ((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))                 ");
		sql.append("		 						AND FACT_POPEDOM='").append(code).append("'                                                                       ");
		sql.append("		 						GROUP BY C_BRAND                                                                                  ");
		sql.append("		 						HAVING( SUM(PRINTNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(REJECTNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(REPEATNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(JUMPNUM1)<>0)                                                                          ");
		sql.append("		 						)K,cig_property_div P                                                                       ");
		sql.append("		 						WHERE 1=1                                                                                         ");
		sql.append("		 					AND K.C_BRAND = P.C_BRAND                                                                           ");
		sql.append("		 					AND P.IS_HIGH_TIER_BRAND =1) A,                                                                     ");
		sql.append("		 				(SELECT                                                                                               ");
		sql.append("		 						COUNT(DISTINCT(C_BRAND)) AS C_BRAND                                                               ");
		sql.append("		 						,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM                                               ");
		sql.append("(JUMPNUM1) AS PRODUCENUM                                         	                                                ");
		sql.append("		 				FROM NICK_K_TJIN_Y_ALL  NK,NICK_TJYY_CIGARETTE SC                                         ");
		sql.append("		 						WHERE	1 = 1                                                                                       ");
		sql.append("		 					 AND Y = YEAR(CURRENT DATE -1 DAYS)                                                                ");
		sql.append("							    AND FACT_POPEDOM=	'").append(code).append("'                                                                    ");
		sql.append("		 	AND NK.C_BRAND = SC.cig_codebar and ((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))                ");
		sql.append("		 						HAVING( SUM(PRINTNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(REJECTNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(REPEATNUM1)<>0                                                                         ");
		sql.append("		 								OR SUM(JUMPNUM1)<>0)) S                                                                       ");
		sql.append("		 		UNION ALL                                                                                                 ");
		sql.append("		 		SELECT                                                                                                    ");
		sql.append("		 				CODE AS SORT_NO                                                                                       ");
		sql.append("		 				,A.NAME AS NAME                                                                                       ");
		sql.append("		 				,A.C_BRAND as C_BRAND	                                                                                ");
		sql.append("		 				,DECIMAL(ROUND(SUM(A.C_BRAND)*1.0000/SUM(S.C_BRAND)*100,2), 16, 2) AS                                 ");
		sql.append("C_BRAND_PER                                                       		                                            ");
		sql.append("		 				,TO_CHAR(DECIMAL(ROUND(SUM(A.PRODUCENUM)*1.0000/50000, 2), 16,                                        ");
		sql.append("                                                                                                                  ");
		sql.append("2),'999990.99') AS PRODUCENUM	                                          		                                      ");
		sql.append("		 				,DECIMAL(ROUND(DECODE(SUM(S.PRODUCENUM),0,0,NULL,0,SUM(A.PRODUCENUM)                                  ");
		sql.append("                                                                                                                  ");
		sql.append("*1.0000/SUM(S.PRODUCENUM))*100, 2), 16, 2) AS PRODUCENUM_PER        		                                          ");
		sql.append("		 				FROM(                                                                                                 ");
		sql.append("		 				SELECT	O.CLASS_NAME AS NAME                                                                          ");
		sql.append("		 						,CODE                                	                                                            ");
		sql.append("		 						,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM                                               ");
		sql.append("(JUMPNUM1) AS PRODUCENUM                                         	                                                ");
		sql.append("		 						,COUNT(DISTINCT(K.C_BRAND)) AS C_BRAND                                                            ");
		sql.append("		 						FROM NICK_K_TJIN_Y_ALL K                                                                ");
		sql.append("		 						INNER JOIN DW_T_CLASS_CODE O ON K.C_CLASS=O.CODE                                           ");
		sql.append("		 					left join NICK_TJYY_CIGARETTE SC on K.C_BRAND = SC.cig_codebar                                          ");
		sql.append("		 						WHERE 1=1                                                                                         ");
		sql.append("		 		AND ((D_YEAR<='2017' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_innx='1'))              ");
		sql.append("		 			AND Y = YEAR(CURRENT DATE -1 DAYS)                                                      ");
		sql.append("		 						AND FACT_POPEDOM='").append(code).append("'                                                                       ");
		sql.append("		 						GROUP BY O.CLASS_NAME, CODE                  		                                                  ");
		sql.append("		 						HAVING( SUM(PRINTNUM1)<>0          				                                                        ");
		sql.append("		 								OR SUM(REJECTNUM1)<>0          			                                                          ");
		sql.append("		 								OR SUM(REPEATNUM1)<>0          			                                                          ");
		sql.append("		 								OR SUM(JUMPNUM1)<>0)           			                                                          ");
		sql.append("		 				) A,                                       		                                                        ");
		sql.append("		 				(SELECT                                    		                                                        ");
		sql.append("		 						COUNT(DISTINCT(C_BRAND)) AS C_BRAND    		                                                        ");
		sql.append("		 						,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM                                               ");
		sql.append("(JUMPNUM1) AS PRODUCENUM                                         	                                                ");
		sql.append("		 					FROM NICK_K_TJIN_Y_ALL K   ,NICK_TJYY_CIGARETTE SC                                          ");
		sql.append("		 						WHERE 1=1                                                                                         ");
		sql.append("		 				AND K.C_BRAND = SC.cig_codebar                                               ");
		sql.append("		 			and ((D_YEAR<='2017' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_innx='1'))                            ");
		sql.append("							    AND FACT_POPEDOM=	'").append(code).append("'                                                                    ");
		sql.append("		 				AND Y = YEAR(CURRENT DATE -1 DAYS )                                            ");
		sql.append("		 						HAVING( SUM(PRINTNUM1)<>0              				                                                    ");
		sql.append("		 								OR SUM(REJECTNUM1)<>0              		                                                        ");
		sql.append("		 								OR SUM(REPEATNUM1)<>0              		                                                        ");
		sql.append("		 								OR SUM(JUMPNUM1)<>0)               		                                                        ");
		sql.append("		 				) S                                            	                                                      ");
		sql.append("		 				GROUP BY NAME,A.C_BRAND,CODE   							                                                          ");
		sql.append("		 		)												                                                                                  ");
		sql.append("		 		ORDER BY DECODE(LEFT(CODE,2),'01',1,'11',2,'22',3,'02',4,'03',5,'04',6,'05',7,0)                          ");
		sql.append("		 	                                             ");
		sql.append("		 	) where NAME not in ('无价类') with ur            										                                                                                                                      ");
		log.info("查询品牌目录产品布局分中烟表格信息Sql为："+sql);
		return sql.toString();
	}
	
	
	public List<ProductLayTableVO> getProductLayTableZY(String dbName,String code){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<ProductLayTableVO> ProductLayList = new ArrayList<ProductLayTableVO>();
		try {
			sql = getSqlProductLayZY(code);
			ProductLayList = dbbean.executeQuery(sql, ProductLayTableVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询品牌目录产品布局分中烟表格信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询品牌目录产品布局分中烟表格信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return ProductLayList;
	}

	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<ProductLayTableVO> ProductLayList=null;
		String type=request.getParameter("type");								//获取下钻的类型
		String code=request.getParameter("code");								//获取编码
		Gson gson = new Gson();
		if (type.equals("first") && code.equals("00000000")) {
			ProductLayList = getProductLayTable("dw");
		
		}else {
			ProductLayList = getProductLayTableZY("dw",code);
		}
		
		
		String s = gson.toJson(ProductLayList);
		log.info("品牌目录产品布局表格信息业务查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	

}
