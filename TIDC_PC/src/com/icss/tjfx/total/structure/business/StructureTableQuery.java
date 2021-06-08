package com.icss.tjfx.total.structure.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.total.structure.vo.PriceClassVO;

/**
 * 总量情况结构模块右侧表格数据查询类
 * @author lcx
 *
 */
public class StructureTableQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(StructureTableQuery.class);
	
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)throws Exception {
		Gson gson = new Gson();
		List<PriceClassVO> priceClassList = null;
		String type=request.getParameter("type");								//获取下钻的类型
		String code=request.getParameter("code");	
		if (type.equals("pp")) {
			priceClassList = getPPTableInfo(code, "dw");
		}else {
			priceClassList = getTableInfo("dw");
		}
		
		
		String s = gson.toJson(priceClassList);
		log.info("总量情况业务查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		
		return request;
	}
	
	
	/**
	 * 总量情况、结构模块<p>表格查询sql获取
	 * @return String
	 */
	public static String getSqlTable(){
		StringBuffer tableSql = new StringBuffer();
		tableSql.append(" 	select * from (																																");
		tableSql.append(" 	SELECT																																																					");
		tableSql.append(" 	NVL(I.C_CLASS, '00') AS C_CLASS																																																		");
		tableSql.append(" 	,NVL(CLASS_NAME, '合  计') AS CLASS_NAME																																																	");
		tableSql.append(" 	,CASE WHEN PRODUCENUM 							= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS produceNum_bq                                                                               																");
		tableSql.append(" 	,CASE WHEN PRODUCENUM_L 						= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS produceNum_tq                                                                              																	");
		tableSql.append(" 	,CASE WHEN (PRODUCENUM+PRODUCENUM_L) 			= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS produceNum_bl                                                                        																				");
		tableSql.append(" 	,CASE WHEN (PRODUCENUM = 0 OR PRODUCENUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((PRODUCENUM - PRODUCENUM_L)*100.0000/DECODE(PRODUCENUM_L,0,1,PRODUCENUM_L), 2), 16, 2)) END AS produceNum_pt                              																							");
		tableSql.append(" 	,CASE WHEN BS_SELLNUM 							= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS sellNum_bq                                                                               																");
		tableSql.append(" 	,CASE WHEN BS_SELLNUM_L 						= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS sellNum_tq                                                                              																	");
		tableSql.append(" 	,CASE WHEN (BS_SELLNUM+BS_SELLNUM_L)			= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS sellNum_bl                                                                         																				");
		tableSql.append(" 	,CASE WHEN (BS_SELLNUM = 0 OR BS_SELLNUM = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)*100.0000/DECODE(BS_SELLNUM_L,0,1,BS_SELLNUM_L), 2), 16, 2)) END AS sellNum_pt                              																							");
		tableSql.append(" 	,CASE WHEN (IN_TERMSTOCKNUM+BS_TERMSTOCKNUM) 		= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	)		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS stock_bq                                                       																		");
		tableSql.append(" 	,CASE WHEN (IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L) 	= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	)		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS stock_tq                                                     																		");
		tableSql.append(" 	,CASE WHEN (IN_TERMSTOCKNUM+BS_TERMSTOCKNUM+IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(((IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	) - (IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	))	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS stock_bl        															");
		tableSql.append(" 	,CASE WHEN ((IN_TERMSTOCKNUM+BS_TERMSTOCKNUM) = 0 OR (IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L) = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND(((IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	) - (IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	))*100.0000/DECODE((IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	),0,1,(IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	)), 2), 16, 2)) END AS stock_pt		");
		tableSql.append(" 	,CASE WHEN NVL(TRANSIT_NUM,0) 					= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(TRANSIT_NUM		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS onRoad_bq                                                                       																					");
		tableSql.append(" 	,CASE WHEN NVL(TRANSIT_NUM_L,0)					= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(TRANSIT_NUM_L	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS onRoad_tq                                                                     																						");
		tableSql.append(" 	,CASE WHEN NVL(TRANSIT_NUM+TRANSIT_NUM_L,0) 	= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((TRANSIT_NUM - TRANSIT_NUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS onRoad_bl                                                                																						");
		tableSql.append(" 	,CASE WHEN (NVL(TRANSIT_NUM,0) = 0 OR NVL(TRANSIT_NUM_L,0) = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND(NVL(TRANSIT_NUM - TRANSIT_NUM_L,0)	*100.0000/DECODE(TRANSIT_NUM_L,0,1,TRANSIT_NUM_L), 2), 16, 2)) END AS onRoad_pt                         																				");
		tableSql.append(" 	,CASE WHEN SINGLE_SELLSUM 						= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END 	AS singleSellAmount_bq                                                                                																");
		tableSql.append(" 	,CASE WHEN SINGLE_SELLSUM_L 					= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L					, 2), 16, 2),'999990.99') END 	AS singleSellAmount_tq                                                                              																	");
		tableSql.append(" 	,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) 	= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS singleSellAmount_bl                                                                             																					");
		tableSql.append(" 	,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS singleSellAmount_pt              																						");
		tableSql.append(" 	,CASE WHEN BS_SELLSUM 							= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 	AS bsSellAmount_bq                                                                                																");
		tableSql.append(" 	,CASE WHEN BS_SELLSUM_L 						= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 	AS bsSellAmount_tq                                                                              																	");
		tableSql.append(" 	,CASE WHEN (BS_SELLSUM+BS_SELLSUM_L) 			= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS bsSellAmount_bl                                                                         																				");
		tableSql.append(" 	,CASE WHEN (BS_SELLSUM = 0 OR BS_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*100.0000/DECODE(BS_SELLSUM_L,0,1,BS_SELLSUM_L), 2), 16, 2)) END AS bsSellAmount_pt                       																								");

		tableSql.append(" FROM ");
		//--工业
		tableSql.append(" ( ");
			tableSql.append(" SELECT ");
				tableSql.append(" NVL(C_CLASS, '00') 				AS C_CLASS ");
				tableSql.append(" ,SUM(PRODUCENUM)			 	AS PRODUCENUM ");
				tableSql.append(" ,SUM(PRODUCENUM_L)			 	AS PRODUCENUM_L ");
				tableSql.append(" ,SUM(IN_TERMSTOCKNUM)		 	AS IN_TERMSTOCKNUM ");
				tableSql.append(" ,SUM(IN_TERMSTOCKNUM_L)		 	AS IN_TERMSTOCKNUM_L ");
			tableSql.append(" FROM ");
				tableSql.append(" ( ");
					tableSql.append(" SELECT ");
						tableSql.append(" C_BRAND ");
						tableSql.append(" ,C_CLASS ");
						tableSql.append(" ,SUM(PRINTNUM1 - REJECTNUM1 + REPEATNUM1 - JUMPNUM1) 		AS PRODUCENUM ");
						tableSql.append(" ,SUM(PRINTNUM1_P - REJECTNUM1_P + REPEATNUM1_P - JUMPNUM1_P) 	AS PRODUCENUM_L ");
						tableSql.append(" ,SUM(TERMSTOCK1 	) 			 											AS IN_TERMSTOCKNUM ");
						tableSql.append(" ,SUM(TERMSTOCK1_P 	) 	 													AS IN_TERMSTOCKNUM_L ");
						tableSql.append("		FROM NICK_K_TJIN_Y_ALL   NK,NICK_TJYY_CIGARETTE SC             ");
						tableSql.append("		WHERE 1=1              ");
						tableSql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
						tableSql.append("		((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))         ");
					tableSql.append("   AND Y = YEAR(CURRENT DATE-1 DAYS)   ");
					tableSql.append("     GROUP BY C_BRAND, C_CLASS  having (SUM(PRINTNUM1 - REJECTNUM1 + REPEATNUM1 - JUMPNUM1)<>0 or  ");
					tableSql.append("     SUM(PRINTNUM1_P - REJECTNUM1_P + REPEATNUM1_P - JUMPNUM1_P)<>0 or    ");
					tableSql.append("    SUM(TERMSTOCK1 )<>0 or SUM(TERMSTOCK1_P )<>0)   ");
				tableSql.append(" ) K   ,  (SELECT C_BRAND FROM NICK_CIG_PROPERTY ) P WHERE 1=1 AND K.C_BRAND = P.C_BRAND   GROUP BY  C_CLASS  WITH ROLLUP  ");
		tableSql.append(" ) I ");
		tableSql.append(" INNER JOIN ");
		//--商业
		tableSql.append(" ( ");
			tableSql.append(" SELECT ");
				tableSql.append(" NVL(C_CLASS, '00') 				AS C_CLASS ");
				tableSql.append(" ,SUM(BS_SELLNUM)			 	AS BS_SELLNUM ");
				tableSql.append(" ,SUM(BS_SELLNUM_L)			 	AS BS_SELLNUM_L ");
				tableSql.append(" ,SUM(BS_SELLSUM)			 	AS BS_SELLSUM ");
				tableSql.append(" ,SUM(BS_SELLSUM_L)			 	AS BS_SELLSUM_L ");
				tableSql.append(" ,DECODE(SUM(BS_SELLNUM	),0,0,NULL,0,SUM(BS_SELLSUM		)*5.0000/SUM(BS_SELLNUM		)) 	AS SINGLE_SELLSUM ");
				tableSql.append(" ,DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L	)*5.0000/SUM(BS_SELLNUM_L	)) 	AS SINGLE_SELLSUM_L ");
				tableSql.append(" ,SUM(BS_TERMSTOCKNUM)		 	AS BS_TERMSTOCKNUM ");
				tableSql.append(" ,SUM(BS_TERMSTOCKNUM_L)		 	AS BS_TERMSTOCKNUM_L ");
			tableSql.append(" FROM ");
				tableSql.append(" ( ");
					tableSql.append(" SELECT ");
						tableSql.append(" C_BRAND ");
						tableSql.append(" ,C_CLASS ");
						tableSql.append(" ,SUM(PRINT_NUM1 	- RBACK_NUM1	) 			AS BS_SELLNUM ");
						tableSql.append(" ,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	) 			AS BS_SELLNUM_L ");
						tableSql.append(" ,SUM(TERM_STOCK1 							) 			AS BS_TERMSTOCKNUM ");
						tableSql.append(" ,SUM(TERM_STOCK1_P 							) 			AS BS_TERMSTOCKNUM_L ");
						tableSql.append(" ,SUM(PRINT_SUM		- RBACK_SUM		) 			AS BS_SELLSUM ");
						tableSql.append(" ,SUM(PRINT_SUM_P 	- RBACK_SUM_P	) 			AS BS_SELLSUM_L ");
						tableSql.append("			FROM NICK_K_TJBS_Y_ALL    NK,NICK_TJYY_CIGARETTE SC         ");
						tableSql.append("		WHERE NK.C_BRAND = SC.cig_codebar and      ");
						tableSql.append("		((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))        ");
						tableSql.append(" AND Y = YEAR(CURRENT DATE-1 DAYS) ");
						tableSql.append(" GROUP BY C_BRAND, C_CLASS having (SUM(PRINT_NUM1 - RBACK_NUM1 )<>0 or SUM(PRINT_NUM1_P - RBACK_NUM1_P )<>0 or      ");
				tableSql.append("    SUM(TERM_STOCK1 )<>0 or SUM(TERM_STOCK1_P )<>0 or SUM(PRINT_SUM - RBACK_SUM )<>0 or     ");
				tableSql.append("     SUM(PRINT_SUM_P - RBACK_SUM_P )<>0)     ");
				tableSql.append(" ) K ,(SELECT C_BRAND FROM NICK_CIG_PROPERTY ) P WHERE 1=1 AND K.C_BRAND = P.C_BRAND   GROUP BY  C_CLASS  WITH ROLLUP  ");
		tableSql.append(" ) B ");
			tableSql.append(" ON I.C_CLASS = B.C_CLASS ");
		tableSql.append(" LEFT JOIN ");
		tableSql.append(" ( ");
			tableSql.append(" SELECT ");
				tableSql.append(" NVL(C_CLASS, '00') 		AS C_CLASS ");
				tableSql.append(" ,SUM(INBS_TRANSIT_NUM) 	AS TRANSIT_NUM ");
				tableSql.append("		FROM N_K_TJIN_IB_IR   NK,NICK_TJYY_CIGARETTE SC             ");
				tableSql.append("		WHERE 1=1              ");
				tableSql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
				tableSql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))        ");
				tableSql.append(" AND D_DAY = DC_FUNC_DTOSTR(CURRENT DATE-1 DAYS) ");
			tableSql.append(" GROUP BY C_CLASS ");
			tableSql.append(" WITH ROLLUP ");
		tableSql.append(" ) T ");
			tableSql.append(" ON I.C_CLASS = T.C_CLASS ");
		tableSql.append(" LEFT JOIN ");
		tableSql.append(" ( ");
			tableSql.append(" SELECT");
			tableSql.append(" 	NVL(C_CLASS, '00') AS C_CLASS ");
				tableSql.append(" ,SUM(INBS_TRANSIT_NUM) AS TRANSIT_NUM_L ");
				tableSql.append("		FROM N_K_TJIN_IB_IR   NK,NICK_TJYY_CIGARETTE SC             ");
				tableSql.append("		WHERE 1=1              ");
				tableSql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
				tableSql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))        ");
				tableSql.append(" AND D_DAY = DC_FUNC_DTOSTR(CURRENT DATE-1 DAYS -1 YEARS) ");
			tableSql.append(" GROUP BY C_CLASS ");
			tableSql.append(" WITH ROLLUP ");
		tableSql.append(" ) TL ");
			tableSql.append(" ON I.C_CLASS = TL.C_CLASS ");
		tableSql.append(" LEFT JOIN DW_T_CLASS_CODE C ");
		tableSql.append(" 	ON I.C_CLASS = C.CODE ");
		tableSql.append(" ORDER BY I.C_CLASS ");
		tableSql.append(" ) where C_CLASS not in ('06') with ur ");
		
		log.info("总量情况结构模块表格查询sql为："+tableSql);
		return tableSql.toString();
	}
	
	/**
	 * 总量情况、结构模块<p>表格数据信息的查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PriceClassVO> getTableInfo(String dbName){
		log.info("进入getTableInfo方法。。。。。");
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<PriceClassVO> list = new ArrayList<PriceClassVO>();
		try {
			sql = getSqlTable();
			list = dbbean.executeQuery(sql, PriceClassVO.class.getName());
		} catch (Exception e) {
			log.error("getTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	/**
	 * 总量情况、结构模块<p>表格下钻品牌查询sql获取
	 * @return String
	 */
	public static String getSqlPPTable(String code){
		StringBuffer ppSql = new StringBuffer();
		ppSql.append("		SELECT																																																			");
		ppSql.append("		NVL(CIG_MARKNAME, '合  计') AS CIG_NAME																																															");
		ppSql.append("		,CASE WHEN PRODUCENUM1 							= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM1					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS produceNum_bq                                                                                													");
		ppSql.append("		,CASE WHEN PRODUCENUM_L 						= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(PRODUCENUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS produceNum_tq                                                                              															");
		ppSql.append("		,CASE WHEN (PRODUCENUM1+PRODUCENUM_L) 			= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS produceNum_bl																											");
		ppSql.append("		,CASE WHEN (PRODUCENUM1 = 0 OR PRODUCENUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((PRODUCENUM1 - PRODUCENUM_L)*100.0000/DECODE(PRODUCENUM_L,0,1,PRODUCENUM_L), 2), 16, 2)) END AS produceNum_pt                              																						");
		ppSql.append("		,CASE WHEN BS_SELLNUM 							= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM					*1.0000/50000, 2), 16, 2),'999990.99') END 	AS sellNum_bq                                                                               														");
		ppSql.append("		,CASE WHEN BS_SELLNUM_L 						= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLNUM_L				*1.0000/50000, 2), 16, 2),'999990.99') END 	AS sellNum_tq                                                                              															");
		ppSql.append("		,CASE WHEN (BS_SELLNUM+BS_SELLNUM_L)			= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS sellNum_bl                                                                         																			");
		ppSql.append("		,CASE WHEN (BS_SELLNUM = 0 OR BS_SELLNUM = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLNUM - BS_SELLNUM_L)*100.0000/DECODE(BS_SELLNUM_L,0,1,BS_SELLNUM_L), 2), 16, 2)) END AS sellNum_pt                              																							");
		ppSql.append("		,CASE WHEN (IN_TERMSTOCKNUM+BS_TERMSTOCKNUM) 		= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	)		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS stock_bq                                                       																	");
		ppSql.append("		,CASE WHEN (IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L) 	= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	)		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS stock_tq                                                    																		");
		ppSql.append("		,CASE WHEN (IN_TERMSTOCKNUM+BS_TERMSTOCKNUM+IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L) = 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(((IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	) - (IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	))	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS stock_bl       															");
		ppSql.append("		,CASE WHEN ((IN_TERMSTOCKNUM+BS_TERMSTOCKNUM) = 0 OR (IN_TERMSTOCKNUM_L+BS_TERMSTOCKNUM_L) = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND(((IN_TERMSTOCKNUM 	+ BS_TERMSTOCKNUM	) - (IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	))*100.0000/DECODE((IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	),0,1,(IN_TERMSTOCKNUM_L 	+ BS_TERMSTOCKNUM_L	)), 2), 16, 2)) END AS stock_pt			");
		ppSql.append("		,CASE WHEN NVL(TRANSIT_NUM,0) 					= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(TRANSIT_NUM		*1.0000/50000, 2), 16, 2),'999990.99') END 	AS onRoad_bq                                                                       																			");
		ppSql.append("		,CASE WHEN NVL(TRANSIT_NUM_L,0)					= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(TRANSIT_NUM_L	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS onRoad_tq                                                                     																				");
		ppSql.append("		,CASE WHEN NVL(TRANSIT_NUM+TRANSIT_NUM_L,0) 	= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((TRANSIT_NUM - TRANSIT_NUM_L)	*1.0000/50000, 2), 16, 2),'999990.99') END 	AS onRoad_bl                                                               																					");
		ppSql.append("		,CASE WHEN (NVL(TRANSIT_NUM,0) = 0 OR NVL(TRANSIT_NUM_L,0) = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND(NVL(TRANSIT_NUM - TRANSIT_NUM_L,0)	*100.0000/DECODE(TRANSIT_NUM_L,0,1,TRANSIT_NUM_L), 2), 16, 2)) END AS onRoad_pt                         																			");
		ppSql.append("		,CASE WHEN SINGLE_SELLSUM 						= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM						, 2), 16, 2),'999990.99') END 	AS singleSellAmount_bq                                                                               														");
		ppSql.append("		,CASE WHEN SINGLE_SELLSUM_L 					= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(SINGLE_SELLSUM_L					, 2), 16, 2),'999990.99') END 	AS singleSellAmount_tq                                                                             															");
		ppSql.append("		,CASE WHEN (SINGLE_SELLSUM+SINGLE_SELLSUM_L) 	= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)	, 2), 16, 2),'999990.99') END 	AS singleSellAmount_bl                                                                             																			");
		ppSql.append("		,CASE WHEN (SINGLE_SELLSUM = 0 OR SINGLE_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((SINGLE_SELLSUM - SINGLE_SELLSUM_L)*100.0000/DECODE(SINGLE_SELLSUM_L,0,1,SINGLE_SELLSUM_L), 2), 16, 2)) END AS singleSellAmount_pt              																					");
		ppSql.append("		,CASE WHEN BS_SELLSUM 							= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM					*1.0000/10000, 2), 16, 2),'999990.99') END 	AS bsSellAmount_bq                                                                               													");
		ppSql.append("		,CASE WHEN BS_SELLSUM_L 						= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND(BS_SELLSUM_L				*1.0000/10000, 2), 16, 2),'999990.99') END 	AS bsSellAmount_tq                                                                              														");
		ppSql.append("		,CASE WHEN (BS_SELLSUM+BS_SELLSUM_L) 			= 0 THEN '-' ELSE TO_CHAR(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)	*1.0000/10000, 2), 16, 2),'999990.99') END 	AS bsSellAmount_bl                                                                         																		");
		ppSql.append("		,CASE WHEN (BS_SELLSUM = 0 OR BS_SELLSUM_L = 0) THEN '-' ELSE DC_FUNC_TRANS_RATE(DECIMAL(ROUND((BS_SELLSUM - BS_SELLSUM_L)*100.0000/DECODE(BS_SELLSUM_L,0,1,BS_SELLSUM_L), 2), 16, 2)) END AS bsSellAmount_pt                       																							");

		ppSql.append("  FROM  ");
		//--工业
		ppSql.append("  (  ");
		ppSql.append("  	SELECT  ");
		ppSql.append("  		NVL(P.C_CIG, '00') 				AS C_CIG  ");
		ppSql.append("  		,SUM(PRODUCENUM)			 	AS PRODUCENUM1  ");
		ppSql.append("  		,SUM(PRODUCENUM_L)			 	AS PRODUCENUM_L  ");
		ppSql.append("  		,SUM(IN_TERMSTOCKNUM)		 	AS IN_TERMSTOCKNUM  ");
		ppSql.append("  		,SUM(IN_TERMSTOCKNUM_L)		 	AS IN_TERMSTOCKNUM_L  ");
		ppSql.append("  	FROM  ");
		ppSql.append("  		(  ");
		ppSql.append("  			SELECT  ");
		ppSql.append("  				C_BRAND  ");
		ppSql.append("  				,C_CIG  ");
		ppSql.append("  				,SUM(PRINTNUM1 - REJECTNUM1 + REPEATNUM1 - JUMPNUM1) 		AS PRODUCENUM  ");
		ppSql.append("  				,SUM(PRINTNUM1_P - REJECTNUM1_P + REPEATNUM1_P - JUMPNUM1_P) 	AS PRODUCENUM_L  ");
		ppSql.append("  				,SUM(TERMSTOCK1 	) 		  											AS IN_TERMSTOCKNUM ");	
		ppSql.append("  				,SUM(TERMSTOCK1_P 	) 												AS IN_TERMSTOCKNUM_L	  ");	
		ppSql.append("		FROM NICK_K_TJIN_Y_ALL   NK,NICK_TJYY_CIGARETTE SC             ");
		ppSql.append("		WHERE 1=1              ");
		ppSql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
		ppSql.append("		((D_YEAR<='2017' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_innx='1'))         ");
		ppSql.append("  			AND C_CLASS = '").append(code).append("'");//--参数
		ppSql.append("  			AND Y = YEAR(CURRENT DATE-1 DAYS)   ");
		ppSql.append("  			GROUP BY C_BRAND, C_CIG  ");
		ppSql.append("  		) K  ,(SELECT C_BRAND, C_CIG FROM CIG_PROPERTY_DIV ) P    		WHERE 1=1     			AND K.C_BRAND = P.C_BRAND    		GROUP BY P.C_CIG    		WITH ROLLUP	    		HAVING    			(    				SUM(PRODUCENUM)		        <> 0    				OR SUM(PRODUCENUM_L)		<> 0    				OR SUM(IN_TERMSTOCKNUM)	    <> 0    				OR SUM(IN_TERMSTOCKNUM_L)   <> 0    			)    		    ) I  ");
		ppSql.append("  INNER JOIN  ");
		//--商业
		ppSql.append("  (  ");
		ppSql.append("  	SELECT  ");
		ppSql.append("  		NVL(P.C_CIG, '00') 				AS C_CIG  ");
		ppSql.append("  		,SUM(BS_SELLNUM)			 	AS BS_SELLNUM  ");
		ppSql.append("  		,SUM(BS_SELLNUM_L)			 	AS BS_SELLNUM_L  ");
		ppSql.append("  		,SUM(BS_SELLSUM)			 	AS BS_SELLSUM  ");
		ppSql.append("  		,SUM(BS_SELLSUM_L)			 	AS BS_SELLSUM_L  ");
		ppSql.append("  		,DECODE(SUM(BS_SELLNUM	),0,0,NULL,0,SUM(BS_SELLSUM		)*5.0000/SUM(BS_SELLNUM		)) 	AS SINGLE_SELLSUM  ");
		ppSql.append("  		,DECODE(SUM(BS_SELLNUM_L),0,0,NULL,0,SUM(BS_SELLSUM_L	)*5.0000/SUM(BS_SELLNUM_L	)) 	AS SINGLE_SELLSUM_L  ");
		ppSql.append("  		,SUM(BS_TERMSTOCKNUM)		 	AS BS_TERMSTOCKNUM  ");
		ppSql.append("  		,SUM(BS_TERMSTOCKNUM_L)		 	AS BS_TERMSTOCKNUM_L  ");
		ppSql.append("  	FROM  ");
		ppSql.append("  		(  ");
		ppSql.append("  			SELECT  ");
		ppSql.append("  				C_BRAND  ");
		ppSql.append("  				,C_CIG		  ");										
		ppSql.append("  				,SUM(PRINT_NUM1 	- RBACK_NUM1	) 			AS BS_SELLNUM  ");
		ppSql.append("  				,SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	) 			AS BS_SELLNUM_L  ");
		ppSql.append("  				,SUM(TERM_STOCK1 							) 			AS BS_TERMSTOCKNUM  ");
		ppSql.append("  				,SUM(TERM_STOCK1_P 							) 			AS BS_TERMSTOCKNUM_L  ");
		ppSql.append("  				,SUM(PRINT_SUM		- RBACK_SUM		) 			AS BS_SELLSUM  ");
		ppSql.append("  				,SUM(PRINT_SUM_P 	- RBACK_SUM_P	) 			AS BS_SELLSUM_L  ");
		ppSql.append("			FROM NICK_K_TJBS_Y_ALL    NK,NICK_TJYY_CIGARETTE SC         ");
		ppSql.append("		WHERE NK.C_BRAND = SC.cig_codebar and      ");
		ppSql.append("		((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))        ");
		ppSql.append("  				AND C_CLASS = '").append(code).append("'");//--参数
		ppSql.append("  				AND Y = YEAR(CURRENT DATE-1 DAYS)     ");
		ppSql.append("  				GROUP BY C_BRAND, C_CIG  ");
		ppSql.append("  		) K  ");
		ppSql.append("  			,(SELECT C_BRAND, C_CIG FROM CIG_PROPERTY_DIV) P  ");
		ppSql.append("  		WHERE 1=1   ");
		ppSql.append("  			AND K.C_BRAND = P.C_BRAND  ");
		ppSql.append("  		GROUP BY P.C_CIG  ");
		ppSql.append("  		WITH ROLLUP  ");
		ppSql.append("  		HAVING  ");
		ppSql.append("  			(  ");
		ppSql.append("  				SUM(BS_SELLNUM)	            <> 0  ");
		ppSql.append("  				OR SUM(BS_SELLNUM_L)	    <> 0  ");
		ppSql.append("  				OR SUM(BS_SELLSUM)	        <> 0  ");
		ppSql.append("  				OR SUM(BS_SELLSUM_L)	    <> 0  ");
		ppSql.append("  				OR SUM(BS_TERMSTOCKNUM)	    <> 0  ");
		ppSql.append("  				OR SUM(BS_TERMSTOCKNUM_L)   <> 0  ");
		ppSql.append("  			)  ");
		ppSql.append("  		");
		ppSql.append("  ) B  ");
		ppSql.append("  	ON I.C_CIG = B.C_CIG  ");
		ppSql.append("  LEFT JOIN  ");
		ppSql.append("  (  ");
		ppSql.append("  	SELECT  ");
		ppSql.append("  		NVL(C_CIG, '00') 		AS C_CIG  ");
		ppSql.append("  		,SUM(INBS_TRANSIT_NUM) 	AS TRANSIT_NUM  ");
		ppSql.append("		FROM N_K_TJIN_IB_IR   NK,NICK_TJYY_CIGARETTE SC             ");
		ppSql.append("		WHERE 1=1              ");
		ppSql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
		ppSql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))         ");
		ppSql.append("  		AND C_CLASS = '").append(code).append("'");//--参数
		ppSql.append("  		AND D_DAY = DC_FUNC_DTOSTR(CURRENT DATE-1 DAYS)  ");
		ppSql.append("  	GROUP BY C_CIG  ");
		ppSql.append("  	WITH ROLLUP  ");
		ppSql.append("  ) T  ");
		ppSql.append("  	ON I.C_CIG = T.C_CIG  ");
		ppSql.append("  LEFT JOIN  ");
		ppSql.append("  (  ");
		ppSql.append("  	SELECT  ");
		ppSql.append("  		NVL(C_CIG, '00') AS C_CIG  ");
		ppSql.append("  		,SUM(INBS_TRANSIT_NUM) AS TRANSIT_NUM_L  ");
		ppSql.append("		FROM N_K_TJIN_IB_IR   NK,NICK_TJYY_CIGARETTE SC             ");
		ppSql.append("		WHERE 1=1              ");
		ppSql.append("		and NK.C_BRAND = SC.cig_codebar and      ");
		ppSql.append("		((d_month<='201712' AND IS_NRT='1' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (d_month>='201801' AND is_innx='1'))         ");
		ppSql.append("  		AND C_CLASS = '").append(code).append("'");//--参数
		ppSql.append("  		AND D_DAY = DC_FUNC_DTOSTR(CURRENT DATE-1 DAYS -1 YEARS)  ");
		ppSql.append("  	GROUP BY C_CIG  ");
		ppSql.append("  	WITH ROLLUP  ");
		ppSql.append("  ) TL  ");
		ppSql.append("  	ON I.C_CIG = TL.C_CIG  ");
		ppSql.append("  LEFT JOIN (SELECT DISTINCT C_CIG, CIG_MARKNAME FROM VIEW_NICK_CIG_PROPERTY) C  ");
		ppSql.append("  	ON I.C_CIG = C.C_CIG  ");
		ppSql.append("  ORDER BY DECIMAL(PRODUCENUM1) DESC  ");
		
		log.info("总量情况结构模块表格下钻品牌sql=="+ppSql);
		
		return ppSql.toString();
	}

	
	/**
	 * 总量情况、结构模块<p>表格数据下钻品牌信息的查询
	 * @param dbName
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<PriceClassVO> getPPTableInfo(String code,String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<PriceClassVO> list = new ArrayList<PriceClassVO>();
		try {
			sql = getSqlPPTable(code);
			list = dbbean.executeQuery(sql, PriceClassVO.class.getName());
		} catch (Exception e) {
			log.error("getPPTableInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}

}
