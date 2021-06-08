package com.icss.tjfx.total.structure.business;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.total.structure.vo.ModelVO;
import com.icss.tjfx.total.structure.vo.PriceClassPieTransVO;
import com.icss.tjfx.total.structure.vo.PriceClassPieVO;
import com.icss.tjfx.total.structure.vo.SellNumBarVO;
import com.icss.tjfx.total.structure.vo.SingleVO;

/**
 * 总量情况结构模块左侧echarts数据查询类
 * @author lcx
 *
 */
public class StructureEchartsQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(StructureEchartsQuery.class);
	
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)throws Exception {
		Gson json = new Gson();
		ModelVO modelVo =getStructureEcharts();
		String s = json.toJson(modelVo);
		log.info("总量情况echarts的 json数据模型："+modelVo);
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	
	public ModelVO getStructureEcharts(){
		ModelVO modelVo = new ModelVO();
		//汇报说拿掉的销量柱图
		//SellNumBarVO sellNumBarVO =  getSellNumBarInfo("dw");
		SingleVO singleVO = getSingleBarInfo("dw");
		PriceClassPieVO priceClassPieVO = getPriceClassPieInfo("dw");
		modelVo.setPriceClassPieVO(priceClassPieVO);
		//modelVo.setSellNumBarVO(sellNumBarVO);
		modelVo.setSingleVO(singleVO);
		return modelVo;
	}
	
	/**
	 * 总量情况、结构模块<p>销量柱状图查询sql获取
	 * @return String
	 */
	public static String getSqlSellNum(){
		StringBuffer sellNumSql = new StringBuffer();
		
		sellNumSql.append("   SELECT    ");
		sellNumSql.append("   	DECIMAL(ROUND(SUM(PRINT_NUM1 - RBACK_NUM1)*1.0000/50000, 2), 16,2) AS sellNum_bq    ");	//--本期销量
		sellNumSql.append("   	,DECIMAL(ROUND(SUM(PRINT_NUM1_P - RBACK_NUM1_P)*1.0000/50000, 2), 16,2) AS sellNum_tq    ");	//--同期销量
		sellNumSql.append("			FROM NICK_K_TJBS_Y_ALL    NK,NICK_TJYY_CIGARETTE SC         ");
		sellNumSql.append("		WHERE NK.C_BRAND = SC.cig_codebar and      ");
		sellNumSql.append("		((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))        ");
		sellNumSql.append("   	AND C_CLASS IS NOT NULL   ");
		sellNumSql.append("   	AND C_CLASS <> ''   ");
		sellNumSql.append("   	AND Y = YEAR(CURRENT DATE-1 DAYS)   ");
		
		log.info("总量情况结构模块销量柱状图查询sql为："+sellNumSql);
		return sellNumSql.toString();
	}
	
	/**
	 * 总量情况、结构模块<p>销量柱状图数据信息的查询
	 * @param dbName
	 * @return SellNumBarVO
	 */
	public SellNumBarVO getSellNumBarInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		SellNumBarVO sellNumBarVO = new SellNumBarVO();
		try {
			sql = getSqlSellNum();
			sellNumBarVO = (SellNumBarVO)dbbean.executeQuerySingle(sql, SellNumBarVO.class.getName());
		} catch (Exception e) {
			log.error("getSellNumBarInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return sellNumBarVO;
	}
	
	
	/**
	 * 总量情况、结构模块<p>单箱柱状图查询sql获取
	 * @return String
	 */
	public static String getSqlSingle(){
		StringBuffer singleSql = new StringBuffer();
		singleSql.append(" 	SELECT																									");
		singleSql.append(" 	DECIMAL(ROUND(DECODE(SUM(PRINT_NUM1 - RBACK_NUM1),0,0,NULL,0,SUM(PRINT_SUM - RBACK_SUM)*5.0000/SUM(PRINT_NUM1 - RBACK_NUM1))		,2),16,2) as single_bq			");
		singleSql.append(" 	,DECIMAL(ROUND(DECODE(SUM(PRINT_NUM1_P - RBACK_NUM1_P),0,0,NULL,0,SUM(PRINT_SUM_P - RBACK_SUM_P)*5.0000/SUM(PRINT_NUM1_P - RBACK_NUM1_P))	,2),16,2) as single_tq			");
		singleSql.append("  FROM NICK_K_TJBS_Y_ALL K, NICK_TJYY_CIGARETTE SC 			");
		singleSql.append("  WHERE 1=1		AND K.C_BRAND = SC.cig_codebar and							");
		singleSql.append("  ((D_YEAR<='2017' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_bsnx='1')) 				");
		singleSql.append(" 	AND K.C_CLASS IS NOT NULL																						");
		singleSql.append(" 	AND K.C_CLASS <> ''																							");
		singleSql.append(" 	AND I_PROVINCE <> '880000'																						");
		singleSql.append(" 	AND Y = YEAR(CURRENT DATE-1 DAYS)																					");
		singleSql.append(" 	WITH UR																									");

		
		log.info("总量情况结构模块单箱柱状图查询sql为："+singleSql);
		return singleSql.toString();
	}
	
	/**
	 * 总量情况、结构模块<P>单箱柱状图数据信息的查询
	 * @param dbName
	 * @return SingleVO
	 */
	public SingleVO getSingleBarInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		SingleVO singleVO = new SingleVO();
		try {
			sql = getSqlSingle();
			singleVO = (SingleVO)dbbean.executeQuerySingle(sql, SingleVO.class.getName());
		} catch (Exception e) {
			log.error("getSingleBarInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return singleVO;
	}
	
	
	/**
	 * 总量情况、结构模块<p>分类销量饼图查询sql获取
	 * @return String
	 */
	public static String getSqlPriceClass(){
		StringBuffer priceClassSql = new StringBuffer();
		
		priceClassSql.append(" 	SELECT									 ");	
		priceClassSql.append(" 	C_CLASS									 ");
		priceClassSql.append(" 	,NAME									 ");
		priceClassSql.append(" 	,DECIMAL(ROUND(BS_SELLNUM*1.0000/50000, 2), 16,2) AS BS_SELLNUM		 ");
		priceClassSql.append(" 	,DECIMAL(ROUND(BS_SELLNUM*100.0000/					 ");
		priceClassSql.append(" 	(									 ");
		priceClassSql.append(" 		SELECT SUM(PRINT_NUM1 - RBACK_NUM1)				 ");
		priceClassSql.append("			FROM NICK_K_TJBS_Y_ALL    NK,NICK_TJYY_CIGARETTE SC         ");
		priceClassSql.append("		WHERE NK.C_BRAND = SC.cig_codebar and      ");
		priceClassSql.append("		((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))        ");
		priceClassSql.append(" 			AND C_CLASS IS NOT NULL					 ");
		priceClassSql.append(" 			AND C_CLASS <> ''					 ");
		priceClassSql.append(" 			AND Y = YEAR(CURRENT DATE-1 DAYS)			 ");
		priceClassSql.append(" 	)									 ");
		priceClassSql.append(" 	, 2), 16,2) AS BS_SELLNUM_RATIO						 ");
		priceClassSql.append(" 	FROM									 ");
		priceClassSql.append(" 	(									 ");
		priceClassSql.append(" 	SELECT 									 ");
		priceClassSql.append(" 		C_CLASS								 ");
		priceClassSql.append(" 		,NAME								 ");
		priceClassSql.append(" 		,SUM(PRINT_NUM1 - RBACK_NUM1) AS BS_SELLNUM			 ");
		priceClassSql.append("  	FROM NICK_K_TJBS_Y_ALL	K	LEFT JOIN NICK_MA_T_CLASS_CODE	P  ON K.C_CLASS = P.CODE							 ");
		priceClassSql.append(" 		left join NICK_TJYY_CIGARETTE SC  ON K.C_BRAND = SC.cig_codebar  WHERE 1=1		 ");
		priceClassSql.append(" 	AND ((D_YEAR<='2017' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND SC.is_bsnx='1'))	 ");
		priceClassSql.append(" 		AND C_CLASS IS NOT NULL						 ");
		priceClassSql.append(" 		AND C_CLASS <> ''						 ");
		priceClassSql.append(" 		AND Y = YEAR(CURRENT DATE-1 DAYS)				 ");
		priceClassSql.append(" 	GROUP BY C_CLASS, NAME							 ");
		priceClassSql.append(" 	)									 ");
		priceClassSql.append(" 	WITH UR									 ");

		
		
		log.info("总量情况结构模块分类销量饼图查询sql为："+priceClassSql);
		return priceClassSql.toString();
	}
	
	/**
	 * 总量情况、结构模块<P>分类销量饼图数据信息的查询
	 * @param dbName
	 * @return SingleVO
	 */
	@SuppressWarnings("unchecked")
	public PriceClassPieVO getPriceClassPieInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<PriceClassPieTransVO> transList = new ArrayList<PriceClassPieTransVO>();
		PriceClassPieVO priceClassPieVO = new PriceClassPieVO();
		try {
			sql = getSqlPriceClass();
			transList = dbbean.executeQuery(sql, PriceClassPieTransVO.class.getName());
			priceClassPieVO.setOneClass(transList.get(0).getBS_SELLNUM());
			priceClassPieVO.setTowClass(transList.get(1).getBS_SELLNUM());
			priceClassPieVO.setThreeClass(transList.get(2).getBS_SELLNUM());
			priceClassPieVO.setFourClass(transList.get(3).getBS_SELLNUM());
			priceClassPieVO.setFiveClass(transList.get(4).getBS_SELLNUM());
			
			priceClassPieVO.setOnePercent(transList.get(0).getBS_SELLNUM_RATIO());
			priceClassPieVO.setTowPercent(transList.get(1).getBS_SELLNUM_RATIO());
			priceClassPieVO.setThreePercent(transList.get(2).getBS_SELLNUM_RATIO());
			priceClassPieVO.setFourPercent(transList.get(3).getBS_SELLNUM_RATIO());
			priceClassPieVO.setFivePercent(transList.get(4).getBS_SELLNUM_RATIO());
			
		} catch (Exception e) {
			log.error("getPriceClassPieInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return priceClassPieVO;
	}

	
}
