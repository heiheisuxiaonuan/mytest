package com.icss.tjfx.innoproduct.productzz.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.util.StringUtil;
import com.icss.tjfx.innoproduct.productdz.bussiness.InnoProductDzTable;
import com.icss.tjfx.innoproduct.vo.InNoProductTableVO;

/**
 * @author yourname
 * @date 2018-7-10 上午11:27:08
 * 
 */
public class InnoProductZzTable extends BaseBusiness{


	private static Log log = LogFactory.getLog(InnoProductDzTable.class);

	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		DBBeanBase dbbean = new DBBeanBase("dw");
		log.info("传入参数： dbName:" + "dw");
		String sql1 = "";
		List<InNoProductTableVO> inNoProductTableVOlist = new ArrayList<InNoProductTableVO>();
		try {
			String type=request.getParameter("type");								//获取下钻的类型
			String code=request.getParameter("code");								//获取编码
			if (type.equals("gg")) {
				sql1 = getTableGGSql(code);	
				inNoProductTableVOlist = dbbean.executeQuery(sql1,InNoProductTableVO.class.getName());
				log.info("创新产品下钻SQL" + sql1);
			}else {
				inNoProductTableVOlist=getInNoProductTable();
			}
			
		} catch (Exception e) {
			log.info("*************************");
			log.info("创新产品sql执行异常:" + e);
			log.info("*************************");
			log.error("创新产品sql执行异常", e);
		} finally {
			dbbean.close();
		}
		
		String result = StringUtil.getJson(inNoProductTableVOlist);
		log.info("创新产品查询结果" + StringUtil.getJson(inNoProductTableVOlist));

		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", result);

