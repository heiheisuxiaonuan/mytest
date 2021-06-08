package com.icss.tjfx.total.bs.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.total.bs.vo.BusinessVO;

/**
 * 总量情况商业模块右侧表格数据查询类
 * @author lcx
 *
 */
public class BsTableQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(BsTableQuery.class);
	
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)throws Exception {
		Gson gson = new Gson();
		List<BusinessVO> businessList = new ArrayList<BusinessVO>();;
		String type=request.getParameter("type");								//获取下钻的类型
		String code=request.getParameter("code");	
		if (type.equals("jl")) {
			businessList = getJLTableInfo(code,"dw");
		}else {
			businessList = getBsTableInfo("dw");
		}		
		String s = gson.toJson(businessList);
		log.info("总量情况商业务查询结果："+s);		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		
		return request;
	}
	
	
	/**
	 * 总量情况、商业模块<p>表格查询sql获取
	 * @return String
	 */
	public static String getSqlTable(){
		StringBuffer tableSql = new StringBuffer();
		tableSql.append(" 	SELECT																														");
		tableSql.append(" 	NVL(K.I_PROVINCE, '00') AS C_CLASS																										");
		tableSql.append(" 	,NVL(PROV_ORG_NAME, '合  计') AS PROV_NAME																									");
		tableSql.append(" 	,CASE WHEN BUYNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BUYNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 				AS buyNum_bq                                                    ");      
		tableSql.append(" 	,CASE WHEN BUYNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BUYNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 				AS buyNum_tq                                                    ");      
		tableSql.append(" 	,CASE WHEN (BUYNUM+BUYNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BUYNUM - BUYNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS buyNum_bl											");	
		tableSql.append(" 	,CASE WHEN (BUYNUM = 0 OR BUYNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BUYNUM - BUYNUM_L)*100.0000/DECODE(BUYNUM_L,0,1,BUYNUM_L), 2), 16, 2)) END AS buyNum_pt									");	
		tableSql.append(" 	,CASE WHEN SELLNUM1 = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	SELLNUM1					*1.0000/50000, 2), 16, 2),'999990.99') END 				AS sellNum_bq                                           ");      
		tableSql.append(" 	,CASE WHEN SELLNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 				AS sellNum_tq                                                   ");      
		tableSql.append(" 	,CASE WHEN (SELLNUM1+SELLNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SELLNUM1 - SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS sellNum_bl											");	
		tableSql.append(" 	,CASE WHEN (SELLNUM1 = 0 OR SELLNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SELLNUM1 - SELLNUM_L)*100.0000/DECODE(SELLNUM_L,0,1,SELLNUM_L), 2), 16, 2)) END AS sellNum_pt							");	
		tableSql.append(" 	,CASE WHEN TERMSTOCK = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	(TERMSTOCK 		)						*1.0000/50000, 2), 16, 2),'999990.99') END 				AS bsStock_bq                   ");      
		tableSql.append(" 	,CASE WHEN TERMSTOCK_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	(TERMSTOCK_L 	)						*1.0000/50000, 2), 16, 2),'999990.99') END 				AS bsStock_tq                           ");      
		tableSql.append(" 	,CASE WHEN (TERMSTOCK+TERMSTOCK_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((TERMSTOCK - TERMSTOCK_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS bsStock_bl										");	
		tableSql.append(" 	,CASE WHEN (TERMSTOCK = 0 OR TERMSTOCK_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((TERMSTOCK - TERMSTOCK_L)*100.0000/DECODE(TERMSTOCK_L,0,1,TERMSTOCK_L), 2), 16, 2)) END AS bsStock_pt						");	
		tableSql.append(" 	,CASE WHEN SINGLE_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END AS singleSellAmount_bq                                                            ");      
		tableSql.append(" 	,CASE WHEN SINGLE_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99') END AS singleSellAmount_tq                                                            ");      
		tableSql.append(" 	,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS singleSellAmount_bl                                                          ");      
		tableSql.append(" 	,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS singleSellAmount_pt	");	
		tableSql.append(" 	,CASE WHEN SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 				AS bsSellAmount_bq                                              ");      
		tableSql.append(" 	,CASE WHEN SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 				AS bsSellAmount_tq                                              ");      
		tableSql.append(" 	,CASE WHEN (SELLSUM+SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SELLSUM - SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS bsSellAmount_bl										");	
		tableSql.append(" 	,CASE WHEN (SELLSUM = 0 OR SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SELLSUM - SELLSUM_L)*100.0000/DECODE(SELLSUM_L,0,1,SELLSUM_L), 2), 16, 2)) END AS bsSellAmount_pt							");	

		tableSql.append("  	FROM																									  ");
		tableSql.append("  	(																									  ");
		tableSql.append("  	SELECT 																									  ");
		tableSql.append("  		NVL(I_PROVINCE, '00'	) 	AS I_PROVINCE																			  ");
		tableSql.append("  		,SUM(BUYNUM         	) 	AS BUYNUM																			  ");
		tableSql.append("  		,SUM(BUYNUM_L       	) 	AS BUYNUM_L																			  ");
		tableSql.append("  		,SUM(SELLNUM        	) 	AS SELLNUM1																			  ");
		tableSql.append("  		,SUM(SELLNUM_L      	) 	AS SELLNUM_L																			  ");
		tableSql.append("  		,SUM(SELLSUM        	) 	AS SELLSUM																			  ");
		tableSql.append("  		,SUM(SELLSUM_L      	) 	AS SELLSUM_L																			  ");
		tableSql.append("  		,DECODE(SUM(SELLNUM		),0,0,SUM(SELLSUM	)*5.0000/SUM(SELLNUM	))  AS SINGLE_SELLSUM												  ");
		tableSql.append("  		,DECODE(SUM(SELLNUM_L	),0,0,SUM(SELLSUM_L	)*5.0000/SUM(SELLNUM_L	))  AS SINGLE_SELLSUM_L													  ");
		tableSql.append("  		,SUM(TERMSTOCK      	) 	AS TERMSTOCK																			  ");
		tableSql.append("  		,SUM(TERMSTOCK_L    	) 	AS TERMSTOCK_L																			  ");
		tableSql.append("  	FROM																									  ");
		tableSql.append("  	(																									  ");
		tableSql.append("  		SELECT																								  ");
		tableSql.append("  			K.C_BRAND																							  ");
		tableSql.append("  			,I_PROVINCE																						  ");
		tableSql.append("  			,SUM(ARRIVAL_NUM1	- BACKOUT_NUM1		)   AS BUYNUM															  ");
		tableSql.append("  			,SUM(ARRIVAL_NUM1_P 	- BACKOUT_NUM1_P		)   AS BUYNUM_L															  ");
		tableSql.append("  			,SUM(PRINT_NUM1		- RBACK_NUM1		)   AS SELLNUM														  ");
		tableSql.append("  			,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P		)   AS SELLNUM_L														  ");
		tableSql.append("  			,SUM(PRINT_SUM		- RBACK_SUM			)   AS SELLSUM														  ");
		tableSql.append("  			,SUM(PRINT_SUM_P 	- RBACK_SUM_P		)   AS SELLSUM_L														  ");
		tableSql.append("  			,SUM(TERM_STOCK1   								)	AS TERMSTOCK											  ");
		tableSql.append("  			,SUM(TERM_STOCK1_P 								)	AS TERMSTOCK_L											  ");
		tableSql.append("  		FROM NICK_K_TJBS_Y_ALL K,NICK_TJYY_CIGARETTE SC  WHERE 1=1 			  ");
		tableSql.append("  		 AND K.C_BRAND = SC.cig_codebar and 				  ");
		tableSql.append("  		((D_YEAR<='2017' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_bsnx='1')) 					  ");
		tableSql.append("  			AND I_PROVINCE <> '880000'																				  ");
		tableSql.append("  			AND Y = YEAR(CURRENT DATE-1 DAYS)																				  ");
		tableSql.append("  		GROUP BY K.C_BRAND, I_PROVINCE																					  ");
		tableSql.append("  	) K																									  ");
		tableSql.append("  	GROUP BY I_PROVINCE																							  ");
		tableSql.append("  	WITH ROLLUP																								  ");
		tableSql.append("  	) K																									  ");
		tableSql.append("  	LEFT JOIN MA_T_PROV_ORG F																						  ");
		tableSql.append("  	ON K.I_PROVINCE = F.PROV_CODE																						  ");
		tableSql.append("  	ORDER BY DECIMAL(SELLNUM1) DESC																						  ");
																														 
		log.info("总量情况商业模块表格查询sql为："+tableSql);
		return tableSql.toString();
	}
	
	/**
	 * 总量情况、商业模块<p>表格数据信息的查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<BusinessVO> getBsTableInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<BusinessVO> list = new ArrayList<BusinessVO>();
		try {
			sql = getSqlTable();
			list = dbbean.executeQuery(sql, BusinessVO.class.getName());
		} catch (Exception e) {
			log.error("getBsTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	/**
	 * 总量情况、商业模块<p>表格下钻价类查询sql获取
	 * @return String
	 */
	public static String getSqlJLTable(String code){
		StringBuffer jlSql = new StringBuffer();
		jlSql.append(" 	select className, buyNum_bq, buyNum_tq, buyNum_bl, buyNum_pt, sellNum_bq, sellNum_tq, sellNum_bl, sellNum_pt, bsStock_bq            ");	
		jlSql.append(" 	, bsStock_tq, bsStock_bl, bsStock_pt, singleSellAmount_bq, singleSellAmount_tq, singleSellAmount_bl, singleSellAmount_pt            ");	
		jlSql.append(" 	, bsSellAmount_bq, bsSellAmount_tq, bsSellAmount_bl, bsSellAmount_pt from (                                                         ");	
		jlSql.append(" 	SELECT																														");
		jlSql.append(" 	NVL(CLASS_NAME, '合  计') AS className																										");
		jlSql.append(" 	,CASE WHEN BUYNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BUYNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 				AS buyNum_bq                                                    ");                 
		jlSql.append(" 	,CASE WHEN BUYNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	BUYNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 				AS buyNum_tq                                                    ");               
		jlSql.append(" 	,CASE WHEN (BUYNUM+BUYNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BUYNUM - BUYNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS buyNum_bl											");	
		jlSql.append(" 	,CASE WHEN (BUYNUM = 0 OR BUYNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BUYNUM - BUYNUM_L)*100.0000/DECODE(BUYNUM_L,0,1,BUYNUM_L), 2), 16, 2)) END AS buyNum_pt									");
		jlSql.append(" 	,CASE WHEN SELLNUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 				AS sellNum_bq                                                   ");                 
		jlSql.append(" 	,CASE WHEN SELLNUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 				AS sellNum_tq                                                   ");               
		jlSql.append(" 	,CASE WHEN (SELLNUM+SELLNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SELLNUM - SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS sellNum_bl											");	
		jlSql.append(" 	,CASE WHEN (SELLNUM = 0 OR SELLNUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SELLNUM - SELLNUM_L)*100.0000/DECODE(SELLNUM_L,0,1,SELLNUM_L), 2), 16, 2)) END AS sellNum_pt								");	
		jlSql.append(" 	,CASE WHEN TERMSTOCK = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	(TERMSTOCK 		)						*1.0000/50000, 2), 16, 2),'999990.99') END 				AS bsStock_bq                   ");               
		jlSql.append(" 	,CASE WHEN TERMSTOCK_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	(TERMSTOCK_L 	)						*1.0000/50000, 2), 16, 2),'999990.99') END 				AS bsStock_tq                           ");             
		jlSql.append(" 	,CASE WHEN (TERMSTOCK+TERMSTOCK_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((TERMSTOCK - TERMSTOCK_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS bsStock_bl										");	
		jlSql.append(" 	,CASE WHEN (TERMSTOCK = 0 OR TERMSTOCK_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((TERMSTOCK - TERMSTOCK_L)*100.0000/DECODE(TERMSTOCK_L,0,1,TERMSTOCK_L), 2), 16, 2)) END AS bsStock_pt						");	
		jlSql.append(" 	,CASE WHEN SINGLE_SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END AS singleSellAmount_bq                                                            ");                  
		jlSql.append(" 	,CASE WHEN SINGLE_SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L						, 2), 16, 2),'999990.99') END AS singleSellAmount_tq                                                            ");                
		jlSql.append(" 	,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS singleSellAmount_bl                                                          ");          
		jlSql.append(" 	,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS singleSellAmount_pt	");     
		jlSql.append(" 	,CASE WHEN SELLSUM = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 				AS bsSellAmount_bq                                              ");                      
		jlSql.append(" 	,CASE WHEN SELLSUM_L = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(	SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 				AS bsSellAmount_tq                                              ");                    
		jlSql.append(" 	,CASE WHEN (SELLSUM+SELLSUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SELLSUM - SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS bsSellAmount_bl										");		
		jlSql.append(" 	,CASE WHEN (SELLSUM = 0 OR SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SELLSUM - SELLSUM_L)*100.0000/DECODE(SELLSUM_L,0,1,SELLSUM_L), 2), 16, 2)) END AS bsSellAmount_pt							");		

		jlSql.append(" 	FROM																									     ");
		jlSql.append(" 	(																									     ");
		jlSql.append(" 	SELECT 																									     ");
		jlSql.append(" 		NVL(C_CLASS, '00'	) 	AS C_CLASS																			     ");
		jlSql.append(" 		,SUM(BUYNUM         	) 	AS BUYNUM																			     ");
		jlSql.append(" 		,SUM(BUYNUM_L       	) 	AS BUYNUM_L																			     ");
		jlSql.append(" 		,SUM(SELLNUM        	) 	AS SELLNUM																			     ");
		jlSql.append(" 		,SUM(SELLNUM_L      	) 	AS SELLNUM_L																			     ");
		jlSql.append(" 		,SUM(SELLSUM        	) 	AS SELLSUM																			     ");
		jlSql.append(" 		,SUM(SELLSUM_L      	) 	AS SELLSUM_L																			     ");
		jlSql.append(" 		,DECODE(SUM(SELLNUM		),0,0,SUM(SELLSUM	)*5.0000/SUM(SELLNUM	))  AS SINGLE_SELLSUM												     ");
		jlSql.append(" 		,DECODE(SUM(SELLNUM_L	),0,0,SUM(SELLSUM_L	)*5.0000/SUM(SELLNUM_L	))  AS SINGLE_SELLSUM_L													     ");
		jlSql.append(" 		,SUM(TERMSTOCK      	) 	AS TERMSTOCK																			     ");
		jlSql.append(" 		,SUM(TERMSTOCK_L    	) 	AS TERMSTOCK_L																			     ");
		jlSql.append(" 	FROM																									     ");
		jlSql.append(" 	(																									     ");
		jlSql.append(" 		SELECT																								     ");
		jlSql.append(" 			C_BRAND																							     ");
		jlSql.append(" 			,C_CLASS																					     ");
		jlSql.append(" 			,SUM(ARRIVAL_NUM1	- BACKOUT_NUM1		)   AS BUYNUM															     ");
		jlSql.append(" 			,SUM(ARRIVAL_NUM1_P 	- BACKOUT_NUM1_P		)   AS BUYNUM_L															     ");
		jlSql.append(" 			,SUM(PRINT_NUM1		- RBACK_NUM1		)   AS SELLNUM														     ");
		jlSql.append(" 			,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P		)   AS SELLNUM_L														     ");
		jlSql.append(" 			,SUM(PRINT_SUM		- RBACK_SUM			)   AS SELLSUM														     ");
		jlSql.append(" 			,SUM(PRINT_SUM_P 	- RBACK_SUM_P		)   AS SELLSUM_L														     ");
		jlSql.append(" 			,SUM(TERM_STOCK1   								)	AS TERMSTOCK											     ");
		jlSql.append(" 			,SUM(TERM_STOCK1_P 								)	AS TERMSTOCK_L											     ");
		jlSql.append("			FROM NICK_K_TJBS_Y_ALL    NK,NICK_TJYY_CIGARETTE SC         ");
		jlSql.append("		WHERE NK.C_BRAND = SC.cig_codebar and      ");
		jlSql.append("		((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))        ");
		jlSql.append(" 			AND I_PROVINCE = '").append(code).append("'");
		jlSql.append(" 			AND Y = YEAR(CURRENT DATE-1 DAYS)																				     ");
		jlSql.append(" 		GROUP BY C_BRAND, C_CLASS		having (SUM(ARRIVAL_NUM1 - BACKOUT_NUM1 )+SUM(ARRIVAL_NUM1_P - BACKOUT_NUM1_P )+SUM(PRINT_NUM1 - RBACK_NUM1 )+SUM(PRINT_NUM1_P - RBACK_NUM1_P )+SUM(PRINT_SUM - RBACK_SUM )+SUM(PRINT_SUM_P - RBACK_SUM_P )+SUM(TERM_STOCK1 )+SUM(TERM_STOCK1_P ))<>0																		     ");
		jlSql.append(" 	) K	GROUP BY  C_CLASS		WITH ROLLUP																								     ");
		jlSql.append(" 	) K																									     ");
		jlSql.append(" 	LEFT JOIN DW_T_CLASS_CODE C																					     ");
		jlSql.append(" 	ON K.C_CLASS = C.CODE																							     ");
		jlSql.append(" 	ORDER BY K.C_CLASS																						     ");
		jlSql.append(" 	) where className not in ('无价类') WITH UR      ");	
		log.info("总量情况商业模块表格下钻价类sql=="+jlSql);
		return jlSql.toString();
	}

	
	/**
	 * 总量情况、商业模块<p>表格数据下价类信息的查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<BusinessVO> getJLTableInfo(String code,String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<BusinessVO> list = new ArrayList<BusinessVO>();
		try {
			sql = getSqlJLTable(code);
			list = dbbean.executeQuery(sql, BusinessVO.class.getName());
		} catch (Exception e) {
			log.error("getJLTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	

}
