package com.icss.tjfx.popcatalog.productlayout.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.popcatalog.productlayout.vo.ProductLayCharsVO;
import com.icss.tjfx.popcatalog.productlayout.vo.ProductLayTableVO;

public class ProductLayOutCharsQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(ProductLayOutCharsQuery.class);
	
	/**
	 * 获取查询品牌目录产品布局图表信息数据的sql
	 * @return 
	 */
	public static String getSqlProductLayChars(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT               ");
		sql.append("	PRICE_AREA  AS MDC_TITLE       ");
		sql.append("	,NVL(BS_SELLNUM, 	0) AS BS_SELLNUM                           ");
		sql.append("	,NVL(COUNT_C_BRAND, 0) AS COUNT_C_BRAND                      ");
		sql.append("FROM              ");
		sql.append("(                 ");
		sql.append("	SELECT          ");
		sql.append("		(CASE         ");
		sql.append("			WHEN PRICE < 30 						THEN 1                       ");
		sql.append("			WHEN PRICE >= 30 	AND PRICE < 50		THEN 2               ");
		sql.append("			WHEN PRICE >= 50 	AND PRICE < 70		THEN 3               ");
		sql.append("			WHEN PRICE >= 70 	AND PRICE < 90		THEN 4               ");
		sql.append("			WHEN PRICE >= 90 	AND PRICE < 110		THEN 5               ");
		sql.append("			WHEN PRICE >= 110	AND PRICE < 130		THEN 6               ");
		sql.append("			WHEN PRICE >= 130 	AND PRICE < 160		THEN 7             ");
		sql.append("			WHEN PRICE >= 160 	AND PRICE < 200		THEN 8             ");
		sql.append("			WHEN PRICE >= 200 	AND PRICE < 260		THEN 9             ");
		sql.append("			WHEN PRICE >= 260 	AND PRICE < 400		THEN 10            ");
		sql.append("			WHEN PRICE >= 400 	AND PRICE < 600		THEN 11            ");
		sql.append("			WHEN PRICE >= 600						THEN 12                      ");
		sql.append("		ELSE 0                                                     ");
		sql.append("		END) AS SORT_NO                                            ");
		sql.append("		,DECIMAL(ROUND(SUM(SELL_NUM)*1.0000/50000,2),16,2) AS BS_SELLNUM          ");
		sql.append("		,COUNT(C_BRAND)	AS COUNT_C_BRAND                    ");
		sql.append("	FROM                                                  ");
		sql.append("	(		                                                  ");
		sql.append("		SELECT                                              ");
		sql.append("			C_BRAND                                           ");
		sql.append("			,SUM(PRINT_NUM1 - RBACK_NUM1)	AS SELL_NUM         ");
		sql.append("			,SUM(PRINT_SUM - RBACK_SUM)		AS SELL_SUM         ");
		sql.append("			,DECODE(SUM(PRINT_NUM1 - RBACK_NUM1), 0, 0, SUM(PRINT_SUM - RBACK_SUM)*200.0000/SUM(PRINT_NUM1 - RBACK_NUM1)) AS PRICE    ");
		sql.append("		FROM NICK_K_TJBS_Y_ALL                               ");
		sql.append("		WHERE 1=1                                            ");
		sql.append("		AND C_CLASS <> '06'                                  ");
		sql.append("		AND Y = YEAR(CURRENT DATE-1 DAYS)                    ");
		sql.append("		GROUP BY C_BRAND                                     ");
		sql.append("		HAVING(SUM(PRINT_NUM1 - RBACK_NUM1) <> 0)            ");
		sql.append("	)                                                      ");
		sql.append("	GROUP BY                                               ");
		sql.append("		(CASE                                                ");
		sql.append("			WHEN PRICE < 30 						THEN 1                 ");
		sql.append("			WHEN PRICE >= 30 	AND PRICE < 50		THEN 2         ");
		sql.append("			WHEN PRICE >= 50 	AND PRICE < 70		THEN 3         ");
		sql.append("			WHEN PRICE >= 70 	AND PRICE < 90		THEN 4         ");
		sql.append("			WHEN PRICE >= 90 	AND PRICE < 110		THEN 5         ");
		sql.append("			WHEN PRICE >= 110	AND PRICE < 130		THEN 6         ");
		sql.append("			WHEN PRICE >= 130 	AND PRICE < 160		THEN 7       ");
		sql.append("			WHEN PRICE >= 160 	AND PRICE < 200		THEN 8       ");
		sql.append("			WHEN PRICE >= 200 	AND PRICE < 260		THEN 9       ");
		sql.append("			WHEN PRICE >= 260 	AND PRICE < 400		THEN 10      ");
		sql.append("			WHEN PRICE >= 400 	AND PRICE < 600		THEN 11      ");
		sql.append("			WHEN PRICE >= 600						THEN 12                ");
		sql.append("		ELSE 0                                               ");
		sql.append("		END)                                                 ");
		sql.append(") A                                                      ");
		sql.append("RIGHT JOIN                                               ");
		sql.append("(                                                        ");
		sql.append("	SELECT 1  AS SORT_NO, '30元以下'     AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL             ");
		sql.append("	SELECT 2  AS SORT_NO, '[30-50元)'    AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 3  AS SORT_NO, '[50-70元)'    AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 4  AS SORT_NO, '[70-90元)'    AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 5  AS SORT_NO, '[90-110元)'   AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 6  AS SORT_NO, '[110-130元)'  AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 7  AS SORT_NO, '[130-160元)'	 AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL          ");
		sql.append("	SELECT 8  AS SORT_NO, '[160-200元)'  AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 9  AS SORT_NO, '[200-260元)'  AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 10 AS SORT_NO, '[260-400元)'	 AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL          ");
		sql.append("	SELECT 11 AS SORT_NO, '[400-600元)'  AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 12 AS SORT_NO, '600以上' 	 AS PRICE_AREA FROM SYSIBM.SYSDUMMY1                        ");
		sql.append(") B                                                       ");
		sql.append("	ON A.SORT_NO = B.SORT_NO                                ");
		sql.append("ORDER BY B.SORT_NO    DESC                                    ");
		sql.append("WITH UR                                                   ");                                                                                   
		log.info("查询品牌目录产品布局图表信息Sql为："+sql);
		return sql.toString();
	}

	public List<ProductLayCharsVO> getProductLayChars(String dbName){
		DBBeanBase dbbean = new DBBeanBase("dw");
		String sql = "";
		List<ProductLayCharsVO> ProductLayList = new ArrayList<ProductLayCharsVO>();
		try {
			sql = getSqlProductLayChars();
			ProductLayList = dbbean.executeQuery(sql, ProductLayCharsVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询品牌目录品牌目录产品布局图表信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询品牌目录品牌目录产品布局图表信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return ProductLayList;
	}
	
	/**
	 * 获取查询品牌目录产品布局图表信息分中烟数据的sql
	 * @return 
	 */
	public static String getSqlProductLayCharsZY(String code){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT                                                    ");
		sql.append("	PRICE_AREA   AS MDC_TITLE                                           ");
		sql.append("	,NVL(BS_SELLNUM, 	0) AS BS_SELLNUM                      ");
		sql.append("	,NVL(COUNT_C_BRAND, 0) AS COUNT_C_BRAND                 ");
		sql.append("FROM                                                      ");
		sql.append("(                                                         ");
		sql.append("	SELECT                                                  ");
		sql.append("		(CASE                                                 ");
		sql.append("			WHEN PRICE < 30 						THEN 1                  ");
		sql.append("			WHEN PRICE >= 30 	AND PRICE < 50		THEN 2          ");
		sql.append("			WHEN PRICE >= 50 	AND PRICE < 70		THEN 3          ");
		sql.append("			WHEN PRICE >= 70 	AND PRICE < 90		THEN 4          ");
		sql.append("			WHEN PRICE >= 90 	AND PRICE < 110		THEN 5          ");
		sql.append("			WHEN PRICE >= 110	AND PRICE < 130		THEN 6          ");
		sql.append("			WHEN PRICE >= 130 	AND PRICE < 160		THEN 7        ");
		sql.append("			WHEN PRICE >= 160 	AND PRICE < 200		THEN 8        ");
		sql.append("			WHEN PRICE >= 200 	AND PRICE < 260		THEN 9        ");
		sql.append("			WHEN PRICE >= 260 	AND PRICE < 400		THEN 10       ");
		sql.append("			WHEN PRICE >= 400 	AND PRICE < 600		THEN 11       ");
		sql.append("			WHEN PRICE >= 600						THEN 12                 ");
		sql.append("		ELSE 0                                                ");
		sql.append("		END) AS SORT_NO                                       ");
		sql.append("		,DECIMAL(ROUND(SUM(SELL_NUM)*1.0000/50000,2),16,2) AS BS_SELLNUM                          ");
		sql.append("		,COUNT(C_BRAND)	AS COUNT_C_BRAND                  ");
		sql.append("	FROM                                                ");
		sql.append("	(		                                                ");
		sql.append("		SELECT                                            ");
		sql.append("			C_BRAND                                         ");
		sql.append("			,SUM(PRINT_NUM1 - RBACK_NUM1)	AS SELL_NUM       ");
		sql.append("			,SUM(PRINT_SUM - RBACK_SUM)		AS SELL_SUM       ");
		sql.append("			,DECODE(SUM(PRINT_NUM1 - RBACK_NUM1), 0, 0, SUM(PRINT_SUM - RBACK_SUM)*200.0000/SUM(PRINT_NUM1 - RBACK_NUM1)) AS PRICE    ");
		sql.append("		FROM NICK_K_TJBS_Y_ALL     NK,NICK_TJYY_CIGARETTE SC         ");
		sql.append("		WHERE 1=1                                           ");
		sql.append("		AND NK.C_BRAND = SC.cig_codebar               ");
		sql.append("		and ((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))  AND Y = YEAR(CURRENT DATE-1 DAYS)     ");
		sql.append("		GROUP BY C_BRAND                                    ");
		sql.append("		HAVING(SUM(PRINT_NUM1 - RBACK_NUM1) <> 0)           ");
		sql.append("	) K                                                   ");
		sql.append("	INNER JOIN (SELECT C_BRAND AS BRAND_CODE FROM NICK_VIEW_CIG_PROPERTY WHERE FACT_POPEDOM = '").append(code).append("') P	       ");
		sql.append("		ON K.C_BRAND = P.BRAND_CODE                          ");
		sql.append("	GROUP BY                                               ");
		sql.append("		(CASE                                                ");
		sql.append("			WHEN PRICE < 30 						THEN 1                 ");
		sql.append("			WHEN PRICE >= 30 	AND PRICE < 50		THEN 2         ");
		sql.append("			WHEN PRICE >= 50 	AND PRICE < 70		THEN 3         ");
		sql.append("			WHEN PRICE >= 70 	AND PRICE < 90		THEN 4         ");
		sql.append("			WHEN PRICE >= 90 	AND PRICE < 110		THEN 5         ");
		sql.append("			WHEN PRICE >= 110	AND PRICE < 130		THEN 6         ");
		sql.append("			WHEN PRICE >= 130 	AND PRICE < 160		THEN 7       ");
		sql.append("			WHEN PRICE >= 160 	AND PRICE < 200		THEN 8       ");
		sql.append("			WHEN PRICE >= 200 	AND PRICE < 260		THEN 9       ");
		sql.append("			WHEN PRICE >= 260 	AND PRICE < 400		THEN 10      ");
		sql.append("			WHEN PRICE >= 400 	AND PRICE < 600		THEN 11      ");
		sql.append("			WHEN PRICE >= 600						THEN 12                ");
		sql.append("		ELSE 0                                               ");
		sql.append("		END)                                                 ");
		sql.append(") A                                                      ");
		sql.append("RIGHT JOIN                                               ");
		sql.append("(                                                        ");
		sql.append("	SELECT 1  AS SORT_NO, '30元以下'     AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL             ");
		sql.append("	SELECT 2  AS SORT_NO, '[30-50元)'    AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 3  AS SORT_NO, '[50-70元)'    AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 4  AS SORT_NO, '[70-90元)'    AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 5  AS SORT_NO, '[90-110元)'   AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 6  AS SORT_NO, '[110-130元)'  AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 7  AS SORT_NO, '[130-160元)'	 AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL          ");
		sql.append("	SELECT 8  AS SORT_NO, '[160-200元)'  AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 9  AS SORT_NO, '[200-260元)'  AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 10 AS SORT_NO, '[260-400元)'	 AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL          ");
		sql.append("	SELECT 11 AS SORT_NO, '[400-600元)'  AS PRICE_AREA FROM SYSIBM.SYSDUMMY1 UNION ALL           ");
		sql.append("	SELECT 12 AS SORT_NO, '600以上' 	 AS PRICE_AREA FROM SYSIBM.SYSDUMMY1                        ");
		sql.append(") B                                ");
		sql.append("	ON A.SORT_NO = B.SORT_NO         ");
		sql.append("ORDER BY B.SORT_NO  DESC             ");
		sql.append("WITH UR                            ");                                                                      
		log.info("查询品牌目录产品布局分中烟图表信息Sql为："+sql);
		return sql.toString();
	}

	public List<ProductLayCharsVO> getProductLayCharsZY(String dbName,String code){
		DBBeanBase dbbean = new DBBeanBase("dw");
		String sql = "";
		List<ProductLayCharsVO> ProductLayList = new ArrayList<ProductLayCharsVO>();
		try {
			sql = getSqlProductLayCharsZY(code);
			ProductLayList = dbbean.executeQuery(sql, ProductLayCharsVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询品牌目录品牌目录产品布局图表分中烟信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询品牌目录品牌目录产品布局图表分中烟信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return ProductLayList;
	}
	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		
		List<ProductLayCharsVO> ProductLayList=null;
		String type=request.getParameter("type");								//获取下钻的类型
		String code=request.getParameter("code");								//获取编码
		Gson gson = new Gson();
		if (type.equals("first") && code.equals("00000000")) {
			ProductLayList = getProductLayChars("dw");
		}else {
			ProductLayList = getProductLayCharsZY("dw",code);
		}
		String s = gson.toJson(ProductLayList);
		log.info("品牌目录产品布局图表信息业务查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	

}
