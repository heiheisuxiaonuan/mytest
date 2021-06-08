package com.icss.tjfx.cigpop.keypoint.bussiness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.dbbean.SessionFactory;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.cigpop.keypoint.vo.KeyPointPieVO;
import com.icss.tjfx.cigpop.keypoint.vo.KeyPointSellTopVO;
import com.icss.tjfx.cigpop.keypoint.vo.ModelVO;

/**
 * 卷烟品牌重点左侧echarts数据查询类
 * @author lcx
 *
 */
public class KeyPointEchartsQuery extends BaseBusiness{
	private static Log log = LogFactory.getLog(KeyPointEchartsQuery.class);
	@Override
	public HttpServletRequest handle(HttpServletRequest request)throws Exception {
		long start = System.currentTimeMillis();
		log.info("调用handle时间：" + new java.util.Date(start));
		/*Gson json = new Gson();
		ModelVO mov = new ModelVO();
		KeyPointPieVO keyPointPieVO = getKeyPointPieInfo("db2_dw");
		List<KeyPointSellTopVO> list = getKeyPointTopInfo("db2_dw");
		mov.setKeyPointPieVO(keyPointPieVO);
		mov.setSellTopList(list);
		String s = json.toJson(mov);
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		long endTime=System.currentTimeMillis();
		log.info("listend==="+new java.util.Date(endTime));
		
		long endList = System.currentTimeMillis();
		long listtime = endList -start;
		log.info("list查询完成时间：" + listtime/1000 +"秒");
		return request;*/
		Gson json = new Gson();
		ModelVO mov = getKeyPointEchartsVO();
		String s = json.toJson(mov);
		log.info("卷烟品牌重点echarts数据查询结果=="+s);
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	
	public ModelVO getKeyPointEchartsVO() throws Exception{
		ModelVO mov = new ModelVO();
		KeyPointPieVO keyPointPieVO = getKeyPointPieInfo("db2_dw");
		List<KeyPointSellTopVO> list = getKeyPointTopInfo("db2_dw");
		mov.setKeyPointPieVO(keyPointPieVO);
		mov.setSellTopList(list);
		return mov;
	}
	
	/**
	 * 卷烟品牌、重点模块<p>占比重环图查询sql获取
	 * @return
	 */
	public static String getSqlPie(){
		StringBuffer sql = new StringBuffer();
		/*sql.append(" 		SELECT														 ");
		sql.append(" 		TO_CHAR(DECIMAL(ROUND(SUM(K.BS_SELLNUM)*1.0000/50000, 2), 16, 2),'999990.99') AS sellNum			 ");
		sql.append(" 		,TO_CHAR(DECIMAL(ROUND(SUM(K.BS_SELLSUM)*1.0000/10000, 2), 16, 2),'999990.99') AS sellAmount			 ");
		sql.append(" 		,SUM(K.BRAND_NBER)								AS brandNum			 ");
		sql.append(" 		,TO_CHAR(DECIMAL(ROUND(SUM(P.BS_SELLNUM)*1.0000/50000, 2), 16, 2),'999990.99') AS sellNumAll    		 ");
		sql.append(" 		,TO_CHAR(DECIMAL(ROUND(SUM(P.BS_SELLSUM)*1.0000/10000, 2), 16, 2),'999990.99') AS sellAmountAll			 ");
		sql.append(" 		,SUM(P.BRAND_NBER)								AS brandNumAll			 ");
		sql.append(" 		FROM														 ");
		sql.append(" 		(SELECT														 ");
		sql.append(" 		SUM(PRINT_NUM1-RBACK_NUM1) AS BS_SELLNUM   								 ");
		sql.append(" 		,SUM(PRINT_SUM-RBACK_SUM) AS BS_SELLSUM									 ");
		sql.append(" 		,COUNT(DISTINCT(K.C_BRAND)) AS BRAND_NBER									 ");
		sql.append(" 		FROM NICK_K_TJBS_Y_ALL K,cig_property_div P								 ");
		sql.append(" 		WHERE 1=1													 ");
		sql.append(" 		 AND K.C_BRAND=P.C_BRAND											 ");
		sql.append(" 		AND IS_MAIN_BRAND='1'												 ");
		sql.append(" 		AND C_CLASS <> '06'												 ");
		sql.append(" 		AND Y = YEAR(CURRENT DATE-1 DAYS) 										 ");
		sql.append(" 		AND (PRINT_NUM1-RBACK_NUM1)>0 										 ");
		sql.append(" 		) K,														 ");
		sql.append(" 		(SELECT														 ");
		sql.append(" 		SUM(PRINT_NUM1-RBACK_NUM1) AS BS_SELLNUM   								 ");
		sql.append(" 		,SUM(PRINT_SUM-RBACK_SUM) AS BS_SELLSUM									 ");
		sql.append(" 		,COUNT(DISTINCT(K.C_BRAND)) AS BRAND_NBER									 ");
		sql.append(" 		FROM NICK_K_TJBS_Y_ALL K,cig_property_div P								 ");
		sql.append(" 			WHERE 1=1												 ");
		sql.append(" 			 AND K.C_BRAND=P.C_BRAND										 ");
		sql.append(" 			 AND C_CLASS <> '06'											 ");
		sql.append(" 		    AND (PRINT_NUM1+ SELF_SUPP_NUM1-RBACK_NUM1)>0 										 ");
		sql.append(" 			AND Y = YEAR(CURRENT DATE-1 DAYS)  									 ");
		sql.append(" 			) P													 ");
		sql.append(" 		WITH UR														 ");
		*/		
		sql.append(" 		SELECT										 ");										
		sql.append(" 	 	TO_CHAR(DECIMAL(ROUND(SUM(BS_SELLNUM_Y_A)*1.0000/50000, 2), 16, 2),'999990.99') AS sellNum   ");                                               		
		sql.append(" 	 	,TO_CHAR(DECIMAL(ROUND((SUM(BS_SELLNUM)*1.0000/50000), 2), 16, 2),'999990.99') AS sellNumAll	 ");												
		sql.append(" 	 	,TO_CHAR(DECIMAL(ROUND(SUM(BS_SELLSUM_Y_A)*1.0000/10000, 2), 16, 2),'999990.99') AS sellAmount	 ");											
		sql.append(" 	 	,(TO_CHAR(DECIMAL(ROUND(SUM(BS_SELLSUM)*1.0000/10000, 2), 16, 2),'999990.99')) AS sellAmountAll		 ");											
		sql.append(" 	 	,SUM(BRADN_NUM)															AS brandNum		 ");												
		sql.append(" 	 	,SUM(BRAND_NBER)															AS brandNumAll	 ");												
		sql.append(" 	 	FROM							 ");													
		sql.append(" 	 	(							 ");													
		sql.append(" 	 	SELECT						 ");														
		 																					
		sql.append(" 	 	SUM(K.PRODUCENUM_Y_A) AS BS_SELLNUM_Y_A   	 ");														
		sql.append(" 	 	,SUM(S.BS_SELLNUM) AS BS_SELLNUM			 ");													
		sql.append(" 	 	,SUM(K.BS_SELLSUM_Y_A) AS BS_SELLSUM_Y_A  	 ");														
		sql.append(" 	 	,SUM(S.BS_SELLSUM) AS  BS_SELLSUM			 ");													
		sql.append(" 	 	,SUM(A.BRADN_NUM)	AS BRADN_NUM		 ");														
		sql.append(" 	 	,SUM(B.BRAND_NBER) AS BRAND_NBER		 ");														
		sql.append(" 	 	FROM (SELECT 							 ");												
		sql.append(" 	 	SUM(PRODUCENUM_Y_A) AS PRODUCENUM_Y_A		 ");														
		sql.append(" 	 	,SUM(BS_SELLSUM_Y_A) AS BS_SELLSUM_Y_A		 ");														
		sql.append(" 	 	FROM							 ");													
		sql.append(" 	 	(									 ");											
		sql.append(" 	 	SELECT									 ");											
		sql.append(" 	 	SUM(BS_SELLNUM) AS PRODUCENUM_Y_A        ");															
		sql.append(" 	 	,SUM(BS_SELLSUM) AS BS_SELLSUM_Y_A		 ");														
		sql.append(" 	 	from									 ");											
		  sql.append(" 	 NICK_K_DS_TJIB_Y_ALL K,cig_property_div P  ,NICK_TJYY_CIGARETTE SC	 ");														
		 	sql.append(" 	 WHERE	1=1	AND K.C_BRAND = SC.cig_codebar and		 ");											
		 	sql.append(" 	  ((D_YEAR<='2017' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_bsnx='1'))	 ");									
		 	sql.append(" 	 	AND K.C_BRAND = P.C_BRAND					 ");												
		 	sql.append(" 	 	AND IS_MAIN_BRAND ='1'						 ");												
		 	sql.append(" 	 	AND Y = year(current date -1 DAYS) 		 ");														
		 	sql.append(" 	 	and ( PRODUCENUM <> 0 			 ");															
		 	sql.append(" 	 	or BS_SELLNUM <> 0 				 ");														
		 	sql.append(" 	 	or IN_TERMSTOCKNUM <> 0			 ");															
		 	sql.append(" 	 	or BS_TERMSTOCKNUM <> 0)		 ");															
		 																					
		 	sql.append(" 		)						 ");														
		 	sql.append(" 	) K,(SELECT				 ");															
		 	sql.append(" 		SUM(PRINT_NUM1-RBACK_NUM1) AS BS_SELLNUM  ");  															
		 	sql.append(" 		,SUM(PRINT_SUM-RBACK_SUM) AS BS_SELLSUM   ");																														
		 	 sql.append(" FROM NICK_K_TJBS_Y_ALL K,cig_property_div P	,NICK_TJYY_CIGARETTE SC  ");														
			 	sql.append(" 	 WHERE 1=1	AND K.C_BRAND=P.C_BRAND		AND K.C_BRAND = SC.cig_codebar and	 ");											
			 	sql.append(" 	 ((D_YEAR<='2017' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_bsnx='1'))		 ");						
		 	sql.append(" 		AND Y = YEAR(CURRENT DATE-1 DAYS) 		 ");														
		 	sql.append(" 	 	AND (PRINT_NUM1 + SELF_SUPP_NUM1-RBACK_NUM1)>0 	 ");														
		 																					
		 	sql.append(" 	 	) S								 ");												
		 	sql.append(" 	 	,(select count(1) as BRADN_NUM	 ");																
		 	sql.append(" 	 	from nick_stma_cigarette_mdm a,NICK_TJYY_CIGARETTE b	 ");														
		 	sql.append(" 	 	where a.cig_barcarrier ='02' 			 ");														
		 	sql.append(" 	 	and a.CIG_BARSTATUS = '01'					 ");												
		 	sql.append(" 	 	and is_bsnx='1' ");														
		 	sql.append(" 	 	and a.CIG_IMPORTFLAG = '0'					 ");												
		 	sql.append(" 	 	and a.cig_codebar=b.cig_codebar					 ");												
		 	sql.append(" 	 	and a.cig_trademark in(select trade_code from nick_STMA_BRAND_DOUBLE15 where  M = MONTH(CURRENT DATE-1 DAYS) ");
		 	sql.append(" AND Y = YEAR(CURRENT DATE-1 DAYS) ");
		 	sql.append(" group by trade_code) ");								
		 	sql.append(" 	 	) A, ");
		 	sql.append(" 		(select count(*) as BRAND_NBER ");
		 	sql.append(" 	from nick_stma_cigarette_mdm a,NICK_TJYY_CIGARETTE b ");
		 	sql.append(" 	where a.cig_barcarrier ='02'  ");
		 	sql.append(" 		and a.CIG_BARSTATUS = '01' ");
		 	sql.append(" 		and is_bsnx='1'   ");
		 	sql.append(" 		and a.CIG_IMPORTFLAG = '0' ");
		 	sql.append(" 		and a.cig_codebar=b.cig_codebar ");
		 	sql.append(" 		and substr(char(b.CIG_RECORDDATE),1,7)<=rtrim(char(YEAR(CURRENT DATE-1 DAYS))) || '-' || rtrim(char(MONTH(CURRENT DATE-1 DAYS))) ");
		 	sql.append(" 	) B ");
		 	sql.append(" 		)			 ");																	
		 	sql.append(" 	WITH UR	 ");

		
		log.info("卷烟品牌重点模块环图查询sql:"+sql);
		return sql.toString();
	}
	
	/**
	 * 卷烟品牌、重点模块<p>占比重环图数据查询
	 * @param dbName
	 * @return 
	 */
	public KeyPointPieVO  getKeyPointPieInfo(String dbName) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		KeyPointPieVO keyPointPieVO = new KeyPointPieVO();
		try {
			sql = getSqlPie();
			conn = SessionFactory.getConn(dbName);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()){
				for(int i = 1; i <= col; i++){
					keyPointPieVO.setSellNum(rs.getString("sellNum"));
					keyPointPieVO.setSellNumAll(rs.getString("sellNumAll"));
					keyPointPieVO.setSellAmount(rs.getString("sellAmount"));
					keyPointPieVO.setSellAmountAll(rs.getString("sellAmountAll"));
					keyPointPieVO.setBrandNum(rs.getString("brandNum"));
					keyPointPieVO.setBrandNumAll(rs.getString("brandNumAll"));
				}
			}
			
		} catch (Exception e) {
			log.info("*************************");
			log.info("getKeyPointPieInfo查询方法sql执行异常:"+e);
			log.info("*************************");
			log.error("getKeyPointPieInfo查询方法sql执行异常", e);
			throw e;
		}finally{
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		
		}
		return keyPointPieVO;
	}
	
	/**
	 * 卷烟品牌、重点模块<p>销量前三查询sql获取
	 * @return
	 */
	public static String getSqlTop3(){
		StringBuffer sql = new StringBuffer();
		sql.append(" 	SELECT  P.CIG_MARKNAME AS CIG_NAME					");
		sql.append(" 	,DECIMAL(ROUND(SUM(PRINT_NUM1-RBACK_NUM1)*1.0000/50000,2),16,2) as BS_SELLNUM_Y_A	");
		 sql.append(" FROM NICK_K_TJBS_Y_ALL K,cig_property_div P	,NICK_TJYY_CIGARETTE SC  ");														
		 	sql.append(" 	 WHERE 1=1	AND K.C_BRAND=P.C_BRAND		AND K.C_BRAND = SC.cig_codebar and	 ");											
		sql.append(" 	 ((D_YEAR<='2017' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_bsnx='1'))		 ");	
		sql.append(" 	AND P.IS_MAIN_BRAND = 1										");
		sql.append(" 	AND K.C_CLASS <> '06'										");
		sql.append(" 	AND K.Y = YEAR(CURRENT DATE-1 DAYS)								");
		sql.append(" 	GROUP BY K.C_CIG, P.CIG_MARKNAME									");
		sql.append(" 	ORDER BY BS_SELLNUM_Y_A DESC									");
		sql.append(" 	FETCH FIRST 3 ROWS ONLY										");
		sql.append(" 	WITH UR												");

		log.info("卷烟品牌重点模块销量前三查询sql:"+sql);
		return sql.toString();
	}
	
	/**
	 * 卷烟品牌、重点模块<p>销量前三数据查询
	 * @param dbName
	 * @return 
	 */
	public List<KeyPointSellTopVO>  getKeyPointTopInfo(String dbName) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List<KeyPointSellTopVO> list = new ArrayList<KeyPointSellTopVO>();
		try {
			sql = getSqlTop3();
			conn = SessionFactory.getConn(dbName);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			while(rs.next()){
				KeyPointSellTopVO keyPointSellTopVO = new KeyPointSellTopVO();
				for(int i = 1; i <= col; i++){
					keyPointSellTopVO.setCIG_NAME(rs.getString("CIG_NAME"));
					keyPointSellTopVO.setBS_SELLNUM_Y_A(rs.getString("BS_SELLNUM_Y_A"));
				}
				list.add(keyPointSellTopVO);
			}
		} catch (Exception e) {
			log.info("*************************");
			log.info("getKeyPointTopInfo查询方法sql执行异常:"+e);
			log.info("*************************");
			log.error("getKeyPointTopInfo查询方法sql执行异常", e);
			throw e;
		}finally{
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		}
		return list;
	}

}
