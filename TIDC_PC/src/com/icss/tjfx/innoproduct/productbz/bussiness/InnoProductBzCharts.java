package com.icss.tjfx.innoproduct.productbz.bussiness;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.innoproduct.vo.BusinessSaleVo;

public class InnoProductBzCharts extends BaseBusiness{

	private static Log log = LogFactory.getLog(InnoProductBzCharts.class);
	

	/**
	 * 获取查询总销量sql()
	 * @return 
	 */
	public static String getSqlSell(){
		StringBuffer sql = new StringBuffer();
		
		/*sql.append(" SELECT ");
		sql.append(" DECIMAL(ROUND(SUM(PRINT_NUM1 - RBACK_NUM1	)*1.0000/50000, 2),16,2) 	AS BS_SELLNUM        ");   // 销量
		sql.append(" FROM NICK_K_TJBS_Y_ALL ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND C_CLASS <> '06' ");
		sql.append(" AND Y = YEAR(CURRENT DATE-1 DAYS) ");
		sql.append(" WITH UR ");*/
		
		sql.append(" SELECT ");
		sql.append(" DECIMAL(ROUND(SUM(PRINT_NUM1 - RBACK_NUM1	)*1.0000/50000, 2),16,2) 	AS BS_SELLNUM        ");   // 销量
		sql.append(" FROM NICK_K_TJBS_Y_ALL  K,NICK_TJYY_CIGARETTE SC ");
		sql.append(" WHERE K.C_BRAND = sc.cig_codebar ");
		sql.append(" AND  ((k.d_year<='2017' AND sc.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_bsnx='1')) AND Y = YEAR(CURRENT DATE-1 DAYS)   ");
		sql.append(" WITH UR ");
		log.info("查询创新产品左侧总销量数据SQL为："+sql);
		return sql.toString();
	}
	/**
	 * 获取查询细支烟销量sql()
	 * @return 
	 */
	public static String getSqlSellXZ(){
		StringBuffer sql = new StringBuffer();
		
		/*sql.append("    SELECT ");
		sql.append("    DECIMAL(ROUND(SUM(PRINT_NUM1 - RBACK_NUM1	)*1.0000/50000, 2),16,2) 											AS SINGLE_BS_SELLNUM      ");     //本期销量
		sql.append("    ,DECIMAL(ROUND(SUM(PRINT_NUM1_P - RBACK_NUM1_P)*1.0000/50000, 2),16,2) 											AS SINGLE_BS_SELLNUM_L    ");     //同期销量
		sql.append("    ,DECIMAL(ROUND((SUM(PRINT_NUM1 - RBACK_NUM1	)-SUM(PRINT_NUM1_P - RBACK_NUM1_P))*1.0000/50000, 2),16,2) 	AS SINGLE_BS_SELLNUM_GROWTH  ");  //量增量
		sql.append("      ,DECIMAL(ROUND(DECODE(SUM(PRINT_NUM1_P - RBACK_NUM1_P),0,0,NULL,0,(SUM(PRINT_NUM1 - RBACK_NUM1)-SUM(PRINT_NUM1_P - RBACK_NUM1_P))*100.0000/SUM(PRINT_NUM1 - RBACK_NUM1)),2),16,2) AS SINGLE_BS_SELLNUM_GROWTH_RATE		");//销量增率
		sql.append("    FROM NICK_K_TJBS_Y_ALL K ");
		sql.append("    ,(SELECT C_BRAND FROM NICK_VIEW_CIG_PROPERTY WHERE  IF_CIGA = '0' AND IS_BEAD = '1') P");
		sql.append("    WHERE 1=1   ");
		sql.append("    AND K.C_BRAND = P.C_BRAND   ");
		sql.append("    	AND C_CLASS <> '06'   ");
		sql.append("    	AND Y = YEAR(CURRENT DATE-1 DAYS)  ");
		sql.append("    WITH UR    ");*/
		
		sql.append("    SELECT ");
		sql.append("    DECIMAL(ROUND(SUM(PRINT_NUM1 - RBACK_NUM1	)*1.0000/50000, 2),16,2) 											AS SINGLE_BS_SELLNUM      ");     //本期销量
		sql.append("    ,DECIMAL(ROUND(SUM(PRINT_NUM1_P - RBACK_NUM1_P)*1.0000/50000, 2),16,2) 											AS SINGLE_BS_SELLNUM_L    ");     //同期销量
		sql.append("    ,DECIMAL(ROUND((SUM(PRINT_NUM1 - RBACK_NUM1	)-SUM(PRINT_NUM1_P - RBACK_NUM1_P))*1.0000/50000, 2),16,2) 	AS SINGLE_BS_SELLNUM_GROWTH  ");  //量增量
		sql.append("      ,DECIMAL(ROUND(DECODE(SUM(PRINT_NUM1_P - RBACK_NUM1_P),0,0,NULL,0,(SUM(PRINT_NUM1 - RBACK_NUM1)-SUM(PRINT_NUM1_P - RBACK_NUM1_P))*100.0000/SUM(PRINT_NUM1 - RBACK_NUM1)),2),16,2) AS SINGLE_BS_SELLNUM_GROWTH_RATE		");//销量增率
		sql.append("    FROM NICK_K_TJBS_Y_ALL K ,NICK_TJYY_CIGARETTE SC ");
		sql.append("    WHERE K.C_BRAND = SC.cig_codebar   ");
		sql.append("    AND ((K.d_year<='2017' AND SC.CIG_PRODUCTTYPE NOT in('05','06')) OR (k.d_year>='2018' AND sc.is_bsnx='1'))     ");
		sql.append("    AND is_bz='1'  AND CIG_IMPORTFLAG in ('0','3')     	     ");
		sql.append("    AND Y = YEAR(CURRENT DATE-1 DAYS)     ");
		sql.append("    WITH UR    ");
		
		log.info("查询创新产品左侧爆竹烟销量数据SQL为："+sql);
		return sql.toString();
	}
	
