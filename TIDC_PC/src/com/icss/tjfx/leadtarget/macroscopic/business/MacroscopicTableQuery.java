package com.icss.tjfx.leadtarget.macroscopic.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.tjfx.leadtarget.macroscopic.vo.MacroscopicTableDataVO;
/**
 * 先行指标宏观查询方法类
 * @author lcx
 *
 */
public class MacroscopicTableQuery extends BaseBusiness{
	
	private static Log log = LogFactory.getLog(MacroscopicTableQuery.class);
	
	/**
	 * 获取先行指标宏观表格查询sql
	 * @return 
	 */
	public static String getSqlMacroscopic(){
		StringBuffer sql = new StringBuffer();
		/*sql.append("SELECT      ");
		sql.append("	M 																AS M        ");
		sql.append("	,DECODE(GDP			    ,NULL,'-',0,'-',CHAR(DECIMAL(GDP			  ,16,2)))  AS GDP			               ");
		sql.append("    ,DECODE(GDP_1		    ,NULL,'-',0,'-',CHAR(DECIMAL(GDP_1		  ,16,2)))  AS GDP_1		             ");
		sql.append("    ,DECODE(GDP_2		    ,NULL,'-',0,'-',CHAR(DECIMAL(GDP_2		  ,16,2)))  AS GDP_2		             ");
		sql.append("    ,DECODE(GDP_3		    ,NULL,'-',0,'-',CHAR(DECIMAL(GDP_3		  ,16,2)))  AS GDP_3		             ");
		sql.append("    ,DECODE(GNP			    ,NULL,'-',0,'-',CHAR(DECIMAL(GNP			  ,16,2)))  AS GNP			             ");
		sql.append("    ,DECODE(GNP_TOWN		,NULL,'-',0,'-',CHAR(DECIMAL(GNP_TOWN	  ,16,2)))  AS GNP_TOWN	             ");
		sql.append("    ,DECODE(GNP_COUNTRY	    ,NULL,'-',0,'-',CHAR(DECIMAL(GNP_COUNTRY	  ,16,2)))  AS GNP_COUNTRY	 ");
		sql.append("    ,DECODE(INDU_ADD_ATM	,NULL,'-',0,'-',CHAR(DECIMAL(INDU_ADD_ATM  ,16,2)))  AS INDU_ADD_ATM     ");
		sql.append("    ,DECODE(CONS_SALE_ATM   ,NULL,'-',0,'-',CHAR(DECIMAL(CONS_SALE_ATM ,16,2)))  AS CONS_SALE_ATM  ");
		sql.append("    ,DECODE(CPI			    ,NULL,'-',0,'-',CHAR(DECIMAL(CPI			  ,16,2)))  AS CPI			             ");
		sql.append("    ,DECODE(CPI_TOBACCO     ,NULL,'-',0,'-',CHAR(DECIMAL(CPI_TOBACCO   ,16,2)))  AS CPI_TOBACCO    ");
		sql.append("FROM MA_T_MACRO_ECONOMY_Y_M_ALL     ");
		sql.append("WHERE Y = YEAR(CURRENT DATE-1 DAYS)        ");
		sql.append("ORDER BY M    ");
		sql.append("WITH UR      ");
		*/
		sql.append(" 	select																													");
		sql.append(" 	A.M																													");
		sql.append(" 	,DECODE(DECODE(GDP,0,0,(GDP - GDP_L)),0,'-',  TO_CHAR(DECIMAL(round(((GDP - GDP_L)/(case GDP_L when 0 then 1 else GDP_L end ))*100.00,2),16,2),'999990.99'))  as GDP	");
		sql.append(" 	,DECODE(DECODE(GDP_1,0,0,(GDP_1 - GDP_1_L)),0,'-', TO_CHAR(DECIMAL(round(((GDP_1 - GDP_1_L)/(case GDP_1_L when 0 then 1 else GDP_1_L end ))*100.00,2),16,2),'999990.99'))  as GDP_1	");
		sql.append(" 	,DECODE(DECODE(GDP_2,0,0,(GDP_2 - GDP_2_L)),0,'-', TO_CHAR(DECIMAL(round(((GDP_2 - GDP_2_L)/(case GDP_2_L when 0 then 1 else GDP_2_L end ))*100.00,2),16,2),'999990.99'))  as GDP_2	");
		sql.append(" 	,DECODE(DECODE(GDP_3,0,0,(GDP_3 - GDP_3_L)),0,'-', TO_CHAR(DECIMAL(round(((GDP_3 - GDP_3_L)/(case GDP_3_L when 0 then 1 else GDP_3_L end ))*100.00,2),16,2),'999990.99'))  as GDP_3	");
		sql.append(" 	,DECODE(DECODE(GNP,0,0,(GNP - GNP_L)),0,'-', TO_CHAR(DECIMAL(round(((GNP - GNP_L)/(case GNP_L when 0 then 1 else GNP_L end ))*100.00,2),16,2),'999990.99'))  as GNP	");

		sql.append(" 	,CASE WHEN GNP_TOWN=0 	   or GNP_TOWN_L=0 THEN'-' ELSE TO_CHAR(DECIMAL(round(((GNP_TOWN - GNP_TOWN_L)/GNP_TOWN_L)*100.00,2),16,2),'999990.99') END as GNP_TOWN							");
		sql.append(" 	,CASE WHEN GNP_COUNTRY=0   or GNP_COUNTRY_L=0 THEN'-'ELSE TO_CHAR(DECIMAL(round(((GNP_COUNTRY - GNP_COUNTRY_L)/GNP_COUNTRY_L)*100.00,2),16,2),'999990.99') END as GNP_COUNTRY				");
		sql.append(" 	,CASE WHEN INDU_ADD_ATM=0  or INDU_ADD_ATM_L=0 THEN'-'ELSE TO_CHAR(DECIMAL(round(((INDU_ADD_ATM - INDU_ADD_ATM_L)/INDU_ADD_ATM_L)*100.00,2),16,2),'999990.99') END as INDU_ADD_ATM			");
		sql.append(" 	,CASE WHEN CONS_SALE_ATM=0 or CONS_SALE_ATM_L=0 THEN'-'ELSE TO_CHAR(DECIMAL(round(((CONS_SALE_ATM - CONS_SALE_ATM_L)/CONS_SALE_ATM_L)*100.00,2),16,2),'999990.99') END as CONS_SALE_ATM		");
		sql.append(" 	,DECODE(CPI,0,'-', TO_CHAR(DECIMAL(round(CPI,2),16,2),'999990.99'))  as CPI																				");
		sql.append(" 	,DECODE(CPI_TOBACCO,0,'-', TO_CHAR(DECIMAL(round(CPI_TOBACCO,2),16,2),'999990.99')) as 	CPI_TOBACCO																	");
		sql.append("   	from 																																																						 ");
		sql.append("   	(																																																						 ");
		sql.append("   	select 																																																						 ");
		sql.append("   	Y  																																																						 ");
		sql.append("   	,M																																																						 ");
		sql.append("   	,GDP  as GDP																																																					 ");
		sql.append("   	,GDP_1 as GDP_1                     																																																		 ");
		sql.append("   	,GDP_2 as GDP_2         																																																			 ");
		sql.append("   	,GDP_3 as GDP_3																																																					 ");
		sql.append("   	,GNP  as GNP                																																																			 ");
		sql.append("   	,GNP_TOWN as GNP_TOWN         																																																			 ");
		sql.append("   	,GNP_COUNTRY as GNP_COUNTRY																																																			 ");
		sql.append("   	,INDU_ADD_ATM  as INDU_ADD_ATM  																																																		 ");
		sql.append("   	,CONS_SALE_ATM as CONS_SALE_ATM																																																			 ");
		sql.append("   	,CPI as CPI		    																																																			 ");
		sql.append("   	,CPI_TOBACCO as  CPI_TOBACCO 																																																			 ");
		sql.append("   																																																							 ");
		sql.append("   																																																							 ");
		sql.append("   	from MA_T_MACRO_ECONOMY_Y_M_ALL where Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-1)  else  YEAR(CURRENT TIMESTAMP) end ) and M <= (case MONTH(CURRENT TIMESTAMP) when 1 then 12 else MONTH(CURRENT TIMESTAMP)-1 end)																								 ");
		sql.append("   	)A																																																						 ");
		sql.append("   	INNER JOIN(																																																					 ");
		sql.append("   	select 																																																						 ");
		sql.append("   	Y																																																						 ");
		sql.append("   	,M																																																						 ");
		sql.append("   	,GDP  as GDP_L																																																					 ");
		sql.append("   	,GDP_1 as GDP_1_L                      																																																		 ");
		sql.append("   	,GDP_2 as GDP_2_L         																																																			 ");
		sql.append("   	,GDP_3 as GDP_3_L																																																				 ");
		sql.append("   	,GNP  as GNP_L                																																																			 ");
		sql.append("   	,GNP_TOWN as GNP_TOWN_L         																																																		 ");
		sql.append("   	,GNP_COUNTRY as GNP_COUNTRY_L																																																			 ");
		sql.append("   	,INDU_ADD_ATM  as INDU_ADD_ATM_L																																																		 ");
		sql.append("   	,CONS_SALE_ATM as CONS_SALE_ATM_L																																																		 ");
		sql.append("   	,CPI as CPI_L          																																																				 ");
		sql.append("   	,CPI_TOBACCO as  CPI_TOBACCO_L 																																																			 ");
		sql.append("   																																																							 ");
		sql.append("   	from MA_T_MACRO_ECONOMY_Y_M_ALL where Y = (case MONTH(CURRENT TIMESTAMP) when 1 then (YEAR(CURRENT TIMESTAMP)-2)  else  YEAR(CURRENT TIMESTAMP)-1 end ) and M <= (case MONTH(CURRENT TIMESTAMP) when 1 then 12 else MONTH(CURRENT TIMESTAMP)-1 end)																								 ");
		sql.append("   	)B																																																						 ");
		sql.append("   	on A.M = B.M																																																					 ");
		sql.append("   	order by A.M																																																					 ");
		sql.append("   	with ur																																																						 ");
		log.info("先行指标宏观初始表格查询sql为："+sql);
		return sql.toString();
	}
	
	
	
