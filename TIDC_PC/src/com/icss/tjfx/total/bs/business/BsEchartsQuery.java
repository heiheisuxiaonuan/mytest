package com.icss.tjfx.total.bs.business;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.total.bs.vo.ModelVO;
import com.icss.tjfx.total.bs.vo.SellUpNumVO;
import com.icss.tjfx.total.bs.vo.SellUpTopThreeVO;

/**
 * 总量情况商业模块左侧echarts数据查询类
 * @author lcx
 *
 */
public class BsEchartsQuery extends BaseBusiness{

	private static Log log = LogFactory.getLog(BsEchartsQuery.class);
	
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)throws Exception {
		Gson json = new Gson();
		ModelVO modelVO = getBsEcharts();
		String s = json.toJson(modelVO);
		log.info("总量模块商业echarts数据查询结果=="+s);
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	public ModelVO getBsEcharts(){
		ModelVO modelVO = new ModelVO();
		List<SellUpTopThreeVO> list = getBsTopThreeInfo("dw");
		SellUpNumVO sellUpNumVO = getSellUpNumInfo("dw");
		modelVO.setList(list);
		modelVO.setSellUpNumVO(sellUpNumVO);
		return modelVO;
	}
	
	
	/**
	 * 总量情况、商业模块<p>销量增长前三查询sql获取
	 * @return String
	 */
	public static String getSqlBsTopThree(){
		StringBuffer bsSingleSql = new StringBuffer();
		bsSingleSql.append("   	SELECT														 ");		
		bsSingleSql.append("   	PROV_NAME													 ");
		bsSingleSql.append("   	,DECIMAL(ROUND(DECODE(SELL_NUM_L,0,0,NULL,0,SELLNUM_GROWTH*100.0000/SELL_NUM_L),2),16,2) AS SELLNUM_GROWTH	 ");
		bsSingleSql.append("   	FROM														 ");
		bsSingleSql.append("   	(														 ");
		bsSingleSql.append("   	SELECT														 ");
		bsSingleSql.append("   		I_PROVINCE												 ");
		bsSingleSql.append("   		,SUM(PRINT_NUM1_P - RBACK_NUM1_P) AS SELL_NUM_L							 ");
		bsSingleSql.append("   		,SUM((PRINT_NUM1 - RBACK_NUM1)-(PRINT_NUM1_P - RBACK_NUM1_P))   AS SELLNUM_GROWTH		 ");
		bsSingleSql.append("			FROM NICK_K_TJBS_Y_ALL    NK,NICK_TJYY_CIGARETTE SC         ");
		bsSingleSql.append("		WHERE NK.C_BRAND = SC.cig_codebar and      ");
		bsSingleSql.append("		((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))        ");
		bsSingleSql.append("   		AND I_PROVINCE <> '880000'										 ");
		bsSingleSql.append("   		AND Y =year(current date -1 day)									 ");
		bsSingleSql.append("   	GROUP BY I_PROVINCE												 ");
		bsSingleSql.append("   	) K														 ");
		bsSingleSql.append("   	LEFT JOIN MA_T_PROV_ORG F											 ");
		bsSingleSql.append("   	ON K.I_PROVINCE = F.PROV_CODE											 ");
		bsSingleSql.append("   	ORDER BY SELLNUM_GROWTH DESC										 ");
		bsSingleSql.append("   	FETCH FIRST 3 ROWS ONLY												 ");
		bsSingleSql.append("   	WITH UR														 ");

		log.info("总量情况商业模块销量前三图查询sql为："+bsSingleSql);
		return bsSingleSql.toString();
	}
	
	/**
	 * 总量情况、商业模块<P>销量增长前三数据信息的查询
	 * @param dbName
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List<SellUpTopThreeVO> getBsTopThreeInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<SellUpTopThreeVO> list = new ArrayList<SellUpTopThreeVO>();
		try {
			sql = getSqlBsTopThree();
			list = dbbean.executeQuery(sql, SellUpTopThreeVO.class.getName());
		} catch (Exception e) {
			log.error("getBsTopThreeInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	
	/**
	 * 总量情况、商业模块<p>销量增长地区总数查询sql获取
	 * @return String
	 */
	public static String getSqlBsUpNum(){
		StringBuffer bsUpNumSql = new StringBuffer();
		bsUpNumSql.append("  	SELECT																		");
		bsUpNumSql.append("  	SUM(CASE WHEN SELLNUM_GROWTH > 0 THEN 1 ELSE 0 END) AS GROWTH_NUM										");
		bsUpNumSql.append("  	,COUNT(I_PROVINCE) AS ALL_NUM															");
		bsUpNumSql.append("  	FROM																		");
		bsUpNumSql.append("  	(																		");
		bsUpNumSql.append("  	SELECT																		");
		bsUpNumSql.append("  		I_PROVINCE																");
		bsUpNumSql.append("  		,DECIMAL(ROUND(SUM((PRINT_NUM1 - RBACK_NUM1)-(PRINT_NUM1_P - RBACK_NUM1_P))*1.0000/50000,2),16,2)   AS SELLNUM_GROWTH	");
		bsUpNumSql.append("			FROM NICK_K_TJBS_Y_ALL    NK,NICK_TJYY_CIGARETTE SC         ");
		bsUpNumSql.append("		WHERE NK.C_BRAND = SC.cig_codebar and      ");
		bsUpNumSql.append("		((D_YEAR<='2017' AND CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND is_bsnx='1'))        ");
		bsUpNumSql.append("  		AND I_PROVINCE <> '880000'														");
		bsUpNumSql.append("  		AND Y = YEAR(CURRENT DATE-1 DAYS)														");
		bsUpNumSql.append("  	GROUP BY I_PROVINCE																");
		bsUpNumSql.append("  	)																		");
		bsUpNumSql.append("  	WITH UR																	");
		
		log.info("总量情况商业模块销量增长地区总数查询sql为："+bsUpNumSql);
		return bsUpNumSql.toString();
	}
	
	/**
	 * 总量情况、商业模块<P>销量增长地区总数数据信息的查询
	 * @param dbName
	 * @return 
	 */
	public SellUpNumVO  getSellUpNumInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		SellUpNumVO sellUpNumVO = new  SellUpNumVO();
		try {
			sql = getSqlBsUpNum();
			sellUpNumVO = (SellUpNumVO)dbbean.executeQuerySingle(sql, SellUpNumVO.class.getName());
		} catch (Exception e) {
			log.error("getSellUpNumInfo查询方法sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return sellUpNumVO;
	}

	
}
