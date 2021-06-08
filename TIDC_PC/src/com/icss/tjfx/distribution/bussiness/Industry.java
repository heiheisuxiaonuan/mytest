package com.icss.tjfx.distribution.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.util.StringUtil;
import com.icss.tjfx.distribution.vo.IndustryVO;

/**
 * 分布图中烟工业地图业务类
 * 
 * @author zhaofeng
 * @version 1.0
 * 
 * */
public class Industry extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(Industry.class);	
	
	//走势图品牌散点图业务
	public static String getSql(String provCode){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT PROV_NAME as PROVNAME,DECIMAL(ROUND(NVL(SUM(SELLNUM),0),2), 16, 2) AS SELLNUM  ");
		sql.append(" FROM ( SELECT C_BRAND,I_PROVINCE,SUM(PRINT_NUM1 - RBACK_NUM1)*1.0000/50000 AS SELLNUM ");
		sql.append(" FROM NICK_K_TJBS_Y_ALL K WHERE 1=1 and 1=1  AND I_PROVINCE <> '880000' AND Y = YEAR(CURRENT DATE-1 DAYS)");
		sql.append(" GROUP BY C_BRAND, I_PROVINCE ) K ");
		sql.append(" INNER JOIN NICK_VIEW_CIG_PROPERTY P ON K.C_BRAND = P.C_BRAND ");
		if(provCode!= null && !"".equals(provCode)){
			sql.append(" AND P.FACT_POPEDOM = '").append(provCode).append("'");//参数
		}
		sql.append(" RIGHT JOIN MA_T_PROV_ORG O ON K.I_PROVINCE = O.PROV_CODE WHERE 1=1 ");
		sql.append(" GROUP BY PROV_NAME order by SELLNUM WITH UR");
		
		return sql.toString();
	}
	
	public List getIndustyData(String provCode){
		DBBeanBase dbbean = new DBBeanBase("dw");
		String sql = "";
		List list = new ArrayList();
		try {   
			sql = getSql(provCode);
			log.info("分布图中烟工业地图业务数据处理sql: "+sql.toString());
			list = dbbean.executeQuery(sql, IndustryVO.class.getName()); 
		}catch (Exception e) {
			log.info("*************************");
			log.info("分布图中烟工业地图业务sql执行异常:"+e);
			log.info("*************************");
			log.error("分布图中烟工业地图业务sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		//获取前台参数
		String provCode = getParameter(request, "provCode", ""); 	//中烟工业单位编码
		log.info("传入参数： provCode:"+provCode);
		List list = getIndustyData(provCode);
		String result = StringUtil.getJson(list);
		log.info("分布图中烟工业地图业务查询结果："+result);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", result);
		
		return request;
	}
	
	public static void main(String[] args) {
		
	}
}
