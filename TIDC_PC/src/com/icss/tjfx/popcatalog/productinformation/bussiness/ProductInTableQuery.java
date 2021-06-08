package com.icss.tjfx.popcatalog.productinformation.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.popcatalog.productinformation.vo.ProductInTableVO;
import com.icss.tjfx.total.in.vo.ProduceNumBarVO;

public class ProductInTableQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(ProductInTableQuery.class);
	
	/**
	 * 获取查询品牌目录产品信息初始品牌数据的sql
	 * @return 
	 */
	public static String getSqlProductIn(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT                                                             ");
		sql.append("	C_CIG as xz_parameter                                                                     ");
		sql.append("	,NVL(CIG_MARKNAME, '合 计') AS CIG_NAME   		                                                            ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(PRODUCENUM)*1.0000/50000, 2), 16, 2),'999990.99') AS PRODUCENUM												                                                               ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(PRODUCENUM_L)*1.0000/50000, 2), 16, 2),'999990.99') AS PRODUCENUM_L                                                                                   ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(PRODUCENUM_L),0,0,NULL,0,SUM(PRODUCENUM - PRODUCENUM_L)*100.0000/SUM(PRODUCENUM_L)), 2), 16, 2),'999990.99') AS PRODUCENUM_GROWTH_RATE         ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(TERMSTOCK1)*1.0000/50000, 2), 16, 2),'999990.99') AS TERMSTOCK1										                                                                   ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(TERMSTOCK1_L)*1.0000/50000, 2), 16, 2),'999990.99') AS TERMSTOCK1_L								                                                                   ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(TERMSTOCK1 - TERMSTOCK1_L)*1.0000/50000, 2), 16, 2),'999990.99') AS TERMSTOCK1_GROWTH		                                                             ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(BS_SELLNUM)*1.0000/50000, 2), 16, 2),'999990.99') AS BS_SELLNUM                                                                                       ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(BS_SELLNUM_L)*1.0000/50000, 2), 16, 2),'999990.99') AS BS_SELLNUM_L												                                                           ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLNUM - BS_SELLNUM_L)*100.0000/SUM(BS_SELLNUM_L)), 2), 16, 2),'999990.99') AS BS_SELLNUM_GROWTH_RATE         ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(TERM_STOCK1)*1.0000/50000, 2), 16, 2),'999990.99') AS TERM_STOCK1										                                                                 ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(TERM_STOCK1_L)*1.0000/50000, 2), 16, 2),'999990.99') AS TERM_STOCK1_L								                                                                 ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(TERM_STOCK1_L),0,0,NULL,0,SUM(TERM_STOCK1 - TERM_STOCK1_L)*100.0000/SUM(TERM_STOCK1_L)), 2), 16, 2),'999990.99') AS TERM_STOCK1_GROWTH_RATE    ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(BS_SELLSUM)*1.0000/10000, 2), 16, 2),'999990.99') AS BS_SELLSUM													                                                             ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(SUM(BS_SELLSUM_L)*1.0000/10000, 2), 16, 2),'999990.99') AS BS_SELLSUM_L											                                                             ");
		sql.append("	,TO_CHAR(DECIMAL(ROUND(DECODE(SUM(BS_SELLSUM_L),0,0,NULL,0,SUM(BS_SELLSUM - BS_SELLSUM_L)*100.0000/SUM(BS_SELLSUM_L)), 2), 16, 2),'999990.99') AS BS_SELLSUM_GROWTH_RATE		     ");
	/*	sql.append("	,' ' AS 	JIALEI								         ");
		sql.append("	,' ' AS TRANSFERPRICE                   ");
		sql.append("	,' ' AS WHOLESALEPRICE                  ");
		sql.append("	,' ' AS SUGGESTEDRETAILPRICE            ");
		sql.append("	,' ' AS 	BRAND_TYPE_NAME				         ");
		sql.append("	,' ' AS picture            ");*/
		sql.append("FROM                                                                                   ");
		sql.append("	(                                                                                    ");
		sql.append("		SELECT                                                                             ");
		sql.append("			NVL(A.CIG_MARKNAME, '合 计') AS CIG_MARKNAME                                       ");
		sql.append("			,A.C_CIG AS C_CIG                                                                ");
		sql.append("			,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM(JUMPNUM1) AS PRODUCENUM                                                                                  ");
		sql.append("			,SUM(PRINTNUM1_P)-SUM(REJECTNUM1_P)+SUM(REPEATNUM1_P)-SUM(JUMPNUM1_P) AS PRODUCENUM_L                                                                            ");
		sql.append("			,SUM(TERMSTOCK1) AS TERMSTOCK1    	                                          ");
		sql.append("			,SUM(TERMSTOCK1_P) AS TERMSTOCK1_L                                             ");
		sql.append("			,0  AS BS_SELLNUM                                                              ");
		sql.append("			,0  AS BS_SELLNUM_L                                                            ");
		sql.append("			,0  AS TERM_STOCK1                                                             ");
		sql.append("			,0  AS TERM_STOCK1_L                                                           ");
		sql.append("			,0  AS BS_SELLSUM                                                              ");
		sql.append("			,0  AS BS_SELLSUM_L                                                            ");
		sql.append("		FROM NICK_K_TJIN_Y_ALL K                                                     ");
		sql.append("			INNER JOIN cig_property_div	A                                            ");
		sql.append("			ON A.C_BRAND = K.C_BRAND                                                       ");
		sql.append("			WHERE 1=1  AND IS_NRT= '1'                                                                    ");
		sql.append("			AND C_CLASS <>'06'                                                               ");
		sql.append("			AND K.Y = YEAR(CURRENT DATE-1 DAYS)                                                   ");
		sql.append("		GROUP BY A.CIG_MARKNAME,A.C_CIG                                                  ");
		sql.append("		WITH ROLLUP                                                                      ");
		sql.append("		HAVING (A.CIG_MARKNAME IS NULL AND A.C_CIG IS NULL) OR (A.CIG_MARKNAME IS NOT NULL AND A.C_CIG IS NOT NULL)                                                                    ");
		sql.append("	                                                                                                  ");
		sql.append("	UNION ALL                                                                                         ");
		sql.append("	                                                                                                  ");
		sql.append("	SELECT 		NVL(A.CIG_MARKNAME, '合 计') AS CIG_MARKNAME                                              ");
		sql.append("			,A.C_CIG AS C_CIG                                                                             ");
		sql.append("			,0 AS PRODUCENUM                                                                              ");
		sql.append("			,0  AS PRODUCENUM_L                                                                           ");
		sql.append("			,0 AS TERMSTOCK1                                                                              ");
		sql.append("			,0 AS TERMSTOCK1_L                                                                            ");
		sql.append("			,SUM(PRINT_NUM1)-SUM(RBACK_NUM1) AS BS_SELLNUM   		                                  ");
		sql.append("			,SUM(PRINT_NUM1_P)-SUM(RBACK_NUM1_P) AS BS_SELLNUM_L                                    ");
		sql.append("			,SUM(TERM_STOCK1) AS TERM_STOCK1 	                                                            ");
		sql.append("			,SUM(TERM_STOCK1_P) AS TERM_STOCK1_L	                                                        ");
		sql.append("			,SUM(PRINT_SUM)-SUM(RBACK_SUM) AS BS_SELLSUM                                          ");
		sql.append("			,SUM(PRINT_SUM_P)-SUM(RBACK_SUM_P) AS BS_SELLSUM_L                                      ");
		sql.append("			FROM NICK_K_TJBS_Y_ALL K,cig_property_div A                                         ");
		sql.append("			WHERE 1=1                                                                                     ");
		sql.append("			AND C_CLASS <>'06'                                                                             ");
		sql.append("			AND A.C_BRAND = K.C_BRAND			              	                                                ");
		sql.append("			AND K.Y = YEAR(CURRENT DATE-1 DAYS)                                                                  ");
		sql.append("		GROUP BY A.CIG_MARKNAME,A.C_CIG                                                                 ");
		sql.append("		WITH ROLLUP                                                                                     ");
		sql.append("		HAVING (A.CIG_MARKNAME IS NULL AND A.C_CIG IS NULL) OR (A.CIG_MARKNAME IS NOT NULL AND A.C_CIG IS NOT NULL)                                                                    ");
		sql.append("	)                                                                              ");
		sql.append("GROUP BY C_CIG,CIG_MARKNAME                                                      ");
		sql.append("HAVING                                                                           ");
		sql.append("	(                                                                              ");
		sql.append("		SUM(PRODUCENUM)         <> 0                                                 ");
		sql.append("		OR SUM(PRODUCENUM_L)    <> 0                                                 ");
		sql.append("		OR SUM(TERMSTOCK1)      <> 0                                                 ");
		sql.append("		OR SUM(TERMSTOCK1_L)    <> 0                                                 ");
		sql.append("		OR SUM(BS_SELLNUM)      <> 0                                                 ");
		sql.append("		OR SUM(BS_SELLNUM_L)    <> 0                                                 ");
		sql.append("		OR SUM(TERM_STOCK1)     <> 0                                                 ");
		sql.append("		OR SUM(TERM_STOCK1_L)   <> 0                                                 ");
		sql.append("		OR SUM(BS_SELLSUM)      <> 0                                                 ");
		sql.append("		OR SUM(BS_SELLSUM_L)    <> 0                                                 ");
		sql.append("	)                                                                              ");
		sql.append("ORDER BY PRODUCENUM DESC                                                         ");
		sql.append("WITH UR                                                                        ");
		
		
		
		
		log.info("查询品牌目录产品信息初始品牌表格信息Sql为："+sql);
		return sql.toString();
	}
	
	
	public List<ProductInTableVO> getProductInTable(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<ProductInTableVO> productInList = new ArrayList<ProductInTableVO>();
		try {
			sql = getSqlProductIn();
			productInList = dbbean.executeQuery(sql, ProductInTableVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询品牌目录产品信息初始品牌表格信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询品牌目录产品信息初始品牌表格信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return productInList;
	}
	
	public static String getSqlProductInSpec(String code,String factCode){
		StringBuffer sql = new StringBuffer();
		sql.append("  select * from (                                            ");
		sql.append("select * from (  SELECT                                                            ");
		sql.append("	NVL(A.CIG_NAME, '合计') AS CIG_NAME   			                           ");
		sql.append("	,A.C_BRAND     AS xz_parameter                                       ");
		sql.append("	,CASE WHEN PRODUCENUM1=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM1*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM												                                                                        ");
		sql.append("	,CASE WHEN PRODUCENUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM_L*1.0000/50000, 2), 16, 2),'999990.99')END AS PRODUCENUM_L                                                                                            ");
		sql.append("	,CASE WHEN PRODUCENUM1=0 OR PRODUCENUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(PRODUCENUM_L,0,0,NULL,0,(PRODUCENUM1 - PRODUCENUM_L)*100.0000/PRODUCENUM_L), 2), 16, 2),'999990.99')END AS PRODUCENUM_GROWTH_RATE        ");
		sql.append("	,CASE WHEN TERMSTOCK1=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(TERMSTOCK1*1.0000/50000, 2), 16, 2),'999990.99')END AS TERMSTOCK1									                                                                                ");
		sql.append("	,CASE WHEN TERMSTOCK1_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(TERMSTOCK1_L*1.0000/50000, 2), 16, 2),'999990.99')END AS TERMSTOCK1_L								                                                                            ");
		sql.append("	,CASE WHEN TERMSTOCK1=0 AND TERMSTOCK1_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND((TERMSTOCK1 - TERMSTOCK1_L)*1.0000/50000, 2), 16, 2),'999990.99')END AS TERMSTOCK1_GROWTH		                                                    ");
		sql.append("	,CASE WHEN BS_SELLNUM=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM*1.0000/50000, 2), 16, 2),'999990.99')END AS BS_SELLNUM                                                                                                  ");
		sql.append("	,CASE WHEN BS_SELLNUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM_L*1.0000/50000, 2), 16, 2),'999990.99')END AS BS_SELLNUM_L											                                                                      ");
		sql.append("	,CASE WHEN BS_SELLNUM=0 OR BS_SELLNUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM_L,0,0,NULL,0,(BS_SELLNUM - BS_SELLNUM_L)*100.0000/BS_SELLNUM_L), 2), 16, 2),'999990.99')END AS BS_SELLNUM_GROWTH_RATE          ");
		sql.append("	,CASE WHEN TERM_STOCK1=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(TERM_STOCK1*1.0000/50000, 2), 16, 2),'999990.99')END AS TERM_STOCK1										                                                                            ");
		sql.append("	,CASE WHEN TERM_STOCK1_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(TERM_STOCK1_L*1.0000/50000, 2), 16, 2),'999990.99')END AS TERM_STOCK1_L									                                                                        ");
		sql.append("	,CASE WHEN TERM_STOCK1=0 OR TERM_STOCK1_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(TERM_STOCK1_L,0,0,NULL,0,(TERM_STOCK1 - TERM_STOCK1_L)*100.0000/TERM_STOCK1_L), 2), 16, 2),'999990.99')END AS TERM_STOCK1_GROWTH_RATE   ");
		sql.append("	,CASE WHEN BS_SELLSUM=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM*1.0000/10000, 2), 16, 2),'999990.99')END AS BS_SELLSUM													                                                                        ");
		sql.append("	,CASE WHEN BS_SELLSUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM_L*1.0000/10000, 2), 16, 2),'999990.99')END AS BS_SELLSUM_L											                                                                      ");
		sql.append("	,CASE WHEN BS_SELLSUM=0 OR BS_SELLSUM_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLSUM_L,0,0,NULL,0,(BS_SELLSUM - BS_SELLSUM_L)*100.0000/BS_SELLSUM_L), 2), 16, 2),'999990.99')END AS BS_SELLSUM_GROWTH_RATE		      ");
		sql.append("	,NVL(CHAR(B.CLASS_NAME),'-')	AS 	JIALEI										                                 ");
		sql.append("	,case when substr(NVL(CHAR(TRANSFERPRICE), '-'),1,1)='.' then '0'||CHAR(TRANSFERPRICE) else NVL(CHAR(TRANSFERPRICE), '-') end AS TRANSFERPRICE      ");
		sql.append("	,case when substr(NVL(CHAR(P.WHOLESALEPRICE), '-'),1,1)='.' then '0'||CHAR(P.WHOLESALEPRICE) else NVL(CHAR(P.WHOLESALEPRICE), '-') end AS WHOLESALEPRICE    ");
		sql.append("	,case when substr(NVL(CHAR(SUGGESTEDRETAILPRICE), '-'),1,1)='.' then '0'||CHAR(SUGGESTEDRETAILPRICE) else NVL(CHAR(SUGGESTEDRETAILPRICE), '-')  end AS SUGGESTEDRETAILPRICE    ");
		sql.append("	,CASE O.IS_BEAD 	WHEN '0' THEN'-' WHEN '1' THEN '1' ELSE '-' END	AS 			IS_BEAD				      ");
		sql.append("	,CASE O.IS_THIN 	WHEN '0' THEN'-' WHEN '1' THEN '1' ELSE '-' END	AS 			IS_THIN				      ");
		sql.append("	,CASE O.IS_SHORT	WHEN '0' THEN'-' WHEN '1' THEN '1' ELSE '-' END AS 			IS_SHORT			      ");
		sql.append("	,' ' as picture			      ");
		sql.append("	FROM                                           ");
		sql.append("	(                                              ");
		sql.append("		SELECT                                       ");
		sql.append("			C_BRAND AS C_BRAND                         ");
		sql.append("			,C_CLASS AS C_CLASS                        ");
		sql.append("			,SUM(PRODUCENUM) AS PRODUCENUM1            ");
		sql.append("			,SUM(PRODUCENUM_L) AS PRODUCENUM_L         ");
		sql.append("			,SUM(TERMSTOCK1) AS TERMSTOCK1    	       ");
		sql.append("			,SUM(TERMSTOCK1_L) AS TERMSTOCK1_L         ");
		sql.append("			,SUM(BS_SELLNUM) AS BS_SELLNUM   			     ");
		sql.append("			,SUM(BS_SELLNUM_L) AS BS_SELLNUM_L         ");
		sql.append("			,SUM(TERM_STOCK1) AS TERM_STOCK1 		       ");
		sql.append("			,SUM(TERM_STOCK1_L) AS TERM_STOCK1_L	     ");
		sql.append("			,SUM(BS_SELLSUM) AS BS_SELLSUM             ");
		sql.append("			,SUM(BS_SELLSUM_L) AS BS_SELLSUM_L         ");
		sql.append("		FROM (                                       ");
		sql.append("		SELECT                                       ");
		sql.append("			NVL(K.C_BRAND,'0000') AS C_BRAND           ");
		sql.append("			,C_CLASS AS C_CLASS                        ");
		sql.append("			,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM(JUMPNUM1) 		AS PRODUCENUM                           ");
		sql.append("			,SUM(PRINTNUM1_P)-SUM(REJECTNUM1_P)+SUM(REPEATNUM1_P)-SUM(JUMPNUM1_P) 	AS PRODUCENUM_L                   ");
		sql.append("			,SUM(TERMSTOCK1) 																	AS TERMSTOCK1    	                                      ");
		sql.append("			,SUM(TERMSTOCK1_P) AS TERMSTOCK1_L              ");
		sql.append("			,SUM(0) as BS_SELLNUM                           ");
		sql.append("			,SUM(0) as BS_SELLNUM_L                         ");
		sql.append("			,SUM(0) as TERM_STOCK1                          ");
		sql.append("			,SUM(0) as TERM_STOCK1_L                        ");
		sql.append("			,SUM(0) as BS_SELLSUM                           ");
		sql.append("			,SUM(0) as BS_SELLSUM_L                         ");
		sql.append("		FROM  NICK_K_TJIN_Y_ALL K   LEFT JOIN NICK_TJYY_CIGARETTE A          ");
		sql.append("			ON K.C_Brand=A.CIG_CODEBAR                   ");
		sql.append("			LEFT JOIN CIG_PROPERTY_DIV B  ON K.C_BRAND=B.C_BRAND                       ");
		sql.append("			left join  DIM_V_PRODUCE_ORG s on A.CIG_PRODUCER=S.PRODUCE_CODE  WHERE 1=1 		                                                                 ");
		sql.append("			AND A.CIG_BARCARRIER='02'                                                   ");
		sql.append("			and ((D_YEAR<='2017' AND IS_NRT='1' AND A.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND A.is_innx='1'))                 ");
		sql.append("			AND C_CLASS <>'06'                                                             ");
		sql.append("			AND B.C_CIG='").append(code).append("'                                         ");                    
		sql.append("			AND s.fact_pop_code='").append(factCode).append("'");
		sql.append("			AND K.D_YEAR=TO_CHAR(CURRENT DATE - 1 DAYS,'YYYY')                             ");
		sql.append("		GROUP BY K.C_BRAND,C_CLASS                                                        ");
		sql.append("		WITH ROLLUP                                                                      ");
		sql.append("	  HAVING (K.C_BRAND IS NULL AND C_CLASS IS NULL) OR (K.C_BRAND IS NOT NULL AND C_CLASS IS NOT NULL)                                             ");
		sql.append("	UNION ALL                                                                           ");
		sql.append("		SELECT 	                                                                          ");
		sql.append("			NVL(K.C_BRAND, '0000') AS C_BRAND                                               ");
		sql.append("			,C_CLASS AS C_CLASS                                                             ");
		sql.append("			,SUM(0) as PRODUCENUM                                                           ");
		sql.append("			,SUM(0) as PRODUCENUM_L                                                         ");
		sql.append("			,SUM(0) as TERMSTOCK1                                                           ");
		sql.append("			,SUM(0) as TERMSTOCK1_L                                                         ");
		sql.append("			,SUM(PRINT_NUM1)-SUM(RBACK_NUM1) AS BS_SELLNUM   			                           ");
		sql.append("			,SUM(PRINT_NUM1_P)-SUM(RBACK_NUM1_P) AS BS_SELLNUM_L                             ");
		sql.append("			,SUM(TERM_STOCK1) AS TERM_STOCK1 		                                             ");
		sql.append("			,SUM(TERM_STOCK1_P) AS TERM_STOCK1_L	                                           ");
		sql.append("			,SUM(PRINT_SUM)-SUM(RBACK_SUM) AS BS_SELLSUM                                     ");
		sql.append("			,SUM(PRINT_SUM_P)-SUM(RBACK_SUM_P) AS BS_SELLSUM_L                               ");
		sql.append("			FROM   NICK_K_TJBS_Y_ALL K      	                                                 ");
		sql.append("			LEFT JOIN NICK_TJYY_CIGARETTE A   ON K.C_Brand=A.CIG_CODEBAR                        ");
		sql.append("			LEFT JOIN CIG_PROPERTY_DIV B ON K.C_BRAND=B.C_BRAND                 ");
		sql.append("			left join  DIM_V_PRODUCE_ORG s on A.CIG_PRODUCER=S.PRODUCE_CODE  WHERE 1=1                                                                        ");
		sql.append("			AND A.CIG_BARCARRIER='02'                                            ");
		sql.append("			and ((D_YEAR<='2017' AND A.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND A.is_bsnx='1'))            ");
		sql.append("			AND B.C_CIG='").append(code).append("'		              	                                           ");
		sql.append("			AND s.fact_pop_code='").append(factCode).append("'");
		sql.append("			AND K.D_YEAR=TO_CHAR(CURRENT DATE - 1 DAYS,'YYYY')                               ");
		sql.append("		GROUP BY K.C_BRAND,C_CLASS                                                         ");
		sql.append("		WITH ROLLUP                                                                        ");
		sql.append("		HAVING (K.C_BRAND IS NULL AND C_CLASS IS NULL) OR (K.C_BRAND IS NOT NULL AND C_CLASS IS NOT NULL)                              ");
		sql.append("	)                                                ");
		sql.append("	GROUP BY C_BRAND,C_CLASS                         ");
		sql.append("		HAVING                                         ");
		sql.append("	(                                                ");
		sql.append("		SUM(PRODUCENUM)         <> 0                   ");
		sql.append("		OR SUM(PRODUCENUM_L)    <> 0                   ");
		sql.append("		OR SUM(TERMSTOCK1)      <> 0                   ");
		sql.append("		OR SUM(TERMSTOCK1_L)    <> 0                   ");
		sql.append("		OR SUM(BS_SELLNUM)      <> 0                   ");
		sql.append("		OR SUM(BS_SELLNUM_L)    <> 0                   ");
		sql.append("		OR SUM(TERM_STOCK1)     <> 0                   ");
		sql.append("		OR SUM(TERM_STOCK1_L)   <> 0                   ");
		sql.append("		OR SUM(BS_SELLSUM)      <> 0                   ");
		sql.append("		OR SUM(BS_SELLSUM_L)    <> 0                   ");
		sql.append("	)                                                ");
		sql.append("	)K                                               ");
		sql.append("	LEFT JOIN CIG_PROPERTY_DIV A                     ");
		sql.append("	ON K.C_Brand=A.C_BRAND                           ");
		sql.append("	LEFT JOIN view_cig_property O                    ");
		sql.append("	ON K.C_BRAND=O.C_BRAND                           ");
		sql.append("	LEFT JOIN DW_T_CLASS_CODE B 		                 ");
		sql.append("	ON B.CODE=K.C_CLASS                              ");
		sql.append("	LEFT JOIN NICK_STMA_CIGARETTE d		               ");
		sql.append("	ON K.C_BRAND = d.CIG_CODEBAR                     ");
		sql.append("	LEFT JOIN (SELECT  BAR, max(TRANSFERPRICE) as TRANSFERPRICE, max(WHOLESALEPRICE) as WHOLESALEPRICE, max(SUGGESTEDRETAILPRICE) as SUGGESTEDRETAILPRICE FROM NICK_PRICELIST group by bar) P	                 ");
		sql.append("	ON K.C_BRAND = P.BAR                                                                                             ");
		sql.append(" ) S ORDER BY DECODE(CIG_NAME,'合计',0,1) ASC, PRODUCENUM DESC                                                         ");
		sql.append("           ");
		sql.append("   ) where JIALEI not in ('无价类') WITH UR                                   ");
		log.info("查询品牌目录产品信息规格下钻表格信息sql："+sql);
		return sql.toString();
	}
	public List<ProductInTableVO> getProductInTableSpec(String dbName,String code,String factCode){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<ProductInTableVO> productInSpecList = new ArrayList<ProductInTableVO>();
		try {
			sql = getSqlProductInSpec(code,factCode);
			productInSpecList = dbbean.executeQuery(sql, ProductInTableVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询品牌目录产品信息规格下钻表格信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询品牌目录产品信息规格下钻表格信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return productInSpecList;
	}
	
	public static String getSqlProductInSmoke(String code){
		StringBuffer sql = new StringBuffer();       
		sql.append("select * from ( 	SELECT									                           ");   
		sql.append("		 	C_CIG AS xz_parameter				                                ");   
		sql.append("		 	,NVL(CIG_MARKNAME,'合  计') AS CIG_NAME   																										                                                     ");   
		sql.append("		 	,CASE WHEN PRODUCENUM1=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM1*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM						      ");   
		sql.append("		 	,CASE WHEN PRODUCENUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS PRODUCENUM_L				      ");   
		sql.append("		 	,CASE WHEN PRODUCENUM1=0 OR PRODUCENUM_L=0 THEN '-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(PRODUCENUM_L,0,0,NULL,0,(PRODUCENUM1 - PRODUCENUM_L)*100.0000/PRODUCENUM_L), 2), 16, 2),'999990.99')) END AS PRODUCENUM_GROWTH_RATE				");   
		sql.append("		 	,CASE WHEN TERMSTOCK1=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(TERMSTOCK1*1.0000/50000, 2), 16, 2),'999990.99') END AS TERMSTOCK1																                                                                                      ");   
		sql.append("		 	,CASE WHEN TERMSTOCK1_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(TERMSTOCK1_L*1.0000/50000, 2), 16, 2),'999990.99') END AS TERMSTOCK1_L															                                                                                  ");   
		sql.append("		 	,CASE WHEN TERMSTOCK1=0 AND TERMSTOCK1_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((TERMSTOCK1 - TERMSTOCK1_L)*1.0000/50000, 2), 16, 2),'999990.99') END AS TERMSTOCK1_GROWTH										                                                        ");   
		sql.append("		 	,CASE WHEN BS_SELLNUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM																                                                                                      ");   
		sql.append("		 	,CASE WHEN BS_SELLNUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM_L*1.0000/50000, 2), 16, 2),'999990.99') END AS BS_SELLNUM_L															                                                                                  ");   
		sql.append("		 	,CASE WHEN BS_SELLNUM=0 OR BS_SELLNUM_L=0 THEN '-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLNUM_L,0,0,NULL,0,(BS_SELLNUM - BS_SELLNUM_L)*100.0000/BS_SELLNUM_L), 2), 16, 2),'999990.99')) END AS BS_SELLNUM_GROWTH_RATE					");   
		sql.append("		 	,CASE WHEN TERM_STOCK1=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(TERM_STOCK1*1.0000/50000, 2), 16, 2),'999990.99') END AS TERM_STOCK1															                                                                                      ");   
		sql.append("		 	,CASE WHEN TERM_STOCK1_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(TERM_STOCK1_L*1.0000/50000, 2), 16, 2),'999990.99') END AS TERM_STOCK1_L															                                                                                ");   
		sql.append("		 	,CASE WHEN TERM_STOCK1=0 OR TERM_STOCK1_L=0 THEN '-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(TERM_STOCK1_L,0,0,NULL,0,(TERM_STOCK1 - TERM_STOCK1_L)*100.0000/TERM_STOCK1_L), 2), 16, 2),'999990.99')) END AS TERM_STOCK1_GROWTH_RATE		");		
		sql.append("		 	,CASE WHEN BS_SELLSUM=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM																                                                                                      ");   
		sql.append("		 	,CASE WHEN BS_SELLSUM_L=0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM_L*1.0000/10000, 2), 16, 2),'999990.99') END AS BS_SELLSUM_L															                                                                                  ");   
		sql.append("		 	,CASE WHEN BS_SELLSUM=0 OR BS_SELLSUM_L=0 THEN '-' ELSE DC_FUNC_TRANS_RATE(TO_CHAR(DECIMAL(ROUND(DECODE(BS_SELLSUM_L,0,0,NULL,0,(BS_SELLSUM - BS_SELLSUM_L)*100.0000/BS_SELLSUM_L), 2), 16, 2),'999990.99')) END AS BS_SELLSUM_GROWTH_RATE					");   
		sql.append("		 	FROM																						                   ");
		sql.append("		 	(																								                   ");
		sql.append("		 	SELECT 																					                   ");
		sql.append("		 			CIG_MARKNAME AS CIG_MARKNAME								                   ");
		sql.append("		 			,C_CIG AS C_CIG															                   ");
		sql.append("		 			,SUM(PRODUCENUM) AS PRODUCENUM1							                   ");
		sql.append("		 			,SUM(PRODUCENUM_L) AS PRODUCENUM_L                            ");
		sql.append("		 			,SUM(TERMSTOCK1) AS TERMSTOCK1    					                   ");
		sql.append("		 			,SUM(TERMSTOCK1_L) AS TERMSTOCK1_L					                   ");
		sql.append("		 			,SUM(BS_SELLNUM) AS BS_SELLNUM   						                   ");
		sql.append("		 			,SUM(BS_SELLNUM_L) AS BS_SELLNUM_L                            ");
		sql.append("		 			,SUM(TERM_STOCK1) AS TERM_STOCK1 						                   ");
		sql.append("		 			,SUM(TERM_STOCK1_L) AS TERM_STOCK1_L				                   ");
		sql.append("		 			,SUM(BS_SELLSUM) AS BS_SELLSUM							                   ");
		sql.append("		 			,SUM(BS_SELLSUM_L) AS BS_SELLSUM_L					                   ");
		sql.append("		 		FROM (																				                   ");
		sql.append("		 		SELECT 																				                   ");
		sql.append("		 			NVL(C.CIG_MARKNAME, '合  计') AS CIG_MARKNAME  				                                                                                                                                                                                            ");
		sql.append("		 			,C.C_CIG AS C_CIG               																									                                                                                                                                                              ");
		sql.append("		 			,SUM(PRINTNUM1)-SUM(REJECTNUM1)+SUM(REPEATNUM1)-SUM(JUMPNUM1) AS PRODUCENUM									                                                                                                                                                    ");
		sql.append("		 			,SUM(PRINTNUM1_P)-SUM(REJECTNUM1_P)+SUM(REPEATNUM1_P)-SUM(JUMPNUM1_P) AS PRODUCENUM_L                                                                                                                                                           ");
		sql.append("		 			,SUM(TERMSTOCK1) AS TERMSTOCK1    		                    ");
		sql.append("		 			,SUM(TERMSTOCK1_P) AS TERMSTOCK1_L		                    ");
		sql.append("		 			,0 AS BS_SELLNUM											                    ");
		sql.append("		 			,0 AS BS_SELLNUM_L							 			                    ");
		sql.append("		 			,0 AS TERM_STOCK1											                    ");
		sql.append("		 			,0 AS TERM_STOCK1_L										                    ");
		sql.append("		 			,0 AS BS_SELLSUM											                    ");
		sql.append("		 			,0 AS BS_SELLSUM_L										                    ");
		sql.append("		 		FROM NICK_K_TJIN_Y_ALL K  , NICK_TJYY_CIGARETTE	A,               ");
		sql.append("		 			CIG_PROPERTY_DIV C     , DIM_V_PRODUCE_ORG S					                    ");
		sql.append("		 		WHERE 1=1																                    ");
		sql.append("		 			AND K.C_BRAND = C.C_BRAND 																								                                                                                                    ");   
		sql.append("		 			AND A.CIG_PRODUCER=S.PRODUCE_CODE																										                                                                                                    ");   
		sql.append("		 			AND A.CIG_CODEBAR = K.C_BRAND																										                                                                                                    ");   
		sql.append("		 			AND A.CIG_BARCARRIER='02'																                                                ");
		sql.append("		 			AND ((D_YEAR<='2017' AND IS_NRT='1' AND A.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND A.is_innx='1'))                        ");
		sql.append("		 			AND FACT_POP_CODE='").append(code).append("'                                                                            ");
		sql.append("		 			AND K.D_YEAR=TO_CHAR(CURRENT DATE - 1 DAYS,'YYYY')                                               ");
		sql.append("		 		GROUP BY C.CIG_MARKNAME,C.C_CIG                                                                           ");
		sql.append("		 		WITH ROLLUP                                                                                               ");   
		sql.append("		 		HAVING (C.CIG_MARKNAME IS NULL AND C.C_CIG IS NULL) OR (C.CIG_MARKNAME IS NOT NULL AND C.C_CIG IS NOT NULL)	                  ");   
		sql.append("		 	UNION ALL                                                                                                   ");   
		sql.append("		 	SELECT 																											                                                 ");   
		sql.append("		 			NVL(C.CIG_MARKNAME, '合  计') AS CIG_MARKNAME							                                                 ");   
		sql.append("		 			,C.C_CIG AS C_CIG																				                                                 ");   
		sql.append("		 			,0 AS PRODUCENUM                                                                                        ");   
		sql.append("		 			,0 AS PRODUCENUM_L																			                                                 ");   
		sql.append("		 			,0 AS TERMSTOCK1																				                                                 ");   
		sql.append("		 			,0 AS TERMSTOCK1_L																			                                                 ");   
		sql.append("		 			,SUM(PRINT_NUM1)-SUM(RBACK_NUM1) AS BS_SELLNUM   				                                                 ");   
		sql.append("		 			,SUM(PRINT_NUM1_P)-SUM(RBACK_NUM1_P) AS BS_SELLNUM_L		                                                 ");   
		sql.append("		 			,SUM(TERM_STOCK1) AS TERM_STOCK1 												                                                 ");   
		sql.append("		 			,SUM(TERM_STOCK1_P) AS TERM_STOCK1_L										                                                 ");   
		sql.append("		 			,SUM(PRINT_SUM)-SUM(RBACK_SUM) AS BS_SELLSUM						                                                 ");   
		sql.append("		 			,SUM(PRINT_SUM_P)-SUM(RBACK_SUM_P) AS BS_SELLSUM_L			                                                 ");   
		sql.append("		 		FROM    NICK_K_TJBS_Y_ALL K,NICK_TJYY_CIGARETTE A, CIG_PROPERTY_DIV C, DIM_V_PRODUCE_ORG S  	                             ");
		sql.append("		 			WHERE 1=1 																												                                                   ");   
		sql.append("		 			AND K.C_BRAND = C.C_BRAND                            ");   
		sql.append("		 			AND A.CIG_PRODUCER=S.PRODUCE_CODE                         ");   
		sql.append("		 			AND A.CIG_CODEBAR = K.C_BRAND       AND K.C_CLASS<>'06'                 ");   
		sql.append("		 			AND A.CIG_BARCARRIER='02'															                                                     ");   
		sql.append("		 			and ((D_YEAR<='2017' AND A.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND A.is_bsnx='1'))                                                ");   
		sql.append("		 			AND FACT_POP_CODE='").append(code).append("'                                                                                ");   
		sql.append("					AND K.D_YEAR=TO_CHAR(CURRENT DATE - 1 DAYS,'YYYY')			                                                     ");   
		sql.append("		 		GROUP BY C.CIG_MARKNAME,C.C_CIG														                                                     ");   
		sql.append("		 		WITH ROLLUP																								                                                     ");   
		sql.append("		 		HAVING (C.CIG_MARKNAME IS NULL AND C.C_CIG IS NULL) OR (C.CIG_MARKNAME IS NOT NULL AND C.C_CIG IS NOT NULL )																	                                                                                                    ");   
		sql.append("		 	)																						             ");   
		sql.append("		 	GROUP BY CIG_MARKNAME,C_CIG									             ");   
		sql.append("		 	HAVING																			             ");   
		sql.append("		 	(																						             ");   
		sql.append("		 		SUM(PRODUCENUM)         <> 0							             ");   
		sql.append("		 		OR SUM(PRODUCENUM_L)    <> 0							             ");   
		sql.append("		 		OR SUM(TERMSTOCK1)      <> 0							             ");   
		sql.append("		 		OR SUM(TERMSTOCK1_L)    <> 0							             ");   
		sql.append("		 		OR SUM(BS_SELLNUM)      <> 0							             ");   
		sql.append("		 		OR SUM(BS_SELLNUM_L)    <> 0							             ");   
		sql.append("		 		OR SUM(TERM_STOCK1)     <> 0							             ");   
		sql.append("		 		OR SUM(TERM_STOCK1_L)   <> 0							             ");   
		sql.append("		 		OR SUM(BS_SELLSUM)      <> 0							             ");   
		sql.append("		 		OR SUM(BS_SELLSUM_L)    <> 0							             ");   
		sql.append("		 	)																						             ");   
		sql.append("		 	ORDER BY SUM(PRODUCENUM) DESC								             ");   
		sql.append("		 	)																						             ");   
		sql.append("		 	ORDER BY DECODE(CIG_MARKNAME,'合  计',0,1) ASC				                          ");   
		sql.append("		) where CIG_NAME <> '福样促试' 	WITH UR									           ");   

		log.info("查询品牌目录产品信息中烟表格信息sql："+sql);
		return sql.toString();
	}
	public List<ProductInTableVO> getProductInTableInSmoke(String dbName,String code){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<ProductInTableVO> productInSpecList = new ArrayList<ProductInTableVO>();
		try {
			sql = getSqlProductInSmoke(code);
			productInSpecList = dbbean.executeQuery(sql, ProductInTableVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询品牌目录产品信息中烟表格信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询品牌目录产品信息中烟表格信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return productInSpecList;
	}
	
	public static String getSqlProductInSmokeBoxCode(String code){
		StringBuffer sql = new StringBuffer();       
		sql.append("SELECT     ");
		sql.append("	CIG_CODEBAR as SORT_NO		  ");
		sql.append("	,CIG_INNERBARCODE as xz_parameter		   ");
		sql.append("FROM NICK_STMA_CIGARETTE ");
		sql.append("WHERE CIG_CODEBAR = '").append(code).append("'   ");
		sql.append("WITH UR    ");		
		log.info("查询品牌目录产品信息中烟表格信息sql："+sql);
		return sql.toString();
	}
	public ProductInTableVO getProductInTableInSmokeBoxCode(String dbName,String code){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		ProductInTableVO productInSpecList = new ProductInTableVO();
		try {
			sql = getSqlProductInSmokeBoxCode(code);
			productInSpecList = (ProductInTableVO)dbbean.executeQuerySingle(sql, ProductInTableVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询品牌目录产品信息中烟表格信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询品牌目录产品信息中烟表格信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return productInSpecList;
	}


	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<ProductInTableVO> productInList=null;
		
		Gson gson = new Gson();
		String type=request.getParameter("type");								//获取下钻的类型
		String code=request.getParameter("code");								//获取编码
		if (type.equals("first") && code.equals("00000000")) {//页面去掉<全国>之后这个if判断不会再走，留着预防后期客户需要改回来
			productInList = getProductInTable("dw");
		}else if (type.equals("gg")) {
			String factCode = request.getParameter("factCode");	
			log.info("factCode========================= :"+factCode);
			productInList = getProductInTableSpec("dw",code,factCode);		
		}else if(type.equals("tp")){
			ProductInTableVO inTableVO=new ProductInTableVO();
			inTableVO=getProductInTableInSmokeBoxCode("dw",code);
			productInList=new ArrayList<ProductInTableVO>();
			productInList.add(inTableVO);
		}else {
			productInList=getProductInTableInSmoke("dw",code);
		}
		
		String s = gson.toJson(productInList);
		log.info("品牌目录产品信息业务查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	

}