		return request;
	}
	
	public List<InNoProductTableVO> getInNoProductTable(){
		DBBeanBase dbbean = new DBBeanBase("dw");
		log.info("传入参数： dbName:" + "dw");
		String sql1 = "";
		List<InNoProductTableVO> inNoProductTableVOlist = new ArrayList<InNoProductTableVO>();
		try {
				sql1 = getTableSql();
				inNoProductTableVOlist = dbbean.executeQuery(sql1,InNoProductTableVO.class.getName());
				log.info("创新产品SQL" + sql1);
			
		} catch (Exception e) {
			log.info("*************************");
			log.info("创新产品sql执行异常:" + e);
			log.info("*************************");
			log.error("创新产品sql执行异常", e);
		} finally {
			dbbean.close();
		}
		return inNoProductTableVOlist;
	}
	

	private String getTableGGSql(String code) {
		StringBuffer sql = new StringBuffer();
		/*sql.append(" SELECT   ");
		sql.append(" 	BRAND_NAME 	AS CIG_NAME  ");
		sql.append(" 	,CASE WHEN PRODUCENUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM       ");                                                                         	//产量
		sql.append(" 	,CASE WHEN PRODUCENUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM_L         ");                                                                     	//同期产量
		sql.append(" 	,CASE WHEN (PRODUCENUM+PRODUCENUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM_GROWTH       ");                                                                  	//产量增量
		sql.append(" 	,CASE WHEN (PRODUCENUM = 0 OR PRODUCENUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)*100.0000/DECODE(PRODUCENUM_L,0,1,PRODUCENUM_L), 2), 16, 2)) END AS PRODUCENUM_GROWTH_RATE     ");                         	//产量增率
		sql.append(" 	,CASE WHEN BS_SELLNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM        ");                                                                        	//销量
		sql.append(" 	,CASE WHEN BS_SELLNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM_L      ");                                                                        	//同期销量
		sql.append(" 	,CASE WHEN (BS_SELLNUM+BS_SELLNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM_GROWTH         ");                                                                	//销量增量
		sql.append(" 	,CASE WHEN (BS_SELLNUM = 0 OR BS_SELLNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)*100.0000/DECODE(BS_SELLNUM_L,0,1,BS_SELLNUM_L), 2), 16, 2)) END AS BS_SELLNUM_GROWTH_RATE     ");                         	//销量增率
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IB_TERMSTOCKNUM		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM      ");                                                                             	//工商库存
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IB_TERMSTOCKNUM_L		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM_L        ");                                                                    	//同期工商库存
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM+IB_TERMSTOCKNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM_GROWTH    ");                                                    	//工商库存增量
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM = 0 OR IB_TERMSTOCKNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)*100.0000/DECODE(IB_TERMSTOCKNUM_L,0,1,IB_TERMSTOCKNUM_L), 2), 16, 2)) END AS IB_TERMSTOCKNUM_GROWTH_RATE	  ");  //工商库存增率
		sql.append(" 	,CASE WHEN BS_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM      ");                                                                          	//销额
		sql.append(" 	,CASE WHEN BS_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM_L     ");                                                                         	//同期销额
		sql.append(" 	,CASE WHEN (BS_SELLSUM+BS_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM_GROWTH          ");                                                               	//销额增量
		sql.append(" 	,CASE WHEN (BS_SELLSUM = 0 OR BS_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*100.0000/DECODE(BS_SELLSUM_L,0,1,BS_SELLSUM_L), 2), 16, 2)) END AS BS_SELLSUM_GROWTH_RATE    ");                          	//销额增率
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM     ");                                                                          	//单箱
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_L          ");                                                                    	//同期单箱
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_GROWTH              ");                                                               	//单箱增量
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS SINGLE_SELLSUM_GROWTH_RATE    ");          	//单箱增率
		sql.append(" 	,NVL(DECODE(C.CIG_PRODUCER,'10000001','国家局',O.PRODUCE_SHORTNAME), '-') AS PRODUCER_NAME     ");          //所有者
		sql.append(" 	,NVL(N.CLASS_NAME, '-') AS CLASS_NAME       ");                    //价类
		sql.append(" 	,NVL(CHAR(TRANSFERPRICE), '-') AS TRANSFERPRICE    ");                       //调拨价
		sql.append(" 	,NVL(CHAR(WHOLESALEPRICE), '-') AS WHOLESALEPRICE             ");            //批发价
		sql.append(" 	,NVL(CHAR(SUGGESTEDRETAILPRICE), '-') AS SUGGESTEDRETAILPRICE     ");        //零售价

		sql.append("  FROM   ");
				sql.append("  (   ");
				sql.append("  	SELECT    ");
				sql.append("  		C_BRAND                    						AS C_BRAND   ");
				sql.append("  	    ,BRAND_NAME                 					AS BRAND_NAME   ");
				sql.append("  	    ,SUM(PRODUCENUM               				)	AS PRODUCENUM   ");
				sql.append("  	    ,SUM(PRODUCENUM_L             				)	AS PRODUCENUM_L   ");
				sql.append("  	    ,SUM(BS_SELLNUM               				)	AS BS_SELLNUM   ");
				sql.append("  	    ,SUM(BS_SELLNUM_L             				)	AS BS_SELLNUM_L   ");
				sql.append("  	    ,SUM(BS_SELLSUM               				)	AS BS_SELLSUM   ");
				sql.append("  	    ,SUM(BS_SELLSUM_L             				)	AS BS_SELLSUM_L   ");
				sql.append("  	    ,SUM(SINGLE_SELLSUM           				)	AS SINGLE_SELLSUM   ");
				sql.append("  	    ,SUM(SINGLE_SELLSUM_L         				)	AS SINGLE_SELLSUM_L   ");
				sql.append("  	    ,SUM(BS_TERMSTOCKNUM 	+ IN_TERMSTOCKNUM   )   AS IB_TERMSTOCKNUM   ");
				sql.append("  	    ,SUM(BS_TERMSTOCKNUM_L	+ IN_TERMSTOCKNUM_L )   AS IB_TERMSTOCKNUM_L   ");
				sql.append("  	FROM   ");
				 	//工业   
				sql.append("  	(  "); 
				sql.append("  		SELECT  "); 
				sql.append("  			NVL(P.C_BRAND , '00')			AS C_BRAND  "); 
				sql.append("  			,NVL(P.CIG_NAME, '合  计') 		AS BRAND_NAME  "); 
				sql.append("  			,SUM(PRODUCENUM)			 	AS PRODUCENUM  "); 
				sql.append("  			,SUM(PRODUCENUM_L)			 	AS PRODUCENUM_L  "); 
				sql.append("  			,SUM(IN_TERMSTOCKNUM)		 	AS IN_TERMSTOCKNUM  "); 
				sql.append("  			,SUM(IN_TERMSTOCKNUM_L)		 	AS IN_TERMSTOCKNUM_L  "); 
				sql.append("  			,0                              AS BS_SELLNUM  "); 
				sql.append("  			,0                              AS BS_SELLNUM_L  "); 
				sql.append("  			,0                              AS BS_SELLSUM  "); 
				sql.append("  			,0                              AS BS_SELLSUM_L  "); 
				sql.append("  			,0                              AS SINGLE_SELLSUM  "); 
				sql.append("  			,0                              AS SINGLE_SELLSUM_L  "); 
				sql.append("  			,0                              AS BS_TERMSTOCKNUM  "); 
				sql.append("  			,0                              AS BS_TERMSTOCKNUM_L  "); 
				sql.append("  		FROM  "); 
				sql.append("  			(  "); 
				sql.append("  				SELECT  "); 
				sql.append("  					C_BRAND  "); 
				sql.append("  					,SUM(PRINTNUM1 - REJECTNUM1 + REPEATNUM1 - JUMPNUM1) 		AS PRODUCENUM  "); 
				sql.append("  					,SUM(PRINTNUM1_P - REJECTNUM1_P + REPEATNUM1_P - JUMPNUM1_P) 	AS PRODUCENUM_L  "); 
				sql.append("  					,SUM(TERMSTOCK1 	) 														AS IN_TERMSTOCKNUM  "); 
				sql.append("  					,SUM(TERMSTOCK1_P 	) 														AS IN_TERMSTOCKNUM_L  "); 
				sql.append("  				FROM NICK_K_TJIN_Y_ALL   "); 
				sql.append("  				WHERE 1=1   "); 
				sql.append("  				AND IS_NRT = '1'  "); 
				sql.append("  				AND C_CLASS <> '06'  "); 
				sql.append("  				AND Y = YEAR(CURRENT DATE-1 DAYS)  "); 
				sql.append("  				GROUP BY C_BRAND  "); 
				sql.append("  			) K  "); 
				sql.append("  			,(SELECT C_BRAND,CIG_NAME FROM CIG_PROPERTY_DIV WHERE C_CIG = '").append(code).append("') P	  "); 	//参数
				sql.append(" 	,(							");
				sql.append(" 	SELECT 							");
				sql.append(" 	DISTINCT CIG_CODEBAR					");
				sql.append(" 	FROM NICK_STMA_CIGARETTE				");
				sql.append(" 	WHERE 1=1 						");
				sql.append(" 	AND CIG_BARCARRIER = '02' 				");
				sql.append(" 	AND CIG_GIRTH > 18 and CIG_GIRTH < 24  AND CIG_PRODUCTTYPE NOT IN('05', '06') AND CIG_IMPORTFLAG in ('0','3')		");
				sql.append(" 	) C							");
				sql.append("  			WHERE 1=1   "); 
				sql.append("  				AND K.C_BRAND = P.C_BRAND   "); 
				sql.append("  				AND K.C_BRAND = C.CIG_CODEBAR  "); 
				sql.append("  			GROUP BY P.C_BRAND, P.CIG_NAME  "); 
				sql.append("  			WITH ROLLUP  "); 
				sql.append("  			HAVING  "); 
				sql.append("  				(  "); 
				sql.append("  					(P.C_BRAND IS NULL AND P.CIG_NAME IS NULL)  "); 
				sql.append("  					OR (P.C_BRAND IS NOT NULL AND P.CIG_NAME IS NOT NULL)  "); 
				sql.append("  				)  "); 
				sql.append("  		UNION ALL  "); 
					//商业
				sql.append("  		SELECT  "); 
				sql.append("  			NVL(P.C_BRAND , '00')			AS C_BRAND  "); 
				sql.append("  			,NVL(P.CIG_NAME, '合  计') 		AS BRAND_NAME  "); 
				sql.append("  			,0                              AS PRODUCENUM  "); 
				sql.append("  			,0                              AS PRODUCENUM_L  "); 
				sql.append("  			,0                              AS IN_TERMSTOCKNUM  "); 
				sql.append("  			,0                              AS IN_TERMSTOCKNUM_L  "); 
				sql.append("  			,SUM(BS_SELLNUM)			 	AS BS_SELLNUM  "); 
				sql.append("  			,SUM(BS_SELLNUM_L)			 	AS BS_SELLNUM_L  "); 
				sql.append("  			,SUM(BS_SELLSUM)			 	AS BS_SELLSUM  "); 
				sql.append("  			,SUM(BS_SELLSUM_L)			 	AS BS_SELLSUM_L  "); 
				sql.append("  			,DECODE(SUM(BS_SELLNUM	),0,0,NULL,0,SUM(BS_SELLSUM		)*5.0000/SUM(BS_SELLNUM		)) 	AS SINGLE_SELLSUM  "); 
				sql.append("  			,DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L	)*5.0000/SUM(BS_SELLNUM_L	)) 	AS SINGLE_SELLSUM_L  "); 
				sql.append("  			,SUM(BS_TERMSTOCKNUM)		 	AS BS_TERMSTOCKNUM  "); 
				sql.append("  			,SUM(BS_TERMSTOCKNUM_L)		 	AS BS_TERMSTOCKNUM_L  "); 
				sql.append("  		FROM  "); 
				sql.append("  			(  "); 
				sql.append("  				SELECT  "); 
				sql.append("  					C_BRAND  "); 
				sql.append("  					,SUM(PRINT_NUM1 	- RBACK_NUM1	) 			AS BS_SELLNUM  "); 
				sql.append("  					,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	) 			AS BS_SELLNUM_L  "); 
				sql.append("  					,SUM(TERM_STOCK1 							) 			AS BS_TERMSTOCKNUM  "); 
				sql.append("  					,SUM(TERM_STOCK1_P 							) 			AS BS_TERMSTOCKNUM_L  "); 
				sql.append("  					,SUM(PRINT_SUM		- RBACK_SUM		) 			AS BS_SELLSUM  "); 
				sql.append("  					,SUM(PRINT_SUM_P 	- RBACK_SUM_P	) 			AS BS_SELLSUM_L  "); 
				sql.append("  				FROM NICK_K_TJBS_Y_ALL   "); 
				sql.append("  				WHERE 1=1  "); 
				sql.append("  					AND C_CLASS <> '06'  "); 
				sql.append("  					AND Y = YEAR(CURRENT DATE-1 DAYS)  "); 
				sql.append("  					GROUP BY C_BRAND  "); 
				sql.append("  			) K "); 
				sql.append("  			,(SELECT C_BRAND,CIG_NAME FROM CIG_PROPERTY_DIV WHERE C_CIG = '").append(code).append("') P	  "); 	//参数
				sql.append(" 	,(							");
				sql.append(" 	SELECT 							");
				sql.append(" 	DISTINCT CIG_CODEBAR					");
				sql.append(" 	FROM NICK_STMA_CIGARETTE				");
				sql.append(" 	WHERE 1=1 						");
				sql.append(" 	AND CIG_BARCARRIER = '02' 				");
				sql.append(" 	AND CIG_GIRTH > 18 and CIG_GIRTH < 24	 AND CIG_PRODUCTTYPE NOT IN('05', '06')  AND CIG_IMPORTFLAG in ('0','3')		");
				sql.append(" 	) C							");
				sql.append("  			WHERE 1=1   "); 
				sql.append("  				AND K.C_BRAND = P.C_BRAND   "); 
				sql.append("  				AND K.C_BRAND = C.CIG_CODEBAR  "); 
				sql.append("  			GROUP BY P.C_BRAND, P.CIG_NAME  "); 
				sql.append("  			WITH ROLLUP  "); 
				sql.append("  			HAVING  "); 
				sql.append("  				(  "); 
				sql.append("  					(P.C_BRAND IS NULL AND P.CIG_NAME IS NULL)  "); 
				sql.append("  					OR (P.C_BRAND IS NOT NULL AND P.CIG_NAME IS NOT NULL)  "); 
				sql.append("  				)  "); 
				sql.append("  	)  "); 
				sql.append("  	GROUP BY C_BRAND, BRAND_NAME   ");
				
				sql.append(" HAVING ");
				sql.append(" (   ");
						sql.append(" 	SUM(PRODUCENUM               					)    <> 0 ");
						sql.append(" 	OR SUM(PRODUCENUM_L             				)    <> 0 ");
						sql.append(" 	OR SUM(BS_SELLNUM               				)    <> 0 ");
						sql.append(" 	OR SUM(BS_SELLNUM_L             				)    <> 0 ");
						sql.append(" 	OR SUM(BS_SELLSUM               				)    <> 0 ");
						sql.append(" 	OR SUM(BS_SELLSUM_L             				)    <> 0 ");
						sql.append(" 	OR SUM(SINGLE_SELLSUM           				)    <> 0 ");
						sql.append(" 	OR SUM(SINGLE_SELLSUM_L         				)    <> 0 ");
				sql.append(" 	OR SUM(BS_TERMSTOCKNUM 		+ IN_TERMSTOCKNUM   )    <> 0 ");
				sql.append(" 	OR SUM(BS_TERMSTOCKNUM_L	+ IN_TERMSTOCKNUM_L )    <> 0 ");
				sql.append(" ) ");
				
				sql.append("  ) K   ");
				
				sql.append("  LEFT JOIN NICK_STMA_CIGARETTE C   ");
				sql.append("  	ON K.C_BRAND = C.CIG_CODEBAR   ");
				sql.append("	LEFT JOIN(SELECT DISTINCT PRODUCE_CODE, PRODUCE_SHORTNAME FROM DIM_PRODUCE_ORG) O ");
				sql.append("	ON C.CIG_PRODUCER = O.PRODUCE_CODE ");
				sql.append("	LEFT JOIN DW_T_CLASS_CODE N ");
				sql.append("	ON C.CIG_PRICETYPE = N.CODE ");
				sql.append("  LEFT JOIN (SELECT DISTINCT BAR, TRANSFERPRICE, WHOLESALEPRICE, SUGGESTEDRETAILPRICE FROM PRICELIST) P   ");
				sql.append("  	ON K.C_BRAND = P.BAR   ");
				sql.append("  ORDER BY DECODE(BRAND_NAME,'合  计',0,1) ASC, 2 DESC   ");
				sql.append("  WITH UR   ");*/
		
        sql.append(" SELECT CIG_NAME ,   ");
		sql.append(" PRODUCENUM ,   ");
		sql.append(" PRODUCENUM_L ,   ");
		sql.append(" PRODUCENUM_GROWTH ,   ");
		sql.append(" PRODUCENUM_GROWTH_RATE ,   ");
		sql.append(" BS_SELLNUM ,   ");
		sql.append(" BS_SELLNUM_L ,   ");
		sql.append(" BS_SELLNUM_GROWTH ,   ");
		sql.append(" BS_SELLNUM_GROWTH_RATE ,   ");
		sql.append(" IB_TERMSTOCKNUM ,   ");
		sql.append(" IB_TERMSTOCKNUM_L ,   ");
		sql.append(" IB_TERMSTOCKNUM_GROWTH ,   ");
		   sql.append(" IB_TERMSTOCKNUM_GROWTH_RATE,   ");
		   sql.append(" BS_SELLSUM ,   ");
		   sql.append(" BS_SELLSUM_L ,   ");
		   sql.append(" BS_SELLSUM_GROWTH ,   ");
		   sql.append(" BS_SELLSUM_GROWTH_RATE ,  ");
		   sql.append(" SINGLE_SELLSUM ,   ");
		   sql.append(" SINGLE_SELLSUM_L ,   ");
		   sql.append(" SINGLE_SELLSUM_GROWTH ,   ");
		   sql.append(" SINGLE_SELLSUM_GROWTH_RATE ,   ");
		   sql.append(" PRODUCER_NAME ,  ");
		   sql.append(" CLASS_NAME ,   ");
		   sql.append(" TRANSFERPRICE ,   ");
		   sql.append(" WHOLESALEPRICE ,   ");
		   sql.append(" SUGGESTEDRETAILPRICE   ");
		   sql.append(" FROM   ");
		   sql.append(" (   ");
		
		sql.append(" SELECT   ");
		sql.append(" 	BRAND_NAME 	AS CIG_NAME  ");
		sql.append(" 	,CASE WHEN PRODUCENUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM       ");                                                                         	//产量
		sql.append(" 	,CASE WHEN PRODUCENUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM_L         ");                                                                     	//同期产量
		sql.append(" 	,CASE WHEN (PRODUCENUM+PRODUCENUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM_GROWTH       ");                                                                  	//产量增量
		sql.append(" 	,CASE WHEN (PRODUCENUM = 0 OR PRODUCENUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)*100.0000/DECODE(PRODUCENUM_L,0,1,PRODUCENUM_L), 2), 16, 2)) END AS PRODUCENUM_GROWTH_RATE     ");                         	//产量增率
		sql.append(" 	,CASE WHEN BS_SELLNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM        ");                                                                        	//销量
		sql.append(" 	,CASE WHEN BS_SELLNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM_L      ");                                                                        	//同期销量
		sql.append(" 	,CASE WHEN (BS_SELLNUM+BS_SELLNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM_GROWTH         ");                                                                	//销量增量
		sql.append(" 	,CASE WHEN (BS_SELLNUM = 0 OR BS_SELLNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)*100.0000/DECODE(BS_SELLNUM_L,0,1,BS_SELLNUM_L), 2), 16, 2)) END AS BS_SELLNUM_GROWTH_RATE     ");                         	//销量增率
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IB_TERMSTOCKNUM		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM      ");                                                                             	//工商库存
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IB_TERMSTOCKNUM_L		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM_L        ");                                                                    	//同期工商库存
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM+IB_TERMSTOCKNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM_GROWTH    ");                                                    	//工商库存增量
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM = 0 OR IB_TERMSTOCKNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)*100.0000/DECODE(IB_TERMSTOCKNUM_L,0,1,IB_TERMSTOCKNUM_L), 2), 16, 2)) END AS IB_TERMSTOCKNUM_GROWTH_RATE	  ");  //工商库存增率
		sql.append(" 	,CASE WHEN BS_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM      ");                                                                          	//销额
		sql.append(" 	,CASE WHEN BS_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM_L     ");                                                                         	//同期销额
		sql.append(" 	,CASE WHEN (BS_SELLSUM+BS_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM_GROWTH          ");                                                               	//销额增量
		sql.append(" 	,CASE WHEN (BS_SELLSUM = 0 OR BS_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*100.0000/DECODE(BS_SELLSUM_L,0,1,BS_SELLSUM_L), 2), 16, 2)) END AS BS_SELLSUM_GROWTH_RATE    ");                          	//销额增率
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM     ");                                                                          	//单箱
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_L          ");                                                                    	//同期单箱
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_GROWTH              ");                                                               	//单箱增量
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS SINGLE_SELLSUM_GROWTH_RATE    ");          	//单箱增率
		sql.append(" 	,NVL(DECODE(C.CIG_PRODUCER,'10000001','国家局',O.PRODUCE_SHORTNAME), '-') AS PRODUCER_NAME     ");          //所有者
		sql.append(" 	,NVL(N.CLASS_NAME, '-') AS CLASS_NAME       ");                    //价类
		sql.append(" 	,NVL(CHAR(TRANSFERPRICE), '-') AS TRANSFERPRICE    ");                       //调拨价
		sql.append(" 	,NVL(CHAR(WHOLESALEPRICE), '-') AS WHOLESALEPRICE             ");            //批发价
		sql.append(" 	,NVL(CHAR(SUGGESTEDRETAILPRICE), '-') AS SUGGESTEDRETAILPRICE     ");        //零售价

		sql.append("  FROM   ");
				sql.append("  (   ");
				sql.append("  	SELECT    ");
				sql.append("  		C_BRAND                    						AS C_BRAND   ");
				sql.append("  	    ,BRAND_NAME                 					AS BRAND_NAME   ");
				sql.append("  	    ,SUM(PRODUCENUM               				)	AS PRODUCENUM   ");
				sql.append("  	    ,SUM(PRODUCENUM_L             				)	AS PRODUCENUM_L   ");
				sql.append("  	    ,SUM(BS_SELLNUM               				)	AS BS_SELLNUM   ");
				sql.append("  	    ,SUM(BS_SELLNUM_L             				)	AS BS_SELLNUM_L   ");
				sql.append("  	    ,SUM(BS_SELLSUM               				)	AS BS_SELLSUM   ");
				sql.append("  	    ,SUM(BS_SELLSUM_L             				)	AS BS_SELLSUM_L   ");
				sql.append("  	    ,SUM(SINGLE_SELLSUM           				)	AS SINGLE_SELLSUM   ");
				sql.append("  	    ,SUM(SINGLE_SELLSUM_L         				)	AS SINGLE_SELLSUM_L   ");
				sql.append("  	    ,SUM(BS_TERMSTOCKNUM 	+ IN_TERMSTOCKNUM   )   AS IB_TERMSTOCKNUM   ");
				sql.append("  	    ,SUM(BS_TERMSTOCKNUM_L	+ IN_TERMSTOCKNUM_L )   AS IB_TERMSTOCKNUM_L   ");
				sql.append("  	FROM   ");
				 	//工业   
				sql.append("  	(  "); 
				sql.append("  		SELECT  "); 
				sql.append("  			NVL(P.C_BRAND , '00')			AS C_BRAND  "); 
				sql.append("  			,NVL(P.CIG_NAME, '合  计') 		AS BRAND_NAME  "); 
				sql.append("  			,SUM(PRODUCENUM)			 	AS PRODUCENUM  "); 
				sql.append("  			,SUM(PRODUCENUM_L)			 	AS PRODUCENUM_L  "); 
				sql.append("  			,SUM(IN_TERMSTOCKNUM)		 	AS IN_TERMSTOCKNUM  "); 
				sql.append("  			,SUM(IN_TERMSTOCKNUM_L)		 	AS IN_TERMSTOCKNUM_L  "); 
				sql.append("  			,0                              AS BS_SELLNUM  "); 
				sql.append("  			,0                              AS BS_SELLNUM_L  "); 
				sql.append("  			,0                              AS BS_SELLSUM  "); 
				sql.append("  			,0                              AS BS_SELLSUM_L  "); 
				sql.append("  			,0                              AS SINGLE_SELLSUM  "); 
				sql.append("  			,0                              AS SINGLE_SELLSUM_L  "); 
				sql.append("  			,0                              AS BS_TERMSTOCKNUM  "); 
				sql.append("  			,0                              AS BS_TERMSTOCKNUM_L  "); 
				sql.append("  		FROM  "); 
				sql.append("  			(  "); 
				sql.append("  				SELECT  "); 
				sql.append("  					C_BRAND  "); 
				sql.append("  					,SUM(PRINTNUM1 - REJECTNUM1 + REPEATNUM1 - JUMPNUM1) 		AS PRODUCENUM  "); 
				sql.append("  					,SUM(PRINTNUM1_P - REJECTNUM1_P + REPEATNUM1_P - JUMPNUM1_P) 	AS PRODUCENUM_L  "); 
				sql.append("  					,SUM(TERMSTOCK1 	) 														AS IN_TERMSTOCKNUM  "); 
				sql.append("  					,SUM(TERMSTOCK1_P 	) 														AS IN_TERMSTOCKNUM_L  "); 
				//
				sql.append("  				FROM NICK_K_TJIN_Y_ALL K,NICK_TJYY_CIGARETTE SC   "); 
				sql.append("  				WHERE K.C_BRAND = sc.cig_codebar   "); 
				sql.append("  				AND ((k.d_year<='2017' AND IS_NRT='1' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_innx='1'))  "); 
				sql.append("  				AND CIG_GIRTH > 18  "); 
				sql.append("  				AND CIG_GIRTH < 24  "); 
				sql.append("  				AND CIG_IMPORTFLAG IN ('0','3')  "); 
				//
				sql.append("  				AND Y = YEAR(CURRENT DATE-1 DAYS)  "); 
				sql.append("  				GROUP BY C_BRAND  "); 
				sql.append("  			) K  "); 
				sql.append("  			,(SELECT C_BRAND,CIG_NAME FROM CIG_PROPERTY_DIV WHERE C_CIG = '").append(code).append("') P	  "); 	//参数
				sql.append("  			WHERE 1=1   "); 
				sql.append("  				AND K.C_BRAND = P.C_BRAND   "); 
				sql.append("  			GROUP BY P.C_BRAND, P.CIG_NAME  "); 
				sql.append("  			WITH ROLLUP  "); 
				sql.append("  			HAVING  "); 
				sql.append("  				(  "); 
				sql.append("  					(P.C_BRAND IS NULL AND P.CIG_NAME IS NULL)  "); 
				sql.append("  					OR (P.C_BRAND IS NOT NULL AND P.CIG_NAME IS NOT NULL)  "); 
				sql.append("  				)  "); 
				sql.append("  		UNION ALL  "); 
					//商业
				sql.append("  		SELECT  "); 
				sql.append("  			NVL(P.C_BRAND , '00')			AS C_BRAND  "); 
				sql.append("  			,NVL(P.CIG_NAME, '合  计') 		AS BRAND_NAME  "); 
				sql.append("  			,0                              AS PRODUCENUM  "); 
				sql.append("  			,0                              AS PRODUCENUM_L  "); 
				sql.append("  			,0                              AS IN_TERMSTOCKNUM  "); 
				sql.append("  			,0                              AS IN_TERMSTOCKNUM_L  "); 
				sql.append("  			,SUM(BS_SELLNUM)			 	AS BS_SELLNUM  "); 
				sql.append("  			,SUM(BS_SELLNUM_L)			 	AS BS_SELLNUM_L  "); 
				sql.append("  			,SUM(BS_SELLSUM)			 	AS BS_SELLSUM  "); 
				sql.append("  			,SUM(BS_SELLSUM_L)			 	AS BS_SELLSUM_L  "); 
				sql.append("  			,DECODE(SUM(BS_SELLNUM	),0,0,NULL,0,SUM(BS_SELLSUM		)*5.0000/SUM(BS_SELLNUM		)) 	AS SINGLE_SELLSUM  "); 
				sql.append("  			,DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L	)*5.0000/SUM(BS_SELLNUM_L	)) 	AS SINGLE_SELLSUM_L  "); 
				sql.append("  			,SUM(BS_TERMSTOCKNUM)		 	AS BS_TERMSTOCKNUM  "); 
				sql.append("  			,SUM(BS_TERMSTOCKNUM_L)		 	AS BS_TERMSTOCKNUM_L  "); 
				sql.append("  		FROM  "); 
				sql.append("  			(  "); 
				sql.append("  				SELECT  "); 
				sql.append("  					C_BRAND  "); 
				sql.append("  					,SUM(PRINT_NUM1 	- RBACK_NUM1	) 			AS BS_SELLNUM  "); 
				sql.append("  					,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	) 			AS BS_SELLNUM_L  "); 
				sql.append("  					,SUM(TERM_STOCK1 							) 			AS BS_TERMSTOCKNUM  "); 
				sql.append("  					,SUM(TERM_STOCK1_P 							) 			AS BS_TERMSTOCKNUM_L  "); 
				sql.append("  					,SUM(PRINT_SUM		- RBACK_SUM		) 			AS BS_SELLSUM  "); 
				sql.append("  					,SUM(PRINT_SUM_P 	- RBACK_SUM_P	) 			AS BS_SELLSUM_L  "); 
				//
				sql.append("  				FROM NICK_K_TJBS_Y_ALL K,NICK_TJYY_CIGARETTE SC   "); 
				sql.append("  				WHERE K.C_BRAND = sc.cig_codebar  "); 
				sql.append("  					 AND ((k.d_year<='2017' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_bsnx='1'))  "); 
				sql.append("  					AND CIG_GIRTH > 18  "); 
				sql.append("  					AND CIG_GIRTH < 24  "); 
				sql.append("  					AND CIG_IMPORTFLAG IN ('0','3')  "); 
				//
				sql.append("  					AND Y = YEAR(CURRENT DATE-1 DAYS)  "); 
				sql.append("  					GROUP BY C_BRAND  "); 
				sql.append("  			) K "); 
				sql.append("  			,(SELECT C_BRAND,CIG_NAME FROM CIG_PROPERTY_DIV WHERE C_CIG = '").append(code).append("') P	  "); 	//参数
				
				sql.append("  			WHERE 1=1   "); 
				sql.append("  				AND K.C_BRAND = P.C_BRAND   "); 
				sql.append("  			GROUP BY P.C_BRAND, P.CIG_NAME  "); 
				sql.append("  			WITH ROLLUP  "); 
				sql.append("  			HAVING  "); 
				sql.append("  				(  "); 
				sql.append("  					(P.C_BRAND IS NULL AND P.CIG_NAME IS NULL)  "); 
				sql.append("  					OR (P.C_BRAND IS NOT NULL AND P.CIG_NAME IS NOT NULL)  "); 
				sql.append("  				)  "); 
				sql.append("  	)  "); 
				sql.append("  	GROUP BY C_BRAND, BRAND_NAME   ");
				
				sql.append(" HAVING ");
				sql.append(" (   ");
						sql.append(" 	SUM(PRODUCENUM               					)    <> 0 ");
						sql.append(" 	OR SUM(PRODUCENUM_L             				)    <> 0 ");
						sql.append(" 	OR SUM(BS_SELLNUM               				)    <> 0 ");
						sql.append(" 	OR SUM(BS_SELLNUM_L             				)    <> 0 ");
						sql.append(" 	OR SUM(BS_SELLSUM               				)    <> 0 ");
						sql.append(" 	OR SUM(BS_SELLSUM_L             				)    <> 0 ");
						sql.append(" 	OR SUM(SINGLE_SELLSUM           				)    <> 0 ");
						sql.append(" 	OR SUM(SINGLE_SELLSUM_L         				)    <> 0 ");
				sql.append(" 	OR SUM(BS_TERMSTOCKNUM 		+ IN_TERMSTOCKNUM   )    <> 0 ");
				sql.append(" 	OR SUM(BS_TERMSTOCKNUM_L	+ IN_TERMSTOCKNUM_L )    <> 0 ");
				sql.append(" ) ");
				
				sql.append("  ) K   ");
				
				sql.append("  LEFT JOIN NICK_STMA_CIGARETTE C   ");
				sql.append("  	ON K.C_BRAND = C.CIG_CODEBAR   ");
				sql.append("	LEFT JOIN(SELECT DISTINCT PRODUCE_CODE, PRODUCE_SHORTNAME FROM DIM_PRODUCE_ORG) O ");
				sql.append("	ON C.CIG_PRODUCER = O.PRODUCE_CODE ");
				sql.append("	LEFT JOIN DW_T_CLASS_CODE N ");
				sql.append("	ON C.CIG_PRICETYPE = N.CODE ");
				sql.append("  LEFT JOIN (SELECT DISTINCT BAR, TRANSFERPRICE, WHOLESALEPRICE, SUGGESTEDRETAILPRICE FROM PRICELIST) P   ");
				sql.append("  	ON K.C_BRAND = P.BAR   ");
				sql.append("  ORDER BY DECODE(BRAND_NAME,'合  计',0,1) ASC, 2 DESC   ");
				
				sql.append("  )   ");
				sql.append("  WHERE CLASS_NAME NOT IN ('无价类')   ");
				sql.append("  WITH UR   ");
		
		log.info("创新产品表格下钻规格数据处理sql: " + sql.toString());
		return sql.toString();
	}


	private String getTableSql() {
		StringBuffer sql = new StringBuffer();

		/*sql.append(" SELECT   ");
		sql.append(" 	K.C_CIG 		AS xz_parameter  ");
		sql.append(" 	,CIG_NAME 	AS CIG_NAME  ");
		sql.append(" 	,CASE WHEN PRODUCENUM1 = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM1					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM     ");                                                                           	//产量
		sql.append(" 	,CASE WHEN PRODUCENUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM_L       ");                                                                       	//同期产量
		sql.append(" 	,CASE WHEN (PRODUCENUM1+PRODUCENUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM_GROWTH        ");                                                                 	//产量增量
		sql.append(" 	,CASE WHEN (PRODUCENUM1 = 0 OR PRODUCENUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)*100.0000/DECODE(PRODUCENUM_L,0,1,PRODUCENUM_L), 2), 16, 2)) END AS PRODUCENUM_GROWTH_RATE     ");                         	//产量增率
		sql.append(" 	,CASE WHEN BS_SELLNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM       ");                                                                         	//销量
		sql.append(" 	,CASE WHEN BS_SELLNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM_L          ");                                                                    	//同期销量
		sql.append(" 	,CASE WHEN (BS_SELLNUM+BS_SELLNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM_GROWTH         ");                                                                	//销量增量
		sql.append(" 	,CASE WHEN (BS_SELLNUM = 0 OR BS_SELLNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)*100.0000/DECODE(BS_SELLNUM_L,0,1,BS_SELLNUM_L), 2), 16, 2)) END AS BS_SELLNUM_GROWTH_RATE   ");                           	//销量增率
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IB_TERMSTOCKNUM		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM         ");                                                                          	//工商库存
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IB_TERMSTOCKNUM_L		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM_L     ");                                                                        	//同期工商库存
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM+IB_TERMSTOCKNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM_GROWTH     ");                                                   	//工商库存增量
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM = 0 OR IB_TERMSTOCKNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)*100.0000/DECODE(IB_TERMSTOCKNUM_L,0,1,IB_TERMSTOCKNUM_L), 2), 16, 2)) END AS IB_TERMSTOCKNUM_GROWTH_RATE	  ");  //工商库存增率
		sql.append(" 	,CASE WHEN BS_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM      ");                                                                          	//销额
		sql.append(" 	,CASE WHEN BS_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM_L    ");                                                                          	//同期销额
		sql.append(" 	,CASE WHEN (BS_SELLSUM+BS_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM_GROWTH        ");                                                                 	//销额增量
		sql.append(" 	,CASE WHEN (BS_SELLSUM = 0 OR BS_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*100.0000/DECODE(BS_SELLSUM_L,0,1,BS_SELLSUM_L), 2), 16, 2)) END AS BS_SELLSUM_GROWTH_RATE        ");                      	//销额增率
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM          ");                                                                      	//单箱
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_L       ");                                                                       	//同期单箱
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_GROWTH           ");                                                                  	//单箱增量
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS SINGLE_SELLSUM_GROWTH_RATE     ");         	//单箱增率
		sql.append("  FROM   ");
		sql.append("  (   ");
		sql.append("  	SELECT    ");
		sql.append("  		C_CIG                    						AS C_CIG   ");
		sql.append("  	    ,CIG_NAME                 						AS CIG_NAME   ");
		sql.append("  	    ,SUM(PRODUCENUM               				)	AS PRODUCENUM1   ");
		sql.append("  	    ,SUM(PRODUCENUM_L             				)	AS PRODUCENUM_L   ");
		sql.append("  	    ,SUM(BS_SELLNUM               				)	AS BS_SELLNUM   ");
		sql.append("  	    ,SUM(BS_SELLNUM_L             				)	AS BS_SELLNUM_L   ");
		sql.append("  	    ,SUM(BS_SELLSUM               				)	AS BS_SELLSUM   ");
		sql.append("  	    ,SUM(BS_SELLSUM_L             				)	AS BS_SELLSUM_L   ");
		sql.append("  	    ,SUM(SINGLE_SELLSUM           				)	AS SINGLE_SELLSUM   ");
		sql.append("  	    ,SUM(SINGLE_SELLSUM_L         				)	AS SINGLE_SELLSUM_L   ");
		sql.append("  	    ,SUM(BS_TERMSTOCKNUM 	+ IN_TERMSTOCKNUM   )   AS IB_TERMSTOCKNUM   ");
		sql.append("  	    ,SUM(BS_TERMSTOCKNUM_L	+ IN_TERMSTOCKNUM_L )   AS IB_TERMSTOCKNUM_L   ");
		sql.append("  	FROM   ");
		  	//工业
		sql.append("(        ");
		sql.append("	SELECT ");
		sql.append("		NVL(P.C_CIG , '00')				AS C_CIG  ");
		sql.append("		,NVL(P.CIG_MARKNAME, '合  计') 	AS CIG_NAME  ");
		sql.append("		,SUM(PRODUCENUM)			 	AS PRODUCENUM ");
		sql.append("		,SUM(PRODUCENUM_L)			 	AS PRODUCENUM_L ");
		sql.append("		,SUM(IN_TERMSTOCKNUM)		 	AS IN_TERMSTOCKNUM ");
		sql.append("		,SUM(IN_TERMSTOCKNUM_L)		 	AS IN_TERMSTOCKNUM_L ");
		sql.append("		,0                              AS BS_SELLNUM ");
		sql.append("		,0                              AS BS_SELLNUM_L ");
		sql.append("			,0                              AS BS_SELLSUM ");
		sql.append("			,0                              AS BS_SELLSUM_L ");
		sql.append("		,0                              AS SINGLE_SELLSUM ");
		sql.append("		,0                              AS SINGLE_SELLSUM_L "); 
		sql.append("		,0                              AS BS_TERMSTOCKNUM ");
		sql.append("		,0                              AS BS_TERMSTOCKNUM_L ");
		sql.append("	FROM   ");
		sql.append("		(    ");
		sql.append("			SELECT   ");
		sql.append("				C_BRAND   ");
		sql.append("				,C_CIG   ");
		sql.append("				,SUM(PRINTNUM1 - REJECTNUM1 + REPEATNUM1 - JUMPNUM1) 		AS PRODUCENUM   ");
		sql.append("				,SUM(PRINTNUM1_P - REJECTNUM1_P + REPEATNUM1_P - JUMPNUM1_P) 	AS PRODUCENUM_L   ");
		sql.append("				,SUM(TERMSTOCK1 	) 														AS IN_TERMSTOCKNUM   ");
		sql.append("				,SUM(TERMSTOCK1_P 	) 														AS IN_TERMSTOCKNUM_L   ");
		sql.append("			FROM NICK_K_TJIN_Y_ALL     ");
		sql.append(" WHERE 1=1      ");
		sql.append(" AND IS_NRT = '1'   ");
		sql.append(" AND C_CLASS <> '06'   ");
		sql.append("			AND Y = YEAR(CURRENT DATE-1 DAYS)   ");
		sql.append("			GROUP BY C_BRAND, C_CIG   ");
		sql.append("		) K     ");
		sql.append(" ,(SELECT C_BRAND, C_CIG, CIG_MARKNAME FROM CIG_PROPERTY_DIV WHERE IF_CIGA = '0') P     ");
		sql.append(" 	,(							");
		sql.append(" 	SELECT 							");
		sql.append(" 	DISTINCT CIG_CODEBAR					");
		sql.append(" 	FROM NICK_STMA_CIGARETTE				");
		sql.append(" 	WHERE 1=1 						");
		sql.append(" 	AND CIG_BARCARRIER = '02' 				");
		sql.append(" 	AND CIG_GIRTH>18 AND CIG_GIRTH<24 AND CIG_PRODUCTTYPE NOT IN('05', '06') AND CIG_IMPORTFLAG in ('0','3') 		");
		sql.append(" 	) C							");
		sql.append("		WHERE 1=1  ");
	    sql.append("			AND K.C_BRAND = P.C_BRAND   ");
	    sql.append("			AND K.C_BRAND = C.CIG_CODEBAR   ");
	    sql.append("		GROUP BY P.C_CIG, P.CIG_MARKNAME    ");
	    sql.append("		WITH ROLLUP     ");
	    sql.append("		HAVING    ");
	    sql.append("			(      ");
	    sql.append("				(P.C_CIG IS NULL AND P.CIG_MARKNAME IS NULL)    ");
	    sql.append("				OR (P.C_CIG IS NOT NULL AND P.CIG_MARKNAME IS NOT NULL)    ");
	    sql.append("			)     ");
	    sql.append("	UNION ALL    ");
		//商业
			sql.append("	SELECT   ");
			sql.append("		NVL(P.C_CIG , '00')				AS C_CIG   ");
			sql.append("		,NVL(P.CIG_MARKNAME, '合  计') 	AS CIG_NAME  ");
			sql.append("		,0                              AS PRODUCENUM  ");
			sql.append("		,0                              AS PRODUCENUM_L  ");
			sql.append("	,0                              AS IN_TERMSTOCKNUM  ");
			sql.append("	,0                              AS IN_TERMSTOCKNUM_L  ");
			sql.append("	,SUM(BS_SELLNUM)			 	AS BS_SELLNUM  ");
			sql.append("	,SUM(BS_SELLNUM_L)			 	AS BS_SELLNUM_L  ");
			sql.append("	,SUM(BS_SELLSUM)			 	AS BS_SELLSUM  ");
			sql.append("	,SUM(BS_SELLSUM_L)			 	AS BS_SELLSUM_L  ");
			sql.append("	,DECODE(SUM(BS_SELLNUM	),0,0,NULL,0,SUM(BS_SELLSUM		)*5.0000/SUM(BS_SELLNUM		)) 	AS SINGLE_SELLSUM  ");
			sql.append("	,DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L	)*5.0000/SUM(BS_SELLNUM_L	)) 	AS SINGLE_SELLSUM_L  ");
			sql.append("	,SUM(BS_TERMSTOCKNUM)		 	AS BS_TERMSTOCKNUM  ");
			sql.append("	,SUM(BS_TERMSTOCKNUM_L)		 	AS BS_TERMSTOCKNUM_L  ");
			sql.append(" FROM  ");
			sql.append("	(  ");
			sql.append("		SELECT  ");
			sql.append("			C_BRAND  ");
			sql.append("			,C_CIG  ");
			sql.append("			,SUM(PRINT_NUM1 	- RBACK_NUM1	) 			AS BS_SELLNUM  ");
			sql.append("			,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	) 			AS BS_SELLNUM_L  ");
			sql.append("			,SUM(TERM_STOCK1 							) 			AS BS_TERMSTOCKNUM  ");
			sql.append("			,SUM(TERM_STOCK1_P 							) 			AS BS_TERMSTOCKNUM_L  ");
			sql.append("			,SUM(PRINT_SUM		- RBACK_SUM		) 			AS BS_SELLSUM  ");
			sql.append("			,SUM(PRINT_SUM_P 	- RBACK_SUM_P	) 			AS BS_SELLSUM_L  ");
			sql.append("		FROM NICK_K_TJBS_Y_ALL   ");
			sql.append("		WHERE 1=1  ");
			sql.append("			AND C_CLASS <> '06'  ");
			sql.append("			AND Y = YEAR(CURRENT DATE-1 DAYS)  ");
			sql.append("			GROUP BY C_BRAND, C_CIG  ");
			sql.append("	) K  ");
			sql.append("	,(SELECT C_BRAND, C_CIG, CIG_MARKNAME FROM CIG_PROPERTY_DIV WHERE IF_CIGA = '0') P  ");
			sql.append(" 	,(							");
			sql.append(" 	SELECT 							");
			sql.append(" 	DISTINCT CIG_CODEBAR					");
			sql.append(" 	FROM NICK_STMA_CIGARETTE				");
			sql.append(" 	WHERE 1=1 						");
			sql.append(" 	AND CIG_BARCARRIER = '02' 				");
			sql.append(" 	AND CIG_GIRTH > 18 and CIG_GIRTH < 24	AND CIG_PRODUCTTYPE NOT IN('05', '06')  AND CIG_IMPORTFLAG in ('0','3')		");
			sql.append(" 	) C							");
			sql.append("	WHERE 1=1   ");
			sql.append("		AND K.C_BRAND = P.C_BRAND  ");
			sql.append("		AND K.C_BRAND = C.CIG_CODEBAR   ");
			sql.append("	GROUP BY P.C_CIG, P.CIG_MARKNAME  ");
			sql.append("	WITH ROLLUP  ");
			sql.append("	HAVING  ");
			sql.append("		(  ");
			sql.append("			(P.C_CIG IS NULL AND P.CIG_MARKNAME IS NULL)  ");
			sql.append("			OR (P.C_CIG IS NOT NULL AND P.CIG_MARKNAME IS NOT NULL)  ");
			sql.append("		)  ");
			sql.append(" )  ");
			sql.append("  	GROUP BY C_CIG, CIG_NAME   ");
		
		sql.append(" HAVING ");
		sql.append(" (   ");
				sql.append(" 	SUM(PRODUCENUM               					)    <> 0 ");
				sql.append(" 	OR SUM(PRODUCENUM_L             				)    <> 0 ");
				sql.append(" 	OR SUM(BS_SELLNUM               				)    <> 0 ");
				sql.append(" 	OR SUM(BS_SELLNUM_L             				)    <> 0 ");
				sql.append(" 	OR SUM(BS_SELLSUM               				)    <> 0 ");
				sql.append(" 	OR SUM(BS_SELLSUM_L             				)    <> 0 ");
				sql.append(" 	OR SUM(SINGLE_SELLSUM           				)    <> 0 ");
				sql.append(" 	OR SUM(SINGLE_SELLSUM_L         				)    <> 0 ");
		sql.append(" 	OR SUM(BS_TERMSTOCKNUM 		+ IN_TERMSTOCKNUM   )    <> 0 ");
		sql.append(" 	OR SUM(BS_TERMSTOCKNUM_L	+ IN_TERMSTOCKNUM_L )    <> 0 ");
		sql.append(" ) ");
		
		sql.append("  ) K   ");
		
		sql.append(" ORDER BY PRODUCENUM1 DESC ");
		sql.append("  WITH UR   ");*/
		
		sql.append(" SELECT   ");
		sql.append(" 	K.C_CIG 		AS xz_parameter  ");
		sql.append(" 	,CIG_NAME 	AS CIG_NAME  ");
		sql.append(" 	,CASE WHEN PRODUCENUM1 = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM1					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM     ");                                                                           	//产量
		sql.append(" 	,CASE WHEN PRODUCENUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	PRODUCENUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM_L       ");                                                                       	//同期产量
		sql.append(" 	,CASE WHEN (PRODUCENUM1+PRODUCENUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS PRODUCENUM_GROWTH        ");                                                                 	//产量增量
		sql.append(" 	,CASE WHEN (PRODUCENUM1 = 0 OR PRODUCENUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)*100.0000/DECODE(PRODUCENUM_L,0,1,PRODUCENUM_L), 2), 16, 2)) END AS PRODUCENUM_GROWTH_RATE     ");                         	//产量增率
		sql.append(" 	,CASE WHEN BS_SELLNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM       ");                                                                         	//销量
		sql.append(" 	,CASE WHEN BS_SELLNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM_L          ");                                                                    	//同期销量
		sql.append(" 	,CASE WHEN (BS_SELLNUM+BS_SELLNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS BS_SELLNUM_GROWTH         ");                                                                	//销量增量
		sql.append(" 	,CASE WHEN (BS_SELLNUM = 0 OR BS_SELLNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)*100.0000/DECODE(BS_SELLNUM_L,0,1,BS_SELLNUM_L), 2), 16, 2)) END AS BS_SELLNUM_GROWTH_RATE   ");                           	//销量增率
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IB_TERMSTOCKNUM		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM         ");                                                                          	//工商库存
		sql.append(" 	,CASE WHEN IB_TERMSTOCKNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	IB_TERMSTOCKNUM_L		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM_L     ");                                                                        	//同期工商库存
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM+IB_TERMSTOCKNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS IB_TERMSTOCKNUM_GROWTH     ");                                                   	//工商库存增量
		sql.append(" 	,CASE WHEN (IB_TERMSTOCKNUM = 0 OR IB_TERMSTOCKNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((IB_TERMSTOCKNUM - IB_TERMSTOCKNUM_L)*100.0000/DECODE(IB_TERMSTOCKNUM_L,0,1,IB_TERMSTOCKNUM_L), 2), 16, 2)) END AS IB_TERMSTOCKNUM_GROWTH_RATE	  ");  //工商库存增率
		sql.append(" 	,CASE WHEN BS_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM      ");                                                                          	//销额
		sql.append(" 	,CASE WHEN BS_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BS_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM_L    ");                                                                          	//同期销额
		sql.append(" 	,CASE WHEN (BS_SELLSUM+BS_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS BS_SELLSUM_GROWTH        ");                                                                 	//销额增量
		sql.append(" 	,CASE WHEN (BS_SELLSUM = 0 OR BS_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*100.0000/DECODE(BS_SELLSUM_L,0,1,BS_SELLSUM_L), 2), 16, 2)) END AS BS_SELLSUM_GROWTH_RATE        ");                      	//销额增率
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM          ");                                                                      	//单箱
		sql.append(" 	,CASE WHEN SINGLE_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_L       ");                                                                       	//同期单箱
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS SINGLE_SELLSUM_GROWTH           ");                                                                  	//单箱增量
		sql.append(" 	,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS SINGLE_SELLSUM_GROWTH_RATE     ");         	//单箱增率
		sql.append("  FROM   ");
		sql.append("  (   ");
		sql.append("  	SELECT    ");
		sql.append("  		C_CIG                    						AS C_CIG   ");
		sql.append("  	    ,CIG_NAME                 						AS CIG_NAME   ");
		sql.append("  	    ,SUM(PRODUCENUM               				)	AS PRODUCENUM1   ");
		sql.append("  	    ,SUM(PRODUCENUM_L             				)	AS PRODUCENUM_L   ");
		sql.append("  	    ,SUM(BS_SELLNUM               				)	AS BS_SELLNUM   ");
		sql.append("  	    ,SUM(BS_SELLNUM_L             				)	AS BS_SELLNUM_L   ");
		sql.append("  	    ,SUM(BS_SELLSUM               				)	AS BS_SELLSUM   ");
		sql.append("  	    ,SUM(BS_SELLSUM_L             				)	AS BS_SELLSUM_L   ");
		sql.append("  	    ,SUM(SINGLE_SELLSUM           				)	AS SINGLE_SELLSUM   ");
		sql.append("  	    ,SUM(SINGLE_SELLSUM_L         				)	AS SINGLE_SELLSUM_L   ");
		sql.append("  	    ,SUM(BS_TERMSTOCKNUM 	+ IN_TERMSTOCKNUM   )   AS IB_TERMSTOCKNUM   ");
		sql.append("  	    ,SUM(BS_TERMSTOCKNUM_L	+ IN_TERMSTOCKNUM_L )   AS IB_TERMSTOCKNUM_L   ");
		sql.append("  	FROM   ");
		  	//工业
		sql.append("(        ");
		sql.append("	SELECT ");
		sql.append("		NVL(P.C_CIG , '00')				AS C_CIG  ");
		sql.append("		,NVL(P.CIG_MARKNAME, '合  计') 	AS CIG_NAME  ");
		sql.append("		,SUM(PRODUCENUM)			 	AS PRODUCENUM ");
		sql.append("		,SUM(PRODUCENUM_L)			 	AS PRODUCENUM_L ");
		sql.append("		,SUM(IN_TERMSTOCKNUM)		 	AS IN_TERMSTOCKNUM ");
		sql.append("		,SUM(IN_TERMSTOCKNUM_L)		 	AS IN_TERMSTOCKNUM_L ");
		sql.append("		,0                              AS BS_SELLNUM ");
		sql.append("		,0                              AS BS_SELLNUM_L ");
		sql.append("			,0                              AS BS_SELLSUM ");
		sql.append("			,0                              AS BS_SELLSUM_L ");
		sql.append("		,0                              AS SINGLE_SELLSUM ");
		sql.append("		,0                              AS SINGLE_SELLSUM_L "); 
		sql.append("		,0                              AS BS_TERMSTOCKNUM ");
		sql.append("		,0                              AS BS_TERMSTOCKNUM_L ");
		sql.append("	FROM   ");
		sql.append("		(    ");
		sql.append("			SELECT   ");
		sql.append("				C_BRAND   ");
		sql.append("				,C_CIG   ");
		sql.append("				,SUM(PRINTNUM1 - REJECTNUM1 + REPEATNUM1 - JUMPNUM1) 		AS PRODUCENUM   ");
		sql.append("				,SUM(PRINTNUM1_P - REJECTNUM1_P + REPEATNUM1_P - JUMPNUM1_P) 	AS PRODUCENUM_L   ");
		sql.append("				,SUM(TERMSTOCK1 	) 														AS IN_TERMSTOCKNUM   ");
		sql.append("				,SUM(TERMSTOCK1_P 	) 														AS IN_TERMSTOCKNUM_L   ");
		//
		sql.append("			FROM NICK_K_TJIN_Y_ALL K,NICK_TJYY_CIGARETTE SC     ");
		sql.append(" WHERE K.C_BRAND = sc.cig_codebar     ");
		sql.append(" AND ((k.d_year<='2017' AND IS_NRT='1' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_innx='1'))  ");
		sql.append(" AND CIG_GIRTH > 18   ");
		sql.append(" AND CIG_GIRTH < 24   ");
		sql.append(" AND CIG_IMPORTFLAG IN ('0','3')   ");
		
		sql.append("			AND Y = YEAR(CURRENT DATE-1 DAYS)   ");
		sql.append("			GROUP BY C_BRAND, C_CIG   ");
		sql.append("		) K     ");
		sql.append(" ,(SELECT C_BRAND, C_CIG, CIG_MARKNAME FROM CIG_PROPERTY_DIV ) P     ");
		
		sql.append("		WHERE 1=1  ");
	    sql.append("			AND K.C_BRAND = P.C_BRAND   ");
	    sql.append("		GROUP BY P.C_CIG, P.CIG_MARKNAME    ");
	    sql.append("		WITH ROLLUP     ");
	    sql.append("		HAVING    ");
	    sql.append("			(      ");
	    sql.append("				(P.C_CIG IS NULL AND P.CIG_MARKNAME IS NULL)    ");
	    sql.append("				OR (P.C_CIG IS NOT NULL AND P.CIG_MARKNAME IS NOT NULL)    ");
	    sql.append("			)     ");
	    sql.append("	UNION ALL    ");
		//商业
			sql.append("	SELECT   ");
			sql.append("		NVL(P.C_CIG , '00')				AS C_CIG   ");
			sql.append("		,NVL(P.CIG_MARKNAME, '合  计') 	AS CIG_NAME  ");
			sql.append("		,0                              AS PRODUCENUM  ");
			sql.append("		,0                              AS PRODUCENUM_L  ");
			sql.append("	,0                              AS IN_TERMSTOCKNUM  ");
			sql.append("	,0                              AS IN_TERMSTOCKNUM_L  ");
			sql.append("	,SUM(BS_SELLNUM)			 	AS BS_SELLNUM  ");
			sql.append("	,SUM(BS_SELLNUM_L)			 	AS BS_SELLNUM_L  ");
			sql.append("	,SUM(BS_SELLSUM)			 	AS BS_SELLSUM  ");
			sql.append("	,SUM(BS_SELLSUM_L)			 	AS BS_SELLSUM_L  ");
			sql.append("	,DECODE(SUM(BS_SELLNUM	),0,0,NULL,0,SUM(BS_SELLSUM		)*5.0000/SUM(BS_SELLNUM		)) 	AS SINGLE_SELLSUM  ");
			sql.append("	,DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L	)*5.0000/SUM(BS_SELLNUM_L	)) 	AS SINGLE_SELLSUM_L  ");
			sql.append("	,SUM(BS_TERMSTOCKNUM)		 	AS BS_TERMSTOCKNUM  ");
			sql.append("	,SUM(BS_TERMSTOCKNUM_L)		 	AS BS_TERMSTOCKNUM_L  ");
			sql.append(" FROM  ");
			sql.append("	(  ");
			sql.append("		SELECT  ");
			sql.append("			C_BRAND  ");
			sql.append("			,C_CIG  ");
			sql.append("			,SUM(PRINT_NUM1 	- RBACK_NUM1	) 			AS BS_SELLNUM  ");
			sql.append("			,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	) 			AS BS_SELLNUM_L  ");
			sql.append("			,SUM(TERM_STOCK1 							) 			AS BS_TERMSTOCKNUM  ");
			sql.append("			,SUM(TERM_STOCK1_P 							) 			AS BS_TERMSTOCKNUM_L  ");
			sql.append("			,SUM(PRINT_SUM		- RBACK_SUM		) 			AS BS_SELLSUM  ");
			sql.append("			,SUM(PRINT_SUM_P 	- RBACK_SUM_P	) 			AS BS_SELLSUM_L  ");
			//       
			sql.append("		FROM NICK_K_TJBS_Y_ALL K,NICK_TJYY_CIGARETTE SC   ");
			sql.append("		WHERE K.C_BRAND = sc.cig_codebar ");
			sql.append("			 AND ((k.d_year<='2017' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_bsnx='1'))  ");
			sql.append("			AND CIG_GIRTH > 18  ");
			sql.append("			AND CIG_GIRTH < 24  ");
			sql.append("			AND CIG_IMPORTFLAG IN ('0','3')  ");
			//
			sql.append("			AND Y = YEAR(CURRENT DATE-1 DAYS)  ");
			sql.append("			GROUP BY C_BRAND, C_CIG  ");
			sql.append("	) K  ");
			sql.append("	,(SELECT C_BRAND, C_CIG, CIG_MARKNAME FROM CIG_PROPERTY_DIV ) P  ");
			
			sql.append("	WHERE 1=1   ");
			sql.append("		AND K.C_BRAND = P.C_BRAND  ");
			sql.append("	GROUP BY P.C_CIG, P.CIG_MARKNAME  ");
			sql.append("	WITH ROLLUP  ");
			sql.append("	HAVING  ");
			sql.append("		(  ");
			sql.append("			(P.C_CIG IS NULL AND P.CIG_MARKNAME IS NULL)  ");
			sql.append("			OR (P.C_CIG IS NOT NULL AND P.CIG_MARKNAME IS NOT NULL)  ");
			sql.append("		)  ");
			sql.append(" )  ");
			sql.append("  	GROUP BY C_CIG, CIG_NAME   ");
		
		sql.append(" HAVING ");
		sql.append(" (   ");
				sql.append(" 	SUM(PRODUCENUM               					)    <> 0 ");
				sql.append(" 	OR SUM(PRODUCENUM_L             				)    <> 0 ");
				sql.append(" 	OR SUM(BS_SELLNUM               				)    <> 0 ");
				sql.append(" 	OR SUM(BS_SELLNUM_L             				)    <> 0 ");
				sql.append(" 	OR SUM(BS_SELLSUM               				)    <> 0 ");
				sql.append(" 	OR SUM(BS_SELLSUM_L             				)    <> 0 ");
				sql.append(" 	OR SUM(SINGLE_SELLSUM           				)    <> 0 ");
				sql.append(" 	OR SUM(SINGLE_SELLSUM_L         				)    <> 0 ");
		sql.append(" 	OR SUM(BS_TERMSTOCKNUM 		+ IN_TERMSTOCKNUM   )    <> 0 ");
		sql.append(" 	OR SUM(BS_TERMSTOCKNUM_L	+ IN_TERMSTOCKNUM_L )    <> 0 ");
		sql.append(" ) ");
		
		sql.append("  ) K   ");
		
		sql.append(" ORDER BY PRODUCENUM1 DESC ");
		sql.append("  WITH UR   ");
		
		
		
		log.info("创新产品表格数据处理sql: " + sql.toString());
		return sql.toString();
	}

}
