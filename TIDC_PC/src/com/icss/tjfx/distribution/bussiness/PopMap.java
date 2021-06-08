package com.icss.tjfx.distribution.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.util.StringUtil;
import com.icss.tjfx.distribution.vo.PopMapVO;

/**
 * 分布图品牌地图业务类
 * 
 * @author 赵峰
 * @version 1.0
 * 
 * */
public class PopMap extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(PopMap.class);	
	
	//分布图品牌地图业务
	public static String getSql(String brandCode){
		StringBuffer sql = new StringBuffer();
		/*sql.append(" SELECT PROV_NAME as PROVNAME,PROV_ORG_NAME AS PROVORGNAME,NVL(BS_SELLNUM, 0) as SELLNUM,DECODE(NVL(BS_SELLNUM, 0),0,0,1) as ISCOVER");
		sql.append(",'").append(brandCode).append("' as brandcode, I_PROVINCE as provcode ");
		sql.append(" FROM ( SELECT I_PROVINCE,DECIMAL(ROUND(SUM(BS_SELLNUM)*1.0000/50000,2), 16, 2) AS BS_SELLNUM");
		sql.append(" FROM NICK_K_DS_TJIB_Y_ALL K,(SELECT C_BRAND,C_CIG,CIG_MARKNAME FROM VIEW_NICK_CIG_PROPERTY WHERE 1=1 ");
		if(brandCode!=null && !"0000".equals(brandCode)){
			sql.append(" AND C_CIG = '").append(brandCode).append("'");//参数
		}
		sql.append(" ) P WHERE 1=1 AND K.C_BRAND = P.C_BRAND AND Y = YEAR(CURRENT DATE-1 DAYS) GROUP BY I_PROVINCE) ");
		sql.append(" RIGHT JOIN MA_T_PROV_ORG ON I_PROVINCE = PROV_CODE ORDER BY SELLNUM DESC WITH UR");
*/		
		
		sql.append("  	SELECT											");								
		sql.append("  	I_PROVINCE as provcode									");
		sql.append("  	,PROV_NAME as PROVNAME 									");
		sql.append("  	,PROV_ORG_NAME	AS PROVORGNAME								");
		sql.append("  	,NVL(BS_SELLNUM, 0)  as SELLNUM								");
		sql.append(" 	,'").append(brandCode).append("' as brandcode	");
		sql.append("  	,DECODE(NVL(BS_SELLNUM, 0),0,0,1) AS ISCOVER						");
		sql.append("  	FROM											");
		sql.append("  	(											");
		sql.append("  	SELECT 											");
		sql.append("  		I_PROVINCE									");
		sql.append("  		,DECIMAL(ROUND(SUM(BS_SELLNUM)*1.0000/50000,2), 16, 2) AS BS_SELLNUM		");
		sql.append("  	FROM NICK_K_DS_TJIB_Y_ALL K,								");
		sql.append("  		(										");
		sql.append("  			SELECT 									");
		sql.append("  				C_BRAND								");
		sql.append("  				,C_CIG								");
		sql.append("  				,CIG_MARKNAME 							");
		sql.append("  			FROM cig_property_div 							");
		sql.append("  			WHERE C_CIG = '").append(brandCode).append("'");
		sql.append("  		) P,NICK_TJYY_CIGARETTE A, DIM_PRODUCE_ORG S				");
		sql.append("  	WHERE 1=1										");
		sql.append("  		AND K.C_BRAND = P.C_BRAND					");
		sql.append("  		AND  A.CIG_PRODUCER=S.PRODUCE_CODE				");
		sql.append("  		AND A.CIG_CODEBAR = K.C_BRAND					");																		
		sql.append("  		 AND A.CIG_BARCARRIER='02'					");																		
		sql.append("  		AND  ((D_YEAR<='2017' AND A.CIG_PRODUCTTYPE NOT in('05','06')) OR (D_YEAR>='2018' AND A.is_bsnx='1'))			");																		
		sql.append("  		AND K.Y = YEAR(CURRENT DATE -1 DAYS)						");
		sql.append("  	GROUP BY I_PROVINCE									");
		sql.append("  	)											");
		sql.append("  	RIGHT JOIN MA_T_PROV_ORG								");
		sql.append("  	ON I_PROVINCE = PROV_CODE								");
		sql.append("  	ORDER BY SELLNUM DESC									");
		sql.append("  	WITH UR											");

		log.info("分布图品牌地图业务数据处理sql: "+sql.toString());
		return sql.toString();
	}
	
	public List getPopMapData(String brandCode){
		DBBeanBase dbbean = new DBBeanBase("dw");
		String sql = "";
		List list = new ArrayList();
		try {  
			sql = getSql(brandCode);
			list = dbbean.executeQuery(sql, PopMapVO.class.getName()); 
		}catch (Exception e) {
			log.info("*************************");
			log.info("分布图品牌地图业务sql执行异常:"+e);
			log.info("*************************");
			log.error("分布图品牌地图业务sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return list;
	}
	
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		
		//获取前台参数
		String brandCode = getParameter(request, "brandCode", ""); 	//品牌规格编码
		log.info("传入参数： brandCode:"+brandCode);
		log.info("传入参数： dbName:"+"dw");
		List list =  getPopMapData(brandCode);
		//设置所有品牌数据
		String result = StringUtil.getJson(list);
		log.info("分布图品牌地图业务查询结果："+result);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", result);
		
		return request;
	}
}
