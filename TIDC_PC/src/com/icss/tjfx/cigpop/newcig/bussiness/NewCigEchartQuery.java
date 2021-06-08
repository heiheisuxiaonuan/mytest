package com.icss.tjfx.cigpop.newcig.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.cigpop.newcig.vo.ModelVO;
import com.icss.tjfx.cigpop.newcig.vo.NewCigSellNumVO;
import com.icss.tjfx.cigpop.newcig.vo.NewCigSellTopVO;

public class NewCigEchartQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(NewCigEchartQuery.class);
	
	/**
	 * 新品销量前三
	 * @param dbName
	 * @return 
	 */
	public List<NewCigSellTopVO> getTopThreeInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<NewCigSellTopVO> top3List = new ArrayList<NewCigSellTopVO>();
		try {
			sql = getSqlCigEchartsXLQSList();
			top3List = dbbean.executeQuery(sql, NewCigSellTopVO.class.getName());
		} catch (Exception e) {
			log.error("getTopThreeInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return top3List;
	}

	/**
	 * 获取查询卷烟品牌新品销量前三左侧Echarts数据的sql(新品烟模块)
	 * @return 
	 */
	public static String getSqlCigEchartsXLQSList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" 	SELECT																						");
		sql.append(" 	BRAND_NAME AS BRAND_NAME																			");
		sql.append(" 	,DECIMAL(ROUND(SELLNUM*1.0000/50000,2),16,2)   AS BS_SELLNUM															");
		sql.append(" 	FROM																						");
		sql.append(" 	(																						");
		sql.append(" 	SELECT 																						");
		sql.append(" 	I.C_BRAND																	AS C_BRAND			");
		sql.append(" 	,BRAND_NAME																					");
		sql.append(" 	,NVL(SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	), 0)		AS PRODUCENUM							");
		sql.append(" 	,NVL(SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	), 0) 		AS PRODUCENUM_L							");
		sql.append(" 	FROM NICK_K_TJIN_Y_ALL I, DW_T_NEW_PRODUCT N	,NICK_TJYY_CIGARETTE SC 									");
		sql.append(" 	WHERE 1=1																					");
		sql.append(" 	AND I.C_BRAND = SC.cig_codebar																");
		sql.append(" 	and ((D_YEAR<='2017' AND IS_NRT='1' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_innx='1'))			");
		sql.append(" 	AND I.C_BRAND = N.C_BRAND																			");
		sql.append(" 	AND  y=year(current date -1 day)																			");
		sql.append(" 	GROUP BY I.C_BRAND,BRAND_NAME																			");
		sql.append(" 	HAVING																						");
		sql.append(" 	(																						");
		sql.append(" 	SUM(PRINTNUM1 		- REJECTNUM1 	+ REPEATNUM1 	- JUMPNUM1	)<>0										");
		sql.append(" 	OR SUM(PRINTNUM1_P 	- REJECTNUM1_P 	+ REPEATNUM1_P 	- JUMPNUM1_P	)<>0											");
		sql.append(" 	)																						");
		sql.append(" 	) I,																						");
		sql.append(" 	(																						");
		sql.append(" 	SELECT 																						");
		sql.append(" 	C_BRAND																						");
		sql.append(" 	,SUM(PRINT_NUM1 	- RBACK_NUM1	)   AS SELLNUM															");
		sql.append(" 	FROM NICK_K_TJBS_Y_ALL 	NK,NICK_TJYY_CIGARETTE SC				");
		sql.append(" 	WHERE 1=1																					");
		sql.append(" 	AND NK.C_BRAND = SC.cig_codebar									");
		sql.append(" 	and ((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))					");
		sql.append(" 	AND C_BRAND IN (SELECT C_BRAND FROM DW_T_NEW_PRODUCT)																");
		sql.append(" 	AND Y = YEAR(CURRENT DATE-1 DAYS)																		");
		sql.append(" 	GROUP BY C_BRAND																				");
		sql.append(" 	) B																						");
		sql.append(" 	WHERE 1=1																					");
		sql.append(" 	AND I.C_BRAND = B.C_BRAND																			");
		sql.append(" 	ORDER BY SELLNUM DESC																				");
		sql.append(" 	FETCH FIRST 3 ROWS ONLY																				");
		sql.append(" 	WITH UR																						");

		
		log.info("查询卷烟品牌新品销量前三左侧Echarts数据为："+sql);
		return sql.toString();
	}
	
	/**
	 * 本期同期
	 * @param dbName
	 * @return 
	 */
	public NewCigSellNumVO getSellNumInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		NewCigSellNumVO newCigSellNumVO = new NewCigSellNumVO();
		try {
			sql = getSqlCigEchartsNew();
			newCigSellNumVO = (NewCigSellNumVO)dbbean.executeQuerySingle(sql, NewCigSellNumVO.class.getName());
		} catch (Exception e) {
			log.error("getSellNumInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return newCigSellNumVO;
	}
	
	/**
	 * 获取查询卷烟品牌新品销量左侧Echarts数据的sql(新品烟模块)
	 * @return 
	 */
	public static String getSqlCigEchartsNew(){
		StringBuffer sql = new StringBuffer();
		sql.append("  	SELECT													  ");
		sql.append("  	DECIMAL(ROUND(SUM(PRINT_NUM1 	- RBACK_NUM1	)*1.0000/50000,2),16,2)   AS SELLNUM	  ");
		sql.append("  	,DECIMAL(ROUND(SUM(PRINT_NUM1_P 	- RBACK_NUM1_P	)*1.0000/50000,2),16,2)   AS SELLNUM_L	  ");
		sql.append("  	FROM NICK_K_TJBS_Y_ALL  NK,NICK_TJYY_CIGARETTE SC			  ");
		sql.append("  	WHERE 1=1												  ");
		sql.append("  	AND NK.C_BRAND = SC.cig_codebar and ((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1')) 	  ");
		sql.append("  	AND C_BRAND IN (SELECT C_BRAND FROM DW_T_NEW_PRODUCT)							  ");
		sql.append("  	AND Y = YEAR(CURRENT DATE-1 DAYS)										  ");
		sql.append("  	WITH UR                              ");
		
		log.info("查询卷烟品牌新品销量左侧Echarts数据数据SQL为："+sql);
		return sql.toString();
	}
	
	public ModelVO getNewCigVO(){
		ModelVO mvo = new ModelVO();
		//销量前三
		List<NewCigSellTopVO> top3List = getTopThreeInfo("dw");
		//本期同期
		NewCigSellNumVO newCigSellNumVO = getSellNumInfo("dw");
		mvo.setList(top3List);
		mvo.setNewCigSellNumVO(newCigSellNumVO);
		return mvo;
	}


	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		ModelVO mvo = getNewCigVO();
		Gson gson = new Gson();
		String s = gson.toJson(mvo);
		log.info("卷烟品牌新品左侧Echarts业务查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	

}