	public BusinessSaleVo getInnoProductCharts(){
		DBBeanBase dbbean = new DBBeanBase("dw");
		String sql = "";
		String sql1="";
		BusinessSaleVo businessAllSaleVo = new BusinessSaleVo();
		BusinessSaleVo businessSingleSaleVo = new BusinessSaleVo();
		try {
			sql = getSqlSell();
			sql1=getSqlSellXZ();
			businessAllSaleVo = (BusinessSaleVo)dbbean.executeQuerySingle(sql, BusinessSaleVo.class.getName()); 
			businessSingleSaleVo = (BusinessSaleVo)dbbean.executeQuerySingle(sql1, BusinessSaleVo.class.getName()); 
			
			log.info("总销量"+businessAllSaleVo.getBS_SELLNUM());
			log.info("爆竹烟本期销量"+businessSingleSaleVo.getSINGLE_BS_SELLNUM());
			log.info("爆竹烟同期销量"+businessSingleSaleVo.getSINGLE_BS_SELLNUM_L());
			log.info("爆竹烟增量"+businessSingleSaleVo.getSINGLE_BS_SELLNUM_GROWTH());
			log.info("爆竹烟增率"+businessSingleSaleVo.getSINGLE_BS_SELLNUM_GROWTH_RATE());
			
			businessSingleSaleVo.setBS_SELLNUM(businessAllSaleVo.getBS_SELLNUM());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询爆竹烟左侧Echarts数据sql执行异常:"+e);
			log.info("*************************");
			log.error("查询爆竹烟左侧Echarts数据sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return businessSingleSaleVo;
	}


	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		BusinessSaleVo businessSingleSaleVo = getInnoProductCharts();
		Gson gson = new Gson();
		String s = gson.toJson(businessSingleSaleVo);
		log.info("高价烟品牌业务查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
	

}
