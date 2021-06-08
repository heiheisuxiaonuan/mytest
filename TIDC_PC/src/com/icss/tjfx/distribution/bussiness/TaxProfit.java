package com.icss.tjfx.distribution.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.util.StringUtil;
import com.icss.tjfx.distribution.vo.TaxProfitVO;

/**
 * 分布图税利业务类
 * 
 * @author zhaofeng
 * @version 1.0
 * 
 * */
public class TaxProfit extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(TaxProfit.class);	
	
	//走势图品牌散点图业务
	public static String getSql(){
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT  ");
		sql.append("  		PROV_NAME AS provname ");
		sql.append("  		,DECIMAL(ROUND(IN_TAX_PROFIT,2),16,2) AS intax ");
		sql.append("  		,DECIMAL(ROUND(BS_TAX_PROFIT,2),16,2) AS bstax ");
		sql.append("  		,DECIMAL(ROUND(IB_TAX_PROFIT,2),16,2) AS ibtax ");
		sql.append("  	FROM DW_T_TAX_PROFIT K ");
		sql.append("  	RIGHT JOIN MA_T_PROV_ORG O ");
		sql.append("  		ON K.PROV_CODE = O.PROV_CODE ");
		sql.append("  	WHERE (Y,M) in(select y,m from DW_T_TAX_PROFIT order by y desc,m desc fetch first 1 rows only) ");
		sql.append("  	WITH UR    ");
		return sql.toString();
	}
	
	public List getTaxProfit(){
		DBBeanBase dbbean = new DBBeanBase("dw");
		//获取前台参数
		String sql = "";
		List list = new ArrayList();
		try {   
			sql = getSql();
			log.info("分布图税利业务数据处理sql: "+sql.toString());
			list = dbbean.executeQuery(sql, TaxProfitVO.class.getName()); 
		}catch (Exception e) {
			log.info("*************************");
			log.info("分布图税利业务sql执行异常:"+e);
			log.info("*************************");
			log.error("分布图税利业务sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List list = getTaxProfit();
		String result = StringUtil.getJson(list);
		log.info("分布图中烟工业地图业务查询结果："+result);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", result);
		
		return request;
	}
	
	public static void main(String[] args) {
		
	}
}