	/**
	 * 查询先行指标宏观初始表格数据信息
	 * @param dbName
	 * @return List
	 */
	public List<MacroscopicTableDataVO> getTransactionInfo(String dbName){
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String sql = "";
		List<MacroscopicTableDataVO> MacroscopicInfoList = new ArrayList<MacroscopicTableDataVO>();
		try {
			sql = getSqlMacroscopic();
			MacroscopicInfoList = dbbean.executeQuery(sql, MacroscopicTableDataVO.class.getName());
		} catch (Exception e) {
			log.info("*************************");
			log.info("查询先行指标宏观初始表格数据信息sql执行异常:"+e);
			log.info("*************************");
			log.error("查询先行指标宏观初始表格数据信息sql执行异常", e);
		}finally{
			dbbean.close();
		}
		return MacroscopicInfoList;
	}
	
	
	@Override
	public HttpServletRequest handle(HttpServletRequest request)
			throws Exception {
		List<MacroscopicTableDataVO> TransactionTableList=null;
		Gson gson = new Gson();
		
		TransactionTableList = getTransactionInfo("dw");
		
		String s = gson.toJson(TransactionTableList);
		log.info("先行指标交易模块表格查询结果："+s);
		
		request.setAttribute("ajxa", "Y");
		request.setAttribute("result", s);
		return request;
	}
}
